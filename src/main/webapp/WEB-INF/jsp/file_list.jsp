<%@ include file="common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件的上传与下载</title>
</head>
<body>
	<form action="${base }/file/upload" method="post" enctype="multipart/form-data">
		token:<input type="text" name="token" value="${token }" readonly="readonly"><br>
		文件：<input type="file" name="file"><br>
		备注：<input type="text" name="memo"><br>
		<input type="submit" value="上传">&nbsp;&nbsp;<span id="msg" style="color: red">${message}</span><br>
		<a href="${base }/file/download">查看我的下载列表</a>
		<a href="${base }/return">返回首页</a>
	</form>
	<form id="form">
		<input type="hidden" name="path"/>
	</form>
	
	<c:if test="${flag }">
		<table cellpadding="10" cellspacing="0" border="1 solid;">
			<thead>
				<th>文件名称</th>
				<th>备注</th>
				<th>操作人</th>
				<th>操作时间</th>
				<th>详情</th>
			</thead>
			<c:forEach items="${set }" var="set">
				<tr>
					<td>${set.fileName }</td>
					<td>${set.memo }</td>
					<td>${set.createDate }</td>
					<td>${set.createUser }</td>
					<td><a href="#" onclick="down(this)" id="${set.savePath }">下载</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
<script type="text/javascript">
	$(function(){
		hideMsg();
	});
	
	function hideMsg(){
		var msg = $("#msg").html();
		if(msg=="" || msg=="上传成功"){
			setTimeout("$('#msg').hide()",1000);
		}else{
			$("#msg").show();
		}
	}
	
	function down(obj){
		var url = $(obj).attr("id");
		$("input[name='path']").val(url);
		$("#form").attr("action","${base}/file/down").submit();
	}

</script>
</html>

