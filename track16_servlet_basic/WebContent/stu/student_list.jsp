<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*, java.util.*" %>    
<%
	ArrayList<StudentDto> dtos = (ArrayList<StudentDto>)request.getAttribute("t_dtos");

	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
	String syear = (String)request.getAttribute("t_syear");
	String sclass = (String)request.getAttribute("t_sclass");
	
	if(select == null){
		select = "no";
		search = "";
	}
	
%>
<!DOCTYPE html>
<html> 
<head>
<script type="text/javascript">
	function goView(syear, sclass, no){
		stu.t_syear.value = syear;
		stu.t_sclass.value = sclass;
		stu.t_no.value = no;
		stu.t_gubun.value = "view";
		
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
	
	function goWriteForm(){
		stu.t_gubun.value = "writeForm";
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
	
	function goSearch(){
		stu_search.method="post";
		stu_search.action="Student";
		stu_search.submit();
	}
</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : Track16 홍길동
	******************************************** 
 -->	
	<title>Track16 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
</head>
<body>
<form name="stu">
	<input type="hidden" name="t_syear">
	<input type="hidden" name="t_sclass">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun">
</form>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 Student</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총학생수 : <span><%=dtos.size() %></span>명</p>
			</div>
			<form name="stu_search">
				<div class="search_group">
					<input name="t_syear" value="all" class="input_30px" type="radio" <%if(syear.equals("all")) out.print("checked"); %>>전체
					<input name="t_syear" value="1" class="input_30px" type="radio" <%if(syear.equals("1")) out.print("checked"); %>>1학년
					<input name="t_syear" value="2" class="input_30px" type="radio" <%if(syear.equals("2")) out.print("checked"); %>>2학년
					<input name="t_syear" value="3" class="input_30px" type="radio" <%if(syear.equals("3")) out.print("checked"); %>>3학년
					<input name="t_syear" value="4" class="input_30px" type="radio" <%if(syear.equals("4")) out.print("checked"); %>>4학년
					<input name="t_syear" value="5" class="input_30px" type="radio" <%if(syear.equals("5")) out.print("checked"); %>>5학년
					<input name="t_syear" value="6" class="input_30px" type="radio" <%if(syear.equals("6")) out.print("checked"); %>>6학년&nbsp;&nbsp;
					<input name="t_sclass" value="all" class="input_30px" type="radio" <%if(sclass.equals("all")) out.print("checked"); %>>전체
					<input name="t_sclass" value="1" class="input_30px" type="radio" <%if(sclass.equals("1")) out.print("checked"); %> >1반
					<input name="t_sclass" value="2" class="input_30px" type="radio" <%if(sclass.equals("2")) out.print("checked"); %>>2반
					<input name="t_sclass" value="3" class="input_30px" type="radio" <%if(sclass.equals("3")) out.print("checked"); %>>3반
					<input name="t_sclass" value="4" class="input_30px" type="radio" <%if(sclass.equals("4")) out.print("checked"); %>>4반
					<input name="t_sclass" value="5" class="input_30px" type="radio" <%if(sclass.equals("5")) out.print("checked"); %>>5반
					<input name="t_sclass" value="6" class="input_30px" type="radio" <%if(sclass.equals("6")) out.print("checked"); %>>6반&nbsp;&nbsp;
					<select class="select" name="t_select">
						<option value="no" <%if(select.equals("no")) out.print("selected"); %>>번호</option>
						<option value="name" <%if(select.equals("name")) out.print("selected"); %>>이름</option>
					</select>
					<input type="text" class="search_word" name="t_search" value="<%=search %>">
					<button class="btn_search" onClick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
				</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>학년</th>
					<th>반</th>
					<th>번호</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
			<%for(StudentDto dto: dtos){ %>
				<tr>
					<td><a href="javascript:goView('<%=dto.getSyear() %>', '<%=dto.getSclass() %>', '<%=dto.getNo() %>')"><%=dto.getSyear() %></a></td>
					<td><%=dto.getSclass() %></td>
					<td><%=dto.getNo() %></td>
					<td><%=dto.getName() %></td>
				</tr>	
			<%} %>
			</tbody>
		</table>
		<div class="paging">
			<a href="javascript:goWriteForm()" class="write">등록</a>
		</div>
	</div>
 </body>
</html>
