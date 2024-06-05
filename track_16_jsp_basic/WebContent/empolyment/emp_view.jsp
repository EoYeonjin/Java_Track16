<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>  
<%
	request.setCharacterEncoding("UTF-8");
	EmpDao dao = new EmpDao();
	String no = request.getParameter("t_no");
	
	EmpDto dto = dao.checkEmp(no);


%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 세부 목록</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goUpdateForm(){
		emp.method="post";
		emp.action="emp_update.jsp";
		emp.submit();
	}
	
	function goDelete(){
		var result = confirm("삭제하시겠습니까?");
		
		if(result == true){
			emp.method="post"
			emp.action="db_emp_delete.jsp";
			emp.submit();
		}
	}
</script>
</head>
<body>
	<table border="1" width="800">
		<tr>
			<th>
				<input type="button" onclick="javascript:location.href='emp_list.jsp'" value="사원관리">
				<input type="button" onclick="javascript:location.href='dpat_list.jsp'" value="부서관리">
				<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="직급관리">
			</th>
		</tr>
	</table>
	
	<form name="emp">
		<input type="hidden" name="t_no" value="<%=dto.getNo() %>">
	</form>
	<table border="1" width="800">
	<caption>사원 세부 목록</caption>
	<colgroup>
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="20%">
	</colgroup>
	<tr>
		<th>ID</th>
		<th>성명</th>
		<th>부서</th>
		<th>직급</th>
		<th>나이</th>
	</tr>
	<tr>
		<td style="text-align:center"><%=dto.getNo() %></td>
		<td style="text-align:center"><%=dto.getName() %></td>
		<td style="text-align:center"><%=dto.getDepart_name() %></td>
		<td style="text-align:center"><%=dto.getGrade_name() %></td>
		<td style="text-align:center"><%=dto.getAge() %></td>
	</tr>
	<tr>
		<th colspan="5">
			<input type="button" onclick="javascript:location.href='emp_list.jsp'" value="목록">
			<input type="button" onclick="goUpdateForm()" value="수정">
			<input type="button" onclick="goDelete()" value="삭제">
		</th>
	</tr>
	</table>
	
</body>
</html>