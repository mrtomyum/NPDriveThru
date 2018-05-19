package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.CK_Reqs_GetDataQueue;
import bean.CK_Reqs_ManageProductBean;
import bean.CK_Resp_ManageItemBean;
import bean.CK_Resp_PendingProductListBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.IV_Reqs_BillingBean;
import bean.IV_Reqs_InvoiceDataBean;
import bean.IV_Reqs_PrintSlipBean;
import bean.IV_Reqs_VerifyCouponBean;
import bean.IV_Resp_BillingBean;
import bean.IV_Resp_InvoiceDataBean;
import bean.IV_Resp_PrintSlipBean;
import bean.IV_Resp_SearchBankBean;
import bean.IV_Resp_SearchCreditTypeBean;
import bean.IV_Resp_VerifyCouponBean;
import bean.LoginResponseBean;
import bean.PK_Reqs_DeleteOrderBean;
import bean.PK_Reqs_EditOrderBean;
import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_EditOrderBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_NewOrderHeaderBean;
import bean.PK_Resp_SearchCarBrandBean;
import bean.UserSearchBean;
import bean.request.CT_Req_LoginBean;
import bean.request.CT_Req_LoginPassBean;
import bean.request.CT_Req_SearchArBean;
import bean.request.CT_Req_SearchBean;
import bean.request.CT_Req_SearchInvoiceBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.SO_Req_BillingBean;
import bean.request.SO_Req_ChangeStatusBean;
import bean.request.SO_Req_CheckOutManageProductBean;
import bean.request.SO_Req_EditQueueBean;
import bean.request.SO_Req_EditSaleOrderBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_LoadManageProductBean;
import bean.request.SO_Req_PickingManageProductBean;
import bean.request.SO_Req_QueueId;
import bean.request.SO_Req_QueueProductBean;
import bean.request.SO_Req_SearchQueueBean;
import bean.request.SO_Req_SearchQueueDataBean;
import bean.request.SO_Req_SearchSaleOrderBean;
import bean.response.CT_Res_LoginBean;
import bean.response.CT_Res_ResponseBean;
import bean.response.CT_Resp_ResponseBean;
import bean.response.DT_Res_CompanyBean;
import bean.response.QU_Res_QueueDataBean;
import bean.response.SO_Res_ARDepositBean;
import bean.response.SO_Res_BillingBean;
import bean.response.SO_Res_CarBrandBean;
import bean.response.SO_Res_ChangeStatusBean;
import bean.response.SO_Res_EditQueueBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_PickZoneBean;
import bean.response.SO_Res_PickingManageProductBean;
import bean.response.SO_Res_ProductQueueBean;
import bean.response.SO_Res_QueueDailyBean;
import bean.response.SO_Res_Response;
import bean.response.SO_Res_SaleOrderBean;
import bean.response.SO_Res_SaleOrderInvoiceBean;
import bean.response.SO_Res_SearchQueueDataBean;
import controller.BillingController;
import controller.CheckOutController;
import controller.DriveThruController;
import controller.PickupController;
import controller.SaleOrderController;

@Path(value="/v2")
public class DriveThru {
	
	//==Company and Zone=============================================================================================
	
	@POST
	@Path("/zone/")//1.รายการ บริษัทและโซนการทำงาน เพื่อระบุคลังและชั้นเก็บ
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DT_Res_CompanyBean searchCompanyZone(){
		DriveThruController ctl = new DriveThruController();
		DT_Res_CompanyBean company = new DT_Res_CompanyBean();
		
		company = ctl.searchCompanyZone("SmartConfig");
		return company;
	}
	
	//==PickZone=============================================================================================
	
	@POST
	@Path("/pick_zone/")//1.รายการ บริษัทและโซนการทำงาน เพื่อระบุคลังและชั้นเก็บ
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_PickZoneBean searchPickZone(){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_PickZoneBean pickzone = new SO_Res_PickZoneBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		pickzone = ctl.searchItemPickZone(db);
		return pickzone;
	}
	
	//==LogIn Password====================================================================================
	
	@POST
	@Path("/userlogin/")//2.login gen access token
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponseBean userlogin(CT_Req_LoginPassBean req){
		DriveThruController ctl = new DriveThruController();
		LoginResponseBean login = new LoginResponseBean();
		
		login = ctl.userlogin("SmartConfig", req);
		return login;
	}
	
	//==LogIn=============================================================================================
	
	@POST
	@Path("/login/")//2.login gen access token
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CT_Res_LoginBean login(CT_Req_LoginBean req){
		DriveThruController ctl = new DriveThruController();
		CT_Res_LoginBean login = new CT_Res_LoginBean();
		
		login = ctl.login("SmartConfig", req);
		return login;
	}
	//==SaleOrder=============================================================================================
	
	@POST
	@Path("/sale_order/")//3.ค้นหาเอกสารใบสั่งขายมาสร้างคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_SaleOrderBean searchSaleOrder(SO_Req_SearchSaleOrderBean req){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_SaleOrderBean so = new SO_Res_SaleOrderBean();
		
		db.setServerName("192.168.0.7");
		//db.setDatabaseName("nptest");
		db.setDatabaseName("bcnp");
		
