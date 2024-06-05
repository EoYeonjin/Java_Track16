<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();
	
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
<title>Insert title here</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goSave(){
		if(gd.t_code.value == ""){
			alert("직급 코드를 입력해주세요");
			emp.t_no.value.focus();
			return;
		}
		
		if(gd.t_name.value == ""){
			alert("직급 이름을 입력해주세요");
			emp.t_name.value.focus();
			return;
		}
		
		if(gd.t_check.value == ""){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		if(gd.t_check.value != gd.t_code.value){
			alert("중복체크 버튼을 눌러주세요");
			return;
		}
		
		gd.method="post";
		gd.action="db_grade_save.jsp";
		gd.submit();
	}
	
	function goCheck(){
		if(gd.t_code.value == ""){
			alert("직급코드를 입력해주세요.")
		}else{
			gd.method="post";
			gd.action="db_grade_check.jsp";
			gd.submit();
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
	
	<form name="gd">
	<table border="1" width="800">
	<caption>직급 등록</caption>
	<colgroup>
		<col width="30%">
		<col width="70%">
	</colgroup>
	<tr>
		<th>직급 코드</th>
		<td>
			<input type="text" size="10" name="t_code" maxlength="3" placeholder="ex) G00" value="<%=check_code %>">
			<input type="button" onclick="goCheck()" value="중복체크">
			<input type="hidden" name="t_check" value="<%=check_code %>">
		</td>
	</tr>
	<tr>
		<th>직급 이름</th>
		<td><input type="text" size="15" name="t_name" maxlength="10" value="<%=check_name %>"></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="목록">
			<input type="button" onclick="goSave()" value="등록" <%=tf %>>
		</th>
	</tr>
	</table>
	</form>

</body>
</html>