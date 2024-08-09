package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.MailContentSend;
import dao.MemberDao;
import dto.MemberDto;

public class MemberPasswordSend implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		
		String toEmail = dao.getEmail(id, name, mobile_1, mobile_2, mobile_3);
		String msg = "";
		
		if(toEmail.equals("")) {
			msg = "존재 하지 않는 회원 정보입니다.";
		}else {
			MailContentSend mail = new MailContentSend();
			String mailServer = "smtp.naver.com";
			String mailUserId = "wks1211";
			String mailPassword = "dusrud823";
			
			mail.setMail(mailServer, mailUserId, mailPassword);
			
			String mailTitle = "EL WIDE에서 새로운 비밀번호를 발송합니다.";
			String mailContent= "비밀번호는 1234입니다.";
			
			String fromName = "EL WIDE";
			String fromEmailAddress = "wks1211@naver.com";
			
			msg = name + "님 새비밀번호를 이메일로 발송하였습니다.";
			
			try {
				mail.sendMail(fromName, fromEmailAddress, toEmail, mailTitle, mailContent);
			} catch (Exception e) {
				msg = "비밀번호 발송 실패! 관리자에게 문의 바랍니다.";
				e.printStackTrace();
			}
			
			
		}

		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
	}

}
