package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class PK_Resp_SearchCarBrandBean {
	private CT_Resp_ResponseBean response;
	private List<PK_Resp_CarBrandBean> brand;
	
	
	public PK_Resp_SearchCarBrandBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PK_Resp_SearchCarBrandBean(CT_Resp_ResponseBean response,
			List<PK_Resp_CarBrandBean> brand) {
		super();
		this.response = response;
		this.brand = brand;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<PK_Resp_CarBrandBean> getBrand() {
		return brand;
	}


	public void setBrand(List<PK_Resp_CarBrandBean> brand) {
		this.brand = brand;
	}

}
