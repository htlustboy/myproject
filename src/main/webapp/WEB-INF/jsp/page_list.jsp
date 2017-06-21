<%@ include file="common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据分页</title>
</head>
<body>
	<a href="${base }/return">返回首页</a>
	<form>
		<c:if test="${pager!=null}">
		<table cellpadding="10" cellspacing="0" border="1 solid">
			<thead>
				<th>序列号</th>
				<th>名称</th>
				<th>时间</th>
				<th>小数</th>
			</thead>
			<c:forEach items="${pager.result}" var="list"> 
				<tr>
					<td>${list.id }</td>
					<td>${list.name }</td>
					<td>${list.date }</td>
					<td>${list.number }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		共<span>${pager.totalPage }</span>页,
		共<span>${pager.totalItems }</span>条记录,
		当前第<span>${pager.pageNo }</span>页
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${pager.pageNo>1 }">
			<a href="${base }/page/list?pageNo=${pager.pageNo-1}">上一页</a>
		</c:if>
		<c:if test="${pager.pageNo<pager.totalPage }">
			<a href="${base }/page/list?pageNo=${pager.pageNo+1}">下一页</a>
		</c:if>
		</c:if>
	</form>
</body>
</html>