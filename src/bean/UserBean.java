package bean;

import java.util.Date;
import java.util.List;

public class UserBean {
	
	private int id;
	private String code;
	private String name;
	private String password;
	private String image_filename;
	private String email;
	private int role;
	private int activeStatus;
	private int isConfirm;
	private String creatorCode;
	private String createdateTime;
	private String lasteditorCode;
	private String lasteditdateTime;
	private String branchCode;
	private String remark;
	
	public UserBean() {}

	public UserBean(int id, String code, String name, String password,
			String image_filename, String email, int role, int activeStatus,
			int isConfirm, String creatorCode, String createdateTime,
			String lasteditorCode, String lasteditdateTime, String branchCode,
			String remark) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.password = password;
		this.image_filename = image_filename;
		this.email = email;
		this.role = role;
		this.activeStatus = activeStatus;
		this.isConfirm = isConfirm;
		this.creatorCode = creatorCode;
		this.createdateTime = createdateTime;
		this.lasteditorCode = lasteditorCode;
		this.lasteditdateTime = lasteditdateTime;
		this.branchCode = branchCode;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getImage_filename() {
		return image_filename;
	}

	public void setImage_filename(String image_filename) {
		this.image_filename = image_filename;
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

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public String getCreatedateTime() {
		return createdateTime;
	}

	public void setCreatedateTime(String createdateTime) {
		this.createdateTime = createdateTime;
	}

	public String getLasteditorCode() {
		return lasteditorCode;
	}

	public void setLasteditorCode(String lasteditorCode) {
		this.lasteditorCode = lasteditorCode;
	}

	public String getLasteditdateTime() {
		return lasteditdateTime;
	}

	public void setLasteditdateTime(String lasteditdateTime) {
		this.lasteditdateTime = lasteditdateTime;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
