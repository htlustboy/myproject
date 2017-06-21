package com.myproject.controller.redis;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.service.redis.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisDataController {
	
	@Resource
	private RedisService redisService;
	
	@RequestMapping("/connection")
	@ResponseBody
	public Map<String, Object> getConnection(){
		return redisService.getConnection();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(){
		return redisService.add();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(){
		return redisService.delete();
	}
	
	@RequestMapping("/close")
	@ResponseBody
	public void close(){
		redisService.closePool();
	}
}
