package bean.response;

public class SO_Res_ItemSaleOrderBean {
	private String item_code;
	private String item_name;
	private double item_price;
	private double qty;
	private double remain_qty;
	private String wh_code;
	private String shelf_code;
	private int item_rate;
	private double item_average;
	private String item_expert_code;
	private String item_department_code;
	private String item_depart_name;
	private String item_category_code;
	private String item_category_name;
	private String sec_code;
	private String sec_name;
	private String zone_id;
	private String pick_zone;
	private String unit_code;
	
	public SO_Res_ItemSaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ItemSaleOrderBean(String item_code, String item_name, double item_price, double qty, double remain_qty,
			String wh_code, String shelf_code, int item_rate, double item_average, String item_expert_code,
			String item_department_code, String item_depart_name, String item_category_code, String item_category_name,
			String sec_code, String sec_name, String zone_id, String pick_zone, String unit_code) {
		super();
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_price = item_price;
		this.qty = qty;
		this.remain_qty = remain_qty;
		this.wh_code = wh_code;
		this.shelf_code = shelf_code;
		this.item_rate = item_rate;
		this.item_average = item_average;
		this.item_expert_code = item_expert_code;
		this.item_department_code = item_department_code;
		this.item_depart_name = item_depart_name;
		this.item_category_code = item_category_code;
		this.item_category_name = item_category_name;
		this.sec_code = sec_code;
		this.sec_name = sec_name;
		this.zone_id = zone_id;
		this.pick_zone = pick_zone;
		this.unit_code = unit_code;
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

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	
	public double  getQty() {
		return qty;
	}
	
	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getRemain_qty() {
		return remain_qty;
	}

	public void setRemain_qty(double remain_qty) {
		this.remain_qty = remain_qty;
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

	public int getItem_rate() {
		return item_rate;
	}

	public void setItem_rate(int item_rate) {
		this.item_rate = item_rate;
	}

	public double getItem_average() {
		return item_average;
	}

	public void setItem_average(double item_average) {
		this.item_average = item_average;
	}

	public String getItem_expert_code() {
		return item_expert_code;
	}

	public void setItem_expert_code(String item_expert_code) {
		this.item_expert_code = item_expert_code;
	}

	public String getItem_department_code() {
		return item_department_code;
	}

	public void setItem_department_code(String item_department_code) {
		this.item_department_code = item_department_code;
	}

	public String getItem_depart_name() {
		return item_depart_name;
	}

	public void setItem_depart_name(String item_depart_name) {
		this.item_depart_name = item_depart_name;
	}

	public String getItem_category_code() {
		return item_category_code;
	}

	public void setItem_category_code(String item_category_code) {
		this.item_category_code = item_category_code;
	}

	public String getItem_category_name() {
		return item_category_name;
	}

	public void setItem_category_name(String item_category_name) {
		this.item_category_name = item_category_name;
	}

	public String getSec_code() {
		return sec_code;
	}

	public void setSec_code(String sec_code) {
		this.sec_code = sec_code;
	}

	public String getSec_name() {
		return sec_name;
	}

	public void setSec_name(String sec_name) {
		this.sec_name = sec_name;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public String getPick_zone() {
		return pick_zone;
	}

	public void setPick_zone(String pick_zone) {
		this.pick_zone = pick_zone;
	}

	public String getUnit_code() {
		return unit_code;
	}

	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	
}
