package com.myproject.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration
//指定mapper.xml对应的接口所在的包
@MapperScan(basePackages="com.myproject.dao")
public class MyBatisConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	
	@Resource
	private Environment environment;
	
	/**
	 * 创建数据源
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource getDataSource() throws Exception{
		Properties properties = new Properties();
		properties.put("driverClassName", environment.getProperty("jdbc.driverClassName"));
		properties.put("url", environment.getProperty("jdbc.url"));
		properties.put("username", environment.getProperty("jdbc.username"));
		properties.put("password", environment.getProperty("jdbc.password"));
		return DruidDataSourceFactory.createDataSource(properties);
	}
	
	/**
	 * 创建sqlSessionFactory
	 * @param ds
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//指定数据源
		sqlSessionFactoryBean.setDataSource(ds);
		//加载全局配置文件
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(environment.getProperty("mybatis.config")));
		//下边的配置仅仅用于*.xml文件，如果不需要用到xml文件，则不需要配置
		sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));
		logger.info("mybatis配置项启动完毕:"+sqlSessionFactoryBean);
		return sqlSessionFactoryBean.getObject();
	}
}
