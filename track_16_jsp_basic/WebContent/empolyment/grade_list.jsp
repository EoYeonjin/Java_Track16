<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null){
		select = "none";
		search = "";
	}

	ArrayList<GradeDto> dtos = dao.getGdInfo(select, search);
	int count = dtos.size();
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직위관리</title>
<style>
	table {
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script type="text/javascript">
	function goSearch(){
		src.method="post";
		src.action="grade_list.jsp";
		src.submit();
	}	
	
	function goDelete(grade_code){
		gd.t_code.value=grade_code;
		var result = confirm("삭제하시겠습니까?");
		
		if(result == true){
			gd.method="post";
			gd.action="db_grade_delete.jsp";
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
		<input type="hidden" name="t_code">
	</form>		
	<table border="1" width="800">
		<caption>직급목록</caption>
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
				<option value="grade_code" <%if(select.equals("grade_code")) out.print("selected"); %>>직급 코드</option>
				<option value="grade_name" <%if(select.equals("grade_name")) out.print("selected"); %>>직급 이름</option>
			</select>
			<input type="text" size="10" name="t_search" value="<%=search %>">
			<input type="button" onclick="goSearch()" value="search">
			<span style="float:right">총 지급 수 : <%=count %> 개</span>
			</td>
		</tr>
		</form>
		<tr>
			<th>직급 코드</th>
			<th>직급 이름</th>
			<th></th>
		</tr>
<%		for(GradeDto dto: dtos){ %>	
		<tr>
			<td style="text-align:center"><%=dto.getGrade_code() %></td>
			<td style="text-align:center"><%=dto.getGrade_name() %></td>
			<th>
			<input type="button" onclick="javascript:location.href='grade_update.jsp?t_code=<%=dto.getGrade_code() %>'" value="수정">
			<input type="button" onclick="goDelete('<%=dto.getGrade_code() %>')" value="삭제">
			</th>
		</tr>
<%		} %>
		<tr>
			<th colspan="3"><a href="grade_write.jsp">[등록]</a></th>
		</tr>
	</table>
	
</body>
</html>