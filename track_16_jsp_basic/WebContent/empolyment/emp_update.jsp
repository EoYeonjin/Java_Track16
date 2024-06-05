<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>  
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("t_no");
	
	EmpDao empDao = new EmpDao();
	DpatDao dptDao = new DpatDao();
	GradeDao gdDao = new GradeDao();
	
	ArrayList<DpatDto> dptDtos = dptDao.getDptList();
	ArrayList<GradeDto> gdDtos = gdDao.getGdList();
	
	EmpDto dto = empDao.checkEmp(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 수정</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goUpdate(){
		if(emp.t_name.value == ""){
			alert("이름을 입력해주세요");
			emp.t_name.value.focus();
			return;
		}
	
		if(emp.t_age.value == ""){
			alert("나이를 입력해주세요");	
			emp.t_age.value.focus();
			return;
		}
	
		emp.method="post";
		emp.action="db_emp_update.jsp";
		emp.submit();		
	}
	
	function inputNumberOnly(el) {
	    el.value = el.value.replace(/[^0-9]/g, '');
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
	<table border="1" width="800">
		<caption>사원 정보 수정</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>ID</th>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<th>성명</th>
			<td><input type="text" size="10" maxlength="10" name="t_name" value="<%=dto.getName() %>"></td>
		</tr>
		<tr>
			<th>부서</th>
			<td>
			<select name="t_depart">
<%				for(DpatDto dptDto: dptDtos) {%>	
				<option value="<%=dptDto.getDepart_code() %>" <%if(dptDto.getDepart_name().equals(dto.getDepart_name())) out.print("selected");  %>><%=dptDto.getDepart_name() %></option>
<%				} %>	
			</select>
		</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>
			<select name="t_grade">
<%				for(GradeDto gdDto: gdDtos) {%>			
				<option value="<%=gdDto.getGrade_code() %>" <%if(gdDto.getGrade_name().equals(dto.getGrade_name())) out.print("selected");  %>><%=gdDto.getGrade_name() %></option>
<%				} %>				
			</select>
		</td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="text" oninput="inputNumberOnly(this)" size="10" maxlength="3" name="t_age" value="<%=dto.getAge() %>"></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" onclick="javascript:location.href='emp_list.jsp'" value="목록">
				<input type="button" onclick="goUpdate()" value="수정 저장">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>