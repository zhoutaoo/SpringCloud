package com.springboot.cloud.gateway.service.impl;

import com.springboot.cloud.core.entity.Result;
import com.springboot.cloud.gateway.provider.AuthProvider;
import com.springboot.cloud.gateway.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by zhoutaoo on 2018/5/27.
 */
@Service
@Slf4j
public class AuthService implements IAuthService {

    @Autowired
    AuthProvider authProvider;

    /**
     * Authorization认证开头是"bearer "
     */
    private final static int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";
    /**
     * jwt验签
     */
    private MacSigner verifier;

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(Result authResult) {
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        return hasPermission(authenticate(authentication, url, method));
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
        //是否无效true表示无效
        boolean invalid = Boolean.TRUE;
        String token = StringUtils.substring(authentication, BEARER_BEGIN_INDEX);
        if (StringUtils.isBlank(token))
            return invalid;

        try {
            Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
            if (null != jwt)
                invalid = Boolean.FALSE;
        } catch (InvalidSignatureException | IllegalArgumentException ex) {
            log.warn("user token has expired or signature error ");
        }
        return invalid;
    }
}
