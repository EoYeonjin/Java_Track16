package bookrent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberList implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		ArrayList<MemberDto> dtos = dao.getMemberList();
		request.setAttribute("t_dtos", dtos);
	}

}
