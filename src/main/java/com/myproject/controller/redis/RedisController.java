package com.myproject.controller.redis;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.common.RedisClient;

@Controller
@RequestMapping("/redis")
public class RedisController {
	
	@Resource
	private RedisClient redisClient;
	
	@RequestMapping("/list")
	public String list(){
		return "redis_list";
	}
	
}
