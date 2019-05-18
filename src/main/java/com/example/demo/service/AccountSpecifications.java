package com.example.demo.service;


import com.example.demo.entity.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


class AccountSpecifications {
    static Specification<Account> accountidContains(String accountid) {
        return StringUtils.isEmpty(accountid) ? null : new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("accountid"), "%" + accountid + "%");
            }
        };
    }

    static Specification<Account> nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    static Specification<Account> passwordContains(String password) {
        return StringUtils.isEmpty(password) ? null : new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("password"), "%" + password + "%");
            }
        };
    }

}
