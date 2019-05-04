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
        public void touroku(Account account) {
            account.setAccountId(account.getAccountId());
            accountRepository.save(account);
        }
        public Account createAccount(AccountRegisterForm accountRegisterForm) {
            Account account = new Account();
            account.setName(accountRegisterForm.getName());
            account.setPassword(accountRegisterForm.getPassword());
            account.setAccountId(accountRegisterForm.getAccountId());
            return account;
        }
    }