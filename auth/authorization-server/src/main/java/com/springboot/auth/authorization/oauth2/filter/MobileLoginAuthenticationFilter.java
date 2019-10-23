package com.springboot.auth.authorization.oauth2.filter;

import com.springboot.auth.authorization.oauth2.granter.MobileAuthenticationToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：zwy
 */
@Slf4j
@Data
public class MobileLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private Boolean postOnly = true;

    public final static String MOBILE_NUMBER = "mobile";
    public final static String MOBILE_CODE = "code";

    private String numberParameter = MOBILE_NUMBER;
    private String codeParameter = MOBILE_CODE;

    public MobileLoginAuthenticationFilter(){
        super(new AntPathRequestMatcher("/mobile/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        String code = obtainCode(request);
        if (mobile==null){
            mobile = "";
        }
        if (code==null){
            code = "";
        }

        mobile = mobile.trim();
        code = code.trim();

        MobileAuthenticationToken authRequest =
                new MobileAuthenticationToken(new UsernamePasswordAuthenticationToken(mobile, code));

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainMobile(HttpServletRequest request){
        return request.getParameter(numberParameter);
    }

    protected String obtainCode(HttpServletRequest request){
        return request.getParameter(codeParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setNumberParameter(String numberParameter) {
        Assert.hasText(numberParameter, "phone number parameter must not be empty or null");
        this.numberParameter = numberParameter;
    }

    public void setCodeParameter(String codeParameter) {
        Assert.hasText(codeParameter, "code parameter must not be empty or null");
        this.codeParameter = codeParameter;
    }
}
