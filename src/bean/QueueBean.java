package bean;

import java.util.Date;

public class QueueBean {
	private int id;
	private String docNo;
	private String docDate;
	private int status;
	private int isCancel;
	private String cancelCode;
	private String cancelDate;
	private int customerIdprivate; 
	private int warehouseId;
	private String customerCode;
	private String whCode;
	private String carLicence;
	private String creatorCode;
	private String createDate;
	private String editorCode;
	private String editDate;
	private int qId;
	
	public QueueBean() {


	}

	public QueueBean(int id, String docNo, String docDate, int status,
			int isCancel, String cancelCode, String cancelDate,
			int customerIdprivate, int warehouseId, String customerCode,
			String whCode, String carLicence, String creatorCode,
			String createDate, String editorCode, String editDate, int qId) {

		this.id = id;
		this.docNo = docNo;
		this.docDate = docDate;
		this.status = status;
		this.isCancel = isCancel;
		this.cancelCode = cancelCode;
		this.cancelDate = cancelDate;
		this.customerIdprivate = customerIdprivate;
		this.warehouseId = warehouseId;
		this.customerCode = customerCode;
		this.whCode = whCode;
		this.carLicence = carLicence;
		this.creatorCode = creatorCode;
		this.createDate = createDate;
		this.editorCode = editorCode;
		this.editDate = editDate;
		this.qId = qId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getCustomerIdprivate() {
		return customerIdprivate;
	}

	public void setCustomerIdprivate(int customerIdprivate) {
		this.customerIdprivate = customerIdprivate;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getCarLicence() {
		return carLicence;
	}

	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
	}

	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEditorCode() {
		return editorCode;
	}

	public void setEditorCode(String editorCode) {
		this.editorCode = editorCode;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	
}
