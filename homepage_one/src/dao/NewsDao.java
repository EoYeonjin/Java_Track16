package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.NewsDto;

public class NewsDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//번호생성
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no), '0000') + 1 as no\r\n" + 
				"from JSL_어연진_NEWS";
		
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
	public int newsSave(NewsDto dto) {
		int result = 0;
		String query = "insert into jsl_어연진_news\r\n" + 
				"(no, title, content, reg_id, reg_date, ipt)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getReg_id()+"',\r\n" + 
				"to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'),\r\n" + 
				"'"+dto.getIpt()+"'\r\n" + 
				")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("newsSave() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//조회
	public ArrayList<NewsDto> getNewsList(String select, String search, int start, int end){
		ArrayList<NewsDto> dtos = new ArrayList<>();
		String query = "select * from(\r\n" + 
				"    select rownum as rnum, tbl.*\r\n" + 
				"    from(\r\n" + 
				"        select n.no, n.title, m.name as reg_name, n.ipt, \r\n" + 
				"        to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit\r\n" + 
				"        from jsl_어연진_member m, jsl_어연진_news n\r\n" + 
				"        where m.id = n.reg_id\r\n" + 
				"        and n."+select+" like '%"+search+"%'\r\n" + 
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
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				String ipt = rs.getString("ipt");
				int hit = rs.getInt("hit");
				
				dtos.add(new NewsDto(no, title, reg_name, reg_date, ipt, hit));
			}
		} catch (SQLException e) {
			System.out.println("getNewsList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//목록건수
	public int getTotalCount(String select, String search) {
		int totalCount = 0;
		String query = "select count(*) as count\r\n" + 
				"from JSL_어연진_NEWS\r\n" + 
				"where "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("count");
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
	public NewsDto getNewsView(String no) {
		NewsDto dto = null;
		String query = "select n.title, n.content, m.name as reg_name, n.hit, n.ipt,\r\n" + 
				"to_char(n.reg_date, 'yyyy-MM-dd') as reg_date\r\n" + 
				"from JSL_어연진_MEMBER m, JSL_어연진_NEWS n\r\n" + 
				"where m.id = n.reg_id\r\n" + 
				"and n.no like '%"+no+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				String ipt = rs.getString("ipt");
				int hit = rs.getInt("hit");
				
				dto = new NewsDto(no, title, content, reg_name, reg_date, ipt, hit);
			}
		} catch (SQLException e) {
			System.out.println("getNewsView() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수 
	public int updateHit(String no) {
		int hitCount = 0;
		String query = "update  JSL_어연진_NEWS\r\n" + 
				"set hit = hit + 1\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			hitCount = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateHit() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return hitCount;
	}
	
	//수정
	public int newsUpdate(NewsDto dto) {
		int result = 0;
		String query = "update  JSL_어연진_NEWS\r\n" + 
				"set title = '"+dto.getTitle()+"', content = '"+dto.getContent()+"', ipt = '"+dto.getIpt()+"'\r\n" + 
				"where no = '"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("newsUpdate() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int newsDelete(String no) {
		int result = 0;
		String query = "delete from JSL_어연진_News\r\n" + 
				"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("newsDelete() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//이전글
	public NewsDto getPreNews(String no, String ipt) {
		NewsDto dto = null;
		String query = "select a.no, b.title\r\n" + 
				"from\r\n" + 
				"    (select max(to_number(no)) as no\r\n" + 
				"    from jsl_어연진_news\r\n" + 
				"    where no < "+no+" and ipt = '"+ipt+"') a, jsl_어연진_news b\r\n" + 
				"where a. no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new NewsDto(no, title, ipt);
			}
		} catch (SQLException e) {
			System.out.println("getPreNews() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//다음글
	public NewsDto getNextNews(String no, String ipt) {
		NewsDto dto = null;
		String query = "select a.no, b.title\r\n" + 
				"from\r\n" + 
				"    (select min(to_number(no)) as no\r\n" + 
				"    from jsl_어연진_news\r\n" + 
				"    where no > "+no+" and ipt='"+ipt+"') a, jsl_어연진_news b\r\n" + 
				"where a. no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				String title = rs.getString("title");
				
				dto = new NewsDto(no, title, ipt);
			}
			
		} catch (SQLException e) {
			System.out.println("getNextNews() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
}
