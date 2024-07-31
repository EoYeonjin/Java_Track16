<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
/**	MemberDto dto = (MemberDto)request.getAttribute("t_dto");
	ArrayList<MemberDto> dtos = (ArrayList<MemberDto>)request.getAttribute("t_dtos");**/
%>    
<script type="text/javascript">
	function goUpdate(){
		mem.method="post";
		mem.action="DBMemberUpdate";
		mem.submit();
	}
</script>   
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Track16 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">

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
								<input type="text" class="input_100px" name="t_id" value="${t_dto.getId() }" readonly>
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name" value="${t_dto.getName() }" class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>지역</th>
							<td class="th_left">
							<c:forEach var="aDto" items="${t_dtos }">
								<input name="t_area" class="input_30px" type="radio" value="${aDto.getArea_code() }" <c:if test="${aDto.getArea_name() eq t_dto.getArea()}">checked</c:if>>${aDto.getArea_name() }
							</c:forEach>
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input type="text" class="input_100px" name="t_age" value="${t_dto.getAge() }">						
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>	
			<div class="btn_wrap">
				<input type="button" onClick="location.href='MemberList'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdate()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>