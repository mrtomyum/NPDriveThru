package bean.request;

public class SO_Res_VerifySaleOrderBean {
	private String doc_no;
	private String doc_date;
	private String ar_code;
	private String sale_code;
	private int bill_type;
	private int deliver_type;
	private double so_qty;
	private double remain_qty;
	private double item_rate;
	
	
	public SO_Res_VerifySaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_VerifySaleOrderBean(String doc_no, String doc_date,
			String ar_code, String sale_code, int bill_type, int deliver_type,
			double so_qty, double remain_qty, double item_rate) {
		super();
		this.doc_no = doc_no;
		this.doc_date = doc_date;
		this.ar_code = ar_code;
		this.sale_code = sale_code;
		this.bill_type = bill_type;
		this.deliver_type = deliver_type;
		this.so_qty = so_qty;
		this.remain_qty = remain_qty;
		this.item_rate = item_rate;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
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


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public int getBill_type() {
		return bill_type;
	}


	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
	}


	public int getDeliver_type() {
		return deliver_type;
	}


	public void setDeliver_type(int deliver_type) {
		this.deliver_type = deliver_type;
	}


	public double getSo_qty() {
		return so_qty;
	}


	public void setSo_qty(double so_qty) {
		this.so_qty = so_qty;
	}


	public double getRemain_qty() {
		return remain_qty;
	}


	public void setRemain_qty(double remain_qty) {
		this.remain_qty = remain_qty;
	}


	public double getItem_rate() {
		return item_rate;
	}


	public void setItem_rate(double item_rate) {
		this.item_rate = item_rate;
	}
	
	

}
