package p_rentcar_어연진;

public class CarDto {
	private String v_num, v_brand, v_model, v_type, v_seater, status;
	private int price;
	
	public CarDto(String v_num, String v_brand, String v_model, String v_type, String v_seater, String status,
			int price) {
		this.v_num = v_num;
		this.v_brand = v_brand;
		this.v_model = v_model;
		this.v_type = v_type;
		this.v_seater = v_seater;
		this.status = status;
		this.price = price;
	}

	public CarDto(String v_num, String v_brand, String v_model, String v_type, String v_seater, int price) {
		this.v_num = v_num;
		this.v_brand = v_brand;
		this.v_model = v_model;
		this.v_type = v_type;
		this.v_seater = v_seater;
		this.price = price;
	}
	
	public CarDto(String v_num, int price) {
		super();
		this.v_num = v_num;
		this.price = price;
	}

	public String getV_num() {
		return v_num;
	}

	public String getV_brand() {
		return v_brand;
	}

	public String getV_model() {
		return v_model;
	}

	public String getV_type() {
		return v_type;
	}

	public String getV_seater() {
		return v_seater;
	}

	public String getStatus() {
		return status;
	}

	public int getPrice() {
		return price;
	}
	
	
	
	
}