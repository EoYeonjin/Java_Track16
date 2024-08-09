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
		
		function logincheck(){
			if(checkValue(loginform.t_id, "ID를 입력해주세요")) return;
			if(checkValue(loginform.t_pw, "비밀번호를 입력해주세요")) return;
			loginform.t_gubun.value="loginCheck";
			loginform.method="post";
			loginform.action="Member";
			loginform.submit();
		}
		
		function goPassword(){
			if(event.keyCode == 13) loginform.t_pw.focus();
		}
		
		function goLogin(){
			if(event.keyCode == 13) logincheck();
		}
		
		function goFindPW(){
			mem.t_gubun.value="findPasswordForm";
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
			<form name="loginform">
			<input type="hidden" name="t_gubun">
				<fieldset>
					<legend>로그인</legend>
					<div class="left-box">
						<p><label for="id" class="readonly">아이디</label>ID &nbsp;&nbsp;&nbsp;<input type="text" name="t_id" class="txt" id="id" placeholder="&nbsp;&nbsp;아이디를 입력하세요" onKeypress="goPassword()" autofocus></p>
						<p><label for="password" class="readonly">비밀번호</label>PW &nbsp;<input type="password" name="t_pw" class="txt" id="password" placeholder="&nbsp;&nbsp;비밀번호를 입력하세요" onKeypress="goLogin()"></p>
					</div>
					
					<div class="right-box">
						<input type="button" value="LOGIN" class="log" onClick="logincheck();">
					</div>
					
					<div class="checksave">
						<input type="checkbox" value="1" id="idsave" name="idsave"><label for="idsave">&nbsp;&nbsp;&nbsp;아이디 저장</label>
				
						<input type="checkbox" value="1" id="pwsave" name="pwsave" class="margin"><label for="pwsave">&nbsp;&nbsp;&nbsp;비밀번호 저장</label>
					</div>
						
						<p class="btn">
						<a href="javascript:goFindPW()">비밀번호 찾기</a>
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
					<li><a href="Index" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
		
	</body>
</html>