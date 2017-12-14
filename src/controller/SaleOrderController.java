package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.LoginBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_ManageItemListBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.request.CT_Req_SearchBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_SearchQueueBean;
import bean.request.SO_Req_SearchSaleOrderBean;
import bean.response.CT_Resp_ResponseBean;
import bean.response.SO_Res_CheckDataSOBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_ListOwnerPhoneBean;
import bean.response.SO_Res_ListQueueBean;
import bean.response.SO_Res_ListQueueStatusBean;
import bean.response.SO_Res_ListSaleOrderBean;
import bean.response.SO_Res_ListSaleOrderItemBean;
import bean.response.SO_Res_QueueDailyBean;
import bean.response.SO_Res_QueuePickupBean;
import bean.response.SO_Res_SaleOrderBean;
import bean.response.SO_Res_SaleOrderDetailsBean;
import connect.QueueConnect;
import connect.SQLConn;

public class SaleOrderController {
	private final SQLConn conn = SQLConn.INSTANCE;
	private final QueueConnect ds = QueueConnect.INSTANCE;
	SO_Res_SaleOrderDetailsBean so = new SO_Res_SaleOrderDetailsBean();
	List<SO_Res_ListSaleOrderItemBean> listSO = new ArrayList<SO_Res_ListSaleOrderItemBean>();
	
	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	GenNewDocnoController gen_no = new GenNewDocnoController();
	LoginBean userCode = new LoginBean();
	ExcecuteController excecute = new  ExcecuteController();
	NPSQLExecController npExec = new NPSQLExecController();
	getDataFromData data = new getDataFromData();
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	PK_Resp_ManageItemBean resp_item = new PK_Resp_ManageItemBean();
	
	SO_Req_GenOTPSaleOrderBean otp = new SO_Req_GenOTPSaleOrderBean();
	SO_Res_GenQueueRespBean queue = new SO_Res_GenQueueRespBean();
	
	SO_Res_SaleOrderBean sale_order = new SO_Res_SaleOrderBean();
	List<SO_Res_ListSaleOrderBean> list_so = new ArrayList<SO_Res_ListSaleOrderBean>();
	SO_Res_ListSaleOrderBean sale_item = new SO_Res_ListSaleOrderBean();
	List<SO_Res_ListSaleOrderItemBean> list_item_so = new ArrayList<SO_Res_ListSaleOrderItemBean>();
	
	SO_Res_CheckDataSOBean saleOrder = new SO_Res_CheckDataSOBean();
	SO_Res_QueueDailyBean qData = new SO_Res_QueueDailyBean();
	List<SO_Res_ListQueueBean> listQ = new ArrayList<SO_Res_ListQueueBean>();
	
//	public SO_Res_SaleOrderBean SearchSaleOrderTest(CT_Req_ServerDataBaseBean db,SO_Req_SearchSaleOrderBean req){
//			sale_order.setResponse(false);
//		return sale_order;
//	}

