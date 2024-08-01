package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	//아이디 중복 체크
	public int memberCheckid(String id) {
		int count = 0;
		String query = "select count(id) as count from jsl_어연진_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("memberCheckid() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
	
	//비밀번호 암호화
    public String encryptSHA256(String value) throws NoSuchAlgorithmException{
        String encryptData ="";
        
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
 
        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
        }
        
        return encryptData;
    }

	public int insultMember(MemberDto dto) {
		int result = 0;
		String query = "insert into jsl_어연진_member\r\n" + 
				"(id, password, name, mobile_1, mobile_2, mobile_3, \r\n" + 
				"email_1, email_2, info, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getId()+"', '"+dto.getPassword()+"', '"+dto.getName()+"',\r\n" +
				" '"+dto.getMobile_1()+"', '"+dto.getMobile_2()+"', '"+dto.getMobile_3()+"', '"+dto.getEmail_1()+"', '"+dto.getEmail_2()+"', 'y', \r\n" + 
				"to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insultMember() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
