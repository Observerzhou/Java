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
 * @description: 数据源2 配置
 * @author: zhouhuihui
 * @date: 2022/08/27
 */
@MapperScan(basePackages = "com.example.template.mapper.second", sqlSessionFactoryRef = "secondSqlSessionFactory")
@Configuration
public class SecondDataSourceConfig {

    @Primary  // 默认数据源
    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource getSecondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 配置mapping所在目录
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/second/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
