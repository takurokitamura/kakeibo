package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退会サービス。
 */
@Service
@Transactional
public class AccountUnsubscribeService {

    /** アカウントリポジトリ */
    private final AccountRepository accountRepository;

    @Autowired
    public AccountUnsubscribeService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 退会処理。
     *
     * @param account 退会対象のアカウント
     */
    public void delete(Account account) {
//        account.setDeleteFlag(true);
        accountRepository.save(account);
    }
}
