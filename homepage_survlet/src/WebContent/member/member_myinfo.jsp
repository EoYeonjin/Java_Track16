<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">

</script>	
<!--  header end -->
	<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> My Information</span></h2>	
				<p>- ${sessionName }의 회원가입 정보입니다. -</p>
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" name="all">
			<input type="hidden" name="t_gubun">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li style="border-bottom:1px dotted gray;">
								<span class="myTitle"><i class="fa-solid fa-check"></i> ID</span>
								<span class="myContent">${t_dto.getId() }</span>
							</li>
							<li>	
								<span class="myTitle"><i class="fa-solid fa-user-tag"></i> 성 명</span>
								<span class="myContent">${t_dto.getName() }</span>
							</li>	
							
						</ul>
						
						<ul class="name_phone">
							<li style="border-bottom:1px dotted gray;">	
								<span class="myTitle"><i class="fa-solid fa-square-phone"></i> Mobile</span>
								<span class="myContent">${t_dto.getMobile_1() } - ${t_dto.getMobile_2() } - ${t_dto.getMobile_3() }</span>
							</li>
							<li>	
								<span class="myTitle"><i class="fa-solid fa-at"></i> Email</span>
								<span class="myContent">${t_dto.getEmail_1() }@${t_dto.getEmail_2() }</span>
							</li>
						</ul>
							
						<ul class="name_phone">
							<li style="border-bottom:1px dotted gray;">	
								<span class="myTitle"><i class="fa-solid fa-file-signature"></i> 정보 수신 동의</span>
								<c:if test="${t_dto.getInfo() eq 'y' }">
									<span class="myContent">1년 정보유지</span>
								</c:if><c:if test="${t_dto.getInfo() eq 'n' }">
									<span class="myContent">탈퇴시까지 정보 유지</span>
								</c:if>
							</li>
							<li style="border-bottom:1px dotted gray;">	
								<span class="myTitle"><i class="fa-solid fa-registered"></i> 가입일자</span>
								<span class="myContent">${t_dto.getReg_date() }</span>
							</li>
							<li>	
								<span class="myTitle"><i class="fa-solid fa-pen"></i> 최근 로그인 일자</span>
								<span class="myContent">${t_dto.getLast_login_date() }</span>
							</li>
						</ul>
				</fieldset>
			</form>
			
				<div class="login_img">
					<li class="photo1"> </li>
					<li class="photo2"> </li>
					<li class="photo3"> </li>
					<li class="photo4"> </li>
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