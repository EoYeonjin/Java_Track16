<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.*" %>    
<%@ include file="../common_header.jsp" %>
<%if(!sessionLevel.equals("top")){ %>
<script type="text/javascript">
	alert("관리자 화면입니다");
	location.href="../index.jsp";
</script>
<%} %>
<script type="text/javascript">
	function goSave(){
		if(checkValueLength(pds.t_title, 10, 40, "제목을 입력해주세요", '제목은 10자 이상 40자 이내로 입력해주세요\n현재자릿수: ')) return;
		if(checkValueLength(pds.t_content, 1, 800, "내용을 입력해주세요", '내용은 1자 이상 800자 이내로 입력해주세요\n현재자릿수: ')) return;
		
		// 1.확장자 검사
		var fileName = pds.t_attach.value;
		if(fileName != ""){ //  C:\fakepath\img_1.png
			var pathFileName = fileName.lastIndexOf(".")+1;    //확장자 제외한 경로+파일명
			var extension = (fileName.substr(pathFileName)).toLowerCase();	//확장자명
			//파일명.확장자
			if(extension != "pdf" && extension != "hwp" && extension != "png"){
				alert(extension +" 형식 파일은 업로드 안됩니다. 한글, PDF 파일만 가능!");
				return;
			}		
		}
		
		// 2.첨부 용량 체크	
		var file = pds.t_attach;
		var fileMaxSize  = 10; // 첨부 최대 용량 설정
		if(file.value !=""){
			// 사이즈체크
			var maxSize  = 1024 * 1024 * fileMaxSize;
			var fileSize = 0;
			// 브라우저 확인
			var browser=navigator.appName;
			// 익스플로러일 경우
			if (browser=="Microsoft Internet Explorer"){
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else {
			// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}

			if(fileSize > maxSize){
				alert(" 첨부파일 사이즈는 "+fileMaxSize+"MB 이내로 등록 가능합니다. ");
				return;
			}	
		}		
		
		pds.method="post";
		pds.action="db_pds_save.jsp";
		pds.submit();
	}
</script>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>자료실</h2>
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
					<a href="">자료실<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="../faq/faq_list.jsp">FAQ</a>
						<a href="pds_list.jsp">자료실</a>
						<%if(sessionLevel.equals("top")){ %>
							<a href="../adm/adm_list.jsp">관리자</a>
						<%} %>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">자료실 글쓰기</h2>
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	  <form name="pds" enctype="multipart/form-data">
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>제목</th>
						<td colspan="3"><input type="text" name="t_title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea name="t_content"></textarea></td>
					</tr>
					<tr>
						<th>첨부</th>
						<td colspan="3"><input type="file" name="t_attach"></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><%=sessionName %></td>
						<th>등록 일자</th>
						<td><%=CommonUtil.getToday() %></td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn_wrap">
				<input type="button" value="저장" class="btn_ok" onClick="javascript:goSave()">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='pds_list.jsp';">
			</div>
	  </div>
	  
	</div>
	<!-- end contents -->
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
