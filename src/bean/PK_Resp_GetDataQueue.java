package bean;

public class PK_Resp_GetDataQueue {
	private String docNo;
	private int isCancel;
	private int status;
	private String carLicense;
	private String saleCode;
	private String otp_password;
	private int delivery_type;
	private int billtype;
	private int doctype;
	private int taxType;
	private int pickStatus;
	private String saleOrder;
	

	public PK_Resp_GetDataQueue() {

	}


	public PK_Resp_GetDataQueue(String docNo, int isCancel, int status,
			String carLicense, String saleCode, String otp_password,
			int delivery_type, int billtype, int doctype, int taxType,
			int pickStatus, String saleOrder) {
		super();
		this.docNo = docNo;
		this.isCancel = isCancel;
		this.status = status;
		this.carLicense = carLicense;
		this.saleCode = saleCode;
		this.otp_password = otp_password;
		this.delivery_type = delivery_type;
		this.billtype = billtype;
		this.doctype = doctype;
		this.taxType = taxType;
		this.pickStatus = pickStatus;
		this.saleOrder = saleOrder;
	}


	public String getDocNo() {
		return docNo;
	}


	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}


	public int getIsCancel() {
		return isCancel;
	}


	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getCarLicense() {
		return carLicense;
	}


	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}


	public String getSaleCode() {
		return saleCode;
	}


	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}


	public String getOtp_password() {
		return otp_password;
	}


	public void setOtp_password(String otp_password) {
		this.otp_password = otp_password;
	}


	public int getDelivery_type() {
		return delivery_type;
	}


	public void setDelivery_type(int delivery_type) {
		this.delivery_type = delivery_type;
	}


	public int getBilltype() {
		return billtype;
	}


	public void setBilltype(int billtype) {
		this.billtype = billtype;
	}


	public int getDoctype() {
		return doctype;
	}


	public void setDoctype(int doctype) {
		this.doctype = doctype;
	}


	public int getTaxType() {
		return taxType;
	}


	public void setTaxType(int taxType) {
		this.taxType = taxType;
	}


	public int getPickStatus() {
		return pickStatus;
	}


	public void setPickStatus(int pickStatus) {
		this.pickStatus = pickStatus;
	}


	public String getSaleOrder() {
		return saleOrder;
	}


	public void setSaleOrder(String saleOrder) {
		this.saleOrder = saleOrder;
	}


}
