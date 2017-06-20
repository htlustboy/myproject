package com.myproject.util;

/**
 * 结果封装类
 * @author hutao
 *
 */
public class Resultinfo {
	
	private String url;
	private String message;
	private int type;
	
	public String getUrl() {
		return url;
	}
	public String getMessage() {
		return message;
	}
	public int getType() {
		return type;
	}
	
	public Resultinfo(String url) {
		this.url = url;
	}
	
	public Resultinfo(String url ,String message){
		this.url = url;
		this.message = message;
	}
	
	public Resultinfo(String url,String message,int type){
		this.url = url;
		this.message = message;
		this.type = type;
	}
}
