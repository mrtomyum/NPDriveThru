package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.ReportDriveThruController;

import bean.RP_Reqs_SearchQueBetweenDateBean;
import bean.RP_Resp_CompareDataDriveThruBean;
import bean.RP_Resp_CountQueueBean;
import bean.RP_Resp_QueueDetailsBean;
import bean.RP_Resp_QueueMasterBean;
import bean.RP_Resp_SumCheckOutDataBean;



@Path(value="/report")
public class DrivethruReport {
	
	@POST
	@Path("/queuecount")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_CountQueueBean queueCount(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_CountQueueBean dataList = new RP_Resp_CountQueueBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.CountQueue("SmartQ", search);
		
		return dataList;
	}
	
	
	@POST
	@Path("/checkout")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_SumCheckOutDataBean checkOutData(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_SumCheckOutDataBean dataList = new RP_Resp_SumCheckOutDataBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.CheckOutData("SmartQ", search);
		
		return dataList;
	}
	
	
	@POST
	@Path("/qmaster")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_QueueMasterBean queueMaster(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_QueueMasterBean dataList = new RP_Resp_QueueMasterBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.QueueMaster("SmartQ", search);
		
		return dataList;
	}
	
	
	@POST
	@Path("/qdetails")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_QueueDetailsBean queueDetails(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_QueueDetailsBean dataList = new RP_Resp_QueueDetailsBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.QueueDetails("SmartQ", search);
		
		return dataList;
	}
	
	
	@POST
	@Path("/invdetails")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_QueueDetailsBean invoiceDetails(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_QueueDetailsBean dataList = new RP_Resp_QueueDetailsBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.InvoiceDetails(search);
		
		return dataList;
	}
	
	
	@POST
	@Path("/compare")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RP_Resp_CompareDataDriveThruBean compareData(RP_Reqs_SearchQueBetweenDateBean search){
		RP_Resp_CompareDataDriveThruBean dataList = new RP_Resp_CompareDataDriveThruBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		
		dataList = rcl.compareData("SmartQ", search);
		
		return dataList;
	}

}
