package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.SaleOrderController;

import bean.request.CT_Req_SearchBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_SearchQueueBean;
import bean.request.SO_Req_SearchSaleOrderBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_QueueDailyBean;
import bean.response.SO_Res_SaleOrderBean;
import bean.response.SO_Res_SaleOrderDetailsBean;

@Path(value="/nopadol")
public class SaleOrder {
	//
	@POST
	@Path("/saleorder2/")//1.ค้นหาเอกสารใบสั่งขายมาสร้างคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_SaleOrderBean searchSaleOrder(SO_Req_SearchSaleOrderBean req){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		SaleOrderController ctl = new SaleOrderController();
		SO_Res_SaleOrderBean so = new SO_Res_SaleOrderBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		so = ctl.SearchSaleOrder(db, req);
		return so;
	}
	
	
	@POST
	@Path("/saleorder1/")//1.ค้นหาเอกสารใบสั่งขายมาสร้างคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_SaleOrderBean searchSaleOrder1(SO_Req_SearchSaleOrderBean req){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		SaleOrderController ctl = new SaleOrderController();
		SO_Res_SaleOrderBean so = new SO_Res_SaleOrderBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		so = ctl.SearchSaleOrder(db, req);
		return so;
	}
	
	@POST
	@Path("/queue/")//2.สร้างเลขที่ คิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_GenQueueRespBean saleOrdertoInvoice(SO_Req_GenOTPSaleOrderBean req){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		SaleOrderController ctl = new SaleOrderController();
		SO_Res_GenQueueRespBean so = new SO_Res_GenQueueRespBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		//so = ctl.SaleOrderToDriveThru("SmartQ", req);
		return so;
	}
	
	@POST
	@Path("/queuelist/")//รายการคิว
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SO_Res_QueueDailyBean queuelist(SO_Req_SearchQueueBean req){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		SaleOrderController ctl = new SaleOrderController();
		SO_Res_QueueDailyBean queue = new SO_Res_QueueDailyBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		queue = ctl.listQueue("SmartQ", req);
		return queue;
	}
	
}
