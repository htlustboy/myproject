package com.myproject.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.myproject.model.JdbcEntity;

@Component
public interface JdbcMapper {
	
	int add(@Param("jdbc")JdbcEntity jdbc);
	
	int query();
	
	int update();
	
	int deleteAll();
	
	List<JdbcEntity> getAll();
}
