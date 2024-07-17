package dto;

public class AdmDto {
	private String id, name, password, job, no, 
	tell_1, tell_2, tell_3, 
	mobile_1, mobile_2, mobile_3, 
	email_1, email_2, reg_date, 
	last_login_date, exit_date;
	
	//조회
	public AdmDto(String no, String id, String name, String mobile_1, String mobile_2, String mobile_3, String email_1, String email_2, String exit_date) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.exit_date = exit_date;
	}

	//상세조회
	public AdmDto(String id, String name, String password, String job, String tell_1, String tell_2, String tell_3,
			String mobile_1, String mobile_2, String mobile_3, String email_1, String email_2, String reg_date,
			String last_login_date, String exit_date) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.job = job;
		this.tell_1 = tell_1;
		this.tell_2 = tell_2;
		this.tell_3 = tell_3;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.last_login_date = last_login_date;
		this.exit_date = exit_date;
	}

	public String getNo() {
		return no;
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

	public String getJob() {
		return job;
	}

	public String getTell_1() {
		return tell_1;
	}

	public String getTell_2() {
		return tell_2;
	}

	public String getTell_3() {
		return tell_3;
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
	
	
}
