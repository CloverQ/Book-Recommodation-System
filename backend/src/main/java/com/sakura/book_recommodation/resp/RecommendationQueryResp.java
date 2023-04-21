package com.sakura.book_recommodation.resp;

import java.math.BigDecimal;
import java.util.Date;

public class RecommendationQueryResp {
    private Integer bookId;

    private String bookTitle;

    private String imgUrl;

    private String bookIsbn;

    private BigDecimal bookRating;

    private Date publicationDate;

    private String publisher;

    private String bookDesc;

    private String bookAuthor;
    private String category;

    private Integer totalReaders;

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

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public BigDecimal getBookRating() {
        return bookRating;
    }

    public void setBookRating(BigDecimal bookRating) {
        this.bookRating = bookRating;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public Integer getTotalReaders() {
        return totalReaders;
    }

    public void setTotalReaders(Integer totalReaders) {
        this.totalReaders = totalReaders;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookQueryResp{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookRating=" + bookRating +
                ", publicationDate=" + publicationDate +
                ", publisher='" + publisher + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", category='" + category + '\'' +
                ", totalReaders=" + totalReaders +
                '}';
    }
}
