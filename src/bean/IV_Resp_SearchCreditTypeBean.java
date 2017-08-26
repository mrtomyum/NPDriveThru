package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_SearchCreditTypeBean {
	private CT_Resp_ResponseBean response;
	private List<IV_Resp_CreditTypeBean> crdType;
	
	
	public IV_Resp_SearchCreditTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_SearchCreditTypeBean(CT_Resp_ResponseBean response,
			List<IV_Resp_CreditTypeBean> crdType) {
		super();
		this.response = response;
		this.crdType = crdType;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<IV_Resp_CreditTypeBean> getCrdType() {
		return crdType;
	}


	public void setCrdType(List<IV_Resp_CreditTypeBean> crdType) {
		this.crdType = crdType;
	}


}
