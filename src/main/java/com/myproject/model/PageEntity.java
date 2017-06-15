package com.myproject.model;

import java.util.Date;

import org.springframework.boot.orm.jpa.EntityScan;

@EntityScan
public class PageEntity {
	
	private int id;
	private String name;
	private Date date;
	private Float number;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getNumber() {
		return number;
	}
	public void setNumber(Float number) {
		this.number = number;
	}
	
}
