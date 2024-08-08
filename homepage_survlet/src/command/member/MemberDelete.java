package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberDelete implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("sessionId");
		String exit_date = CommonUtil.getTodayTime();
		
		String msg="", url="";
		if(id == null) {
			
			msg = "로그인 정보가 만료되었습니다. 다시 로그인 해주세요.";
			url = "Member";
			request.setAttribute("sessionGubun", "noSession");
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", url);
		}else {
		
		msg = "계정이 삭제되었습니다.";
		
		int result = dao.updateExitDate(id, exit_date);
		
		if(result != 1) msg = "삭제 실패";
		
		session.invalidate();
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
		}
		
		
	}

}
