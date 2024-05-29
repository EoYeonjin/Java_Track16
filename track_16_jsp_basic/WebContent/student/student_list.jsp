<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	StudentDao dao = new StudentDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	String syear = request.getParameter("t_syear");
	String sclass = request.getParameter("t_sclass");
	
	if(select == null){
		select = "kor";
		search = "";
	}
	
	if(syear == null || syear.equals("0")){
		syear = "";
	}
	
	if(sclass == null || sclass.equals("0")){
		sclass = "";
	}

	ArrayList<StudentDto> dtos = dao.getStudentList(select, search, syear, sclass);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어연진</title>
<script>
	function goView(syear, sclass, no){
		std.t_syear.value=syear;
		std.t_sclass.value=sclass;
		std.t_no.value=no;
		
		std.method="post";
		std.action="student_view.jsp";
		std.submit();
	}
	
	function goSearch(){
		src.method="post";
		src.action="student_list.jsp";
		src.submit();
	}
</script>

</head>
<body>
	<form name = "std">
		<input type="hidden" name="t_syear">
		<input type="hidden" name="t_sclass">
		<input type="hidden" name="t_no">
	</form>
	<table border="1" width="800">
		<caption>학생 목록</caption>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<form name = src>
		<tr>
			<th>검색</th>
			<td colspan="3">
			<div>
				<input type="radio" name="t_syear" value="0" <%if(syear.equals("")) out.print("checked"); %>> 전체&nbsp;
				<input type="radio" name="t_syear" value="1" <%if(syear.equals("1")) out.print("checked"); %>> 1학년&nbsp;
				<input type="radio" name="t_syear" value="2" <%if(syear.equals("2")) out.print("checked"); %>> 2학년&nbsp;
				<input type="radio" name="t_syear" value="3" <%if(syear.equals("3")) out.print("checked"); %>> 3학년&nbsp;
			</div>
			<div>
				<input type="radio" name="t_sclass" value="0" <%if(sclass.equals("")) out.print("checked"); %>> 전체&nbsp;
				<input type="radio" name="t_sclass" value="1" <%if(sclass.equals("1")) out.print("checked"); %>> 1반&nbsp;
				<input type="radio" name="t_sclass" value="2" <%if(sclass.equals("2")) out.print("checked"); %>> 2반&nbsp;
				<input type="radio" name="t_sclass" value="3" <%if(sclass.equals("3")) out.print("checked"); %>> 3반&nbsp;
				<input type="radio" name="t_sclass" value="4" <%if(sclass.equals("4")) out.print("checked"); %>> 4반&nbsp;
				<input type="radio" name="t_sclass" value="5" <%if(sclass.equals("5")) out.print("checked"); %>> 5반&nbsp;
				<input type="radio" name="t_sclass" value="6" <%if(sclass.equals("6")) out.print("checked"); %>> 6반&nbsp;
			</div>
			<div>
				<select name="t_select">
					<option value="kor" <%if(select.equals("kor")) out.print("selected"); %>>=선택=</option>
					<option value="no" <%if(select.equals("no")) out.print("selected"); %>>번호</option>
					<option value="name" <%if(select.equals("name")) out.print("selected"); %>>이름</option>
				</select>
				<input type="text" name="t_search" size="10" value="<%=search %>">
				<input type="button" onclick="goSearch()" value="search">
			</div>
			</td>
		</tr>
		</form>
		<tr>
			<th>학년</th>
			<th>반</th>
			<th>번호</th>
			<th>이름</th>
		</tr>
<%		for(StudentDto dto: dtos){ %>
		<tr>
			<td style="text-align:center"><%=dto.getSyear() %> 학년</td>
			<td style="text-align:center"><%=dto.getSclass() %> 반</td>
			<td style="text-align:center"><%=dto.getNo() %> 번</td>
			<td style="text-align:center">
			<a href="javascript:goView('<%=dto.getSyear() %>', '<%=dto.getSclass() %>', '<%=dto.getNo() %>')">
			<%=dto.getName() %>
			</a>
			</td>
		<tr>
<%		} %>
		<tr>
			<td colspan="4" style="text-align:center">
				<a href="student_write.jsp">[등록]</a>
			</td>
		</tr>
	</table>
</body>
</html>