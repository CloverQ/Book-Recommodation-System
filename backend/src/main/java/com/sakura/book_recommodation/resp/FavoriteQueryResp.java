package com.sakura.book_recommodation.resp;

import java.util.Date;

public class FavoriteQueryResp {
    private Integer userId;
    private Integer bookId;
    private String bookTitle;
    private String imgUrl;
    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FavoriteQueryResp{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
