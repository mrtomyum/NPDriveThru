package bean.response;

public class ResItemSearchBean {
	private String item_barcode;
	String item_code;;
	String item_name;
	String item_category;
	Double item_price;
	String item_unit_code;
	String item_remark;
	String item_short_code;
	String item_file_path;


	/**
	 * @param barcode
	 * @param itemCode
	 * @param itemName
	 * @param itemCategory
	 * @param itemPrice
	 * @param unitCode
	 * @param remark
	 * @param shortCode
	 * @param filePath
	 */
	
	public ResItemSearchBean() {}


	public ResItemSearchBean(String item_barcode, String item_code,
			String item_name, String item_category, Double item_price,
			String item_unit_code, String item_remark, String item_short_code,
			String item_file_path) {
		super();
		this.item_barcode = item_barcode;
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_category = item_category;
		this.item_price = item_price;
		this.item_unit_code = item_unit_code;
		this.item_remark = item_remark;
		this.item_short_code = item_short_code;
		this.item_file_path = item_file_path;
	}


	public String getItem_barcode() {
		return item_barcode;
	}


	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
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


	public String getItem_category() {
		return item_category;
	}


	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}


	public Double getItem_price() {
		return item_price;
	}


	public void setItem_price(Double item_price) {
		this.item_price = item_price;
	}


	public String getItem_unit_code() {
		return item_unit_code;
	}


	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
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
