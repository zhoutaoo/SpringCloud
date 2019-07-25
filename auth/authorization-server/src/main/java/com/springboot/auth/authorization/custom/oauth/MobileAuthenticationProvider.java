package com.springboot.auth.authorization.custom.oauth;

/**
 * MobileAuthenticationProvider
 * 
 * 调用userDetailsService根据用户名查询用户信息
 * 
 * @author majie
 *
 */

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.auth.authorization.service.CustomUserDetailsService;

public class MobileAuthenticationProvider implements AuthenticationProvider {

	private CustomUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MobileAndCodeAuthenticationToken authenticationToken = (MobileAndCodeAuthenticationToken) authentication;
		
		String mobile = String.valueOf(authenticationToken.getPrincipal());
		String code = String.valueOf(authenticationToken.getCredentials());
		
		/**
		 * 这里做验证码验证，或者在loaduserbyMobile 里做 
		 * 生成短信随机码的方法我没有写(Math.random()*9+1)*1000)
		 * 可根据业务来增加，具体增加到哪一个微服务涛哥来定
		 *  code 即验证码
		 */
		
		UserDetails userDetails = userDetailsService.loadUserBymobile(mobile, code);
		MobileAndCodeAuthenticationToken authenticationResult = new MobileAndCodeAuthenticationToken(userDetails,
				userDetails.getUsername(), userDetails.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAndCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public CustomUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
