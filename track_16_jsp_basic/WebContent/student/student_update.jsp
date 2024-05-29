<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
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
<title>학생 정보 수정</title>
<script>
	function goUpdate(){
		if(std.t_name.value == ""){
			alert("이름을 입력하세요");
			std.t_name.focus();
			return;
		}
		
		var kor = parseInt(std.t_kor.value);
		if(kor<0 || kor>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_kor.focus();
			return;
		}
		
		if(std.t_kor.value == ""){
			alert("국어점수를 입력해주세요");
			std.t_kor.focus();
			return;
		}
		
		var eng = parseInt(std.t_eng.value);
		if(eng<0 || eng>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_eng.focus();
			return;
		}
		
		if(std.t_eng.value == ""){
			alert("영어점수를 입력해주세요");
			std.t_eng.focus();
			return;
		}
		
		if(std.t_mat.value == ""){
			alert("수학점수를 입력해주세요");
			std.t_mat.focus();
			return;
		}
		
		var mat = parseInt(std.t_mat.value);
		if(mat<0 || mat>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_mat.focus();
			return;
		}
		
		std.method = "post";
		std.action = "db_student_update.jsp";
		std.submit();
	}
</script>
</head>
<body>
	<form type="text" name="std">
	<input type="hidden" name="t_syear" value="<%=dto.getSyear() %>">
	<input type="hidden" name="t_sclass" value="<%=dto.getSclass() %>">
	<input type="hidden" name="t_no" value="<%=dto.getNo() %>">
	<table border="1" width="800">
		<caption>학생 정보 수정</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>학년</th>
			<td><%=dto.getSyear() %> 학년</td>
		</tr>
		<tr>
			<th>반</th>
			<td><%=dto.getSclass() %> 반</td>
		</tr>
		<tr>
			<th>번호</th>
			<td><%=dto.getNo() %> 번</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" value="<%=dto.getName() %>" size="20" name="t_name" maxlength="10"></td>
		</tr>
		<tr>
			<th>국어</th>
			<td><input type="text" value="<%=dto.getKor() %>" size="3" name="t_kor" maxlength="3"> 점</td>
		</tr>
		<tr>
			<th>영어</th>
			<td><input type="text" value="<%=dto.getEng() %>" size="3" name="t_eng" maxlength="3"> 점</td>
		</tr>
		<tr>
			<th>수학</th>
			<td><input type="text" value="<%=dto.getMat() %>" size="3" name="t_mat" maxlength="3"> 점</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" onclick="location.href='student_list.jsp'" value="목록">
				<input type="button" onclick="goUpdate()" value="수정 저장"> 
			</th>
		</tr>
	</table>
	</form>
</body>
</html>