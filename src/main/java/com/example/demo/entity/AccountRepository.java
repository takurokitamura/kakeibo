package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

   Account findByAccountid(String accountid);


    @Query(value = "SELECT COUNT(*) FROM accounts WHERE accountid = :accountid", nativeQuery = true)
    int countByAccountid(@Param("accountid") String accountid);

    @Query(value = "SELECT * FROM accounts", nativeQuery = true)
    List<Account> findAllAccount();
}
