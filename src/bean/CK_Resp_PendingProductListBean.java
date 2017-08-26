package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class CK_Resp_PendingProductListBean {
	private CT_Resp_ResponseBean response;
	private CK_Resp_AllSummaryBean allSummary;
	private List<CK_Resp_QueueListBean> list;
	
	
	public CK_Resp_PendingProductListBean() {
		// TODO Auto-generated constructor stub
	}


	public CK_Resp_PendingProductListBean(CT_Resp_ResponseBean response,
			CK_Resp_AllSummaryBean allSummary, List<CK_Resp_QueueListBean> list) {
		super();
		this.response = response;
		this.allSummary = allSummary;
		this.list = list;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public CK_Resp_AllSummaryBean getAllSummary() {
		return allSummary;
	}


	public void setAllSummary(CK_Resp_AllSummaryBean allSummary) {
		this.allSummary = allSummary;
	}


	public List<CK_Resp_QueueListBean> getList() {
		return list;
	}


	public void setList(List<CK_Resp_QueueListBean> list) {
		this.list = list;
	}


}
