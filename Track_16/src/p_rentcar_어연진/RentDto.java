package p_rentcar_어연진;

public class RentDto {
	private String r_code, name, v_num, v_model, s_date, l_date, member_id, status;
	
	private int price, days;
	
	public RentDto(String r_code, String name, String v_num, String v_model, String s_date, String l_date, String status, int price) {
		this.r_code = r_code;
		this.name = name;
		this.v_num = v_num;
		this.v_model = v_model;
		this.s_date = s_date;
		this.l_date = l_date;
		this.price = price;
		this.status = status;
	}

	public RentDto(String r_code, String member_id, String v_num, String s_date) {
		this.r_code = r_code;
		this.member_id = member_id;
		this.v_num = v_num;
		this.s_date = s_date;
	}

	public RentDto(String r_code, String l_date, int days, int price) {
		this.r_code = r_code;
		this.l_date = l_date;
		this.days = days;
		this.price = price;
	}

	public String getR_code() {
		return r_code;
	}

	public String getName() {
		return name;
	}

	public String getV_num() {
		return v_num;
	}

	public String getS_date() {
		return s_date;
	}

	public String getL_date() {
		return l_date;
	}

	public String getV_model() {
		return v_model;
	}

	public int getPrice() {
		return price;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getStatus() {
		return status;
	}

	public int getDays() {
		return days;
	}

	
}