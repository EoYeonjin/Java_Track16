package dto;

public class FaqDto {
	private String no, title, answer_content, reg_id, reg_name, reg_date, ipt;
	private int hit;
	
	//조회
	public FaqDto(String no, String title, String answer_content, String reg_name, String reg_date, int hit, String ipt) {
		super();
		this.no = no;
		this.title = title;
		this.answer_content = answer_content;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
		this.ipt = ipt;
	}

	//등록
	public FaqDto(String no, String title, String answer_content, String reg_id, String reg_date, String ipt) {
		super();
		this.no = no;
		this.title = title;
		this.answer_content = answer_content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.ipt = ipt;
	}

	public String getIpt() {
		return ipt;
	}

	public String getNo() {
		return no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAnswer_content() {
		return answer_content;
	}
	
	public String getReg_id() {
		return reg_id;
	}
	
	public String getReg_name() {
		return reg_name;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	
	public int getHit() {
		return hit;
	}
}
