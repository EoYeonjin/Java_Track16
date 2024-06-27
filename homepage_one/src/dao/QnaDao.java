package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.QnaDto;

public class QnaDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//no 생성
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no), 0000) + 1 as no\r\n" + 
				"from JSL_어연진_QNA";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
			}
		} catch (SQLException e) {
			System.out.println("getNo() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return no;
	}
	
	//등록
	public int insertQna(QnaDto dto) {
		int result = 0;
		String query = "insert into JSL_어연진_QNA\r\n" + 
				"(no, title, content, reg_id, reg_date, answer_state)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"','"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getReg_id()+"', \r\n" + 
				"to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'),\r\n" + 
				"'"+dto.getAnswer_state()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate(); 
		} catch (SQLException e) {
			System.out.println("insertQna() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return result;
	}
	
	//조회
	public ArrayList<QnaDto> getQnaList(String select, String search, int start, int end){
		ArrayList<QnaDto> dtos = new ArrayList<QnaDto>();
		String query = "select * from\r\n" + 
				"    (select rownum as rnum, tbl.* from\r\n" + 
				"        (select q.no, q.title, q.answer_state, m.name, \r\n" + 
				"        to_char(q.reg_date, 'yyyy-MM-dd') as reg_date, q.hit\r\n" + 
				"        from jsl_어연진_member m, jsl_어연진_qna q\r\n" + 
				"        where m.id = q.reg_id\r\n" + 
				"        and q."+select+" like '%"+search+"%'\r\n" + 
				"        order by to_number(no) desc) tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end;
		String answer_msg = "답변대기";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String reg_name = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				String answer_state = rs.getString("answer_state");
				if(answer_state.equals("complet")) answer_msg = "답변완료";
				int hit = rs.getInt("hit");
				
				dtos.add(new QnaDto(no, title, reg_name, reg_date, answer_state, answer_msg, hit));
			}
		} catch (SQLException e) {
			System.out.println("getQnaList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return dtos;
	}
	
	//총 페이지 수
	public int getTotlaCount(String select, String search) {
		int totalCount = 0;
		String query = "select count(*) as totalCount\r\n" + 
				"from JSL_어연진_QNA\r\n" + 
				"where "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
		} catch (SQLException e) {
			System.out.println("getTotalCount() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return totalCount;
	}
	
	//조회수 증가
	public int updateHit(String no) {
		int result = 0;
		String query = "update JSL_어연진_QNA\r\n" + 
				"set hit = hit + 1\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate(); 
		} catch (SQLException e) {
			System.out.println("upadteHit() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return result;
	}
	
	//상세 목록 조회
	public QnaDto getQnaView(String no) {
		QnaDto dto = null;
		String query = "select q.title, q.content, m.name as reg_name, q.anwser_content,\r\n" + 
				"to_char(q.reg_date, 'yyyy-MM-dd'), q.hit\r\n" + 
				"from JSL_어연진_QNA q, JSL_어연진_MEMBER m\r\n" + 
				"where q.reg_id = m.id\r\n" + 
				"and q.no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String answer_content = rs.getString("answer_content");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				dto = new QnaDto(title, content, reg_name, reg_date, answer_content, hit);
			}
		} catch (SQLException e) {
			System.out.println("getQnaView() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.getConnection();
		}
		
		return dto;
	}
}
