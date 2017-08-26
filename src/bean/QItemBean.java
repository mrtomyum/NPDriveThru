package bean;

import java.util.Date;

public class QItemBean {
	private int id;
	private int qId;
	private String docNo;
	private int itemId;
	private String itemCode;
	private String unitCode;
	private double qty;
	private double pickQty;
	private double loadQty;
	private double checkoutQty;
	private String pickerCode;
	private String pickDate;
	private String loaderCode;
	private String loadDate;
	private String checkerCode;
	private String checkoutDate;
	private int zoneId;
	private String zoneCode;
	private int locationItemId;
	private String locationCode;
	private int isCancel;
	private String cancelCode;
	private String cancelDate;
	private int lineNumber;
	
	
	public QItemBean() {

	}


	public QItemBean(int id, int qId, String docNo, int itemId,
			String itemCode, String unitCode, double qty, double pickQty,
			double loadQty, double checkoutQty, String pickerCode,
			String pickDate, String loaderCode, String loadDate,
			String checkerCode, String checkoutDate, int zoneId,
			String zoneCode, int locationItemId, String locationCode,
			int isCancel, String cancelCode, String cancelDate, int lineNumber) {

		this.id = id;
		this.qId = qId;
		this.docNo = docNo;
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.unitCode = unitCode;
		this.qty = qty;
		this.pickQty = pickQty;
		this.loadQty = loadQty;
		this.checkoutQty = checkoutQty;
		this.pickerCode = pickerCode;
		this.pickDate = pickDate;
		this.loaderCode = loaderCode;
		this.loadDate = loadDate;
		this.checkerCode = checkerCode;
		this.checkoutDate = checkoutDate;
		this.zoneId = zoneId;
		this.zoneCode = zoneCode;
		this.locationItemId = locationItemId;
		this.locationCode = locationCode;
		this.isCancel = isCancel;
		this.cancelCode = cancelCode;
		this.cancelDate = cancelDate;
		this.lineNumber = lineNumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getqId() {
		return qId;
	}


	public void setqId(int qId) {
		this.qId = qId;
	}


	public String getDocNo() {
		return docNo;
	}


	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getUnitCode() {
		return unitCode;
	}


	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public double getQty() {
		return qty;
	}


	public void setQty(double qty) {
		this.qty = qty;
	}


	public double getPickQty() {
		return pickQty;
	}


	public void setPickQty(double pickQty) {
		this.pickQty = pickQty;
	}


	public double getLoadQty() {
		return loadQty;
	}


	public void setLoadQty(double loadQty) {
		this.loadQty = loadQty;
	}


	public double getCheckoutQty() {
		return checkoutQty;
	}


	public void setCheckoutQty(double checkoutQty) {
		this.checkoutQty = checkoutQty;
	}


	public String getPickerCode() {
		return pickerCode;
	}


	public void setPickerCode(String pickerCode) {
		this.pickerCode = pickerCode;
	}


	public String getPickDate() {
		return pickDate;
	}


	public void setPickDate(String pickDate) {
		this.pickDate = pickDate;
	}


	public String getLoaderCode() {
		return loaderCode;
	}


	public void setLoaderCode(String loaderCode) {
		this.loaderCode = loaderCode;
	}


	public String getLoadDate() {
		return loadDate;
	}


	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}


	public String getCheckerCode() {
		return checkerCode;
	}


	public void setCheckerCode(String checkerCode) {
		this.checkerCode = checkerCode;
	}


	public String getCheckoutDate() {
		return checkoutDate;
	}


	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}


	public int getZoneId() {
		return zoneId;
	}


	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}


	public String getZoneCode() {
		return zoneCode;
	}


	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}


	public int getLocationItemId() {
		return locationItemId;
	}


	public void setLocationItemId(int locationItemId) {
		this.locationItemId = locationItemId;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	public int getIsCancel() {
		return isCancel;
	}


	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}


	public String getCancelCode() {
		return cancelCode;
	}


	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}


	public String getCancelDate() {
		return cancelDate;
	}


	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}


	public int getLineNumber() {
		return lineNumber;
	}


	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}


	
}
