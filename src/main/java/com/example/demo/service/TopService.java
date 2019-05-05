package com.example.demo.service;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    @Service
    @Transactional
    public class TopService {

        private final AccountRepository accountRepository;

        @Autowired
        public TopService(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }

        public Account getAccountById(int id) {
            return accountRepository.findById(id).get();
        }
    }

