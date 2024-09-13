package dto;

public class MemberDto {
	
	String p_no, p_name, p_birth, p_gender, p_tel1, p_tel2, p_tel3, p_reg_date;
	
	//회원조회
	public MemberDto(String p_no, String p_name, String p_birth, String p_gender, String p_tel1, String p_tel2,
			String p_tel3, String p_reg_date) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_birth = p_birth;
		this.p_gender = p_gender;
		this.p_tel1 = p_tel1;
		this.p_tel2 = p_tel2;
		this.p_tel3 = p_tel3;
		this.p_reg_date = p_reg_date;
	}

	public String getP_no() {
		return p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public String getP_birth() {
		return p_birth;
	}

	public String getP_gender() {
		return p_gender;
	}

	public String getP_tel1() {
		return p_tel1;
	}

	public String getP_tel2() {
		return p_tel2;
	}

	public String getP_tel3() {
		return p_tel3;
	}

	public String getP_reg_date() {
		return p_reg_date;
	}
	
	

}
