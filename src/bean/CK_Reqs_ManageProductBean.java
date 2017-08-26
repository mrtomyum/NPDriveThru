package bean;

public class CK_Reqs_ManageProductBean {
	private String access_token;
	private String barCode;
	private double qtyAfter;
	private int qId;
	private int isCheck;
	private int isCancel;
	
	
	public CK_Reqs_ManageProductBean() {

		// TODO Auto-generated constructor stub
	}
	
	public CK_Reqs_ManageProductBean(String access_token, String barCode,
			double qtyAfter, int qId, int isCheck, int isCancel) {

		this.access_token = access_token;
		this.barCode = barCode;
		this.qtyAfter = qtyAfter;
		this.qId = qId;
		this.isCheck = isCheck;
		this.isCancel = isCancel;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public double getQtyAfter() {
		return qtyAfter;
	}
	public void setQtyAfter(double qtyAfter) {
		this.qtyAfter = qtyAfter;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public int getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}
	
	
}
