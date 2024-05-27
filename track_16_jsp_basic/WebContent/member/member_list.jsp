<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	Integer.parseInt("60");
	Scanner sc = new Scanner(System.in);

 	MemberDao dao = new MemberDao();
	ArrayList<MemberDto> dtos = dao.getListAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<script type="text/javascript">
	function goView(id){
		member.t_id.value=id;
		member.method="post";
		member.action="member_view.jsp";
		member.submit();
	}
</script>

</head>
<body>
<form name="member">
	<input type="hidden" name="t_id">
</form>

	<table border="1" width="800">
		<caption>Member List</caption>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tr>
			<td colspan="4">
				<select>
					<option>ID</option>
					<option>name</option>
				</select>
				<input type="text" size="5">
				<input type="button" value="search">
			</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>name</th>
			<th>area</th>
			<th>age</th>
		</tr>
<%		for(MemberDto dto: dtos){ %>
		<tr>
			<td><a href="member_view.jsp?t_id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
			<td><a href="javascript:goView('<%=dto.getId() %>')"><%=dto.getName() %></a></td>
			<td><%=dto.getArea_name() %></td>
			<td><%=dto.getAge() %></td>
		</tr>
<% 		}%>
		
		<tr>
			<td colspan="4" style="text-align:center">
				<a href="member_write.jsp">[등록]</a>
			</td>
		</tr>
		
	</table>
</body>
</html>