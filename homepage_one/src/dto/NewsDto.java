package dto;

public class NewsDto {
	private String no, title, content, reg_id, reg_name, reg_date, ipt;
	private int hit;
	
	//이전, 다음글
	public NewsDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}

	//수정
	public NewsDto(String no, String title, String content, String ipt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.ipt = ipt;
	}

	//조회
	public NewsDto(String no, String title, String reg_name, String reg_date, String ipt, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.ipt = ipt;
		this.hit = hit;
	}
	
	//상세조회
	public NewsDto(String no, String title, String content, String reg_name, String reg_date, String ipt, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
		this.ipt = ipt;
	}

	//등록
	public NewsDto(String no, String title, String content, String reg_id, String reg_date, String ipt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.ipt = ipt;
	}

	public String getNo() {
		return no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
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
	
	public String getIpt() {
		return ipt;
	}
	
	public int getHit() {
		return hit;
	}
	
	
}
