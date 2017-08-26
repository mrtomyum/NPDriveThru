package bean.response;

public class SO_Res_CheckQueueItemBean {
	private int item_exist;
	private String item_code;
	private String item_name;
	private String item_filepath;
	private String item_barcode;
	private double item_price;
	private String item_unitcode;
	private int item_source;
	private String sale_code;
	private String sale_name;
	private double sale_qty;
	private double request_qty;
	private double qty_after;
	private double qty_before;
	private double after_amount;
	private double before_amount;
	private int bill_type;
	private int delivery_type;
	
	public SO_Res_CheckQueueItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_CheckQueueItemBean(int item_exist, String item_code,
			String item_name, String item_filepath, String item_barcode,
			double item_price, String item_unitcode, int item_source,
			String sale_code, String sale_name, double sale_qty,
			double request_qty, double qty_after, double qty_before,
			double after_amount, double before_amount, int bill_type,
			int delivery_type) {
		super();
		this.item_exist = item_exist;
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_filepath = item_filepath;
		this.item_barcode = item_barcode;
		this.item_price = item_price;
		this.item_unitcode = item_unitcode;
		this.item_source = item_source;
		this.sale_code = sale_code;
		this.sale_name = sale_name;
		this.sale_qty = sale_qty;
		this.request_qty = request_qty;
		this.qty_after = qty_after;
		this.qty_before = qty_before;
		this.after_amount = after_amount;
		this.before_amount = before_amount;
		this.bill_type = bill_type;
		this.delivery_type = delivery_type;
	}

	public int getItem_exist() {
		return item_exist;
	}

	public void setItem_exist(int item_exist) {
		this.item_exist = item_exist;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_filepath() {
		return item_filepath;
	}

	public void setItem_filepath(String item_filepath) {
		this.item_filepath = item_filepath;
	}

	public String getItem_barcode() {
		return item_barcode;
	}

	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	public String getItem_unitcode() {
		return item_unitcode;
	}

	public void setItem_unitcode(String item_unitcode) {
		this.item_unitcode = item_unitcode;
	}

	public int getItem_source() {
		return item_source;
	}

	public void setItem_source(int item_source) {
		this.item_source = item_source;
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

	public double getSale_qty() {
		return sale_qty;
	}

	public void setSale_qty(double sale_qty) {
		this.sale_qty = sale_qty;
	}

	public double getRequest_qty() {
		return request_qty;
	}

	public void setRequest_qty(double request_qty) {
		this.request_qty = request_qty;
	}

	public double getQty_after() {
		return qty_after;
	}

	public void setQty_after(double qty_after) {
		this.qty_after = qty_after;
	}

	public double getQty_before() {
		return qty_before;
	}

	public void setQty_before(double qty_before) {
		this.qty_before = qty_before;
	}

	public double getAfter_amount() {
		return after_amount;
	}

	public void setAfter_amount(double after_amount) {
		this.after_amount = after_amount;
	}

	public double getBefore_amount() {
		return before_amount;
	}

	public void setBefore_amount(double before_amount) {
		this.before_amount = before_amount;
	}

	public int getBill_type() {
		return bill_type;
	}

	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
	}

	public int getDelivery_type() {
		return delivery_type;
	}

	public void setDelivery_type(int delivery_type) {
		this.delivery_type = delivery_type;
	}

}
