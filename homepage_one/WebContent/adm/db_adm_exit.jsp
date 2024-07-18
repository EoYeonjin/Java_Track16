<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	AdmDao dao = new AdmDao();
	
	String id = request.getParameter("t_id");
	String exit_date = CommonUtil.getTodayTime();
	
	String msg = "회원이 탈퇴되었습니다";
	
	int result = dao.updateExitDate(id, exit_date);
	if(result != 1) msg = "탈퇴 실패";
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="adm_list.jsp";
</script>
</head>
<body>

</body>
</html>