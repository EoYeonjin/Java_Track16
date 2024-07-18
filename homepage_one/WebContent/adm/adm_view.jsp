<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %> 
<%
	request.setCharacterEncoding("utf-8");
	AdmDao dao = new AdmDao();
	
	String id = request.getParameter("t_id");
	
	AdmDto dto = dao.getAdmView(id); 
%>   
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">
	function goExit(){
		if(confirm("회원을 탈퇴시키겠습니까?")){
			adm.method="post";
			adm.action="db_adm_exit.jsp";
			adm.submit();
		}
	}
</script>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>회원상세목록</h2>
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
					<a href="">관리자<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="../faq/faq_list.jsp">FAQ</a>
						<a href="../pds/pds_list.jsp">자료실</a>
						<a href="adm_list.jsp">관리자</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="con_title">
            <h1>회원 정보</h1>
         <p>HOME / 관리자 / 회원정보</p>
        </div>
		<div class="join_write col_400">
            <form name="adm">   
            <table class="table_write02" style="align:center">
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
                        <td><%=dto.getName() %></td>
                    </tr>
                    
                    <tr>
                        <th>소속</th>
                        <td>
                        	<%	String job = dto.getJob();
                     			if(job.equals("1")) job = "기업";
                       			else if(job.equals("2"))  job = "교수자";
                       			else if(job.equals("3"))  job = "미취업자";
                       			else if(job.equals("4"))  job = "학생";
                     			out.print(job);
                     		%>
                        </td>
                    </tr>
                    <tr>
                        <th>유선전화</th>
                        <td>
                        	<%
                        		if(!dto.getTell_1().equals("")){
                        			out.print(dto.getTell_1()+"-"+dto.getTell_2()+"-"+dto.getTell_3());
                        		}else out.print("-");
                     		%>
                        </td>
                    </tr>
                    <tr>
                        <th>휴대전화</th>
                        <td>
                            <%
                        		if(!dto.getMobile_1().equals("")){
                        			out.print(dto.getMobile_1()+"-"+dto.getMobile_2()+"-"+dto.getMobile_3());
                        		}else out.print("없음");
                     		%>
                        </td>
                    </tr> 
                    <tr>
                        <th>이메일</th>
                        <td>
                             <%out.print(dto.getEmail_1()+"@"+dto.getEmail_2()); %>
                        </td>
                    </tr>
                    <tr>
                    	<th>등록일</th>
                    	<td><%=dto.getReg_date() %></td>
                    </tr>
                    <%if(dto.getLast_login_date() != null){ %>
	                    <tr>
	                    	<th>최근 로그인 시간</th>
	                    	<td><%=dto.getLast_login_date() %></td>
	                    </tr>
                    <%} %>
                    <%if(dto.getExit_date() != null){ %>
	                    <tr>
	                    	<th>탈퇴일</th>
	                    	<td><%=dto.getExit_date() %></td>
	                    </tr>
                    <%} %>
                </tbody>
            </table>
            </form> 
        </div>
	</div>

	<!-- end contents -->
	
	<div class="btnArea Acenter pt60 pb100">
       	<a href="adm_list.jsp" class="btn_round btn_large btn_pointColor w180"><b>목록</b></a>
       	<%if(dto.getExit_date() == null){ %>
       		<a href="javascript:goExit();" class="btn_round btn_large btn_RedOrange w180"><b>탈퇴</b></a>
       	<%} %>	
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