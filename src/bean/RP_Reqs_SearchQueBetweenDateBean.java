package bean;

public class RP_Reqs_SearchQueBetweenDateBean {
	String beginDate;
	String endDate;
	
	
	public RP_Reqs_SearchQueBetweenDateBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Reqs_SearchQueBetweenDateBean(String beginDate, String endDate) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
	}


	public String getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}