		so = ctl.SearchSaleOrder(db, req);
		return so;
	}
	
	@POST
	@Path("/queue/")//4.สร้างเลขที่ คิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_GenQueueRespBean saleOrdertoInvoice(SO_Req_GenOTPSaleOrderBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_GenQueueRespBean so = new SO_Res_GenQueueRespBean();
				
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		//so = ctl.SaleOrderToDriveThru("DriveThru_Test",db, req);
		so = ctl.SaleOrderToDriveThru("SmartQ",db, req);
		return so;
	}
	
	@POST
	@Path("/list_queue/")//5.รายการคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_SearchQueueDataBean queuelist(SO_Req_SearchQueueDataBean req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_SearchQueueDataBean queue = new SO_Res_SearchQueueDataBean();
		
		
		//queue = ctl.SearchQueueList("DriveThru_Test", req);
		queue = ctl.SearchQueueList("SmartQ", req);
		return queue;
	}
	
	@POST
	@Path("/queue/product/")//6.รายการสินค้าในคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QU_Res_QueueDataBean queueproduct(SO_Req_QueueProductBean req){
		DriveThruController ctl = new DriveThruController();
		QU_Res_QueueDataBean product = new QU_Res_QueueDataBean();
		
		//product = ctl.QueueProduct("DriveThru_Test", req);
		product = ctl.QueueProduct("SmartQ", req);
		return product;
	}
	
	@POST
	@Path("/pickup/manage_product/")//7.pickup manage product
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_PickingManageProductBean pickupmanageproduct(SO_Req_PickingManageProductBean req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_PickingManageProductBean manageproduct = new SO_Res_PickingManageProductBean();
		
		//manageproduct = ctl.PickupManageProduct("DriveThru_Test", req);
		manageproduct = ctl.PickupManageProduct("SmartQ", req);
		return manageproduct;
	}
	
	@POST
	@Path("/load/manage_product/")//7.pickup manage product
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_PickingManageProductBean loadmanageproduct(SO_Req_LoadManageProductBean req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_PickingManageProductBean loadproduct = new SO_Res_PickingManageProductBean();
		
		//manageproduct = ctl.PickupManageProduct("DriveThru_Test", req);
		loadproduct = ctl.LoadManageProduct("SmartQ", req);
		return loadproduct;
	}
	
	@POST
	@Path("/checkout/manage_product/")//8.checkout manage product
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_PickingManageProductBean checkoutmanageproduct(SO_Req_CheckOutManageProductBean req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_PickingManageProductBean manageproduct = new SO_Res_PickingManageProductBean();
		
		//manageproduct = ctl.ManageProductCheckOut("DriveThru_Test", req);
		manageproduct = ctl.ManageProductCheckOut("SmartQ", req);
		return manageproduct;
	}
	
	@POST
	@Path("/queue/edit/")//9.edit queue
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_EditQueueBean checkoutmanageproduct(SO_Req_EditQueueBean req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_EditQueueBean edit = new SO_Res_EditQueueBean();
		
		//edit = ctl.editOrder("DriveThru_Test", req);
		edit = ctl.editOrder("SmartQ", req);
		return edit;
	}
	
	@POST
	@Path("/saleorder/edit/")//10.edit saleorder
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_EditQueueBean editWebSaleOrderQueue(SO_Req_EditSaleOrderBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_EditQueueBean edit = new SO_Res_EditQueueBean();
		
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		//edit = ctl.editWebSaleOrderQueue("DriveThru_Test",db, req);
		edit = ctl.editWebSaleOrderQueue("SmartQ",db, req);
		return edit;
	}
	
	@POST
	@Path("/queue/status/")//11.change status
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_ChangeStatusBean QueueChangeStatus(SO_Req_ChangeStatusBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_ChangeStatusBean change = new SO_Res_ChangeStatusBean();
		
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		change = ctl.ChangeQueueStatus("SmartQ",db, req);
		return change;
	}
	
	@POST
	@Path("/pickup/delete/")//11.cancel queue
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_Response QueueCancel(SO_Req_QueueId req){
		DriveThruController ctl = new DriveThruController();
		SO_Res_Response cancel = new SO_Res_Response();
		
		//cancel = ctl.CancelQueueDriveThru("DriveThru_Test", req);
		cancel = ctl.CancelQueueDriveThru("SmartQ", req);
		return cancel;
	}
	
	@POST
	@Path("/billing/deposit_amount/")//12.list ar deposit
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_ARDepositBean SearchARDeposit(CT_Req_SearchArBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_ARDepositBean edit = new SO_Res_ARDepositBean();
		
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		edit = ctl.SearchARDepositBalance(db,req);
		return edit;
	}
	
	@POST
	@Path("/billing/done/")//13.บันทึกบิลขาย 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_BillingBean PostBilling(SO_Req_BillingBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DT_User_LoginBranchBean pos = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_BillingBean edit = new SO_Res_BillingBean();
		
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		pos.setServerName("192.168.0.26");
		//pos.setDbName("pos_test");
		pos.setDbName("pos");
		
		//edit = ctl.PostBilling("DriveThru_Test",db,pos,req);
		edit = ctl.PostBilling("SmartQ",db,pos,req);
		return edit;
	}
	
	
	@POST
	@Path("/print/")//14.ฟอร์มบิลเงินสด-เชื่อ
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_SaleOrderInvoiceBean SaleOrderInvoiceDetails(CT_Req_SearchInvoiceBean req){
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		DriveThruController ctl = new DriveThruController();
		SO_Res_SaleOrderInvoiceBean invslip = new SO_Res_SaleOrderInvoiceBean();
		
		db.setServerName("192.168.0.7");
		//db.setDbName("nptest");
		db.setDbName("bcnp");
		
		invslip = ctl.SaleOrderInvoiceDetails(db,req);
		return invslip;
	}
	
	//==Company and Zone=============================================================================================
	
	@POST
	@Path("/carbrand/")//1.รายการ ยี่ห้อรถยนต์
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_CarBrandBean searchCarBrand(){
		DriveThruController ctl = new DriveThruController();
		SO_Res_CarBrandBean carbrand = new SO_Res_CarBrandBean();
				
		//carbrand = ctl.searchCarBrand("DriveThru_Test");
		carbrand = ctl.searchCarBrand("SmartQ");
		return carbrand;
	}
}
