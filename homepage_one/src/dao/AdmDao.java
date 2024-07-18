package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.AdmDto;

public class AdmDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//조회
	public ArrayList<AdmDto> getAdmList(String select, String search, int start, int end){
		ArrayList<AdmDto> dtos = new ArrayList<AdmDto>();
		String query = "select * from(    \r\n" + 
				"    (select rownum as rnum, tbl.*\r\n" + 
				"    from\r\n" + 
				"        (select rownum, id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, \r\n" + 
				"        to_char(exit_date, 'yyyy-MM-dd') as exit_date\r\n" + 
				"        from  jsl_어연진_member\r\n" + 
				"        where "+select+" like '%"+search+"%'\r\n" + 
				"        order by reg_date desc)tbl, jsl_어연진_member m\r\n" + 
				"    where tbl.id = m.id))\r\n" + 
				"where rnum >="+start+" and rnum <="+end;
		System.out.println(query);
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("rownum");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String email_1 = rs.getString("email_1");
				String email_2 = rs.getString("email_2");
				String exit_date = rs.getString("exit_date");
				
				dtos.add(new AdmDto(no, id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, exit_date));
			}
		} catch (SQLException e) {
			System.out.println("getAdmList() method error\n"+query);
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
				"from jsl_어연진_member\r\n" + 
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
	public AdmDto getAdmView(String id) {
		AdmDto dto = null;
		String query = "select name, PASSWORD, JOB, \r\n" + 
				"TELL_1, TELL_2, TELL_3, \r\n" + 
				"MOBILE_1, MOBILE_2, MOBILE_3,\r\n" + 
				"EMAIL_1, EMAIL_2, to_char(reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date,\r\n" + 
				"to_char(LAST_LOGIN_DATE, 'yyyy-MM-dd hh24:mi:ss') as LAST_LOGIN_DATE,\r\n" + 
				"to_char(EXIT_DATE, 'yyyy-MM-dd hh24:mi:ss') as EXIT_DATE\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String password = rs.getString("password");
				String name = rs.getString("name");
				String job = rs.getString("job");
				String tell_1 = rs.getString("tell_1");
				if(tell_1 == null) tell_1 = "";
				String tell_2 = rs.getString("tell_2");
				if(tell_2 == null) tell_2 = "";
				String tell_3 = rs.getString("tell_3");
				if(tell_3 == null) tell_3 = "";
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String email_1 = rs.getString("email_1");
				String email_2 = rs.getString("email_2");
				String reg_date = rs.getString("reg_date");
				String last_login_date = rs.getString("LAST_LOGIN_DATE");
				String exit_date = rs.getString("exit_date");
				
				dto = new AdmDto(id, name, password, job, tell_1, tell_2, tell_3, mobile_1, mobile_2, mobile_3, email_1, email_2, reg_date, last_login_date, exit_date);
			}
		} catch (SQLException e) {
			System.out.println("getAdmView() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//삭제
	public int deleteAdm(String id) {
		int result = 0;
		String query = "delete from jsl_어연진_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteAdm() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//탈퇴일자 수정
	public int updateExitDate(String id, String exit_date) {
		int result = 0;
		String query = "update jsl_어연진_member\r\n" + 
				"set exit_date = to_date('"+exit_date+"', 'yyyy-MM-dd hh24:mi:ss')\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteAdm() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
