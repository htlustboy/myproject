package com.myproject.emun;

public enum ResultType {
	Success(1),
	Fail(0);
	private int value;
	
	public int getValue(){
		return value;
	}
	
	private ResultType(int value) {
		this.value=value;
	}
	
}
