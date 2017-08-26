package bean.response;

import java.util.List;

public class DT_Res_CompanyBean {
	//private CT_Resp_ResponseBean resp;
	boolean success;
	boolean error;
	String message;
	List<DT_Res_ListCompanyBean> company;
	
	
	public DT_Res_CompanyBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DT_Res_CompanyBean(boolean success, boolean error, String message,
			List<DT_Res_ListCompanyBean> company) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.company = company;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<DT_Res_ListCompanyBean> getCompany() {
		return company;
	}


	public void setCompany(List<DT_Res_ListCompanyBean> company) {
		this.company = company;
	}

}
