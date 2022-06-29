package com.springboot.cloud.auth.client.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.sysadmin.facade.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "authentication-server", fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return <pre>
     * Result:
     * {
     *   code:"000000"
     *   mesg:"请求成功"
     *   data: true/false
     * }
     * </pre>
     */
    @PostMapping(value = "/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

    @PostMapping(value = "/auth/data/permission")
    Result dataAuth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestBody PermissionDTO permissionDTO);


    @Component
    class AuthProviderFallback implements AuthProvider {
        /**
         * 降级统一返回无权限
         *
         * @param authentication
         * @param url
         * @param method
         * @return <pre>
         * Result:
         * {
         *   code:"-1"
         *   mesg:"系统异常"
         * }
         * </pre>
         */
        @Override
        public Result auth(String authentication, String url, String method) {
            return Result.fail();
        }

        /**
         * 降级统一返回无权限
         *
         * @param authentication 身份验证
         * @param permissionDTO  许可dto
         * @return {@link Result}
         */
        @Override
        public Result dataAuth(String authentication,  PermissionDTO permissionDTO) {
            return Result.fail();
        }
    }
}
