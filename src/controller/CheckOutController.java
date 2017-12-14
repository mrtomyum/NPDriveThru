package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.QueueConnect;

import bean.CK_Reqs_GetDataQueue;
import bean.CK_Reqs_ManageProductBean;
import bean.CK_Resp_AllSummaryBean;
import bean.CK_Resp_ManageItemBean;
import bean.CK_Resp_ManageItemListBean;
import bean.CK_Resp_QueueListBean;
import bean.LoginBean;
import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_EditOrderBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_ManageItemListBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.User_Resp_CheckDataAccessTokenBean;

import bean.CK_Resp_PendingProductListBean;
import bean.response.CT_Resp_ResponseBean;

public class CheckOutController {
	private QueueConnect ds = QueueConnect.INSTANCE;
	CK_Reqs_GetDataQueue reqDataQueue = new CK_Reqs_GetDataQueue();
	CK_Resp_PendingProductListBean resPending =new CK_Resp_PendingProductListBean();
	getDataFromData getData = new getDataFromData();
	ExcecuteController ex = new ExcecuteController();
	private boolean isSuccess;
	private String vQuery;
	
	CK_Resp_ManageItemBean editOrder = new CK_Resp_ManageItemBean();
	CK_Resp_ManageItemListBean itemList = new CK_Resp_ManageItemListBean();
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	CK_Resp_AllSummaryBean allSum = new CK_Resp_AllSummaryBean();
	List<CK_Resp_QueueListBean> list = new ArrayList<CK_Resp_QueueListBean>(); 
	
	ExcecuteController excecute = new  ExcecuteController();
	
	CK_Resp_ManageItemListBean respItem = new CK_Resp_ManageItemListBean();
	
	LoginBean userCode = new LoginBean();
	
