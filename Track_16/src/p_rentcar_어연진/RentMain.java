package p_rentcar_어연진;

import java.util.ArrayList;
import java.util.Scanner;

public class RentMain {

	public static void main(String[] args) {
		CarDao carDao = new CarDao();
		MemberDao memberDao = new MemberDao();
		RentDao rentDao = new RentDao();
		TypeDao typeDao = new TypeDao();
		Scanner sc = new Scanner(System.in);
		
		String gubun = "";
		
		do {
			System.out.println("차량관리: V, 회원관리: M, 대여관리: R, 종료: Q?");
			gubun = sc.next();
			
			//상품관리
			if(gubun.equalsIgnoreCase("v") || gubun.equals("ㅍ")) {
				System.out.println("[차량관리] 조회: S, 등록: I, 수정: U, 삭제: D?");
				String search = sc.next();
				
				//조회
				if(search.equalsIgnoreCase("s") || search.equals("ㄴ")) {
					ArrayList<CarDto> dtos = carDao.getListAll();
					carDao.infoPrint(dtos);
					
				//차량등록	
				}else if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					//자릿수 초과 체크
					String v_num = "";
					do {
						System.out.println("등록할 차량번호를 입력하세요. ex)oo가-oooo");
						v_num = sc.next();
						if(v_num.length() > 8) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
						else if(v_num.length() != 8) System.out.println("잘못 입력하셨습니다.");
					}while(v_num.length() != 8);
					
					//차량 중복 체크
					ArrayList<CarDto> dtos1 = carDao.getOneList(v_num);
					if(dtos1.size() != 0) System.out.println("해당 차량번호는 존재하여 등록이 불가능 합니다.");
					else {
						//자릿수 초과 체크
						String v_brand = "";
						do {
							System.out.println("등록할 차량의 제조사을 입력하세요. (6글자 이내)");
							v_brand = sc.next();
							if(v_brand.length() > 6) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
						}while(v_brand.length() > 6);
						
						//자릿수 초과 체크
						String v_model = "";
						do {
							System.out.println("등록할 차량의 모델명을 입력하세요. (5글자 이내)");
							v_model = sc.next();
							if(v_model.length() > 5) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
						}while(v_model.length() > 5);
						
						ArrayList<TypeDto> dtos = typeDao.getListAll();
						typeDao.infoPrint(dtos);
						
						System.out.println("등록할 차종 코드를 입력하세요.");
						String v_type = sc.next();
						System.out.println("등록할 차량의 탑승인원을 입력하세요.");
						String v_seater = sc.next();
						
						System.out.println("등록할 차량의 렌트비용 입력하세요.");
						int price = sc.nextInt();
						
						CarDto dto = new CarDto(v_num, v_brand, v_model, v_type, v_seater, "n", price);
						
						int result = carDao.insertVh(dto);
						
						if(result == 0) System.out.println("등록 실패");
						else System.out.println("등록 성공");
					}
					
				//차량수정	
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 차량번호를 입력하세요.");
					String v_num = sc.next();
					
					ArrayList<CarDto> dtos1 = carDao.getOneList(v_num);
					
					if(dtos1.size() == 0) {
						System.out.println("해당 번호의 차량은 존재하지 않습니다.");
					}else {
						carDao.infoPrint(dtos1);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							System.out.println("전체 수정: A, 렌탈비용 수정: M");
							String sr = sc.next();
								//전체 수정
							if(sr.equalsIgnoreCase("a") || sr.equals("ㅁ")) {
									//자릿수 초과 체크
								String v_brand = "";
								do {
									System.out.println("차량의 제조사을 입력하세요. (6글자 이내)");
									v_brand = sc.next();
									if(v_brand.length() > 6) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
								}while(v_brand.length() > 6);
									
								//자릿수 초과 체크
								String v_model = "";
								do {
									System.out.println("차량의 모델명을 입력하세요. (5글자 이내)");
									v_model = sc.next();
									if(v_model.length() > 5) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
								}while(v_model.length() > 5);
								
								ArrayList<TypeDto> dtos = typeDao.getListAll();
								typeDao.infoPrint(dtos);
								
								System.out.println("차종 코드를 입력하세요.");
								String v_type = sc.next();
								System.out.println("차량의 탑승인원을 입력하세요.");
								String v_seater = sc.next();
								System.out.println("차량의 렌트비용 입력하세요.");
								int price = sc.nextInt();
								
								CarDto dto = new CarDto(v_num, v_brand, v_model, v_type, v_seater, price);
								int result = carDao.updateVh(dto);
								
								if(result == 0) System.out.println("수정 실패");
								else System.out.println("수정 성공");
									
								//렌탈비용 수정
							}else if(sr.equalsIgnoreCase("m") || sr.equals("ㅡ")) {
								System.out.println("차량의 렌트비용 입력하세요.");
								int price = sc.nextInt();
								
								CarDto dto = new CarDto(v_num, price);
								int result = carDao.updatePriceVh(dto);
									
								if(result == 0) System.out.println("수정 실패");
								else System.out.println("수정 성공");
							}
						}
					}
					
				//차량삭제	
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 차량번호를 입력하세요.");
					String v_num = sc.next();
					
					ArrayList<CarDto> dtos1 = carDao.getOneList(v_num);
					
					if(dtos1.size() == 0) {
						System.out.println("해당 번호의 차량은 존재하지 않습니다.");
					}else {
						//렌탈등록된 차량은 삭제 불가
						ArrayList<RentDto> dtos = rentDao.getListAll();
						if(dtos.size() == 0) {
							carDao.infoPrint(dtos1);
							System.out.println("삭제하시겠습니까? Yes: y, No: n");
							String qt = sc.next();
							
							if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
								int result = carDao.deleteVh(v_num);
								
								if(result == 0) System.out.println("삭제 실패");
								else System.out.println("삭제 성공");
							}
						}else System.out.println("대여 기록이 있어서 삭제가 불가능합니다.");
					}
				}else System.out.println("잘못 입력하셨습니다.");
				
