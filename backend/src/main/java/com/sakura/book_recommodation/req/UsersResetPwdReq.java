package com.sakura.book_recommodation.req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UsersResetPwdReq {
    private Integer userId;
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "The password contains at least 6 to 32 characters and digits.")
    @NotNull(message = "password cannot be null.")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersResetPwdReq{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
