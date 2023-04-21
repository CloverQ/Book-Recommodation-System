package com.sakura.book_recommodation.req;

public class CommentsQueryReq {
    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "CommentsQueryReq{" +
                "bookId=" + bookId +
                '}';
    }
}
