package bean.request;


public class SO_Req_ListItemBean {
	private String item_barcode;
	private double qty_load;
	private int is_cancel;
	private String sale_code;
	private String item_unit_code;
	private int line_number;
	
	
	public SO_Req_ListItemBean() {
		super();
	}


	public SO_Req_ListItemBean(String item_barcode, double qty_load, int is_cancel, String sale_code,
			String item_unit_code, int line_number) {
		super();
		this.item_barcode = item_barcode;
		this.qty_load = qty_load;
		this.is_cancel = is_cancel;
		this.sale_code = sale_code;
		this.item_unit_code = item_unit_code;
		this.line_number = line_number;
	}


	public String getItem_barcode() {
		return item_barcode;
	}


	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
	}


	public double getQty_load() {
		return qty_load;
	}


	public void setQty_load(double qty_load) {
		this.qty_load = qty_load;
	}


	public int getIs_cancel() {
		return is_cancel;
	}


	public void setIs_cancel(int is_cancel) {
		this.is_cancel = is_cancel;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
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
