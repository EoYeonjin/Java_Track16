<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*"%>
<%
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	String exit_date = CommonUtil.getTodayTime();
	
	int result = dao.memberExit(id, exit_date);
	String msg = "회원 탈퇴 되었습니다";
	
	if(result != 1) msg = "회원 탈퇴 오류! 관리자에게 문의 바랍니다";
	else{
		session.invalidate();
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