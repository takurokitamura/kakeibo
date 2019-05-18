package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.form.AccountUpdateForm;
import com.example.demo.service.AccountUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/account/update")
public class AccountUpdateController {

    private final AccountUpdateService service;
    private final HttpSession session;
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public AccountUpdateController(AccountUpdateService accountUpdateService, HttpSession session) {
        this.service = accountUpdateService;
        this.session = session;
    }
    @RequestMapping(value = "/init")
    public String updateInit(Model model) {
        // セッションに格納されているアカウントをもとに、DBから最新のアカウントを取得してModelに格納する。
        Account account = (Account) session.getAttribute(SESSION_FORM_ID);
        Account targetAccount = service.getAccountById(account.getId());
        model.addAttribute("accountUpdateForm", targetAccount);
        return "account/accountUpdateForm";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute @Validated AccountUpdateForm accountUpdateForm, BindingResult bindingResult, Model model) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            return "account/accountUpdateForm";
        }
        Account account = (Account) session.getAttribute(SESSION_FORM_ID);
        Account targetAccount = service.getAccountById(account.getId());

        if (service.isNoChange(accountUpdateForm, targetAccount)) {
            bindingResult.reject("validation.noChange", "default message");
            return "account/accountUpdateForm";
        }
        String accountid = accountUpdateForm.getAccountid();
        if (!accountid.equals(targetAccount.getAccountid())) {
            if (service.isExistsAccountId(accountid)) {
                bindingResult.rejectValue("accountid", "validation.duplicate", new String[]{"アカウントID"}, "default message");
                return "account/accountUpdateForm";
            }
        }
        return "account/accountUpdateConfirmForm";
    }

    @RequestMapping(value = "/do", params = "update", method = RequestMethod.POST)
    public String doUpdate(@ModelAttribute @Validated AccountUpdateForm accountUpdateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "account/accountUpdateForm";
        }
        Account account = (Account) session.getAttribute(SESSION_FORM_ID);
        Account targetAccount = service.getAccountById(account.getId());

        if (service.isNoChange(accountUpdateForm, targetAccount)) {
            bindingResult.reject("validation.noChange", "default message");
            return "account/accountUpdateForm";
        }
        String accountid = accountUpdateForm.getAccountid();
        if (!accountid.equals(targetAccount.getAccountid())) {
            if (service.isExistsAccountId(accountid)) {
                bindingResult.rejectValue("accountid", "validation.duplicate", new String[]{"アカウントID"}, "default message");
                return "account/accountUpdateForm";
            }
        }
        targetAccount.setAccountid(accountUpdateForm.getAccountid());
        targetAccount.setName(accountUpdateForm.getName());
        targetAccount.setPassword(accountUpdateForm.getPassword());

        service.updateAccountById(targetAccount);
        Account sessionAccount = service.getAccountById(targetAccount.getId());
        session.setAttribute(SESSION_FORM_ID, sessionAccount);
        return "account/accountUpdateCompleteForm";
    }

    @RequestMapping(value = "/do", params = "back", method = RequestMethod.POST)
    public String back(@ModelAttribute AccountUpdateForm accountUpdateForm) {
        return "account/accountUpdateForm";
    }

}
