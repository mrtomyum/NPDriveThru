package bean.request;

public class SO_Req_ListItemToQueueBean {
	private String item_code;
	private String item_barcode;
	private double request_qty;
	private String item_unit_code;
	private int line_number;

	
	public SO_Req_ListItemToQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_ListItemToQueueBean(String item_code, String item_barcode,
			double request_qty, String item_unit_code, int line_number) {
		super();
		this.item_code = item_code;
		this.item_barcode = item_barcode;
		this.request_qty = request_qty;
		this.item_unit_code = item_unit_code;
		this.line_number = line_number;
	}


	public String getItem_code() {
		return item_code;
	}


	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}


	public String getItem_barcode() {
		return item_barcode;
	}


	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
	}


	public double getRequest_qty() {
		return request_qty;
	}


	public void setRequest_qty(double request_qty) {
		this.request_qty = request_qty;
	}


	public String getItem_unit_code() {
		return item_unit_code;
	}


	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
	}


	public int getLine_number() {
		return line_number;
	}


	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}


}
