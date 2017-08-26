package bean;

public class RP_Resp_CountQueue {
	String docYear;
	String docMonth;
	String docDate;
	int docQty;
	int isUsedQty;
	int isCancelQty;
	
	
	public RP_Resp_CountQueue() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_CountQueue(String docYear, String docMonth, String docDate,
			int docQty, int isUsedQty, int isCancelQty) {
		super();
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docDate = docDate;
		this.docQty = docQty;
		this.isUsedQty = isUsedQty;
		this.isCancelQty = isCancelQty;
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


	public int getDocQty() {
		return docQty;
	}


	public void setDocQty(int docQty) {
		this.docQty = docQty;
	}


	public int getIsUsedQty() {
		return isUsedQty;
	}


	public void setIsUsedQty(int isUsedQty) {
		this.isUsedQty = isUsedQty;
	}


	public int getIsCancelQty() {
		return isCancelQty;
	}


	public void setIsCancelQty(int isCancelQty) {
		this.isCancelQty = isCancelQty;
	}
	
	
}
