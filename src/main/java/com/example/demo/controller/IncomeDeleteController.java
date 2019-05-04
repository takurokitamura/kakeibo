package com.example.demo.controller;
import com.example.demo.service.IncomeDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

     @Controller
    @RequestMapping(value = "/income/delete")
    public class IncomeDeleteController {

        private final IncomeDeleteService service;

        @Autowired
        public IncomeDeleteController(IncomeDeleteService incomeDeleteService, MessageSource messageSource) {
            this.service = incomeDeleteService;
        }
        @RequestMapping(value = "/confirm", method = RequestMethod.POST)
        public String deleteConfirm(@RequestParam String incomeId, Model model) {
            model.addAttribute("incomeId", incomeId);
            return "money/incomeDeleteConfirmForm";
        }

        @RequestMapping(value = "/do", method = RequestMethod.POST)
        public String deleteComplete(@RequestParam String incomeId, Model model) {
            service.delete(Integer.parseInt(incomeId));
            return "money/incomeDeleteCompleteForm";
        }
    }
