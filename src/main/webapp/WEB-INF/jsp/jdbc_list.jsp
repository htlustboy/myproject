<%@ include file="common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="contactDbByJdbc()">使用jdbc连接数据库</button>
	<br><br>
	<button onclick="add()">增加记录</button>
	<button onclick="query()">查询记录</button>
	<button onclick="update()">修改记录</button>
	<button onclick="deleteAll()">清空数据表</button>
	<br><br>
	<textarea style="width: 500px;height: 300px;font-size: 18px;" readonly="readonly" id="console" wrap="physical"></textarea>
	
</body>
<script type="text/javascript">
	var html="";
	
	function contactDbByJdbc(){
		var url = "${base}/jdbc/getConnectionByJdbc.json";
		$.post(url,null,function(data){
			if(data!=null){
				html = data.message+"\n";
				html += data.connection+"\n";
				html += "----------------------------------------\n";
				$("#console").html(html);
			}
		});		
	}
	
	function add(){
		var url = "${base}/jdbc/add.json";
		$.post(url,null,function(data){
			if(data!=null){
				html += data.message+"\n";
				html += "操作时间："+data.date+"\n";
				html += "----------------------------------------\n";
				$("#console").html(html);
			}
		});
	}
	
	function query(){
		var url = "${base}/jdbc/query.json";
		$.ajax({
			url:url,
			type:"post",
			data:null,
			dataType:"json",
			success:function(data){
				html += "查询成功,共"+data+"条记录"+"\n";
				html += "操作时间："+new Date().toLocaleString()+"\n";
				html += "----------------------------------------\n";
				$("#console").html(html);
			},
			error:function(){
				alert("未找到记录！");
			}
		});
	}
	
	function update(){
		var url = "${base}/jdbc/update.json";
		$.ajax({
			type:"POST",
			data:null,
			dataType:"json",
			url:url,
			success:function(data){
				html += "更新成功，共更新"+data+"条记录\n"
				html += "操作时间："+new Date().toLocaleString()+"\n";
				html += "----------------------------------------\n";
				$("#console").html(html);
			},
			error:function(){
				alert("更新失败！");
			}
		});
	}
	
	function deleteAll(){
		var url = "${base}/jdbc/deleteAll.json";
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:null,
			success:function(data){
				html += "数据表清空成功！共"+data+"条记录\n"
				html += "操作时间："+new Date().toLocaleString()+"\n";
				html += "----------------------------------------\n";
				$("#console").html(html);
			},
			error:function(){
				alert("清空失败");
			}
		});
	}
</script>
</html>
