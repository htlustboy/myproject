package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.myproject.model.PageEntity;
import com.myproject.util.Pager;

@Component
public interface PageMapper {
	
	int getCount();
	
	List<PageEntity> getPageList(@Param("pager") Pager pager,@Param("start")int start);
}
