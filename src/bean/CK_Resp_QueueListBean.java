package bean;

public class CK_Resp_QueueListBean {
	private String barCode;
	private String itemCode;
	private String filePath; 
	private String itemName; 
	private double pricePerUnit;
	private double totalPriceBefore; 
	private double totalPriceAfter;
	private double request_qty;
	private double qtyBefore;
	private double qtyAfter;
	private String unitCode; 
	private String pickupStaffName;
	private int isCheck;
	private int isCancel;
	
	
	public CK_Resp_QueueListBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CK_Resp_QueueListBean(String barCode, String itemCode,
			String filePath, String itemName, double pricePerUnit,
			double totalPriceBefore, double totalPriceAfter,
			double request_qty, double qtyBefore, double qtyAfter,
			String unitCode, String pickupStaffName, int isCheck, int isCancel) {
		super();
		this.barCode = barCode;
		this.itemCode = itemCode;
		this.filePath = filePath;
		this.itemName = itemName;
		this.pricePerUnit = pricePerUnit;
		this.totalPriceBefore = totalPriceBefore;
		this.totalPriceAfter = totalPriceAfter;
		this.request_qty = request_qty;
		this.qtyBefore = qtyBefore;
		this.qtyAfter = qtyAfter;
		this.unitCode = unitCode;
		this.pickupStaffName = pickupStaffName;
		this.isCheck = isCheck;
		this.isCancel = isCancel;
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


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public double getPricePerUnit() {
		return pricePerUnit;
	}


	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}


	public double getTotalPriceBefore() {
		return totalPriceBefore;
	}


	public void setTotalPriceBefore(double totalPriceBefore) {
		this.totalPriceBefore = totalPriceBefore;
	}


	public double getTotalPriceAfter() {
		return totalPriceAfter;
	}


	public void setTotalPriceAfter(double totalPriceAfter) {
		this.totalPriceAfter = totalPriceAfter;
	}


	public double getRequest_qty() {
		return request_qty;
	}


	public void setRequest_qty(double request_qty) {
		this.request_qty = request_qty;
	}


	public double getQtyBefore() {
		return qtyBefore;
	}


	public void setQtyBefore(double qtyBefore) {
		this.qtyBefore = qtyBefore;
	}


	public double getQtyAfter() {
		return qtyAfter;
	}


	public void setQtyAfter(double qtyAfter) {
		this.qtyAfter = qtyAfter;
	}


	public String getUnitCode() {
		return unitCode;
	}


	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public String getPickupStaffName() {
		return pickupStaffName;
	}


	public void setPickupStaffName(String pickupStaffName) {
		this.pickupStaffName = pickupStaffName;
	}


	public int getIsCheck() {
		return isCheck;
	}


	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}


	public int getIsCancel() {
		return isCancel;
	}


	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}


	
	
}
