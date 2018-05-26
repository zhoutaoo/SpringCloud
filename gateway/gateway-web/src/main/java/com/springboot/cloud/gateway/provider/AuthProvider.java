package com.springboot.cloud.gateway.provider;

import com.springboot.cloud.core.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhoutaoo on 2018/5/26.
 */
@Component
@FeignClient(name = "authentication-server", fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {

    @PostMapping(value = "/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam("url") String url, @RequestParam("method") String method);

    class AuthProviderFallback implements AuthProvider {
        @Override
        public Result auth(String token, String url, String method) {
            return Result.fail();
        }
    }
}
