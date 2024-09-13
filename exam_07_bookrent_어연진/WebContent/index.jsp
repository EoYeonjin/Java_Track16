<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function goMemberList(){
		mem.t_gubun.value="member";
		mem.method="post";
		mem.action="BookRent";
		mem.submit();
	}
</script>
<form name="mem">
	<input name="t_gubun">
</form>
<head>
	<meta charset="UTF-8">
	<title>도서대여 프로그램</title>
	<link href="main.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1>(과정평가형정보처리산업기사)도서대여 프로그램</h1>
	</header>	
	
	<nav>
		<ul>
			<li><a href="javascript:goMemberList()">회원조회</a></li>
			<li><a href="sub_2.jsp">도서대여등록</a></li>
			<li><a href="sub_3.jsp">대여이력조회</a></li>
			<li><a href="sub_4.jsp">도서대여건수</a></li>
			<li><a href="index.jsp">홈으로</a></li>
		</ul>
	</nav>
	
	<section>
		<h2>
			과정평가형 자격 CBQ
		</h2>
		<p>국가직무능력표준(NCS:National Cempetency Standard)으로 설계된 교육훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가기술자격입니다.</p>
		<p>산업현장 중심으이 교육평가로 더 커지는 능력!</p>
		<p>알고 있는 것에 할 수 있는 것을 더하는</p>
		<p>과정평가형 자격은</p>
		<p>현장 중심형 인재교육을 지원 합니다.</p>
	</section>
	
	<footer>
		<p>
			HRDKOREA Copyright © All rights Reserved. Human Resources Developement Service of Korea.
		</p>
	</footer>
</body>
</html>