<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>    
<%
	String name = (String)request.getAttribute("t_name");
	int age = (int)request.getAttribute("t_age");
	MemberDto dto = (MemberDto)request.getAttribute("t_dto");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ID : <%=dto.getId() %><br>
	성명 : <%=dto.getName() %><br>
	지역 : <%=dto.getArea() %><br>
	나이 : <%=dto.getAge() %>
</body>
</html>