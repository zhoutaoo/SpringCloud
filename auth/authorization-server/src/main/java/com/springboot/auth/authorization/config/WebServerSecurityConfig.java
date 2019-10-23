package com.springboot.auth.authorization.config;

import com.springboot.auth.authorization.oauth2.config.MobileLoginAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.auth.authorization.oauth2.granter.MobileAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("userDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  @Qualifier("mobileUserDetailsService")
  private UserDetailsService mobileUserDetailsService;

  @Autowired
  private MobileLoginAuthenticationSecurityConfig mobileLoginAuthenticationSecurityConfig;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests()
        .antMatchers("/actuator/**","/auth/login","/sc/login","/mobile/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/sc/login")
        .permitAll()
        .and()
        .apply(mobileLoginAuthenticationSecurityConfig);
  }

  /**
   * 注入自定义的userDetailsService实现，获取用户信息，设置密码加密方式
   *
   * @param authenticationManagerBuilder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    // 设置手机验证码登陆的AuthenticationProvider
    authenticationManagerBuilder.authenticationProvider(mobileAuthenticationProvider());
  }

  /** 将 AuthenticationManager 注册为 bean , 方便配置 oauth server 的时候使用 */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 创建手机验证码登陆的AuthenticationProvider
   *
   * @return mobileAuthenticationProvider
   */
  @Bean
  public MobileAuthenticationProvider mobileAuthenticationProvider() {
    MobileAuthenticationProvider mobileAuthenticationProvider =
        new MobileAuthenticationProvider(this.mobileUserDetailsService);
    mobileAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return mobileAuthenticationProvider;
  }
}
