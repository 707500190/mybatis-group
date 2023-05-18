package com.dragon.practice;


import com.dragon.entity.Content;
import com.dragon.mapper.ContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/4/14
 */

@Slf4j
public class Query {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = null;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;

        try {
            sqlSession = sqlSessionFactory.openSession();
            ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
            List<Content> list = mapper.queryContent("1", "2");
            System.out.println(list.toString());
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


}
