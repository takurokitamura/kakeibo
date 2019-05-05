package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.Objects;

    @Controller
    @RequestMapping("/top")
    public class TopController {

        private final TopService service;
        private final HttpSession session;
        private static final String SESSION_FORM_ID = "account";

        @Autowired
        public TopController(TopService topService, HttpSession session) {
            this.service = topService;
            this.session = session;
        }

        @RequestMapping(value = "")
        public String init(@AuthenticationPrincipal Account account) {
            if (Objects.isNull(session.getAttribute(SESSION_FORM_ID))) {
                Account sessionAccount = service.getAccountById(account.getId());
                session.setAttribute(SESSION_FORM_ID, sessionAccount);
            }
            return "top/topForm";
        }
    }
