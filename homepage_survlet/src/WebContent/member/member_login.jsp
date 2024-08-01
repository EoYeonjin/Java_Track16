<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<!--  header end -->
	<script type="text/javascript">
		function goJoin(){
			mem.t_gubun.value="join";
			mem.method="post";
			mem.action="Member";
			mem.submit();
		}	
	</script>	
		<form name="mem">
			<input type="hidden" name="t_gubun">
		</form>		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> LOGIN </span></h2>	
				<p>- 로그인이 필요합니다 -</p>
			</div>
			
		<!--login start-->
			<div class="login-box">
			<form name="loginform" method="post" enctype="multipart/form-data" action="logincheck.html">
				<fieldset>
					<legend>로그인</legend>
					<div class="left-box">
						<p><label for="id" class="readonly">아이디</label>ID &nbsp;&nbsp;&nbsp;<input type="text" class="txt" id="id" placeholder="&nbsp;&nbsp;아이디를 입력하세요"></p>
						<p><label for="password" class="readonly">비밀번호</label>PW &nbsp;<input type="password" class="txt" id="password" placeholder="&nbsp;&nbsp;비밀번호를 입력하세요"></p>
					</div>
					
					<div class="right-box">
						<input type="button" value="LOGIN" class="log" onClick="logincheck();">
					</div>
					
					<div class="checksave">
						<input type="checkbox" value="1" id="idsave" name="idsave"><label for="idsave">&nbsp;&nbsp;&nbsp;아이디 저장</label>
				
						<input type="checkbox" value="1" id="pwsave" name="pwsave" class="margin"><label for="pwsave">&nbsp;&nbsp;&nbsp;비밀번호 저장</label>
					</div>
						
						<p class="btn">
						<a href="#">ID/PW찾기</a>
						<a href="javascript:goJoin()">회원가입</a>
						</p>
						
				</fieldset>
			</form>
			</div>
		</div>
		
		
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
	
	
		<script>
			function logincheck() {
				if(loginform.id.value=="") {
				alert("아이디를 입력하세요");
				loginform.id.focus();
				return;
				}
				if(loginform.password.value=="") {
				alert("비밀번호를 입력하세요");
				loginform.password.focus();
				return;
				}
				loginform.submit();
			}
		</script>
		
	</body>
</html>