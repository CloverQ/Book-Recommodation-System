package com.sakura.book_recommodation.VO;

import com.sakura.book_recommodation.domain.Book;
import com.sakura.book_recommodation.domain.Comments;

public class CommentsVO extends Comments {
    private Book book;
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
