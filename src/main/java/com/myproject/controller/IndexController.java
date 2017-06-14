package com.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/list")
	public String list(){
		return "index";
	}
	
	@RequestMapping("/page")
	public String page(){
		return "page";
	}
	
	@RequestMapping("/code")
	public String code(){
		return "code";
	}
}
