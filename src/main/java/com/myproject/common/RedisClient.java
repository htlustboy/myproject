package com.myproject.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis工具类
 * @author hutao
 *
 */
public class RedisClient {
	
	private JedisPool jedisPool;
	
	/**
	 * 添加
	 * @param key
	 * @param value
	 */
	public void set(String key,String value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public Object get(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedis.close();
		}
	}
	
	public JedisPool getJedisPool(){
		return jedisPool;
	}
	
	public void setJedisPool(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}
	
}
