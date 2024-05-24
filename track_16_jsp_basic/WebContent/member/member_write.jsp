<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>[등록]</title>
	<script type="text/javascript">
		function goSave(){
			var id = mem.t_id.value;
			if(id == ""){
				alert("typing ID");
				mem.t_id.focus();
				return;
			}
			
			if(mem.t_name.value == ""){
				alert("typing name");
				mem.t_name.focus();
				return;
			}
			
			if(mem.t_area.value == ""){
				alert("typing area code");
				mem.t_area.focus();
				return;
			}
			
			mem.method="post"; //post, get
			mem.action="db_member_save.jsp";
			mem.submit();
		}
		
		function goList(){
			location.href="member_list.jsp";
		}
	</script>
	
</head>
<body>
<form name="mem">
	<table border="1" width="500">
		<caption>회원등록</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>ID</th>
			<td><input type="text" name="t_id" size="10"></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="t_name" size="10"></td>
		</tr>
		<tr>
			<th>area</th>
			<td><input type="text" name="t_area" size="10"></td>
		</tr>
		<tr>
			<th>age</th>
			<td><input type="text" name="t_age" size="10"></td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="button" onclick="goList()" value="list">
			<input type="button" onclick="goSave()" value="save">
			</th>
		</tr>
	</table>
</form>	
</body>
</html>