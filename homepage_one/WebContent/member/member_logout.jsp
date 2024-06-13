<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)session.getAttribute("sessionName");
	String msg = name+"님 로그아웃 되었습니다.";
	if(name == null) msg = "로그아웃 되었습니다";

	session.invalidate();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="../index.jsp";
</script>
</head>
<body>

</body>
</html>