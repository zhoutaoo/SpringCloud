package com.springboot.cloud.gateway.filter;



import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.Constants;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import org.reactivestreams.Publisher;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.spring.web.json.Json;

/**
 * @desc 服务层异常过滤器
 * @Auth 姚仲杰
 * @Date 2019/11/5 15:56
 **/
@Component
@Slf4j
public class ServiceExceptionFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                HttpHeaders headers = getHeaders();
                List<String> errCode = headers.get(Constants.SYSTEM_ERROR_CODE);
                // 如果请求头带有服务抛出的异常统一处理
                if (errCode != null && errCode.size() != 0
                        && errCode.get(0).equals(String.valueOf(SystemErrorType.SYSTEM_ERROR.getCode()))) {
                    log.debug("{}:{}",Constants.SYSTEM_ERROR_CODE,errCode);
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        DataBufferUtils.release(join);
                        String exMessage = new String(content, Charset.forName("UTF-8"));
                        log.error("gateway catch service exception:{}", exMessage);
                        Result result=new Result(SystemErrorType.SYSTEM_ERROR);
                        ObjectMapper mapper=new ObjectMapper();
                        byte[] newRs = new byte[0];
                        try {
                            newRs = mapper.writeValueAsString(result).getBytes("utf-8");
                        } catch (JsonProcessingException e) {
                            log.error("json convert error",e.getMessage());
                        } catch (UnsupportedEncodingException e) {
                            log.error("UnsupportedEncodingException",e.getMessage());
                        }
                        // 如果不重新设置长度则收不到消息
                        originalResponse.getHeaders().setContentLength(newRs.length);
                        return bufferFactory.wrap(newRs);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }
}

