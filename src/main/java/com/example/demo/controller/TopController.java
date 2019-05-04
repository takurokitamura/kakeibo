package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

    /**
     * トップコントローラ。
     */
    @Controller
    @RequestMapping("/top")
    public class TopController {

        /**トップサービス*/
        private final TopService service;
        /** HTTPセッション */
        private final HttpSession session;
        /** セッションキー(ログインユーザのアカウント) */
        private static final String SESSION_FORM_ID = "account";

        @Autowired
        public TopController(TopService topService, HttpSession session) {
            this.service = topService;
            this.session = session;
        }

        /**
         * トップ画面表示。
         *
         * @param account 認証されたアカウント
         * @return Path
         */
        @RequestMapping(value = "")
        public String init(@AuthenticationPrincipal Account account) {
            // 初回のアクセスなら、アカウントを検索してセッションに格納する
            if (Objects.isNull(session.getAttribute(SESSION_FORM_ID))) {
                Account sessionAccount = service.getAccountById(account.getId());
                session.setAttribute(SESSION_FORM_ID, sessionAccount);
            }
            return "top/topForm";
        }
    }
