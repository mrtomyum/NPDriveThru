package bean.request;

public class ReqUserRegisterBean {
	private String code ;
	private String name;
	private String password;
	private String picturePath;
	private String email;
	private int role;
	private String creatorCode;
	
	
	public ReqUserRegisterBean(String code, String name, String password,
			String picturePath, String email, int role, String creatorCode) {
		
		this.code = code;
		this.name = name;
		this.password = password;
		this.picturePath = picturePath;
		this.email = email;
		this.role = role;
		this.creatorCode = creatorCode;
	}
	
	public ReqUserRegisterBean() {}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	
	 
}
