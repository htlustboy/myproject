<jsp:include page="common.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro</title>
</head>
<body>
	<div id="login">
		<form id="l_form" action="" method="post">
			用户名：<input type="text" name="l_username">
			<br><br>
			密码：<input type="password" name="l_password">
			<br><br>
			<input type="button" value="登陆" onclick="login()">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="doRegisiter()">点击注册</a>
			<span style="color: red;">${message }</span>
		</form>
	</div>	
	
	<div id="regisiter">
		<form id="r_form" action="" method="post">
			用户名：<input id="r_username" type="text" name="username">
			<br><br>
			密码：<input id="r_password" type="password" name="password">
			<br><br>
			重复密码：<input id="r_newPassword" type="password" name="newPassword">
			<br><br>
			<input type="button" value="注册" onclick="doSubmit()">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="doLogin()">点击登陆</a>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(function(){
		$("#login").show();
		$("#regisiter").hide();
	});
	
	//注册
	function doRegisiter(){
		$("#login").hide();
		$("#regisiter").show();
	}
	
	//返回登陆
	function doLogin(){
		$("#login").show();
		$("#regisiter").hide();
	}
	
	//校验注册信息
	function doSubmit(){
		var userName = $("#r_username").val();
		var password = $("#r_password").val();
		var newPassword = $("#r_newPassword").val();
		if(userName==null||userName==""){
			alert("用户名不能为空！");
			return;
		}
		if(password==null||password==""){
			alert("密码不能为空！");
			return;
		}
		if(password!=newPassword){
			alert("两次密码输入不一致！");
			return;
		}
		$("#r_form").attr("action","/shiro/regisiter").submit();
	}
	
	function login(){
		$("#l_form").attr("action","/shiro/login").submit();
	}
	
</script>
</html>