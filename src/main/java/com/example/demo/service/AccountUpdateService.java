package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import com.example.demo.form.AccountUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountUpdateService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountUpdateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Transactional(readOnly = true)
    public Account getAccountById(int id) {
        Optional<Account> result = accountRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("account is not found"));
    }


    public boolean isNoChange(AccountUpdateForm accountUpdateForm, Account targetAccount) {
        return accountUpdateForm.getAccountid().equals(targetAccount.getAccountid())
                && accountUpdateForm.getName().equals(targetAccount.getName())
                && accountUpdateForm.getPassword().equals(targetAccount.getPassword());
    }

    @Transactional(readOnly = true)
    public boolean isExistsAccountId(String accountid) {
        int result = accountRepository.countByAccountid(accountid);
        return result != 0;
    }

    @Transactional
    public void updateAccountById(Account account) {
        accountRepository.save(account);
    }

}