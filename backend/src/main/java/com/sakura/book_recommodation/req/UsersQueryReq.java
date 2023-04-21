package com.sakura.book_recommodation.req;

public class UsersQueryReq extends PageReq {
    private String userName;
    private Integer userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UsersQueryReq{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
