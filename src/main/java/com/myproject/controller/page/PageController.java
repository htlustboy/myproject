package com.myproject.controller.page;


import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myproject.service.page.PageService;
import com.myproject.util.Pager;

@RequestMapping("/page")
@Controller
public class PageController {
	
	@Resource
	private PageService pageService;
	
	@RequestMapping("/list")
	public String list(Model model,@Param("pageNo")String pageNo){
		if(pageNo==null || pageNo.length()==0){
			pageNo = "1";
		}
		Pager pager = new Pager();
		pager.setPageNo(pageNo);
		pager = pageService.getPageList(pager);
		model.addAttribute("pager", pager);
		model.addAttribute("list", pager.getResult());
		return "page_list";
	}
}
