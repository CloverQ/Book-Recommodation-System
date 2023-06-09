package com.sakura.book_recommodation.mapper;

import com.sakura.book_recommodation.VO.BookVO;
import com.sakura.book_recommodation.domain.Book;
import com.sakura.book_recommodation.domain.BookExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    BookVO selectByBookId(@Param("bookId") Integer bookId);
}