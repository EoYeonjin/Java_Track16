<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %> 
<%
	StudentDto dto = (StudentDto)request.getAttribute("t_dto");
%>     
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Track16 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
<script type="text/javascript">
	function goList(){
		stu.t_gubun.value = "list";
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
	
	function goUpdate(){
		stu.t_gubun.value = "update";
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 Student</h1>
		</div>		
		<div class="write_wrap">
		<form name="stu">
			<input type="hidden" name="t_gubun">
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>학년</th>
							<td class="th_left">
								<input name="t_syear"  class="input_100px" type="text" value="<%=dto.getSyear() %>" readonly>
							</td>
						</tr>
						<tr>
							<th>반</th>
							<td class="th_left">
								<input name="t_sclass"  class="input_100px" type="text" value="<%=dto.getSclass() %>" readonly>
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<td class="th_left">
								<input name="t_no"  class="input_100px" type="text" value="<%=dto.getNo() %>" readonly>
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td class="th_left">
								<input name="t_name"  class="input_100px" type="text" value="<%=dto.getName() %>">							
							</td>
						</tr>
						<tr>
							<th>국어점수</th>
							<td class="th_left">
								<input name="t_kor"  class="input_100px" type="text" value="<%=dto.getKor() %>">							
							</td>
						</tr>
						<tr>
							<th>영어점수</th>
							<td class="th_left">
								<input name="t_eng"  class="input_100px" type="text" value="<%=dto.getEng() %>">							
							</td>
						</tr>
						<tr>
							<th>수학점수</th>
							<td class="th_left">
								<input name="t_mat"  class="input_100px" type="text" value="<%=dto.getMat() %>">							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
			<div class="btn_wrap">
				<input type="button" onClick="javascript:goList()" value="목록" class="btn_list">
				<input type="button" onClick="javascript:goUpdate()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>