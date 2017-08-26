package bean;

import bean.response.CT_Resp_ResponseBean;

public class CK_Resp_ManageItemBean {
	CT_Resp_ResponseBean response;
	CK_Resp_ManageItemListBean item;
	
	
	public CK_Resp_ManageItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CK_Resp_ManageItemBean(CT_Resp_ResponseBean response,
			CK_Resp_ManageItemListBean item) {
		super();
		this.response = response;
		this.item = item;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public CK_Resp_ManageItemListBean getItem() {
		return item;
	}


	public void setItem(CK_Resp_ManageItemListBean item) {
		this.item = item;
	}
	
	
}
