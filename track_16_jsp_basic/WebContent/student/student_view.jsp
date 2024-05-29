<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	StudentDao dao = new StudentDao();
	String syear = request.getParameter("t_syear");
	String sclass = request.getParameter("t_sclass");
	String no = request.getParameter("t_no");
	
	StudentDto dto = dao.getStudentView(syear, sclass, no);
%>     
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세 목록</title>
<script type="text/javascript">
	function goList(){
		location.href="student_list.jsp";
	}
	
	function goUpdateForm(){
		std.method="post";
		std.action="student_update.jsp";
		std.submit();
	}
	
	function goDelete(){
		var result = confirm("삭제하시겠습니까?");
		
		if(result == true){
			std.method = "post";
			std.action = "db_student_delete.jsp";
			std.submit();
		}
	}
</script>
</head>
<body>
	<form name="std">
		<input type="hidden" name="t_syear" value="<%=dto.getSyear() %>">
		<input type="hidden" name="t_sclass" value="<%=dto.getSclass() %>">
		<input type="hidden" name="t_no" value="<%=dto.getNo() %>">
	</form>
	<table border="1" width="800">
	<caption>학생 상세 목록</caption>
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<tr>
		<th>학년</th>
		<th>반</th>
		<th>번호</th>
		<th>이름</th>
		<th>국어</th>
		<th>영어</th>
		<th>수학</th>
		<th>총점</th>
		<th>평균</th>
	</tr>
	<tr>
		<td style="text-align:center"><%=dto.getSyear() %> 학년</td>
		<td style="text-align:center"><%=dto.getSclass() %> 반</td>
		<td style="text-align:center"><%=dto.getNo() %> 번</td>
		<td style="text-align:center"><%=dto.getName() %> </td>
		<td style="text-align:center"><%=dto.getKor() %> 점</td>
		<td style="text-align:center"><%=dto.getEng() %> 점</td>
		<td style="text-align:center"><%=dto.getMat() %> 점</td>
		<td style="text-align:center"><%=dto.getSum() %> 점</td>
		<td style="text-align:center"><%=dto.getAve() %> 점</td>
	</tr>
	<tr>
		<th colspan="9">
		<input type="button" onclick="goList()" value="목록">
		<input type="button" onclick="goUpdateForm()" value="수정">
		<input type="button" onclick="goDelete()" value="삭제">
		</th>
	</tr>
	</table> 
</body>
</html>