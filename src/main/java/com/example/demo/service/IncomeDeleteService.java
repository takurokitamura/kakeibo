package com.example.demo.service;
import com.example.demo.entity.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    @Service
    @Transactional
    public class IncomeDeleteService {
        private final IncomeRepository incomeRepository;
        @Autowired
        public IncomeDeleteService(IncomeRepository incomeRepository) {
            this.incomeRepository = incomeRepository;
        }

        public void delete(int id) {
            incomeRepository.deleteById(id);
        }

    public boolean isExistsById(int id) {
            return incomeRepository.existsById(id);
        }
    }

