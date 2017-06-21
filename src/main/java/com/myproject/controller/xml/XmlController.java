package com.myproject.controller.xml;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.service.xml.XmlService;

@Controller
@RequestMapping("/xml")
public class XmlController {
	
	@Resource
	private XmlService xmlService;
	
	@RequestMapping("/list")
	public String list(Model model){
		Map<String, String> map = xmlService.getXmlNodes("config.xml");
		model.addAttribute("map", map);
		return "xml_list";
	}
}
