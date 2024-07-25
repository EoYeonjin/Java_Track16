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
	
	function goUpdateForm(){
		stu.t_gubun.value = "updateForm";
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
	
	function goDelete(){
		if(confirm("삭제하시겠습니까?")){
			stu.t_gubun.value = "delete";
			stu.method="post";
			stu.action="Student";
			stu.submit();
		}
	}
</script>

</head>
<body>
<form name="stu">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_syear" value="<%=dto.getSyear() %>">
	<input type="hidden" name="t_sclass" value="<%=dto.getSclass() %>">
	<input type="hidden" name="t_no" value="<%=dto.getNo() %>">
</form>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 Student</h1>
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
							<th>학년</th>
							<td class="th_left">
								<%=dto.getSyear() %>
							</td>
						</tr>
						<tr>
							<th>반</th>
							<td class="th_left">
								<%=dto.getSclass() %>
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<td class="th_left">
								<%=dto.getNo() %>
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td class="th_left">
								<%=dto.getName() %>
							</td>
						</tr>
						<tr>
							<th>국어점수</th>
							<td class="th_left">
								<%=dto.getKor() %>
							</td>
						</tr>
						<tr>
							<th>영어점수</th>
							<td class="th_left">
								<%=dto.getEng() %>
							</td>
						</tr>
						<tr>
							<th>수학점수</th>
							<td class="th_left">
								<%=dto.getMat() %>
							</td>
						</tr>
						<tr>
							<th>합계</th>
							<td class="th_left">
								<%=dto.getSum() %>
							</td>
						</tr>
						<tr>
							<th>평균</th>
							<td class="th_left">
								<%=dto.getAve() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="javascript:goList()" value="목록" class="btn_list">
				<input type="button" onClick="javascript:goUpdateForm()" value="수정" class="btn_list">
				<input type="button" onClick="javascript:goDelete()" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>
