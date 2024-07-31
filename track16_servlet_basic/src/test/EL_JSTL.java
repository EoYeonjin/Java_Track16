package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDto;

/**
 * Servlet implementation class EL_JSTL
 */
@WebServlet("/EL_JSTL")
public class EL_JSTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EL_JSTL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "홍길동";
		int age = 19;
		
		request.setAttribute("t_name", name);
		request.setAttribute("t_age", age);
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("서울");
		arr.add("대전");
		arr.add("대구");
		
		request.setAttribute("t_arr", arr);
		
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		dtos.add(new MemberDto("101", "skantmdgus", "pyeongyang", 25));
		dtos.add(new MemberDto("201", "rladbtjd", "seoul", 35));
		dtos.add(new MemberDto("301", "qkrtlrn", "daejeon", 45));
		request.setAttribute("t_dtos", dtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("test/el_jstl.jsp");
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
