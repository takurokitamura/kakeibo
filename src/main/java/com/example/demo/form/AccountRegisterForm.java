package main.java.com.example.demo.form;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class AccountRegisterForm implements Serializable {

    @Size(min = 3, max = 15, message = "{error.size.min.max}")
    private String accountId;

    private String password;

    private String confirmPassword;

    @Size(max = 45, message = "{error.size.max}")
    private String name;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

