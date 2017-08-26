package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class RP_Resp_CompareDataDriveThruBean {
	CT_Resp_ResponseBean response;
	List<RP_Resp_ListCompareBean> details;
	
	
	public RP_Resp_CompareDataDriveThruBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_CompareDataDriveThruBean(CT_Resp_ResponseBean response,
			List<RP_Resp_ListCompareBean> details) {
		super();
		this.response = response;
		this.details = details;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<RP_Resp_ListCompareBean> getDetails() {
		return details;
	}


	public void setDetails(List<RP_Resp_ListCompareBean> details) {
		this.details = details;
	}
	
	
}
