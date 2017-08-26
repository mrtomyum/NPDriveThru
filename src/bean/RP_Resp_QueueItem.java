package bean;

public class RP_Resp_QueueItem {
	String itemCode;
	String itemName;
	String unitCode;
	double pickQty;
	double checkoutQty;
	double itemAmount;
	double checkoutAmount;
	double billAmount;
	String pickerCode;
	String checkerCode;
	String pickerName;
	String checkerName;
	int itemCancel;
	
	public RP_Resp_QueueItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RP_Resp_QueueItem(String itemCode, String itemName, String unitCode,
			double pickQty, double checkoutQty, double itemAmount,
			double checkoutAmount, double billAmount, String pickerCode,
			String checkerCode, String pickerName, String checkerName,
			int itemCancel) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.unitCode = unitCode;
		this.pickQty = pickQty;
		this.checkoutQty = checkoutQty;
		this.itemAmount = itemAmount;
		this.checkoutAmount = checkoutAmount;
		this.billAmount = billAmount;
		this.pickerCode = pickerCode;
		this.checkerCode = checkerCode;
		this.pickerName = pickerName;
		this.checkerName = checkerName;
		this.itemCancel = itemCancel;
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public double getPickQty() {
		return pickQty;
	}

	public void setPickQty(double pickQty) {
		this.pickQty = pickQty;
	}

	public double getCheckoutQty() {
		return checkoutQty;
	}

	public void setCheckoutQty(double checkoutQty) {
		this.checkoutQty = checkoutQty;
	}

	public double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public double getCheckoutAmount() {
		return checkoutAmount;
	}

	public void setCheckoutAmount(double checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public String getPickerCode() {
		return pickerCode;
	}

	public void setPickerCode(String pickerCode) {
		this.pickerCode = pickerCode;
	}

	public String getCheckerCode() {
		return checkerCode;
	}

	public void setCheckerCode(String checkerCode) {
		this.checkerCode = checkerCode;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public int getItemCancel() {
		return itemCancel;
	}

	public void setItemCancel(int itemCancel) {
		this.itemCancel = itemCancel;
	}
	
	
}
