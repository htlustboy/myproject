package com.myproject.controller.page;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.service.page.PageService;
import com.myproject.util.Pager;

@RequestMapping("/page")
@Controller
public class PageController {
	
	@Resource
	private PageService pageService;
	
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(value="pageNo",required=false)String pageNo){
		if(pageNo==null || pageNo.length()==0){
			pageNo = "1";
		}
		Pager pager = new Pager(pageNo);
		pager = pageService.getPageList(pager);
		model.addAttribute("pager", pager);
		return "page_list";
	}
}
