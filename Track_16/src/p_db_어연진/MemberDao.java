package p_db_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//전체조회
	public ArrayList<MemberDto> memberInfo() {
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String query = "select m.id, m.name, m.area, a.area_name, m.age\r\n" + 
				"				from member_어연진  m, area a\r\n" + 
				"				where m.area = a.area_code\r\n" + 
				"				order by to_number(id)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				String area_name = rs.getString("area_name");
				int age = rs.getInt("age");
				
				MemberDto dto = new MemberDto(id, name, area, area_name, age);
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("studentInfo() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//출력
	public void infoPrint(ArrayList<MemberDto> dtos) {
		if(dtos.size() == 0) {
			System.out.println("해당 사항 없음");
		}else {
			System.out.println("=============출력=============");
			System.out.println("ID\t이름\t지역\t나이");
			System.out.println("=============================");
			for(MemberDto dto: dtos) {
				System.out.println(dto.getId() +"\t"+
								dto.getName() +"\t"+ 
								dto.getArea_name()+ "[" +dto.getArea()+"]" + "\t"+
								dto.getAge());
				System.out.println("----------------------------");
			}
		}
		
		
	}

	//이름검색
	public ArrayList<MemberDto> getNameList(String name) {
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String query = "select m.id, m.name, m.area, a.area_name, m.age \r\n" + 
				"from member_어연진 m, area a \r\n" + 
				"where m.area = a.area_code \r\n" + 
				"and name like '%"+name+"%'"
						+ "order by to_number(id)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				name = rs.getString("name");
				String area = rs.getString("area");
				String area_name = rs.getString("area_name");
				int age = rs.getInt("age");
				
				MemberDto dto = new MemberDto(id, name, area, area_name, age);
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("getNameLisst() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//지역코드 조회
	public ArrayList<MemberDto> getAreaCode() {
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String query = "select * from area";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String area_name = rs.getString("area_name");
				String area_code = rs.getString("area_code");
				
				MemberDto dto = new MemberDto(area_name, area_code);
				dtos.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println("getAreaCode() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//지역코드 출력
	public void areaCodePrint(ArrayList<MemberDto> dtos) {
		System.out.println("지역 코드 :");
		for(MemberDto dto: dtos) {
			System.out.print(dto.getArea_name()+"["+dto.getArea_code()+"]"+ "\t");
		}
		System.out.println("\n");
		
	}
	
	//지역검색
	public ArrayList<MemberDto> getAreaList(String area) {
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String query = "select m.id, m.name, m.area, a.area_name, m.age \r\n" + 
				"from member_어연진 m, area a \r\n" + 
				"where m.area = a.area_code \r\n" + 
				"and a.area_code like '%"+area+"%'\r\n" + 
				"order by to_number(id)";
		
		try {
			con =DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				area = rs.getString("area");
				String area_name = rs.getString("area_name");
				int age = rs.getInt("age");
				
				MemberDto dto = new MemberDto(id, name, area, area_name, age);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getAreaList() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//등록
	public int saveMember(MemberDto dto) {
		int result = 0;
		String query = "insert into member_어연진\r\n" + 
				"(id, name, area, age)\r\n" + 
				"values\r\n" + 
				"('"+dto.getId()+"', '"+dto.getName()+"', '"+dto.getArea()+"', "+dto.getAge()+")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("saveMember() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정 조건 출력
	public MemberDto getMemberView(MemberDto dto) {
		MemberDto resultDto = null;
		String query = "select m.id, m.name, m.area, a.area_name, m.age\r\n" + 
				"from member_어연진 m, area a\r\n" + 
				"where m.area = a.area_code\r\n" + 
				"and m.id like '%"+dto.getId()+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				String area_name = rs.getString("area_name");
				int age = rs.getInt("age");
				
				resultDto = new MemberDto(id, name, area, area_name, age);
				
			}
		} catch (SQLException e) {
			System.out.println("getMemberView() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return resultDto;
	}
	
	//수정
	public int updateMember(MemberDto dto) {
		int result = 0;
		String query = "update member_어연진 \r\n" + 
				"set name='"+dto.getName()+"', area='"+dto.getArea()+"', age="+dto.getAge()+"\r\n" + 
				"where id='"+dto.getId()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateMember() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//삭제
	public int deleteMember(String id) {
		int result = 0;
		String query = "delete from member_어연진\r\n" + 
				"where id='"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteMember() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//지역코드 조회
	public MemberDto sameMemberCode(String area) {
		MemberDto dto = null;
		String query = "select area_code\r\n" + 
				"from area\r\n" + 
				"where area_code='"+area+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String area_code = rs.getString("area_code");
				
				dto = new MemberDto(area_code);
			}
		} catch (SQLException e) {
			System.out.println("deleteMember() method error"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}


	
	
	
	
	
}