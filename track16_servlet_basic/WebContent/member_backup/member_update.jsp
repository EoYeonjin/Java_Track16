<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*,java.util.*" %>
<%
	MemberDto dto = (MemberDto)request.getAttribute("t_dto");
	ArrayList<AreaDto> dtos =
		(ArrayList<AreaDto>)request.getAttribute("t_areaDtos");	
%>    
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Track16 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >		
	<script type="text/javascript">
		function goUpdate(){
			mem.method="post";
			mem.action="DBMemberUpdate";
			mem.submit();
		} 
	</script>
</head>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 회원관리</h1>
		</div>		
		<div class="write_wrap">
			<form name="mem">
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>ID</th>
							<td class="th_left">
								<input name="t_id" readonly value="<%=dto.getId()%>" class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name" value="<%=dto.getName()%>" class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>지역</th>
							<td class="th_left">
								<select name="t_area" class="select">
									<% for(AreaDto aDto :dtos){ %>
										<option value="<%=aDto.getArea_code()%>" <% if(dto.getArea().equals(aDto.getArea_name())) out.print("selected"); %> ><%=aDto.getArea_name()%></option>
									<%} %>
								</selct>							
							</td>
						</tr>						
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input name="t_age" value="<%=dto.getAge()%>" class="input_100px" type="text">
							</td>
						</tr>

					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" onclick="goUpdate()" value="수정저정" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='MemberList'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>








