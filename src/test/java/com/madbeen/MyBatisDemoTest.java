package com.madbeen;

import com.madbeen.config.MyBatisConfig;
import com.madbeen.entity.User;
import com.madbeen.repository.IUserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: madbeen
 * @date: 2022/03/20/10:45 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBatisConfig.class)
public class MyBatisDemoTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void sqlSessionOpenSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);

        User user = userRepository.queryUserByUserName("李");
        System.out.println(user);
    }

    @Test
    public void sqlSessionQuery() {

        Object user = sqlSession.selectOne("com.madbeen.repository.IUserRepository.queryUserByUserName", "李");

        System.out.println(user);

    }

}
