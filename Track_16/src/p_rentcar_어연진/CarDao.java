package p_rentcar_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//전체 조회
	public ArrayList<CarDto> getListAll() {
		ArrayList<CarDto> dtos = new ArrayList<CarDto>();
		String query = "select v.v_num, v.v_brand, v.v_model, t.v_type, v.v_seater, v.price, \r\n" + 
				"decode(v.status, 'n', '입고', 'y', '출고') as status\r\n" + 
				"from car_어연진_vehicle v, car_어연진_type t\r\n" + 
				"where v.v_type = t.code\r\n" + 
				"order by v.v_num";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String v_num = rs.getString("v_num");
				String v_brand = rs.getString("v_brand");
				String v_model = rs.getString("v_model");
				String v_type = rs.getString("v_type");
				String v_seater = rs.getString("v_seater");
				String status = rs.getString("status");
				int price = rs.getInt("price");
				
				dtos.add(new CarDto(v_num, v_brand, v_model, v_type, v_seater, status, price));
			}
		} catch (SQLException e) {
			System.out.println("getListAll() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//출력
	public void infoPrint(ArrayList<CarDto> dtos) {
		if(dtos.size() == 0) System.out.println("해당 사항 없음");
		else {
			System.out.println("================================출력================================");
			System.out.println("차량번호\t\t제조사\t모델명\t차량분류\t탑승인원\t렌트비용\t\t상태");
			System.out.println("===================================================================");
			for(CarDto dto: dtos) {
				DecimalFormat df = new DecimalFormat("###,###");
				System.out.println(dto.getV_num() + "\t" +
									dto.getV_brand() +"\t" +
									dto.getV_model() +"\t" +
									dto.getV_type() +"\t" +
									dto.getV_seater() +"인승\t" +
									df.format(dto.getPrice()) + "원/1d\t" +
									dto.getStatus());
				System.out.println("-------------------------------------------------------------------");
			}
		}
	}

	//등록
	public int insertVh(CarDto dto) {
		int result = 0;
		String query = "insert into car_어연진_vehicle\r\n" + 
				"(v_num, v_brand, v_model, v_type, v_seater, price, status)\r\n" + 
				"values\r\n" + 
				"('"+dto.getV_num()+"', '"+dto.getV_brand()+"', '"+dto.getV_model()+"', '"+dto.getV_type()+"', '"+dto.getV_seater()+"', "+dto.getPrice()+", '"+dto.getStatus()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("insertVh() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정 조건 차량 조회
	public ArrayList<CarDto> getOneList(String v_num) {
		ArrayList<CarDto> dtos = new ArrayList<CarDto>();
		String query = "select v.v_num, v.v_brand, v.v_model, t.v_type, v.v_seater, v.price\r\n" + 
				"from car_어연진_vehicle v, car_어연진_type t\r\n" + 
				"where v.v_type = t.code\r\n" + 
				"and v.v_num like '%"+v_num+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				v_num = rs.getString("v_num");
				String v_brand = rs.getString("v_brand");
				String v_model = rs.getString("v_model");
				String v_type = rs.getString("v_type");
				String v_seater = rs.getString("v_seater");
				int price = rs.getInt("price");
				
				dtos.add(new CarDto(v_num, v_brand, v_model, v_type, v_seater, price));
			}
		} catch (SQLException e) {
			System.out.println("getOneList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//전체 수정
	public int updateVh(CarDto dto) {
		int result = 0;
		String query = "update car_어연진_vehicle\r\n" + 
				"set v_brand = '"+dto.getV_brand()+"', v_model = '"+dto.getV_model()+"', v_type = '"+dto.getV_type()+"', v_seater = '"+dto.getV_seater()+"', price = "+dto.getPrice()+"\r\n" + 
				"where v_num like '%"+dto.getV_num()+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateVh() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//렌탈비용 수정
	public int updatePriceVh(CarDto dto) {
		int result = 0;
		String query = "update car_어연진_vehicle\r\n" + 
				"set price = "+dto.getPrice()+"\r\n" + 
				"where v_num like '%"+dto.getV_num()+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updatePriceVh() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//삭제
	public int deleteVh(String v_num) {
		int result = 0;
		String query = "delete from car_어연진_vehicle\r\n" + 
				"where v_num like '%"+v_num+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteVh() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
}