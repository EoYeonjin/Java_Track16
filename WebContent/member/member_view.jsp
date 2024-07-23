<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%
	MemberDto dto = (MemberDto)request.getAttribute("t_dto");
%>    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Track16 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
<script type="text/javascript">
	function goUpdateForm(){
		mem.t_gubun.value="updateForm";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
	function goDelete(){
		if(confirm("삭제하시겠습니까?")){
			mem.t_gubun.value="delete";
			mem.method="post";
			mem.action="Member";
			mem.submit();
		}
	}
	
	function goList(){
		mem.t_gubun.value="list";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
</script>
<form name="mem">
	<input type="hidden" name="t_id" value="<%=dto.getId() %>">
	<input type="hidden" name="t_gubun">
</form>
</head>
<body>
	<div class="container">
	
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 회원관리</h1>
		</div>		
		<div class="write_wrap">
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
								<%=dto.getId() %>
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<%=dto.getName() %>
							</td>
						</tr>
						<tr>
							<th>지역</th>
							<td class="th_left">
								<%=dto.getArea() %>
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<%=dto.getAge() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="goList()" value="목록" class="btn_list">
				<input type="button" onClick="javascript:goUpdateForm()" value="수정" class="btn_list">
				<input type="button" onClick="javascript:goDelete()" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>