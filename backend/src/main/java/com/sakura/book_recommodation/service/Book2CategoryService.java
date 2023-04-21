package com.sakura.book_recommodation.service;

import com.sakura.book_recommodation.VO.CategoryVO;
import com.sakura.book_recommodation.domain.Book2categoryExample;
import com.sakura.book_recommodation.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class Book2CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Resource
    private CategoryMapper categoryMapper;

    public CategoryVO list(Integer bookId) {
        Book2categoryExample book2categoryExample = new Book2categoryExample();
        Book2categoryExample.Criteria criteria = book2categoryExample.createCriteria();
        if(!ObjectUtils.isEmpty(bookId)) {
            criteria.andBookIdEqualTo(bookId);
        }
        CategoryVO categoryVO = categoryMapper.selectCategoriesByBookId(bookId);
        System.out.println(bookId);
        System.out.println(categoryVO);
        return categoryVO;
    }
}
