package bean.response;

public class SO_Res_CheckDataSOBean {
	private String doc_date;
	private String ar_code;
	private String ar_name;
	private String sale_code;
	private String sale_name;
	
	
	public SO_Res_CheckDataSOBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_CheckDataSOBean(String doc_date, String ar_code,
			String ar_name, String sale_code, String sale_name) {
		super();
		this.doc_date = doc_date;
		this.ar_code = ar_code;
		this.ar_name = ar_name;
		this.sale_code = sale_code;
		this.sale_name = sale_name;
	}


	public String getDoc_date() {
		return doc_date;
	}


	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}


	public String getAr_code() {
		return ar_code;
	}


	public void setAr_code(String ar_code) {
		this.ar_code = ar_code;
	}


	public String getAr_name() {
		return ar_name;
	}


	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public String getSale_name() {
		return sale_name;
	}


	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	
	
}
