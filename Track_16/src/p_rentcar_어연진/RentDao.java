package p_rentcar_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class RentDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//코드 자동 변환
	public String getMaxCode() {
		String r_code = "";
		String query = "select nvl (max(r_code), 'R000') as r_code\r\n" + 
				"from car_어연진_rent";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				r_code = rs.getString("r_code");
				String str = r_code.substring(1);
				int newNum = Integer.parseInt(str) + 1;
				
				DecimalFormat df = new DecimalFormat("R000");
				r_code = df.format(newNum);
			}
		} catch (SQLException e) {
			System.out.println("getMaxCode() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return r_code;
	}

	//대여 가능한 차량 목록 조회
	public ArrayList<CarDto> getRentList() {
		ArrayList<CarDto> dtos = new ArrayList<>();
		String query = "select v.v_num, v.v_brand, v.v_model, t.v_type, v.v_seater, v.price\r\n" + 
				"from car_어연진_vehicle v, car_어연진_type t\r\n" + 
				"where v.v_type = t.code\r\n" + 
				"and v.status like '%n%'\r\n" + 
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
				int price = rs.getInt("price");
				
				dtos.add(new CarDto(v_num, v_brand, v_model, v_type, v_seater, price));
			}
		} catch (SQLException e) {
			System.out.println("getRentList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//반납 가능한 차량 목록 조회
	public ArrayList<RentDto> getRetrunList() {
		ArrayList<RentDto> dtos = new ArrayList<RentDto>();
		String query = "select r.r_code, m.name, r.v_num, v.v_model, v.price, v.status,\r\n" + 
				"to_char(r.s_date, 'yyyy/mm/dd') as s_date,\r\n" + 
				"to_char(r.l_date, 'yyyy/mm/dd') as l_date\r\n" + 
				"from car_어연진_rent r, member_어연진 m, car_어연진_vehicle v\r\n" + 
				"where r.member_id = m.id\r\n" + 
				"and r.v_num = v.v_num\r\n" + 
				"and v.status like '%y%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String r_code = rs.getString("r_code");
				String name = rs.getString("name");
				String v_num = rs.getString("v_num");
				String v_model = rs.getString("v_model");
				String s_date = rs.getString("s_date");
				String l_date = rs.getString("l_date");
				String status = rs.getString("status");
				int price = rs.getInt("price");	
				
				dtos.add(new RentDto(r_code, name, v_num, v_model, s_date, l_date, status, price));
			}
		} catch (SQLException e) {
			System.out.println("getRetrunList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//현재날짜 비교
	public int compareDate(String s_date) {
		int compare = 0;
		LocalDate today = LocalDate.now();
		
		LocalDate date = LocalDate.parse(s_date, DateTimeFormatter.ISO_DATE);
		
		compare = today.compareTo(date);
		
		return compare;
	}
	
	//대여
	public int setRent(RentDto dto) {
		int result = 0;
		String insertquery = "insert into car_어연진_rent\r\n" + 
				"(r_code, member_id, v_num, s_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getR_code()+"', '"+dto.getMember_id()+"', '"+dto.getV_num()+"', '"+dto.getS_date()+"')";
		
		String updatequery = "update car_어연진_vehicle\r\n" + 
				"set status = 'y'\r\n" + 
				"where v_num like '%"+dto.getV_num()+"%'";
		
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertquery);
			result = ps.executeUpdate();
			
			if(result == 1) {
				ps = con.prepareStatement(updatequery);
				result = ps.executeUpdate();
			}
			
			if(result == 1) con.commit();
			else con.rollback();
			
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.out.println("insertRt() method error\n"+insertquery);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//반납
		public int setReturn(RentDto dto) {
			int result = 0;
			String v_num = "";
			String getV_numquery = "select v_num from car_어연진_rent\r\n" + 
					"where r_code like '%"+dto.getR_code()+"%'";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(getV_numquery);
				rs = ps.executeQuery();
				
				if(rs.next()) {
				 v_num = rs.getString("v_num");
				}
				
			} catch (SQLException e1) {
				System.out.println("setReturn() method error\n"+getV_numquery);
				e1.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			int total = dto.getPrice() * dto.getDays();
			
			String returnquery = "update car_어연진_rent\r\n" + 
					"set l_date = '"+dto.getL_date()+"', total = "+total+"\r\n" + 
					"where r_code like '%"+dto.getR_code()+"%'";
			
			String updatequery = "update car_어연진_vehicle\r\n" + 
					"set status = 'y'\r\n" + 
					"where v_num like '%"+v_num+"%'";
			
			try {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				ps = con.prepareStatement(returnquery);
				result = ps.executeUpdate();
				
				if(result == 1) {
					ps = con.prepareStatement(updatequery);
					result = ps.executeUpdate();
				}
				
				if(result == 1) con.commit();
				else con.rollback();
				
			} catch (SQLException e) {
				System.out.println("setReturn() method error\n"+returnquery);
				System.out.println("setReturn() method error\n"+updatequery);
				e.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}

	//날짜 차이 
	public int getSubDate(String s_date, String l_date) {
		Date st = null;
		Date lt = null;
		try {
			st = new SimpleDateFormat("yyyy/MM/dd").parse(s_date);
			lt = new SimpleDateFormat("yyyy-MM-dd").parse(l_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long diffSec = (lt.getTime() - st.getTime()) / 1000;
		long diffDays = diffSec / (24*60*60);
		
		
		int days = Long.valueOf(diffDays).intValue();
		
		return days;
	}
	
	//가격조회
	public int getPrice(String r_code) {
		int price = 0;
		String query = "select v.price \r\n" + 
				"from car_어연진_rent r, car_어연진_vehicle v\r\n" + 
				"where r.v_num = v.v_num\r\n" + 
				"and r.r_code like '%"+r_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("price");
			}
		} catch (SQLException e) {
			System.out.println("getPrice() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return price;
	}
	
	//조회
	public ArrayList<RentDto> getListAll() {
		ArrayList<RentDto> dtos = new ArrayList<RentDto>();
		String query = "select r.r_code, m.name, r.v_num, v.v_model, v.price, v.status,\r\n" + 
				"to_char(r.s_date, 'yyyy/mm/dd') as s_date,\r\n" + 
				"to_char(r.l_date, 'yyyy/mm/dd') as l_date\r\n" + 
				"from car_어연진_rent r, member_어연진 m, car_어연진_vehicle v\r\n" + 
				"where r.member_id = m.id\r\n" + 
				"and r.v_num = v.v_num\r\n" + 
				"order by r.r_code";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String r_code = rs.getString("r_code");
				String name = rs.getString("name");
				String v_num = rs.getString("v_num");
				String v_model = rs.getString("v_model");
				String s_date = rs.getString("s_date");
				String l_date = rs.getString("l_date");
				String status = rs.getString("status");
				int price = rs.getInt("price");	
				
				dtos.add(new RentDto(r_code, name, v_num, v_model, s_date, l_date, status, price));
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
	public void infoPrint(ArrayList<RentDto> dtos) {
		if(dtos.size() == 0) System.out.println("해당 사항 없음");
		else {
			System.out.println("=======================================출력=======================================");
			System.out.println("대여코드\t차량번호\t\t모델명\t회원명\t대여일\t\t반납일\t\t총 금액");
			System.out.println("=================================================================================");
			for(RentDto dto: dtos) {
				if(dto.getStatus().equals("n")) {
					System.out.println(dto.getR_code() + "\t" +
							dto.getV_num() +"\t" +
							dto.getV_model() +"\t" +
							dto.getName() +"\t" +
							dto.getS_date() +"\t");	
				}else {
					int s_date = Integer.parseInt(dto.getS_date());
					int l_date = Integer.parseInt(dto.getL_date());
					int total = (l_date - s_date) * dto.getPrice();
					
					DecimalFormat df = new DecimalFormat("###,###");
					System.out.println(dto.getR_code() + "\t" +
							dto.getV_num() +"\t" +
							dto.getV_model() +"\t" +
							dto.getName() +"\t" +
							dto.getS_date() +"\t" +
							dto.getL_date() +"\t" +
							df.format(total));	
				}
				
				System.out.println("---------------------------------------------------------------------------------");
			}
		}
	}

	//대여 차량 출력
	public void infoRRPrint(ArrayList<CarDto> dtos) {
		if(dtos.size() == 0) System.out.println("해당 사항 없음");
		else {
			System.out.println("============================출력============================");
			System.out.println("차량번호\t\t제조사\t모델명\t차량분류\t탑승인원\t렌트비용");
			System.out.println("===========================================================");
			for(CarDto dto: dtos) {
				DecimalFormat df = new DecimalFormat("###,###");
				System.out.println(dto.getV_num() + "\t" +
									dto.getV_brand() +"\t" +
									dto.getV_model() +"\t" +
									dto.getV_type() +"\t" +
									dto.getV_seater() +"인승\t" +
									df.format(dto.getPrice()) + "원/1d\t");
				System.out.println("-----------------------------------------------------------");
			}
		}
		
	}
	
	//특정 코드의 대여차량 조회
	public ArrayList<RentDto> getOneList(String r_code) {
		ArrayList<RentDto> dtos = new ArrayList<RentDto>();
		String query = "select r.r_code, m.name, r.v_num, v.v_model, v.price, v.status,\r\n" + 
				"to_char(r.s_date, 'yyyy/mm/dd') as s_date,\r\n" + 
				"to_char(r.l_date, 'yyyy/mm/dd') as l_date\r\n" + 
				"from car_어연진_rent r, member_어연진 m, car_어연진_vehicle v\r\n" + 
				"where r.member_id = m.id\r\n" + 
				"and r.v_num = v.v_num\r\n" + 
				"and r.r_code like '%"+r_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				r_code = rs.getString("r_code");
				String name = rs.getString("name");
				String v_num = rs.getString("v_num");
				String v_model = rs.getString("v_model");
				String s_date = rs.getString("s_date");
				String l_date = rs.getString("l_date");
				String status = rs.getString("status");
				int price = rs.getInt("price");	
				
				dtos.add(new RentDto(r_code, name, v_num, v_model, s_date, l_date, status, price));
			}
		} catch (SQLException e) {
			System.out.println("getListAll() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//삭제
	public int deleteRent(String r_code) {
		int result = 0;
		String query = "delete from car_어연진_rent\r\n" + 
				"where r_code like '%"+r_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteRent() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	

	

	

	
	

	
	
}