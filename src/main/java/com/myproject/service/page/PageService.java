package com.myproject.service.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.PageMapper;
import com.myproject.model.PageEntity;
import com.myproject.util.Pager;

@Service
public class PageService {
	
	@Resource
	private PageMapper pageMapper;
	
	@Transactional
	public Pager getPageList(Pager pager){
		int totalItems = pageMapper.getCount();
		pager.setTotalItems(totalItems);
		pager.setTotalPage();
		pager.isHasNext(pager.getPageNo());
		pager.isHasPrev(pager.getPageNo());
		int start = (pager.getPageNo()-1)*pager.getPageSize();
		List<PageEntity> list =  pageMapper.getPageList(pager,start);
		pager.setResult(list);
		return pager;
	}
	
}
