package dto;

public class MemberDto {
	String id, name, password, 
	mobile_1, mobile_2, mobile_3, 
	email_1, email_2, reg_date, 
	last_login_date, exit_date,
	info;
	
	//로그인 시 조회
	public MemberDto(String name, String last_login_date) {
		super();
		this.name = name;
		this.last_login_date = last_login_date;
	}

	//등록
	public MemberDto(String id, String name, String password, String mobile_1, String mobile_2, String mobile_3,
			String email_1, String email_2, String reg_date, String info) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getMobile_1() {
		return mobile_1;
	}

	public String getMobile_2() {
		return mobile_2;
	}

	public String getMobile_3() {
		return mobile_3;
	}

	public String getEmail_1() {
		return email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public String getExit_date() {
		return exit_date;
	}

	public String getInfo() {
		return info;
	}
	
	
}
