package com.myproject.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface MailMapper {
	
	Map<String, String> getAccount(); 
}
