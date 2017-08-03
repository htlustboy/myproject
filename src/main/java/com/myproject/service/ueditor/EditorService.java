package com.myproject.service.ueditor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myproject.dao.EditorMapper;
import com.myproject.emun.ResultType;

@Service
public class EditorService {
	
	@Resource
	private EditorMapper editorMapper;
	
	public Object query(){
		return editorMapper.query();
	}
	
	public String save(Object content){
		editorMapper.save(content);
		return ResultType.Success.toString();
	}
}
