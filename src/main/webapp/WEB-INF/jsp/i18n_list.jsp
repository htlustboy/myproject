<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际化</title>
</head>
<body>
	<a href="${base }/i18n/list?language=1">中文</a>
	&nbsp;&nbsp;
	<a href="${base }/i18n/list?language=2">英文</a>
	<br><br>
	<label>${bundler.getString("I18N_USER")}</label>
	<input type="text">
	<br>
	<label>${bundler.getString("I18N_PASSWORD")}</label>
	<input type="text">
</body>
</html>