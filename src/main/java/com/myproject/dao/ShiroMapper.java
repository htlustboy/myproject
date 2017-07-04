package com.myproject.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.myproject.model.UserEntity;

@Component
public interface ShiroMapper {
	
	UserEntity getUserByName(@Param("userName")String userName);
	
	int save(@Param("user")UserEntity user);
}
