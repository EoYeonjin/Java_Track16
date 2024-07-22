package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.StudentDto;

public class StudentDao {
	Connection con= null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//조회
	public ArrayList<StudentDto> getStudentList(String select, String search, String syear, String sclass){
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();
		String query = "select syear, sclass, no, name\r\n" + 
				"from student_어연진\r\n" + 
				"where syear like '%"+syear+"%'\r\n" + 
				"and sclass like '%"+sclass+"%'\r\n" + 
				"and "+select+" like '%"+search+"%'" +
				"order by syear, sclass, no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				syear = rs.getString("syear");
				sclass = rs.getString("sclass");
				String no = rs.getString("no");
				String name = rs.getString("name");
				
				dtos.add(new StudentDto(syear, sclass, no, name));
			}
		} catch (SQLException e) {
			System.out.println("getStudentList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dtos;
	}
	
	//상세조회
	public StudentDto getStudentView(String syear, String sclass, String no) {
		StudentDto dto = null;
		String query = "select *\r\n" + 
				"from student_어연진\r\n" + 
				"where syear like '"+syear+"'\r\n" + 
				"and sclass like '"+sclass+"'\r\n" + 
				"and no like '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				int sum = kor + eng + mat;
				double ave0 = sum / 3.0;
				double ave = (Math.floor(ave0*10.0) / 10.0);
				
				dto = new StudentDto(syear, sclass, no, name, kor, eng, mat, sum, ave);
				
			}
		} catch (SQLException e) {
			System.out.println("getStudentView() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//등록
	public int insertStd(StudentDto dto){
		int result = 0;
		String query = "insert into student_어연진\r\n" + 
				"(syear, sclass, no, name, kor, eng, mat)\r\n" + 
				"values\r\n" + 
				"('"+dto.getSyear()+"', '"+dto.getSclass()+"', '"+dto.getNo()+"', '"+dto.getName()+"', "+dto.getKor()+", "+dto.getEng()+", "+dto.getMat()+")";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertStd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//수정
	public int updateStd(String syear, String sclass, String no, String name, int kor, int eng, int mat) {
		int result = 0;
		String query = "update student_어연진 \r\n" + 
				"set name='"+name+"', kor="+kor+", eng="+eng+", mat="+mat+"\r\n" + 
				"where syear like '%"+syear+"%' and sclass like '%"+sclass+"%' and no like '%"+no+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateStd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int deleteStd(String syear, String sclass, String no) {
		int result = 0;
		String query = "delete from student_어연진\r\n" + 
				"where syear like '%"+syear+"%' and sclass like '%"+sclass+"%' and no like '%"+no+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteStd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
