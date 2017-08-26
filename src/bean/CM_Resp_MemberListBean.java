package bean;

public class CM_Resp_MemberListBean {
    private String arCode;
    private String arName;
    private String arAddress;
    private String arPhone;
    private String arId; 
    private double arPoint;
    
    
	public CM_Resp_MemberListBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CM_Resp_MemberListBean(String arCode, String arName,
			String arAddress, String arPhone, String arId, double arPoint) {
		super();
		this.arCode = arCode;
		this.arName = arName;
		this.arAddress = arAddress;
		this.arPhone = arPhone;
		this.arId = arId;
		this.arPoint = arPoint;
	}


	public String getArCode() {
		return arCode;
	}


	public void setArCode(String arCode) {
		this.arCode = arCode;
	}


	public String getArName() {
		return arName;
	}


	public void setArName(String arName) {
		this.arName = arName;
	}


	public String getArAddress() {
		return arAddress;
	}


	public void setArAddress(String arAddress) {
		this.arAddress = arAddress;
	}


	public String getArPhone() {
		return arPhone;
	}


	public void setArPhone(String arPhone) {
		this.arPhone = arPhone;
	}


	public String getArId() {
		return arId;
	}


	public void setArId(String arId) {
		this.arId = arId;
	}


	public double getArPoint() {
		return arPoint;
	}


	public void setArPoint(double arPoint) {
		this.arPoint = arPoint;
	}


}
