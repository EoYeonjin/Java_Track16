<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDao dao = new MemberDao();
	
	String id = request.getParameter("t_id");
	String password = request.getParameter("t_password");
	
	
	
	String name = dao.getLoginName(id, password);
	String msg = "", url = "";
	
	if(name.equals("")){
		msg = "ID나 비밀번호가 정확하지 않습니다";
		url = "member_login.jsp";
	}
	else{
		msg = name+"님 환영합니다";
		url = "../index.jsp";
		
		int result = dao.setMemberLoginTime(id, CommonUtil.getTodayTime());
		if(result != 1) System.out.print("회원 최종 로그인 시간 Update 오류"); 
		
		session.setAttribute("sessionId", id);
		session.setAttribute("sessionName", name);
		session.setMaxInactiveInterval(30*60);
	}
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