package com.myproject.util;

import java.util.List;

/**
 * 分页工具
 * @author hutao
 *
 */
public class Pager {
	
	private int pageSize = 5;
	private int pageNo = 1;
	private int totalItems;
	private int totalPage;
	private List<?> result;
	
	
	/**
	 * 获取每页显示的记录数
	 * @return
	 */
	public int getPageSize(){
		return this.pageSize;
	}
	
	/**
	 * 设置每页显示的记录数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取总的记录数
	 * @return
	 */
	public int getTotalItems(){
		return this.totalItems;
	}
	
	/**
	 * 设置总的记录书
	 * @param totalItems
	 */
	public void setTotalItems(int totalItems){
		this.totalItems = totalItems;
	}
	
	/**
	 * 获取总的页数
	 * @return
	 */
	public int getTotalPage(){
		return this.totalPage;
	}
	
	/**
	 * 设置总的页数
	 * @param totalItems
	 */
	public void setTotalPage(){
		if(getTotalItems() % getPageSize()==0){
			this.totalPage = getTotalItems() / getPageSize();
		}else{
			this.totalPage = getTotalItems() / getPageSize()+1;
		}
	}
	
	/**
	 * 获取当前页数
	 * @return
	 */
	public int getPageNo(){
		return this.pageNo;
	}
	
	/**
	 * 设置当前的页数
	 * @param pageNoStr
	 */
	public void setPageNo(int pageNo){
		if(pageNo<=1 || pageNo>getTotalPage()){
			this.pageNo = 1;
		}else{
			this.pageNo = pageNo;
		}
	}
	
	/**
	 * 获取具体内容
	 * @return
	 */
	public List<?> getResult(){
		return this.result;
	}
	
	/**
	 * 设置具体的内容
	 * @param list
	 */
	public void setResult(List<?> list){
		this.result = list;
	}
	
	/**
	 * 首页
	 * @return
	 */
	public int doFirst(){
		return 1;
	}
	
	/**
	 * 尾页
	 * @return
	 */
	public int doLast(){
		return getTotalPage();
	}
	
	
	public Pager() {
		
	}
	
	public Pager(String pageNoStr){
		if(pageNoStr!=null){
			try {
				this.pageNo=Integer.parseInt(pageNoStr);
			} catch (Exception e) {
				this.pageNo=1;
			}
		}
	}
	
}
