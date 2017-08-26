package bean.request;

public class userEditBean {
	String code;
	String name; 
	String picturePath ;
	int role ; 
	String phone;
	int activeStatus;
	String remark;
	public userEditBean(String code, String name, String picturePath, int role,
			String phone, int activeStatus, String remark) {
		super();
		this.code = code;
		this.name = name;
		this.picturePath = picturePath;
		this.role = role;
		this.phone = phone;
		this.activeStatus = activeStatus;
		this.remark = remark;
	}
	
	public userEditBean() {}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
