package bean;

import java.util.List;

public class IV_Resp_InUpBillingBean {
	private IV_Resp_ARInvoiceBean billHeader;
	private List<IV_Resp_ARInvoiceSubBean> billSub;
	
	public IV_Resp_InUpBillingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Resp_InUpBillingBean(IV_Resp_ARInvoiceBean billHeader,
			List<IV_Resp_ARInvoiceSubBean> billSub) {
		super();
		this.billHeader = billHeader;
		this.billSub = billSub;
	}

	public IV_Resp_ARInvoiceBean getBillHeader() {
		return billHeader;
	}

	public void setBillHeader(IV_Resp_ARInvoiceBean billHeader) {
		this.billHeader = billHeader;
	}

	public List<IV_Resp_ARInvoiceSubBean> getBillSub() {
		return billSub;
	}

	public void setBillSub(List<IV_Resp_ARInvoiceSubBean> billSub) {
		this.billSub = billSub;
	}

	
	
}
