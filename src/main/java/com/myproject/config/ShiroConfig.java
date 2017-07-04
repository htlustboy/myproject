package com.myproject.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myproject.realm.JdbcRealm;

@Configuration
public class ShiroConfig {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	
	
	@Bean
	public EhCacheManager getEhCacheManager(){
		EhCacheManager eh = new EhCacheManager();
		eh.setCacheManagerConfigFile("classpath:ehcache.xml");
		logger.info("***********shiro缓存配置加载完毕***********");
		return eh;
	}
	
	//配置shiroFilter
	@Bean
	public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager manager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		//配置登陆的url和登陆失败的url
		bean.setLoginUrl("/shiro/list");
		bean.setSuccessUrl("/shiro/success");
		bean.setUnauthorizedUrl("/shiro/unauthor");
		//设置url的访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/shiro/list", "anon"); //访问登陆界面的入口
		filterChainDefinitionMap.put("/shiro/regisiter", "anon"); //访问登陆界面的入口
		filterChainDefinitionMap.put("/shiro/login", "anon");//登陆
		filterChainDefinitionMap.put("/shiro/logout", "anon");//登出
		filterChainDefinitionMap.put("/shiro/*", "authc");//需要权限的url路径
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		logger.info("***********shiro拦截链加载完毕***********");
		return bean;
	}
	
	//配置核心的事务安全管理器
	@Bean
	public SecurityManager securityManager(@Qualifier("jdbcRealm")JdbcRealm jdbcRealm){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(jdbcRealm);
		logger.info("***********shiro加载完毕***********");
		return manager;
	}
	
	//配置自定义的权限登陆管理器
	@Bean(name="jdbcRealm")
	public JdbcRealm jdbcRealm(@Qualifier("credentialsMatcher")CredentialsMatcher credentialsMatcher){
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setCredentialsMatcher(credentialsMatcher);
		jdbcRealm.setCacheManager(getEhCacheManager());
		return jdbcRealm;
	}
	
	//配置自定义的密码比较器
	@Bean(name="credentialsMatcher")
	public CredentialsMatcher credentialsMatcher(){
		return new com.myproject.config.CredentialsMatcher();
	}
	
	//配置生命周期进程
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}
	
	
}
