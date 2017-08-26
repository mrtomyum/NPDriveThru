package bean.response;

public class SO_Res_ListItemInvoiceBean {
	private String item_code;
	private String item_name;
	private String wh_code;
	private String shelf_code;
	private double qty;
	private double price;
	private String discount_word;
	private double discount_amount_sub;
	private double net_amount;
	private String unit_code;
	private String item_description;
	private int line_number;
	
	public SO_Res_ListItemInvoiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ListItemInvoiceBean(String item_code, String item_name,
			String wh_code, String shelf_code, double qty, double price,
			String discount_word, double discount_amount_sub,
			double net_amount, String unit_code, String item_description,
			int line_number) {
		super();
		this.item_code = item_code;
		this.item_name = item_name;
		this.wh_code = wh_code;
		this.shelf_code = shelf_code;
		this.qty = qty;
		this.price = price;
		this.discount_word = discount_word;
		this.discount_amount_sub = discount_amount_sub;
		this.net_amount = net_amount;
		this.unit_code = unit_code;
		this.item_description = item_description;
		this.line_number = line_number;
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

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscount_word() {
		return discount_word;
	}

	public void setDiscount_word(String discount_word) {
		this.discount_word = discount_word;
	}

	public double getDiscount_amount_sub() {
		return discount_amount_sub;
	}

	public void setDiscount_amount_sub(double discount_amount_sub) {
		this.discount_amount_sub = discount_amount_sub;
	}

	public double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
	}

	public String getUnit_code() {
		return unit_code;
	}

	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public int getLine_number() {
		return line_number;
	}

	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}

	
}
