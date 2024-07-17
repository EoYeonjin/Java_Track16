<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	MemberDao dao = new MemberDao();
	
	String id = request.getParameter("t_id");
	String password = request.getParameter("t_password");
	String sessionName = (String)session.getAttribute("sessionName");
	
	String name = dao.getLoginName(id, password);
	String msg = "", url = "", state= "";
	
	if(!name.equals(sessionName)){
		msg = "ID나 비밀번호가 정확하지 않습니다";
		url = "adm_list.jsp";
	}else{
		msg = name+"님 환영합니다";
		url = "../index.jsp";
		state="on";
	}
	
	session.setAttribute("sessionState", state);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="<%=url %>";
</script>
</head>
<body>

</body>
</html>