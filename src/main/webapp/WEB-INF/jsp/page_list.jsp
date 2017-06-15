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
<span>-----${pager.result }</span>
	<form>
		<table cellpadding="10" cellspacing="0" border="1 solid">
			<thead>
				<th>序列号</th>
				<th>名称</th>
				<th>时间</th>
				<th>小数</th>
			</thead>
			<c:if test="${pager!=null}" var="pager">
<%-- 				<c:set var="list" value="${pager.result }"></c:set> --%>
<%-- 				<c:forEach items="${list}" var="list"> --%>
<!-- 					<tr> -->
<%-- 						<td>${list.id }</td> --%>
<%-- 						<td>${list.name }</td> --%>
<%-- 						<td>${list.date }</td> --%>
<%-- 						<td>${list.number }</td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<%-- 				(共<span>${pager.totalPage }</span>页, --%>
<%-- 				共<span>${pager.totalItems }</span>条记录, --%>
<%-- 				当前<span>${pager.pageNo }</span>页) --%>
<%-- 				<c:if test="${pager.isHasPrev }"> --%>
<!-- 					<a href="#">上一页</a> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${pager.isHasnext }"> --%>
<!-- 					<a href="#">下一页</a> -->
<%-- 				</c:if> --%>
			</c:if>
		</table>
	</form>
</body>
</html>