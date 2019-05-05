package com.example.demo.service;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Transactional
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    private  AccountRepository accountRepository;

    @Autowired
    public JpaUserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String accountid) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountid(accountid);
        if (Objects.isNull(account)) {
            return new Account();
        }
        return account;
    }
//    @Override
//    public UserDetails loadUserByUsername(String accountid) throws UsernameNotFoundException {
//        Account account;
//        try {
//            account = accountRepository.findByAccountid(accountid);
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("It could not be got the user");
//        }
//        if (account == null) {
//            throw new UsernameNotFoundException("User not found with Username : " + accountid);
//        }
//        return account;
//    }
}
