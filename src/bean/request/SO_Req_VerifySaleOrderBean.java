package bean.request;

public class SO_Req_VerifySaleOrderBean {
	private String doc_no;
	private String item_code;
	private String item_unit_code;
	
	
	public SO_Req_VerifySaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_VerifySaleOrderBean(String doc_no, String item_code,
			String item_unit_code) {
		super();
		this.doc_no = doc_no;
		this.item_code = item_code;
		this.item_unit_code = item_unit_code;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}


	public String getItem_code() {
		return item_code;
	}


	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}


	public String getItem_unit_code() {
		return item_unit_code;
	}


	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
	}
	
	
}
