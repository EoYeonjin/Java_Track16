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
}
