package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberInfo implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		String msg="", url="";
		if(id == null) {
			
			msg = "로그인 정보가 만료되었습니다. 다시 로그인 해주세요.";
			url = "Member";
			request.setAttribute("sessionGubun", "noSession");
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", url);
		}else {
			MemberDto dto = dao.getMemberInfo(id);
			request.setAttribute("sessionGubun", "yesSession");
			request.setAttribute("t_dto", dto);
		}

	}

}
