package p_shop_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//전체 조회
	public ArrayList<ProductDto> getListAll() {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		String query = "select * from SHOP_어연진_PRODUCT\r\n" + 
				"order by p_code";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String p_code = rs.getString("p_code");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				int count = rs.getInt("count");
				int price = rs.getInt("price");
				
				dtos.add(new ProductDto(p_code, p_name, p_size, count, price));
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
		public static void infoPrint(ArrayList<ProductDto> dtos) {
			System.out.println("===================출력==================");
			System.out.println("상품 코드\t상품명\t사이즈\t수량\t가격");
			System.out.println("=======================================");
			for(ProductDto dto: dtos) {
				System.out.println(dto.getP_code() + "\t" +
									dto.getP_name() + "\t" +
									dto.getP_size() + "\t" +
									dto.getCount() + "개\t" +
									dto.getPrice() + "원");
				System.out.println("----------------------------------------");
			}
		}
	
	//코드 자동변환	
	public String getMaxCode() {
		String newCode = "";
		String query = "select nvl (max(p_code), 'P000') as p_code\r\n" + 
						"from shop_어연진_product";
		//코드 자동 변환
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				newCode = rs.getString("p_code");
				String str = newCode.substring(1);
				int newNum = Integer.parseInt(str) + 1;
				
				DecimalFormat df = new DecimalFormat("P000");
				newCode = df.format(newNum);
				
			/*	if(newNum < 10) newCode = "P00" + Integer.toString(newNum);
				else if(newNum < 100) newCode = "P0" + Integer.toString(newNum);
				else newCode = "P" + Integer.toString(newNum);*/
			}
		} catch (SQLException e) {
			System.out.println("getMaxCode() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return newCode;
	}
	
	//등록
	public int insertPd(ProductDto dto) {
		int result = 0;
		String query = "insert into shop_어연진_product\r\n" + 
				"(p_code, p_name, p_size, count, price)\r\n" + 
				"values\r\n" + 
				"('"+dto.getP_code()+"', '"+dto.getP_name()+"', '"+dto.getP_size()+"', "+dto.getCount()+", "+dto.getPrice()+")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("insertPd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정조건의 상품코드 조회
	public ArrayList<ProductDto> getOneList(String p_code) {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		String query = "select * \r\n" + 
				"from shop_어연진_product\r\n" + 
				"where p_code like '%"+p_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				p_code = rs.getString("p_code");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				int count = rs.getInt("count");
				int price = rs.getInt("price");
				
				dtos.add(new ProductDto(p_code, p_name, p_size, count, price));
			}
			
		} catch (SQLException e) {
			System.out.println("getOneList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//수정
	public int updatePd(ProductDto dto) {
		int result = 0;
		String query = "update shop_어연진_product\r\n" + 
				"set p_name = '"+dto.getP_name()+"', p_size = '"+dto.getP_size()+"', count = "+dto.getCount()+", price = "+dto.getPrice()+"\r\n" + 
				"where p_code = '"+dto.getP_code()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updatePd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//삭제
	public int deletePd(String p_code) {
		int result = 0;
		String query = "delete from shop_어연진_product\r\n" + 
				"where p_code = '"+p_code+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deletePd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//동일한 상품명과 상품의 사이즈 유무 확인
	public ProductDto existSamePd(String p_name, String p_size) {
		ProductDto resultDto = null;
		String query = "select p_name, p_size\r\n" + 
				"from shop_어연진_product\r\n" + 
				"where p_name like '%"+p_name+"%'\r\n" + 
				"and p_size like '%"+p_size+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				p_name = rs.getString("p_name");
				p_size = rs.getString("p_size");
				
				resultDto = new ProductDto(p_name, p_size);
			}
		} catch (SQLException e) {
			System.out.println("existSamePd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return resultDto;
	}

	//재고 비교
	public int getCountList(String p_code) {
		int count = 0;
		String query = "select count\r\n" + 
				"from shop_어연진_product\r\n" + 
				"where p_code like '%"+p_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
				
		} catch (SQLException e) {
			System.out.println("getCountList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return count;
	}

	//수정시 상품수량 변경
	public int updateCountPd(int total, String p_code) {
		int result = 0;
		
		String query = "update shop_어연진_product\r\n" + 
				"set count = "+total+"\r\n" + 
				"where p_code like '%"+p_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateCountPd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	

}