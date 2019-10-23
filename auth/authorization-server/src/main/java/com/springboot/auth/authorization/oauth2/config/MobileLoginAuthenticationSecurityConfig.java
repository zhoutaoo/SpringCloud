package com.springboot.auth.authorization.oauth2.config;

import com.springboot.auth.authorization.oauth2.filter.MobileLoginAuthenticationFilter;
import com.springboot.auth.authorization.oauth2.granter.MobileAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ：zwy
 */
@Slf4j
@Configuration
public class MobileLoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MobileAuthenticationProvider mobileAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileLoginAuthenticationFilter mobileLoginAuthenticationFilter = new MobileLoginAuthenticationFilter();
        mobileLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mobileLoginAuthenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());

        SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        simpleUrlAuthenticationFailureHandler.setDefaultFailureUrl("/auth/login?error");
        mobileLoginAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler);

        http.authenticationProvider(mobileAuthenticationProvider).addFilterAfter(mobileLoginAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}
