package com.example.demo.service;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import com.example.demo.form.AccountRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
    @Service
    public class AccountRegisterService {
        private  AccountRepository accountRepository;

        @Autowired
        public AccountRegisterService(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }
        @Transactional
        public void touroku(AccountRegisterForm accountRegisterForm) {
            Account account = new Account();
            account.setAccountId(accountRegisterForm.getAccountId());
            account.setPassword(accountRegisterForm.getPassword());
            account.setName(accountRegisterForm.getName());
            accountRepository.save(account);
        }
    }