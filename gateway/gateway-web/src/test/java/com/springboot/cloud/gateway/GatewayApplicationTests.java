package com.springboot.cloud.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String GATEWAY_ROUTES = "gateway_routes";

    @Test
    public void contextLoads() throws JsonProcessingException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8888/header").build().toUri();
        // URI uri = UriComponentsBuilder.fromHttpUrl("http://baidu.com").build().toUri();

        //定义第一个断言
        Map<String, String> predicateParams = new HashMap<>();
        predicateParams.put("pattern", "/jd");

        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");
        predicate.setArgs(predicateParams);

        //定义Filter
        Map<String, String> filterParams = new HashMap<>();
        //该_genkey_前缀是固定的，见org.springframework.cloud.gateway.support.NameUtils类
        filterParams.put("_genkey_0", "header");
        filterParams.put("_genkey_1", "addHeader");

        FilterDefinition filter = new FilterDefinition();
        filter.setName("AddRequestHeader");
        filter.setArgs(filterParams);

        Map<String, String> filter1Params = new HashMap<>();
        filter1Params.put("_genkey_0", "param");
        filter1Params.put("_genkey_1", "addParam");

        FilterDefinition filter1 = new FilterDefinition();
        filter1.setName("AddRequestParameter");
        filter1.setArgs(filter1Params);

        RouteDefinition definition = new RouteDefinition();
        definition.setId("id");
        definition.setUri(uri);
        definition.setFilters(Arrays.asList(filter, filter1));
        definition.setPredicates(Arrays.asList(predicate));

        ObjectMapper json = new ObjectMapper();

        System.out.println("definition:" + json.writeValueAsString(definition));
        redisTemplate.opsForHash().put(GATEWAY_ROUTES, "key", json.writeValueAsString(definition));
    }

}
