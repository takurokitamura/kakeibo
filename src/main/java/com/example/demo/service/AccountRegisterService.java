package com.example.demo.service;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import com.example.demo.form.AccountRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
    @Service
    public class AccountRegisterService {
        private  AccountRepository accountRepository;
        private  PasswordEncoder passwordEncoder;

        @PersistenceContext
        private EntityManager entityManager;

        @Autowired
        public AccountRegisterService(AccountRepository accountRepository,PasswordEncoder passwordEncoder){
            this.accountRepository = accountRepository;
            this.passwordEncoder = passwordEncoder;
        }
        @Transactional
        public void touroku(Account account,String rawPassword) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            account.setPassword(encodedPassword);
            account.setAccountid(account.getAccountid());
            accountRepository.save(account);
        }
        public Account createAccount(AccountRegisterForm accountRegisterForm) {
            Account account = new Account();
            account.setName(accountRegisterForm.getName());
            account.setPassword(accountRegisterForm.getPassword());
            account.setAccountid(accountRegisterForm.getAccountid());
            return account;
        }
    }