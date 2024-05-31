<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();
	
	String grade_code = request.getParameter("t_code");
	String grade_name = request.getParameter("t_name");
	
	GradeDto dto = dao.checkGd(grade_code);
	String msg = "";
	
	if(dto != null) msg = "";
	else{
		int result = dao.insertGd(new GradeDto(grade_code, grade_name));
		
		if(result == 1) msg = "등록 성공";
		else msg = "등록 실패";
	}
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%	if(msg.equals("")){ %>
		alert('해당 직급코드는 이미 존재하므로 등록이 불가합니다.\n다시 입력해주세요');
		location.href="grade_write.jsp";
		
<%	}else { %>
		alert("<%=msg %>");
		location.href="grade_list.jsp";
<%	} %>
</script>
</head>
<body>

</body>
</html>