package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_SearchBankBean {
	private CT_Resp_ResponseBean response;
	private List<IV_Resp_BankBean> bank;
	
	
	public IV_Resp_SearchBankBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_SearchBankBean(CT_Resp_ResponseBean response,
			List<IV_Resp_BankBean> bank) {
		super();
		this.response = response;
		this.bank = bank;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<IV_Resp_BankBean> getBank() {
		return bank;
	}


	public void setBank(List<IV_Resp_BankBean> bank) {
		this.bank = bank;
	}


}
