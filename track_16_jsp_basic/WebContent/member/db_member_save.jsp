<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDao dao = new MemberDao(); 
	

	String id = request.getParameter("t_id");
	String name = request.getParameter("t_name");
	String area = request.getParameter("t_area");
	String age = request.getParameter("t_age");
	
	MemberDto dto = new MemberDto(id, name, area, Integer.parseInt(age));
	
	/*out.print("id: "+id+"<br>");
	out.print("name: "+name+"<br>");
	out.print("area: "+area+"<br>");
	out.print("age: "+age+"<br>");*/
	
	int result = dao.insertMember(dto);
	String msg = "Insert Successed";
	result = 1;
	if(result != 1) msg = "Insert Failed";
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		alert("<%=msg%>");
		location.href="member_list.jsp";
	</script>
</head>
<body>

</body>
</html>