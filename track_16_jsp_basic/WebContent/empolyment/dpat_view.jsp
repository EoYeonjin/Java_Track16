<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>  
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();
	String depart_code = request.getParameter("t_code");
	
	DpatDto dto = dao.checkDpt(depart_code);


%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 세부 정보</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goUpdateForm(){
		dpt.method="post";
		dpt.action="dpat_update.jsp";
		dpt.submit();
	}
	
	function goDelete(){
		var result = confirm("삭제하시겠습니까?");
		
		if(result == true){
			dpt.method="post"
			dpt.action="db_dpat_delete.jsp";
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
				<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="직위관리">
			</th>
		</tr>
	</table>
	
	<form name="dpt">
		<input type="hidden" name="t_code" value="<%=dto.getDepart_code() %>">
	</form>
	<table border="1" width="800">
	<caption>사원 세부 목록</caption>
	<colgroup>
		<col width="50%">
		<col width="50%">
	</colgroup>
	<tr>
		<th>부서 코드</th>
		<th>부서 이름</th>
	</tr>
	<tr>
		<td style="text-align:center"><%=dto.getDepart_code() %></td>
		<td style="text-align:center"><%=dto.getDepart_name() %></td>
	</tr>
	<tr>
		<th colspan="5">
			<input type="button" onclick="javascript:location.href='dpat_list.jsp'" value="목록">
			<input type="button" onclick="goUpdateForm()" value="수정">
			<input type="button" onclick="goDelete()" value="삭제">
		</th>
	</tr>
	</table>
</body>
</html>