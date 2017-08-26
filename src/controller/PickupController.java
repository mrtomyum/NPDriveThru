package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Locale;


import bean.IV_Resp_BankBean;
import bean.IV_Resp_SearchBankBean;
import bean.LoginBean;
import bean.PK_Resp_CarBrandBean;
import bean.PK_Resp_EditOrderBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Reqs_EditOrderBean;
import bean.PK_Reqs_ManageItemBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_ManageItemListBean;
import bean.PK_Resp_NewOrderHeaderBean;
import bean.PK_Resp_OrderPendingBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.PK_Resp_SearchCarBrandBean;
import bean.SearchBean;
import bean.PK_Reqs_DeleteOrderBean;
import bean.UserSearchBean;
import bean.User_Resp_CheckDataAccessTokenBean;
import bean.orderPendingBean;
import bean.request.DT_User_LoginBranchBean;
import bean.response.CT_Resp_ResponseBean;
import connect.QueueConnect;

import controller.TestInsertPicture;

public class PickupController {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private String Textstring;
	private String vQuery;
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	PK_Resp_OrderPendingBean respPending = new PK_Resp_OrderPendingBean();
	List<PK_Resp_OrderPendingBean> orderList = new ArrayList<PK_Resp_OrderPendingBean>();
	orderPendingBean order = new orderPendingBean();
	PK_Resp_EditOrderBean editOrder = new PK_Resp_EditOrderBean();
	PK_Resp_ManageItemBean respItem = new PK_Resp_ManageItemBean();
	
	PK_Resp_NewOrderHeaderBean qIdOrder = new PK_Resp_NewOrderHeaderBean();
	GenNewDocnoController genNo = new GenNewDocnoController();
	
	List<PK_Resp_CarBrandBean> brand = new ArrayList<PK_Resp_CarBrandBean>();
	PK_Resp_SearchCarBrandBean carBrand = new PK_Resp_SearchCarBrandBean();
	
	ExcecuteController excecute = new  ExcecuteController();
	
	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
	
	getDataFromData getData = new getDataFromData();
	
	LoginBean userCode = new LoginBean();
	
	User_Resp_CheckDataAccessTokenBean branch = new User_Resp_CheckDataAccessTokenBean();
	
	PK_Resp_SaleCodeDetails sale = new PK_Resp_SaleCodeDetails();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
	
	DT_User_LoginBranchBean server = new DT_User_LoginBranchBean();
	NPSQLExecController npexec = new NPSQLExecController();
	
