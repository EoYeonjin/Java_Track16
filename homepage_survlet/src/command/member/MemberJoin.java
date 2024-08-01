package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class MemberJoin implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_pw");
		String name = request.getParameter("t_name");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String info = request.getParameter("t_info");
		String reg_date = CommonUtil.getTodayTime();
		
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		MemberDto dto = new MemberDto(id, name, password, mobile_1, mobile_2, mobile_3, email_1, email_2, reg_date, info);
		int result = dao.insultMember(dto);
		String msg = "회원가입이 되었습니다";
		
		if(result != 1) msg = "등록 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
		
	}

}
