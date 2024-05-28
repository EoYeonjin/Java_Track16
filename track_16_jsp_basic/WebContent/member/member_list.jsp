<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	Integer.parseInt("60");
	Scanner sc = new Scanner(System.in);

 	MemberDao dao = new MemberDao();
 	String select = request.getParameter("t_select");
 	String search = request.getParameter("t_search");
 	
 	if(select == null){
 		select = "id";
 		search = "";
 	}
 	
	ArrayList<MemberDto> dtos = dao.getListAll(select, search);
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
	
	function goSearch(){
		mem.method="post";
		mem.action="member_list.jsp";
		mem.submit();
		
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
		<form name="mem">
		<tr>
			<td colspan="4">
				<select name="t_select">
					<option value="id" <%if(select.equals("id")) out.print("selected"); %>>ID</option>
					<option value="name" <%if(select.equals("name")) out.print("selected"); %>>name</option>
				</select>
				<input type="text" name="t_search" size="5" value="<%=search %>">
				<input type="button" onclick="goSearch()" value="search">
			</td>
		</tr>
		</form>
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