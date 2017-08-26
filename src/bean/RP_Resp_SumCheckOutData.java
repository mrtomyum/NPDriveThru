package bean;

public class RP_Resp_SumCheckOutData {
	String docYear;
	String docMonth;
	String docDate;
	int countItem;
	double sumPickupQty;
	double sumCheckOutQty;
	double pickupAmount;
	double checkoutAmount;
	double billAmount;
	
	
	public RP_Resp_SumCheckOutData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_SumCheckOutData(String docYear, String docMonth,
			String docDate, int countItem, double sumPickupQty,
			double sumCheckOutQty, double pickupAmount, double checkoutAmount,
			double billAmount) {
		super();
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docDate = docDate;
		this.countItem = countItem;
		this.sumPickupQty = sumPickupQty;
		this.sumCheckOutQty = sumCheckOutQty;
		this.pickupAmount = pickupAmount;
		this.checkoutAmount = checkoutAmount;
		this.billAmount = billAmount;
	}


	public String getDocYear() {
		return docYear;
	}


	public void setDocYear(String docYear) {
		this.docYear = docYear;
	}


	public String getDocMonth() {
		return docMonth;
	}


	public void setDocMonth(String docMonth) {
		this.docMonth = docMonth;
	}


	public String getDocDate() {
		return docDate;
	}


	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}


	public int getCountItem() {
		return countItem;
	}


	public void setCountItem(int countItem) {
		this.countItem = countItem;
	}


	public double getSumPickupQty() {
		return sumPickupQty;
	}


	public void setSumPickupQty(double sumPickupQty) {
		this.sumPickupQty = sumPickupQty;
	}


	public double getSumCheckOutQty() {
		return sumCheckOutQty;
	}


	public void setSumCheckOutQty(double sumCheckOutQty) {
		this.sumCheckOutQty = sumCheckOutQty;
	}


	public double getPickupAmount() {
		return pickupAmount;
	}


	public void setPickupAmount(double pickupAmount) {
		this.pickupAmount = pickupAmount;
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

}
