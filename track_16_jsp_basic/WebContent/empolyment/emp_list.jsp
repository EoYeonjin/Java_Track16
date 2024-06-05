<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	EmpDao dao = new EmpDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null){
		select = "grade";
		search = "";
	}

	ArrayList<EmpDto> dtos = dao.getEmpList(select, search);
	int count = dtos.size();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어연진</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goView(no){
		emp.t_no.value=no;
		
		emp.method="post";
		emp.action="emp_view.jsp";
		emp.submit();
	}

	function goSearch(){
		src.method="post";
		src.action="emp_list.jsp";
		src.submit();
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
		<input type="hidden" name="t_no">
	</form>
	<table border="1" width="800">
		<caption>사원목록</caption>
		<colgroup>
			<col width="30%">
			<col width="30%">
			<col width="30%">
		</colgroup>
		<form name=src>
		<tr>
			<td colspan="3">
			<select name="t_select">
				<option value="grade" <%if(select.equals("grade")) out.print("selected"); %>>=선택=</option>
				<option value="no" <%if(select.equals("no")) out.print("selected"); %>>사번</option>
				<option value="name" <%if(select.equals("name")) out.print("selected"); %>>성명</option>
				<option value="depart_name" <%if(select.equals("depart_name")) out.print("selected"); %>>부서</option>
			</select>
			<input type="text" size="10" name="t_search" value="<%=search %>">
			<input type="button" onclick="goSearch()" value="search">
			<span style="float:right">총 인원 : <%=count %> 명</span>
			</td>
		</tr>
		</form>
		<tr>
			<th>ID</th>
			<th>성명</th>
			<th>부서</th>
		</tr>
<%		for(EmpDto dto: dtos){ %>		
		<tr>
			<td style="text-align:center"><a href="javascript:goView('<%=dto.getNo() %>')"><%=dto.getNo() %></a></td>
			<td style="text-align:center"><a href="javascript:goView('<%=dto.getNo() %>')"><%=dto.getName() %></a></td>
			<td style="text-align:center"><%=dto.getDepart_name() %></td>
		</tr>
<%		} %>		
		<tr>
			<th colspan="3"><a href="emp_write.jsp">[등록]</a></th>
		</tr>
	</table>
</body>
</html>