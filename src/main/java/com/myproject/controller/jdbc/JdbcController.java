package com.myproject.controller.jdbc;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.service.jdbc.JDBCService;

@Controller
@RequestMapping("/jdbc")
public class JdbcController {
	
	@Resource
	private JDBCService jdbcService;
	
	@RequestMapping("/list")
	public String list(){
		return "jdbc_list";
	}
	
}