	public SO_Res_SaleOrderBean SearchSaleOrder(CT_Req_ServerDataBaseBean db,SO_Req_SearchSaleOrderBean req){
		String vQuery;
		String vQuerySub;
		String vSaleCode;
		
		vSaleCode = data.searchUserAccessToken(req.getAccess_token()).getEmployeeCode();
		
		try {
			Statement st = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
			vQuery = "exec dbo.USP_API_SearchSaleOrderNotBilling '"+vSaleCode+"','"+req.getKeyword()+"'";
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			
			list_so.clear();
			SO_Res_ListSaleOrderBean evt;
			while(rs.next()){
				evt = new SO_Res_ListSaleOrderBean();
				evt.setDoc_no(rs.getString("docno"));
				evt.setDoc_date(rs.getString("docdate"));
				evt.setAr_code(rs.getString("arcode"));
				evt.setAr_name(rs.getString("arname"));
				evt.setSale_code(rs.getString("salecode"));
				evt.setSale_name(rs.getString("salename"));
				evt.setReceiver_name(rs.getString("receivename"));
				evt.setPickup_datetime(rs.getString("pickupdatetime"));
				evt.setSum_Of_item_amount(rs.getDouble("sumofitemamount"));
				evt.setBefore_tax_amount(rs.getDouble("beforetaxamount"));
				evt.setTax_amount(rs.getDouble("taxamount"));
				evt.setTotal_amount(rs.getDouble("totalamount"));
				evt.setApprove_code(rs.getString("approvecode"));
				evt.setApprove_datetime(rs.getString("approvedatetime"));
				evt.setDiscount_amount(rs.getDouble("discountamount"));
				evt.setDelivery_type(rs.getInt("isconditionsend"));
				evt.setIs_confirm(rs.getInt("isconfirm"));
				
				List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
				String vQueryOwner;
				
				Statement st_owner = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
				vQueryOwner = "exec dbo.USP_NP_SearchContactList '"+rs.getString("arcode")+"','"+rs.getString("contactcode")+"'";
				ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
				System.out.println(vQueryOwner);
				
				SO_Res_ListOwnerPhoneBean evt_ownerphone;
				SO_Res_ListOwnerPhoneBean evt_ownerphone1;
				list_ownerphone.clear();
				while(rs_owner.next()){
					evt_ownerphone = new SO_Res_ListOwnerPhoneBean();
					evt_ownerphone1 = new SO_Res_ListOwnerPhoneBean();
					evt_ownerphone.setPhone_no(rs_owner.getString("telephone"));
					
					if(rs_owner.getRow()!=0){
						list_ownerphone.add(evt_ownerphone);
					}else{
						list_ownerphone.add(evt_ownerphone1);
					}
					
					rs_owner.close();
					st_owner.close();
				}
				evt.setOwner_phone(list_ownerphone);
				
				
				List<SO_Res_ListOwnerPhoneBean>list_trustphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();
				evt.setReceiver_phone(list_trustphone);
				
				
				List<SO_Res_ListSaleOrderItemBean> list_item_so = new ArrayList<SO_Res_ListSaleOrderItemBean>();
				
				Statement st_sub = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
				vQuerySub = "exec dbo.USP_API_SearchSaleOrderItemNotBilling '"+rs.getString("docno")+"'";
				ResultSet rs_sub = st_sub.executeQuery(vQuerySub);
				System.out.println(vQuerySub);
				SO_Res_ListSaleOrderItemBean evt_item;
				list_item_so.clear();
				while(rs_sub.next()){
					System.out.println("count_row"+rs_sub.getRow());
					evt_item = new SO_Res_ListSaleOrderItemBean();
					evt_item.setItem_code(rs_sub.getString("itemcode"));
					evt_item.setItem_name(rs_sub.getString("itemname"));
					evt_item.setWh_code(rs_sub.getString("whcode"));
					evt_item.setShelf_code(rs_sub.getString("shelfcode"));
					evt_item.setItem_qty(rs_sub.getDouble("remainqty"));
					evt_item.setItem_price(rs_sub.getDouble("price"));
					evt_item.setItem_unit_code(rs_sub.getString("unitcode"));
					evt_item.setItem_amount(rs_sub.getDouble("amount"));
					evt_item.setDiscount_amount(rs_sub.getDouble("discountamount"));
					evt_item.setNet_amount(rs_sub.getDouble("netamount"));
					evt_item.setRequest_qty(rs_sub.getDouble("remainqty"));
					evt_item.setPacking_rate1(rs_sub.getInt("packingrate1"));
					evt_item.setLine_number(rs_sub.getInt("linenumber"));
					evt_item.setItem_file_path(rs_sub.getString("picfilename1"));
					list_item_so.add(evt_item);
				}
				
				rs_sub.close();
				st_sub.close();
				
				evt.setList_item(list_item_so);
				
				list_so.add(evt);
				
			}
			
			response.setIsSuccess(true);
			response.setProcess("Search Sale Order");
			response.setProcessDesc("Successfull");

			rs.close();
			st.close();
		} catch (SQLException e) {
			response.setIsSuccess(false);
			response.setProcess("Search Sale Order");
			response.setProcessDesc(e.getMessage());
			
			so.setError(true);
			so.setMessage(e.getMessage());
		}finally{
			conn.close();
		}
		
		System.out.println("เลขที่เอกสาร :"+list_so.get(0).getDoc_no());
		sale_order.setSale_order(list_so);
		//sale_order.setResp(response);
		return sale_order;
	}
	
//	public SO_Res_SaleOrderDetailsBean saleOrdertoOTP(CT_Req_ServerDataBaseBean db,SO_Req_SearchSaleOrderBean req){
//		String vQuery;
//		int isHolding;
//		
//		try {
//			Statement st = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
//			vQuery = "exec dbo.USP_API_SearchSaleOrderItemNotBilling '"+req.getAccess_token()+"'";
//			ResultSet rs = st.executeQuery(vQuery);
//			
//			//System.out.println("holding ="+rs.getInt("holdingstatus"));
//			
//			//System.out.println("rows ="+rs.getRow());
//			
//			list_item_so.clear();
//
//			//System.out.println("msg ="+"Moo");
//			//if(rs.next() == true){
//			SO_Res_ListSaleOrderItemBean evt;
//			while(rs.next()){
//				System.out.println("msg1 ="+"มีข้อมูล");
//			isHolding = rs.getInt("holdingstatus");
//			
//			//System.out.println("holding ="+isHolding);
//			
//			//if (isHolding==0){
//				so.setSuccess(true);
//				so.setDoc_no(rs.getString("docno"));
//				so.setDoc_date(rs.getString("docdate"));
//				so.setAr_code(rs.getString("arcode"));
//				so.setAr_name(rs.getString("arname"));
//				so.setSale_code(rs.getString("salecode"));
//				so.setSale_name(rs.getString("salename"));
//				so.setSum_Of_item_amount(rs.getDouble("sumofitemamount"));
//				so.setBefore_tax_amount(rs.getDouble("beforetaxamount"));
//				so.setTax_amount(rs.getDouble("taxamount"));
//				so.setTotal_amount(rs.getDouble("totalamount"));
//				so.setApprove_code(rs.getString("approvecode"));
//				so.setApprove_datetime(rs.getString("approvedatetime"));
//				so.setDiscount_amount(rs.getDouble("discountamount"));
//				so.setIs_condition_send(rs.getInt("isconditionsend"));
//				so.setIs_confirm(rs.getInt("isconfirm"));
//				
//
//				evt = new SO_Res_ListSaleOrderItemBean();
//				evt.setItem_code(rs.getString("itemcode"));
//				evt.setItem_name(rs.getString("itemname"));
//				evt.setWh_code(rs.getString("whcode"));
//				evt.setShelf_code(rs.getString("shelfcode"));
//				evt.setItem_qty(rs.getDouble("remainqty"));
//				evt.setItem_price(rs.getDouble("price"));
//				evt.setUnit_code(rs.getString("unitcode"));
//				evt.setItem_amount(rs.getDouble("amount"));
//				evt.setDiscount_amount(rs.getDouble("discountamount"));
//				evt.setNet_amount(rs.getDouble("netamount"));
//				evt.setRemain_qty(rs.getDouble("remainqty"));
//				evt.setPacking_rate1(rs.getInt("packingrate1"));
//				evt.setLine_number(rs.getInt("linenumber"));
//				list_item_so.add(evt);
//				
//			//}else{
//				//so.setError(true);
//				//so.setMessage("Sale Order is holding");
//			//}
//			
//			so.setList_item(list_item_so);
//
//		}
//		//}else{
//			//System.out.println("msg2 ="+"ไม่มีข้อมูล");
//		//}
//
//		rs.close();
//		st.close();
//		} catch (SQLException e) {
//			so.setError(true);
//			so.setMessage(e.getMessage());
//		}finally{
//			conn.close();
//		}
//		
//		System.out.println("เลขที่เอกสาร :"+so.getDoc_no());
//		
//		return so;
//	}
	
//	public SO_Res_GenQueueRespBean SaleOrderToDriveThru (String dbName,SO_Req_GenOTPSaleOrderBean req){
//		getDataFromData getData = new getDataFromData();
//		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
//		PK_Resp_ManageItemListBean itemList = new PK_Resp_ManageItemListBean();
//		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
//		
//		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
//		
//		LoginBean userCode = new LoginBean();
//		int vCheckExistItem=0;
//		double itemPrice=0.0;
//		double itemAmount=0.0;
//		String vQuery;
//		String vQuerySub;
//		String vQueryLog;
//		String saleCode="";
//		String saleName="";
//		String creatorCode="";
//		String vGenNewDocNo;
//		int qId;
//		String vQueryHead;
//		boolean isSuccess;
//		
//		dtDoc.applyPattern("yyyy-MM-dd");
//		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
//
//		int vCountToken = 0 ;
//		int status = 0;
//		
//		System.out.println("ItemCount"+req.getItem().size());
//		
//		if (req.getItem().size()!=0){
//			
//			db.setDbName("bcnp");
//			db.setServerName("192.168.0.7");
//			
//			vGenNewDocNo = gen_no.genDocNo(0);
//			qId = gen_no.genqId();
//			
//			saleOrder = data.checkSaleOrderData(req.getDoc_no());
//			
//			saleCode = saleOrder.getSale_code();
//			saleName = saleOrder.getSale_name();
//			
//			try{
//	    	
//			vQueryHead ="insert into Queue(docNo,docDate,status,isCancel,customerId,warehouseId,customerCode,whCode,carLicence,carBrand,creatorCode,createDate,qId,saleCode,saleName,docType,saleorder,OTPCode,pickstatus,receiveName,pickupDatetime) values("+"'"+vGenNewDocNo+"',CURDATE(),0,0,'99999',0,'99999','S1-B','"+req.getPlate_number()+"','"+req.getCar_brand()+"','"+saleCode+"',CURRENT_TIMESTAMP,"+qId+",'"+saleCode+"','"+saleName+"',1,'"+req.getDoc_no()+"','"+req.getOtp_password()+"',0,'"+req.getReceiver_name()+"','"+req.getPickup_datetime()+"')";
//			System.out.println(vQueryHead);
//			isSuccess= excecute.execute(dbName,vQueryHead);
//			
//			vQuery ="insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values('"+req.getOtp_password()+"',"+qId+",'"+vGenNewDocNo+"',0,CURRENT_TIMESTAMP)";
//			System.out.println(vQuery);
//			isSuccess= excecute.execute(dbName,vQuery);
//			
//			queue.setQueue_id(qId);
//			queue.setOtp_password(req.getOtp_password());
//			queue.setPlate_number(req.getPlate_number());
//			queue.setCar_brand(req.getCar_brand());
//			queue.setReciver_name(req.getReceiver_name());
//			
//			String vQueryOwner;
//			if(req.getOwner_phone().size()!=0){
//				for(int a=0;a<(req.getOwner_phone().size()-1);a++){
//					vQueryOwner = "insert into OrderOwnerPhone(doc_no,otp_code,phone_no) values('"+vGenNewDocNo+"','"+req.getOtp_password()+"','"+req.getOwner_phone().get(a).getPhone_no()+"')";
//					System.out.println(vQueryOwner);
//					isSuccess= excecute.execute(dbName,vQueryOwner);
//				}
//			}
//			
//			
//			String vQueryTrust;
//			if(req.getReceiver_phone().size()!=0){
//				for(int b=0;b<(req.getReceiver_phone().size()-1);b++){
//					vQueryTrust = "insert into OrderTrustPhone(doc_no,otp_code,phone_no) values('"+vGenNewDocNo+"','"+req.getOtp_password()+"','"+req.getReceiver_phone().get(b).getPhone_no()+"')";
//					System.out.println(vQueryTrust);
//					isSuccess= excecute.execute(dbName,vQueryTrust);
//				}
//			}
//			
//			//isSuccess = true;
//								
//			for(int i=0;i<(req.getItem().size()-1);i++){
//				if (req.getItem().get(i).getItem_barcode()!=null && req.getItem().get(i).getItem_barcode()!=""){
//						if (isSuccess==true){	
//							if(status < 2) {
//								vCheckExistItem = 0;
//								itemPrice = req.getItem().get(i).getItem_price();
//								
//								creatorCode = userCode.getEmployeeCode();
//								
//								if (saleCode == "" || saleCode == null) {
//									lastSale = getData.searchTopSaleCode(qId);
//									saleCode = lastSale.getSaleCode();
//									saleName = lastSale.getSaleName();
//									
//									System.out.println("No Have SaleCode");
//									
//									
//								}else{
//
//									saleCode = saleOrder.getSale_code();
//									saleName = saleOrder.getSale_name();
//								}
//								
//								itemAmount = req.getItem().get(i).getRequest_qty()*req.getItem().get(i).getItem_price();
//								
//								System.out.println("SaleName : "+saleCode+"/"+saleName);
//								
//								//System.out.println("ItemAmount : "+itemAmount);
//								
//								getBarData = getData.searchItemCode(req.getItem().get(i).getItem_barcode());
//								
//								if (getBarData.getCode()!=null && getBarData.getCode()!=""){
//
//								if (vCheckExistItem==0){
//									vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,unitCode,barCode,qty,reqQty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost) "+ "values("
//										+qId+",'"+vGenNewDocNo+"',CURDATE(),'"+ req.getItem().get(i).getItem_code()+"','"+req.getItem().get(i).getItem_name()+"','"+req.getItem().get(i).getItem_unit_code()+"','"+req.getItem().get(i).getItem_barcode()+"',"+ req.getItem().get(i).getRequest_qty()+","+req.getItem().get(i).getRequest_qty()+",0,0,0,"+req.getItem().get(i).getItem_price()+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,0,0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
//									System.out.println("QuerySub :"+vQuery);
//									isSuccess= excecute.execute(dbName,vQuery);
//									
//									vQueryLog = "exec dbo.USP_NP_InsertPickItemDriveThru '"+req.getDoc_no()+"','"+req.getOtp_password()+"','"+req.getPlate_number()+"','"+req.getItem().get(i).getItem_code()+"',"+req.getItem().get(i).getRequest_qty()+",'"+req.getItem().get(i).getItem_unit_code()+"','"+req.getItem().get(i).getItem_whcode()+"','"+req.getItem().get(i).getItem_shelfcode()+"','','',0";
//									isSuccess= npExec.executeSqlBranch(db,vQueryLog);
//								}else{
//									vQuery = "update QItem set qty ="+req.getItem().get(i).getRequest_qty()+",reqQty="+req.getItem().get(i).getRequest_qty()+",price ="+req.getItem().get(i).getItem_price()+",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"' where qId = "+qId+" and docNo ='"+vGenNewDocNo+"' and itemCode='"+req.getItem().get(i).getItem_code()+"' and barCode ='"+req.getItem().get(i).getItem_barcode()+"' and unitCode ='"+req.getItem().get(i).getItem_unit_code()+"'";
//									System.out.println("QuerySub :"+vQuery);
//									isSuccess= excecute.execute(dbName,vQuery);
//								}
//								
//									vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+vGenNewDocNo+"') where docNo ='"+vGenNewDocNo+"'";
//									isSuccess= excecute.execute(dbName,vQuerySub);
//									
//									itemList.setBarCode(req.getItem().get(i).getItem_barcode());
//									itemList.setFilePath(getData.getItemFilePath(req.getItem().get(i).getItem_barcode()));
//									itemList.setItemCategory(getBarData.getCategoryCode());
//									itemList.setItemCode(getBarData.getCode());
//									itemList.setItemName(getBarData.getItemName());
//									itemList.setItemPrice(itemPrice);
//									itemList.setRemark("");
//									itemList.setShortCode(req.getItem().get(i).getItem_barcode());
//									itemList.setUnitCode(getBarData.getUnitCode());
//									
//									response.setIsSuccess(true);
//									response.setProcess("insertItemPickup");
//									response.setProcessDesc("Successfull");
//									
//									resp_item.setItem(itemList);
//									resp_item.setResponse(response);
//									
//				
//								}else{
//									
//									isSuccess=false;
//									response.setIsSuccess(false);
//									response.setProcess("insertItemPickup");
//									response.setProcessDesc("Error : "+"No have barcode");
//
//									resp_item.setItem(itemList);
//									resp_item.setResponse(response);
//								}
//							}else{
//								isSuccess=false;
//								response.setIsSuccess(false);
//								response.setProcess("insertItemPickup");
//								response.setProcessDesc("Error : Queue status is used");
//
//								resp_item.setItem(itemList);
//								resp_item.setResponse(response);
//							}
//							
//						}else{
//							isSuccess=false;
//							response.setIsSuccess(false);
//							response.setProcess("insertItemPickup");
//							response.setProcessDesc("Error : Queue is cancel");
//
//							resp_item.setItem(itemList);
//							resp_item.setResponse(response);
//						}
//				}else{
//					//==============================
//					isSuccess=false;
//					response.setIsSuccess(false);
//					response.setProcess("insertItemPickup");
//					response.setProcessDesc("Error : Barcode is null");
//
//					resp_item.setItem(itemList);
//					resp_item.setResponse(response);
//				}
//				
//				String whCode;
//				String shelfCode;
//				String zoneID;
//				String pickZone;
//				String familyGroup;
//				
//				String vQuery1;
//				String vQuery2;
//				
//				
//				vQuery = "exec dbo.USP_NP_GroupItemZone '"+req.getDoc_no()+"','"+req.getOtp_password()+"'";
//				Statement st = conn.getSqlStatement(db.getServerName(), db.getDbName());
//				ResultSet rs = st.executeQuery(vQuery);
//				while(rs.next()){
//					whCode = rs.getString("whcode");
//					shelfCode = rs.getString("shelfcode");
//					zoneID = rs.getString("zoneid");
//					pickZone = rs.getString("pickzone");
//					familyGroup = rs.getString("familygroup");
//					
//					
//					List<SO_Res_QueuePickupBean> queue_number =new ArrayList<SO_Res_QueuePickupBean>();
//					
//					vQuery2 = "exec dbo.USP_NP_InsertPickupFromDriveTrue '" + req.getDoc_no() + "','" +  req.getOtp_password() + "','" +req.getPlate_number()+ "','" + whCode + "','" + shelfCode + "','" + zoneID + "','" + pickZone + "','" + familyGroup + "','" + req.getPickup_datetime() + "','" + saleCode + "'";
//					System.out.println("vQuery2 :"+vQuery2);
//					Statement st1 = conn.getSqlStatement(db.getServerName(), db.getDbName());
//					ResultSet rs1 = st1.executeQuery(vQuery2);
//					
//					SO_Res_QueuePickupBean evt1;
//					while(rs1.next()){
//						evt1 = new SO_Res_QueuePickupBean();
//						evt1.setZone_id(rs1.getString("zoneid"));
//						evt1.setQueue_id(rs1.getInt("QueueID"));
//						
//						queue_number.add(evt1);
//					}
//					
//					queue.setQueue_number(queue_number);
//					
//					//isSuccess= npExec.executeSqlBranch(db,vQuery2);
//					rs1.close();
//					st1.close();
//				}
//				rs.close();
//				st.close();
//			}
//			}catch(Exception e){
//				isSuccess=false;
//				response.setIsSuccess(false);
//				response.setProcess("insertItemPickup");
//				response.setProcessDesc("Error : "+e.getMessage());
//				
//				resp_item.setItem(itemList);
//				resp_item.setResponse(response);
//			}finally{
//				ds.close();
//			}
//		}
//			
//		return queue;
//	}
	
	
	public SO_Res_QueueDailyBean listQueue(String dbName,SO_Req_SearchQueueBean req){
		String vQuery;
		
		try {
			Statement st = ds.getStatement(dbName);
			vQuery = "call USP_DT_QueueDaily ('"+req.getKeyword()+"','"+req.getPage()+"')";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			

			listQ.clear();
			SO_Res_ListQueueBean evt;
			while(rs.next()){
				evt = new SO_Res_ListQueueBean();
				evt.setQueue_id(rs.getInt("qid"));
				evt.setNumber_of_item(rs.getDouble("numberofitem"));
				evt.setTime_created(rs.getDate("createDate"));
				evt.setStatus(rs.getInt("status"));
				evt.setPick_state(rs.getString("pickstatusname"));
				evt.setIs_cancel(rs.getInt("iscancel"));
				evt.setDoc_no(rs.getString("docno"));
				evt.setSource(rs.getString("source"));
				evt.setReciver_name(rs.getString("receivename"));
				evt.setPickup_datetime(rs.getDate("pickupdatetime"));
				evt.setCar_brand(rs.getString("carbrand"));
				evt.setPlate_number(rs.getString("carlicence"));
				evt.setStatus_for_saleorder_current(rs.getString("queueStatusname"));

				
				List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
				String vQueryOwner;
				
				Statement st_owner = ds.getStatement(dbName);
				vQueryOwner = "call USP_DT_SearchOrderOwnerPhone ('"+rs.getString("docno")+"')";
				ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
				System.out.println(vQueryOwner);
				
				SO_Res_ListOwnerPhoneBean evt_ownerphone;
				SO_Res_ListOwnerPhoneBean evt_ownerphone1;
				list_ownerphone.clear();
				while(rs_owner.next()){
					evt_ownerphone = new SO_Res_ListOwnerPhoneBean();
					evt_ownerphone1 = new SO_Res_ListOwnerPhoneBean();
					evt_ownerphone.setPhone_no(rs_owner.getString("phone_no"));
					
					if(rs_owner.getRow()!=0){
						list_ownerphone.add(evt_ownerphone);
					}else{
						list_ownerphone.add(evt_ownerphone1);
					}
					
					rs_owner.close();
					st_owner.close();
				}
				evt.setOwner_phone(list_ownerphone);
				
				
				List<SO_Res_ListOwnerPhoneBean>list_trustphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();
				String vQueryTrust;
				
				Statement st_trust = ds.getStatement(dbName);
				vQueryTrust = "call USP_DT_SearchOrderTrustPhone ('"+rs.getString("docno")+"')";
				ResultSet rs_trust = st_trust.executeQuery(vQueryTrust);
				System.out.println(vQueryTrust);
				
				SO_Res_ListOwnerPhoneBean evt_trustphone;
				SO_Res_ListOwnerPhoneBean evt_trustphone1;
				list_trustphone.clear();
				while(rs_owner.next()){
					evt_trustphone = new SO_Res_ListOwnerPhoneBean();
					evt_trustphone1 = new SO_Res_ListOwnerPhoneBean();
					evt_trustphone.setPhone_no(rs_trust.getString("phone_no"));
					
					if(rs_trust.getRow()!=0){
						list_trustphone.add(evt_trustphone);
					}else{
						list_trustphone.add(evt_trustphone1);
					}
					
					rs_trust.close();
					st_trust.close();
				}
				evt.setTrust_phone(list_trustphone);
				
				
				
				List<SO_Res_ListQueueStatusBean> status_for_saleorder_history = new ArrayList<SO_Res_ListQueueStatusBean>();
				String vQuerySub;
				
				Statement st_sub = ds.getStatement(dbName);
				vQuerySub = "call USP_DT_SearchQueueStatusHistory ('"+rs.getString("docno")+"')";
				ResultSet rs_sub = st_sub.executeQuery(vQuerySub);
				System.out.println(vQuerySub);
				SO_Res_ListQueueStatusBean evt_item;
				status_for_saleorder_history.clear();
				while(rs_sub.next()){
					System.out.println("count_row"+rs_sub.getRow());
					evt_item = new SO_Res_ListQueueStatusBean();
					evt_item.setStatus_for_saleorder(rs_sub.getString("pickname"));
					
					status_for_saleorder_history.add(evt_item);
				}
				
				rs_sub.close();
				st_sub.close();
				
				evt.setStatus_for_saleorder_history(status_for_saleorder_history);
				
				listQ.add(evt);
				
			}
			
			qData.setListQueue(listQ);
			
			response.setIsSuccess(true);
			response.setProcess("Search Sale Order");
			response.setProcessDesc("Successfull");

			rs.close();
			st.close();
		} catch (SQLException e) {
			response.setIsSuccess(false);
			response.setProcess("Search Sale Order");
			response.setProcessDesc(e.getMessage());
			
			so.setError(true);
			so.setMessage(e.getMessage());
		}finally{
			conn.close();
		}
		
		return qData;
	}
	

}



