<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	GradeDao dao = new GradeDao();
	String code = request.getParameter("t_code");
	
	GradeDto dto = dao.checkGd(code);
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직급 수정</title>
	<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
	</style>
	<script type="text/javascript">
	function goUpdate(){
		if(gd.t_name.value == ""){
			alert("부서 이름을 입력해주세요");
			gd.t_name.value.focus();
			return;
		}
		
		gd.method="post";	
		gd.action="db_grade_update.jsp";
		gd.submit();	
	}
	</script>
</head>
<body>
	<table border="1" width="800">
		<tr>
			<th>
				<input type="button" onclick="javascript:location.href='emp_list.jsp'" value="사원관리">
				<input type="button" onclick="javascript:location.href='dpat_list.jsp'" value="부서관리">
				<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="직위관리">
			</th>
		</tr>
	</table>

	<form name="gd">
	<input type="hidden" name="t_code" value="<%=dto.getGrade_code() %>">
	<table border="1" width="800">
		<caption>사원 정보 수정</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>부서 코드</th>
			<td><%=dto.getGrade_code() %></td>
		</tr>
		<tr>
			<th>부서 이름</th>
			<td><input type="text" size="15" maxlength="10" name="t_name" value="<%=dto.getGrade_name() %>"></td>
		</tr>

		<tr>
			<th colspan="2">
				<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="목록">
				<input type="button" onclick="goUpdate()" value="수정 저장">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>