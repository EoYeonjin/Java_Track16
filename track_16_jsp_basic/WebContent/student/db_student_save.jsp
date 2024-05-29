<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
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
	
	String msg = "";
	
	StudentDto checkDto = dao.getStudentView(syear, sclass, no);
	
	if(checkDto != null) msg = "";
	else{
		StudentDto dto = new StudentDto(syear, sclass, no, name, kor, eng, mat);
		int result = dao.insertStd(dto);
		msg = "등록 성공";
		if(result != 1) msg = "등록 실패";
	}
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%	if(msg.equals("")){ %>
		alert('해당 학년과 반, 번호의 학생은 이미 존재하므로 등록이 불가합니다.\n다시 입력해주세요');
		location.href="student_write.jsp";
		
<%	}else { %>
		alert("<%=msg %>");
		location.href="student_list.jsp";
<%	} %>
</script>
</head>
<body>

</body>
</html>