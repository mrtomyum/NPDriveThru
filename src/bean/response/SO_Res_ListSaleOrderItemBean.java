package bean.response;

public class SO_Res_ListSaleOrderItemBean {
	private String item_code;
	private String item_barcode;
	private String item_file_path;
	private String item_name;
	private String item_unit_code;
	private String item_category;
	private String item_remark;
	private String item_short_code;
	private String wh_code;
	private String shelf_code;
	private double item_qty;
	private double request_qty;
	private double qty_before;
	private double item_price;
	private double discount_amount;
	private double item_amount;
	private double net_amount;

	private int packing_rate1;
	private int line_number;
	
	
	public SO_Res_ListSaleOrderItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_ListSaleOrderItemBean(String item_code, String item_barcode,
			String item_file_path, String item_name, String item_unit_code,
			String item_category, String item_remark, String item_short_code,
			String wh_code, String shelf_code, double item_qty,
			double request_qty, double qty_before, double item_price,
			double discount_amount, double item_amount, double net_amount,
			int packing_rate1, int line_number) {
		super();
		this.item_code = item_code;
		this.item_barcode = item_barcode;
		this.item_file_path = item_file_path;
		this.item_name = item_name;
		this.item_unit_code = item_unit_code;
		this.item_category = item_category;
		this.item_remark = item_remark;
		this.item_short_code = item_short_code;
		this.wh_code = wh_code;
		this.shelf_code = shelf_code;
		this.item_qty = item_qty;
		this.request_qty = request_qty;
		this.qty_before = qty_before;
		this.item_price = item_price;
		this.discount_amount = discount_amount;
		this.item_amount = item_amount;
		this.net_amount = net_amount;
		this.packing_rate1 = packing_rate1;
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


	public String getItem_file_path() {
		return item_file_path;
	}


	public void setItem_file_path(String item_file_path) {
		this.item_file_path = item_file_path;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public String getItem_unit_code() {
		return item_unit_code;
	}


	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
	}


	public String getItem_category() {
		return item_category;
	}


	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}


	public String getItem_remark() {
		return item_remark;
	}


	public void setItem_remark(String item_remark) {
		this.item_remark = item_remark;
	}


	public String getItem_short_code() {
		return item_short_code;
	}


	public void setItem_short_code(String item_short_code) {
		this.item_short_code = item_short_code;
	}


	public String getWh_code() {
		return wh_code;
	}


	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}


	public String getShelf_code() {
		return shelf_code;
	}


	public void setShelf_code(String shelf_code) {
		this.shelf_code = shelf_code;
	}


	public double getItem_qty() {
		return item_qty;
	}


	public void setItem_qty(double item_qty) {
		this.item_qty = item_qty;
	}


	public double getRequest_qty() {
		return request_qty;
	}


	public void setRequest_qty(double request_qty) {
		this.request_qty = request_qty;
	}


	public double getQty_before() {
		return qty_before;
	}


	public void setQty_before(double qty_before) {
		this.qty_before = qty_before;
	}


	public double getItem_price() {
		return item_price;
	}


	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}


	public double getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}


	public double getItem_amount() {
		return item_amount;
	}


	public void setItem_amount(double item_amount) {
		this.item_amount = item_amount;
	}


	public double getNet_amount() {
		return net_amount;
	}


	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
	}


	public int getPacking_rate1() {
		return packing_rate1;
	}


	public void setPacking_rate1(int packing_rate1) {
		this.packing_rate1 = packing_rate1;
	}


	public int getLine_number() {
		return line_number;
	}


	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}


}
