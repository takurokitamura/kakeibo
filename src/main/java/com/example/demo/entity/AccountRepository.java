package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

    /**
     * アカウントIDに紐づくアカウントを検索する。
     *
     * @param accountId アカウントID
     * @return アカウント
     */
    Account findByAccountId(String accountId);

    /**
     * IDに紐づくアカウントを削除する。
     *
     * @param id ID
     */
    void deleteById(Integer id);
}
