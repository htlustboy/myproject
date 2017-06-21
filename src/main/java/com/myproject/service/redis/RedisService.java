package com.myproject.service.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.myproject.common.RedisClient;

@Service
public class RedisService {
	
	@Resource
	private RedisClient redisClient;
	
	/**
	 * 连接redis
	 * @return
	 */
	public Map<String, Object> getConnection(){
		Map<String, Object> map = new HashMap<String, Object>();
		JedisPool pool = redisClient.getJedisPool();
		Jedis jedis = pool.getResource();
		try {
			if(jedis!=null){
				map.put("message", "连接redis服务器成功");
				map.put("Ok",true);
			}
			map.put("redis",jedis.toString());
		} catch (Exception e) {
			map.put("message", e.getMessage());
			e.printStackTrace();
		}finally{
			jedis.close();
//			pool.close();
		}
		return map;
	}
	
	/**
	 * 添加操作
	 * @return
	 */
	public Map<String, Object> add(){
		Map<String, Object> map = new HashMap<String,Object>();
		JedisPool pool = redisClient.getJedisPool();
		Jedis jedis = pool.getResource();
		if(pool==null || jedis==null){
			map.put("message", "添加失败,redis连接出现问题！");
			return map;
		}
		List<String> list = new ArrayList<String>();
		//添加String
		String code = jedis.set("string", "hello world!");
		if("OK".equals(code)){
			list.add("string对象添加成功...");
		}
		//添加List
		jedis.lpush("list", "hutao");
		jedis.lpush("list", "lustboy");
		jedis.lpush("list", "timer");
		jedis.lpush("list", "hutao");
		list.add("list对象添加成功...");
		
		//添加set
		jedis.sadd("set", "one");
		jedis.sadd("set", "two");
		jedis.sadd("set", "three");
		jedis.sadd("set", "one");
		list.add("set对象添加成功...");
		
		//添加hash
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("yuwen", "100");
		hash.put("yingyu", "88");
		hash.put("shuxue", "95");
		hash.put("yuwen", "80");
		jedis.hmset("hash", hash);
		list.add("hash对象添加成功...");
		
		map.put("message",list);
		jedis.close();
		return map;
	}
	
	/**
	 * 清空数据库
	 * @return
	 */
	public Object delete(){
		Jedis jedis = null;
		try {
			JedisPool pool = redisClient.getJedisPool();
			jedis = pool.getResource();
			return jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 关闭redisPool
	 */
	public void closePool(){
		JedisPool pool = redisClient.getJedisPool();
		if(!pool.isClosed()){
			pool.close();
		}
	}
}
