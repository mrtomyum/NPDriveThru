package bean;

public class RP_Resp_ListCompareBean {
	private String docDate;
	private int countPickup;
	private int countCheckout;
	private int countBill;
	private int countBillAll;
	
	
	public RP_Resp_ListCompareBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_ListCompareBean(String docDate, int countPickup,
			int countCheckout, int countBill,int countBillAll) {
		super();
		this.docDate = docDate;
		this.countPickup = countPickup;
		this.countCheckout = countCheckout;
		this.countBill = countBill;
		this.countBillAll = countBillAll;
	}


	public String getDocDate() {
		return docDate;
	}


	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}


	public int getCountPickup() {
		return countPickup;
	}


	public void setCountPickup(int countPickup) {
		this.countPickup = countPickup;
	}


	public int getCountCheckout() {
		return countCheckout;
	}


	public void setCountCheckout(int countCheckout) {
		this.countCheckout = countCheckout;
	}


	public int getCountBill() {
		return countBill;
	}


	public void setCountBill(int countBill) {
		this.countBill = countBill;
	}

	public int getCountBillAll() {
		return countBillAll;
	}


	public void setCountBillAll(int countBillAll) {
		this.countBillAll = countBillAll;
	}


}
