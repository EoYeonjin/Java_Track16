package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	//회원조회
	public ArrayList<MemberDto> getMemberList() {
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String query = "select * from e07_어연진_client";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String p_no = rs.getString("p_no");
				String p_name = rs.getString("p_name");
				String p_birth = rs.getString("p_birth");
				String p_gender = rs.getString("p_gender");
				String p_tel1 = rs.getString("p_tel1");
				String p_tel2 = rs.getString("p_tel2");
				String p_tel3 = rs.getString("p_tel3");
				String p_reg_date = rs.getString("p_reg_date");
				
				dtos.add(new MemberDto(p_no, p_name, p_birth, p_gender, p_tel1, p_tel2, p_tel3, p_reg_date));
			}
		} catch (SQLException e) {
			System.out.println("getMemberList error/n/r"+query);
			e.printStackTrace();
		}
		
		return dtos;
	}
}
