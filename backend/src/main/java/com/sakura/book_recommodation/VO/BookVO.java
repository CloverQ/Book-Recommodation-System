package com.sakura.book_recommodation.VO;

import com.sakura.book_recommodation.domain.Book;
import com.sakura.book_recommodation.domain.Comments;

import java.util.ArrayList;
import java.util.List;

public class BookVO extends Book {
    private List<Comments> comments = new ArrayList<>();

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
