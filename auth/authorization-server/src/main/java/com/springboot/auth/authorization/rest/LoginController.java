package com.springboot.auth.authorization.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：zwy
 */
@Controller
public class LoginController {

    @GetMapping("/auth/login")
    public String toLogin(){

        //授权码模式使用手机登录，返回/sc-mobile-login

        return "/sc-login";
    }
}
