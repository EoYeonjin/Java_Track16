package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CMemberWrite {

	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		ArrayList<MemberDto> dtos = dao.getAreaCode();
		
		request.setAttribute("t_dtos", dtos);

	}

}
