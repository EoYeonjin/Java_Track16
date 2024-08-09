<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">
	function emailChange(){
		mem.t_email_2.value = mem.t_emailtype.value;
	}
	
	function goUpdate(){
		if(checkLength(mem.t_re_pw, 3, 10, "비밀번호를 입력해주세요\n현재 자릿수: ")) return;
		checkPassword();
		if(mem.t_pw_check.value == "no"){
			alert("비밀번호가 정확하지 않습니다");
			mem.t_pw_check.focus();
			return;
		}
		
		if(checkLength(mem.t_name, 2, 10, "성명은 최소 2자리 최대 10자리 이내입니다\n현재 자릿수: ")) return;
		if(checkValue(mem.t_name, "이름을 입력해주세요")) return;
		
		if(checkValue(mem.t_mobile_1, "전화번호를 입력해주세요")) return;
		if(checkLength(mem.t_mobile_1, 3, 3, "전화번호 3자리를 입력해주세요\n현재 자릿수: ")) return;
		if(checkValue(mem.t_mobile_2, "전화번호를 입력해주세요")) return;
		if(checkLength(mem.t_mobile_2, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
		if(checkValue(mem.t_mobile_3, "전화번호를 입력해주세요")) return;
		if(checkLength(mem.t_mobile_3, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
		
		if(checkValue(mem.t_email_1, "이메일을 입력해주세요")) return;
		if(checkValue(mem.t_email_2, "이메일을 입력해주세요")) return;
		
		mem.t_gubun.value="update";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
  	//중복검사
  	function checkPassword(){
  		$.ajax({
  			type:"post",
  			url :"MemberCheckPassword",
  			async:false,
  			data:"t_pw="+mem.t_re_pw.value+"&t_id="+mem.t_id.value,
  			dataType:"text",
  			error:function(){
  				alert("통신 실패");
  			},
  			success:function(data){
  				var result = $.trim(data);	//공백 사라지게 하는 메소드
  				mem.t_pw_check.value=result;
  			}
  		});
  	}
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
			
			<form class="join" name="mem">
				<input type="hidden" name="t_gubun">
				<input type="hidden" name="t_pw_check">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<input type="text" id="tt_id" value="${t_dto.getId() }" /disabled>
								<input type="hidden" name="t_id" value="${t_dto.getId() }">
							</li>
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input type="password" name="t_re_pw" placeholder="비밀번호 확인"></label>
							</li>	
							
						</ul>
						
						<ul class="name_phone">
							<li>	
								<label for="name"><input type="text" name="t_name" value="${t_dto.getName() }"></label>
							</li>	
							<li>	
								<input type="text" name="t_mobile_1" maxlength="3" value="${t_dto.getMobile_1() }" class="mobile" >-
								<input type="text" name="t_mobile_2" maxlength="4" value="${t_dto.getMobile_2() }" class="mobile">-
								<input type="text" name="t_mobile_3" maxlength="4" value="${t_dto.getMobile_3() }" class="mobile">
							</li>
							<li>	
								<input type="text" name="t_email_1" class="email" value="${t_dto.getEmail_1() }">&#64;
								<input type="text" name="t_email_2" class="email" value="${t_dto.getEmail_2() }">
								<select name="t_emailtype" onchange="emailChange()" class="email">
									<option value="">직접입력</option>
									<option value="naver.com" <c:if test="${t_dto.getEmail_2() eq 'naver.com'}">selected</c:if>>naver.com</option>
									<option value="daum.net" <c:if test="${t_dto.getEmail_2() eq 'daum.net'}">selected</c:if>>daum.net</option>
									<option value="gmail.com" <c:if test="${t_dto.getEmail_2() eq 'gmail.com'}">selected</c:if>>gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<li>	
								<label for="agree"><input type="radio" name="t_info" id="agree" value="y" <c:if test="${t_dto.getInfo() eq 'y' }">checked</c:if>> 1년 정보유지</label>
								<label for="agree2"><input type="radio" name="t_info" id="agree2" value="n" <c:if test="${t_dto.getInfo() eq 'n' }">checked</c:if>> 탈퇴시까지 정보유지</label>
							</li>
							<li>	
								<span class="myTitle"><i class="fa-solid fa-registered"></i> 가입일자</span>
								<span class="myContent">${t_dto.getReg_date() }</span>
							</li>
							<li>	
								<span class="myTitle"><i class="fa-solid fa-pen"></i> 최근 로그인 일자</span>
								<span class="myContent">${t_dto.getLast_login_date() }</span>
							</li>
						</ul>
						
						<ul class="signup">
							<input type="button" value="✔ SAVE" onClick="goUpdate();">
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