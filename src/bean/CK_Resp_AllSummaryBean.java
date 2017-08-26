package bean;

public class CK_Resp_AllSummaryBean {
private double totalBeforePrice;
private double totalAfterPrice;

	public CK_Resp_AllSummaryBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CK_Resp_AllSummaryBean(double totalBeforePrice, double totalAfterPrice) {
		super();
		this.totalBeforePrice = totalBeforePrice;
		this.totalAfterPrice = totalAfterPrice;
	}
	
	public double getTotalBeforePrice() {
		return totalBeforePrice;
	}
	
	public void setTotalBeforePrice(double totalBeforePrice) {
		this.totalBeforePrice = totalBeforePrice;
	}
	
	public double getTotalAfterPrice(){
		return totalAfterPrice;
	}
	
	public void setTotalAfterPrice(double totalAfterPrice){
		this.totalAfterPrice = totalAfterPrice;
	}
}
