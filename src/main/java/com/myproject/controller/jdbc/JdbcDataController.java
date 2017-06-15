package com.myproject.controller.jdbc;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.service.jdbc.JDBCService;

@RestController
@RequestMapping("/jdbc")
public class JdbcDataController {
	
	@Resource
	private JDBCService jdbcService;
	
	@RequestMapping("/getConnectionByJdbc")
	@ResponseBody
	public Map<String, String> getConnectionByJdbc(){
		return jdbcService.getConnecttionByJdbc();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> add(){
		return jdbcService.add();
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(){
		return jdbcService.query();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(){
		return jdbcService.update();
	}
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public int deleteAll(){
		return jdbcService.deleteAll();
	}
}
