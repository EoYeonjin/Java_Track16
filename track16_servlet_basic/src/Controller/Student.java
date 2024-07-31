package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.student.DBStudentDelete;
import command.student.DBStudentSave;
import command.student.DBStudentUpdate;
import command.student.StudentList;
import command.student.StudentUpdate;
import command.student.StudentView;
import member.DBMemberDelete;
/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		String viewPage = "";
		
		if(gubun == null) gubun = "list";
		
		//목록
		if(gubun.equals("list")) {
			StudentList stu = new StudentList();
			stu.execute(request);
			
			viewPage = "stu/student_list.jsp";
			
		//등록폼	
		}else if(gubun.equals("writeForm")) {
			viewPage = "stu/student_write.jsp";
			
		//저장	
		}else if(gubun.equals("save")) {
			DBStudentSave stu = new DBStudentSave();
			stu.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//상세정보	
		}else if(gubun.equals("view")) {
			StudentView stu = new StudentView();
			stu.execute(request);
			
			viewPage = "stu/student_view.jsp";
			
		//수정폼	
		}else if(gubun.equals("updateForm")) {
			StudentUpdate stu = new StudentUpdate();
			stu.execute(request);
			
			viewPage = "stu/student_update.jsp";
			
		//수정
		}else if(gubun.equals("update")) {
			DBStudentUpdate stu = new DBStudentUpdate();
			stu.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//삭제
		}else if(gubun.equals("delete")) {
			DBStudentDelete stu = new DBStudentDelete();
			stu.execute(request);
			
			viewPage = "common_alert.jsp";
		}
			
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
