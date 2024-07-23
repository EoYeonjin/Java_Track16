<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*, java.util.*" %>
<%
	ArrayList<MemberDto> dtos = (ArrayList<MemberDto>)request.getAttribute("t_dtos");
%>    
<script type="text/javascript">
	function goSave(){
		mem.method="post";
		mem.action="DBMemberSave";
		mem.submit();
	}
</script>    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Track16 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >		
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
								<input name="t_id"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name"  class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>지역</th>
							<td class="th_left">
							<%for(MemberDto dto: dtos){ %>
								<input name="t_area" class="input_30px" type="radio" value="<%=dto.getArea_code() %>"><%=dto.getArea_name() %>
							<%} %>	
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input name="t_age"  class="input_100px" type="text">							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" class="btn_ok" onClick="goSave()">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='MemberList'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>
