<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<script type="text/javascript">
	function goList(){
		location.href="student_list.jsp";
	}
	
	function goSave(){
		if(std.t_no.value == ""){
			alert("번호를 입력해주세요");
			std.t_no.focus();
			return;
		}
		
		if(std.t_name.value == ""){
			alert("이름을 입력해주세요");
			std.t_name.focus();
			return;
		}
		
		if(std.t_kor.value == ""){
			alert("국어점수를 입력해주세요");
			std.t_kor.focus();
			return;
		}
		
		var kor = parseInt(std.t_kor.value);
		if(kor<0 || kor>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_kor.focus();
			return;
		}
		
		if(std.t_eng.value == ""){
			alert("영어점수를 입력해주세요");
			std.t_eng.focus();
			return;
		}
		
		var eng = parseInt(std.t_eng.value);
		if(eng<0 || eng>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_eng.focus();
			return;
		}
		
		if(std.t_mat.value == ""){
			alert("수학점수를 입력해주세요");
			std.t_mat.focus();
			return;
		}
		
		var mat = parseInt(std.t_mat.value);
		if(mat<0 || mat>100){
			alert("0~100사이의 값만 입력해주세요.");
			std.t_mat.focus();
			return;
		}
		
		std.method="post";
		std.action="db_student_save.jsp";
		std.submit();
	}

</script>
</head>
<body>
	<form name="std">
	<table border="1" width="800">
		<caption>학생 등록</caption>
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>학년</th>
			<td>
				<select name="t_syear">
					<option value="1">1학년</option>
					<option value="2">2학년</option>
					<option value="3">3학년</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>반</th>
			<td>
				<input type="radio" name="t_sclass" value="1"> 1반&nbsp;
				<input type="radio" name="t_sclass" value="2"> 2반&nbsp;
				<input type="radio" name="t_sclass" value="3"> 3반&nbsp;
				<input type="radio" name="t_sclass" value="4"> 4반&nbsp;
				<input type="radio" name="t_sclass" value="5"> 5반&nbsp;
				<input type="radio" name="t_sclass" value="6"> 6반&nbsp;
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<td>
				<input type="text" name="t_no" size="10" maxlength="2"> 번
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="t_name" size="20" maxlength="10"> 
			</td>
		</tr>
		<tr>
			<th>국어</th>
			<td>
				<input type="text" name="t_kor" size="10" maxlength="3"> 점
			</td>
		</tr>
		<tr>
			<th>영어</th>
			<td>
				<input type="text" name="t_eng" size="10" maxlength="3"> 점
			</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>
				<input type="text" name="t_mat" size="10" maxlength="3"> 점
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" onclick="goList()" value="목록">
				<input type="button" onclick="goSave()" value="저장">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>