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

    //등록
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

	//로그인 시 조회
	public MemberDto getLogName(String id, String password) {
		MemberDto dto = null;
		String query = "select name, to_char(last_login_date, 'yyyy-MM-ss') as last_login_date\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id='"+id+"' and password = '"+password+"' and exit_date is null";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String last_login_date = rs.getString("last_login_date");
				
				dto = new MemberDto(name, last_login_date);
			}
		} catch (SQLException e) {
			System.out.println("getLogName() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}

	//로그인 시 최종 로그인 시간 업데이트
	public int setMemberLoginTime(String id, String todayTime) {
		int result = 0;
		String query = "update jsl_어연진_member\r\n" + 
				"set last_login_date=to_date('"+todayTime+"', 'yyyy-MM-dd hh24:mi:ss')\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("setMemberLoginTime() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//회원 상세 조회
	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String query = "select name, mobile_1, mobile_2, mobile_3, email_1, email_2, info, \r\n" + 
				"to_char(reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, \r\n" + 
				"to_char(last_login_date, 'yyyy-MM-dd hh24:mi:ss') as last_login_date\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String email_1 = rs.getString("email_1");
				String email_2 = rs.getString("email_2");
				String info = rs.getString("info");
				String reg_date = rs.getString("reg_date");
				String last_login_date = rs.getString("last_login_date");
				String exit_date = "";
				
				dto = new MemberDto(id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, reg_date, last_login_date, exit_date, info);
			}
		} catch (SQLException e) {
			System.out.println("getMemberInfo() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}

	//비밀번호 조회
	public int memberCheckPassword(String id, String pw) {
		int count = 0;
		String query = "select count(*) as count from jsl_어연진_member\r\n" + 
				"where id = '"+id+"' and password = '"+pw+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("memberCheckPassword() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}

	//수정
	public int updateMember(MemberDto dto) {
		int result = 0;
		String query = "update jsl_어연진_member\r\n" + 
				"set name='"+dto.getName()+"', mobile_1='"+dto.getMobile_1()+"',mobile_2='"+dto.getMobile_2()+"', mobile_3='"+dto.getMobile_3()+"', \r\n" + 
				"email_1='"+dto.getEmail_1()+"', email_2='"+dto.getEmail_2()+"', info='"+dto.getInfo()+"'\r\n" + 
				"where id = '"+dto.getId()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateMember() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//탈퇴 날짜 업데이트
	public int updateExitDate(String id, String exit_date) {
		int result = 0;
		String query = "update jsl_어연진_member\r\n" + 
				"set exit_date=to_date('"+exit_date+"', 'yyyy-MM-dd hh24:mi:ss')\r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateExitDate() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//비밀번호 조회시 이메일 조회
	public String getEmail(String id, String name, String mobile_1, String mobile_2, String mobile_3) {
		String toEmail = "";
		String query = "select email_1 ||'@'|| email_2 as email\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id = '"+id+"' and name = '"+name+"'\r\n" + 
				"and mobile_1 = '"+mobile_1+"' and mobile_2 = '"+mobile_2+"' and mobile_3 = '"+mobile_3+"'" +
				"and exit_date is null\r\n";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				toEmail = rs.getString("email");
			}
		} catch (SQLException e) {
			System.out.println("updateExitDate() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return toEmail;
	}
}
