package com.example.demo.form;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class AccountSearchForm implements Serializable {

    @Size(max = 15, message = "{error.size.max}")
    private String accountid;

    @Size(max = 45, message = "{error.size.max}")
    private String name;

    @Size(max = 255, message = "{error.size.max}")
    private String password;

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

