package p_rentcar_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import p_shop_어연진.SaleDto;

public class TypeDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//차종 조회
	public ArrayList<TypeDto> getListAll() {
		ArrayList<TypeDto> dtos = new ArrayList<TypeDto>();
		String query = "select * from car_어연진_type";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String code = rs.getString("code");
				String v_type = rs.getString("v_type");
				
				dtos.add(new TypeDto(code, v_type));
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
	public void infoPrint(ArrayList<TypeDto> dtos) {
		if(dtos.size() == 0) System.out.println("해당 사항 없음");
		else {
			System.out.println("======================출력======================");
			System.out.println("차종[코드]");
			System.out.println("===============================================");
			for(TypeDto dto: dtos) {
				System.out.print(dto.getV_type() + "[" +
									dto.getCode() +"]\t");
				
			}
			System.out.println("\n-----------------------------------------------");
		}
	}
	
	
}