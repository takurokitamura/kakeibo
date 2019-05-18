package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import com.example.demo.form.AccountSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.service.AccountSpecifications.*;

@Service
public class AccountSearchService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountSearchService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<Account> findAccount(AccountSearchForm form) {
        return accountRepository.findAll(
                Specification
                        .where(accountidContains(form.getAccountid()))
                        .and(nameContains(form.getName()))
                        .and(passwordContains(form.getPassword())));
    }

    @Transactional(readOnly = true)
    public Page<Account> findAccount(AccountSearchForm form, Pageable pageable) {
        return accountRepository.findAll(
                Specification
                        .where(accountidContains(form.getAccountid()))
                        .and(nameContains(form.getName()))
                        .and(passwordContains(form.getPassword()))
                , pageable);
    }
}
