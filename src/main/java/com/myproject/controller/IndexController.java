package com.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("base")
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/toerror")
	public String error(){
		return "toerror";
	}
	
	@RequestMapping("/return")
	public String goback(){
		return "redirect:/index";
	}
	
}
