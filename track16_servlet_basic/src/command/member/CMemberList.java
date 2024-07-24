package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CMemberList {
	public void execute(HttpServletRequest request){
		MemberDao dao = new MemberDao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select ="id";
			search ="";
		}
		
		ArrayList<MemberDto> dtos =	dao.memberList(select, search);
		
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);		
	}
}





