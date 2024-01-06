package com.lct.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

//该注解类似于spring配置文件
@Configuration
//扫描mapper
@MapperScan(basePackages="com.lct.study.dao")
public class MyBatisConfig {
	
	@Autowired
	private Environment env;

	//1、根据配置文件，注入数据源
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix="datasource")
	public DataSource getDataSource() throws Exception{
        return new DruidDataSource();
	}
	
     //2、根据数据源创建SqlSessionFactory
	@Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
	    //其实就是封装了mybatis初始化的信息
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //指定数据源(这个必须有，否则报错)
        sqlSessionFactoryBean.setDataSource(dataSource);
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        String basePackage = env.getProperty("mybatis.typeAliasesPackage");
        String xmlPackage = env.getProperty("mybatis.mapperLocations");

        //在Mapper配置文件中在parameterType的值就不用写成全路径名了
        sqlSessionFactoryBean.setTypeAliasesPackage(basePackage);
        //指定xml文件位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(xmlPackage));
        return sqlSessionFactoryBean.getObject();
    }

    //3、根据SqlSessionFactory创建SqlSessionTemplate
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //4、创建事务管理器
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() throws Exception{
        return new DataSourceTransactionManager(getDataSource());
    }
    
}








