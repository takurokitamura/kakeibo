package com.example.demo.controller;

import com.example.demo.form.IncomeRegisterForm;
import com.example.demo.service.IncomeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/income/register")
public class IncomeRegisterController {
    private final IncomeRegisterService service;

    @Autowired
    public IncomeRegisterController(IncomeRegisterService incomeRegisterService) {
        this.service = incomeRegisterService;
    }

    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute IncomeRegisterForm incomeRegisterForm) {


        return "money/incomeRegisterForm";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String registerConfirm(@ModelAttribute IncomeRegisterForm incomeRegisterForm) {
        return "money/incomeRegisterConfirmForm";
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String registerComplete(@ModelAttribute IncomeRegisterForm incomeRegisterForm, Model model) {
        service.register(incomeRegisterForm);
        model.addAttribute("complete","商品登録が完了しました。");
        return "money/incomeRegisterCompleteForm";
    }
}
