package com.myproject.controller.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.model.UserEntity;
import com.myproject.service.shiro.ShiroService;
import com.myproject.util.BaseUtil;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	@Resource
	private ShiroService shiroService;
	
	@RequestMapping("/list")
	public String list(){
		return "shiro_list";
	}
	
	@RequestMapping("/regisiter")
	public String regisiter(@RequestParam("username")String userName,@RequestParam("password")String password,Model model){
		UserEntity userEntity = shiroService.getUserByName(userName);
		if(userEntity!=null){
			model.addAttribute("message", "该用户已经被注册");
			model.addAttribute("type", "regisiter");
			return "shiro_list";
		}else{
			int number = shiroService.save(userName,BaseUtil.password2Hex(userName, password));
			if(number!=0){
				model.addAttribute("message", "注册成功，注册账号为："+userName);
				return "success";
			}else{
				model.addAttribute("message", "注册失败");
				return "toerror";
			}
		}
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("l_username")String username,@RequestParam("l_password")String password,Model model){
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "toerror";
		}
		return "success";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			currentUser.logout();
		}
		return "shiro_list";
	}
	
	@RequestMapping("/unauthor")
	public String unauthor(){
		return "shiro_unautor";
	}
}
