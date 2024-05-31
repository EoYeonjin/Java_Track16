<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null){
		select = "none";
		search = "";
	}

	ArrayList<DpatDto> dtos = dao.getDptInfo(select, search);
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goView(depart_code){
		dpt.t_code.value=depart_code;
		
		dpt.method="post";
		dpt.action="dpat_view.jsp";
		dpt.submit();
	}

	function goSearch(){
		src.method="post";
		src.action="dpat_list.jsp";
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
				<input type="button" onclick="javascript:location.href='grade_list.jsp'" value="직위관리">
			</th>
		</tr>
	</table>
	
	<form name="dpt">
		<input type="hidden" name="t_code">
	</form>
	<table border="1" width="800">
		<caption>부서목록</caption>
		<colgroup>
			<col width="30%">
			<col width="30%">
			<col width="30%">
		</colgroup>
		<form name=src>
		<tr>
			<td colspan="3">
			<select name="t_select">
				<option value="none" <%if(select.equals("none")) out.print("selected"); %>>==선택==</option>
				<option value="depart_code" <%if(select.equals("depart_code")) out.print("selected"); %>>부서 코드</option>
				<option value="depart_name" <%if(select.equals("depart_name")) out.print("selected"); %>>부서 이름</option>
			</select>
			<input type="text" size="10" name="t_search" value="<%=search %>">
			<input type="button" onclick="goSearch()" value="search">
			</td>
		</tr>
		</form>
		<tr>
			<th>부서 코드</th>
			<th>부서 이름</th>
			<th></th>
		</tr>
<%		for(DpatDto dto: dtos){ %>		
		<tr>
			<td style="text-align:center"><%=dto.getDepart_code() %></td>
			<td style="text-align:center"><%=dto.getDepart_name() %></td>
			<th>
			<input type="button" onclick="goUpdateForm()" value="수정">
			<input type="button" onclick="goDelete()" value="삭제">
			</th>
		</tr>
<%		} %>
		<tr>
			<th colspan="3"><a href="dpat_write.jsp">[등록]</a></th>
		</tr>
	</table>
</body>
</html>