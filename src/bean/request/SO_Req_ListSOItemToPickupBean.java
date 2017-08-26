package bean.request;

public class SO_Req_ListSOItemToPickupBean {
	private String item_id;
	private double qty;
	private String item_barcode;
	private String item_name;
	private String item_category;
	private double item_price;
	private String item_unit_code;
	private String item_whcode;
	private String item_shelfcode;
	private String item_remark;
	private String item_short_code;
	private String item_file_path;

	
	
	public SO_Req_ListSOItemToPickupBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public SO_Req_ListSOItemToPickupBean(String item_id, double qty,
			String item_barcode, String item_name, String item_category,
			double item_price, String item_unit_code, String item_whcode,
			String item_shelfcode, String item_remark, String item_short_code,
			String item_file_path) {
		super();
		this.item_id = item_id;
		this.qty = qty;
		this.item_barcode = item_barcode;
		this.item_name = item_name;
		this.item_category = item_category;
		this.item_price = item_price;
		this.item_unit_code = item_unit_code;
		this.item_whcode = item_whcode;
		this.item_shelfcode = item_shelfcode;
		this.item_remark = item_remark;
		this.item_short_code = item_short_code;
		this.item_file_path = item_file_path;
	}



	public String getItem_id() {
		return item_id;
	}



	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}



	public double getQty() {
		return qty;
	}



	public void setQty(double qty) {
		this.qty = qty;
	}



	public String getItem_barcode() {
		return item_barcode;
	}



	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
	}



	public String getItem_name() {
		return item_name;
	}



	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}



	public String getItem_category() {
		return item_category;
	}



	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}



	public double getItem_price() {
		return item_price;
	}



	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}



	public String getItem_unit_code() {
		return item_unit_code;
	}



	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
	}



	public String getItem_whcode() {
		return item_whcode;
	}



	public void setItem_whcode(String item_whcode) {
		this.item_whcode = item_whcode;
	}



	public String getItem_shelfcode() {
		return item_shelfcode;
	}



	public void setItem_shelfcode(String item_shelfcode) {
		this.item_shelfcode = item_shelfcode;
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



	public String getItem_file_path() {
		return item_file_path;
	}



	public void setItem_file_path(String item_file_path) {
		this.item_file_path = item_file_path;
	}


	
}
