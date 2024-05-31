<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	DpatDao dao = new DpatDao();
	String depart_code = request.getParameter("t_code");

	DpatDto dto = dao.checkDpt(depart_code);
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
	function goUpdate(){
		if(dpt.t_name.value == ""){
			alert("부서 이름을 입력해주세요");
			emp.t_name.value.focus();
			return;
		}
		
		dpt.method="post";
		dpt.action="db_dpat_update.jsp";
		dpt.submit();	
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

	<form name="dpt">
	<input type="hidden" name="t_code" value="<%=dto.getDepart_code() %>">
	<table border="1" width="800">
		<caption>사원 정보 수정</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>부서 코드</th>
			<td><%=dto.getDepart_code() %></td>
		</tr>
		<tr>
			<th>부서 이름</th>
			<td><input type="text" size="15" maxlength="10" name="t_name"></td>
		</tr>

		<tr>
			<th colspan="2">
				<input type="button" onclick="javascript:location.href='dpat_list.jsp'" value="목록">
				<input type="button" onclick="goUpdate()" value="수정 저장">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>