package bean.response;

public class SO_Res_ListProductQueueBean {
	 private String item_barcode;
	 private String file_path;
	 private int is_cancel;
	 private int is_check;
	 private String item_code;
	 private String item_name;
	 private String pickup_staff_name;
	 private String sale_code;
	 private double item_price;
	 private double qty_after;
	 private double qty_before;
	 private double qty_load;
	 private double total_price_after;
	 private double total_price_before;
	 private String item_unit_code;
	 private double request_qty;
	 private double item_qty;
	 private String pick_zone_id;
	 private int line_number;
	 
	 
	public SO_Res_ListProductQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_ListProductQueueBean(String item_barcode, String file_path, int is_cancel, int is_check,
			String item_code, String item_name, String pickup_staff_name, String sale_code, double item_price,
			double qty_after, double qty_before, double qty_load, double total_price_after, double total_price_before,
			String item_unit_code, double request_qty, double item_qty, String pick_zone_id, int line_number) {
		super();
		this.item_barcode = item_barcode;
		this.file_path = file_path;
		this.is_cancel = is_cancel;
		this.is_check = is_check;
		this.item_code = item_code;
		this.item_name = item_name;
		this.pickup_staff_name = pickup_staff_name;
		this.sale_code = sale_code;
		this.item_price = item_price;
		this.qty_after = qty_after;
		this.qty_before = qty_before;
		this.qty_load = qty_load;
		this.total_price_after = total_price_after;
		this.total_price_before = total_price_before;
		this.item_unit_code = item_unit_code;
		this.request_qty = request_qty;
		this.item_qty = item_qty;
		this.pick_zone_id = pick_zone_id;
		this.line_number = line_number;
	}


	public String getItem_barcode() {
		return item_barcode;
	}


	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
	}


	public String getFile_path() {
		return file_path;
	}


	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	public int getIs_cancel() {
		return is_cancel;
	}


	public void setIs_cancel(int is_cancel) {
		this.is_cancel = is_cancel;
	}


	public int getIs_check() {
		return is_check;
	}


	public void setIs_check(int is_check) {
		this.is_check = is_check;
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


	public String getPickup_staff_name() {
		return pickup_staff_name;
	}


	public void setPickup_staff_name(String pickup_staff_name) {
		this.pickup_staff_name = pickup_staff_name;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public double getItem_price() {
		return item_price;
	}


	public void setItem_price(double item_price) {
		this.item_price = item_price;
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
	
	public double getQty_load() {
		return qty_load;
	}


	public double getTotal_price_after() {
		return total_price_after;
	}


	public void setTotal_price_after(double total_price_after) {
		this.total_price_after = total_price_after;
	}


	public double getTotal_price_before() {
		return total_price_before;
	}


	public void setTotal_price_before(double total_price_before) {
		this.total_price_before = total_price_before;
	}


	public String getItem_unit_code() {
		return item_unit_code;
	}


	public void setItem_unit_code(String item_unit_code) {
		this.item_unit_code = item_unit_code;
	}


	public double getRequest_qty() {
		return request_qty;
	}


	public void setRequest_qty(double request_qty) {
		this.request_qty = request_qty;
	}


	public double getItem_qty() {
		return item_qty;
	}


	public void setItem_qty(double item_qty) {
		this.item_qty = item_qty;
	}

	
	public String getPick_zone_id() {
		return pick_zone_id;
	}
	
	public void setPick_zone_id(String pick_zone_id) {
		this.pick_zone_id = pick_zone_id;
	}

	public int getLine_number() {
		return line_number;
	}


	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}


}
