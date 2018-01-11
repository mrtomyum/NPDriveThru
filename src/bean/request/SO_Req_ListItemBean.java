package bean.request;


public class SO_Req_ListItemBean {
	private String access_token;
	private String item_barcode;
	private double qty_load;
	private int queue_id;
	private int is_cancel;
	private String sale_code;
	private int line_number;
	
	
	public SO_Req_ListItemBean() {
		super();
	}


	public SO_Req_ListItemBean(String access_token, String item_barcode, double qty_load, int queue_id, int is_cancel,
			String sale_code, int line_number) {
		super();
		this.access_token = access_token;
		this.item_barcode = item_barcode;
		this.qty_load = qty_load;
		this.queue_id = queue_id;
		this.is_cancel = is_cancel;
		this.sale_code = sale_code;
		this.line_number = line_number;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
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


	public int getLine_number() {
		return line_number;
	}


	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}
	
	
}
