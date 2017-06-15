package com.myproject.service.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myproject.dao.JdbcMapper;
import com.myproject.model.JdbcEntity;
import com.myproject.util.BaseUtil;


@Service
public class JDBCService {
	
	@Resource
	private JdbcMapper jdbcMapper;
	
	/**
	 * 采用原生的方法获取数据库连接对象
	 * @return
	 */
	public Map<String, String> getConnecttionByJdbc(){
		Connection connection = null;
		Properties properties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
		try {
			properties.load(in);
			Class.forName(properties.getProperty("jdbc.driverClassName"));
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			connection = DriverManager.getConnection(url, username, password);
			if(connection!=null){
				Map<String, String> result = new HashMap<String, String>();
				result.put("message", "连接成功！");
				result.put("connection", connection.toString());
				result.put("catalog",connection.getCatalog());
				connection.close();
				return result;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加记录
	 * @return
	 */
	public Map<String, String> add(){
		JdbcEntity jdbc = new JdbcEntity();
		jdbc.setName("jdbc");
		jdbc.setStatus(true);
		jdbc.setCreateTime(new Date());
		Map<String, String> map = new HashMap<String, String>();
		int result = jdbcMapper.add(jdbc);
		if(result==0){
			map.put("message", "sorry,添加记录失败！");
		}else{
			map.put("message", "success,添加记录成功！");
			map.put("date", BaseUtil.date2String(new Date()));
		}
		return map;
	}
	
	/**
	 * 查询记录
	 * @return
	 */
	public int query(){
		return jdbcMapper.query();
	}
	
	/**
	 * 更新记录
	 * @return
	 */
	public int update(){
		return jdbcMapper.update();
	}
	
	/**
	 * 清空数据表
	 */
	public int deleteAll(){
		return jdbcMapper.deleteAll();
	}
}
