package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberUpdate implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String info = request.getParameter("t_info");
		
		MemberDto dto = new MemberDto(id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, info);
		int result = dao.updateMember(dto);
		String msg = name+"님의 정보가 수정되었습니다";
		
		if(result != 1) msg = "수정 실패";
		else {
			HttpSession session = request.getSession();
			session.setAttribute("sessionName", name);
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		request.setAttribute("t_gubun", "myinfo");
	}

}
