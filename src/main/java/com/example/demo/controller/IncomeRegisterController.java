package com.example.demo.controller;
import com.example.demo.entity.Income;
import com.example.demo.form.IncomeRegisterForm;
import com.example.demo.service.IncomeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/income/register")
public class IncomeRegisterController {
    private final IncomeRegisterService service;

    @Autowired
    public IncomeRegisterController(IncomeRegisterService incomeRegisterService) {
        this.service = incomeRegisterService;
    }

    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute IncomeRegisterForm incomeRegisterForm,Model model) {

        List<Income> accounts = service.findAllIncome();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Income income :accounts) {
            categoryMap.put(income.getId(), income.getCategory());
        }
        model.addAttribute("categoryList", categoryMap);
        return "money/incomeRegisterForm";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String Confirm(@ModelAttribute IncomeRegisterForm incomeRegisterForm) {
        return "money/incomeRegisterConfirmForm";
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String Complete(@ModelAttribute IncomeRegisterForm incomeRegisterForm, Model model) {
        service.register(incomeRegisterForm);
        model.addAttribute("complete","収支の登録が完了しました。");
        return "money/incomeRegisterCompleteForm";
    }
}
