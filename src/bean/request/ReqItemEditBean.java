package bean.request;

public class ReqItemEditBean {
	private String access_token ; 
	private String barCode;
	private String shortCode;
	private String remark ; 
	private String filePath;
	private String image_filename;
	
	
	public ReqItemEditBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReqItemEditBean(String access_token, String barCode, String shortCode, String remark, String filePath,
			String image_filename) {
		super();
		this.access_token = access_token;
		this.barCode = barCode;
		this.shortCode = shortCode;
		this.remark = remark;
		this.filePath = filePath;
		this.image_filename = image_filename;
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

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getImage_filename() {
		return image_filename;
	}

	public void setImage_filename(String image_filename) {
		this.image_filename = image_filename;
	}
	
}
