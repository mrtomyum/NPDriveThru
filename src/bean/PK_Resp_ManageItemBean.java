package bean;

import bean.response.CT_Resp_ResponseBean;


public class PK_Resp_ManageItemBean {
	CT_Resp_ResponseBean response;
	PK_Resp_ManageItemListBean item;
	
	
	public PK_Resp_ManageItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PK_Resp_ManageItemBean(CT_Resp_ResponseBean response,
			PK_Resp_ManageItemListBean item) {
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


	public PK_Resp_ManageItemListBean getItem() {
		return item;
	}


	public void setItem(PK_Resp_ManageItemListBean item) {
		this.item = item;
	}

	
}
