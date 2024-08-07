package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.MemberInfo;
import command.member.MemberJoin;
import command.member.MemberLogin;
import command.member.MemberLogout;
import common.CommonExecute;
import dao.MemberDao;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }
          
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "login";
		String viewPage = "";
		
		//로그인
		if(gubun.equals("login")) viewPage = "member/member_login.jsp";
		
		//회원가입
		else if(gubun.equals("join")) viewPage = "member/member_join.jsp";
		
		//등록(회원)
		else if(gubun.equals("save")) {
			CommonExecute member = new MemberJoin();
			member.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//로그인	
		}else if(gubun.equals("loginCheck")) {
			CommonExecute member = new MemberLogin();
			member.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//로그아숭	
		}else if(gubun.equals("logout")) {
			CommonExecute member = new MemberLogout();
			member.execute(request);
			
			viewPage = "common_alert.jsp";
		
		//내정보
		}else if(gubun.equals("myinfo")) {
			CommonExecute member = new MemberInfo();
			member.execute(request);
			
			String sessionGubun = (String)request.getAttribute("sessionGubun");
			if(sessionGubun.equals("noSession")) {
				viewPage = "common_alert.jsp";
			}else {
				viewPage = "member/member_myinfo.jsp";
			}
		
		//수정 형식
		}else if(gubun.equals("updateForm")) {
			CommonExecute member = new MemberInfo();
			member.execute(request);
			
			String sessionGubun = (String)request.getAttribute("sessionGubun");
			if(sessionGubun.equals("noSession")) {
				viewPage = "common_alert.jsp";
			}else {
				viewPage = "member/member_update.jsp";
			}
			
		//수정	
		}else if(gubun.equals("update")) {
			viewPage = "member/member_login.jsp";
		}
			
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
