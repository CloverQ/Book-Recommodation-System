package com.sakura.book_recommodation.exception;

public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST("The user name already exists."),
    LOGIN_USER_ERROR("The user name does not exist or the password is incorrect."),
    VOTE_REPEAT("You've already liked it."),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
