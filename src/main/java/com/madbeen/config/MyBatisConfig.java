package com.madbeen.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author: madbeen
 * @date: 2022/03/20/9:59 PM
 */
@Configuration
@Import(JdbcConfig.class)
@ComponentScan(basePackages = "com.madbeen")
@PropertySource("classpath:mybatis-config.properties")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,
                                               @Value("${mybatis.mapper-locations}") String mapperLocation,
                                               @Value("${mybatis.type-aliases-package}") String typeAliasesPackage) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置 mapper 路径
        PathMatchingResourcePatternResolver classPathResource = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(classPathResource.getResources(mapperLocation));
        // 配置包扫描
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        // 配置字段驼峰映射
        sqlSessionFactoryBean.setConfiguration(new XMLConfigBuilder(Resources.getResourceAsStream("mybatis-config.xml")).parse());

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSession sqlSession(@Autowired SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
