<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	StudentDao dao = new StudentDao();
	
	String syear = request.getParameter("t_syear");
	String sclass = request.getParameter("t_sclass");
	String no = request.getParameter("t_no");
	String name = request.getParameter("t_name");
	int kor = Integer.parseInt(request.getParameter("t_kor"));
	int eng = Integer.parseInt(request.getParameter("t_eng"));
	int mat = Integer.parseInt(request.getParameter("t_mat"));
	
	int result = dao.updateStd(syear, sclass, no, name, kor, eng, mat);
	
	String msg = "수정 성공";
	if(result != 1) msg = "수정 실패";
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="student_list.jsp";
</script>
</head>
<body>

</body>
</html>