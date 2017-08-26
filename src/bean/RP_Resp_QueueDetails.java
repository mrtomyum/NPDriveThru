package bean;

import java.util.List;

public class RP_Resp_QueueDetails {
	String docYear;
	String docMonth;
	String docWeek;
	String docDate;
	String docHour;
	String createDate;
	int qId;
	String carLicence;
	String carBrand;
	int status;
	int numberOfItem;
	int isCancel;
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
	String invoiceNo;
	private String expertCode;
	private String departmentCode;
	private String departmentName;
	private String categoryCode;
	private String categoryName;
	private String secCode;
	private String secName;
	private int itemId;
	private String saleCode;
	private String saleName;
	
	public RP_Resp_QueueDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RP_Resp_QueueDetails(String docYear, String docMonth,
			String docWeek, String docDate, String docHour, String createDate,
			int qId, String carLicence, String carBrand, int status,
			int numberOfItem, int isCancel, String itemCode, String itemName,
			String unitCode, double pickQty, double checkoutQty,
			double itemAmount, double checkoutAmount, double billAmount,
			String pickerCode, String checkerCode, String pickerName,
			String checkerName, int itemCancel, String invoiceNo,
			String expertCode, String departmentCode, String departmentName,
			String categoryCode, String categoryName, String secCode,
			String secName, int itemId, String saleCode, String saleName) {
		super();
		this.docYear = docYear;
		this.docMonth = docMonth;
		this.docWeek = docWeek;
		this.docDate = docDate;
		this.docHour = docHour;
		this.createDate = createDate;
		this.qId = qId;
		this.carLicence = carLicence;
		this.carBrand = carBrand;
		this.status = status;
		this.numberOfItem = numberOfItem;
		this.isCancel = isCancel;
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
		this.invoiceNo = invoiceNo;
		this.expertCode = expertCode;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.secCode = secCode;
		this.secName = secName;
		this.itemId = itemId;
		this.saleCode = saleCode;
		this.saleName = saleName;
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

	public String getDocWeek() {
		return docWeek;
	}

	public void setDocWeek(String docWeek) {
		this.docWeek = docWeek;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getDocHour() {
		return docHour;
	}

	public void setDocHour(String docHour) {
		this.docHour = docHour;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getExpertCode() {
		return expertCode;
	}

	public void setExpertCode(String expertCode) {
		this.expertCode = expertCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	
}
