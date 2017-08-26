package bean;

import java.sql.Date;
import java.sql.Time;

public class RP_Resp_QueueMaster {
	String docYear;
	String docMonth;
	String docNo;
	int qId;
	String docDate;
	String carLicence;
	String carBrand;
	int queStatus;
	int numberOfItem;
	double pickAmount;
	double checkoutAmount;
	double billAmount;
	Date createDate;
	Time createTime;
	int isCancel;
	
	
	public RP_Resp_QueueMaster() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_QueueMaster(String docYear, String docMonth, String docNo,
			int qId, String docDate, String carLicence, String carBrand,
			int queStatus, int numberOfItem, double pickAmount,
			double checkoutAmount, double billAmount, Date createDate,
			Time createTime, int isCancel) {
		super();
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docNo = docNo;
		this.qId = qId;
		this.docDate = docDate;
		this.carLicence = carLicence;
		this.carBrand = carBrand;
		this.queStatus = queStatus;
		this.numberOfItem = numberOfItem;
		this.pickAmount = pickAmount;
		this.checkoutAmount = checkoutAmount;
		this.billAmount = billAmount;
		this.createDate = createDate;
		this.createTime = createTime;
		this.isCancel = isCancel;
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


	public String getDocNo() {
		return docNo;
	}


	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}


	public int getqId() {
		return qId;
	}


	public void setqId(int qId) {
		this.qId = qId;
	}


	public String getDocDate() {
		return docDate;
	}


	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}


	public String getCarLicence() {
		return carLicence;
	}


	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
	}


	public String getCarBrand() {
		return carBrand;
	}


	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}


	public int getQueStatus() {
		return queStatus;
	}


	public void setQueStatus(int queStatus) {
		this.queStatus = queStatus;
	}


	public int getNumberOfItem() {
		return numberOfItem;
	}


	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}


	public double getPickAmount() {
		return pickAmount;
	}


	public void setPickAmount(double pickAmount) {
		this.pickAmount = pickAmount;
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


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Time getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}


	public int getIsCancel() {
		return isCancel;
	}


	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}


}
