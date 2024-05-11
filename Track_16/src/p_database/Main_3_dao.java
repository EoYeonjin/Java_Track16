package p_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main_3_dao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//��ü�˻�
	public ArrayList<Main_3_dto> studentInfo() {
		ArrayList<Main_3_dto> dtos = new ArrayList<Main_3_dto>();
		
		String query = "select * from student\r\n" + 
				"order by to_number(syear),\r\n" + 
				"         to_number(sclass),\r\n" + 
				"         to_number(no)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String syear = rs.getString("syear");
				String sclass = rs.getString("sclass");
				String no = rs.getString("no");
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("MemberAll() method error");
			System.out.println(query);
			e.printStackTrace();
			
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		
		return dtos;
	}
	
	//�г�˻�
	public ArrayList<Main_3_dto> getSyearList(String syear) {
		ArrayList<Main_3_dto> dtos = new ArrayList<Main_3_dto>();
		String query = "select syear, sclass, no, name, kor, eng, mat\r\n" + 
				"from student\r\n" + 
				"where syear = '"+syear+"'"
						+ "order by to_number(syear),\r\n" + 
						"         to_number(sclass),\r\n" + 
						"         to_number(no)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				syear = rs.getString("syear");
				String sclass = rs.getString("sclass");
				String no = rs.getString("no");
				String name = rs.getString("name");
				int	kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			
			System.out.println("getSyearList() method error");
			System.out.println(query);
			e.printStackTrace();
			
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}

	//�ݰ˻�
	public ArrayList<Main_3_dto> getSclassList(String sclass) {
		ArrayList<Main_3_dto> dtos = new ArrayList<Main_3_dto>();
		String query = "select syear, sclass, no, name, kor, eng, mat\r\n" + 
				"from student\r\n" + 
				"where sclass = '"+sclass+"'"
						+ "order by to_number(syear),\r\n" + 
						"         to_number(sclass),\r\n" + 
						"         to_number(no)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String syear = rs.getString("syear");
				sclass = rs.getString("sclass");
				String no = rs.getString("no");
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getSclassList() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
	
	//�̸��˻�
	public ArrayList<Main_3_dto> getNameList(String name) {
		ArrayList<Main_3_dto> dtos = new ArrayList<Main_3_dto>();
		String query = "select syear, sclass, no, name, kor, eng, mat\r\n" + 
				"from student\r\n" + 
				"where name like '%"+name+"%'"
						+ "order by to_number(syear),\r\n" + 
						"         to_number(sclass),\r\n" + 
						"         to_number(no)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				String syear = rs.getString("syear");
				String sclass = rs.getString("sclass");
				String no = rs.getString("no");
				name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getNameList() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}

	//������
	public void infoPrint(ArrayList<Main_3_dto> dtos) {
		System.out.println("=========================���========================");
		System.out.println("�г�\t��\t��ȣ\t�̸�\t����\t����\t����");
		System.out.println("====================================================");
		
		if(dtos.size() == 0) System.out.println("�ش� ���� ����");
		for(Main_3_dto dto: dtos) {
			System.out.println("\n"+dto.getSyear() + "�г�" + "\t" +
					dto.getSclass() + "��" + "\t" +
					dto.getNo() + "��" +"\t" +
					dto.getName() + "\t" +
					dto.getKor() + "��" +"\t" +
					dto.getEng() + "��" +"\t" +
					dto.getMat() + "��" +"\n");
	System.out.println("----------------------------------------------------");
		}
		
	}
	
	//�� ���
	public int saveStudent(Main_3_dto dto) {
		int result = 0;
		String query = "insert into student\r\n" + 
				"		(syear, sclass, no, name, kor, eng, mat)\r\n" + 
				"		values\r\n" + 
				"		('"+dto.getSyear()+"', '"+dto.getSclass()+"', '"+dto.getNo()+"', '"+dto.getName()+"', "+dto.getKor()+", "+dto.getEng()+", "+dto.getMat()+")"; 
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("saveStudent() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		
		return result;
		
	}

	//����ȸ
	public Main_3_dto getStudentView(Main_3_dto dto) {
		Main_3_dto resultDto = null;
		String query = "select * from student\r\n" + 
				"where syear='"+dto.getSyear()+"' and sclass='"+dto.getSclass()+"' and no='"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String syear = rs.getString("syear");
				String sclass = rs.getString("sclass");
				String no = rs.getString("no");
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				resultDto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				
			}
		} catch (SQLException e) {
			System.out.println("getStudentView() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return resultDto;
	}

	//���� ����
	public int updateStudent(Main_3_dto dto) {
		int result = 0;
		String query = "update student \r\n" + 
				"set name = '"+dto.getName()+"', kor="+dto.getKor()+", eng="+dto.getEng()+", mat ="+dto.getMat()+" \r\n" + 
				"where syear='"+dto.getSyear()+"' and sclass='"+dto.getSclass()+"' and no='"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateStudent() method error"+query);
			e.printStackTrace();
			
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//����
	public int deleteStudent(Main_3_dto dto) {
		int result = 0;
		String query = "delete from student\r\n" + 
				"where syear='"+dto.getSyear()+"'\r\n" + 
				"and sclass='"+dto.getSclass()+"'\r\n" + 
				"and no='"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteStudent() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}

		return result;
	}
	
	
	

	

	 
	
	
	
	
}