			//회원관리		
			}else if(gubun.equalsIgnoreCase("m") || gubun.equals("ㅡ")){
				ArrayList<MemberDto> dtos = memberDao.getListAll();
				memberDao.infoPrint(dtos);
				
			//대여관리	
			}else if(gubun.equalsIgnoreCase("r") || gubun.equals("ㄱ")) {
				System.out.println("[대여관리] 조회: S, 대여: R, 반납: B, 수정: U, 삭제: D?");
				String search = sc.next();
				
				//조회
				if(search.equalsIgnoreCase("s") || search.equals("ㄴ")) {
					System.out.println("[대여관리 조회] 전체: A, 회원별 조회: M, 차량별 조회: V?");
					String gubun1 = sc.next();
					
					//전체조회
					if(gubun1.equalsIgnoreCase("a") || gubun1.equals("ㅁ")) {
						ArrayList<RentDto> dtos = rentDao.getListAll();
						rentDao.infoPrint(dtos);
						
					}else if(gubun1.equalsIgnoreCase("m") || gubun1.equals("ㅡ")) {
						
					}else if(gubun1.equalsIgnoreCase("v") || gubun1.equals("ㅍ")) {
						
					}else System.out.println("잘못 입력하셨습니다.");
					
				//대여	
				}else if(search.equalsIgnoreCase("r") || search.equals("ㄱ")){
					String r_code = rentDao.getMaxCode();
					System.out.println("등록될 렌탈 코드: "+r_code);
					
					//대여 가능한 차량 목록 조회
					ArrayList<CarDto> rentDtos = rentDao.getRentList();
					rentDao.infoRRPrint(rentDtos);
					
					//자릿수 초과 체크
					String v_num = "";
					do {
						System.out.println("대여할 차량번호를 입력하세요. ex)oo가-oooo");
						v_num = sc.next();
						if(v_num.length() > 8) System.out.println("자릿수 초과하였습니다. 다시 입력해주세요.");
						else if(v_num.length() != 8) System.out.println("잘못 입력하셨습니다.");
					}while(v_num.length() > 8 || v_num.length() < 8);
					
					//차량 중복 체크
					ArrayList<CarDto> dtos1 = carDao.getOneList(v_num);
					if(dtos1.size() == 0) System.out.println("해당 차량번호는 존재하지 않아 등록이 불가능 합니다.");
					else {
						System.out.println("차량을 대여하실 회원의 ID를 입력해주세요.");
						String member_id = sc.next();
						
						ArrayList<MemberDto> mbDtos = memberDao.getOneList(member_id);
						if(mbDtos.size() == 0) System.out.println("해당 ID의 회원은 존재하지 않습니다.");
						else {
							System.out.println("대여하실 날짜를 입력해주세요. ex)2000-01-01");
							String s_date = sc.next();
							
							//현재날짜비교
							int compare = rentDao.compareDate(s_date);
							if(compare <= 0) {
								RentDto dto = new RentDto(r_code, member_id, v_num, s_date);
								int result = rentDao.setRent(dto);
								
								if(result == 0) System.out.println("수정 실패");
								else System.out.println("수정 성공");
							}else System.out.println("현재보다 과거의 날짜는 대여가 불가능 합니다.");
						}
					}
					
				//반납
				}else if(search.equalsIgnoreCase("b") || search.equals("ㅠ")) {
					ArrayList<RentDto> Retrundtos = rentDao.getRetrunList();
					rentDao.infoPrint(Retrundtos);
					
					System.out.println("반납할 차량의 대여코드를 입력해주세요.");
					String r_code = sc.next();
					
					ArrayList<RentDto> dtos = rentDao.getOneList(r_code);
					if(dtos.size() == 0) System.out.println("해당 반납코드의 차량은 존재하지 않습니다.");
					else {
						System.out.println(dtos.get(0).getS_date());
						rentDao.infoPrint(dtos);
						System.out.println("반납하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							System.out.println("반납일을 입력해주세요. ex)2000-01-01");
							String l_date =sc.next();
							
							//현재날짜비교
							int compare = rentDao.compareDate(l_date);
							if(compare <= 0) {
								int days = rentDao.getSubDate(dtos.get(0).getS_date(), l_date);
								int price = rentDao.getPrice(r_code);
								
								RentDto dto = new RentDto(r_code, l_date, days, price);
								int result = rentDao.setReturn(dto);
								
								if(result == 1) System.out.println("반납 성공");
								else System.out.println("반납 실패");
							}else System.out.println("현재보다 과거의 날짜 입력은 불가능합니다.");
						}
					}
					//반납시 금액과 반납일 등록
					
					
				//대여수정
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					
					
				//대여삭제	
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 차량의 대여코드를 입력해주세요.");
					String r_code = sc.next();
					
					ArrayList<RentDto> dtos = rentDao.getOneList(r_code);
					if(dtos.size() == 0) System.out.println("해당 대여코드의 차량은 존재하지 않습니다.");
					else {
						System.out.println(dtos.get(0).getS_date());
						rentDao.infoPrint(dtos);
						System.out.println("삭제하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							int result = rentDao.deleteRent(r_code);
							if(result == 0) System.out.println("삭제 실패");
							else System.out.println("삭제 성공");
						}
					}
				}else System.out.println("잘못 입력하셨습니다.");	
				
			//종료
			}else if(gubun.equalsIgnoreCase("q") || gubun.equals("ㅂ")) {
				System.out.println("종료");
				break;
			}else System.out.println("잘못 입력하셨습니다.");
			
		}while(!gubun.equalsIgnoreCase("q"));
	}
}