package com.myproject.controller.mail;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.service.mail.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Resource
	MailService mailService;
	
	@RequestMapping("/list")
	public String list(Model model){
		Map<String, String> map = mailService.getAccount();
		model.addAttribute("fromAccount", map.get("fromAccount"));
		model.addAttribute("fromPassword", map.get("fromPassword"));
		return "mail_list";
	}
	
	@RequestMapping("/send")
	public String send(@RequestParam("fromAccount")String fromAccount,@RequestParam("fromPassword")String fromPassword,
					   @RequestParam("toAccount")String toAccount,@RequestParam("content")String content,Model model) throws Exception{
		String message = mailService.sendEmail(fromAccount, fromPassword, toAccount, content);
		model.addAttribute("message", message);
		return "mail_list";
	}
}
