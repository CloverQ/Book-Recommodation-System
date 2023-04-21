package com.sakura.book_recommodation.VO;

import com.sakura.book_recommodation.domain.Book2category;
import com.sakura.book_recommodation.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryVO extends Category {
    private List<Book2category> book2categories = new ArrayList<>();

    public List<Book2category> getBook2categories() {
        return book2categories;
    }

    public void setBook2categories(List<Book2category> book2categories) {
        this.book2categories = book2categories;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "book2categories=" + book2categories +
                '}';
    }
}
