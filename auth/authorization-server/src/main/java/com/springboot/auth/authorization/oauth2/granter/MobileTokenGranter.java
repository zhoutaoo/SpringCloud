package com.springboot.auth.authorization.oauth2.granter;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author joe_chen
 * 短信验证码登陆与用户名密码登陆相似,密码为动态
 * 故继承ResourceOwnerPasswordTokenGranter
 */
public class MobileTokenGranter extends ResourceOwnerPasswordTokenGranter {

    private static final String GRANT_TYPE = "mobile";

    private AuthenticationManager authenticationManager;

    public MobileTokenGranter(AuthenticationManager authenticationManager,
                              AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                              OAuth2RequestFactory requestFactory) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        String password = parameters.get("password");
        // Protect from downstream leaks of password
        parameters.remove("password");

        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(userAuth);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = this.authenticationManager.authenticate(mobileAuthenticationToken);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, mobileAuthenticationToken);
    }
}
