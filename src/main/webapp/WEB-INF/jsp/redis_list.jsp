<%@ include file="common.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redis</title>
</head>
<body>
	<button onclick="getRedis()">连接Redis服务器</button>
	<a href="${base }/return">返回首页</a>
	<br><br>
	<button onclick="add()">增加记录</button>
	<button onclick="query()">查询记录</button>
	<button onclick="update()">修改记录</button>
	<button onclick="deleteAll()">清空redis</button>
	<button onclick="close()" id="close">关闭redis连接池</button>
	<button onclick="clear()" id="clear">清空日志</button>
	<br><br>
	<textarea style="width: 500px;height: 300px;font-size: 18px;" readonly="readonly" id="console" wrap="physical"></textarea>
</body>

<script type="text/javascript">
	
	var html = "";
	
	$(function(){
		
	});
	
	function getRedis(){
		var url = "${base}/redis/connection.json";
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:null,
			success:function(data){
				if(data!=null){
					if(data.Ok==undefined || !data.Ok ){
						html += "连接失败！\n";
					}
					html += data.message+"\n";
					html += data.redis+"\n";
					html += "\n";
					$("#console").html(html);
				}
			},
			error:function(){
				alert("连接失败！");
			}
		});
	}
	
	function add(){
		var url = "${base}/redis/add.json";
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:null,
			success:function(data){
				if(data!=null){
					for(var i=0;i<data.message.length;i++){
						html += data.message[i]+"\n";
						$("#console").html(html);
					}
					html += "\n";
				}
			},
			error:function(){
				alert("添加失败！");
			}
		});
	}
	
	function update(){
		var url = "${base}/redis/update.json";
		alert("该方法未实现....");
	}
	
	function query(){
		alert("该方法未实现....");
	}
	
	function deleteAll(){
		var url = "${base}/redis/delete";
		$.post(url,null,function(data){
			if(data!=null){
				html += "success,数据全部清空完成...\n";
				html += "\n";
				$("#console").html(html);
			}
		});
	}
	
	$("#close").click(function(){
		var url = "${base}/redis/close";
		$.post(url,null,function(){
			html += "redisPool关闭成功\n";
			html += "\n";
			$("#console").html(html);
		});
	});
	
	$("#clear").click(function(){
		html = "";
		$("#console").html("");
	});
</script>
</html>