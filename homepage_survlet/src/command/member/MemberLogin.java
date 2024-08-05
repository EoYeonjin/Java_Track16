package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class MemberLogin implements CommonExecute {
	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_pw");
		
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		MemberDto dto = dao.getLogName(id, password);
		HttpSession session = request.getSession();
		String msg = "ID나 비밀번호가 정확하지 않습니다", url = "Member";
		
		if(dto != null) {
			msg = dto.getName()+"님 환영합니다.";
			if(dto.getLast_login_date() != null) msg += " 최종 로그인 시간: "+dto.getLast_login_date();
			url = "Index";
			
			int result = dao.setMemberLoginTime(id, CommonUtil.getTodayTime());
			if(result != 1) System.out.println("회원 최종 로그인 시간 Update 오류 db_MemberLogin.jsp");
			
			if(dto.getName().equals("master")) session.setAttribute("sessionLevel", "top");
			else session.setAttribute("sessionLevel", "no");	
			
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionName", dto.getName());
			session.setMaxInactiveInterval(60 * 60);
			
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}
