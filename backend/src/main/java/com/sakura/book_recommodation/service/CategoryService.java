package com.sakura.book_recommodation.service;

import com.sakura.book_recommodation.domain.Category;
import com.sakura.book_recommodation.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    public List<Category> list() {
        return categoryMapper.selectByExample(null);
    }
}
