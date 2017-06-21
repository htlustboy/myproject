package com.myproject.controller.i18n;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.service.i18n.I18nService;

@RequestMapping("/i18n")
@Controller
public class I18nController {

	@Resource
	private I18nService i18nService;
	
	@RequestMapping("/list")
	public String list(Model model,@RequestParam("language")String language){
		model.addAttribute("bundler", i18nService.getBundle(language));
		return "i18n_list";
	}
}
