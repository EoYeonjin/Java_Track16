package p_shop_어연진;

public class SaleDto {
	private String ordernum, p_name, p_code, name, member_id, payment, date;
	private String code;
	private int buycount, price;
	
	public SaleDto(String code) {
		this.code = code;
	}

	public SaleDto(String ordernum, String p_name, String p_code, String name, String member_id, String payment, String date, int buycount, int price) {
		this.ordernum = ordernum;
		this.p_name = p_name;
		this.p_code = p_code;
		this.name = name;
		this.member_id = member_id;
		this.payment = payment;
		this.date = date;
		this.buycount = buycount;
		this.price = price;
	}

	public SaleDto(String ordernum, String p_code, String member_id, String payment, String date, int buycount) {
		this.ordernum = ordernum;
		this.p_code = p_code;
		this.member_id = member_id;
		this.payment = payment;
		this.date = date;
		this.buycount = buycount;
	}

	public String getCode() {
		return code;
	}

	public int getPrice() {
		return price;
	}

	public String getP_name() {
		return p_name;
	}

	public String getName() {
		return name;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public String getP_code() {
		return p_code;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getPayment() {
		return payment;
	}

	public String getDate() {
		return date;
	}

	public int getBuycount() {
		return buycount;
	}
	
}