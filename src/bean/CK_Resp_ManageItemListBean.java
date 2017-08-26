package bean;

public class CK_Resp_ManageItemListBean {
	 private String barCode;
	 private String itemCode;
	 private String itemName;
	 private String itemCategory;
	 private double itemPrice;
	 private String unitCode;
	 private String remark;
	 private String shortCode;
	 private String filePath;
	 
	 
	public CK_Resp_ManageItemListBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CK_Resp_ManageItemListBean(String barCode, String itemCode,
			String itemName, String itemCategory, double itemPrice,
			String unitCode, String remark, String shortCode, String filePath) {
		super();
		this.barCode = barCode;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemPrice = itemPrice;
		this.unitCode = unitCode;
		this.remark = remark;
		this.shortCode = shortCode;
		this.filePath = filePath;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
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


	public String getItemCategory() {
		return itemCategory;
	}


	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}


	public double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public String getUnitCode() {
		return unitCode;
	}


	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getShortCode() {
		return shortCode;
	}


	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	 
	 
	 
	 
	 
}
