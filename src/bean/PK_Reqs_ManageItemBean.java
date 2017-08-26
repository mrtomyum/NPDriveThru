package bean;

public class PK_Reqs_ManageItemBean {
	private String access_token;
	private String barCode;
	private double qtyBefore;
	private int qId;
	private int isCancel;
	private String saleCode;
	
	public PK_Reqs_ManageItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PK_Reqs_ManageItemBean(String access_token, String barCode,
			double qtyBefore, int qId, int isCancel, String saleCode) {
		super();
		this.access_token = access_token;
		this.barCode = barCode;
		this.qtyBefore = qtyBefore;
		this.qId = qId;
		this.isCancel = isCancel;
		this.saleCode = saleCode;
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

	public double getQtyBefore() {
		return qtyBefore;
	}

	public void setQtyBefore(double qtyBefore) {
		this.qtyBefore = qtyBefore;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	
	
}
