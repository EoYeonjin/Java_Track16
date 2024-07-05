package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.FaqDto;

public class FaqDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//번호생성
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no), '0000') + 1 as no\r\n" + 
				"from JSL_어연진_FAQ";
		
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
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}
	
	//등록
	public int insertFaq(FaqDto dto) {
		int result = 0;
		String query = "insert into JSL_어연진_FAQ\r\n" + 
				"(no, title, answer_content, reg_id, reg_date, ipt)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getAnswer_content()+"', '"+dto.getReg_id()+"', \r\n" + 
				"to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'), '"+dto.getIpt()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertFaq() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//조회
	public ArrayList<FaqDto> getFaqList(String select, String search, int start, int end) {
		ArrayList<FaqDto> dtos = new ArrayList<FaqDto>();
		String query = "select * from(\r\n" + 
				"    select rownum as rnum, tbl.*\r\n" + 
				"    from(\r\n" + 
				"        select f.no, f.title, f.answer_content, m.name as reg_name, \r\n" + 
				"        to_char(f.reg_date, 'yyyy-MM-dd') as reg_date, hit, ipt\r\n" + 
				"        from JSL_어연진_FAQ f, JSL_어연진_MEMBER m\r\n" + 
				"        where f.reg_id = m.id\r\n" + 
				"        and f."+select+" like '%"+search+"%'\r\n" + 
				"        order by decode(ipt, 'i', '1'), to_number(no) desc\r\n" + 
				") tbl)\r\n" + 
				"where rnum>= "+start+" and rnum<="+end;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String answer_content = rs.getString("answer_content");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				String ipt = rs.getString("ipt");
				int hit = rs.getInt("hit");
				
				dtos.add(new FaqDto(no, title, answer_content, reg_name, reg_date, hit, ipt));
			}
		} catch (SQLException e) {
			System.out.println("getFaqList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//페이지 조회
	public int getTotalCount(String select, String search) {
		int totalCount = 0;
		String query = "select count(*) as totalCount\r\n" + 
				"from JSL_어연진_FAQ\r\n" + 
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
			DBConnection.closeDB(con, ps, rs);
		}
		
		return totalCount;
	}
	
	//상세조회
	public FaqDto getFaqView(String no) {
		FaqDto dto = null;
		String query = "select f.title, f.answer_content, m.name as reg_name, \r\n" + 
				"to_char(f.reg_date, 'yyyy-MM-dd') as reg_date, ipt\r\n" + 
				"from JSL_어연진_FAQ f, JSL_어연진_MEMBER m \r\n" + 
				" where f.reg_id = m.id\r\n" + 
				" and f.no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String answer_content = rs.getString("answer_content");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				String ipt = rs.getString("ipt");
				
				dto = new FaqDto(title, answer_content, reg_name, reg_date, ipt);
			}
		} catch (SQLException e) {
			System.out.println("getFaqView() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//수정
	public int updateFaq(FaqDto dto) {
		int result = 0;
		String query = "update JSL_어연진_FAQ\r\n" + 
				"set title='"+dto.getTitle()+"', answer_content='"+dto.getAnswer_content()+"', ipt='"+dto.getIpt()+"'\r\n" + 
				"where no='"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateFaq() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int deleteFaq(String no) {
		int result = 0;
		String query = "delete from JSL_어연진_FAQ\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteFaq() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//조회수 증가
	public int updateHit(String no) {
		int result = 0;
		String query = "update JSL_어연진_FAQ\r\n" + 
				"set hit = hit +1\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateHit() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//조회수 조회
	public int getHit(String no) {
		int hit = 0;
		String query = "select hit \r\n" + 
				"from jsl_어연진_faq\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				hit = rs.getInt("hit");
			}
		} catch (SQLException e) {
			System.out.println("getHit() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return hit;
	}
}
