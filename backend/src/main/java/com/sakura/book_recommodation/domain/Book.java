package com.sakura.book_recommodation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Integer bookId;

    private String bookTitle;

    private String imgUrl;

    private String bookIsbn;

    private String bookAuthor;

    private String category;

    private BigDecimal bookRating;

    private Date publicationDate;

    private String publisher;

    private String bookDesc;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", bookTitle=").append(bookTitle);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", bookIsbn=").append(bookIsbn);
        sb.append(", bookAuthor=").append(bookAuthor);
        sb.append(", category=").append(category);
        sb.append(", bookRating=").append(bookRating);
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", publisher=").append(publisher);
        sb.append(", bookDesc=").append(bookDesc);
        sb.append(", totalReaders=").append(totalReaders);
        sb.append("]");
        return sb.toString();
    }
}