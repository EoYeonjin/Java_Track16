<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!doctype html>
<html lang="ko">
	<title>어연진 El Wide</title>
		<meta charset="utf-8">
		<link href="css/common.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
		<link href="css/join.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/common.js"></script>
		<script src="https://kit.fontawesome.com/17da812ad5.js" crossorigin="anonymous"></script>
		<script type="text/javascript">
			function goMyinfo(){
				commonMember.t_gubun.value="myinfo";
				commonMember.method="post";
				commonMember.action="Member";
				commonMember.submit();
			}
		</script>
	<body>
		<form name="commonMember">
			<input type="hidden" name="t_gubun">
		</form>
		<!-- skip navigation -->
		<dl id="access">
			<dt>바로가기 및 건너띄기 링크</dt>
			<dd><a href="#contents">본문바로가기</a></dd>
			<dd><a href="#navigation">주메뉴 바로가기</a></dd>
		</dl>
		<hr>
		
		<div id="big-box">
			<header>
			<div id="header">
				<div class="nav">
					<h2 class="readonly">주메뉴</h2>
						<ul class="nav-menu">
						<li><a href="sub1.html">ABOUT EL WIDE</a></li>
						<li><a href="sub2.html">MUSIC</a></li>
						<li><a href="sub3.html">MEDIA</a></li>
						<li><a href="sub4.html">CULTURE</a></li>
						<li><a href="sub4.html">NOTICE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="Index"><img src="images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank">
							<i class="fab fa-youtube"> </i></a></li>
							<c:if test="${not empty sessionId }">
								<li><a href="javascript:goMyinfo()" title="My Information"><i class="fa-solid fa-user"></i></a></li>
							</c:if>
							<li>
								<c:if test="${empty sessionId}">
									<a href="Member" title="Login"><i class="fa-solid fa-right-to-bracket"></i></a>
								</c:if><c:if test="${not empty sessionId}">	
									<a href="Member?t_gubun=logout" title="Logout"><i class="fa-solid fa-right-from-bracket"></i></a>
								</c:if>	
							</li>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
							<c:if test="${not empty sessionId }">
								<li>${sessionName }님 환영합니다.</li>
							</c:if><c:if test="${empty sessionId }">
								<li>CONNECT WITH WIDE</li>	
							</c:if>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>