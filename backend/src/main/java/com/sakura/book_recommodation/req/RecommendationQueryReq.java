package com.sakura.book_recommodation.req;

public class RecommendationQueryReq extends PageReq{
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RecommodationQueryReq{" +
                "userId=" + userId +
                '}';
    }
}