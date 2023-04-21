package com.sakura.book_recommodation.req;

import java.util.Date;

public class FavoriteSaveReq {
    private Integer userId;
    private Integer bookId;
    private String bookTitle;
    private String imgUrl;
    private Date creatTime;

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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "FavoriteSaveReq{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
