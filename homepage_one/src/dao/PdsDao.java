package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.PdsDto;

public class PdsDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//get number
	public String getNo() {
		String no = "";
		String query = "select nvl(max(to_number(no)), '0000') + 1 as no\r\n" + 
				"from JSL_어연진_PDS";
		
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
	
	//insert
	public int insertPds(PdsDto dto) {
		int result = 0;
		String query = "insert into JSL_어연진_PDS\r\n" + 
				"(no, title, content, attach, reg_id, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getAttach()+"', '"+dto.getReg_id()+"', to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertPds() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//조회
	public ArrayList<PdsDto> getPdsList(String select, String search, int start, int end){
		ArrayList<PdsDto> dtos = new ArrayList<PdsDto>();
		String query = "select * from(\r\n" + 
				"    select rownum as rnum, tbl.*\r\n" + 
				"    from(\r\n" + 
				"        select p.no, p.title, m.name as reg_name, p.attach,\r\n" + 
				"        to_char(p.reg_date, 'yyyy-MM-dd') as reg_date, p.hit\r\n" + 
				"        from JSL_어연진_pds p, JSL_어연진_MEMBER m \r\n" + 
				"        where p.reg_id = m.id\r\n" + 
				"        and p."+select+" like '%"+search+"%'\r\n" + 
				"        order by to_number(no) desc\r\n" + 
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
				String attach = rs.getString("attach");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				dtos.add(new PdsDto(no, title, attach, reg_name, reg_date, hit));
			}
		} catch (SQLException e) {
			System.out.println("getPdsList() method error\n"+query);
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
				"from JSL_어연진_PDS\r\n" + 
				"where "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
		} catch (SQLException e) {
			System.out.println("getPdsList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return totalCount;
	}
	
	//조회수 증가
	public int updateHit(String no) {
		int result = 0;
		String query = "update JSL_어연진_PDS\r\n" + 
				"set hit = hit + 1\r\n" + 
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
	
	//상세조회
	public PdsDto getPdsView(String no) {
		PdsDto dto = null;
		String query = "select p.title, p.content, m1.name as reg_name, p.attach,\r\n" + 
				"to_char(p.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, p.hit,\r\n" + 
				"to_char(p.update_date, 'yyyy-MM-dd hh24:mi:ss') as update_date, m2.name as update_name\r\n" +
				"from JSL_어연진_PDS p, jsl_어연진_member m1, jsl_어연진_member m2\r\n" + 
				"where p.reg_id = m1.id\r\n" + 
				"and p.update_id = m2.id(+)\r\n" +
				"and p.no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String reg_name = rs.getString("reg_name");
				String attach = rs.getString("attach");
				if(attach == null) attach = "첨부파일 없음";
				String reg_date = rs.getString("reg_date");
				String update_name = rs.getString("update_name");
				String update_date = rs.getString("update_date");
				int hit = rs.getInt("hit");
				
				
				dto = new PdsDto(title, content, attach, reg_name, reg_date, update_name, update_date, hit);
			}
		} catch (SQLException e) {
			System.out.println("getPdsList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//이전글
	public PdsDto getPrePds(String no) {
		PdsDto preDto = null;
		String query = "select p.no, p.title\r\n" + 
				"from \r\n" + 
				"    (select max(to_number(no)) as no\r\n" + 
				"    from JSL_어연진_PDS\r\n" + 
				"    where no < "+no+") a, jsl_어연진_pds p\r\n" + 
				"where a.no = p.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				String title = rs.getString("title");
				
				preDto = new PdsDto(no, title);
			}
		} catch (SQLException e) {
			System.out.println("getPrePds() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return preDto;
	}
	
	//다음글
	public PdsDto getNextPds(String no) {
		PdsDto nexDto = null;
		String query = "select p.no, p.title\r\n" + 
				"from \r\n" + 
				"    (select min(to_number(no)) as no\r\n" + 
				"    from JSL_어연진_PDS\r\n" + 
				"    where no > "+no+") a, jsl_어연진_pds p\r\n" + 
				"where a.no = p.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				String title = rs.getString("title");
				
				nexDto = new PdsDto(no, title);
			}
		} catch (SQLException e) {
			System.out.println("getNexPds() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return nexDto;
	}
}