	PK_Resp_SaleCodeDetails sale = new PK_Resp_SaleCodeDetails();
	
	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	User_Resp_CheckDataAccessTokenBean branch = new User_Resp_CheckDataAccessTokenBean();
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	
	public CK_Resp_PendingProductListBean searchDataQueue(String dbName,CK_Reqs_GetDataQueue reqQueue){
		int vCountToken = 0;
		
		
		if (reqQueue.getqId()!=0){
			allSum = getData.searchQueueSummary(reqQueue.getqId());
			try{
				
				Statement st = ds.getStatement("DriveThru_Test");
				vQuery="select a.docNo,a.docDate,a.carLicence,a.customerCode,a.qId,b.itemCode,b.itemName as barcodeName,b.unitCode, "+
						" b.qty,ifnull(b.reqQty,0) as reqQty,b.pickQty,b.checkoutqty,b.rate1,b.price,b.itemAmount,concat(b.salecode,'/',b.salename) as pickercode,b.isCheckOut,b.checkoutAmount,b.isCancel,b.barcode, "+
						" (select sum(itemAmount) from QItem where docno = a.docNo) as totalamount "+
						" from 	Queue a "+
						" inner join QItem b on a.docno = b.docNo "+
						" where a.qId = '"+reqQueue.getqId()+"' and a.docDate = CURRENT_DATE() order by b.pickDate desc";
				
				ResultSet rs = st.executeQuery(vQuery);
				
				System.out.println("checkout : "+vQuery);
				
				int roworder=0;
				
				list.clear();
				
				CK_Resp_QueueListBean evt;
				
				while(rs.next()){
					
					roworder=roworder++;
					evt = new CK_Resp_QueueListBean();
					evt.setBarCode(rs.getString("barcode"));
					evt.setItemCode(rs.getString("itemCode"));
					evt.setFilePath(getData.getItemFilePath(rs.getString("itemCode")));
					evt.setItemName(rs.getString("barcodeName"));
					evt.setPricePerUnit(rs.getDouble("price"));
					evt.setQtyBefore(rs.getDouble("pickQty"));
					evt.setQtyAfter(rs.getDouble("checkoutQty"));
					evt.setTotalPriceBefore(rs.getDouble("itemAmount"));
					evt.setTotalPriceAfter(rs.getDouble("checkoutAmount"));
					evt.setUnitCode(rs.getString("unitCode"));
					evt.setPickupStaffName(rs.getString("pickercode"));
					evt.setIsCheck(rs.getInt("isCheckOut"));
					evt.setIsCancel(rs.getInt("isCancel"));
					evt.setRequest_qty(rs.getDouble("reqQty"));
					
					list.add(evt);
				}
			   	if (roworder==0) { //§ÈπÀ“‰¡Ëæ∫
			   		resPending.setList(list);
			   	} 
				
				response.setIsSuccess(true);
				response.setProcess("QueuePending Details");
				response.setProcessDesc("Successful");
				
				resPending.setAllSummary(allSum);
				resPending.setResponse(response);
				resPending.setList(list);

			    rs.close();
			    st.close();
			    
			}catch(SQLException e){
				list.clear();
				
				CK_Resp_QueueListBean evt;
				evt = new CK_Resp_QueueListBean();
				evt.setBarCode("");
				evt.setItemCode("");
				evt.setFilePath("");
				evt.setItemName("");
				evt.setPricePerUnit(0);
				evt.setQtyBefore(0);
				evt.setQtyAfter(0.0);
				evt.setTotalPriceBefore(0);
				evt.setTotalPriceAfter(0.0);
				evt.setUnitCode("");
				evt.setPickupStaffName("");
				evt.setIsCheck(0);
				evt.setIsCancel(0);
				
				response.setIsSuccess(false);
				response.setProcess("QueuePending Details");
				response.setProcessDesc("Error : "+e.getMessage());
				
				resPending.setAllSummary(allSum);
				resPending.setResponse(response);
				resPending.setList(list);
			}finally{
				ds.close();
			}
		}else{
			list.clear();
			
			CK_Resp_QueueListBean evt;
			evt = new CK_Resp_QueueListBean();
			evt.setBarCode("");
			evt.setItemCode("");
			evt.setFilePath("");
			evt.setItemName("");
			evt.setPricePerUnit(0);
			evt.setQtyBefore(0);
			evt.setQtyAfter(0.0);
			evt.setTotalPriceBefore(0);
			evt.setTotalPriceAfter(0.0);
			evt.setUnitCode("");
			evt.setPickupStaffName("");
			evt.setIsCheck(0);
			evt.setIsCancel(0);
			
			response.setIsSuccess(false);
			response.setProcess("QueuePending Details");
			response.setProcessDesc("Error : "+"No queue list");
			
			resPending.setAllSummary(allSum);
			resPending.setResponse(response);
			resPending.setList(list);	
		}
		
		return resPending;
		
	}
	
	
	public CK_Resp_PendingProductListBean searchDataQueueBranch(String dbName,CK_Reqs_GetDataQueue reqQueue){
		int vCountToken = 0;
		
		branch = getData.CheckDataAccessToken(reqQueue.getAccess_token());
		
		if (reqQueue.getqId()!=0){
			allSum = getData.searchQueueSummaryBranch(reqQueue.getqId(),branch.getBranchCode());
			try{
				
				Statement st = ds.getStatement("SmartQ");
//				vQuery="select a.docNo,a.docDate,a.carLicence,a.customerCode,a.qId,b.itemCode,b.itemName as barcodeName,b.unitCode, "+
//						" b.qty,b.pickQty,b.checkoutqty,b.rate1,b.price,b.itemAmount,concat(b.salecode,'/',b.salename) as pickercode,b.isCheckOut,b.checkoutAmount,b.isCancel,b.barcode, "+
//						" (select sum(itemAmount) from QueueSub where docno = a.docNo) as totalamount "+
//						" from 	QueueMaster a "+
//						" inner join QueueSub b on a.docno = b.docNo and a.branchCode = b.branchCode "+
//						" where a.branchCode = '"+branch.getBranchCode()+"' and a.qId = '"+reqQueue.getqId()+"' and a.docDate = CURRENT_DATE() order by b.pickDate desc";
				vQuery = "call USP_DT_SearchDataQueue ('"+branch.getBranchCode()+"',"+reqQueue.getqId()+")";
				ResultSet rs = st.executeQuery(vQuery);
				
				System.out.println("checkout : "+vQuery);
				
				int roworder=0;
				
				list.clear();
				
				CK_Resp_QueueListBean evt;
				
				while(rs.next()){
					
					roworder=roworder++;
					evt = new CK_Resp_QueueListBean();
					evt.setBarCode(rs.getString("barcode"));
					evt.setItemCode(rs.getString("itemCode"));
					evt.setFilePath(getData.getItemFilePath(rs.getString("itemCode")));
					evt.setItemName(rs.getString("barcodeName"));
					evt.setPricePerUnit(rs.getDouble("price"));
					evt.setQtyBefore(rs.getDouble("pickQty"));
					evt.setQtyAfter(rs.getDouble("checkoutQty"));
					evt.setTotalPriceBefore(rs.getDouble("itemAmount"));
					evt.setTotalPriceAfter(rs.getDouble("checkoutAmount"));
					evt.setUnitCode(rs.getString("unitCode"));
					evt.setPickupStaffName(rs.getString("pickercode"));
					evt.setIsCheck(rs.getInt("isCheckOut"));
					evt.setIsCancel(rs.getInt("isCancel"));
					
					System.out.println(rs.getString("filePath"));
					
					list.add(evt);
				}
			   	if (roworder==0) { //§ÈπÀ“‰¡Ëæ∫
			   		resPending.setList(list);
			   	} 
				
				response.setIsSuccess(true);
				response.setProcess("QueuePending Details");
				response.setProcessDesc("Successful");
				
				resPending.setAllSummary(allSum);
				resPending.setResponse(response);
				resPending.setList(list);

			    rs.close();
			    st.close();
			    
			}catch(SQLException e){
				list.clear();
				
				CK_Resp_QueueListBean evt;
				evt = new CK_Resp_QueueListBean();
				evt.setBarCode("");
				evt.setItemCode("");
				evt.setFilePath("");
				evt.setItemName("");
				evt.setPricePerUnit(0);
				evt.setQtyBefore(0);
				evt.setQtyAfter(0.0);
				evt.setTotalPriceBefore(0);
				evt.setTotalPriceAfter(0.0);
				evt.setUnitCode("");
				evt.setPickupStaffName("");
				evt.setIsCheck(0);
				evt.setIsCancel(0);
				
				response.setIsSuccess(false);
				response.setProcess("QueuePending Details");
				response.setProcessDesc("Error : "+e.getMessage());
				
				resPending.setAllSummary(allSum);
				resPending.setResponse(response);
				resPending.setList(list);
			}finally{
				ds.close();
			}
		}else{
			list.clear();
			
			CK_Resp_QueueListBean evt;
			evt = new CK_Resp_QueueListBean();
			evt.setBarCode("");
			evt.setItemCode("");
			evt.setFilePath("");
			evt.setItemName("");
			evt.setPricePerUnit(0);
			evt.setQtyBefore(0);
			evt.setQtyAfter(0.0);
			evt.setTotalPriceBefore(0);
			evt.setTotalPriceAfter(0.0);
			evt.setUnitCode("");
			evt.setPickupStaffName("");
			evt.setIsCheck(0);
			evt.setIsCancel(0);
			
			response.setIsSuccess(false);
			response.setProcess("QueuePending Details");
			response.setProcessDesc("Error : "+"No queue list");
			
			resPending.setAllSummary(allSum);
			resPending.setResponse(response);
			resPending.setList(list);	
		}
		
		return resPending;
		
	}
	
