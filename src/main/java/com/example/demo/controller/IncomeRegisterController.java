package com.example.demo.controller;

import com.example.demo.common.CodeValue;
import com.example.demo.form.IncomeRegisterForm;
import com.example.demo.service.IncomeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/income/register")
public class IncomeRegisterController {
    private final IncomeRegisterService service;
    private final CodeValue codeValue;

    @Autowired
    public IncomeRegisterController(IncomeRegisterService incomeRegisterService, CodeValue codeValue) {
        this.service = incomeRegisterService;
        this.codeValue = codeValue;
    }

    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute IncomeRegisterForm incomeRegisterForm,Model model) {

//        List<Income> incomes = service.findAllIncome();
//        Map<Integer, String> categoryMap = new HashMap<>();
//        for (Income income :incomes) {
//            categoryMap.put(income.getId(), income.getCategory());
//        }
//        model.addAttribute("categoryList", categoryMap);
//        model.addAttribute("allcategory", codeValue.getCategory());

            Map<String, String> selectMap = new HashMap<String, String>();
            selectMap.put("key_A", "食費");
            selectMap.put("key_B", "交通費");
            selectMap.put("key_C", "インフラ費");
            selectMap.put("key_D", "交際費");
            selectMap.put("key_E", "娯楽費");

           model.addAttribute("allcategory",selectMap);
        return "money/incomeRegisterForm";

    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String Confirm(@ModelAttribute IncomeRegisterForm incomeRegisterForm) {
        service.register(incomeRegisterForm);


        return "money/incomeRegisterConfirmForm";
    }

    @RequestMapping(value = "/do", params = "register", method = RequestMethod.POST)
    public String Complete(@ModelAttribute IncomeRegisterForm incomeRegisterForm, Model model) {
        service.register(incomeRegisterForm);
        model.addAttribute("complete","収支の登録が完了しました。");
        return "money/incomeRegisterCompleteForm";
    }

    @RequestMapping(value = "/do", params = "registerBack", method = RequestMethod.POST)
    String registerBack(@ModelAttribute IncomeRegisterForm incomeRegisterForm) {
        return "money/incomeRegisterForm";
    }
}
