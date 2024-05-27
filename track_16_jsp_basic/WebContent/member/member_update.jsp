<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	MemberDao dao = new MemberDao();

	String id = request.getParameter("t_id");
	MemberDto dto = dao.getMemberView(id);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change of the information</title>
<script>

	function goUpdate(){
		if(mem.t_name.value == ""){
			alert("typing the name");
			mem.t_name.focus();
			return;
		}
		
		if(mem.t_area.value == ""){
			alert("typing the area");
			mem.t_area.focus();
			return;
		}
		
		mem.method="post";
		mem.action="db_member_update.jsp";
		mem.submit();
	}

	function goList(){
		location.href="member_list.jsp;"
	}

</script>
</head>
<body>
	<form name="mem">
	<input type="hidden" name="t_id" value="<%=dto.getId() %>">
	<table border="1" width="1000">
		<caption>Detailed Info of <%=dto.getName() %></caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>Id</th>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="t_name" size="10" value="<%=dto.getName() %>"></td>
		</tr>
		<tr>
			<th>area</th>
			<td>
			<!--  <input type="text" size="10" value="<%=dto.getArea() %>"> -->
			<select name="t_area_select">
				<option value="10" <%if(dto.getArea().equals("서울")) out.print("selected"); %>>Seoul</option>
				<option value="20" <%if(dto.getArea().equals("대전")) out.print("selected"); %>>Daejeon</option>
				<option value="30" <%if(dto.getArea().equals("대구")) out.print("selected"); %>>Daegu</option>
				<option value="40" <%if(dto.getArea().equals("부산")) out.print("selected"); %>>Busan</option>
				<option value="50" <%if(dto.getArea().equals("청주")) out.print("selected"); %>>Cheongju</option>
				<option value="60" <%if(dto.getArea().equals("세종")) out.print("selected"); %>>Sejong</option>
			</select>
			
			<input value="10" type="radio" name="t_area" <%if(dto.getArea().equals("서울")) out.print("checked"); %>>Seoul &nbsp;
			<input value="20" type="radio" name="t_area" <%if(dto.getArea().equals("대전")) out.print("checked"); %>>Daejeon &nbsp;
			<input value="30" type="radio" name="t_area" <%if(dto.getArea().equals("대구")) out.print("checked"); %>>Daegu &nbsp;
			<input value="40" type="radio" name="t_area" <%if(dto.getArea().equals("부산")) out.print("checked"); %>>Busan &nbsp;
			<input value="50" type="radio" name="t_area" <%if(dto.getArea().equals("청주")) out.print("checked"); %>>Cheongju &nbsp;
			<input value="60" type="radio" name="t_area" <%if(dto.getArea().equals("세종")) out.print("checked"); %>>Sejong &nbsp;
			</td>
		</tr>
		<tr>
			<th>age</th>
			<td><input type="text" name="t_age" size="10" value="<%=dto.getAge() %>"></td>
		</tr>
		<tr>
			<th colspan="2">
				<a href="member_list.jsp">list1</a>
				<a href="javascript:goLsit()">list2</a>
				<input type="button" onclick="location.href='member_list.jsp'" value="list3">
				<input type="button" onclick="goUpdate()" value="SaveTheChange">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>
