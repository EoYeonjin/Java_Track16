package p_shop_어연진;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopMain {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		MemberDao memberDao = new MemberDao();
		SaleDao saleDao = new SaleDao();
		Scanner sc = new Scanner(System.in);
		
		String gubun = "";
		
		do {
			System.out.println("상품관리: P, 회원관리: M, 판매관리: S, 종료: Q?");
			gubun = sc.next();
			
			//상품관리
			if(gubun.equalsIgnoreCase("p") || gubun.equals("ㅔ")) {
				System.out.println("[상품관리] 조회: S, 등록: I, 수정: U, 삭제: D?");
				String search = sc.next();
				
				//전체조회
				if(search.equalsIgnoreCase("s") || search.equals("ㄴ")) {
					ArrayList<ProductDto> dtos = productDao.getListAll();
					ProductDao.infoPrint(dtos);
					
				//상품등록	
				}else if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					String p_code = productDao.getMaxCode();
					System.out.println("등록될 상품코드: "+p_code);
					System.out.println("등록할 상품명을 입력하세요.");
					String p_name = sc.next();
					System.out.println("등록할 상품의 사이즈를 입력하세요.");
					String p_size = sc.next();
					
					ProductDto resultDto = productDao.existSamePd(p_name, p_size);
					if(resultDto != null) System.out.println("동일한 조건의 상품이 존재하여 등록이 불가능 합니다.");
					else {
						System.out.println("등록할 상품의 수량을 입력하세요.");
						int count = sc.nextInt();
						System.out.println("등록할 상품의 가격을 입력하세요.");
						int price = sc.nextInt();
						
						ProductDto dto = new ProductDto(p_code, p_name, p_size, count, price);
						
						int result = productDao.insertPd(dto);
						
						if(result == 0) {
							System.out.println("등록 실패");
						}else {
							System.out.println("등록 성공");
						}
					}

				//상품수정	
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 상품 코드를 입력하세요.");
					String p_code = sc.next();
					ArrayList<ProductDto> dtos = productDao.getOneList(p_code);
					
					if(dtos.size() == 0) {
						System.out.println("해당 코드의 상품은 존재하지 않습니다.");
					}else {
						productDao.infoPrint(dtos);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							System.out.println("상품명을 입력하세요.");
							String p_name = sc.next();
							System.out.println("상품의 사이즈를 입력하세요.");
							String p_size = sc.next();
							ProductDto resultDto = productDao.existSamePd(p_name, p_size);
							if(resultDto != null) System.out.println("동일한 조건의 상품이 존재하여 등록이 불가능 합니다.");
							else {
								System.out.println("상품의 수량을 입력하세요.");
								int count = sc.nextInt();
								System.out.println("상품의 가격을 입력하세요.");
								int price = sc.nextInt();
								
								ProductDto dto = new ProductDto(p_code, p_name, p_size, count, price);
								
								int result = productDao.updatePd(dto);
								
								if(result == 0) {
									System.out.println("수정 실패");
								}else {
									System.out.println("수정 성공");
								}
							}
						}
					}
					
				//상품삭제	
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 상품 코드를 입력하세요.");
					String p_code = sc.next();
					ArrayList<ProductDto> dtos = productDao.getOneList(p_code);
					
					if(dtos.size() == 0) {
						System.out.println("해당 코드의 상품은 존재하지 않습니다.");
					}else {
						SaleDto resultDto = saleDao.existPd(p_code);
						if(resultDto != null) System.out.println("해당 상품은 판매이력이 존재하므로 삭제가 불가합니다.");
						else {
							productDao.infoPrint(dtos);
							System.out.println("삭제하시겠습니까? Yes: y, No: n");
							String qt = sc.next();
							
							if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
								int result = productDao.deletePd(p_code);
								
								if(result == 0) {
									System.out.println("삭제 실패");
								}else {
									System.out.println("삭제 성공");
								}
							}
						}
					}
				}else System.out.println("잘못 입력하셨습니다.");
				
			//회원관리	
			}else if(gubun.equalsIgnoreCase("m") || gubun.equals("ㅡ")) {
				System.out.println("[회원관리] 조회: S, 등록: I, 수정: U, 삭제: D?");
				String search = sc.next();
					
				//조회
				if(search.equalsIgnoreCase("s") || search.equals("ㄴ")) {
					System.out.println("[회원관리 조회] 전체 조회: S, 이름 조회: N, 지역 조회: A?");
					String gubun1 = sc.next();
						
					//전체조회
					if(gubun1.equalsIgnoreCase("s") || gubun1.equals("ㄴ")) {
						ArrayList<MemberDto> dtos = memberDao.getListAll();
						memberDao.infoPrint(dtos);
							
					//이름 조회
					}else if(gubun1.equalsIgnoreCase("n") || gubun1.equals("ㅜ")) {
						System.out.println("조회할 이름을 입력하세요.");
						String name = sc.next();
						
						ArrayList<MemberDto> dtos = memberDao.getNameList(name);
						memberDao.infoPrint(dtos);
							
					//지역 조회
					}else if(gubun1.equalsIgnoreCase("a") || gubun1.equals("ㅁ")) {
						ArrayList<MemberDto> dtos1 = memberDao.getAreaCode();
						memberDao.areaCodePrint(dtos1);
						System.out.println("조회할 지역 코드를 입력하세요.");
						String area_code = sc.next();
						
						MemberDto resultDto = memberDao.sameMemberCode(area_code);
						if(resultDto == null) {
							System.out.println("해당 지역 코드는 존재하지 않습니다.");
						}else {
							ArrayList<MemberDto> dtos = memberDao.getAreaList(area_code);
							memberDao.infoPrint(dtos);
						}
					}else if(gubun1.equalsIgnoreCase("b") || gubun1.equals("ㅠ")) {}
				
				//회원등록
				}else if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					System.out.println("등록할 회원 ID를 입력해주세요.");
					String id = sc.next();
					
					ArrayList<MemberDto> dtos = memberDao.getOneList(id);
					if(dtos.size() != 0) {
						System.out.println("이미 존재하는 ID이므로 해당 ID로는 등록이 불가합니다.");
					}else {
						System.out.println("등록할 이름을 입력해주세요.");
						String name = sc.next();
						
						ArrayList<MemberDto> dtos1 = memberDao.getAreaCode();
						memberDao.areaCodePrint(dtos1);
						System.out.println("등록할 지역 코드를 입력하세요.");
						String area = sc.next();
						
						MemberDto resultDto = memberDao.sameMemberCode(area);
						if(resultDto == null) {
							System.out.println("해당 지역 코드는 존재하지 않습니다.");
						}else {
							System.out.println("등록할 나이을 입력하세요.");
							int age = sc.nextInt();
							
							MemberDto dto = new MemberDto(id, name, area, age);
							
							int result = memberDao.insertMember(dto);
							if(result == 0) {
								System.out.println("등록 실패");
							}else {
								System.out.println("등록 성공");
							}
						}
					}
					
				//회원수정
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 회원 ID를 입력하세요.");
					String id = sc.next();
					ArrayList<MemberDto> dtos = memberDao.getOneList(id);
					
					if(dtos.size() == 0) {
						System.out.println("해당 회원의 ID는 존재하지 않습니다.");
					}else {
						memberDao.infoPrint(dtos);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							System.out.println("회원명을 입력하세요.");
							String name = sc.next();
							
							ArrayList<MemberDto> dtos1 = memberDao.getAreaCode();
							memberDao.areaCodePrint(dtos1);
							System.out.println("지역 코드를 입력하세요.");
							String area = sc.next();
							
							MemberDto resultDto = memberDao.sameMemberCode(area);
							if(resultDto == null) {
								System.out.println("해당 지역 코드는 존재하지 않습니다.");
							}else {
								System.out.println("나이을 입력하세요.");
								int age = sc.nextInt();
								
								
								MemberDto dto = new MemberDto(id, name, area, age);
								
								int result = memberDao.updateMember(dto);
								
								if(result == 0) {
									System.out.println("수정 실패");
								}else {
									System.out.println("수정 성공");
								}
							}
						}
					}
					
				//회원삭제	
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 회원 ID를 입력하세요.");
					String id = sc.next();
					ArrayList<MemberDto> dtos = memberDao.getOneList(id);
					
					if(dtos.size() == 0) {
						System.out.println("해당 회원의 ID는 존재하지 않습니다.");
					}else {
						SaleDto resultDto = saleDao.existMb(id);
						if(resultDto != null) System.out.println("해당 회원은 구매이력이 존재하므로 삭제가 불가합니다.");
						else {
							memberDao.infoPrint(dtos);
							System.out.println("삭제하시겠습니까? Yes: y, No: n");
							String qt = sc.next();
							
							if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
								int result = memberDao.deleteMember(id);
									
								if(result == 0) {
									System.out.println("삭제 실패");
								}else {
									System.out.println("삭제 성공");
								}
							}
						}
					}
				}else System.out.println("잘못 입력하셨습니다.");
					
				
			//판매관리
			}else if(gubun.equalsIgnoreCase("s") || gubun.equals("ㄴ")) {
				System.out.println("[판매관리] 조회: S, 등록: I, 수정: U, 삭제: D?");
				String search = sc.next();
				
				//판매조회
				if(search.equalsIgnoreCase("s") || search.equals("ㄴ")) {
					System.out.println("[판매관리 조회] 전체: A, 제품별 판매이력: P, 회원별 구매이력: M?");
					String gubun1 = sc.next();
					
					//전체조회
					if(gubun1.equalsIgnoreCase("a") || gubun1.equals("ㅁ")) {
						ArrayList<SaleDto> dtos = saleDao.getListAll();
						saleDao.infoPrint(dtos);
						
					//제품별 판매이력
					}else if(gubun1.equalsIgnoreCase("p") || gubun1.equals("ㅔ")) {
						System.out.println("조회할 제품의 코드를 입력해주세요.");
						String p_code = sc.next();
						
						ArrayList<SaleDto> dtos = saleDao.getPdList(p_code);
						saleDao.infoPrint(dtos);
						
					//회원별 구매이력	
					}else if(gubun1.equalsIgnoreCase("m") || gubun1.equals("ㅡ")) {
						System.out.println("조회할 회원ID를 입력해주세요.");
						String member_id = sc.next();
						
						ArrayList<SaleDto> dtos = saleDao.getMbList(member_id);
						saleDao.infoPrint(dtos);
					}else System.out.println("잘못 입력하셨습니다.");
				
				//판매등록	
				}else if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					String ordernum = saleDao.getMaxCode();
					System.out.println("등록될 판매코드: "+ordernum);
					
					System.out.println("등록할 제품의 코드를 입력해주세요.");
					String p_code = sc.next();
					ArrayList<ProductDto> pdDtos = productDao.getOneList(p_code);
					if(pdDtos.size() == 0) System.out.println("해당 코드의 제품은 없습니다.");
					else {
						int count = productDao.getCountList(p_code);
						if(count == 0) System.out.println("재고가 부족하여 판매 등록이 불가합니다.");
						else {
							System.out.println("등록할 회원의 코드를 입력해주세요.");
							String member_id = sc.next();
							ArrayList<MemberDto> mbDtos = memberDao.getOneList(member_id);
							if(mbDtos.size() == 0) System.out.println("해당 코드의 회원은 존재하지 않습니다.");
							else {
								System.out.println("결제방식을 입력해주세요. 현금: 1, 카드: 2, 외상: 3");
								String payment = sc.next();
								if(payment.equals("1") || payment.equals("2") || payment.equals("3")) {
									System.out.println("판매수량을 입력해주세요 남은 재고: "+count);
									int buycount = sc.nextInt();
									
									if(count < buycount) System.out.println("재고가 부족하여 판매 등록이 불가합니다.");
									else {
										System.out.println("판매 일자를 입력해주세요. ex) 2000-01-01");
										String date = sc.next();
										
										SaleDto dto = new SaleDto(ordernum, p_code, member_id, payment, date, buycount);
										int result = saleDao.insertSale(dto);
										
										if(result == 0) System.out.println("등록 실패");
										else {
											int total = count - buycount;
											int result1 = productDao.updateCountPd(total, p_code);
											if(result1 == 1) {
												System.out.println("등록 성공");
											} else System.out.println("등록 실패");
										}
									}
								}else System.out.println("결제방식을 잘못 입력하셨습니다.");
							}
						}
					}
				
				//판매수정
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 판매코드를 입력해주세요.");
					String ordernum = sc.next();
					
					ArrayList<SaleDto> dtos = saleDao.getOneList(ordernum);
					if(dtos.size() == 0) System.out.println("해당 판매코드의 정보는 존재하지 않습니다.");
					else {
						saleDao.infoPrint(dtos);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							System.out.println("제품의 코드를 입력해주세요.");
							String p_code = sc.next();
							ArrayList<ProductDto> pdDtos = productDao.getOneList(p_code);
							
							if(dtos.get(0).getP_code().equals(p_code)) {
								if(pdDtos.size() == 0) System.out.println("해당 코드의 제품은 없습니다.");
								else {
									System.out.println("회원의 코드를 입력해주세요.");
									String member_id = sc.next();
									ArrayList<MemberDto> mbDtos = memberDao.getOneList(member_id);
									if(mbDtos.size() == 0) System.out.println("해당 코드의 회원은 존재하지 않습니다.");
									else {
										System.out.println("결제방식을 입력해주세요. 현금: 1, 카드: 2, 외상: 3");
										String payment = sc.next();
										if(payment.equals("1") || payment.equals("2") || payment.equals("3")) {
											int count = productDao.getCountList(p_code);
											System.out.println("판매수량을 입력해주세요. 남은 재고: "+count);
											int buycount = sc.nextInt();
												
											int buycountO = saleDao.getCountList(ordernum);
											int total = count + (buycountO - buycount);
											if(total < 0) System.out.println("재고가 부족하여 수정이 불가능 합니다.");
											else {
												int result1 = productDao.updateCountPd(total, p_code);
												if(result1 == 1) {
													System.out.println("판매 일자를 입력해주세요. ex) 2000-01-01");
													String date = sc.next();
														
													SaleDto dto = new SaleDto(ordernum, p_code, member_id, payment, date, buycount);
													int result = saleDao.updateSale(dto);
														
													if(result == 0) System.out.println("수정 실패");
													else System.out.println("수정 성공");
												}	
											}
										}else System.out.println("결제방식을 잘못 입력하셨습니다.");
									}
								}
							}else {
								if(pdDtos.size() == 0) System.out.println("해당 코드의 제품은 없습니다.");
								else {
									System.out.println("회원의 코드를 입력해주세요.");
									String member_id = sc.next();
									ArrayList<MemberDto> mbDtos = memberDao.getOneList(member_id);
									if(mbDtos.size() == 0) System.out.println("해당 코드의 회원은 존재하지 않습니다.");
									else {
										System.out.println("결제방식을 입력해주세요. 현금: 1, 카드: 2, 외상: 3");
										String payment = sc.next();
										if(payment.equals("1") || payment.equals("2") || payment.equals("3")) {
											int count1 = productDao.getCountList(p_code);
											System.out.println("판매수량을 입력해주세요. 남은 재고: "+count1);
											int buycount = sc.nextInt();
												
											int count2 = productDao.getCountList(dtos.get(0).getP_code());
											
											int buycountO = saleDao.getCountList(ordernum);
											
											int total1 = count1 - buycount;
											int total2 = count2 + buycountO;
													
											if(total1 < 0 || count1 == 0) System.out.println("재고가 부족하여 수정이 불가능 합니다.");
											else {
												int result1 = productDao.updateCountPd(total1, p_code);
												int result2 = productDao.updateCountPd(total2, dtos.get(0).getP_code());
												if(result1 == 1 && result2 == 1) {
													System.out.println("판매 일자를 입력해주세요. ex) 2000-01-01");
													String date = sc.next();
														
													SaleDto dto = new SaleDto(ordernum, p_code, member_id, payment, date, buycount);
													int result = saleDao.updateSale(dto);
														
													if(result == 0) System.out.println("수정 실패");
													else System.out.println("수정 성공");
												}	
											}
										}else System.out.println("결제방식을 잘못 입력하셨습니다.");
									}
								}
							}
						}
					}	
					
				//판매삭제	
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 판매코드를 입력해주세요.");
					String ordernum = sc.next();
					
					ArrayList<SaleDto> dtos = saleDao.getOneList(ordernum);
					if(dtos.size() == 0) System.out.println("해당 판매코드의 정보는 존재하지 않습니다.");
					else {
						saleDao.infoPrint(dtos);
						System.out.println("삭제하시겠습니까? Yes: y, No: n");
						String qt = sc.next();
						
						if(qt.equalsIgnoreCase("y") || qt.equals("ㅛ")) {
							int result = saleDao.deleteSale(ordernum);
							
							if(result == 0) System.out.println("삭제 실패");
							else {
								int count = productDao.getCountList(dtos.get(0).getP_code());
								int total = count + dtos.get(0).getBuycount();
								int result1 = productDao.updateCountPd(total, dtos.get(0).getP_code());
								if(result1 == 1) {
									System.out.println("삭제 성공");
								} else System.out.println("삭제 실패");
							}
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