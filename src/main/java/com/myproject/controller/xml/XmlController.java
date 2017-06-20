package com.myproject.controller.xml;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xml")
public class XmlController {
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("xml", "");
		return "xml_list";
	}
}
