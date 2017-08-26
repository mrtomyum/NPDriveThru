package bean;

import java.io.File;

public class PT_Reqs_MovePictureBean {
	private String moduleType;
	private String code;
	private String fromUrl;
	private String toPath;
	
	
	public PT_Reqs_MovePictureBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PT_Reqs_MovePictureBean(String moduleType, String code,
			String fromUrl, String toPath) {
		super();
		this.moduleType = moduleType;
		this.code = code;
		this.fromUrl = fromUrl;
		this.toPath = toPath;
	}


	public String getModuleType() {
		return moduleType;
	}


	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getFromUrl() {
		return fromUrl;
	}


	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}


	public String getToPath() {
		return toPath;
	}


	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
	
}
