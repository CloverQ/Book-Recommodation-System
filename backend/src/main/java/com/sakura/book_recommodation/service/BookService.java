package com.sakura.book_recommodation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sakura.book_recommodation.domain.Book;
import com.sakura.book_recommodation.domain.BookExample;
import com.sakura.book_recommodation.mapper.BookMapper;
import com.sakura.book_recommodation.req.BookQueryReq;
import com.sakura.book_recommodation.req.BookSaveReq;
import com.sakura.book_recommodation.resp.BookQueryResp;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    @Resource
    private BookMapper bookMapper;

    @Resource
    private Book2CategoryService book2CategoryService;

    //查找所有图书 id or title or all
    public PageResp<BookQueryResp> list(BookQueryReq req) {
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getBookTitle())) {
            criteria.andBookTitleLike("%" + req.getBookTitle() + "%");
        }
        if(!ObjectUtils.isEmpty(req.getBookId())) {
            criteria.andBookIdEqualTo(req.getBookId());
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Book> bookList = bookMapper.selectByExample(bookExample);
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        LOG.info("Total lines: {}", pageInfo.getTotal());
        LOG.info("Total pages: {}", pageInfo.getPages());
        List<BookQueryResp> list = CopyUtil.copyList(bookList, BookQueryResp.class);
        PageResp<BookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    // 增删改查
    public void save(BookSaveReq req) {
        Book book = CopyUtil.copy(req, Book.class);
        if(ObjectUtils.isEmpty(req.getBookId())) {
            //增
            bookMapper.insert(book);
        }
        else {
            //改
            bookMapper.updateByPrimaryKey(book);
        }
    }
}
