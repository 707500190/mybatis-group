package com.dragon.mapper;


import com.dragon.entity.Content;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2022/11/29
 */
public interface ContentMapper {

    List<Content> queryContent(@Param("p1") String p1, @Param("p2") String p2);

    void update(String title ,Integer id);

}
