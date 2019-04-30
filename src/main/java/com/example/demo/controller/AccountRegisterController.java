package main.java.com.example.demo.controller;

import main.java.com.example.demo.entity.Account;
import main.java.com.example.demo.entity.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
public class AccountRegisterController {
    @Autowired
    AccountRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView form(
            @ModelAttribute("accountRegisterForm") Account account,
            ModelAndView mav) {
        repository.save(account);
        return new ModelAndView("account/accountRegisterForm");
    }

    @RequestMapping(value = "/do", params = "register", method = RequestMethod.POST)
    String registerComplete(@ModelAttribute main.java.com.example.demo.form.AccountRegisterForm accountRegisterForm) {
        Account account = new Account();
        account.setAccountId(accountRegisterForm.getAccountId());
        account.setName(accountRegisterForm.getName());
        repository.save(account);
        return "account/accountRegisterCompleteForm";
    }
}
