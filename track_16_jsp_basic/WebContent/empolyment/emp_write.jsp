<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");

	DpatDao dptDao = new DpatDao();
	GradeDao gdDao = new GradeDao();
	
	ArrayList<DpatDto> dptDtos = dptDao.getDptList();
	ArrayList<GradeDto> gdDtos = gdDao.getGdList();
	
	String c_no = request.getParameter("n_no");
	String c_name = request.getParameter("n_name");
	String c_depart = request.getParameter("n_depart");
	String c_grade = request.getParameter("n_grade");
	String c_age = request.getParameter("n_age");
	String tf = request.getParameter("tf");
	
	if(tf == null){
		tf = "/disabled";
	}
	
	if(c_no == null){
		c_no = "";
	}
	
	if(c_name == null){
		c_name = "";
	}
	
	if(c_depart == null){
		c_depart = "";
	}
	
	if(c_grade == null){
		c_grade = "";
	}
	
	if(c_age == null){
		c_age = "";
	}
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>

<script type="text/javascript">
	function goSave(){
		if(emp.t_no.value == ""){
			alert("사원 번호를 입력해주세요");
			emp.t_no.value.focus();
			return;
		}
		
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
		
		if(emp.t_check.value == ""){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		if(emp.t_check.value != emp.t_no.value){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		emp.method="post";
		emp.action="db_emp_save.jsp";
		emp.submit();		
	}
	
	function inputNumberOnly(el) {
	    el.value = el.value.replace(/[^0-9]/g, '');
	  }
	
	function goCheck(){
		if(emp.t_no.value == ""){
			alert("사원번호를 입력해주세요.")
		}else{
			emp.method="post";
			emp.action="db_emp_check.jsp";
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
	<table border="1" width="800">
	<caption>사원 등록</caption>
	<colgroup>
		<col width="30%">
		<col width="70%">
	</colgroup>
	<tr>
		<th>ID</th>
		<td>
			<input type="text" size="10" maxlength="3" name="t_no" placeholder="ex) 000" value="<%=c_no %>">
			<input type="button" onclick="goCheck()" value="중복체크">
			<input type="hidden" name="t_check" value="<%=c_no %>">
		</td>
	</tr>
	<tr>
		<th>성명</th>
		<td><input type="text" size="10" maxlength="10" name="t_name" value="<%=c_name %>"></td>
	</tr>
	<tr>
		<th>부서</th>
		<td>
			<select name="t_depart">
<%				for(DpatDto dto: dptDtos) {%>	
				<option value="<%=dto.getDepart_code() %>" <%if(dto.getDepart_code().equals(c_depart)) out.print("selected"); %>><%=dto.getDepart_name() %></option>
<%				} %>	
			</select>
		</td>
	</tr>
	<tr>
		<th>직급</th>
		<td>
			<select name="t_grade">
<%				for(GradeDto dto: gdDtos) {%>			
				<option value="<%=dto.getGrade_code() %>" <%if(dto.getGrade_code().equals(c_grade)) out.print("selected"); %>><%=dto.getGrade_name() %></option>
<%				} %>				
			</select>
		</td>
	</tr>
	<tr>
		<th>나이</th>
		<td><input type="text" oninput="inputNumberOnly(this)" size="10" maxlength="3" name="t_age" value="<%=c_age %>"></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="button" onclick="javascript:location.href='emp_list.jsp'" value="목록">
			<input type="button" onclick="goSave()" value="등록" <%=tf %>>
		</th>
	</tr>
	</table>
	</form>
</body>
</html>