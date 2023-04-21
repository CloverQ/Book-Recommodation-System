package com.sakura.book_recommodation.req;

public class TokenReq {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenReq{" +
                "token='" + token + '\'' +
                '}';
    }
}
