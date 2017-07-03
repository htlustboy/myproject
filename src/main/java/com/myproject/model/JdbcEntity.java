package com.myproject.model;

import java.util.Date;

import org.springframework.boot.orm.jpa.EntityScan;

@EntityScan
public class JdbcEntity {
	
	private int id;
	private String name;
	private boolean status;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public JdbcEntity(int id, String name, boolean status, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.createTime = createTime;
	}
	
	public JdbcEntity() {
		// TODO Auto-generated constructor stub
	}
	
}
