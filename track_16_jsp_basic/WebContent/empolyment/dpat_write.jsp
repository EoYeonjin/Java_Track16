<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();
	
	String check_code = request.getParameter("new_code");
	String check_name = request.getParameter("new_name");
	String tf = request.getParameter("tf");
	
	if(tf == null){
		tf = "/disabled";
	}
	
	if(check_code == null){
		check_code = "";
	}
	
	if(check_name == null){
		check_name = "";
	}
%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 등록</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goSave(){
		if(dpt.t_code.value == ""){
			alert("부서 코드를 입력해주세요");
			emp.t_no.value.focus();
			return;
		}
		
		if(dpt.t_name.value == ""){
			alert("부서 이름을 입력해주세요");
			emp.t_name.value.focus();
			return;
		}
		
		if(dpt.t_check.value == ""){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		if(dpt.t_check.value != dpt.t_code.value){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		dpt.method="post";
		dpt.action="db_dpat_save.jsp";
		dpt.submit();
	}
	
	function goCheck(){
		if(dpt.t_code.value == ""){
			alert("부서코드를 입력해주세요.")
		}else{
			dpt.method="post";
			dpt.action="db_dpat_check.jsp";
			dpt.submit();
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
	
	<form name="dpt">
	<table border="1" width="800">
	<caption>부서 등록</caption>
	<colgroup>
		<col width="30%">
		<col width="70%">
	</colgroup>
	<tr>
		<th>부서 코드</th>
		<td>
			<input type="text" size="10" name="t_code" maxlength="3" placeholder="ex) D00" value="<%=check_code %>">
			<input type="button" onclick="goCheck()" value="중복체크">
			<input type="hidden" name="t_check" value="<%=check_code %>">
		</td>
	</tr>
	<tr>
		<th>부서 이름</th>
		<td><input type="text" size="15" name="t_name" maxlength="10" value="<%=check_name %>"></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="button" onclick="javascript:location.href='dpat_list.jsp'" value="목록">
			<input type="button" onclick="goSave()" value="등록" <%=tf %>>
		</th>
	</tr>
	</table>
	</form>
</body>
</html>