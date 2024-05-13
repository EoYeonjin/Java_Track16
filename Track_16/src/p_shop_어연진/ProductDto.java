package p_shop_어연진;

public class ProductDto {
	private String p_code, p_name, p_size;
	private int count, price;
	
	public ProductDto(String p_name, String p_size) {
		super();
		this.p_name = p_name;
		this.p_size = p_size;
	}

	public ProductDto(String p_code, String p_name, String p_size, int count, int price) {
		this.p_code = p_code;
		this.p_name = p_name;
		this.p_size = p_size;
		this.count = count;
		this.price = price;
	}
	
	public String getP_code() {
		return p_code;
	}
	
	public String getP_name() {
		return p_name;
	}
	
	public String getP_size() {
		return p_size;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getPrice() {
		return price;
	}
	
	
}