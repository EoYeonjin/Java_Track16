package command.student;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.StudentDao;
import dto.StudentDto;

public class DBStudentSave implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		StudentDao dao = new StudentDao();
		
		String syear = request.getParameter("t_syear");
		String sclass = request.getParameter("t_sclass");
		String no = request.getParameter("t_no");
		String name = request.getParameter("t_name");
		String kor = request.getParameter("t_kor");
		String eng = request.getParameter("t_eng");
		String mat = request.getParameter("t_mat");
		
		StudentDto dto = new StudentDto(syear, sclass, no, name, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
		
		int result = dao.insertStd(dto);
		String msg = "학생이 등록되었습니다";
		
		if(result != 1) msg = "등록 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Student");
	}

}