	public CK_Resp_ManageItemBean ManageProductCheckOut(String dbName,CK_Reqs_ManageProductBean reqQueue){
		LoginBean userCode = new LoginBean();
		getDataFromData getData = new getDataFromData();
		
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		
		PK_Reqs_ManageItemBean reqItem = new PK_Reqs_ManageItemBean();
		
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		int vCountToken = 0;
		
		reqItem.setAccess_token(reqQueue.getAccess_token());
		reqItem.setBarCode(reqQueue.getBarCode());
		reqItem.setIsCancel(reqQueue.getIsCancel());
		reqItem.setqId(reqQueue.getqId());
		reqItem.setQtyBefore(0);

		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		
		
		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
		
		creatorCode = userCode.getEmployeeCode();
		
		getBarData = getData.searchItemCode(reqQueue.getBarCode());
		getQueue = getData.searchQueue(reqQueue.getqId());
		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
		itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getBarCode(), getBarData.getUnitCode());
		
		
		if (reqQueue.getqId()!=0){
			
			if (reqQueue.getBarCode()!=null){
				
					getQueue = getData.searchQueue(reqQueue.getqId());
					
					if (getQueue.getIsCancel()==0){
						
						System.out.println("Error : "+getQueue.getStatus());
						
						if(getQueue.getStatus()!=3) {
							getBarData = getData.searchItemCode(reqQueue.getBarCode());
							userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
							vCheckExistItem = getData.checkItemExistQueue(reqItem);
							itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getBarCode(),getBarData.getUnitCode());

							itemAmount = itemPrice*reqQueue.getQtyAfter();
							saleCode = userCode.getEmployeeCode();
							saleName = userCode.getEmployeeName();
							
							if (getBarData.getCode()!=null){
							
							if (vCheckExistItem==0){
								 vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,barcode,unitCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,checkoutAmount,rate1,checkerCode,checkoutDate,isCancel,isCheckOut,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost,zoneId) "+ "values("
									+reqQueue.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+reqQueue.getBarCode()+"','"+getBarData.getUnitCode()+"',"+ reqQueue.getQtyAfter()+",0,0,"+reqQueue.getQtyAfter()+","+itemPrice+",0,"+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqQueue.getIsCancel()+",1,0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+",'"+getBarData.getZoneId()+"' )";
								 	//+reqQueue.getqId()+",'"+getQueue.getDocNo()+"','"+dateFormat.format(dateNow)+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+reqQueue.getBarCode()+"','"+getBarData.getUnitCode()+"',"+ reqQueue.getQtyAfter()+",0,0,"+reqQueue.getQtyAfter()+","+itemPrice+",0,"+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqQueue.getIsCancel()+",1,0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
								}else{
								if(reqItem.getIsCancel()==0){
									vQuery = "update QItem set checkoutQty="+reqQueue.getQtyAfter()+",price ="+itemPrice+",checkoutAmount="+itemAmount+",isCheckOut=1,checkerCode='"+userCode.getEmployeeCode()+"',checkoutDate = CURRENT_TIMESTAMP,isCancel=0 where qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
								}else{
									vQuery = "update QItem set isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
								}
							}
							System.out.println(vQuery);

							try{
								isSuccess= excecute.execute(dbName,vQuery);
								
								vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+getQueue.getDocNo()+"') where docNo ='"+getQueue.getDocNo()+"'";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								vQuerySub = "update  Queue set status = 1 where docNo ='"+getQueue.getDocNo()+"'";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								itemList.setBarCode(reqQueue.getBarCode());
								itemList.setFilePath("");
								itemList.setItemCategory(getBarData.getCategoryCode());
								itemList.setItemCode(getBarData.getCode());
								itemList.setItemName(getBarData.getItemName());
								itemList.setItemPrice(itemPrice);
								itemList.setRemark("");
								itemList.setShortCode(reqItem.getBarCode());
								itemList.setUnitCode(getBarData.getUnitCode());
								
								response.setIsSuccess(true);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Successfull");
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}catch(Exception e){
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Error : "+e.getMessage());
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}finally{
								ds.close();
							}
							}else{
								
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Error : "+"No have barcode");
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}
						}else{
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("checkOutManageItem");
							response.setProcessDesc("Error : Queue status is used");
							editOrder.setResponse(response);
							editOrder.setItem(itemList);
						}
						
					}else{
						isSuccess=false;
						response.setIsSuccess(false);
						response.setProcess("checkOutManageItem");
						response.setProcessDesc("Error : Queue is cancel");
						editOrder.setResponse(response);
						editOrder.setItem(itemList);
					}
			}else{
				//==============================
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("checkOutManageItem");
				response.setProcessDesc("Error : Barcode is null");
				editOrder.setResponse(response);
				editOrder.setItem(itemList);
			}
		}else{
			//===================================
			isSuccess=false;
			response.setIsSuccess(false);
			response.setProcess("checkOutManageItem");
			response.setProcessDesc("Error : qID not generate");
			editOrder.setResponse(response);
			editOrder.setItem(itemList);
		}
			
		return editOrder;
	}
	
	
	public CK_Resp_ManageItemBean ManageProductCheckOutBranch(String dbName,CK_Reqs_ManageProductBean reqQueue){
		LoginBean userCode = new LoginBean();
		getDataFromData getData = new getDataFromData();
		
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		
		PK_Reqs_ManageItemBean reqItem = new PK_Reqs_ManageItemBean();
		
		branch = getData.CheckDataAccessToken(reqQueue.getAccess_token());
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		
		int vCountToken = 0;
		
		reqItem.setAccess_token(reqQueue.getAccess_token());
		reqItem.setBarCode(reqQueue.getBarCode());
		reqItem.setIsCancel(reqQueue.getIsCancel());
		reqItem.setqId(reqQueue.getqId());
		reqItem.setQtyBefore(0);

		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		
		
		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
		
		creatorCode = userCode.getEmployeeCode();
		
		getBarData = getData.searchItemCode(reqQueue.getBarCode());
		getQueue = getData.searchQueueBranch(dbName,reqQueue.getqId(),branch.getBranchCode());
		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
		itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getBarCode(), getBarData.getUnitCode());
		
		
		if (reqQueue.getqId()!=0){
			
			if (reqQueue.getBarCode()!=null){
				
					getQueue = getData.searchQueueBranch(dbName,reqQueue.getqId(),branch.getBranchCode());
					
					if (getQueue.getIsCancel()==0){
						
						System.out.println("Error : "+getQueue.getStatus());
						
						if(getQueue.getStatus()!=3) {
							getBarData = getData.searchItemCode(reqQueue.getBarCode());
							userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());
							vCheckExistItem = getData.checkItemExistQueueBranch(branch.getBranchCode(),reqItem);
							itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getBarCode(),getBarData.getUnitCode());

							itemAmount = itemPrice*reqQueue.getQtyAfter();
							saleCode = userCode.getEmployeeCode();
							saleName = userCode.getEmployeeName();
							
							if (getBarData.getCode()!=null){
							
							if (vCheckExistItem==0){
								 //vQuery = "insert into QueueSub(qId,docNo,docDate,branchCode,itemCode,itemName,barcode,unitCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,checkoutAmount,rate1,checkerCode,checkoutDate,isCancel,isCheckOut,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost) "+ "values("
									//+reqQueue.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+branch.getBranchCode()+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+reqQueue.getBarCode()+"','"+getBarData.getUnitCode()+"',"+ reqQueue.getQtyAfter()+",0,0,"+reqQueue.getQtyAfter()+","+itemPrice+",0,"+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqQueue.getIsCancel()+",1,0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
								 vQuery = "call USP_DT_InsertCheckOutSub ("+reqQueue.getqId()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+branch.getBranchCode()+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqQueue.getBarCode()+"',"+ reqQueue.getQtyAfter()+","+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "','"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
							}else{
								if(reqItem.getIsCancel()==0){
									//vQuery = "update QueueSub set checkoutQty="+reqQueue.getQtyAfter()+",price ="+itemPrice+",checkoutAmount="+itemAmount+",isCheckOut=1,checkerCode='"+userCode.getEmployeeCode()+"',checkoutDate = CURRENT_TIMESTAMP,isCancel=0 where branchCode='"+branch.getBranchCode()+"' and qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
									vQuery = "call USP_DT_UpdateCheckOutSub ("+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+branch.getBranchCode()+"','"+getBarData.getCode()+"','"+getBarData.getUnitCode()+"',"+reqQueue.getQtyAfter()+","+itemPrice+","+itemAmount+",'"+userCode.getEmployeeCode()+"')";
								}else{
									//vQuery = "update QueueSub set isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where branchCode = '"+branch.getBranchCode()+"' and qId = "+reqItem.getqId()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"'";
									vQuery = "call USP_DT_CancelCheckOutSub ("+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+branch.getBranchCode()+"','"+getBarData.getCode()+"','"+getBarData.getUnitCode()+"','"+creatorCode+"')";
								}
							}
							System.out.println(vQuery);

							try{
								isSuccess= excecute.execute(dbName,vQuery);
								
								//vQuerySub = "update  QueueMaster set numberofitem = (select count(itemCode) as countItem from QueueSub where docNo = '"+getQueue.getDocNo()+"') where branchCode = '"+branch.getBranchCode()+"' and docNo ='"+getQueue.getDocNo()+"'";
								vQuerySub = "call USP_DT_UpdateCountItemBill ('"+branch.getBranchCode()+"','"+getQueue.getDocNo()+"')";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								//vQuerySub = "update  QueueMaster set status = 1 where branchCode = '"+branch.getBranchCode()+"' and docNo ='"+getQueue.getDocNo()+"'";
								vQuery ="call USP_DT_UpdateStatusQueueMaster ( "+reqQueue.getqId()+",'"+branch.getBranchCode()+"',"+1+")";
								isSuccess= excecute.execute(dbName,vQuerySub);
								
								itemList.setBarCode(reqQueue.getBarCode());
								itemList.setFilePath("");
								itemList.setItemCategory(getBarData.getCategoryCode());
								itemList.setItemCode(getBarData.getCode());
								itemList.setItemName(getBarData.getItemName());
								itemList.setItemPrice(itemPrice);
								itemList.setRemark("");
								itemList.setShortCode(reqItem.getBarCode());
								itemList.setUnitCode(getBarData.getUnitCode());
								
								response.setIsSuccess(true);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Successfull");
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}catch(Exception e){
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Error : "+e.getMessage());
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}finally{
								ds.close();
							}
							}else{
								
								isSuccess=false;
								response.setIsSuccess(false);
								response.setProcess("checkOutManageItem");
								response.setProcessDesc("Error : "+"No have barcode");
								editOrder.setResponse(response);
								editOrder.setItem(itemList);
							}
						}else{
							isSuccess=false;
							response.setIsSuccess(false);
							response.setProcess("checkOutManageItem");
							response.setProcessDesc("Error : Queue status is used");
							editOrder.setResponse(response);
							editOrder.setItem(itemList);
						}
						
					}else{
						isSuccess=false;
						response.setIsSuccess(false);
						response.setProcess("checkOutManageItem");
						response.setProcessDesc("Error : Queue is cancel");
						editOrder.setResponse(response);
						editOrder.setItem(itemList);
					}
			}else{
				//==============================
				isSuccess=false;
				response.setIsSuccess(false);
				response.setProcess("checkOutManageItem");
				response.setProcessDesc("Error : Barcode is null");
				editOrder.setResponse(response);
				editOrder.setItem(itemList);
			}
		}else{
			//===================================
			isSuccess=false;
			response.setIsSuccess(false);
			response.setProcess("checkOutManageItem");
			response.setProcessDesc("Error : qID not generate");
			editOrder.setResponse(response);
			editOrder.setItem(itemList);
		}
			
		return editOrder;
	}
}
