package com.sakura.book_recommodation.mapper;

import com.sakura.book_recommodation.domain.User2favorite;
import com.sakura.book_recommodation.domain.User2favoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User2favoriteMapper {
    long countByExample(User2favoriteExample example);

    int deleteByExample(User2favoriteExample example);

    int insert(User2favorite record);

    int insertSelective(User2favorite record);

    List<User2favorite> selectByExample(User2favoriteExample example);

    int updateByExampleSelective(@Param("record") User2favorite record, @Param("example") User2favoriteExample example);

    int updateByExample(@Param("record") User2favorite record, @Param("example") User2favoriteExample example);
}