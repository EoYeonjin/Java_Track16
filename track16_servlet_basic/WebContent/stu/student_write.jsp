<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function goList(){
		stu.t_gubun.value = "list";
		stu.method="post";
		stu.action="Student";
		stu.submit();
	}
	
	function goSave(){
		stu.t_gubun.value="save";
		stu.method="post";
		stu.action="Student";
		stu.submit();
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
								<input name="t_syear" value="1" class="input_30px" type="radio">1학년
								<input name="t_syear" value="2" class="input_30px" type="radio">2학년
								<input name="t_syear" value="3" class="input_30px" type="radio">3학년
								<input name="t_syear" value="4" class="input_30px" type="radio">4학년
								<input name="t_syear" value="5" class="input_30px" type="radio">5학년
								<input name="t_syear" value="6" class="input_30px" type="radio">6학년
							</td>
						</tr>
						<tr>
							<th>반</th>
							<td class="th_left">
								<input name="t_sclass" value="1" class="input_30px" type="radio">1반
								<input name="t_sclass" value="2" class="input_30px" type="radio">2반
								<input name="t_sclass" value="3" class="input_30px" type="radio">3반
								<input name="t_sclass" value="4" class="input_30px" type="radio">4반
								<input name="t_sclass" value="5" class="input_30px" type="radio">5반
								<input name="t_sclass" value="6" class="input_30px" type="radio">6반
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<td class="th_left">
								<input name="t_no"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td class="th_left">
								<input name="t_name"  class="input_100px" type="text">							
							</td>
						</tr>
						<tr>
							<th>국어점수</th>
							<td class="th_left">
								<input name="t_kor"  class="input_100px" type="text">							
							</td>
						</tr>
						<tr>
							<th>영어점수</th>
							<td class="th_left">
								<input name="t_eng"  class="input_100px" type="text">							
							</td>
						</tr>
						<tr>
							<th>수학점수</th>
							<td class="th_left">
								<input name="t_mat"  class="input_100px" type="text">							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" onclick="javascript:goSave()" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="goList()" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>
