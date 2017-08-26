package bean;

public class IV_Resp_PrintSlipSubBean {
	private String itemCode;
	private String itemName;
	private int qty;
	private String unitCode;
	private double price;
	private double amount;
	private int linenumber;
	
	public IV_Resp_PrintSlipSubBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Resp_PrintSlipSubBean(String itemCode, String itemName, int qty,
			String unitCode, double price, double amount, int linenumber) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.qty = qty;
		this.unitCode = unitCode;
		this.price = price;
		this.amount = amount;
		this.linenumber = linenumber;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getLinenumber() {
		return linenumber;
	}

	public void setLinenumber(int linenumber) {
		this.linenumber = linenumber;
	}
	
	
	
	
}
