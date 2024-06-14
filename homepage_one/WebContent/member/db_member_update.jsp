<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDao dao = new MemberDao();
	
	String id = request.getParameter("t_id");
	String name = request.getParameter("t_name");
	String job = request.getParameter("t_job");
	String tell_1 = request.getParameter("t_tell_1");
	String tell_2 = request.getParameter("t_tell_2");
	String tell_3 = request.getParameter("t_tell_3");
	String mobile_1 = request.getParameter("t_mobile_1");
	String mobile_2 = request.getParameter("t_mobile_2");
	String mobile_3 = request.getParameter("t_mobile_3");
	String email_1 = request.getParameter("t_email_1");
	String email_2 = request.getParameter("t_email_2");
	
	MemberDto dto = new MemberDto(id, name, job, tell_1, tell_2, tell_3, mobile_1, mobile_2, mobile_3, email_1, email_2);
	
	int result = dao.memberUpdate(dto);
	String msg = name+"님 회원정보가 수정되었습니다";
	
	if(result != 1) msg = "회원정보 수정 실패했습니다";
	else{
		session.setAttribute("sessionName", name);
	}
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