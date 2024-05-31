<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	EmpDao dao = new EmpDao();
	
	String no = request.getParameter("t_no");
	String name = request.getParameter("t_name");
	String depart = request.getParameter("t_depart");
	String grade = request.getParameter("t_grade");
	int age = Integer.parseInt(request.getParameter("t_age"));
	
	EmpDto dto = dao.checkEmp(no);
	String msg = "";
	
	if(dto != null) msg = "";
	else{
		int result = dao.insertEmp(new EmpDto(no, name, depart, grade, age));
		
		if(result == 1) msg = "등록 성공";
		else msg = "등록 실패";
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록</title>
<script type="text/javascript">
<%	if(msg.equals("")){ %>
		alert('해당 사원번호는 이미 존재하므로 등록이 불가합니다.\n다시 입력해주세요');
		location.href="emp_write.jsp";
		
<%	}else { %>
		alert("<%=msg %>");
		location.href="emp_list.jsp";
<%	} %>
</script>
</head>
<body>

</body>
</html>