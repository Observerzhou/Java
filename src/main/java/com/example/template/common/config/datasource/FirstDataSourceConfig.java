package com.example.template.common.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @description: 数据源1 配置
 * @author: zhouhuihui
 * @date: 2022/08/27
 */
@MapperScan(basePackages = "com.example.template.mapper.first", sqlSessionFactoryRef = "firstSqlSessionFactory")
@Configuration
public class FirstDataSourceConfig {

    @Primary  // 默认数据源
    @Bean("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource getFirstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("firstSqlSessionFactory")
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 配置mapping所在目录
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/first/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("firstSqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
