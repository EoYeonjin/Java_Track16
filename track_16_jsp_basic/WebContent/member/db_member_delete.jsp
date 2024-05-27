<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	MemberDao dao = new MemberDao();

	String id = request.getParameter("t_id");
	int result = dao.deleteMember(id);
	
	String msg = "delete successed";
	if(result != 1) msg = "delete failed";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		alert("<%=msg %>");
		location.href="member_list.jsp";
	</script>
</head>
<body>

</body>
</html>