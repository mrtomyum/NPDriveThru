package bean.response;

public class CT_Res_CompanyDataBean {
	private String branch_code;
	private String wh_code;
	private String shelf_code;
	
	
	public CT_Res_CompanyDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CT_Res_CompanyDataBean(String branch_code, String wh_code,
			String shelf_code) {
		super();
		this.branch_code = branch_code;
		this.wh_code = wh_code;
		this.shelf_code = shelf_code;
	}


	public String getBranch_code() {
		return branch_code;
	}


	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}


	public String getWh_code() {
		return wh_code;
	}


	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}


	public String getShelf_code() {
		return shelf_code;
	}


	public void setShelf_code(String shelf_code) {
		this.shelf_code = shelf_code;
	}
	
	
}
