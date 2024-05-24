<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	MemberDto dto = dao.getMemberView(id);
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detailed Information of Member</title>
<script type="text/javascript">
	function goList(){
		location.href="member_list.jsp";
	}
</script>
</head>
<body>
	<table border="1" width="500">
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>ID</th>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<th>성명</th>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<th>지역</th>
			<td><%=dto.getArea() %></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><%=dto.getAge() %></td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="button" onclick="goList()" value="list">
			<input type="button" value="modify">
			<input type="button" value="delete">
			</th>
		</tr>
	</table>
</body>
</html>