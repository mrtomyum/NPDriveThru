package view;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.BillingController;
import controller.getDataFromData;

import bean.IV_Reqs_BillingBean;
import bean.IV_Reqs_BillingBranchBean;
import bean.IV_Reqs_CouponBean;
import bean.IV_Reqs_InvoiceDataBean;
import bean.IV_Reqs_PrintSlipBean;
import bean.IV_Reqs_VerifyCouponBean;
import bean.IV_Resp_BillingBean;
import bean.IV_Resp_InvoiceDataBean;
import bean.IV_Resp_PrintSlipBean;
import bean.IV_Resp_SearchBankBean;
import bean.IV_Resp_SearchCreditTypeBean;
import bean.IV_Resp_VerifyCouponBean;
import bean.UserSearchBean;
import bean.request.DT_User_LoginBranchBean;
import bean.response.CT_Resp_ResponseBean;

@Path(value="/billing")
public class Billing {

	@POST
	@Path("/credittype")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_SearchCreditTypeBean searchCreditType(UserSearchBean search){
		BillingController bcl = new BillingController();
		IV_Resp_SearchCreditTypeBean response = new IV_Resp_SearchCreditTypeBean();
		
		//response = bcl.searchCreditType("nptest",search);
		response = bcl.searchCreditType("bcnp",search);
		return response;
	}

	
	@POST
	@Path("/bank")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_SearchBankBean searchBank(UserSearchBean search){
		BillingController bcl = new BillingController();
		IV_Resp_SearchBankBean response = new IV_Resp_SearchBankBean();
		
		//response = bcl.searchBank("nptest",search);
		response = bcl.searchBank("bcnp",search);
		
		return response;
	}
	
	@POST
	@Path("/done")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_BillingBean saveBilling(IV_Reqs_BillingBean reqsBill){
		IV_Resp_BillingBean billing = new IV_Resp_BillingBean();
		BillingController bcl = new BillingController();
		
		//billing = bcl.PostBilling("nptest", reqsBill);
		billing = bcl.PostBilling("bcnp", reqsBill);
		//billing = bcl.PostBillingBranch( reqsBill);
		
		return billing;
	}
	

	@POST
	@Path("/verifycoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_VerifyCouponBean verifyCoupon(IV_Reqs_VerifyCouponBean coupong){
		IV_Resp_VerifyCouponBean verify = new IV_Resp_VerifyCouponBean();
		BillingController bcl = new BillingController();
		
		//verify = bcl.verifyCoupong("nptest", coupong);
		verify = bcl.verifyCoupong("bcnp", coupong);
		//verify = bcl.verifyCoupongBranch(coupong);
		
		return verify;
	}
	
	@POST
	@Path("/printslip")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_PrintSlipBean printSlip(IV_Reqs_PrintSlipBean req){
		IV_Resp_PrintSlipBean printslip = new IV_Resp_PrintSlipBean();
		BillingController bcl = new BillingController();
		
		//printslip = bcl.printSlip("nptest",req);
		printslip = bcl.printSlip("pos",req);
		//printslip = bcl.printSlipBranch(req);
		
		return printslip;
	}
	
	
//	@POST
//	@Path("/copy")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public CT_Resp_ResponseBean copySlip(IV_Reqs_InvoiceDataBean req){
//		CT_Resp_ResponseBean copy = new CT_Resp_ResponseBean();
//		BillingController bcl = new BillingController();
//		
//		copy = bcl.testHTML("POS",req);
//		//printslip = bcl.printSlipBranch(req);
//		
//		return copy;
//	}
	
	@POST
	@Path("/invoice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IV_Resp_InvoiceDataBean invoiceData(IV_Reqs_InvoiceDataBean reqs){
		IV_Resp_InvoiceDataBean invoice = new IV_Resp_InvoiceDataBean();
		BillingController bcl = new BillingController();
		
		invoice = bcl.InvoiceDetails("192.168.0.7","bcnp", reqs);
		
		
		return invoice;
	}
	
	@POST
	@Path("/copy")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CT_Resp_ResponseBean printSlip(IV_Reqs_InvoiceDataBean req){
		CT_Resp_ResponseBean copy = new CT_Resp_ResponseBean();
		BillingController bcl = new BillingController();
		
		//copy = bcl.testHTML("nptest",req);
		copy = bcl.testHTML("bcnp",req);
		//printslip = bcl.printSlipBranch(req);
		
		return copy;
	}
}
