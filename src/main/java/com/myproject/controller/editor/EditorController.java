package com.myproject.controller.editor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.service.ueditor.EditorService;

@RequestMapping("/editor")
@Controller
public class EditorController {
	
	@Resource
	EditorService editorService;
	
	@RequestMapping("/list")
	public String list(Model model){
		Object content = editorService.query();
		model.addAttribute("content", content);
		return "editor_list";
	}
	
	@RequestMapping("/save")
	public String save(@RequestParam("ueditor")Object ueditor){
		editorService.save(ueditor);
		return "redirect:/editor/list";
	}
}
