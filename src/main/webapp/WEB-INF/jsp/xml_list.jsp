<%@ include file="common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XML文件解析</title>
</head>
<body>
	<c:forEach items="${map }" var="map">
		<label >${map.key }</label>	:
		<input type="text" value="${map.value }" id="${map.key }"><br><br>
	</c:forEach>
	<a href="${base }/return">返回首页</a>
</body>
</html>