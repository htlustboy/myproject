package com.myproject.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface EditorMapper {
	
	Object query();
	
	void save(@Param("content")Object content);
}
