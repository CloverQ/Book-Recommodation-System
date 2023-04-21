package com.sakura.book_recommodation.controller;

import com.sakura.book_recommodation.domain.Category;
import com.sakura.book_recommodation.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.list();
    }
}
