package com.myproject.controller.excel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.service.excel.ExcelService;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	@Resource
	ExcelService excelService;
	
	@RequestMapping("/export")
	public String export(Model model){
		boolean result = excelService.excelExport();
		if(result){
			model.addAttribute("message", "导出成功！");
			return "success";
		}else{
			model.addAttribute("message", "导出失败！");
			return "toerror";

		}
	}
}