	private boolean isSuccess;
	private boolean isSuccessSub;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean isSuccessSub() {
		return isSuccessSub;
	}
	public void setSuccessSub(boolean isSuccessSub) {
		this.isSuccessSub = isSuccessSub;
	}
	
	
	public orderPendingBean searchOrderPending(String dbName,SearchBean keyword){
		TestInsertPicture p = new TestInsertPicture();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		int vCountToken=0;
		
		boolean checkPic;

		//checkPic = p.insertPic();
		
		vCountToken = getData.CheckAccessToken(keyword.getAccess_token());
		
		System.out.println("Token = "+vCountToken);
		
			try {
					Statement stmt = ds.getStatement(dbName);
			    
					if (keyword.getKeyword().equals("")){
						Textstring="select * from Queue where docDate = CURDATE() order by createDate ";
					}else{
						Textstring="select * from Queue where docDate = CURDATE() and (docno like'%"+keyword.getKeyword()+"%' or carLicence like '%"+keyword.getKeyword()+"%') order by createDate";
					}
					
				   	//System.out.println(new Date(System.currentTimeMillis())+" : "+Textstring);
				    
				    ResultSet rs = stmt.executeQuery(Textstring);
				    
				    int roworder=0;
				    
				    PK_Resp_OrderPendingBean evt; 
				    
				    orderList.clear();
				   	while (rs.next()) {
				   		roworder++;
				   		
				   		evt = new PK_Resp_OrderPendingBean();	
	
				   		evt.setqId(rs.getInt("qId"));
				   		evt.setCarNumber(rs.getString("carLicence"));
				   		evt.setCarBrand(rs.getString("carBrand"));
				   		evt.setNumberOfItem(rs.getInt("numberOfItem"));
				   		evt.setTimeCreate(rs.getString("createDate"));
				   		evt.setStatus(rs.getInt("status"));
				   		evt.setIscancel(rs.getInt("iscancel"));
	
		    		    orderList.add(evt);
		    		    
		    		    order.setOrder(orderList);
						
		    		    //System.out.println(order.getCarNumber());
					}
					rs.close();
					stmt.close();
					
				   	if (roworder==0) { //ค้นหาไม่พบ
		    		    order.setOrder(orderList);
				   	}  
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			    ds.close();
			}
			return order;
		}
	
	
	public orderPendingBean searchOrderPendingBranch(String dbName,SearchBean keyword){
		TestInsertPicture p = new TestInsertPicture();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		int vCountToken=0;
		
		boolean checkPic;
		
		
		//checkPic = p.insertPic();
		
		vCountToken = getData.CheckAccessToken(keyword.getAccess_token());
		branch = getData.CheckDataAccessToken(keyword.getAccess_token());
		
		//System.out.println("Token = "+vCountToken);
		
			try {
					Statement stmt = ds.getStatement(dbName);
			    
//					if (keyword.getKeyword().equals("")){
//						Textstring="select * from QueueMaster where branchCode = '"+branch.getBranchCode()+"' and docDate = CURDATE() order by createDate ";
//					}else{
//						Textstring="select * from QueueMaster where branchCode = '"+branch.getBranchCode()+"' and  docDate = CURDATE() and (docno like'%"+keyword.getKeyword()+"%' or carLicence like '%"+keyword.getKeyword()+"%') order by createDate";
//					}
					
					Textstring = "call USP_DT_SearchQueueDaily ('"+branch.getBranchCode()+"','"+keyword.getKeyword()+"')";
					
				   //	System.out.println(new Date(System.currentTimeMillis())+" : "+Textstring);
				    
				    ResultSet rs = stmt.executeQuery(Textstring);
				    
				    int roworder=0;
				    
				    PK_Resp_OrderPendingBean evt; 
				    
				    orderList.clear();
				   	while (rs.next()) {
				   		roworder++;
				   		
				   		evt = new PK_Resp_OrderPendingBean();	
	
				   		evt.setqId(rs.getInt("qId"));
				   		evt.setCarNumber(rs.getString("carLicence"));
				   		evt.setCarBrand(rs.getString("carBrand"));
				   		evt.setNumberOfItem(rs.getInt("numberOfItem"));
				   		evt.setTimeCreate(rs.getString("createDate"));
				   		evt.setStatus(rs.getInt("status"));
				   		evt.setIscancel(rs.getInt("iscancel"));
	
		    		    orderList.add(evt);
		    		    
		    		    order.setOrder(orderList);
						
		    		    //System.out.println(order.getCarNumber());
					}
					rs.close();
					stmt.close();
					
				   	if (roworder==0) { //ค้นหาไม่พบ
		    		    order.setOrder(orderList);
				   	}  
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			    ds.close();
			}
			return order;
		}
	
	
	public PK_Resp_NewOrderHeaderBean newOrder(String dbName,CT_Reqs_NewDocNoBean reqOrder){
		String docDate;
		String vGenNewDocNo = "";
		String saleCode = "";
		String saleName="";
		boolean success;
		int qId;
		int vCountToken=0;
		String vQryItem;
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		CT_Reqs_NewDocNoBean reqNewDocNo = new CT_Reqs_NewDocNoBean();
		
		vGenNewDocNo = genNo.genDocNo(0);
		qId = genNo.genqId();
		
		qIdOrder = new PK_Resp_NewOrderHeaderBean();
		
		//reqNewDocNo.setCarNumber("4712");
		
		if(reqOrder.getCarNumber()!=null && reqOrder.getCarNumber()!=""){
		
		userCode = getData.searchUserAccessToken(reqOrder.getAccess_token());
		saleCode = userCode.getEmployeeCode();
		saleName = userCode.getEmployeeName();
		
		server.setServerName("192.168.0.7");
		server.setDbName("bcnp");
		
    	vQryItem = "exec dbo.USP_NP_InsertAPILogs 'SmartQWs','pickup/new','"+reqOrder.getCarBrand()+":"+reqOrder.getCarNumber()+"','"+saleCode+"'";
     	System.out.println(vQryItem);
    	try{
    		isSuccess= npexec.executeSqlBranch(server,vQryItem);
    	}catch(Exception e){
    		isSuccess=false;
    	}
    	
		vQuery ="insert into Queue(docNo,docDate,status,isCancel,customerId,warehouseId,customerCode,whCode,carLicence,carBrand,creatorCode,createDate,qId,saleCode,saleName) values("+"'"+vGenNewDocNo+"',CURDATE(),0,0,'99999',0,'99999','S1-B','"+reqOrder.getCarNumber()+"','"+reqOrder.getCarBrand()+"','"+saleCode+"',CURRENT_TIMESTAMP,"+qId+",'"+saleCode+"','"+saleName+"')";
		//vQuery ="insert into Queue(docNo,docDate,status,isCancel,customerId,warehouseId,customerCode,whCode,carLicence,carBrand,creatorCode,createDate,qId,saleCode,saleName) values("+"'"+vGenNewDocNo+"','"+dateFormat.format(dateNow)+"',0,0,'99999',0,'99999','S1-B','"+reqOrder.getCarNumber()+"','"+reqOrder.getCarBrand()+"','"+saleCode+"',CURRENT_TIMESTAMP,"+qId+",'"+saleCode+"','"+saleName+"')";
		System.out.println(vQuery); 
		try {
				isSuccess= excecute.execute(dbName,vQuery);
				
				response.setIsSuccess(true);
				response.setProcess("newPickup");
				response.setProcessDesc("Successfull");
				qIdOrder.setqId(qId);
				qIdOrder.setResponse(response);
			} catch (Exception e) {
				response.setIsSuccess(false);
				response.setProcess("newPickup");
				response.setProcessDesc("Error : "+e.getMessage());
				qIdOrder.setqId(0);
				qIdOrder.setResponse(response);
			}finally{
				ds.close();
			}
		}else{
			
			response.setIsSuccess(false);
			
			response.setProcess("newPickup");
			response.setProcessDesc("Error : No Have CarLicence");
			qIdOrder.setqId(0);
			qIdOrder.setResponse(response);
		}
		return qIdOrder;
	}
	
	
	public PK_Resp_NewOrderHeaderBean newOrderBranch(String dbName,CT_Reqs_NewDocNoBean reqOrder){
		String docDate;
		String vGenNewDocNo = "";
		String saleCode = "";
		String saleName="";
		boolean success;
		int qId;
		int vCountToken=0;
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		CT_Reqs_NewDocNoBean reqNewDocNo = new CT_Reqs_NewDocNoBean();
		branch = getData.CheckDataAccessToken(reqOrder.getAccess_token());
		vGenNewDocNo = genNo.genDocNoBranch(0,branch.getBranchCode());
		
		qId = genNo.genqIdBranch(branch.getBranchCode());
		
		qIdOrder = new PK_Resp_NewOrderHeaderBean();
		
		//reqNewDocNo.setCarNumber("4712");
		
		if(reqOrder.getCarNumber()!=null && reqOrder.getCarNumber()!=""){
		
		userCode = getData.searchUserAccessToken(reqOrder.getAccess_token());
		saleCode = userCode.getEmployeeCode();
		saleName = userCode.getEmployeeName();
		//vQuery ="insert into QueueMaster(docNo,docDate,branchCode,status,isCancel,customerId,warehouseId,customerCode,whCode,carLicence,carBrand,creatorCode,createDate,qId,saleCode,saleName) values("+"'"+vGenNewDocNo+"',CURDATE(),'"+branch.getBranchCode()+"',0,0,'99999',0,'99999','"+branch.getWhCode()+"','"+reqOrder.getCarNumber()+"','"+reqOrder.getCarBrand()+"','"+saleCode+"',CURRENT_TIMESTAMP,"+qId+",'"+saleCode+"','"+saleName+"')";
		vQuery = "call USP_DT_InsertQueueMaster ('"+vGenNewDocNo+"','"+branch.getBranchCode()+"','"+branch.getWhCode()+"','"+reqOrder.getCarNumber()+"','"+reqOrder.getCarBrand()+"','"+saleCode+"','"+saleName+"',"+qId+")";
		System.out.println(vQuery); 
		try {
				isSuccess= excecute.execute(dbName,vQuery);
				
				response.setIsSuccess(true);
				response.setProcess("newPickup");
				response.setProcessDesc("Successfull");
				qIdOrder.setqId(qId);
				qIdOrder.setResponse(response);
			} catch (Exception e) {
				response.setIsSuccess(false);
				response.setProcess("newPickup");
				response.setProcessDesc("Error : "+e.getMessage());
				qIdOrder.setqId(0);
				qIdOrder.setResponse(response);
			}finally{
				ds.close();
			}
		}else{
			
			response.setIsSuccess(false);
			
			response.setProcess("newPickup");
			response.setProcessDesc("Error : No Have CarLicence");
			qIdOrder.setqId(0);
			qIdOrder.setResponse(response);
		}
		return qIdOrder;
	}
	
	
	public PK_Resp_EditOrderBean  editOrder(String dbName,PK_Reqs_EditOrderBean reqEdit){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		
		getQueue = getData.searchQueue(reqEdit.getqId());
		int vCountToken =0;
		
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		
		if (reqEdit.getCarNumber()!=null && reqEdit.getCarNumber()!=""){
			if(getQueue.getIsCancel()==0){
				
				if(getQueue.getStatus()!=3){
					
					userCode = getData.searchUserAccessToken(reqEdit.getAccess_token());
					
					System.out.println("รหัสพนักงานคือ "+reqEdit.getSaleCode());
					
					creatorCode = userCode.getEmployeeCode();
					
					if (reqEdit.getSaleCode()=="" || reqEdit.getSaleCode() == null){
						saleCode = userCode.getEmployeeCode();
						saleName = userCode.getEmployeeName();
						
						if (reqEdit.getStatus()!=2){
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getqId();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}else{
							vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = CURDATE() and qid ="+reqEdit.getqId();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}
					}else{
						
						sale = getData.searchSaleCode(reqEdit.getSaleCode());
						if (sale.getIsExist()==1){
							saleCode = sale.getSaleCode();
							saleName = sale.getSaleName();
						}else{
							saleCode = "N/A";
							saleName = "-";
						}
						
						if (reqEdit.getStatus()!=2){
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getqId();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}else{
							vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = CURDATE() and qid ="+reqEdit.getqId();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}
						
					}
					
					System.out.println("รหัสพนักงานใหม่คือ "+saleCode);

					//vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+saleCode+"',editDate = CURRENT_TIMESTAMP where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
					System.out.println(vQuery); 
					try {
							isSuccess= excecute.execute(dbName,vQuery);
							response.setIsSuccess(true);
							response.setProcess("editPickup");
							response.setProcessDesc("Successfull");
							editOrder.setResponse(response);
							
						} catch (Exception e) {
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("editPickup");
							response.setProcessDesc("Error : "+e.getMessage());
							editOrder.setResponse(response);
						}	finally{
							ds.close();
						}
				}else{
					isSuccess=false;
					response.setIsSuccess(false);
					response.setProcess("editPickup");
					response.setProcessDesc("Error : Queue status is used");
					editOrder.setResponse(response);
				}

			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("editPickup");
				response.setProcessDesc("Error : Queue is cancel");
				editOrder.setResponse(response);			
			}
			
			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("editPickup");
				response.setProcessDesc("Error : No Have CarLicence");
				editOrder.setResponse(response);
			}
		
		
		return editOrder;
	}
	
	
	public PK_Resp_EditOrderBean  editOrderBranch(String dbName,PK_Reqs_EditOrderBean reqEdit){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		branch = getData.CheckDataAccessToken(reqEdit.getAccess_token());
		getQueue = getData.searchQueue(reqEdit.getqId());
		int vCountToken =0;
		
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		
		if (reqEdit.getCarNumber()!=null && reqEdit.getCarNumber()!=""){
			if(getQueue.getIsCancel()==0){
				
				if(getQueue.getStatus()!=3){
					
					userCode = getData.searchUserAccessToken(reqEdit.getAccess_token());
					
					System.out.println("รหัสพนักงานคือ "+reqEdit.getSaleCode());
					
					creatorCode = userCode.getEmployeeCode();
					
					if (reqEdit.getSaleCode()=="" || reqEdit.getSaleCode() == null){
						saleCode = userCode.getEmployeeCode();
						saleName = userCode.getEmployeeName();
						
						if (reqEdit.getStatus()!=2){
							//vQuery ="update QueueMaster set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where branchCode = '"+branch.getBranchCode()+"' and docDate = CURDATE() and qid ="+reqEdit.getqId();
							vQuery ="call USP_DT_UpdateDataQueueMaster ( "+reqEdit.getqId()+",'"+branch.getBranchCode()+"',"+reqEdit.getStatus()+",'"+reqEdit.getCarNumber()+"','"+reqEdit.getCarBrand()+"','"+creatorCode+"','"+saleCode+"','"+saleName+"')";
						}else{
							//vQuery ="update QueueMaster set status = "+reqEdit.getStatus()+" where branchCode = '"+branch.getBranchCode()+"' and docDate = CURDATE() and qid ="+reqEdit.getqId();
							vQuery ="call USP_DT_UpdateStatusQueueMaster ( "+reqEdit.getqId()+",'"+branch.getBranchCode()+"',"+reqEdit.getStatus()+")";
						}
					}else{
						
						sale = getData.searchSaleCode(reqEdit.getSaleCode());
						if (sale.getIsExist()==1){
							saleCode = sale.getSaleCode();
							saleName = sale.getSaleName();
						}else{
							saleCode = "N/A";
							saleName = "-";
						}
						
						if (reqEdit.getStatus()!=2){
							//vQuery ="update QueueMaster set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where branchCode = '"+ branch.getBranchCode()+"' and docDate = CURDATE() and qid ="+reqEdit.getqId();
							vQuery ="call USP_DT_UpdateDataQueueMaster ( "+reqEdit.getqId()+",'"+branch.getBranchCode()+"',"+reqEdit.getStatus()+",'"+reqEdit.getCarNumber()+"','"+reqEdit.getCarBrand()+"','"+creatorCode+"','"+saleCode+"','"+saleName+"')";
						}else{
							//vQuery ="update QueueMaster set status = "+reqEdit.getStatus()+" where branchCode = '"+branch.getBranchCode()+"' and docDate = CURDATE() and qid ="+reqEdit.getqId();
							vQuery ="call USP_DT_UpdateStatusQueueMaster ( "+reqEdit.getqId()+",'"+branch.getBranchCode()+"',"+reqEdit.getStatus()+")";
						}
						
					}
					
					System.out.println("รหัสพนักงานใหม่คือ "+saleCode);

					//vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+saleCode+"',editDate = CURRENT_TIMESTAMP where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
					System.out.println(vQuery); 
					try {
							isSuccess= excecute.execute(dbName,vQuery);
							response.setIsSuccess(true);
							response.setProcess("editPickup");
							response.setProcessDesc("Successfull");
							editOrder.setResponse(response);
							
						} catch (Exception e) {
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("editPickup");
							response.setProcessDesc("Error : "+e.getMessage());
							editOrder.setResponse(response);
						}	finally{
							ds.close();
						}
				}else{
					isSuccess=false;
					response.setIsSuccess(false);
					response.setProcess("editPickup");
					response.setProcessDesc("Error : Queue status is used");
					editOrder.setResponse(response);
				}

			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("editPickup");
				response.setProcessDesc("Error : Queue is cancel");
				editOrder.setResponse(response);			
			}
			
			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("editPickup");
				response.setProcessDesc("Error : No Have CarLicence");
				editOrder.setResponse(response);
			}
		
		
		return editOrder;
	}
		

	public PK_Resp_EditOrderBean deleteOrder(String dbName,PK_Reqs_DeleteOrderBean delOrder){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();	
		String vQuerySub;
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken=0;
		
		String saleCode = "";
		
		
		if (delOrder.getqId() != 0){
			
			getQueue = getData.searchQueue(delOrder.getqId());
			
			if (getQueue.getIsCancel()==0){
				
				if(getQueue.getStatus() < 3 ){
					
					userCode = getData.searchUserAccessToken(delOrder.getAccess_token());
					saleCode = userCode.getEmployeeCode();
					
					//System.out.println("moooooooooooooooooooooooooooooooooooooooooooooooooo");
					vQuery ="update Queue set isCancel = 1,cancelCode='"+ saleCode+"',cancelDate = CURRENT_TIMESTAMP where  docDate = CURDATE() and status <> 3 and qid ="+delOrder.getqId();
					//vQuery ="update Queue set isCancel = 1,cancelCode='"+ saleCode+"',cancelDate = CURRENT_TIMESTAMP where  docDate ='"+dateFormat.format(dateNow)+"' and status <> 3 and qid ="+delOrder.getqId();
					System.out.println("Delete :"+vQuery); 
					try {
							isSuccess= excecute.execute(dbName,vQuery);
							
							vQuerySub ="update QItem set isCancel = 1,cancelCode='"+saleCode+"',cancelDate = CURRENT_TIMESTAMP where docDate = CURDATE() and isCheckOut = 0 and qid ="+delOrder.getqId();
							//vQuerySub ="update QItem set isCancel = 1,cancelCode='"+saleCode+"',cancelDate = CURRENT_TIMESTAMP where docDate ='"+dateFormat.format(dateNow)+"' and isCheckOut = 0 and qid ="+delOrder.getqId();
							isSuccess= excecute.execute(dbName,vQuerySub);
							
							response.setIsSuccess(true);
							response.setProcess("delPickup");
							response.setProcessDesc("Successfull");
							editOrder.setResponse(response);
						} catch (Exception e) {
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("delPickup");
							response.setProcessDesc("Error : "+e.getMessage());
							editOrder.setResponse(response);
						}finally{
							ds.close();
						}
				}else{
					isSuccess=false;
					response.setIsSuccess(false);
					response.setProcess("delPickup");
					response.setProcessDesc("Error : Queue status is uesd");
					editOrder.setResponse(response);
				}
					
				
			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("delPickup");
				response.setProcessDesc("Error : Queue is cancel");
				editOrder.setResponse(response);
			}

			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("delPickup");
				response.setProcessDesc("Error : No Have Queue ID");
				editOrder.setResponse(response);
			}
		
		System.out.println("Update :"+response.getProcessDesc()+" this que is "+getQueue.getStatus());
		return editOrder;
	}
	
	
	public PK_Resp_EditOrderBean deleteOrderBranch(String dbName,PK_Reqs_DeleteOrderBean delOrder){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();	
		String vQuerySub;
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken=0;
		
		String saleCode = "";
		
		
		if (delOrder.getqId() != 0){
			
			branch = getData.CheckDataAccessToken(delOrder.getAccess_token());
			getQueue = getData.searchQueue(delOrder.getqId());
			
			if (getQueue.getIsCancel()==0){
				
				if(getQueue.getStatus() < 3 ){
					
					userCode = getData.searchUserAccessToken(delOrder.getAccess_token());
					saleCode = userCode.getEmployeeCode();
					
					//System.out.println("moooooooooooooooooooooooooooooooooooooooooooooooooo");
					//vQuery ="update QueueMaster set isCancel = 1,cancelCode='"+ saleCode+"',cancelDate = CURRENT_TIMESTAMP where  branchCode ='"+branch.getBranchCode()+"' and docDate = CURDATE() and status <> 3 and qid ="+delOrder.getqId();
					vQuery = "call USP_DT_CancelQueueMaster ("+delOrder.getqId()+",'"+branch.getBranchCode()+"','"+saleCode+"')";
					System.out.println("Delete :"+vQuery); 
					try {
							isSuccess= excecute.execute(dbName,vQuery);
							
							//vQuerySub ="update QueueSub set isCancel = 1,cancelCode='"+saleCode+"',cancelDate = CURRENT_TIMESTAMP where branchCode='"+branch.getBranchCode()+"' and docDate =CURDATE() and isCheckOut = 0 and qid ="+delOrder.getqId();
							vQuerySub = "call USP_DT_CancelQueueSub ("+delOrder.getqId()+",'"+branch.getBranchCode()+"','"+saleCode+"')";
							isSuccess= excecute.execute(dbName,vQuerySub);
							
							response.setIsSuccess(true);
							response.setProcess("delPickup");
							response.setProcessDesc("Successfull");
							editOrder.setResponse(response);
						} catch (Exception e) {
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("delPickup");
							response.setProcessDesc("Error : "+e.getMessage());
							editOrder.setResponse(response);
						}finally{
							ds.close();
						}
				}else{
					isSuccess=false;
					response.setIsSuccess(false);
					response.setProcess("delPickup");
					response.setProcessDesc("Error : Queue status is uesd");
					editOrder.setResponse(response);
				}
					
				
			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("delPickup");
				response.setProcessDesc("Error : Queue is cancel");
				editOrder.setResponse(response);
			}

			}else{
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("delPickup");
				response.setProcessDesc("Error : No Have Queue ID");
				editOrder.setResponse(response);
			}
		
		System.out.println("Update :"+response.getProcessDesc()+" this que is "+getQueue.getStatus());
		return editOrder;
	}
	
	
	public PK_Resp_ManageItemBean insertItemPickup(String dbName,PK_Reqs_ManageItemBean reqItem){
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		PK_Resp_ManageItemListBean itemList = new PK_Resp_ManageItemListBean();
		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
		
		LoginBean userCode = new LoginBean();
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken = 0 ;
		
		System.out.println("MangeItem");
		
		if (reqItem.getqId()!=0){
			if (reqItem.getBarCode()!=null && reqItem.getBarCode()!=""){
				System.out.println("1");
					getQueue = getData.searchQueue(reqItem.getqId());
					
					if (getQueue.getIsCancel()==0){
						
						System.out.println("Error : "+getQueue.getStatus());
						
						if(getQueue.getStatus() < 2) {
							getBarData = getData.searchItemCode(reqItem.getBarCode());
							userCode = getData.searchUserAccessToken(reqItem.getAccess_token());
							vCheckExistItem = getData.checkItemExistQueue(reqItem);
							itemPrice = getData.searchItemPrice(getBarData.getCode(),reqItem.getBarCode(), getBarData.getUnitCode());
							
							creatorCode = userCode.getEmployeeCode();
							
							if (reqItem.getSaleCode() == "" || reqItem.getSaleCode() == null) {
								//lastSale = getData.searchTopSaleCode(reqItem.getqId());
								saleCode = userCode.getEmployeeCode();//lastSale.getSaleCode();
								saleName = userCode.getEmployeeName();//lastSale.getSaleName();
								
								System.out.println("No Have SaleCode");
								
								
							}else{
								sale = getData.searchSaleCode(reqItem.getSaleCode());
								System.out.println("Have SaleCode");
								if (sale.getIsExist()==1){
									saleCode = sale.getSaleCode();
									saleName = sale.getSaleName();
								}else{
									saleCode = "N/A";
									saleName = "-";
								}
								
							}
							
							itemAmount = itemPrice*reqItem.getQtyBefore();
							
							System.out.println("SaleName : "+saleCode+"/"+saleName);
							
							//System.out.println("ItemAmount : "+itemAmount);
							
							
							if (getBarData.getCode()!=null && getBarData.getCode()!=""){

							if (vCheckExistItem==0){
								vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,unitCode,barCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost) "+ "values("
									+reqItem.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getBarCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
									//+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+dateFormat.format(dateNow)+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getBarCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
							}else{
								if(reqItem.getIsCancel()==0 && reqItem.getQtyBefore()!= 0){
									vQuery = "update QItem set qty ="+reqItem.getQtyBefore()+",pickQty="+reqItem.getQtyBefore()+",price ="+itemPrice+",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"' where qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getBarCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
								}else{
									vQuery = "update QItem set qty=0,pickQty = 0,itemAmount=0,isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getBarCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
								}
								
								System.out.println("Update Cancel :"+vQuery);
								
							}
							System.out.println(vQuery);

							try{
								isSuccess= excecute.execute(dbName,vQuery);
								
								vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+getQueue.getDocNo()+"') where docNo ='"+getQueue.getDocNo()+"'";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								itemList.setBarCode(reqItem.getBarCode());
								itemList.setFilePath(getData.getItemFilePath(reqItem.getBarCode()));
								itemList.setItemCategory(getBarData.getCategoryCode());
								itemList.setItemCode(getBarData.getCode());
								itemList.setItemName(getBarData.getItemName());
								itemList.setItemPrice(itemPrice);
								itemList.setRemark("");
								itemList.setShortCode(reqItem.getBarCode());
								itemList.setUnitCode(getBarData.getUnitCode());
								
								response.setIsSuccess(true);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Successfull");
								
								respItem.setItem(itemList);
								respItem.setResponse(response);
								
							}catch(Exception e){
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Error : "+e.getMessage());
								
								respItem.setItem(itemList);
								respItem.setResponse(response);
							}finally{
								ds.close();
							}
							}else{
								
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Error : "+"No have barcode");

								respItem.setItem(itemList);
								respItem.setResponse(response);
							}
						}else{
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("insertItemPickup");
							response.setProcessDesc("Error : Queue status is used");

							respItem.setItem(itemList);
							respItem.setResponse(response);
						}
						
					}else{
						isSuccess=false;
						response.setIsSuccess(false);
						response.setProcess("insertItemPickup");
						response.setProcessDesc("Error : Queue is cancel");

						respItem.setItem(itemList);
						respItem.setResponse(response);
					}
			}else{
				//==============================
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("insertItemPickup");
				response.setProcessDesc("Error : Barcode is null");

				respItem.setItem(itemList);
				respItem.setResponse(response);
			}
		}else{
			//===================================
			isSuccess=false;
			response.setIsSuccess(false);
			response.setProcess("insertItemPickup");
			response.setProcessDesc("Error : qID not generate");

			respItem.setItem(itemList);
			respItem.setResponse(response);
		}
		
		return respItem;
		
	}
	
	
	
	public PK_Resp_ManageItemBean insertItemPickupBranch(String dbName,PK_Reqs_ManageItemBean reqItem){
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		PK_Resp_ManageItemListBean itemList = new PK_Resp_ManageItemListBean();
		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
		
		LoginBean userCode = new LoginBean();
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken = 0 ;
		
		branch = getData.CheckDataAccessToken(reqItem.getAccess_token());
		
		
		if (reqItem.getqId()!=0){
			if (reqItem.getBarCode()!=null && reqItem.getBarCode()!=""){
				System.out.println("1");
					getQueue = getData.searchQueueBranch(dbName,reqItem.getqId(),branch.getBranchCode());
					
					if (getQueue.getIsCancel()==0){
						
						System.out.println("Error : "+getQueue.getStatus());
						
						if(getQueue.getStatus() < 2) {
							getBarData = getData.searchItemCode(reqItem.getBarCode());
							userCode = getData.searchUserAccessToken(reqItem.getAccess_token());
							vCheckExistItem = getData.checkItemExistQueueBranch(branch.getBranchCode(),reqItem);
							itemPrice = getData.searchItemPrice(getBarData.getCode(),reqItem.getBarCode(), getBarData.getUnitCode());
							
							creatorCode = userCode.getEmployeeCode();
							
							if (reqItem.getSaleCode() == "" || reqItem.getSaleCode() == null) {
								//lastSale = getData.searchTopSaleCode(reqItem.getqId());
								saleCode = userCode.getEmployeeCode();//lastSale.getSaleCode();
								saleName = userCode.getEmployeeName();//lastSale.getSaleName();
								
								System.out.println("No Have SaleCode");
								
								
							}else{
								sale = getData.searchSaleCode(reqItem.getSaleCode());
								System.out.println("Have SaleCode");
								if (sale.getIsExist()==1){
									saleCode = sale.getSaleCode();
									saleName = sale.getSaleName();
								}else{
									saleCode = "N/A";
									saleName = "-";
								}
								
							}
							
							itemAmount = itemPrice*reqItem.getQtyBefore();
							
							System.out.println("SaleName : "+saleCode+"/"+saleName);
							
							//System.out.println("ItemAmount : "+itemAmount);
							
							
							if (getBarData.getCode()!=null && getBarData.getCode()!=""){

							if (vCheckExistItem==0){
								//vQuery = "insert into QueueSub(qId,docNo,docDate,branchcode,itemCode,itemName,unitCode,barCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost) "+ "values("
									//+reqItem.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+branch.getBranchCode()+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getBarCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
								vQuery = "call USP_DT_InsertQueueSub ("+reqItem.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+branch.getBranchCode()+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getBarCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+",0 )";
							}else{
								if(reqItem.getIsCancel()==0 && reqItem.getQtyBefore()!= 0){
									//vQuery = "update QueueSub set qty ="+reqItem.getQtyBefore()+",pickQty="+reqItem.getQtyBefore()+",price ="+itemPrice+",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"' where branchCode = '"+branch.getBranchCode()+"' and qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getBarCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
									vQuery = "call USP_DT_UpdateDataQueueSub ("+reqItem.getQtyBefore()+","+itemPrice+","+itemAmount+",'"+saleCode+"','"+saleName+"','"+branch.getBranchCode()+"',"+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+getBarData.getCode()+"','"+reqItem.getBarCode()+"','"+getBarData.getUnitCode()+"')";
								}else{
									//vQuery = "update QueueSub set qty=0,pickQty = 0,itemAmount=0,isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where branchCode = '"+branch.getBranchCode()+"' and qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getBarCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
									vQuery = "call USP_DT_CancelQueueSubItem ('"+creatorCode+"','"+branch.getBranchCode()+"',"+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+getBarData.getCode()+"','"+reqItem.getBarCode()+"','"+getBarData.getUnitCode()+"')";
								}
								
								System.out.println("Update Cancel :"+vQuery);
								
							}
							System.out.println(vQuery);

							try{
								isSuccess= excecute.execute(dbName,vQuery);
								
								//vQuerySub = "update  QueueMaster set numberofitem = (select count(itemCode) as countItem from QueueSub where branchCode = '"+branch.getBranchCode()+"' and docNo = '"+getQueue.getDocNo()+"') where branchCode = '"+branch.getBranchCode()+"' and docNo ='"+getQueue.getDocNo()+"'";
								vQuerySub = "call USP_DT_UpdateCountItemBill ('"+branch.getBranchCode()+"','"+getQueue.getDocNo()+"')";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								itemList.setBarCode(reqItem.getBarCode());
								itemList.setFilePath(getData.getItemFilePath(reqItem.getBarCode()));
								itemList.setItemCategory(getBarData.getCategoryCode());
								itemList.setItemCode(getBarData.getCode());
								itemList.setItemName(getBarData.getItemName());
								itemList.setItemPrice(itemPrice);
								itemList.setRemark("");
								itemList.setShortCode(reqItem.getBarCode());
								itemList.setUnitCode(getBarData.getUnitCode());
								
								response.setIsSuccess(true);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Successfull");
								
								respItem.setItem(itemList);
								respItem.setResponse(response);
								
							}catch(Exception e){
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Error : "+e.getMessage());
								
								respItem.setItem(itemList);
								respItem.setResponse(response);
							}finally{
								ds.close();
							}
							}else{
								
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("insertItemPickup");
								response.setProcessDesc("Error : "+"No have barcode");

								respItem.setItem(itemList);
								respItem.setResponse(response);
							}
						}else{
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("insertItemPickup");
							response.setProcessDesc("Error : Queue status is used");

							respItem.setItem(itemList);
							respItem.setResponse(response);
						}
						
					}else{
						isSuccess=false;
						response.setIsSuccess(false);
						response.setProcess("insertItemPickup");
						response.setProcessDesc("Error : Queue is cancel");

						respItem.setItem(itemList);
						respItem.setResponse(response);
					}
			}else{
				//==============================
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("insertItemPickup");
				response.setProcessDesc("Error : Barcode is null");

				respItem.setItem(itemList);
				respItem.setResponse(response);
			}
		}else{
			//===================================
			isSuccess=false;
			response.setIsSuccess(false);
			response.setProcess("insertItemPickup");
			response.setProcessDesc("Error : qID not generate");

			respItem.setItem(itemList);
			respItem.setResponse(response);
		}
		
		return respItem;
		
	}
	
	
	
	public PK_Resp_SearchCarBrandBean searchCarBrand(String dbName,UserSearchBean search){
		int countrow=0;
		
		try{
			Statement stmt = ds.getStatement(dbName);
			PK_Resp_CarBrandBean evt;
			PK_Resp_CarBrandBean evt1;
			
			if (search.getKeyword()== null || search.getKeyword()==""){
				//vQuery = "select distinct carbrand from CarBrand order by carBrand";
				vQuery = "call USP_DT_SearchCarBrand ('"+search.getKeyword()+"')";
			}else{
				//vQuery = "select distinct carbrand from CarBrand  where carBrand like '%"+search.getKeyword()+"%'order by carBrand";
				vQuery = "call USP_DT_SearchCarBrand ('"+search.getKeyword()+"')";
			}
			System.out.println(vQuery);
			ResultSet rs = stmt.executeQuery(vQuery);
			
			brand.clear();
			while(rs.next()){
				countrow++;
				
				evt = new PK_Resp_CarBrandBean();
				evt.setName(rs.getString("carBrand"));
				brand.add(evt);
			}
			
			response.setIsSuccess(true);
			response.setProcess("Search Brand");
			response.setProcessDesc("Successfully");
			carBrand.setResponse(response);
			carBrand.setBrand(brand);
			
			if (countrow==0){
				evt1 = new PK_Resp_CarBrandBean();
				brand.add(evt1);
				
				response.setIsSuccess(true);
				response.setProcess("Search Brand");
				response.setProcessDesc("No list brand");
				carBrand.setResponse(response);
			}
			
		    rs.close();
		    stmt.close();
		    
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		return carBrand;
	}

	public CT_Resp_ResponseBean updateQueueStatus(String dbName,PK_Reqs_EditOrderBean req){
		try{
			Statement stmt = ds.getStatement(dbName);

			vQuery = "call USP_DT_UpdateQueuePickStatus ("+req.getqId()+","+req.getStatus()+")";
			
			System.out.println(vQuery);
			ResultSet rs = stmt.executeQuery(vQuery);
			
			
			response.setIsSuccess(true);
			response.setProcess("Update Queue PickStatus");
			response.setProcessDesc("Successfully");
			
		    
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		
		return response;
	}
	
}



