package p_shop_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SaleDao {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	//출력
	public void infoPrint(ArrayList<SaleDto> dtos) {
		if(dtos.size() == 0) System.out.println("해당 사항 없음");
		else {
			System.out.println("========================================출력=======================================");
			System.out.println("주문번호\t제품명[제품코드]\t회원명[회원코드]\t결제방식\t판매 수량\t총 금액\t\t판매 일자");
			System.out.println("==================================================================================");
			for(SaleDto dto: dtos) {
				int total = dto.getBuycount() * dto.getPrice();
				DecimalFormat df = new DecimalFormat("###,###");
				System.out.println(dto.getOrdernum() + "\t" +
									dto.getP_name() +"["+dto.getP_code()+ "]\t" +
									dto.getName() +"["+ dto.getMember_id() + "]\t" +
									dto.getPayment() + "\t" +
									dto.getBuycount() + "\t" +
									df.format(total) + "원\t" +
									dto.getDate() + "\t");
				System.out.println("----------------------------------------------------------------------------------");
			}
		}
	}
	
	//전체 조회
	public ArrayList<SaleDto> getListAll() {
		ArrayList<SaleDto> dtos = new ArrayList<SaleDto>();
		String query = "select s.ordernum, s.p_code, p.p_name, s.member_id, m.name, s.buycount, p.price,\r\n" + 
				"decode(s.payment, '1', '현금', '2', '카드', '외상') as payment,\r\n" + 
				"to_char(s.buy_date, 'yyyy/mm/dd') as buy_date\r\n" + 
				"from shop_어연진_sale s, shop_어연진_product p, member_어연진 m\r\n" + 
				"where s.p_code = p.p_code\r\n" + 
				"and s.member_id = m.id\r\n" + 
				"order by s.buy_date desc, s.ordernum desc";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String ordernum = rs.getString("ordernum");
				String p_name = rs.getString("p_name");
				String p_code = rs.getString("p_code");
				String name = rs.getString("name");
				String member_id = rs.getString("member_id");
				String payment = rs.getString("payment");
				String date = rs.getString("buy_date");
				int price = rs.getInt("price");
				int buycount = rs.getInt("buycount");
				
				dtos.add(new SaleDto(ordernum, p_name, p_code, name, member_id, payment, date, buycount, price));
				
			}
		} catch (SQLException e) {
			System.out.println("getListAll() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//제품별 판매 이력
	public ArrayList<SaleDto> getPdList(String p_code) {
		ArrayList<SaleDto> dtos = new ArrayList<SaleDto>();
		String query = "select s.ordernum, s.p_code, p.p_name, s.member_id, m.name, s.buycount, p.price,\r\n" + 
				"decode(s.payment, '1', '현금', '2', '카드', '외상') as payment,\r\n" + 
				"to_char(s.buy_date, 'yyyy/mm/dd') as buy_date\r\n" + 
				"from shop_어연진_sale s, shop_어연진_product p, member_어연진 m\r\n" + 
				"where s.p_code = p.p_code\r\n" + 
				"and s.member_id = m.id\r\n" + 
				"and s.p_code like '%"+p_code+"%'\r\n"+ 
				"order by s.buy_date desc, s.ordernum desc";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String ordernum = rs.getString("ordernum");
				String p_name = rs.getString("p_name");
				p_code = rs.getString("p_code");
				String name = rs.getString("name");
				String member_id = rs.getString("member_id");
				String payment = rs.getString("payment");
				String date = rs.getString("buy_date");
				int price = rs.getInt("price");
				int buycount = rs.getInt("buycount");
				
				dtos.add(new SaleDto(ordernum, p_name, p_code, name, member_id, payment, date, buycount, price));
			}
		} catch (SQLException e) {
			System.out.println("getPdList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//회원별 구매이력
	public ArrayList<SaleDto> getMbList(String member_id) {
		ArrayList<SaleDto> dtos = new ArrayList<SaleDto>();
		String query = "select s.ordernum, s.p_code, p.p_name, s.member_id, m.name, s.buycount, p.price,\r\n" + 
				"decode(s.payment, '1', '현금', '2', '카드', '외상') as payment,\r\n" + 
				"to_char(s.buy_date, 'yyyy/mm/dd') as buy_date\r\n" + 
				"from shop_어연진_sale s, shop_어연진_product p, member_어연진 m\r\n" + 
				"where s.p_code = p.p_code\r\n" + 
				"and s.member_id = m.id\r\n" + 
				"and m.id like '%"+member_id+"%'\r\n" + 
				"order by s.buy_date desc, s.ordernum desc";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String ordernum = rs.getString("ordernum");
				String p_name = rs.getString("p_name");
				String p_code = rs.getString("p_code");
				String name = rs.getString("name");
				member_id = rs.getString("member_id");
				String payment = rs.getString("payment");
				String date = rs.getString("buy_date");
				int price = rs.getInt("price");
				int buycount = rs.getInt("buycount");
				
				dtos.add(new SaleDto(ordernum, p_name, p_code, name, member_id, payment, date, buycount, price));
			}
		} catch (SQLException e) {
			System.out.println("getMbList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//코드 자동변환
	public String getMaxCode() {
		String ordernum = "";
		String query = "select nvl (max(ordernum), 'B000') as ordernum\r\n" + 
				"from shop_어연진_sale";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ordernum = rs.getString("ordernum");
				String str = ordernum.substring(1);
				int newNum = Integer.parseInt(str) + 1;
				
				DecimalFormat df = new DecimalFormat("B000");
				ordernum = df.format(newNum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return ordernum;
	}

	//등록
	public int insertSale(SaleDto dto) {
		int result = 0;
		String query = "insert into shop_어연진_sale\r\n" + 
				"(ordernum, p_code, member_id, buycount, payment, buy_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getOrdernum()+"', '"+dto.getP_code()+"', '"+dto.getMember_id()+"', "+dto.getBuycount()+", '"+dto.getPayment()+"', '"+dto.getDate()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertSale() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정조건의 판매코드 조회
	public ArrayList<SaleDto> getOneList(String ordernum) {
		ArrayList<SaleDto> dtos = new ArrayList<SaleDto>();
		String query = "select s.ordernum, s.p_code, p.p_name, s.member_id, m.name, s.buycount, p.price,\r\n" + 
				"decode(s.payment, '1', '현금', '2', '카드') as payment,\r\n" + 
				"to_char(s.buy_date, 'yyyy/mm/dd') as buy_date\r\n" + 
				"from shop_어연진_sale s, shop_어연진_product p, member_어연진 m\r\n" + 
				"where s.p_code = p.p_code\r\n" + 
				"and s.member_id = m.id\r\n" + 
				"and s.ordernum like '%"+ordernum+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ordernum = rs.getString("ordernum");
				String p_name = rs.getString("p_name");
				String p_code = rs.getString("p_code");
				String name = rs.getString("name");
				String member_id = rs.getString("member_id");
				String payment = rs.getString("payment");
				String date = rs.getString("buy_date");
				int price = rs.getInt("price");
				int buycount = rs.getInt("buycount");
				
				dtos.add(new SaleDto(ordernum, p_name, p_code, name, member_id, payment, date, buycount, price));
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
	public int updateSale(SaleDto dto) {
		int result = 0;
		String query = "update shop_어연진_sale\r\n" + 
				"set p_code = '"+dto.getP_code()+"', member_id = '"+dto.getMember_id()+"', buycount = "+dto.getBuycount()+", payment = '"+dto.getPayment()+"', buy_date = '"+dto.getDate()+"'\r\n" + 
				"where ordernum like '%"+dto.getOrdernum()+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateSale() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//삭제
	public int deleteSale(String ordernum) {
		int result = 0;
		String query = "delete from shop_어연진_sale\r\n" + 
				"where ordernum like '%"+ordernum+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteSale() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정 상품 판매이력 존재여부
	public SaleDto existPd(String p_code) {
		SaleDto resultDto = null;
		String query = "select p_code\r\n" + 
				"from shop_어연진_sale\r\n" + 
				"where p_code like '%"+p_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String code = rs.getString("p_code");
				
				resultDto = new SaleDto(code);
			}
		} catch (SQLException e) {
			System.out.println("existPd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return resultDto;
	}

	//특정 회원 구매이력 존재여부
		public SaleDto existMb(String id) {
			SaleDto resultDto = null;
			String query = "select member_id\r\n" + 
					"from shop_어연진_sale\r\n" + 
					"where member_id like '%"+id+"%'";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()) {
					String code = rs.getString("member_id");
					
					resultDto = new SaleDto(code);
				}
			} catch (SQLException e) {
				System.out.println("existMb() method error"+query);
				e.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return resultDto;
		}

		//판매등록된 판매수량 조회
		public int getCountList(String ordernum) {
			int buycount = 0;
			String query = "select buycount\r\n" + 
					"from shop_어연진_sale\r\n" + 
					"where ordernum like '%"+ordernum+"%'";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					buycount = rs.getInt("buycount");
				}
			} catch (SQLException e) {
				System.out.println("getCountList() method error"+query);
				e.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return buycount;
		}
	
}