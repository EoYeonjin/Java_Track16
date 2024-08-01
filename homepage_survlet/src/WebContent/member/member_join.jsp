<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">
	function goJoin() {
		if(checkValue(all.t_id, "ID를 입력해주세요")) return;
		
		if(checkValue(all.t_pw, "비밀번호를 입력해주세요")) return;
		if(checkValue(all.t_re_pw, "비밀번호를 한번 더 입력해주세요")) return;
		if(checkPassword(all.t_pw, all.t_re_pw)) return;
		
		if(checkLength(all.t_name, 2, 10, "성명은 최소 2자리 최대 10자리 이내입니다\n현재 자릿수: ")) return;
		if(checkValue(all.t_name, "이름을 입력해주세요")) return;
		
		if(checkValue(all.t_mobile_1, "전화번호를 입력해주세요")) return;
		if(checkLength(all.t_mobile_1, 3, 3, "전화번호 3자리를 입력해주세요\n현재 자릿수: ")) return;
		if(checkValue(all.t_mobile_2, "전화번호를 입력해주세요")) return;
		if(checkLength(all.t_mobile_2, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
		if(checkValue(all.t_mobile_3, "전화번호를 입력해주세요")) return;
		if(checkLength(all.t_mobile_3, 4, 4, "전화번호 4자리를 입력해주세요\n현재 자릿수: ")) return;
		
		if(checkValue(all.t_email_1, "이메일을 입력해주세요")) return;
		if(checkValue(all.t_email_2, "이메일을 입력해주세요")) return;
		
		if(!all.t_yak1.checked){
			alert("이용약관에 체크해주세요");
			return;
		}
		
		if(!all.t_yak2.checked){
			alert("개인정보이용동의에 체크해주세요");
			return;
		}
		
		if(all.t_checkValue.value == '사용 불가'){
			alert("사용 불가 ID입니다");
			all.t_id.focus();
			return;
		}
		
		if(all.t_id_checkValue.value != all.t_id.value){
			alert("변경된 ID 중복검사 하세요");
			all.t_id.focus();
			return;
		}
		
		all.t_gubun.value="save";
		all.method="post";
		all.action="Member";
		all.submit();
	}
	
	function emailChange(){
		all.t_email_2.value = all.t_emailtype.value;
	}
	
  	//중복검사
  	function checkId(){
  		if(checkLength(all.t_id, 3, 10, 'ID는 3자리 이상 10자리 이내 입력 후 중복검사 해주세요\n현재 자릿수: ')) return;
  		$.ajax({
  			type:"post",
  			url :"MemberCheckid",
  			data:"t_id="+all.t_id.value,
  			dataType:"text",
  			error:function(){
  				alert("통신 실패");
  			},
  			success:function(data){
  				var result = $.trim(data);	//공백 사라지게 하는 메소드
  				all.t_checkValue.value = result;
  				
  				if(result == "사용 가능") all.t_id_checkValue.value = all.t_id.value;
  				else all.t_id_checkValue.value = "";
  			}
  		});
  	}
</script>	
<!--  header end -->
	<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> SIGN UP</span></h2>	
				<p>- 회원가입을 위해, 작성해주세요 -</p>
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" name="all">
			<input type="hidden" name="t_gubun">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id">
								</label>
								<input type="text" id="tt_id" name="t_id" class="t_id"  placeholder="아이디">
								<input type="button" onClick="checkId()" class="t_id_button" id="idCheck" value="ID중복검사">
								<input type="text" name="t_checkValue" class="idCheck" /disabled>
								<input type="hidden" name="t_id_checkValue">								
							</li>
							<li>	
								<i class="fas fa-unlock-alt fa-2x" ></i>
								<label for="pw"><input type="password" name="t_pw" placeholder="비밀번호"></label>
							</li>	
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input type="password" name="t_re_pw" placeholder="비밀번호 재확인"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>						
								<label for="name"><input type="text" name="t_name" placeholder="이름"></label>
							</li>
							<li>
								<label for="mobile">
									<input type="text" name="t_mobile_1" maxlength="3" placeholder="010" class="mobile" >-
									<input type="text" name="t_mobile_2" maxlength="4" placeholder="1234" class="mobile">-
									<input type="text" name="t_mobile_3" maxlength="4" placeholder="5678" class="mobile">
								</label>
								<label for="certifi"></label>
								<label for="certifi"></label>
							</li>
							<li>
								<input type="text" name="t_email_1" class="email">&#64;
								<input type="text" name="t_email_2" class="email">
								<select name="t_emailtype" onchange="emailChange()" class="email">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
								<label for="agree"><input type="radio" name="t_info" id="agree" value="y" checked> 1년 정보유지</label>
								<label for="agree2"><input type="radio" name="t_info" id="agree2" value="n"> 탈퇴시까지 정보유지</label>
							</li>
							<li>
							<label for="yak1"><input type="checkbox" name="t_yak1" id="yak1">이용약관</label>
								<a href="#">전문보기</a>
								
							<label for="yak2"><input type="checkbox" name="t_yak2" id="yak2">개인정보이용동의</label>
								<a href="#">전문보기</a>
							</li>
						</ul>
						
						<ul class="signup">
						<input type="button" value="✔ SIGN UP" onClick="goJoin();">
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