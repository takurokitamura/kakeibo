package com.example.demo.service;
import com.example.demo.entity.Income;
import com.example.demo.entity.IncomeRepository;
import com.example.demo.form.IncomeRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
    public class IncomeRegisterService {

        private final IncomeRepository incomeRepository;

        @Autowired
        public IncomeRegisterService(IncomeRepository incomeRepository) {
            this.incomeRepository = incomeRepository;
        }

        @Transactional
        public void register(IncomeRegisterForm incomeRegisterForm) {
            Income income = new Income();
            income.setTitle(incomeRegisterForm.getTitle());
            income.setValue(incomeRegisterForm.getValue());
            income.setCategory(incomeRegisterForm.getCategory());
            income.setDate(incomeRegisterForm.getDate());
            incomeRepository.save(income);
        }

    public List<Income> findAllIncome() {
        return incomeRepository.findAllIncome();
    }
    }

