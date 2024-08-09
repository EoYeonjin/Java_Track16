<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<!--  header end -->
	<script type="text/javascript">
		function passwordSend(){
			if(checkValue(pw.t_id, "ID를 입력해주세요")) return;
			
			if(checkValue(pw.t_name, "성명을 입력해주세요")) return;
			if(checkLength(pw.t_name, 2, 10, "성명은 최소 2자리 최대 10자리 이내입니다\n현재 자릿수: ")) return;
			
			if(checkValue(pw.t_mobile_1, "전화번호를 입력해주세요")) return;
			if(checkLength(pw.t_mobile_1, 3, 3, "전화번호 3자리를 입력해주세요\n현재 자릿수: ")) return;
			if(checkValue(pw.t_mobile_2, "전화번호를 입력해주세요")) return;
			if(checkLength(pw.t_mobile_2, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
			if(checkValue(pw.t_mobile_3, "전화번호를 입력해주세요")) return;
			if(checkLength(pw.t_mobile_3, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
			
			pw.t_gubun.value="passwordSend";
			pw.method="post";
			pw.action="Member";
			pw.submit();
		}
	</script>	
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> Find Password </span></h2>	
				<p>- 비밀번호를 e-mail로 발송합니다 -</p>
			</div>
			
		<!--login start-->
			<div class="login-box">
			<form name="pw">
			<input type="hidden" name="t_gubun">
				<fieldset>
					<legend>로그인</legend>
					<div class="left-box">
						<p>ID &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="t_id" class="txt" placeholder="&nbsp;&nbsp;아이디를 입력하세요" onKeypress="goPassword()" autofocus></p>
						<p>성명 &nbsp;<input type="text" name="t_name" class="txt" placeholder="&nbsp;&nbsp;성명을 입력하세요" onKeypress="goLogin()"></p>
						<p>연락처 &nbsp;
							<input type="text" name="t_mobile_1" class="txt_small" placeholder="010"> - 
							<input type="text" name="t_mobile_2" class="txt_small" placeholder="1234"> - 
							<input type="text" name="t_mobile_3" class="txt_small" placeholder="5678">
						</p>
					</div>
					
					<div class="right-box">
						<input type="button" value="CHECK" class="find" onClick="passwordSend();">
					</div>
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