package com.sakura.book_recommodation.mapper;

import com.sakura.book_recommodation.domain.Book2category;
import com.sakura.book_recommodation.domain.Book2categoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Book2categoryMapper {
    long countByExample(Book2categoryExample example);

    int deleteByExample(Book2categoryExample example);

    int insert(Book2category record);

    int insertSelective(Book2category record);

    List<Book2category> selectByExample(Book2categoryExample example);

    int updateByExampleSelective(@Param("record") Book2category record, @Param("example") Book2categoryExample example);

    int updateByExample(@Param("record") Book2category record, @Param("example") Book2categoryExample example);
}