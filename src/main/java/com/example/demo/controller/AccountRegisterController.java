package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.form.AccountRegisterForm;
import com.example.demo.service.AccountRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/account/register")
public class AccountRegisterController {
    private  AccountRegisterService service;

    @Autowired
    public AccountRegisterController(AccountRegisterService accountRegisterService) {
        this.service = accountRegisterService;
    }

    @RequestMapping(value = "/init")
    public String init(@ModelAttribute AccountRegisterForm accountRegisterForm) {
        return "account/accountRegisterForm";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String kakunin(@ModelAttribute AccountRegisterForm accountRegisterForm) {
        return "account/accountRegisterConfirmForm";
    }

    @RequestMapping(value = "/do", params = "register",method = RequestMethod.POST)
    public String kanryo(@ModelAttribute AccountRegisterForm accountRegisterForm) {
        Account account = service.createAccount(accountRegisterForm);
        service.touroku(account);
        return "account/accountRegisterCompleteForm";
    }

    @RequestMapping(value = "/do", params = "registerBack", method = RequestMethod.POST)
    public String modoru(@ModelAttribute AccountRegisterForm accountRegisterForm) {
        return "account/accountRegisterForm";
    }
}