package com.sakura.book_recommodation.service;

import com.github.pagehelper.PageInfo;
import com.sakura.book_recommodation.VO.BookVO;
import com.sakura.book_recommodation.domain.Comments;
import com.sakura.book_recommodation.domain.CommentsExample;
import com.sakura.book_recommodation.mapper.BookMapper;
import com.sakura.book_recommodation.mapper.CommentsMapper;
import com.sakura.book_recommodation.req.CommentsQueryReq;
import com.sakura.book_recommodation.req.CommentsSaveReq;
import com.sakura.book_recommodation.resp.CommentsQueryResp;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Book2CommentsService {
    private static final Logger LOG = LoggerFactory.getLogger(Book2CommentsService.class);
    @Resource
    private BookMapper bookMapper;
    @Resource
    private CommentsMapper commentsMapper;

    public PageResp<CommentsQueryResp> list(CommentsQueryReq req) {
        CommentsExample commentsExample = new CommentsExample();
        CommentsExample.Criteria criteria = commentsExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getBookId())) {
            criteria.andBookIdEqualTo(req.getBookId());
        }
        BookVO bookVO = bookMapper.selectByBookId(req.getBookId());
        List<Comments> commentsList = bookVO.getComments();
        System.out.println(bookVO.getComments());
//        for(Comments s: commentsList) {
//            System.out.println(s.getContent());
//        }

        PageInfo<Comments> pageInfo = new PageInfo<>(commentsList);
        LOG.info("Total lines: {}", pageInfo.getTotal());
        LOG.info("Total pages: {}", pageInfo.getPages());
        List<CommentsQueryResp> list = CopyUtil.copyList(commentsList, CommentsQueryResp.class);
        PageResp<CommentsQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(CommentsSaveReq req) {
        Comments comments = CopyUtil.copy(req, Comments.class);
        commentsMapper.insert(comments);
    }
}
