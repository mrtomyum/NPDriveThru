package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class CM_Resp_SearchCustomerBean {
	private CT_Resp_ResponseBean response;
	private List<CM_Resp_MemberListBean> member;
	
	
	public CM_Resp_SearchCustomerBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CM_Resp_SearchCustomerBean(CT_Resp_ResponseBean response,
			List<CM_Resp_MemberListBean> member) {
		super();
		this.response = response;
		this.member = member;
	}



	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<CM_Resp_MemberListBean> getMember() {
		return member;
	}


	public void setMember(List<CM_Resp_MemberListBean> member) {
		this.member = member;
	}
	
	
}
