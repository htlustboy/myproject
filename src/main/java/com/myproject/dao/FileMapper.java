package com.myproject.dao;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface FileMapper {
	
	int save(@Param("savePath")String savePath,@Param("fileName")String fileName,@Param("memo")String memo,
			 @Param("createDate")Date createDate,@Param("createUser")String createUser);	
	
	Set<Map<String, Object>> getDownLoadList();
}
