<%@ include file="common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件收发</title>
</head>
<body>
	<form action="${base }/mail/send" method="post">
		发送者 : <input type="text" id="fromAccount" name="fromAccount" value="${fromAccount }"/><br><br>
		发送者密码 : <input type="password" name="fromPassword" readonly="readonly" value="${fromPassword }"/><br><br>
		接收者 : <input type="text" id="toAccount" name="toAccount"/><br><br>
		内容 : <br>
		<textarea style="width: 250px;height: 100px;" name="content" id="content"></textarea><br><br>
		<input type="submit" value="发送">&nbsp;&nbsp;<span>${message }</span><br>
		<a href="${base }/return">返回首页</a>
	</form>
</body>
</html>
