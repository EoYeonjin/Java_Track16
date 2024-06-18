<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%@ include file="../common_header.jsp" %> 
<%
	MemberDao dao = new MemberDao();
	request.setCharacterEncoding("UTF-8");
	MemberDto dto = null;
	if(sessionId == null){ 
%>
	<script type="text/javascript">
		alert("로그인 정보가 만료 되었습니다\n다시 로그인 하세요");
		location.href="member_login.jsp"
	</script>	
<% 
	}else{
		dto= dao.getMemberInfo(sessionId);
	}
	

%>		

  <script src="../js/common.js"></script>
  <script type="text/javascript">
  	//빈값 체크
  	function checkValue(obj, msg){
  		var result = false;
  		if(obj.value == ""){
  			alert(msg);
  			obj.focus();
  			result = true;
  		}
  		return result;
  	}
  	
  	//글자수 체크
  	function checkLength(obj, min, max, msg){
  		var result = false;
  		var len = obj.value.length;
  		if(len < min || len > max){
  			alert(msg+len);
  			obj.focus();
  			result = true;
  		}
  		return result;
  	}
  	
  	//저장
  	function goUpdate(){
  		if(checkValue(mem.t_name, '이름을 입력해주세요')) return;
  		if(checkLength(mem.t_name, 2, 10, '이름은 2자이상 10자 이내로 입력해주세요\n현재자릿수: ')) return;
  		
  		if(checkValue(mem.t_mobile_2, '전화번호를 입력해주세요')) return;
  		if(checkLength(mem.t_mobile_2, 4, 4, '전화번호는 4자입니다\n현재자릿수: ')) return;
  		
  		if(checkValue(mem.t_mobile_3, '전화번호를 입력해주세요')) return;
  		if(checkLength(mem.t_mobile_3, 4, 4, '전화번호는 4자입니다\n현재자릿수: ')) return;
  		
  		if(checkValue(mem.t_email_1, '이메일을 입력해주세요')) return;
  		if(checkLength(mem.t_email_1, 4, 20, '이메일은 4자이상 20자 이내로 입력해주세요\n현재자릿수: ')) return;
  		
  		if(checkValue(mem.t_password, 'password를 입력해주세요')) return;
		checkPassword();
		if(mem.t_password_check.value == "no"){
			alert("비밀번호가 맞지 않습니다\n다시 입력해주세요");
			mem.t_password.focus();
			return;
		}
		
		mem.method="post";
  		mem.action="db_member_update.jsp";
  		mem.submit();
  	}
  	
  	//비밀번호검사
  	function checkPassword(){
  		$.ajax({
  			type:"post",
  			url :"member_checkpassword.jsp",
  			async: false,
  			data:"t_id="+mem.t_id.value+"&t_password="+mem.t_password.value,
  			dataType:"text",
  			error:function(){
  				alert("통신 실패");
  			},
  			success:function(msg){
  				var result = $.trim(msg);	//공백 사라지게 하는 메소드
  				mem.t_password_check.value = result;
  			}
  		});
  	}
  	
  	//회원탈퇴
  	function goExit(){
  		if(confirm("정말 탈퇴하시겠습니까?")){
  			if(checkValue(mem.t_password, 'password를 입력해주세요')) return;
  			checkPassword();
  			if(mem.t_password_check.value == "no"){
  				alert("비밀번호가 맞지 않습니다\n다시 입력해주세요");
  				mem.t_password.focus();
  				return;
  			}
  			
  			mem.method="post";
  			mem.action="db_member_logout.jsp";
  			mem.submit()
  		}
  		
  		
  	}
  </script>
	
	<!-- sub contents -->
	<div class="sub_title">
		<h2>My Information</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="../index.jsp"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="notice.html">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">FAQ</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="con_title">
            <h1>내정보(개인회원)</h1>
         <p>HOME / 마이페이지 / 내정보(개인회원)</p>
        </div>
		<div class="join_write col_989">
                <div class="list_con">
                    <ul class="icon_type1">
                        <li>회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이 공개 또는 제3자에게 제공되지 않습니다.</li>
                    </ul>
                </div>
            <form name="mem">   
            <input type="hidden" name="t_password_check">
            <table class="table_write02" summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
                <caption>회원가입을 위한 정보입력표</caption>
                <colgroup>
                    <col width="160px">
                    <col width="auto">
                </colgroup>
                <tbody id="joinDataBody">
                    <tr>
                        <th><label for="id">아이디<span class="must"></span></label></th>
                        <td>
                        	<%=dto.getId() %>
                        	<input type="hidden" name="t_id" value="<%=dto.getId() %>">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="name">이름</label></th>
                        <td>
                            <input type="text" name="t_name" id="mbrName" class="w300" value="<%=dto.getName() %>">
                        </td>
                    </tr>
                    
                    <tr>
                        <th>소속<span class="must"><b>필수입력</b></span></th>
                        <td>
                            <label for="mbrClCd" class="blind">소속1차 카테고리 선택</label>
                            <select name="t_job" id="mbrClCd">
                                <option value="" <%if(dto.getJob().equals("")) out.print("selected"); %>>선택</option>
                                <option value="1" <%if(dto.getJob().equals("1")) out.print("selected"); %>>기업</option>
                                <option value="2" <%if(dto.getJob().equals("2")) out.print("selected"); %>>교수자</option>
                                <option value="3" <%if(dto.getJob().equals("3")) out.print("selected"); %>>미취업자</option>
                                <option value="4" <%if(dto.getJob().equals("4")) out.print("selected"); %>>학생</option>
                            </select>
                            <p class="guideTxt">학생 신분은 '미취업자-학생' 소속으로 선택해주십시오.</p>
                        </td>
                    </tr>
                    <tr>
                        <th>유선전화</th>
                        <td>
                            <input type="hidden" name="telNo" id="telNo" value="">
                            <label for="phone_number1" class="blind">유선전화 앞번호 선택</label>
                            <select name="t_tell_1" id="telNo1" class="w95">
                                <option value="" <%if(dto.getTell_1().equals("")) out.print("selected"); %>>선택</option>
                                <option value="02" <%if(dto.getTell_1().equals("02")) out.print("selected"); %>>02</option>
                                <option value="042" <%if(dto.getTell_1().equals("042")) out.print("selected"); %>>042</option>
                                <option value="051" <%if(dto.getTell_1().equals("051")) out.print("selected"); %>>051</option>
                                <option value="061" <%if(dto.getTell_1().equals("061")) out.print("selected"); %>>061</option>
                                <option value="070" <%if(dto.getTell_1().equals("070")) out.print("selected"); %>>070</option>
                            </select>
                            <input type="text" name="t_tell_2" value="<%=dto.getTell_2() %>" style="width:100px" id="telNo2" class="w95" value="" maxlength="3"><label for="phone_number2" class="blind">중간번호</label>
                            <input type="text" name="t_tell_3" value="<%=dto.getTell_3() %>" style="width:100px" id="telNo3" class="w95" value="" maxlength="4"><label for="phone_number3" class="blind">마직막번호</label>
                        </td>
                    </tr>
                    <tr>
                        <th>휴대전화<span class="must"></th>
                        <td>
                            <input type="hidden" name="mphonNo" id="mphonNo" value="">
                            <label for="mphonNo1" class="blind">휴대전화 앞번호 선택</label>
                            <select name="t_mobile_1" id="t_mobile_1" class="w95">
                                    <option value="010" <%if(dto.getMobile_1().equals("010")) out.print("selected"); %>>010</option>
                                    <option value="011" <%if(dto.getMobile_1().equals("011")) out.print("selected"); %>>011</option>
                            </select>
                            <input type="text" name="t_mobile_2" value="<%=dto.getMobile_2() %>" style="width:100px" id="mphonNo2" class="w95" maxlength="4"><label for="mphonNo2" class="blind">중간번호</label>
                            <input type="text" name="t_mobile_3" value="<%=dto.getMobile_3() %>" style="width:100px" id="mphonNo3" class="w95" maxlength="4"><label for="mphonNo3" class="blind">마직막번호</label>
                        </td>
                    </tr> 
                    <tr>
                        <th><label for="email">이메일</label></th>
                        <td>
                            <input type="email" name="t_email_1"  value="<%=dto.getEmail_1() %>" id="email" class="w600" >
                            @
                            <select name="t_email_2">
                            	<option value="">선택</option>
                            	<option value="naver.com" <%if(dto.getEmail_2().equals("naver.com")) out.print("selected"); %>>naver.com</option>
                            	<option value="gmail.com" <%if(dto.getEmail_2().equals("gmail.com")) out.print("selected"); %>>gmail.com</option>
                            	<option value="daum.net" <%if(dto.getEmail_2().equals("daum.net")) out.print("selected"); %>>daum.net</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="pw">비밀번호 확인<!-- <span class="must"><b>필수입력</b></span> --></label></th>
                        <td>
                            <input type="password" name="t_password" id="scrtNo" class="w300">
                            <p class="guideTxt"><span class="tc_point"></p>
                        </td>
                    </tr>
                    <tr>
                    	<th>최근 로그인 시간</th>
                    	<td><%=dto.getLast_login_date() %></td>
                    </tr>
                </tbody>
            </table>
            </form> 
        </div>
	</div>
	<!-- end contents -->
	
	<div class="btnArea Acenter pt60 pb100">
        <a href="javascript:goUpdate();" class="btn_round btn_large btn_pointColor w180"><b>수정 저장</b></a>
        <a href="javascript:goExit();" class="btn_round btn_large btn_BlueGray w180"><b>회원 탈퇴</b></a>
    </div>
	
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

	<footer class="footer">
		<%@ include file="../common_footer.jsp" %>
	</footer>

 </body>
</html>









 
