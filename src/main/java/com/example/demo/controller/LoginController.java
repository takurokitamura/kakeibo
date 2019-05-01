package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/templates/login")
public class LoginController {
    @RequestMapping(value="")
    public String login() {
        return "templates/login/loginForm";
    }
}
