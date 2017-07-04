package com.myproject.service.shiro;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myproject.dao.ShiroMapper;
import com.myproject.model.UserEntity;

@Service
public class ShiroService {
	
	@Resource
	private ShiroMapper shiroMapper;
	
	public UserEntity getUserByName(String userName){
		return shiroMapper.getUserByName(userName);
	}
	
	public int save(String userName,String password){
		UserEntity u = new UserEntity();
		u.setUsername(userName);
		u.setPassword(password);
		u.setEnable(true);
		u.setCreateTime(new Date());
		return shiroMapper.save(u);
	}
}
