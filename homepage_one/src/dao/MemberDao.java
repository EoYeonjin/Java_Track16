package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//ID중복 검사
	public int checkId(String id){
		int count = 0;
		String query = "select count(*) as count\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id='"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			System.out.println("checkId() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
	
	//register
	public int memberSave(MemberDto dto) {
		int result = 0;
		String query = "insert into jsl_어연진_member\r\n" + 
				"(id, name, password, job, \r\n" + 
				"tell_1, tell_2, tell_3, \r\n" + 
				"mobile_1, mobile_2, mobile_3, \r\n" + 
				"email_1, email_2, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getId()+"',	'"+dto.getName()+"', '"+dto.getPassword()+"', '"+dto.getJob()+"',\r\n" + 
				"'"+dto.getTell_1()+"', '"+dto.getTell_2()+"', '"+dto.getTell_3()+"',\r\n" + 
				"'"+dto.getMobile_1()+"', '"+dto.getMobile_2()+"', '"+dto.getMobile_3()+"',\r\n" + 
				"'"+dto.getEmail_1()+"', '"+dto.getEmail_2()+"', to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("memberSave() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//로그인
	public String getLoginName(String id, String password) {
		String name = "";
		String query = "select name \r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id='"+id+"' and password='"+password+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			System.out.println("getLoginName() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return name;
	}
	
	//상세조회_내정보
	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String query = "select id, name, password, job,\r\n" + 
				"tell_1, tell_2, tell_3,\r\n" + 
				"mobile_1, mobile_2, mobile_3,\r\n" + 
				"email_1, email_2, \r\n" + 
				"reg_date, last_login_date, exit_date\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String job = rs.getString("job");
				String tell_1 = rs.getString("tell_1");
				String tell_2 = rs.getString("tell_2");
				String tell_3 = rs.getString("tell_3");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String email_1 = rs.getString("email_1");
				String email_2 = rs.getString("email_2");
				String reg_date = rs.getString("reg_date");
				String last_login_date = rs.getString("last_login_date");
				String exit_date = rs.getString("exit_date");
				
				dto = new MemberDto(id, name, password, job, 
						tell_1, tell_2, tell_3, 
						mobile_1, mobile_2, mobile_3, 
						email_1, email_2, 
						reg_date, last_login_date, exit_date);
			}
		} catch (SQLException e) {
			System.out.println("getMemberInfo() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
}
