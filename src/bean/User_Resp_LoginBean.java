package bean;

public class User_Resp_LoginBean {
	private String pathPHPUpload;
	private String pathFile;
	
	
	public User_Resp_LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User_Resp_LoginBean(String pathPHPUpload, String pathFile) {
		super();
		this.pathPHPUpload = pathPHPUpload;
		this.pathFile = pathFile;
	}


	public String getPathPHPUpload() {
		return pathPHPUpload;
	}


	public void setPathPHPUpload(String pathPHPUpload) {
		this.pathPHPUpload = pathPHPUpload;
	}


	public String getPathFile() {
		return pathFile;
	}


	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
	
	
}
