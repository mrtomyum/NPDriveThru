package controller;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import connect.QueueConnect;
import connect.SQLConn;
import connect.bCryptPassword;
import bean.CM_Resp_MemberListBean;
import bean.IV_Reqs_CouponBean;
import bean.IV_Reqs_CreditCardBean;
import bean.IV_Reqs_PrintSlipBean;
import bean.IV_Resp_ARInvoiceBean;
import bean.IV_Resp_ARInvoiceSubBean;
import bean.IV_Resp_BillingBean;
import bean.IV_Resp_InUpBillingBean;
import bean.IV_Resp_PrintSlipBean;
import bean.LoginBean;
import bean.LoginResponseBean;
import bean.PK_Resp_CarBrandBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_ManageItemListBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.PK_Resp_SearchCarBrandBean;
import bean.UserBean;
import bean.UserSearchBean;
import bean.UserSearchResponseBean;
import bean.User_Resp_CheckDataAccessTokenBean;
import bean.request.CT_Req_LoginBean;
import bean.request.CT_Req_LoginPassBean;
import bean.request.CT_Req_SearchArBean;
import bean.request.CT_Req_SearchBean;
import bean.request.CT_Req_SearchInvoiceBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.IV_Req_DepositBean;
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
import bean.request.SO_Req_SearchQueueDataBean;
import bean.request.SO_Req_SearchSaleOrderBean;
import bean.response.CT_Res_CompanyDataBean;
import bean.response.CT_Res_LoginBean;
import bean.response.CT_Res_ResponseBean;
import bean.response.CT_Res_UserBean;
import bean.response.CT_Resp_ResponseBean;
import bean.response.DT_Res_CompanyBean;
import bean.response.DT_Res_ListCompanyBean;
import bean.response.DT_Res_ListZoneBean;
import bean.response.DT_Res_ZoneBean;
import bean.response.QU_Res_QueueDataBean;
import bean.response.SO_Res_ARDepositBean;
import bean.response.SO_Res_BillingBean;
import bean.response.SO_Res_CarBrandBean;
import bean.response.SO_Res_ChangeStatusBean;
import bean.response.SO_Res_CheckDataSOBean;
import bean.response.SO_Res_CheckQueueItemBean;
import bean.response.SO_Res_EditQueueBean;
import bean.response.SO_Res_EditQueueDataBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_InvoiceBean;
import bean.response.SO_Res_ItemSaleOrderBean;
import bean.response.SO_Res_ListARDepositBean;
import bean.response.SO_Res_ListCarBrandBean;
import bean.response.SO_Res_ListItemInvoiceBean;
import bean.response.SO_Res_ListOwnerPhoneBean;
import bean.response.SO_Res_ListPickZoneBean;
import bean.response.SO_Res_ListProductQueueBean;
import bean.response.SO_Res_ListQueueBean;
import bean.response.SO_Res_ListQueueStatusBean;
import bean.response.SO_Res_ListSaleOrderBean;
import bean.response.SO_Res_ListSaleOrderItemBean;
import bean.response.SO_Res_ListSearchQueueDataBean;
import bean.response.SO_Res_PickZoneBean;
import bean.response.SO_Res_PickingManageProductBean;
import bean.response.SO_Res_ProductQueueBean;
import bean.response.SO_Res_QueueBean;
import bean.response.SO_Res_QueueDailyBean;
import bean.response.SO_Res_QueuePickupBean;
import bean.response.SO_Res_Response;
import bean.response.SO_Res_SaleOrderBean;
import bean.response.SO_Res_SaleOrderDetailsBean;
import bean.response.SO_Res_SaleOrderInvoiceBean;
import bean.response.SO_Res_SearchQueueDataBean;

import org.mindrot.jbcrypt.*;


public class DriveThruController {
	private final SQLConn conn = SQLConn.INSTANCE;
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private final SQLConn sql = SQLConn.INSTANCE;
	
	private SQLConn sqlDS = SQLConn.INSTANCE;
	
	private String Textstring;
	
	CT_Res_LoginBean login = new CT_Res_LoginBean();
	List<CT_Req_LoginBean>userlogin = new ArrayList<CT_Req_LoginBean>();
	UserBean user = new UserBean();

	DT_Res_CompanyBean company = new DT_Res_CompanyBean();
	List<DT_Res_ListCompanyBean> list_Company = new ArrayList<DT_Res_ListCompanyBean>();
	DT_Res_ZoneBean zone = new DT_Res_ZoneBean();
	
	SO_Res_PickZoneBean pick_zone = new SO_Res_PickZoneBean();
	List<SO_Res_ListPickZoneBean>list_Zone = new ArrayList<SO_Res_ListPickZoneBean>();
	
	SO_Res_SaleOrderDetailsBean so = new SO_Res_SaleOrderDetailsBean();
	List<SO_Res_ListSaleOrderItemBean> listSO = new ArrayList<SO_Res_ListSaleOrderItemBean>();
	
	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	GenNewDocnoController gen_no = new GenNewDocnoController();
	LoginBean userCode = new LoginBean();
	ExcecuteController excecute = new  ExcecuteController();
	NPSQLExecController npExec = new NPSQLExecController();
	SQLExecController sqlexec = new SQLExecController();
	getDataFromData data = new getDataFromData();
	UserController user_details = new UserController();
	
	CT_Res_CompanyDataBean branch = new CT_Res_CompanyDataBean();
	
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
	
	SO_Res_QueueBean queuedata1 = new SO_Res_QueueBean();
	SO_Res_ListSearchQueueDataBean queuedata = new SO_Res_ListSearchQueueDataBean();
	
	SO_Res_SearchQueueDataBean que_data = new SO_Res_SearchQueueDataBean();
	List<SO_Res_ListSearchQueueDataBean> listQueue = new ArrayList<SO_Res_ListSearchQueueDataBean>(); 
	SO_Res_ProductQueueBean product = new SO_Res_ProductQueueBean();
	List<SO_Res_ListProductQueueBean>listproduct = new ArrayList<SO_Res_ListProductQueueBean>();
	PK_Resp_SaleCodeDetails sale = new PK_Resp_SaleCodeDetails();
	SO_Res_PickingManageProductBean resItem = new SO_Res_PickingManageProductBean();
	SO_Res_ListProductQueueBean item = new SO_Res_ListProductQueueBean();	
	SO_Res_EditQueueBean edit = new SO_Res_EditQueueBean();
	SO_Res_EditQueueDataBean queue_edit = new SO_Res_EditQueueDataBean();
	SO_Res_ARDepositBean dep = new SO_Res_ARDepositBean();
	List<SO_Res_ListARDepositBean> listdep = new ArrayList<SO_Res_ListARDepositBean>();
	SO_Res_ChangeStatusBean change = new SO_Res_ChangeStatusBean();
	SO_Res_EditQueueDataBean qstatus = new SO_Res_EditQueueDataBean();
	getDataFromData getdb = new getDataFromData();
	bCryptPassword bcrypt = new bCryptPassword();
	
	IV_Resp_InUpBillingBean bill = new IV_Resp_InUpBillingBean();
	IV_Resp_ARInvoiceBean header = new IV_Resp_ARInvoiceBean();
	List<IV_Resp_ARInvoiceSubBean> sub = new ArrayList<IV_Resp_ARInvoiceSubBean>();
	
	List<IV_Reqs_CreditCardBean> crdCard = new ArrayList<IV_Reqs_CreditCardBean>();
	List<IV_Reqs_CouponBean> listCoupong = new ArrayList<IV_Reqs_CouponBean>();
	List<IV_Req_DepositBean> listDeposit = new ArrayList<IV_Req_DepositBean>();
	
	double checkSumCreditAmount=0;
	double checkSumCouponAmount=0;
	double checkSumDepositAmount=0;
	
	double checkCashAmount=0;
	double checkRemainAmount=0;
	double checkChangeAmount=0;
	double checkRemain=0;
	
	double sumCreditAmount=0;
	double sumCouponAmount=0;
	double sumDepositAmount=0;
	
	double sumRemain=0;
	
	
	SO_Res_BillingBean respBill = new SO_Res_BillingBean();
	SO_Res_InvoiceBean invoice = new SO_Res_InvoiceBean();
	GenNewDocnoController genDoc = new GenNewDocnoController();
	
	CM_Resp_MemberListBean ar = new CM_Resp_MemberListBean();
	PK_Resp_GetDataQueue que = new PK_Resp_GetDataQueue();
	
	QU_Res_QueueDataBean qDetails = new QU_Res_QueueDataBean();
	
	private boolean isSuccess;
	
	
	public DT_Res_CompanyBean searchCompanyZone(String dbName){
		String vQuery;
		
		try {
			Statement st = ds.getStatement(dbName);
			vQuery = "call USP_NP_SearchBranchMaster('pos')";
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			DT_Res_ListCompanyBean evt;
			list_Company.clear();
			while(rs.next()){
				evt = new DT_Res_ListCompanyBean();
				evt.setCompany_id(rs.getString("branch_id"));
				evt.setCompany_name(rs.getString("branch_name"));
				
				List<DT_Res_ListZoneBean> list_Zone = new ArrayList<DT_Res_ListZoneBean>();
				
				Statement st_sub = ds.getStatement(dbName);
				vQuery = "call USP_NP_SearchZoneMaster ('pos','"+rs.getString("branch_id")+"')";
				ResultSet rs_sub = st_sub.executeQuery(vQuery);
				System.out.println(vQuery);
				DT_Res_ListZoneBean evt_sub;
				//list_Zone.clear();
				while(rs_sub.next()){
					System.out.println("count_row"+rs.getRow());
					evt_sub = new DT_Res_ListZoneBean();
					evt_sub.setZone_id(rs_sub.getString("zone_id"));
					evt_sub.setZone_name(rs_sub.getString("zone_name"));
					
					System.out.println(rs_sub.getString("zone_name"));
					
					list_Zone.add(evt_sub);
				}
				evt.setZone(list_Zone);
				
				list_Company.add(evt);
				
				rs_sub.close();
				st_sub.close();
			}
			
			company.setError(false);
			company.setSuccess(true);
			company.setMessage("");
			
			rs.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			company.setError(true);
			company.setSuccess(false);
			company.setMessage(e.getMessage());
		}finally{
			ds.close();
		}

		company.setCompany(list_Company);
		
		
		return company;
	}
	
	public SO_Res_PickZoneBean searchItemPickZone(CT_Req_ServerDataBaseBean db){
		String vQuery;
		
		try {
			Statement st = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
			vQuery = "exec dbo.USP_NP_SearchItemPickZone";
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			SO_Res_ListPickZoneBean evt;
			list_Zone.clear();
			while(rs.next()){
				evt = new SO_Res_ListPickZoneBean();
				evt.setPick_zone_id(rs.getString("pickzone"));
				evt.setName(rs.getString("name"));
			
				list_Zone.add(evt);
			}
			
			pick_zone.setError(false);
			pick_zone.setSuccess(true);
			pick_zone.setMessage("");
			
			rs.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			pick_zone.setError(true);
			pick_zone.setSuccess(false);
			pick_zone.setMessage(e.getMessage());
		}finally{
			ds.close();
		}

		pick_zone.setPick_zone(list_Zone);
		
		
		return pick_zone;
	}
	
	
	private void loginResponseTemplage(String keyword) {
		response.setIsSuccess(false);
 		response.setProcessDesc("Not found :" +keyword);
 		
 		user.setId(-1);
		    user.setCode(null);
		    user.setName(null);
		    user.setPassword(null);
		    user.setImage_filename(null);
		    user.setEmail(null);
		    user.setRole(-1);
		    user.setActiveStatus(-1);
		    user.setIsConfirm(-1);
		    user.setCreatorCode(null);
		    user.setCreatedateTime(null);
		    user.setLasteditorCode(null);
		    user.setLasteditdateTime(null);
	}
	
	
	BranchController checkbranch = new BranchController();
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	LoginResponseBean loginResponse = new LoginResponseBean();
	
	public LoginResponseBean userlogin(String dbName,CT_Req_LoginPassBean login) {//LoginBean
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		String branchCode="S01";
		
		String vQuery;
		String vUserID="";

		try {
			branchCode = checkbranch.getBranchID(dbName, login.getBranch_id()).getCode().toUpperCase();
			Statement stmt = ds.getStatement(dbName);
		    
			Textstring="select * from User where code='"+login.getUser_code()+"' and branchCode='"+branchCode+"'";
		    
		   	System.out.println("SQL LogIn :" + Textstring);
		   	System.out.println("สาขาที่เข้าใช้งาน : "+login.getBranch_id());
		    
		    ResultSet rs = stmt.executeQuery(Textstring);
		    
		    response.setProcess("login");
		        if ( rs.next() != false ) 
		        	{	
		        		response.setIsSuccess(true);
		        		response.setProcessDesc("successful");
		        		
		        		user.setId(rs.getInt("id"));
		    		    user.setCode(rs.getString("code"));
		    		    user.setName(rs.getString("name"));
		    		    user.setPassword(rs.getString("password"));
		    		    user.setImage_filename(rs.getString("picturePath"));
		    		    user.setEmail(rs.getString("email"));
		    		    user.setRole(rs.getInt("role"));
		    		    user.setActiveStatus(rs.getInt("activeStatus"));
		    		    user.setIsConfirm(rs.getInt("isConfirm"));
		    		    user.setCreatorCode(rs.getString("creatorCode"));
		    		    user.setCreatedateTime(rs.getString("createdateTime"));
		    		    user.setLasteditorCode(rs.getString("lasteditorCode"));
		    		    user.setLasteditdateTime(rs.getString("lasteditdateTime"));
		        		
		        		String uuid = UUID.randomUUID().toString(); 
		        		loginResponse.setAccessToken(uuid);
		    		    loginResponse.setAccessDatetime(dt.format(dateNow));

		    			 
		        	} else 
		        	{
		        		loginResponseTemplage(login.getUser_code());
		        	}
		        
	       	
		    loginResponse.setResponse(response); 
		    loginResponse.setUser(user); 
		    loginResponse.setPathFile("http://qserver.nopadol.com/drivethru/tmp/");
		    loginResponse.setPathPHPUpload("http://qserver.nopadol.com/drivethru/upload.php");
		    
		    System.out.println("uuid = " + loginResponse.getAccessToken() + " : "+ loginResponse.getResponse().getProcessDesc()+ "-"+loginResponse.getAccessDatetime() ); 

		    rs.close();
		    stmt.close();
		    
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		    ds.close();
		}
		
		// เก็บ log UserAccess
		boolean connect;
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		
		System.out.println("Branch Code = "+branchCode);
		
		if (branchCode.equals("S01")){
			db.setServerName("192.168.0.7");
			db.setDatabaseName("bcnp");
		}
		
		if (branchCode.equals("S02")){
			db.setServerName("192.168.0.7");
			db.setDatabaseName("bcnp");
		}
		
		try {
			vQuery = "exec dbo.USP_NP_SearchSaleDataLogIn '"+login.getUser_code()+"'";
			System.out.println("Sale"+vQuery);
			Statement st = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				vUserID = rs.getString("code");
				
				System.out.println("vUserID"+vUserID);
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			isSuccess=false;
			response.setIsSuccess(isSuccess); 
			response.setProcess("login");
			response.setProcessDesc("Login failed");
			
		}finally{
			conn.close();
		}
		
		connect = conn.checkConnect(db.getServerName(),db.getDatabaseName(),vUserID,login.getPassword());
		
		System.out.println("connect = "+connect);
		
		if (loginResponse.getAccessToken()!=null && connect == true){
		
		Textstring="insert UserAccess(userId,userCode,userUUID,branchCode,dateTimeStamp)"
				+" select '" +loginResponse.getUser().getId()+"','"+loginResponse.getUser().getCode()
					+"','"+loginResponse.getAccessToken()+"','"+branchCode+"','"+loginResponse.getAccessDatetime()+"'";
		
						
		 System.out.println(Textstring);
		 System.out.println("BranchID = "+branchCode);
		
		// return true;
		 try {
			isSuccess= excecute.execute(dbName,Textstring);
		} catch (Exception e) {
			isSuccess=false;
			loginResponseTemplage(login.getUser_code());
		}
		}else {
			isSuccess=false;
			response.setIsSuccess(isSuccess); 
			response.setProcess("login");
			response.setProcessDesc("Login failed");
		}
		
		return loginResponse;
		
	}
	
	public CT_Res_LoginBean login(String db,CT_Req_LoginBean req) {
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		CT_Req_ServerDataBaseBean dbName = new CT_Req_ServerDataBaseBean();
		List<CT_Res_UserBean> user_data = new ArrayList<CT_Res_UserBean>();
		
		String vQuery;
		String vUserID="";
		String token;
		
		boolean connect;
		
		//branchCode = data.searchCompanyData(req.getCompany_id(),req.getZone_id()).getBranch_code();
		
		if (req.getCompany_id().equals("s01")){
			dbName.setServerName("192.168.0.7");
			dbName.setDatabaseName("bcnp");
		}
		
		if (req.getCompany_id().equals("s02")){
			dbName.setServerName("192.168.0.7");
			dbName.setDatabaseName("bcnp");
		}
		
		
		try {
			vQuery = "exec dbo.USP_NP_SearchSaleDataLogIn '"+req.getUser_code()+"'";
			System.out.println("Sale"+vQuery);
			Statement st = conn.getSqlStatement(dbName.getServerName(), dbName.getDatabaseName());
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				vUserID = rs.getString("code");
				
				System.out.println("vUserID"+vUserID);
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			login.setSuccess(false);
			login.setError(true);
			login.setMessage(e.getMessage());
			
		}finally{
			conn.close();
		}
		
		connect = conn.checkConnect(dbName.getServerName(),dbName.getDatabaseName(),vUserID,req.getPassword());

		if (connect == true){
			try{
	
				Statement stmt = ds.getStatement(db);
			    
				Textstring="select * from User where code='"+req.getUser_code()+"' and branchCode='"+req.getCompany_id()+"'";
			    
			   	System.out.println("SQL LogIn :" + Textstring);
			    
			    ResultSet rs = stmt.executeQuery(Textstring);
			    
			        if ( rs.next() != false ) 
			        	{	
			        		String uuid = UUID.randomUUID().toString(); 
			        		login.setAccess_token(uuid);
			    		    
			    			// เก็บ log UserAccess
			        		
			    		    if (login.getAccess_token()!=null){
			    				
			    				Textstring="insert UserAccess(userId,userCode,userUUID,app_id,branchCode,zoneid,dateTimeStamp)"
			    						+" select '" +rs.getInt("id")+"','"+req.getUser_code()+"','"+login.getAccess_token()+"','Pos_DriveThru','"+req.getCompany_id()+"','"+req.getZone_id()+"',CURRENT_TIMESTAMP()";
			    				
			    								
			    				 System.out.println("Textstring = "+Textstring);
			    				
			    				// return true;
			    				 CT_Res_UserBean userlogin = new CT_Res_UserBean();
			    				 user_data.clear();
				    					isSuccess= excecute.execute(db,Textstring);	  
				    					
				    					UserSearchBean user_search = new UserSearchBean();
				    					
				    					user_search.setAccess_token(login.getAccess_token());
				    					user_search.setKeyword(req.getUser_code());
				    					
				    					
				    					userlogin.setCompany_id(req.getCompany_id());
				    					userlogin.setCompany_id(req.getCompany_id());
				    					userlogin.setSale_code(rs.getString("code"));
				    					userlogin.setUser_name(rs.getString("name"));
				    					userlogin.setZone_id(req.getZone_id());
				    					userlogin.setPic_profile(rs.getString("picturePath"));
				    					
				    					
				    					login.setUser(userlogin);
				    					
				    					login.setSuccess(true);
				    					login.setError(false);
				    					login.setMessage("");
				    					
			    				}
			    			 
			        	} else 
			        	{
			        		login.setSuccess(false);
	    					login.setError(true);
	    					login.setMessage("User ID Not in DataBase");
			        	}
			        
			}catch(SQLException e){
				login.setSuccess(false);
				login.setError(true);
				login.setMessage(e.getMessage());
			}finally{
				ds.close();
			}
			   // System.out.println("uuid = " + login.getAccess_token()); 

				
			} else{
				login.setSuccess(false);
				login.setError(true);
				login.setMessage("User "+vUserID +" login failed password is wrong !!!!!");
			}

		return login;
		
	}
	
	public SO_Res_SaleOrderBean SearchSaleOrder(CT_Req_ServerDataBaseBean db,SO_Req_SearchSaleOrderBean req){
		String vQuery;
		String vQuerySub;
		String vSaleCode;
		
		User_Resp_CheckDataAccessTokenBean company = new User_Resp_CheckDataAccessTokenBean();
		
		vSaleCode = data.searchUserAccessToken(req.getAccess_token()).getEmployeeCode();
		company = data.CheckDataAccessToken(req.getAccess_token());
		System.out.println("token : "+req.getAccess_token());
		System.out.println("vSaleCode : "+vSaleCode);
		System.out.println("company : "+company.getBranchCode());
		System.out.println("zone : "+company.getZoneid());
		
		try {
			Statement st = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
			vQuery = "exec dbo.USP_API_SearchSaleOrderNotBilling '"+company.getBranchCode()+"','"+company.getWhCode()+"','"+vSaleCode+"','"+req.getKeyword()+"'";
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
				evt.setBill_type(rs.getInt("billtype"));
				evt.setIs_load(rs.getInt("isload"));
				evt.setTax_type(rs.getInt("taxtype"));
				
				List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
				String vQueryOwner;
				
				Statement st_owner = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
				vQueryOwner = "exec dbo.USP_NP_SearchContactList '"+rs.getString("arcode")+"','"+rs.getString("contactcode")+"'";
				System.out.println("vQueryOwner : "+vQueryOwner);
				ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
				
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
					
				}
				rs_owner.close();
				st_owner.close();
				//evt.setOwner_phone(list_ownerphone);
				
				ArrayList<String> list = new ArrayList<>();
				for(int i=0;i<list_ownerphone.size();i++){
					list.add(list_ownerphone.get(i).getPhone_no());
				}
				evt.setOwner_phone(list);
				
				
				List<SO_Res_ListOwnerPhoneBean>list_trustphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();
				evt.setReceiver_phone(list_trustphone);
				
				
				List<SO_Res_ListSaleOrderItemBean> list_item_so = new ArrayList<SO_Res_ListSaleOrderItemBean>();
				
				Statement st_sub = conn.getSqlStatement(db.getServerName(), db.getDatabaseName());
				vQuerySub = "exec dbo.USP_API_SearchSaleOrderItemNotBilling '"+company.getBranchCode()+"','"+company.getWhCode()+"','"+rs.getString("docno")+"'";
				System.out.println(vQuerySub);
				ResultSet rs_sub = st_sub.executeQuery(vQuerySub);
				
				SO_Res_ListSaleOrderItemBean evt_item;
				list_item_so.clear();
				while(rs_sub.next()){
					System.out.println("count_row"+rs_sub.getString("barcode"));
					evt_item = new SO_Res_ListSaleOrderItemBean();
					evt_item.setItem_code(rs_sub.getString("itemcode"));
					evt_item.setItem_name(rs_sub.getString("itemname"));
					evt_item.setItem_file_path(rs_sub.getString("picfilename1"));
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
					evt_item.setItem_category(rs_sub.getString("categorycode"));
					evt_item.setItem_remark(rs_sub.getString("remark"));
					evt_item.setItem_short_code(rs_sub.getString("shortcode"));
					evt_item.setItem_barcode(rs_sub.getString("barcode"));
					evt_item.setPick_zone_id(rs_sub.getString("zoneid"));
					list_item_so.add(evt_item);
				}
				
				rs_sub.close();
				st_sub.close();
				
				evt.setList_item(list_item_so);
				
				list_so.add(evt);
				
			}
			sale_order.setError(false);
			sale_order.setSuccess(true);
			sale_order.setMessage("");
			rs.close();
			st.close();
		} catch (SQLException e) {
			sale_order.setError(true);
			sale_order.setSuccess(false);
			sale_order.setMessage(e.getMessage());
			
			System.out.println("error :"+list_so.get(0).getDoc_no());
			
			so.setError(true);
			so.setMessage(e.getMessage());
		}finally{
			conn.close();
		}
		
		//System.out.println("เลขที่เอกสาร :"+list_so.get(0).getDoc_no());
		sale_order.setSale_order(list_so);
		return sale_order;
	}
	
	public SO_Res_GenQueueRespBean SaleOrderToDriveThru (String dbName,DT_User_LoginBranchBean db,SO_Req_GenOTPSaleOrderBean req){
		getDataFromData getData = new getDataFromData();
		//PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		SO_Res_ItemSaleOrderBean item_saleorder = new SO_Res_ItemSaleOrderBean();
		PK_Resp_ManageItemListBean itemList = new PK_Resp_ManageItemListBean();
		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
		
		//DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		
		LoginBean userCode = new LoginBean();
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		String vQuery;
		String vQuerySub;
		String vQueryLog;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		String vGenNewDocNo;
		int qId=0;
		String vQueryHead;
		boolean isSuccess;
		CT_Resp_ResponseBean check_itemsaleorder;
		
		String otp_password= getdb.GenPinOTP();
		//String keyPass  = bcrypt.hashPassword(otp_password);
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

		int vCountToken = 0 ;
		int status = 0;
		
		System.out.println("ItemCount"+req.getItem().size());
		
//		for(int i=0;i<req.getItem().size();i++){
//			System.out.println("Check i = " +i);
//			System.out.println("Check Item1 = "+req.getItem().get(i).getItem_code());
//		}
		
		check_itemsaleorder = data.verifyItemSaleOrder(db, req);
		if ((req.getDelivery_type() == 0 && req.getPlate_number() != "" && req.getCar_brand() != "") || (req.getDelivery_type()==1)) {
		if (req.getReceiver_name() !="" && req.getReceiver_phone().size()>0){
			if (req.getItem().size()!=0){
				if(check_itemsaleorder.getIsSuccess()==true){

					//			db.setDbName("bcnp");
					//			db.setServerName("192.168.0.7"); DT_User_LoginBranchBean db

					vGenNewDocNo = gen_no.genDocNo(0);
					qId = gen_no.genqId();

					saleOrder = data.checkSaleOrderData(db,req.getDoc_no());

					saleCode = saleOrder.getSale_code();
					saleName = saleOrder.getSale_name();

					try{

						vQueryHead ="insert into Queue(docNo,docDate,status,isCancel,customerId,warehouseId,billType,deliveryType,customerCode,customerName,whCode,carLicence,carBrand,creatorCode,createDate,qId,saleCode,saleName,docType,saleorder,OTPCode,pickstatus,receiveName,pickupDatetime,numberofitem,keypass,taxtype) values("+"'"+vGenNewDocNo+"',CURDATE(),0,0,0,0,"+req.getBill_type()+","+req.getDelivery_type()+",'"+saleOrder.getAr_code()+"','"+saleOrder.getAr_name()+"','S1-B','"+req.getPlate_number()+"','"+req.getCar_brand()+"','"+saleCode+"',CURRENT_TIMESTAMP,"+qId+",'"+saleCode+"','"+saleName+"',1,'"+req.getDoc_no()+"','"+otp_password+"',0,'"+req.getReceiver_name()+"','"+req.getPickup_datetime()+"',"+req.getItem().size()+",'"+otp_password+"',"+req.getTax_type()+")";
						System.out.println(vQueryHead);
						isSuccess= excecute.execute(dbName,vQueryHead);

						vQuery ="insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values('"+otp_password+"',"+qId+",'"+vGenNewDocNo+"',0,CURRENT_TIMESTAMP)";
						System.out.println(vQuery);
						isSuccess= excecute.execute(dbName,vQuery);

						System.out.println("owner = "+req.getOwner_phone().size());
						String vQueryOwner;
						if(req.getOwner_phone().size()!=0){
							for(int a=0;a<(req.getOwner_phone().size());a++){
								vQueryOwner = "insert into OrderOwnerPhone(doc_no,queue_id,sale_order,doc_date,otp_code,phone_no) values('"+vGenNewDocNo+"',"+qId+",'"+req.getDoc_no()+"',CURDATE(),'"+otp_password+"','"+req.getOwner_phone().get(a).getPhone_no()+"')";
								System.out.println("vQueryOwner="+vQueryOwner);
								isSuccess= excecute.execute(dbName,vQueryOwner);
							}
						}

						System.out.println("trust = "+req.getReceiver_phone().size());

						String vQueryTrust;
						if(req.getReceiver_phone().size()!=0){
							for(int b=0;b<(req.getReceiver_phone().size());b++){
								vQueryTrust = "insert into OrderTrustPhone(doc_no,queue_id,sale_order,doc_date,otp_code,phone_no) values('"+vGenNewDocNo+"',"+qId+",'"+req.getDoc_no()+"',CURDATE(),'"+otp_password+"','"+req.getReceiver_phone().get(b).getPhone_no()+"')";
								System.out.println("vQueryTrust = "+vQueryTrust);
								isSuccess= excecute.execute(dbName,vQueryTrust);
							}
						}

						isSuccess = true;

						System.out.println("Item = "+req.getItem().size());

						for(int i=0;i<req.getItem().size();i++){
							System.out.println("i = " +i);
							System.out.println("Item1 = "+req.getItem().get(i).getItem_code());

							item_saleorder = data.checkItemSaleOrderData(db, req.getDoc_no(), req.getItem().get(i).getItem_code(), req.getItem().get(i).getItem_unit_code(), req.getItem().get(i).getLine_number());

							if (req.getItem().get(i).getItem_code()!=null && req.getItem().get(i).getItem_code()!=""){
								System.out.println("Item2 = "+req.getItem().get(i).getItem_code());	
								if (isSuccess==true){
									System.out.println("Item3 = "+req.getItem().get(i).getItem_code());
									if(status < 2) {
										vCheckExistItem = 0;

										itemPrice = item_saleorder.getItem_price();
										creatorCode = userCode.getEmployeeCode();

										if (saleCode == "" || saleCode == null) {
											//lastSale = getData.searchTopSaleCode(qId);
											saleCode = userCode.getEmployeeCode();//lastSale.getSaleCode();
											saleName = userCode.getEmployeeName();//lastSale.getSaleName();

											System.out.println("No Have SaleCode");


										}else{

											saleCode = saleOrder.getSale_code();
											saleName = saleOrder.getSale_name();
										}

										itemAmount = 0;//req.getItem().get(i).getRequest_qty()*itemPrice;

										System.out.println("SaleName : "+saleCode+"/"+saleName);

										//System.out.println("ItemAmount : "+itemAmount);

										//getBarData = getData.searchItemCode(req.getItem().get(i).getItem_barcode());

										//if (getBarData.getCode()!=null && getBarData.getCode()!=""){
										if (req.getItem().get(i).getItem_code()!=null && req.getItem().get(i).getItem_code()!=""){
											if (vCheckExistItem==0 && req.getItem().get(i).getRequest_qty()>0){

												vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,unitCode,barCode,qty,reqQty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost,whcode,shelfcode,zoneid,pickzone) "+ "values("
														+qId+",'"+vGenNewDocNo+"',CURDATE(),'"+ req.getItem().get(i).getItem_code()+"','"+item_saleorder.getItem_name()+"','"+req.getItem().get(i).getItem_unit_code()+"','"+req.getItem().get(i).getItem_barcode()+"',"+ item_saleorder.getRemain_qty()+","+req.getItem().get(i).getRequest_qty()+",0,0,0,"+itemPrice+","+itemAmount+","+item_saleorder.getItem_rate()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,0,"+req.getItem().get(i).getLine_number()+",'"+saleCode+"','"+saleName+"','"+item_saleorder.getItem_expert_code()+"','"+item_saleorder.getItem_department_code()+"','"+item_saleorder.getItem_depart_name()+"','"+item_saleorder.getItem_category_code()+"','"+item_saleorder.getItem_category_name()+"','"+item_saleorder.getSec_code()+"','"+item_saleorder.getSec_name()+"',"+item_saleorder.getItem_average()+",'"+item_saleorder.getWh_code()+"','"+item_saleorder.getShelf_code()+"','"+item_saleorder.getZone_id()+"','"+item_saleorder.getPick_zone()+"' )";
												System.out.println("QuerySub :"+vQuery);
												isSuccess= excecute.execute(dbName,vQuery);

												vQueryLog = "exec dbo.USP_NP_InsertPickItemDriveThru '"+req.getDoc_no()+"','"+otp_password+"','"+req.getPlate_number()+"','"+req.getItem().get(i).getItem_code()+"',"+req.getItem().get(i).getRequest_qty()+",'"+req.getItem().get(i).getItem_unit_code()+"','"+item_saleorder.getWh_code()+"','"+item_saleorder.getShelf_code()+"','','',0,"+req.getItem().get(i).getLine_number();
												System.out.println("vQueryLog = "+vQueryLog);

												isSuccess= npExec.executeSqlBranch(db,vQueryLog);

											}else{
												vQuery = "update QItem set qty ="+req.getItem().get(i).getRequest_qty()+",reqQty="+req.getItem().get(i).getRequest_qty()+",price ="+itemPrice +",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"' where qId = "+qId+" and docNo ='"+vGenNewDocNo+"' and itemCode='"+req.getItem().get(i).getItem_code()+"' and barCode ='"+req.getItem().get(i).getItem_barcode()+"' and unitCode ='"+req.getItem().get(i).getItem_unit_code()+"' and lineNumber ="+req.getItem().get(i).getLine_number();
												System.out.println("QuerySub :"+vQuery);
												isSuccess= excecute.execute(dbName,vQuery);
											}

											vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+vGenNewDocNo+"') where docNo ='"+vGenNewDocNo+"'";
											System.out.println("vQuerySub = "+vQuerySub);

											isSuccess= excecute.execute(dbName,vQuerySub);

											itemList.setBarCode(req.getItem().get(i).getItem_barcode());
											itemList.setFilePath(getData.getItemFilePath(req.getItem().get(i).getItem_barcode()));
											itemList.setItemCategory(item_saleorder.getItem_category_code());
											itemList.setItemCode(item_saleorder.getItem_code());
											itemList.setItemName(item_saleorder.getItem_name());
											itemList.setItemPrice(itemPrice);
											itemList.setRemark("");
											itemList.setShortCode(req.getItem().get(i).getItem_barcode());
											itemList.setUnitCode(req.getItem().get(i).getItem_unit_code());

											resp_item.setItem(itemList);
											//resp_item

											queue.setSuccess(true);
											queue.setError(false);
											queue.setMessage("");


										}else{

											isSuccess=false;
											queue.setSuccess(false);
											queue.setError(true);
											queue.setMessage("No have barcode");

											resp_item.setItem(itemList);
										}
									}else{
										isSuccess=false;
										queue.setSuccess(false);
										queue.setError(true);
										queue.setMessage("Queue status is used");

										resp_item.setItem(itemList);
									}

								}else{
									isSuccess=false;
									queue.setSuccess(false);
									queue.setError(true);
									queue.setMessage("Queue is cancel");

									resp_item.setItem(itemList);
								}
							}else{
								//==============================
								isSuccess=false;
								queue.setSuccess(false);
								queue.setError(true);
								queue.setMessage("Barcode is null");

								resp_item.setItem(itemList);
							}
						}

						String whCode;
						String shelfCode;
						String zoneID;
						String pickZone;
						String familyGroup;

						String vQuery1;
						String vQuery2;


						vQuery = "exec dbo.USP_NP_GroupItemZone '"+req.getDoc_no()+"','"+otp_password+"'";
						Statement st = conn.getSqlStatement(db.getServerName(), db.getDbName());
						ResultSet rs = st.executeQuery(vQuery);

						System.out.println("GroupZone ="+vQuery);
						while(rs.next()){
							whCode = rs.getString("whcode");
							shelfCode = rs.getString("shelfcode");
							zoneID = rs.getString("zoneid");
							pickZone = rs.getString("pickzone");
							familyGroup = rs.getString("familygroup");


							List<SO_Res_QueuePickupBean> queue_number =new ArrayList<SO_Res_QueuePickupBean>();

							vQuery2 = "exec dbo.USP_NP_InsertPickupFromDriveTrue '" + req.getDoc_no() + "','" +  otp_password + "','" +req.getPlate_number()+ "','" + whCode + "','" + shelfCode + "','" + zoneID + "','" + pickZone + "','" + familyGroup + "','" + req.getPickup_datetime() + "','" + saleCode + "'";
							System.out.println("vQuery2 :"+vQuery2);
							isSuccess = npExec.executeSqlBranch(db, vQuery2);

							String vQueryQueue;

							vQueryQueue = "exec dbo.USP_NP_SearchQueueDriveTrue '" + req.getDoc_no() + "'";
							Statement st1 = conn.getSqlStatement(db.getServerName(), db.getDbName());
							ResultSet rs1 = st1.executeQuery(vQueryQueue);
							SO_Res_QueuePickupBean evt1;
							while(rs1.next()){
								evt1 = new SO_Res_QueuePickupBean();
								evt1.setZone_id(rs1.getString("ZoneID"));
								evt1.setQueue_id(rs1.getInt("docno"));

								queue_number.add(evt1);
							}

							queue.setPickup_id(queue_number);

							//isSuccess= npExec.executeSqlBranch(db,vQuery2);
							rs1.close();
							st1.close();
						}
						rs.close();
						st.close();
					}catch(Exception e){
						isSuccess=false;
						queue.setSuccess(false);
						queue.setError(true);
						queue.setMessage(e.getMessage());
						//				response.setIsSuccess(false);
						//				response.setProcess("insertItemPickup");
						//				response.setProcessDesc("Error : "+e.getMessage());

						resp_item.setItem(itemList);
						//				resp_item.setResponse(response);
					}finally{
						ds.close();
					}

					try {
						Statement stq = ds.getStatement(dbName);
						vQuery = "call USP_DT_SearchQueueDetails ('"+qId+"')";
						ResultSet rsq = stq.executeQuery(vQuery);
						System.out.println(vQuery);

						System.out.println("Count"+rsq.getRow());
						while(rsq.next()){
							queuedata.setQueue_id(rsq.getInt("qid"));// queue_id;//: "XXXX",
							queuedata.setNumber_of_item(rsq.getDouble("numberOfItem"));// number_of_item;//, "10",
							queuedata.setTime_created(rsq.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
							queuedata.setStatus(rsq.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
							queuedata.setIs_cancel(rsq.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
							queuedata.setAr_code(rsq.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
							queuedata.setAr_name(rsq.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
							queuedata.setSale_code(rsq.getString("salecode"));
							queuedata.setSale_name(rsq.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
							queuedata.setDoc_no(rsq.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
							queuedata.setSource(rsq.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
							queuedata.setReceiver_name(rsq.getString("receiveName"));// receiver_name;//: "xxx xxx",
							queuedata.setDoc_date(rsq.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
							queuedata.setPickup_datetime(rsq.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
							queuedata.setTotal_amount(rsq.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
							queuedata.setIs_loaded(rsq.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
							queuedata.setCar_brand(rsq.getString("carBrand"));// car_brand;//: "toyota",
							queuedata.setPlate_number(rsq.getString("carLicence"));// plate_number;//: "กท7777",
							queuedata.setStatus_for_saleorder_current(rsq.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
							queuedata.setOtp_password(rsq.getString("otpcode"));
							System.out.println("ArName :"+rsq.getString("arname"));

							List<SO_Res_ListProductQueueBean>list_item = new ArrayList<SO_Res_ListProductQueueBean>();

							Statement st_item = ds.getStatement(dbName);
							vQuery = "call USP_DT_SearchQueueProduct ("+rsq.getInt("qid")+")";
							ResultSet rs_item = st_item.executeQuery(vQuery);
							System.out.println(vQuery);

							list_item.clear();
							SO_Res_ListProductQueueBean evt_item;
							System.out.println("Count"+rs_item.getRow());
							while(rs_item.next()){
								evt_item = new SO_Res_ListProductQueueBean();
								evt_item.setFile_path(rs_item.getString("filepath"));
								evt_item.setIs_cancel(rs_item.getInt("itemcancel"));
								evt_item.setIs_check(rs_item.getInt("ischeckout"));
								evt_item.setItem_barcode(rs_item.getString("barcode"));
								evt_item.setItem_code(rs_item.getString("itemcode"));
								evt_item.setItem_name(rs_item.getString("itemname"));
								evt_item.setPickup_staff_name(rs_item.getString("pickername"));
								evt_item.setItem_price(rs_item.getDouble("price"));
								evt_item.setQty_after(rs_item.getDouble("checkoutqty"));
								evt_item.setQty_before(rs_item.getDouble("pickqty"));
								evt_item.setRequest_qty(rs_item.getDouble("reqqty"));
								evt_item.setQty_load(rs_item.getDouble("loadqty"));
								evt_item.setTotal_price_after(rs_item.getDouble("checkoutamount"));
								evt_item.setTotal_price_before(rs_item.getDouble("itemamount"));
								evt_item.setItem_unit_code(rs_item.getString("unitcode"));
								evt_item.setLine_number(rs_item.getInt("linenumber"));
								evt_item.setItem_qty(rs_item.getDouble("qty"));

								list_item.add(evt_item);

							}

							queuedata.setItem(list_item);

							rs_item.close();
							st_item.close();

							List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
							String vQueryOwner;

							Statement st_owner = ds.getStatement(dbName);
							vQueryOwner = "call USP_DT_SearchOwnerPhone ('"+rsq.getString("docno")+"','"+rsq.getString("otpcode")+"')";
							System.out.println("vQueryOwner : "+vQueryOwner);
							ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);


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

							}
							rs_owner.close();
							st_owner.close();

							ArrayList<String> listowner = new ArrayList<>();
							for(int i=0;i<list_ownerphone.size();i++){
								listowner.add(list_ownerphone.get(i).getPhone_no());
							}

							queuedata.setOwner_phone(listowner);

							List<SO_Res_ListOwnerPhoneBean>list_receiverphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
							String vQueryReceiver;

							Statement st_receiver = ds.getStatement(dbName);
							vQueryReceiver = "call USP_DT_SearchReceiverPhone ('"+rsq.getString("docno")+"','"+rsq.getString("otpcode")+"')";
							System.out.println("vQueryReceiver : "+vQueryReceiver);
							ResultSet rs_receiver = st_receiver.executeQuery(vQueryReceiver);


							SO_Res_ListOwnerPhoneBean evt_receiverphone;
							SO_Res_ListOwnerPhoneBean evt_receiverphone1;
							list_receiverphone.clear();
							while(rs_receiver.next()){
								evt_receiverphone = new SO_Res_ListOwnerPhoneBean();
								evt_receiverphone1 = new SO_Res_ListOwnerPhoneBean();
								evt_receiverphone.setPhone_no(rs_receiver.getString("phone_no"));

								if(rs_receiver.getRow()!=0){
									list_receiverphone.add(evt_receiverphone);
								}else{
									list_receiverphone.add(evt_receiverphone1);
								}

							}
							rs_receiver.close();
							st_receiver.close();

							ArrayList<String> listreceiver = new ArrayList<>();
							for(int i=0;i<list_receiverphone.size();i++){
								listreceiver.add(list_receiverphone.get(i).getPhone_no());
							}

							queuedata.setReceiver_phone(listreceiver);


							List<SO_Res_ListQueueStatusBean>list_queuestatus = new ArrayList<SO_Res_ListQueueStatusBean>();				
							String vQueryStatus;

							Statement st_status = ds.getStatement(dbName);
							vQueryStatus = "call USP_DT_SearchQueueStatusHistory ('"+rsq.getString("docno")+"','"+rsq.getString("otpcode")+"')";
							System.out.println("vQueryStatus : "+vQueryStatus);
							ResultSet rs_status = st_status.executeQuery(vQueryStatus);


							SO_Res_ListQueueStatusBean evt_status;
							SO_Res_ListQueueStatusBean evt_status1;
							list_queuestatus.clear();
							while(rs_status.next()){
								evt_status = new SO_Res_ListQueueStatusBean();
								evt_status1 = new SO_Res_ListQueueStatusBean();
								evt_status.setStatus_id(rs_status.getInt("pick_status"));
								evt_status.setStatus_for_saleorder(rs_status.getString("pick_status"));
								evt_status.setCreate_datetime(rs_status.getString("insertdatetime"));

								if(rs_status.getRow()!=0){
									list_queuestatus.add(evt_status);
								}else{
									list_queuestatus.add(evt_status1);
								}

							}
							rs_status.close();
							st_status.close();

							queuedata.setStatus_for_saleorder_history(list_queuestatus);

						}

						rsq.close();
						stq.close();

					}catch (SQLException e) {
						System.out.println(e.getMessage());
					}finally{
						ds.close();
					}	


				}
				else{
					isSuccess=false;
					queue.setSuccess(false);
					queue.setError(true);
					queue.setMessage("Item request qty over remain qty");
				}

			}else{
				isSuccess=false;
				queue.setSuccess(false);
				queue.setError(true);
				queue.setMessage("This saleorder not have item");
			}
		}else{
			isSuccess=false;
			queue.setSuccess(false);
			queue.setError(true);
			queue.setMessage("This saleorder not have receive_name or receive_phone");
		}
		}else {
			isSuccess=false;
			queue.setSuccess(false);
			queue.setError(true);
			queue.setMessage("This saleorder not have plate_number and car_brand");	
		}
		
		queue.setQueue(queuedata);

		return queue;
	}
	
	
	
	public SO_Res_SearchQueueDataBean SearchQueueList(String dbName,SO_Req_SearchQueueDataBean req){
		String vQuery;
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		db.setDbName("bcnp");
		db.setServerName("192.168.0.7"); 
		boolean checkData;
		
		//call USP_DT_SearchListQueue('','','',0,'1')
		try {
					
			Statement st = ds.getStatement(dbName);
			vQuery = "call USP_DT_SearchListQueue ('"+req.getPickup_date()+"','"+req.getCreated_date()+"',"+req.getStatus_for_saleorder_current()+",'"+req.getPage()+"','"+req.getKeyword()+"',"+req.getQueue_id()+")";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			
			list_so.clear();
			SO_Res_ListSearchQueueDataBean evt;
			System.out.println("Count"+rs.getRow());
			while(rs.next()){
				System.out.println("arname = "+rs.getString("arname"));
				evt = new SO_Res_ListSearchQueueDataBean();
				evt.setQueue_id(rs.getInt("qid"));// queue_id;//: "XXXX",
				evt.setNumber_of_item(rs.getDouble("numberOfItem"));// number_of_item;//, "10",
				evt.setTime_created(rs.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
				evt.setStatus(rs.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
				evt.setIs_cancel(rs.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
				evt.setAr_code(rs.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
				evt.setAr_name(rs.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
				evt.setSale_code(rs.getString("salecode"));
				evt.setSale_name(rs.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
				evt.setDoc_no(rs.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
				evt.setSource(rs.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
				evt.setReceiver_name(rs.getString("receiveName"));// receiver_name;//: "xxx xxx",
				evt.setDoc_date(rs.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
				evt.setPickup_datetime(rs.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
				evt.setTotal_amount(rs.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
				evt.setIs_loaded(rs.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
				evt.setCar_brand(rs.getString("carBrand"));// car_brand;//: "toyota",
				evt.setPlate_number(rs.getString("carLicence"));// plate_number;//: "กท7777",
				evt.setStatus_for_saleorder_current(rs.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
				evt.setTotal_before_amount(rs.getDouble("totalamount"));
				evt.setTotal_after_amount(rs.getDouble("checkoutamount"));
				evt.setOtp_password(rs.getString("OTPCode"));
				evt.setBill_type(rs.getInt("billtype"));
				evt.setWho_cancel(rs.getString("cancelcode"));
				evt.setCancel_remark(rs.getString("cancelremark"));
				System.out.println("BillType :"+rs.getInt("billtype"));
				
				System.out.println("ID :"+req.getQueue_id());
				
				
				if  (req.getQueue_id()!=null) {
					System.out.println("Queue ID = "+ req.getQueue_id());
					checkData = data.updateSaleOrderQtyRequestData(db, dbName, req.getQueue_id(), rs.getString("saleorder"));
				}
				
				List<SO_Res_ListProductQueueBean>list_item = new ArrayList<SO_Res_ListProductQueueBean>();
				
				try {
				Statement st_item = ds.getStatement(dbName);
				vQuery = "call USP_DT_SearchQueueProduct ("+rs.getInt("qid")+")";
				ResultSet rs_item = st_item.executeQuery(vQuery);
				System.out.println(vQuery);
				
				list_item.clear();
				SO_Res_ListProductQueueBean evt_item;
				System.out.println("Count"+rs_item.getRow());
				while(rs_item.next()){
					evt_item = new SO_Res_ListProductQueueBean();
					evt_item.setFile_path(rs_item.getString("filepath"));
					evt_item.setIs_cancel(rs_item.getInt("itemcancel"));
					evt_item.setIs_check(rs_item.getInt("ischeckout"));
					evt_item.setItem_barcode(rs_item.getString("barcode"));
					evt_item.setItem_code(rs_item.getString("itemcode"));
					evt_item.setItem_name(rs_item.getString("itemname"));
					evt_item.setPickup_staff_name(rs_item.getString("pickername"));
					evt_item.setSale_code(rs_item.getString("salecode"));
					evt_item.setItem_price(rs_item.getDouble("price"));
					evt_item.setQty_after(rs_item.getDouble("checkoutqty"));
					evt_item.setQty_before(rs_item.getDouble("pickqty"));
					evt_item.setRequest_qty(rs_item.getDouble("reqqty"));
					evt_item.setQty_load(rs_item.getDouble("loadqty"));
					evt_item.setTotal_price_after(rs_item.getDouble("checkoutamount"));
					evt_item.setTotal_price_before(rs_item.getDouble("itemamount"));
					evt_item.setItem_unit_code(rs_item.getString("unitcode"));
					evt_item.setLine_number(rs_item.getInt("linenumber"));
					evt_item.setItem_qty(rs_item.getDouble("qty"));
					evt_item.setPick_zone_id(rs_item.getString("zoneid"));
					
					list_item.add(evt_item);

				}
				
				rs_item.close();
				st_item.close();
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					ds.close();	
				}
				
				
				evt.setItem(list_item);
				
				
				List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
				String vQueryOwner;
				try{
				Statement st_owner = ds.getStatement(dbName);
				vQueryOwner = "call USP_DT_SearchOwnerPhone ('"+rs.getString("docno")+"','"+rs.getString("otpcode")+"')";
				System.out.println("vQueryOwner : "+vQueryOwner);
				ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
				
				
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
					
				}
				rs_owner.close();
				st_owner.close();
				} catch(Exception e){
					
				}finally{
					ds.close();
				}
			
				ArrayList<String> listowner = new ArrayList<>();
				for(int i=0;i<list_ownerphone.size();i++){
					listowner.add(list_ownerphone.get(i).getPhone_no());
				}
				
				evt.setOwner_phone(listowner);
				
				List<SO_Res_ListOwnerPhoneBean>list_receiverphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
				String vQueryReceiver;
				try{
				Statement st_receiver = ds.getStatement(dbName);
				vQueryReceiver = "call USP_DT_SearchReceiverPhone ('"+rs.getString("docno")+"','"+rs.getString("otpcode")+"')";
				System.out.println("vQueryReceiver : "+vQueryReceiver);
				ResultSet rs_receiver = st_receiver.executeQuery(vQueryReceiver);
				
				
				SO_Res_ListOwnerPhoneBean evt_receiverphone;
				SO_Res_ListOwnerPhoneBean evt_receiverphone1;
				list_receiverphone.clear();
				while(rs_receiver.next()){
					evt_receiverphone = new SO_Res_ListOwnerPhoneBean();
					evt_receiverphone1 = new SO_Res_ListOwnerPhoneBean();
					evt_receiverphone.setPhone_no(rs_receiver.getString("phone_no"));
					
					if(rs_receiver.getRow()!=0){
						list_receiverphone.add(evt_receiverphone);
					}else{
						list_receiverphone.add(evt_receiverphone1);
					}
					
				}
				rs_receiver.close();
				st_receiver.close();
				} catch(Exception e){
					
				}finally{
					ds.close();
				}
			
				ArrayList<String> listreceiver = new ArrayList<>();
				for(int i=0;i<list_receiverphone.size();i++){
					listreceiver.add(list_receiverphone.get(i).getPhone_no());
				}
				
				evt.setReceiver_phone(listreceiver);
				
				
				List<SO_Res_ListQueueStatusBean>list_queuestatus = new ArrayList<SO_Res_ListQueueStatusBean>();				
				String vQueryStatus;
				
				try{
				Statement st_status = ds.getStatement(dbName);
				vQueryStatus = "call USP_DT_SearchQueueStatusHistory ('"+rs.getString("docno")+"','"+rs.getString("otpcode")+"')";
				System.out.println("vQueryStatus : "+vQueryStatus);
				ResultSet rs_status = st_status.executeQuery(vQueryStatus);
				
				
				SO_Res_ListQueueStatusBean evt_status;
				SO_Res_ListQueueStatusBean evt_status1;
				list_queuestatus.clear();
				while(rs_status.next()){
					evt_status = new SO_Res_ListQueueStatusBean();
					evt_status1 = new SO_Res_ListQueueStatusBean();
					evt_status.setStatus_id(rs_status.getInt("pick_status"));
					evt_status.setStatus_for_saleorder(rs_status.getString("pick_status"));
					evt_status.setCreate_datetime(rs_status.getString("insertdatetime"));
					
					if(rs_status.getRow()!=0){
						list_queuestatus.add(evt_status);
					}else{
						list_queuestatus.add(evt_status1);
					}
					
				}
				rs_status.close();
				st_status.close();
				} catch(Exception e){
					
				}finally{
					ds.close();
				}
				
				evt.setStatus_for_saleorder_history(list_queuestatus);
				
				listQueue.add(evt);
				
				que_data.setSuccess(true);
				que_data.setError(false);
				que_data.setMessage("");
			}
			
			rs.close();
			st.close();
			
		}catch (SQLException e) {
			que_data.setSuccess(false);
			que_data.setError(true);
			que_data.setMessage(e.getMessage());
			
			//System.out.println("error :"+list_so.get(0).getDoc_no());
			
			so.setError(true);
			so.setMessage(e.getMessage());
			
		}finally{
			ds.close();
		}
		
		que_data.setOrder(listQueue);
		return que_data;
	}
	
	public QU_Res_QueueDataBean QueueProduct(String dbName,SO_Req_QueueProductBean req){
		String vQuery;
		String vQuerySub;
		try {
			Statement st = ds.getStatement(dbName);
			//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
			vQuery = "call USP_DT_QueueData ("+req.getQueue_id()+")";
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			
			System.out.println("Count"+rs.getRow());
			while(rs.next()){
				
				queuedata.setQueue_id(rs.getInt("qid"));// queue_id;//: "XXXX",
				queuedata.setNumber_of_item(rs.getDouble("numberOfItem"));// number_of_item;//, "10",
				queuedata.setTime_created(rs.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
				queuedata.setStatus(rs.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
				queuedata.setIs_cancel(rs.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
				queuedata.setAr_code(rs.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
				queuedata.setAr_name(rs.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
				queuedata.setSale_code(rs.getString("salecode"));
				queuedata.setSale_name(rs.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
				queuedata.setDoc_no(rs.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
				queuedata.setSource(rs.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
				queuedata.setReceiver_name(rs.getString("receiveName"));// receiver_name;//: "xxx xxx",
				queuedata.setDoc_date(rs.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
				queuedata.setPickup_datetime(rs.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
				queuedata.setTotal_amount(rs.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
				queuedata.setIs_loaded(rs.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
				queuedata.setCar_brand(rs.getString("carBrand"));// car_brand;//: "toyota",
				queuedata.setPlate_number(rs.getString("carLicence"));// plate_number;//: "กท7777",
				queuedata.setStatus_for_saleorder_current(rs.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
				queuedata.setTotal_before_amount(rs.getDouble("totalamount"));
				queuedata.setTotal_after_amount(rs.getDouble("checkoutamount"));
				queuedata.setWho_cancel(rs.getString("cancelcode"));
				queuedata.setCancel_remark(rs.getString("cancelremark"));

				try{
				Statement st_Sub = ds.getStatement(dbName);
				vQuerySub = "call USP_DT_QueueListItem ('"+rs.getInt("qid")+"')";
				ResultSet rs_Sub = st_Sub.executeQuery(vQuerySub);
				System.out.println(vQuerySub);
				
				listproduct.clear();
				SO_Res_ListProductQueueBean evt;
				System.out.println("Count"+rs.getRow());
				while(rs_Sub.next()){
					
				evt = new SO_Res_ListProductQueueBean();
				//evt.setFile_path(rs_Sub.getString("filepath"));
				evt.setFile_path(data.getItemFilePath(rs_Sub.getString("itemCode")));
				evt.setIs_cancel(rs_Sub.getInt("itemcancel"));
				evt.setIs_check(rs_Sub.getInt("ischeckout"));
				evt.setItem_barcode(rs_Sub.getString("barcode"));
				evt.setItem_code(rs_Sub.getString("itemcode"));
				evt.setItem_name(rs_Sub.getString("itemname"));
				evt.setPickup_staff_name(rs_Sub.getString("pickername"));
				evt.setSale_code(rs_Sub.getString("salecode"));
				evt.setItem_price(rs_Sub.getDouble("price"));
				evt.setQty_after(rs_Sub.getDouble("checkoutqty"));
				evt.setQty_before(rs_Sub.getDouble("pickqty"));
				evt.setRequest_qty(rs_Sub.getDouble("reqqty"));
				evt.setQty_load(rs_Sub.getDouble("loadqty"));
				evt.setTotal_price_after(rs_Sub.getDouble("checkoutamount"));
				evt.setTotal_price_before(rs_Sub.getDouble("itemamount"));
				evt.setItem_unit_code(rs_Sub.getString("unitcode"));
				evt.setLine_number(rs_Sub.getInt("linenumber"));
				evt.setItem_qty(rs_Sub.getDouble("qty"));
				evt.setPick_zone_id(rs_Sub.getString("zoneid"));
				
				listproduct.add(evt);
				}
				rs_Sub.close();
				st_Sub.close();
				} catch(Exception e){
					
				}finally{
					ds.close();
				}
				

			}
			
			rs.close();
			st.close();
			
			queuedata.setItem(listproduct);
			
			qDetails.setSuccess(true);
			qDetails.setError(false);
			qDetails.setMessage("");
			
		}catch (SQLException e) {
			qDetails.setSuccess(false);
			qDetails.setError(true);
			qDetails.setMessage(e.getMessage());
			
		}finally{
			ds.close();
		}
		
		qDetails.setQueue(queuedata);
		return qDetails;
	} 
	
	
	public SO_Res_Response CancelQueueDriveThru(String dbName,SO_Req_QueueId req){
		String vQuery;
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		SO_Res_Response resp = new SO_Res_Response();
		String creatorCode;
		
		userCode = getData.searchUserAccessToken(req.getAccess_token());
		creatorCode = userCode.getEmployeeCode();
		
		if (req.getQid()!=0){
			getQueue = getData.searchQueue(req.getQid());

			if (getQueue.getIsCancel()==0){

				if (getQueue.getPickStatus()!=2){
					vQuery = "update Queue set status = 0,pickstatus=4,iscancel=1,cancelremark='"+ req.getCancel_remark()+"',cancelcode = '"+creatorCode+"',canceldate= CURRENT_TIMESTAMP() where qid = "+req.getQid()+" and docdate = curdate()";
					System.out.println(vQuery);
					isSuccess= excecute.execute(dbName,vQuery);
					
					resp.setSuccess(true);
					resp.setError(false);
				}
			}
		}
		return resp;			
	}

	public SO_Res_PickingManageProductBean PickupManageProduct(String dbName,SO_Req_PickingManageProductBean reqItem){
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
		
		LoginBean userCode = new LoginBean();
		String vQuery="";
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		double vQty=0.0;
		String pick_zone_id;


		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken = 0 ;

		System.out.println("MangeItem");

		SO_Res_CheckQueueItemBean itemExist = new SO_Res_CheckQueueItemBean();

		if (reqItem.getQueue_id()!=0){
			if (reqItem.getItem_barcode()!=null && reqItem.getItem_barcode()!=""){
				System.out.println("1");
				getQueue = getData.searchQueue(reqItem.getQueue_id());

				if (getQueue.getIsCancel()==0){

					if (getQueue.getPickStatus()!=2){

						System.out.println("Error : "+getQueue.getStatus());

						if(getQueue.getStatus() < 2 && getQueue.getPickStatus()!= 2) {
							getBarData = getData.searchItemCode(reqItem.getItem_barcode());
							userCode = getData.searchUserAccessToken(reqItem.getAccess_token());
							itemExist = getData.checkItemExistPickupProduct(reqItem);
							if(itemExist.getItem_exist()==0 && itemExist.getItem_source() == 0){
								itemPrice = getData.searchItemPrice(getBarData.getCode(),reqItem.getItem_barcode(), getBarData.getUnitCode());
							}else{
								itemPrice = itemExist.getItem_price();
							}

							creatorCode = userCode.getEmployeeCode();

							vCheckExistItem =  itemExist.getItem_exist();

							if (reqItem.getSale_code()==""  || reqItem.getSale_code() == null) {
								//lastSale = getData.searchTopSaleCode(reqItem.getQueue_id());
								saleCode = userCode.getEmployeeCode();//lastSale.getSaleCode();
								saleName = userCode.getEmployeeName();//lastSale.getSaleName();

								System.out.println("No Have SaleCode");

							}else{
								sale = getData.searchSaleCode(reqItem.getSale_code());
								System.out.println("Have SaleCode");
								if (sale.getIsExist()==1){
									saleCode = sale.getSaleCode();
									saleName = sale.getSaleName();
								}else{
									saleCode = "N/A";
									saleName = "-";
								}

							}

							itemAmount = itemPrice*reqItem.getQty_before();

							System.out.println("SaleName : "+saleCode+"/"+saleName);

							//System.out.println("ItemAmount : "+itemAmount);
							System.out.println("Source="+itemExist.getItem_source());
							System.out.println("BillType="+itemExist.getBill_type());
							System.out.println("getQty_before="+itemExist.getQty_before());

							//System.out.println("Item_Source1 = "+itemExist.getItem_source());
							if (getBarData.getCode()!=null && getBarData.getCode()!=""){
								if((reqItem.getQty_before()<=itemExist.getRequest_qty() && itemExist.getItem_source() == 1 && itemExist.getBill_type()==1) || (itemExist.getItem_source()==0)||(itemExist.getItem_source() == 1 && itemExist.getBill_type()==0)){

									System.out.println("Item_Source2 = "+itemExist.getItem_source());
									
									if (itemExist.getItem_source()==0){
										vQty = itemExist.getQty_before();
									}else{
										vQty = itemExist.getSale_qty();
									}
									
									if (vCheckExistItem==0){
										if ((itemExist.getItem_source()==0)||(itemExist.getItem_source()==1 && itemExist.getBill_type()==0)){
											vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,unitCode,barCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost,zoneId) "+ "values("
													+reqItem.getQueue_id()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getItem_barcode()+"',"+ vQty+","+reqItem.getQty_before()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIs_cancel()+","+reqItem.getLine_number()+",'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+",'"+getBarData.getZoneId()+"' )";
											//+reqItem.getqId()+",'"+getQueue.getDocNo()+"','"+dateFormat.format(dateNow)+"','"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+getBarData.getUnitCode()+"','"+reqItem.getBarCode()+"',"+ reqItem.getQtyBefore()+","+reqItem.getQtyBefore()+",0,0,"+itemPrice+","+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqItem.getIsCancel()+",0,'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+" )";
											isSuccess= excecute.execute(dbName,vQuery);

											vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+getQueue.getDocNo()+"') where docNo ='"+getQueue.getDocNo()+"'";

											System.out.println("vQuerySub = "+vQuerySub);

											isSuccess= excecute.execute(dbName,vQuerySub);

											resItem.setSuccess(true);
											resItem.setError(false);
											resItem.setMessage("");

										}else{
											System.out.println("Source = "+itemExist.getItem_source());
											resItem.setItem(listproduct);
											resItem.setSuccess(false);
											resItem.setError(true);
											resItem.setMessage("Sale Order can not add new item");
										}
									}else{
										if((itemExist.getItem_source()==0 && reqItem.getIs_cancel()==0 && reqItem.getQty_before()!= 0)||(itemExist.getItem_source()==1 && reqItem.getQty_before()!=0 && itemExist.getBill_type() == 0) || (itemExist.getItem_source()==1 && reqItem.getQty_before()<= itemExist.getSale_qty() && itemExist.getBill_type() == 1)){
											vQuery = "update QItem set qty ="+vQty+",pickQty="+reqItem.getQty_before()+",price ="+itemPrice+",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"',pickerCode ='"+userCode.getEmployeeCode()+"' where qId = "+reqItem.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getItem_barcode()+"' and unitCode ='"+getBarData.getUnitCode()+"' and lineNumber ="+reqItem.getLine_number();
										}else{
											vQuery = "update QItem set qty=0,pickQty = 0,itemAmount=0,isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where qId = "+reqItem.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getItem_barcode()+"' and unitCode ='"+getBarData.getUnitCode()+"' and lineNumber = "+reqItem.getLine_number();
										}

										isSuccess= excecute.execute(dbName,vQuery);

										resItem.setSuccess(true);
										resItem.setError(false);
										resItem.setMessage("");

									}
									System.out.println(vQuery);

									if(resItem.isSuccess()==true){
										try{

												Statement st = ds.getStatement(dbName);
												//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
												vQuery = "call USP_DT_QueueDataItem ("+reqItem.getQueue_id()+",'"+getBarData.getCode()+"')";
												ResultSet rs = st.executeQuery(vQuery);
												System.out.println(vQuery);
												
												listproduct.clear();
												SO_Res_ListProductQueueBean evt;
												System.out.println("Count"+rs.getRow());
												while(rs.next()){
													
													evt = new SO_Res_ListProductQueueBean();
													evt.setFile_path(rs.getString("filepath"));
													evt.setIs_cancel(rs.getInt("itemcancel"));
													evt.setIs_check(rs.getInt("ischeckout"));
													evt.setItem_barcode(rs.getString("barcode"));
													evt.setItem_code(rs.getString("itemcode"));
													evt.setItem_name(rs.getString("itemname"));
													evt.setPickup_staff_name(rs.getString("pickername"));
													evt.setSale_code(rs.getString("salecode"));
													evt.setItem_price(rs.getDouble("price"));
													evt.setQty_after(rs.getDouble("checkoutqty"));
													evt.setQty_before(rs.getDouble("pickqty"));
													evt.setRequest_qty(rs.getDouble("reqqty"));
													evt.setQty_load(rs.getDouble("loadqty"));
													evt.setTotal_price_after(rs.getDouble("checkoutamount"));
													evt.setTotal_price_before(rs.getDouble("itemamount"));
													evt.setItem_unit_code(rs.getString("unitcode"));
													evt.setLine_number(rs.getInt("linenumber"));
													evt.setItem_qty(rs.getDouble("qty"));
													
													listproduct.add(evt);

												}
												
												rs.close();
												st.close();
												
												queuedata.setItem(listproduct);
												

											resItem.setItem(listproduct);
											resItem.setSuccess(true);
											resItem.setError(false);
											resItem.setMessage("");

										}catch(Exception e){
											resItem.setItem(listproduct);
											resItem.setSuccess(false);
											resItem.setError(true);
											resItem.setMessage(e.getMessage());
										}finally{
											ds.close();
										}
									}
								}else{
									resItem.setItem(listproduct);
									resItem.setSuccess(false);
									resItem.setError(true);
									resItem.setMessage("Pick Qty more than request qty");
								}
							}else{
								resItem.setItem(listproduct);
								resItem.setSuccess(false);
								resItem.setError(true);
								resItem.setMessage("No have barcode");
							}
						}else{
							resItem.setItem(listproduct);
							resItem.setSuccess(false);
							resItem.setError(true);
							resItem.setMessage("Queue status is used");

						}

					}else{
						resItem.setItem(listproduct);
						resItem.setSuccess(false);
						resItem.setError(true);
						resItem.setMessage("Queue is not manage item becuase queue pick status not ready");

					}
				}else{
					resItem.setItem(listproduct);
					resItem.setSuccess(false);
					resItem.setError(true);
					resItem.setMessage("Queue is cancel");

				}
			}else{
				//==============================
				resItem.setItem(listproduct);
				resItem.setSuccess(false);
				resItem.setError(true);
				resItem.setMessage("Barcode is null");
			}
		}else{
			//===================================
			resItem.setItem(listproduct);
			resItem.setSuccess(false);
			resItem.setError(true);
			resItem.setMessage("qID not generate");
		}

		return resItem;

	}
	
	
	public SO_Res_PickingManageProductBean LoadManageProduct(String dbName,SO_Req_LoadManageProductBean reqItem){
		getDataFromData getData = new getDataFromData();
		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		PK_Resp_SaleCodeDetails lastSale = new PK_Resp_SaleCodeDetails();
		
		LoginBean userCode = new LoginBean();
		String vQuery="";
		String QueryCheckLoad="";
		String vQueryLoad="";
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";
		double vQty=0.0;
		String pick_zone_id;
		int checkCountUnLoad=0;


		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		int vCountToken = 0 ;

		System.out.println("LoadItem");

		SO_Res_CheckQueueItemBean itemExist = new SO_Res_CheckQueueItemBean();

		if (reqItem.getQueue_id()!=0){
			if (reqItem.getItem_barcode()!=null && reqItem.getItem_barcode()!=""){
				System.out.println("1");
				getQueue = getData.searchQueue(reqItem.getQueue_id());

				if (getQueue.getIsCancel()==0){
					
					if (getQueue.getIsCancel()==0){
						
					if (getQueue.getPickStatus()==1 && getQueue.getStatus() == 1 && getQueue.getDoctype() == 1){

						System.out.println("getQueue.getStatus : "+getQueue.getStatus());
						
							getBarData = getData.searchItemCode(reqItem.getItem_barcode());
							
							System.out.println("getQueue.getStatus : "+getBarData.getCode());

							if (getBarData.getCode()!=null && getBarData.getCode()!=""){
								
								if(reqItem.getQty_load() != 0){
				
									vQuery = "update QItem set loadqty ="+reqItem.getQty_load()+" where qId = "+reqItem.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and barCode ='"+reqItem.getItem_barcode()+"' and unitCode ='"+getBarData.getUnitCode()+"' and lineNumber ="+reqItem.getLine_number();
									System.out.println("vQuery LoadQty" + vQuery);
									isSuccess= excecute.execute(dbName,vQuery);
									
									try {
										Statement st_load = ds.getStatement(dbName);
										QueryCheckLoad = "select count(itemcode) as countItemLoad from QItem where loadqty = 0 and qid = "+reqItem.getQueue_id()+" and docno ='"+getQueue.getDocNo()+"'";
										ResultSet rs_load = st_load.executeQuery(QueryCheckLoad);
										while(rs_load.next()){
											checkCountUnLoad = rs_load.getInt("countItemLoad");
										}
										rs_load.close();
										st_load.close();
										
										System.out.println("Unload ="+checkCountUnLoad);
										
									} catch(Exception e) {
										resItem.setItem(listproduct);
										resItem.setSuccess(false);
										resItem.setError(true);
										resItem.setMessage(e.getMessage());
									} 
									
									if (checkCountUnLoad==0) {
										vQueryLoad = "update Queue set isload = 1  where qId = "+reqItem.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"'";
										System.out.println("vQuery LoadQty" + vQuery);
										isSuccess= excecute.execute(dbName,vQueryLoad);
									}
									
										try{									
												Statement st = ds.getStatement(dbName);
												vQuery = "call USP_DT_QueueDataItem ("+reqItem.getQueue_id()+",'"+getBarData.getCode()+"')";
												ResultSet rs = st.executeQuery(vQuery);
												System.out.println(vQuery);
												
												listproduct.clear();
												SO_Res_ListProductQueueBean evt;
												System.out.println("Count"+rs.getRow());
												while(rs.next()){
													
													evt = new SO_Res_ListProductQueueBean();
													evt.setFile_path(rs.getString("filepath"));
													evt.setIs_cancel(rs.getInt("itemcancel"));
													evt.setIs_check(rs.getInt("ischeckout"));
													evt.setItem_barcode(rs.getString("barcode"));
													evt.setItem_code(rs.getString("itemcode"));
													evt.setItem_name(rs.getString("itemname"));
													evt.setPickup_staff_name(rs.getString("pickername"));
													evt.setSale_code(rs.getString("salecode"));
													evt.setItem_price(rs.getDouble("price"));
													evt.setQty_after(rs.getDouble("checkoutqty"));
													evt.setQty_before(rs.getDouble("pickqty"));
													evt.setRequest_qty(rs.getDouble("reqqty"));
													evt.setQty_load(rs.getDouble("loadqty"));
													evt.setTotal_price_after(rs.getDouble("checkoutamount"));
													evt.setTotal_price_before(rs.getDouble("itemamount"));
													evt.setItem_unit_code(rs.getString("unitcode"));
													evt.setLine_number(rs.getInt("linenumber"));
													evt.setItem_qty(rs.getDouble("qty"));
													
													listproduct.add(evt);

												}
												
												rs.close();
												st.close();
												
												queuedata.setItem(listproduct);
												

											resItem.setItem(listproduct);
											resItem.setSuccess(true);
											resItem.setError(false);
											resItem.setMessage("");

										}catch(Exception e){
											resItem.setItem(listproduct);
											resItem.setSuccess(false);
											resItem.setError(true);
											resItem.setMessage(e.getMessage());
										}finally{
											ds.close();
										}
									
								}else{
									resItem.setItem(listproduct);
									resItem.setSuccess(false);
									resItem.setError(true);
									resItem.setMessage("Pick Qty more than request qty");
								}
							}else{
								resItem.setItem(listproduct);
								resItem.setSuccess(false);
								resItem.setError(true);
								resItem.setMessage("No have barcode");
							}

					}else{
						resItem.setItem(listproduct);
						resItem.setSuccess(false);
						resItem.setError(true);
						resItem.setMessage("Queue is not manage item becuase queue pick status not ready");

					}
				}else{
					resItem.setItem(listproduct);
					resItem.setSuccess(false);
					resItem.setError(true);
					resItem.setMessage("Queue is loaded");

				}
				}else{
					resItem.setItem(listproduct);
					resItem.setSuccess(false);
					resItem.setError(true);
					resItem.setMessage("Queue is cancel");

				}
			}else{
				//==============================
				resItem.setItem(listproduct);
				resItem.setSuccess(false);
				resItem.setError(true);
				resItem.setMessage("Barcode is null");
			}
		}else{
			//===================================
			resItem.setItem(listproduct);
			resItem.setSuccess(false);
			resItem.setError(true);
			resItem.setMessage("qID not generate");
		}

		return resItem;

	}
	
	public SO_Res_PickingManageProductBean ManageProductCheckOut(String dbName,SO_Req_CheckOutManageProductBean reqQueue){
		LoginBean userCode = new LoginBean();
		getDataFromData getData = new getDataFromData();

		PK_Resp_GetItemBarcodeBean getBarData = new PK_Resp_GetItemBarcodeBean();
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();

		//PK_Reqs_ManageItemBean reqItem = new PK_Reqs_ManageItemBean();

		SO_Res_CheckQueueItemBean itemExist = new SO_Res_CheckQueueItemBean();


		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		String vQuery="";
		String vQuerySub;
		String saleCode="";
		String saleName="";
		String creatorCode="";

		int vCountToken = 0;
		int vCheckExistItem=0;
		double itemPrice=0.0;
		double itemAmount=0.0;
		double vQty=0.0;


		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());

		creatorCode = userCode.getEmployeeCode();

		getBarData = getData.searchItemCode(reqQueue.getItem_barcode());
		getQueue = getData.searchQueue(reqQueue.getQueue_id());
		userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());

		itemExist = getData.checkItemExistCheckOutProduct(reqQueue);
		if(itemExist.getItem_exist()==0 && itemExist.getItem_source() == 0){
			itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getItem_barcode(), getBarData.getUnitCode());
		}else{
			itemPrice = itemExist.getItem_price();
		}

		if (reqQueue.getQueue_id()!=0){

			if (reqQueue.getItem_barcode()!=null){

				getQueue = getData.searchQueue(reqQueue.getQueue_id());

				if (getQueue.getIsCancel()==0){
					if(getQueue.getPickStatus() != 2){

						System.out.println("Status : "+getQueue.getStatus());

						if(getQueue.getStatus()!=3) {
							getBarData = getData.searchItemCode(reqQueue.getItem_barcode());
							userCode = getData.searchUserAccessToken(reqQueue.getAccess_token());

							System.out.println("EmployeeName="+userCode.getEmployeeName());
							vCheckExistItem = itemExist.getItem_exist();//getData.checkItemExistQueue(reqItem);
							if(itemExist.getItem_source()==0){
								itemPrice = getData.searchItemPrice(getBarData.getCode(),reqQueue.getItem_barcode(),getBarData.getUnitCode());
							}else{
								itemPrice = itemExist.getItem_price();
							}

							itemAmount = itemPrice*reqQueue.getQty_after();
							if (itemExist.getItem_source()==0){
								saleCode = userCode.getEmployeeCode();
								saleName = userCode.getEmployeeName();
							}else{
								saleCode = itemExist.getSale_code();
								saleName = itemExist.getSale_name();
							}

							if (getBarData.getCode()!=null){
								System.out.println("vCheckExistItem = "+vCheckExistItem);
								
								if (itemExist.getItem_source()==0){
									vQty = itemExist.getQty_after();
								}else{
									vQty = itemExist.getSale_qty();
								}
								
								if (vCheckExistItem==0){
									if(itemExist.getItem_source()==0 || (itemExist.getItem_source()==1 && itemExist.getBill_type()==0)){																				
										vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,barcode,unitCode,qty,pickQty,loadQty,checkoutQty,price,itemAmount,checkoutAmount,rate1,checkerCode,checkoutDate,isCancel,isCheckOut,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost,zoneId) "+ "values("
												+reqQueue.getQueue_id()+",'"+getQueue.getDocNo()+"',CURDATE(),'"+ getBarData.getCode()+"','"+getBarData.getItemName()+"','"+reqQueue.getItem_barcode()+"','"+getBarData.getUnitCode()+"',"+ vQty+",0,0,"+reqQueue.getQty_after()+","+itemPrice+",0,"+itemAmount+","+getBarData.getRate1()+",'"+userCode.getEmployeeCode()+ "',CURRENT_TIMESTAMP,"+reqQueue.getIs_cancel()+",1,"+reqQueue.getLine_number()+",'"+saleCode+"','"+saleName+"','"+getBarData.getExpertCode()+"','"+getBarData.getDepartmentCode()+"','"+getBarData.getDepartmentName()+"','"+getBarData.getCategoryCode()+"','"+getBarData.getCategoryName()+"','"+getBarData.getSecCode()+"','"+getBarData.getSecName()+"',"+getBarData.getAverageCost()+",'"+getBarData.getZoneId()+"' )";
										isSuccess= excecute.execute(dbName,vQuery);

										vQuerySub = "update  Queue set status = 1,pickStatus=1,isload = 1,numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+getQueue.getDocNo()+"') where docNo ='"+getQueue.getDocNo()+"'";
										isSuccess= excecute.execute(dbName,vQuerySub);

										resItem.setSuccess(true);
										resItem.setError(false);
										resItem.setMessage("");

									}else{
										System.out.println("Source = "+itemExist.getItem_source());
										resItem.setItem(listproduct);
										resItem.setSuccess(false);
										resItem.setError(true);
										resItem.setMessage("Sale Order can not add new item");
									}
								}else{
									System.out.println("reqQueue.getQty_after = "+reqQueue.getQty_after());
									System.out.println("itemExist.getQty_before ="+itemExist.getQty_before());
									System.out.println("itemExist.getItem_source ="+itemExist.getItem_source());
									if((itemExist.getItem_source()==0)||(itemExist.getItem_source()==1 && itemExist.getQty_before() > 0 && reqQueue.getQty_after()==itemExist.getQty_before() && itemExist.getBill_type() == 1) || (itemExist.getItem_source()==1 &&  itemExist.getQty_before() > 0 && itemExist.getBill_type()==0)){
										System.out.println("1");
										if(reqQueue.getIs_cancel()==0){
											System.out.println("2");
											vQuery = "update QItem set checkoutQty="+reqQueue.getQty_after()+",price ="+itemPrice+",checkoutAmount="+itemAmount+",isCheckOut=1,checkerCode='"+userCode.getEmployeeCode()+"',checkoutDate = CURRENT_TIMESTAMP,isCancel=0 where qId = "+reqQueue.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"' and lineNumber ="+reqQueue.getLine_number();
										}else{
											System.out.println("3");
											vQuery = "update QItem set isCancel=1,cancelCode='"+creatorCode+"',cancelDate = CURRENT_TIMESTAMP where qId = "+reqQueue.getQueue_id()+" and docNo ='"+getQueue.getDocNo()+"' and itemCode='"+getBarData.getCode()+"' and unitCode ='"+getBarData.getUnitCode()+"' and lineNumber ="+reqQueue.getLine_number();
										}
										isSuccess= excecute.execute(dbName,vQuery);
										
										vQuerySub = "update  Queue set status = 1,pickstatus=1,isload = 1,numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+getQueue.getDocNo()+"') where docNo ='"+getQueue.getDocNo()+"'";
										isSuccess= excecute.execute(dbName,vQuerySub);

										resItem.setSuccess(true);
										resItem.setError(false);
										resItem.setMessage("");

									}else
									{System.out.println("4");
									resItem.setItem(listproduct);
									resItem.setSuccess(false);
									resItem.setError(true);
									resItem.setMessage("Sale Order must add qty equal pickqty");
									}
								}
								System.out.println(vQuery);
								if (resItem.isSuccess()==true){
									try{
										
										Statement st = ds.getStatement(dbName);
										//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
										vQuery = "call USP_DT_QueueDataItem ("+reqQueue.getQueue_id()+",'"+getBarData.getCode()+"')";
										ResultSet rs = st.executeQuery(vQuery);
										System.out.println(vQuery);
										
										listproduct.clear();
										SO_Res_ListProductQueueBean evt;
										System.out.println("Count"+rs.getRow());
										while(rs.next()){

											evt = new SO_Res_ListProductQueueBean();
											evt.setFile_path(rs.getString("filepath"));
											evt.setIs_cancel(rs.getInt("itemcancel"));
											evt.setIs_check(rs.getInt("ischeckout"));
											evt.setItem_barcode(rs.getString("barcode"));
											evt.setItem_code(rs.getString("itemcode"));
											evt.setItem_name(rs.getString("itemname"));
											evt.setPickup_staff_name(rs.getString("pickername"));
											evt.setSale_code(rs.getString("salecode"));
											evt.setItem_price(rs.getDouble("price"));
											evt.setQty_after(rs.getDouble("checkoutqty"));
											evt.setQty_before(rs.getDouble("pickqty"));
											evt.setRequest_qty(rs.getDouble("reqqty"));
											evt.setQty_load(rs.getDouble("loadqty"));
											evt.setTotal_price_after(rs.getDouble("checkoutamount"));
											evt.setTotal_price_before(rs.getDouble("itemamount"));
											evt.setItem_unit_code(rs.getString("unitcode"));
											evt.setLine_number(rs.getInt("linenumber"));
											evt.setItem_qty(rs.getDouble("qty"));
											
											listproduct.add(evt);

										}
										
										rs.close();
										st.close();
										
										queuedata.setItem(listproduct);
										
										resItem.setItem(listproduct);
										resItem.setSuccess(true);
										resItem.setError(false);
										resItem.setMessage("");

									}catch(Exception e){
										resItem.setItem(listproduct);
										resItem.setSuccess(false);
										resItem.setError(true);
										resItem.setMessage(e.getMessage());
									}finally{
										ds.close();
									}
								}
							}else{
								resItem.setItem(listproduct);
								resItem.setSuccess(false);
								resItem.setError(true);
								resItem.setMessage("No have barcode");
							}
						}else{
							resItem.setItem(listproduct);
							resItem.setSuccess(false);
							resItem.setError(true);
							resItem.setMessage("Queue status is used");
						}

					}else{
						resItem.setItem(listproduct);
						resItem.setSuccess(false);
						resItem.setError(true);
						resItem.setMessage("Queue is not manage item becuase queue pick status not ready");
					}
				}else{
					resItem.setItem(listproduct);
					resItem.setSuccess(false);
					resItem.setError(true);
					resItem.setMessage("Queue is cancel");
				}
			}else{
				//==============================
				resItem.setItem(listproduct);
				resItem.setSuccess(false);
				resItem.setError(true);
				resItem.setMessage("Barcode is null");
			}
		}else{
			//===================================
			resItem.setItem(listproduct);
			resItem.setSuccess(false);
			resItem.setError(true);
			resItem.setMessage("Queue_id not generate");

		}

		return resItem;
	}
	
	public SO_Res_EditQueueBean  editOrder(String dbName,SO_Req_EditQueueBean reqEdit){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		//Date dateNow = new Date();
		
		String vQuery;
		getQueue = getData.searchQueue(reqEdit.getQueue_id());
		int vCountToken =0;
		
		String saleCode="";
		String saleName="";
		String creatorCode="";
		int status;
		int pick_status;
		
		status = getQueue.getStatus();
		pick_status = getQueue.getPickStatus();
		
		if(status < 2){
		if (reqEdit.getPlate_number()!=null && reqEdit.getPlate_number()!=""){
			if(getQueue.getIsCancel()==0){
				
				if(getQueue.getStatus()!=3){
					
					userCode = getData.searchUserAccessToken(reqEdit.getAccess_token());
					
					System.out.println("รหัสพนักงานคือ "+reqEdit.getSale_code());
					
					creatorCode = userCode.getEmployeeCode();
					
					if (reqEdit.getSale_code()=="" || reqEdit.getSale_code() == null){
						saleCode = userCode.getEmployeeCode();
						saleName = userCode.getEmployeeName();
						
						if (reqEdit.getStatus()!=2){
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getPlate_number()+"',"+"carBrand= '"+reqEdit.getCar_brand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getQueue_id();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}else{
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",carLicence = '"+reqEdit.getPlate_number()+"',"+"carBrand= '"+reqEdit.getCar_brand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getQueue_id();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}
					}else{
						
						sale = getData.searchSaleCode(reqEdit.getSale_code());
						if (sale.getIsExist()==1){
							saleCode = sale.getSaleCode();
							saleName = sale.getSaleName();
						}else{
							saleCode = "N/A";
							saleName = "-";
						}
						
						if (reqEdit.getStatus()!=2){
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getPlate_number()+"',"+"carBrand= '"+reqEdit.getCar_brand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getQueue_id();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}else{
							vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getPlate_number()+"',"+"carBrand= '"+reqEdit.getCar_brand()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP,salecode='"+saleCode+"',salename='"+saleName+"' where docDate = CURDATE() and qid ="+reqEdit.getQueue_id();
							//vQuery ="update Queue set status = "+reqEdit.getStatus()+" where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
						}
						
					}
					
					System.out.println("รหัสพนักงานใหม่คือ "+saleCode);

					//vQuery ="update Queue set status = "+reqEdit.getStatus()+",saleCode = '"+saleCode+"',carLicence = '"+reqEdit.getCarNumber()+"',"+"carBrand= '"+reqEdit.getCarBrand()+"',editorCode='"+saleCode+"',editDate = CURRENT_TIMESTAMP where docDate = '"+dateFormat.format(dateNow)+"' and qid ="+reqEdit.getqId();
					System.out.println(vQuery); 
					try {
							isSuccess= excecute.execute(dbName,vQuery);
							
							Statement st = ds.getStatement(dbName);
							//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
							vQuery = "call USP_DT_QueueData ("+reqEdit.getQueue_id()+")";
							ResultSet rs = st.executeQuery(vQuery);
							System.out.println(vQuery);
							
							listproduct.clear();
							SO_Res_ListProductQueueBean evt;
							System.out.println("Count"+rs.getRow());
							while(rs.next()){
								
								queuedata.setQueue_id(rs.getInt("qid"));// queue_id;//: "XXXX",
								queuedata.setNumber_of_item(rs.getDouble("numberOfItem"));// number_of_item;//, "10",
								queuedata.setTime_created(rs.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
								queuedata.setStatus(rs.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
								queuedata.setIs_cancel(rs.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
								queuedata.setAr_code(rs.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
								queuedata.setAr_name(rs.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
								queuedata.setSale_code(rs.getString("salecode"));
								queuedata.setSale_name(rs.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
								queuedata.setDoc_no(rs.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
								queuedata.setSource(rs.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
								queuedata.setReceiver_name(rs.getString("receiveName"));// receiver_name;//: "xxx xxx",
								queuedata.setDoc_date(rs.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
								queuedata.setPickup_datetime(rs.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
								queuedata.setTotal_amount(rs.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
								queuedata.setIs_loaded(rs.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
								queuedata.setCar_brand(rs.getString("carBrand"));// car_brand;//: "toyota",
								queuedata.setPlate_number(rs.getString("carLicence"));// plate_number;//: "กท7777",
								queuedata.setStatus_for_saleorder_current(rs.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
								queuedata.setOtp_password(rs.getString("otpcode"));
								queuedata.setWho_cancel(rs.getString("cancelcode"));
								queuedata.setCancel_remark(rs.getString("cancelremark"));
								
								evt = new SO_Res_ListProductQueueBean();
								evt.setFile_path(rs.getString("filepath"));
								evt.setIs_cancel(rs.getInt("itemcancel"));
								evt.setIs_check(rs.getInt("ischeckout"));
								evt.setItem_barcode(rs.getString("barcode"));
								evt.setItem_code(rs.getString("itemcode"));
								evt.setItem_name(rs.getString("itemname"));
								evt.setPickup_staff_name(rs.getString("pickername"));
								evt.setSale_code(rs.getString("salecode"));
								evt.setItem_price(rs.getDouble("price"));
								evt.setQty_after(rs.getDouble("checkoutqty"));
								evt.setQty_before(rs.getDouble("pickqty"));
								evt.setRequest_qty(rs.getDouble("reqqty"));
								evt.setQty_load(rs.getDouble("loadqty"));
								evt.setTotal_price_after(rs.getDouble("checkoutamount"));
								evt.setTotal_price_before(rs.getDouble("itemamount"));
								evt.setItem_unit_code(rs.getString("unitcode"));
								evt.setLine_number(rs.getInt("linenumber"));
								evt.setItem_qty(rs.getDouble("qty"));
								
								listproduct.add(evt);

							}
							
							//rs.close();
							//st.close();
							
							queuedata.setItem(listproduct);
							
							List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
							String vQueryOwner;
							
							try{
							Statement st_owner = ds.getStatement(dbName);
							vQueryOwner = "call USP_DT_SearchOwnerPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
							System.out.println("vQueryOwner : "+vQueryOwner);
							ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
							
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
								
							}
							rs_owner.close();
							st_owner.close();
							}catch(Exception e){}
							finally{ds.close();}
						
							ArrayList<String> listowner = new ArrayList<>();
							for(int i=0;i<list_ownerphone.size();i++){
								listowner.add(list_ownerphone.get(i).getPhone_no());
							}
							
							queuedata.setOwner_phone(listowner);
							
							List<SO_Res_ListOwnerPhoneBean>list_receiverphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
							String vQueryReceiver;
							
							try{
							Statement st_receiver = ds.getStatement(dbName);
							vQueryReceiver = "call USP_DT_SearchReceiverPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
							System.out.println("vQueryReceiver : "+vQueryReceiver);
							ResultSet rs_receiver = st_receiver.executeQuery(vQueryReceiver);
							
							SO_Res_ListOwnerPhoneBean evt_receiverphone;
							SO_Res_ListOwnerPhoneBean evt_receiverphone1;
							list_receiverphone.clear();
							while(rs_receiver.next()){
								evt_receiverphone = new SO_Res_ListOwnerPhoneBean();
								evt_receiverphone1 = new SO_Res_ListOwnerPhoneBean();
								evt_receiverphone.setPhone_no(rs_receiver.getString("phone_no"));
								
								if(rs_receiver.getRow()!=0){
									list_receiverphone.add(evt_receiverphone);
								}else{
									list_receiverphone.add(evt_receiverphone1);
								}
								
							}
							rs_receiver.close();
							st_receiver.close();
							}catch(Exception e){}
							finally{ds.close();}
						
							ArrayList<String> listreceiver = new ArrayList<>();
							for(int i=0;i<list_receiverphone.size();i++){
								listreceiver.add(list_receiverphone.get(i).getPhone_no());
							}
							
							queuedata.setReceiver_phone(listreceiver);
							
							
							List<SO_Res_ListQueueStatusBean>list_queuestatus = new ArrayList<SO_Res_ListQueueStatusBean>();				
							String vQueryStatus;
							
							try{
							Statement st_status = ds.getStatement(dbName);
							vQueryStatus = "call USP_DT_SearchQueueStatusHistory ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
							System.out.println("vQueryStatus : "+vQueryStatus);
							ResultSet rs_status = st_status.executeQuery(vQueryStatus);
							
							
							SO_Res_ListQueueStatusBean evt_status;
							SO_Res_ListQueueStatusBean evt_status1;
							list_queuestatus.clear();
							while(rs_status.next()){
								evt_status = new SO_Res_ListQueueStatusBean();
								evt_status1 = new SO_Res_ListQueueStatusBean();
								evt_status.setStatus_id(rs_status.getInt("pick_status"));
								evt_status.setStatus_for_saleorder(rs_status.getString("pick_status"));
								evt_status.setCreate_datetime(rs_status.getString("insertdatetime"));
								
								if(rs_status.getRow()!=0){
									list_queuestatus.add(evt_status);
								}else{
									list_queuestatus.add(evt_status1);
								}
								
							}
							rs_status.close();
							st_status.close();
							}catch(Exception e){}
							finally{ds.close();}
							
							queuedata.setStatus_for_saleorder_history(list_queuestatus);
							

							
//							queue_edit.setCar_brand(reqEdit.getCar_brand());
//							queue_edit.setPlate_number(reqEdit.getPlate_number());
//							queue_edit.setQueue_id(reqEdit.getQueue_id());
//							queue_edit.setSale_code(reqEdit.getSale_code());
//							queue_edit.setStatus(reqEdit.getStatus());
							
							edit.setSuccess(true);
							edit.setError(false);
							edit.setMessage("");
							edit.setQueue(queuedata);
							
							rs.close();
							st.close();
							
						} catch (Exception e) {
							edit.setSuccess(false);
							edit.setError(true);
							edit.setMessage(e.getMessage());
						}	finally{
							ds.close();
						}
				}else{
					edit.setSuccess(false);
					edit.setError(true);
					edit.setMessage("Queue status is used");
				}

			}else{
				edit.setSuccess(false);
				edit.setError(true);
				edit.setMessage("Queue is cancel");
		
			}
			
			}else{
				edit.setSuccess(false);
				edit.setError(true);
				edit.setMessage("No Have CarLicence");

			}
		}else{
			edit.setSuccess(false);
			edit.setError(true);
			edit.setMessage("Queue is complete");
		}
		
		
		return edit;
	}
	
	public SO_Res_EditQueueBean editWebSaleOrderQueue(String dbName,DT_User_LoginBranchBean db,SO_Req_EditSaleOrderBean req){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();

		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

		String vQueryHeader;
		String vQuery;
		String vMyQuery;
		String creatorCode;
		int addItem;
		int status;
		int pick_status;
		CT_Resp_ResponseBean check_itemsaleorder;

		//getQueue = getData.searchSaleOrder(req.getDoc_no());
		getQueue = getData.searchQueue(req.getQueue_id());


		userCode = getData.searchUserAccessToken(req.getAccess_token());

		System.out.println("status="+getQueue.getStatus() +"  "+"pick_status ="+getQueue.getPickStatus());

		creatorCode = userCode.getEmployeeCode();
		status = getQueue.getStatus();
		pick_status = getQueue.getPickStatus();
		boolean very_item = true;

		if (getQueue.getDoctype() == 1 && getQueue.getBilltype() == 1){
			check_itemsaleorder = data.verifyEditItemSaleOrder(db, req);
			very_item = check_itemsaleorder.getIsSuccess();
			System.out.println("check_itemsaleorder ="+check_itemsaleorder.getIsSuccess());
		}

		if (very_item == true){
			if (req.getCar_brand() != ""){
				System.out.println("req.getCar_brand()"+req.getCar_brand());
				if (req.getReceiver_phone().size()!=0){
					System.out.println("req.getReceiver_phone().size()"+req.getReceiver_phone().size());
					if (req.getReceiver_name()!=""){
						System.out.println("req.getReceiver_name()"+req.getReceiver_name());
						if (req.getPlate_number()!=""){ 
							System.out.println("req.getPlate_number()"+req.getPlate_number());
							if(status != 1 && pick_status != 4){
								if(req.getItem().size()!=0){
									if(req.getQueue_id()!=0){
										if(getQueue.getIsCancel()==0){
											if(getQueue.getStatus()<=2){

												String vQueryOwner;
												String vQueryReceive;
												
												data.UpdateSaleOrderData(dbName, db, req);

												vQueryOwner="delete from OrderOwnerPhone where doc_date = CURDATE() and queue_id = '"+req.getQueue_id()+"'";
												isSuccess= excecute.execute(dbName,vQueryOwner);

												vQueryReceive="delete from OrderTrustPhone where doc_date = CURDATE() and queue_id = '"+req.getQueue_id()+"'";
												isSuccess= excecute.execute(dbName,vQueryReceive);

												if(req.getOwner_phone().size()!= 0){
													for(int a=0;a<req.getOwner_phone().size();a++){
														System.out.println(req.getOwner_phone());
														vQueryOwner = "insert into OrderOwnerPhone(doc_no,queue_id,sale_order,doc_date,otp_code,phone_no) values('"+getQueue.getDocNo()+"',"+req.getQueue_id()+",'"+getQueue.getSaleOrder()+"',CURDATE(),'"+getQueue.getOtp_password()+"','"+req.getOwner_phone().get(a)+"')";
														System.out.println("vQueryOwner="+vQueryOwner);
														isSuccess= excecute.execute(dbName,vQueryOwner);
													}
												}

												if(req.getReceiver_phone().size()!= 0){
													for(int b=0;b<req.getReceiver_phone().size();b++){
														System.out.println(req.getReceiver_phone());
														vQueryReceive = "insert into OrderTrustPhone(doc_no,queue_id,sale_order,doc_date,otp_code,phone_no) values('"+getQueue.getDocNo()+"',"+req.getQueue_id()+",'"+getQueue.getSaleOrder()+"',CURDATE(),'"+getQueue.getOtp_password()+"','"+req.getReceiver_phone().get(b)+"')";
														System.out.println("vQueryReceive="+vQueryReceive);
														isSuccess= excecute.execute(dbName,vQueryReceive);
													}
												}

												vQueryHeader ="update Queue set carLicence = '"+req.getPlate_number()+"',"+"carBrand= '"+req.getCar_brand()+"',receiveName ='"+req.getReceiver_name()+"',pickupDatetime = '"+req.getPickup_datetime()+"',editorCode='"+creatorCode+"',editDate = CURRENT_TIMESTAMP()"+" where docDate = CURDATE() and qid = "+req.getQueue_id()+"" ;
												System.out.println("vQueryHeader ="+vQueryHeader);
												isSuccess= excecute.execute(dbName,vQueryHeader);

												for(int i =0;i<=req.getItem().size()-1;i++){

													//ห้ามลืม แก้ไข คนรับของข้อมูลที่ต้องการอัพเดทใน คิวด้วย ไม่ใช่แค่สินค้า
													vQuery = "exec dbo.USP_NP_UpdatePickItemDriveThru '"+getQueue.getSaleOrder()+"','"+req.getPlate_number()+"','"+req.getItem().get(i).getItem_code()+"',"+req.getItem().get(i).getRequest_qty()+",'"+req.getItem().get(i).getItem_unit_code()+"',"+req.getItem().get(i).getLine_number();
													isSuccess = npExec.executeSqlBranch(db, vQuery);

													vMyQuery = "call USP_DT_UpdateSaleOrderQItem ('"+getQueue.getSaleOrder()+"','"+req.getItem().get(i).getItem_code()+"','"+req.getItem().get(i).getItem_unit_code()+"',"+req.getItem().get(i).getRequest_qty()+","+req.getItem().get(i).getLine_number()+")";
													System.out.println("vMyQuery ="+vMyQuery);
													isSuccess= excecute.execute(dbName,vMyQuery);

												}

												try {
													Statement st = ds.getStatement(dbName);
													//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
													vQuery = "call USP_DT_DataDocNo ('"+getQueue.getDocNo()+"')";
													System.out.println("call USP_DT_DataDocNo="+vQuery);
													ResultSet rs = st.executeQuery(vQuery);

													listproduct.clear();
													SO_Res_ListProductQueueBean evt;

													while(rs.next()){
														System.out.println("Count"+rs.getRow());
														queuedata.setQueue_id(rs.getInt("qid"));// queue_id;//: "XXXX",
														queuedata.setNumber_of_item(rs.getDouble("numberOfItem"));// number_of_item;//, "10",
														queuedata.setTime_created(rs.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
														queuedata.setStatus(rs.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
														queuedata.setIs_cancel(rs.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
														queuedata.setAr_code(rs.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
														queuedata.setAr_name(rs.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
														queuedata.setSale_code(rs.getString("salecode"));
														queuedata.setSale_name(rs.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
														queuedata.setDoc_no(rs.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
														queuedata.setSource(rs.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
														queuedata.setReceiver_name(rs.getString("receiveName"));// receiver_name;//: "xxx xxx",
														queuedata.setDoc_date(rs.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
														queuedata.setPickup_datetime(rs.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
														queuedata.setTotal_amount(rs.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
														queuedata.setIs_loaded(rs.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
														queuedata.setCar_brand(rs.getString("carBrand"));// car_brand;//: "toyota",
														queuedata.setPlate_number(rs.getString("carLicence"));// plate_number;//: "กท7777",
														queuedata.setStatus_for_saleorder_current(rs.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
														queuedata.setWho_cancel(rs.getString("cancelcode"));
														queuedata.setCancel_remark(rs.getString("cancelremark"));
														
														System.out.println("Count1"+rs.getRow());
														evt = new SO_Res_ListProductQueueBean();
														evt.setFile_path(rs.getString("filepath"));
														evt.setIs_cancel(rs.getInt("itemcancel"));
														evt.setIs_check(rs.getInt("ischeckout"));
														evt.setItem_barcode(rs.getString("barcode"));
														evt.setItem_code(rs.getString("itemcode"));
														evt.setItem_name(rs.getString("itemname"));
														evt.setPickup_staff_name(rs.getString("pickername"));
														evt.setSale_code(rs.getString("salecode"));
														evt.setItem_price(rs.getDouble("price"));
														evt.setQty_after(rs.getDouble("checkoutqty"));
														evt.setQty_before(rs.getDouble("pickqty"));
														evt.setRequest_qty(rs.getDouble("reqqty"));
														evt.setQty_load(rs.getDouble("loadqty"));
														evt.setTotal_price_after(rs.getDouble("checkoutamount"));
														evt.setTotal_price_before(rs.getDouble("itemamount"));
														evt.setItem_unit_code(rs.getString("unitcode"));
														evt.setLine_number(rs.getInt("linenumber"));
														evt.setItem_qty(rs.getDouble("qty"));

														listproduct.add(evt);

													}

													rs.close();
													st.close();

													queuedata.setItem(listproduct);

													List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
													//String vQueryOwner;

													try{
													Statement st_owner = ds.getStatement(dbName);
													vQueryOwner = "call USP_DT_SearchOwnerPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
													System.out.println("vQueryOwner : "+vQueryOwner);
													ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);

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

													}
													rs_owner.close();
													st_owner.close();
													}catch(Exception e){}
													finally{ds.close();}

													ArrayList<String> listowner = new ArrayList<>();
													for(int i=0;i<list_ownerphone.size();i++){
														listowner.add(list_ownerphone.get(i).getPhone_no());
													}

													queuedata.setOwner_phone(listowner);

													List<SO_Res_ListOwnerPhoneBean>list_receiverphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
													String vQueryReceiver;

													try{
													Statement st_receiver = ds.getStatement(dbName);
													vQueryReceiver = "call USP_DT_SearchReceiverPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
													System.out.println("vQueryReceiver : "+vQueryReceiver);
													ResultSet rs_receiver = st_receiver.executeQuery(vQueryReceiver);


													SO_Res_ListOwnerPhoneBean evt_receiverphone;
													SO_Res_ListOwnerPhoneBean evt_receiverphone1;
													list_receiverphone.clear();
													while(rs_receiver.next()){
														evt_receiverphone = new SO_Res_ListOwnerPhoneBean();
														evt_receiverphone1 = new SO_Res_ListOwnerPhoneBean();
														evt_receiverphone.setPhone_no(rs_receiver.getString("phone_no"));

														if(rs_receiver.getRow()!=0){
															list_receiverphone.add(evt_receiverphone);
														}else{
															list_receiverphone.add(evt_receiverphone1);
														}

													}
													rs_receiver.close();
													st_receiver.close();
													}catch(Exception e){}
													finally{ds.close();}

													ArrayList<String> listreceiver = new ArrayList<>();
													for(int i=0;i<list_receiverphone.size();i++){
														listreceiver.add(list_receiverphone.get(i).getPhone_no());
													}

													queuedata.setReceiver_phone(listreceiver);


													List<SO_Res_ListQueueStatusBean>list_queuestatus = new ArrayList<SO_Res_ListQueueStatusBean>();				
													String vQueryStatus;

													try{
													Statement st_status = ds.getStatement(dbName);
													vQueryStatus = "call USP_DT_SearchQueueStatusHistory ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
													System.out.println("vQueryStatus : "+vQueryStatus);
													ResultSet rs_status = st_status.executeQuery(vQueryStatus);


													SO_Res_ListQueueStatusBean evt_status;
													SO_Res_ListQueueStatusBean evt_status1;
													list_queuestatus.clear();
													while(rs_status.next()){
														evt_status = new SO_Res_ListQueueStatusBean();
														evt_status1 = new SO_Res_ListQueueStatusBean();
														evt_status.setStatus_id(rs_status.getInt("pick_status"));
														evt_status.setStatus_for_saleorder(rs_status.getString("pick_status"));
														evt_status.setCreate_datetime(rs_status.getString("insertdatetime"));

														if(rs_status.getRow()!=0){
															list_queuestatus.add(evt_status);
														}else{
															list_queuestatus.add(evt_status1);
														}

													}
													rs_status.close();
													st_status.close();
													}catch(Exception e){}
													finally{ds.close();}

													queuedata.setStatus_for_saleorder_history(list_queuestatus);


												}catch (SQLException e) {
													edit.setSuccess(false);
													edit.setError(true);
													edit.setMessage(e.getMessage());

												}finally{
													ds.close();
												}

												edit.setSuccess(true);
												edit.setError(false);
												edit.setMessage("");
												edit.setQueue(queuedata);

											}else{
												// queue is confirmed
												edit.setSuccess(false);
												edit.setError(true);
												edit.setMessage("Queue is confirm");
											}
										}else{
											//is cancel
											edit.setSuccess(false);
											edit.setError(true);
											edit.setMessage("Queue is cancel");
										}

									}else{
										//no have docno
										edit.setSuccess(false);
										edit.setError(true);
										edit.setMessage("No Have Docno");
									}

								}else{
									//no have item
									edit.setSuccess(false);
									edit.setError(true);
									edit.setMessage("No Have Item for sale");
								}
							}else{
								edit.setSuccess(false);
								edit.setError(true);
								edit.setMessage("This sale order is complete or is cancel");
							}
						}else{
							edit.setSuccess(false);
							edit.setError(true);
							edit.setMessage("This sale order not have plate_number");
						}
					}else{
						edit.setSuccess(false);
						edit.setError(true);
						edit.setMessage("This sale order not have receive_name");
					}
				}else{
					edit.setSuccess(false);
					edit.setError(true);
					edit.setMessage("This sale order not have receive_phone");
				}
			}else{
				edit.setSuccess(false);
				edit.setError(true);
				edit.setMessage("This sale order not have car brand");
			}
		}else{
			edit.setSuccess(false);
			edit.setError(true);
			edit.setMessage("This sale order request over remain qty");
		}
		return edit;

	}
	
	
	public SO_Res_ARDepositBean SearchARDepositBalance(DT_User_LoginBranchBean db,CT_Req_SearchArBean req){
		String vQuery;
		try {
			Statement st = sql.getSqlStatement(db.getServerName(), db.getDbName());
			vQuery = "exec dbo.USP_NP_SearchArDepositRemain '"+req.getAr_code()+"'";
			System.out.println(vQuery);	
			
			ResultSet rs = st.executeQuery(vQuery);
			
			
			listdep.clear();
			SO_Res_ListARDepositBean evt;
			System.out.println("Count"+rs.getRow());
			while(rs.next()){
				evt = new SO_Res_ListARDepositBean();
				evt.setDeposit_id(rs.getString("docno"));
				evt.setDeposit_amount(rs.getDouble("netamount"));
				evt.setDeposit_remain(rs.getDouble("billbalance"));
				
				System.out.println("docno ="+(rs.getString("docno")));
				System.out.println("netamount ="+(rs.getDouble("netamount")));
				System.out.println("billbalance ="+(rs.getDouble("billbalance")));
				
				listdep.add(evt);

			}
			
			rs.close();
			st.close();
			
			
			dep.setSuccess(true);
			dep.setError(false);
			dep.setMessage("");
			
		}catch (SQLException e) {
			dep.setSuccess(false);
			dep.setError(true);
			dep.setMessage(e.getMessage());
			
			
		}finally{
			ds.close();
		}
		
		dep.setDeposit(listdep);
		
		return dep;
	}
	
	public SO_Res_ChangeStatusBean ChangeQueueStatus (String dbName,DT_User_LoginBranchBean db,SO_Req_ChangeStatusBean req){
		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getDataFromData getData = new getDataFromData();
		PK_Resp_SaleCodeDetails saleCode = new PK_Resp_SaleCodeDetails();
		
		String vQuery;
		String vQuerySub;
		String vQueryStatus;
		String creatorCode;
		
		System.out.println("Get Password = "+req.getPassword());
		
		userCode = getData.searchUserAccessToken(req.getAccess_token());

		System.out.println("status="+getQueue.getStatus() +"  "+"pick_status ="+getQueue.getPickStatus());

		creatorCode = userCode.getEmployeeCode();
		
		if (req.getQueue_id() != 0){
			System.out.println("QueueID :"+req.getQueue_id());
			getQueue = getData.searchQueue(req.getQueue_id());
			
			if(getQueue.getDelivery_type() == 1) {
				saleCode = getData.searchSaleCode(req.getPassword());
			}else {
				saleCode.setSaleCode("");
				saleCode.setSaleName("");
			}
			
			if(req.getStatus_for_saleorder_current() != ""){
				System.out.println("Status :"+req.getStatus_for_saleorder_current());
				System.out.println("Password :"+req.getPassword());
				System.out.println("getQueue password()="+getQueue.getOtp_password());
				if (req.getStatus_for_saleorder_current().equals("1") && (req.getIs_loaded() == 0) && getQueue.getIsCancel() == 0){
					System.out.println("IsLoad :"+req.getIs_loaded());
					//if(req.getPassword()!=""){
						//if(req.getPassword()==getQueue.getOtp_password()){
							vQuery = "update Queue set status = 1,pickstatus=1 where qid = "+req.getQueue_id()+" and docdate = curdate()";
							System.out.println(vQuery);
							isSuccess= excecute.execute(dbName,vQuery);
							
							vQueryStatus = "insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values ('"+req.getPassword()+"',"+req.getQueue_id()+",'"+getQueue.getDocNo()+"',1,CURRENT_TIMESTAMP())";
							System.out.println(vQueryStatus);
							isSuccess= excecute.execute(dbName,vQueryStatus);
							
							qstatus.setQueue_id(req.getQueue_id());
							if(req.getStatus_for_saleorder_current().equals("0")){
								qstatus.setStatus(0);
							}
							if(req.getStatus_for_saleorder_current().equals("1")){
								qstatus.setStatus(1);
							}
							if(req.getStatus_for_saleorder_current().equals("2")){
								qstatus.setStatus(2);
							}
							if(req.getStatus_for_saleorder_current().equals("3")){
								qstatus.setStatus(3);
							}
							if(req.getStatus_for_saleorder_current().equals("4")){
								qstatus.setStatus(4);
							}
							//isSuccess= npExec.executeSqlBranch(db,vQueryLog);
							
							change.setSuccess(true);
							change.setError(false);
							change.setMessage("");
							//change.setQueue(qstatus);
							
	
						//}else{
							//change.setSuccess(false);
							//change.setError(true);
							//change.setMessage("Otp password is incorrect");
						//}
					//}else{
						//change.setSuccess(false);
						//change.setError(true);
						//change.setMessage("Not have otp password");
					//}
				}//else{
					//เงื่อนไขผิด
					//change.setSuccess(false);
					//change.setError(true);
					//change.setMessage("Status complete status = 1 and isload = 1");
				//}
				
				
				if (req.getStatus_for_saleorder_current().equals("1") && req.getIs_loaded() == 1 && req.getPassword() != ""  && getQueue.getIsCancel() == 0){
					System.out.println("IsLoad :"+req.getIs_loaded());
					System.out.println("Password :"+req.getPassword());
					System.out.println("Pass Hash :"+getQueue.getOtp_password());
					//if(req.getPassword()!=""){
					
					//bcrypt.checkPassword(req.getPassword(), getQueue.getOtp_password());
					//String checkOtp = bcrypt.checkPassword(req.getPassword(), getQueue.getOtp_password())? "Passwords Match" : "Passwords do not match";
					//System.out.println("checkOtp status 1 = "+checkOtp);
					
					System.out.println("SaleName :"+saleCode.getSaleName());
					
					System.out.println(""+getQueue.getDelivery_type()+",,,,"+saleCode.getSaleName()+",,,,"+req.getPassword()+",,,,"+getQueue.getOtp_password());
					
						if ((getQueue.getDelivery_type()==1 && !saleCode.getSaleName().equals(""))|| (req.getPassword().equals(getQueue.getOtp_password()) && getQueue.getDelivery_type() == 0)){//(checkOtp.equals("Passwords Match")){//req.getPassword().equals(getQueue.getOtp_password())
							vQuery = "update Queue set status = 1,pickstatus=1,isload =1 ,confirmcode = '"+creatorCode+"',confirmdate = CURRENT_TIMESTAMP() where qid = "+req.getQueue_id()+" and docdate = curdate()";
							System.out.println(vQuery);
							isSuccess= excecute.execute(dbName,vQuery);
							
							if (getQueue.getDelivery_type()==1 && !saleCode.getSaleName().equals("")) {
								vQuery = "update Queue set sendSaleCode = '"+req.getPassword()+"' where qid = "+req.getQueue_id()+" and docdate = curdate()";
								System.out.println(vQuery);
								isSuccess= excecute.execute(dbName,vQuery);
							}
							
							vQueryStatus = "insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values ('"+req.getPassword()+"',"+req.getQueue_id()+",'"+getQueue.getDocNo()+"',1,CURRENT_TIMESTAMP())";
							System.out.println(vQueryStatus);
							isSuccess= excecute.execute(dbName,vQueryStatus);
							
							qstatus.setQueue_id(req.getQueue_id());
							if(req.getStatus_for_saleorder_current().equals("0")){
								qstatus.setStatus(0);
							}
							if(req.getStatus_for_saleorder_current().equals("1")){
								qstatus.setStatus(1);
							}
							if(req.getStatus_for_saleorder_current().equals("2")){
								qstatus.setStatus(2);
							}
							if(req.getStatus_for_saleorder_current().equals("3")){
								qstatus.setStatus(3);
							}
							if(req.getStatus_for_saleorder_current().equals("4")){
								qstatus.setStatus(4);
							}
							//isSuccess= npExec.executeSqlBranch(db,vQueryLog);
							
							change.setSuccess(true);
							change.setError(false);
							change.setMessage("");
							//change.setQueue(qstatus);
							
	
						}else{
							change.setSuccess(false);
							change.setError(true);
							change.setMessage("Otp password is incorrect");
						}
					//}else{
						//change.setSuccess(false);
						//change.setError(true);
						//change.setMessage("Not have otp password");
					//}
				}//else{
					//เงื่อนไขผิด
					//change.setSuccess(false);
					//change.setError(true);
					//change.setMessage("Status = 1 and isload = 1 but password invalid ");
				//}
				
				if (req.getStatus_for_saleorder_current().equals("2") && getQueue.getIsCancel() == 0){
					vQuery = "update Queue set status = 0,pickstatus=2 where qid = "+req.getQueue_id()+" and docdate = curdate()";
					System.out.println(vQuery);
					isSuccess= excecute.execute(dbName,vQuery);
					
					vQueryStatus = "insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values ('"+req.getPassword()+"',"+req.getQueue_id()+",'"+getQueue.getDocNo()+"',2,CURRENT_TIMESTAMP())";
					System.out.println(vQueryStatus);
					isSuccess= excecute.execute(dbName,vQueryStatus);
					
					qstatus.setQueue_id(req.getQueue_id());
					if(req.getStatus_for_saleorder_current().equals("0")){
						qstatus.setStatus(0);
					}
					if(req.getStatus_for_saleorder_current().equals("1")){
						qstatus.setStatus(1);
					}
					if(req.getStatus_for_saleorder_current().equals("2")){
						qstatus.setStatus(2);
					}
					if(req.getStatus_for_saleorder_current().equals("3")){
						qstatus.setStatus(3);
					}
					if(req.getStatus_for_saleorder_current().equals("4")){
						qstatus.setStatus(4);
					}
					
					change.setSuccess(true);
					change.setError(false);
					change.setMessage("");
					//change.setQueue(qstatus);
				}
				if (req.getStatus_for_saleorder_current().equals("3") && getQueue.getIsCancel() == 0){
					vQuery = "update Queue set status = 0,pickstatus=3 where qid = "+req.getQueue_id()+" and docdate = curdate()";
					System.out.println(vQuery);
					isSuccess= excecute.execute(dbName,vQuery);
					
					vQueryStatus = "insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values ('"+req.getPassword()+"',"+req.getQueue_id()+",'"+getQueue.getDocNo()+"',3,CURRENT_TIMESTAMP())";
					System.out.println(vQueryStatus);
					isSuccess= excecute.execute(dbName,vQueryStatus);
					
					qstatus.setQueue_id(req.getQueue_id());
					if(req.getStatus_for_saleorder_current().equals("0")){
						qstatus.setStatus(0);
					}
					if(req.getStatus_for_saleorder_current().equals("1")){
						qstatus.setStatus(1);
					}
					if(req.getStatus_for_saleorder_current().equals("2")){
						qstatus.setStatus(2);
					}
					if(req.getStatus_for_saleorder_current().equals("3")){
						qstatus.setStatus(3);
					}
					if(req.getStatus_for_saleorder_current().equals("4")){
						qstatus.setStatus(4);
					}
					
					change.setSuccess(true);
					change.setError(false);
					change.setMessage("");
					//change.setQueue(qstatus);
					
				}
				if (req.getStatus_for_saleorder_current().equals("4")  && getQueue.getIsCancel() == 0){
					vQuery = "update Queue set status = 0,pickstatus=4,iscancel=1,cancelremark='"+ req.getCancel_remark()+"',cancelcode = '"+creatorCode+"',canceldate= CURRENT_TIMESTAMP() where qid = "+req.getQueue_id()+" and docdate = curdate()";
					System.out.println(vQuery);
					isSuccess= excecute.execute(dbName,vQuery);
					
					vQuerySub = "update QItem set iscancel = 1 where qid = "+req.getQueue_id()+" and docdate = curdate()";
					System.out.println(vQuerySub);
					isSuccess= excecute.execute(dbName,vQuerySub);
					
					vQueryStatus = "insert into QueuePickStatus(otp_code,qid,docno,pick_status,insertdatetime) values ('"+req.getPassword()+"',"+req.getQueue_id()+",'"+getQueue.getDocNo()+"',4,CURRENT_TIMESTAMP())";
					System.out.println(vQueryStatus);
					isSuccess= excecute.execute(dbName,vQueryStatus);
					
					qstatus.setQueue_id(req.getQueue_id());
					if(req.getStatus_for_saleorder_current().equals("0")){
						qstatus.setStatus(0);
					}
					if(req.getStatus_for_saleorder_current().equals("1")){
						qstatus.setStatus(1);
					}
					if(req.getStatus_for_saleorder_current().equals("2")){
						qstatus.setStatus(2);
					}
					if(req.getStatus_for_saleorder_current().equals("3")){
						qstatus.setStatus(3);
					}
					if(req.getStatus_for_saleorder_current().equals("4")){
						qstatus.setStatus(4);
					}
					
					change.setSuccess(true);
					change.setError(false);
					change.setMessage("");
					//change.setQueue(qstatus);
				}
				
				try {
					Statement st = ds.getStatement(dbName);
					//vQuery = "call USP_DT_SearchQueueProduct ("+req.getQueue_id()+")";
					vQuery = "call USP_DT_DataDocNo ('"+getQueue.getDocNo()+"')";
					System.out.println("call USP_DT_DataDocNo"+vQuery);
					ResultSet rs = st.executeQuery(vQuery);
					
					listproduct.clear();
					SO_Res_ListProductQueueBean evt;
					System.out.println("Count"+rs.getRow());
					while(rs.next()){

						queuedata.setQueue_id(rs.getInt("qid"));// queue_id;//: "XXXX",
						queuedata.setNumber_of_item(rs.getDouble("numberOfItem"));// number_of_item;//, "10",
						queuedata.setTime_created(rs.getString("createDate"));// time_created;//, "วันเวลาสร้าง queue",
						queuedata.setStatus(rs.getInt("status"));// status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
						queuedata.setIs_cancel(rs.getInt("isCancel"));// is_cancel;//, "0 หรือ 1",
						queuedata.setAr_code(rs.getString("customerCode"));// ar_code;//: “รหัสลุกค้า”,
						queuedata.setAr_name(rs.getString("arname"));// ar_name;//: “ชื่อลูกค้า”,
						queuedata.setSale_code(rs.getString("salecode"));
						queuedata.setSale_name(rs.getString("salename"));// sale_name;//: “ชื่อพนักงานขาย”,
						queuedata.setDoc_no(rs.getString("saleorder"));// doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
						queuedata.setSource(rs.getInt("docType"));// source;//, "drivethru" หรือ "sale_order",
						queuedata.setReceiver_name(rs.getString("receiveName"));// receiver_name;//: "xxx xxx",
						queuedata.setDoc_date(rs.getString("docdate"));// doc_date;//: “วันที่ออกใบสั่งขาย”,
						queuedata.setPickup_datetime(rs.getString("pickupDatetime"));// pickup_datetime;//: "2015-04-03 10:00",
						queuedata.setTotal_amount(rs.getDouble("totalamount"));// totol_amount;//: “มูลค่า”,
						queuedata.setIs_loaded(rs.getInt("isload"));;// is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
						queuedata.setCar_brand(rs.getString("carBrand"));// car_brand;//: "toyota",
						queuedata.setPlate_number(rs.getString("carLicence"));// plate_number;//: "กท7777",
						queuedata.setStatus_for_saleorder_current(rs.getInt("pickStatus"));//status_for_saleorder_current ;//: “new”
						queuedata.setOtp_password(rs.getString("otpcode"));
						queuedata.setWho_cancel(rs.getString("cancelcode"));
						queuedata.setCancel_remark(rs.getString("cancelremark"));
						
						evt = new SO_Res_ListProductQueueBean();
						evt.setFile_path(rs.getString("filepath"));
						evt.setIs_cancel(rs.getInt("itemcancel"));
						evt.setIs_check(rs.getInt("ischeckout"));
						evt.setItem_barcode(rs.getString("barcode"));
						evt.setItem_code(rs.getString("itemcode"));
						evt.setItem_name(rs.getString("itemname"));
						evt.setPickup_staff_name(rs.getString("pickername"));
						evt.setSale_code(rs.getString("salecode"));
						evt.setItem_price(rs.getDouble("price"));
						evt.setQty_after(rs.getDouble("checkoutqty"));
						evt.setQty_before(rs.getDouble("pickqty"));
						evt.setRequest_qty(rs.getDouble("reqqty"));
						 
						evt.setTotal_price_after(rs.getDouble("checkoutamount"));
						evt.setTotal_price_before(rs.getDouble("itemamount"));
						evt.setItem_unit_code(rs.getString("unitcode"));
						evt.setLine_number(rs.getInt("linenumber"));
						evt.setItem_qty(rs.getDouble("qty"));

						listproduct.add(evt);

					}

					rs.close();
					st.close();

					queuedata.setItem(listproduct);
					
					List<SO_Res_ListOwnerPhoneBean>list_ownerphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
					String vQueryOwner;
					
					try{
					Statement st_owner = ds.getStatement(dbName);
					vQueryOwner = "call USP_DT_SearchOwnerPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
					System.out.println("vQueryOwner : "+vQueryOwner);
					ResultSet rs_owner = st_owner.executeQuery(vQueryOwner);
					
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
						
					}
					rs_owner.close();
					st_owner.close();
					}catch(Exception e){}
					finally{ds.close();}
				
					ArrayList<String> listowner = new ArrayList<>();
					for(int i=0;i<list_ownerphone.size();i++){
						listowner.add(list_ownerphone.get(i).getPhone_no());
					}
					
					queuedata.setOwner_phone(listowner);
					
					List<SO_Res_ListOwnerPhoneBean>list_receiverphone = new ArrayList<SO_Res_ListOwnerPhoneBean>();				
					String vQueryReceiver;
					
					try{
					Statement st_receiver = ds.getStatement(dbName);
					vQueryReceiver = "call USP_DT_SearchReceiverPhone ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
					System.out.println("vQueryReceiver : "+vQueryReceiver);
					ResultSet rs_receiver = st_receiver.executeQuery(vQueryReceiver);
					
					
					SO_Res_ListOwnerPhoneBean evt_receiverphone;
					SO_Res_ListOwnerPhoneBean evt_receiverphone1;
					list_receiverphone.clear();
					while(rs_receiver.next()){
						evt_receiverphone = new SO_Res_ListOwnerPhoneBean();
						evt_receiverphone1 = new SO_Res_ListOwnerPhoneBean();
						evt_receiverphone.setPhone_no(rs_receiver.getString("phone_no"));
						
						if(rs_receiver.getRow()!=0){
							list_receiverphone.add(evt_receiverphone);
						}else{
							list_receiverphone.add(evt_receiverphone1);
						}
						
					}
					rs_receiver.close();
					st_receiver.close();
					}catch(Exception e){}
					finally{ds.close();}
				
					ArrayList<String> listreceiver = new ArrayList<>();
					for(int i=0;i<list_receiverphone.size();i++){
						listreceiver.add(list_receiverphone.get(i).getPhone_no());
					}
					
					queuedata.setReceiver_phone(listreceiver);
					
					
					List<SO_Res_ListQueueStatusBean>list_queuestatus = new ArrayList<SO_Res_ListQueueStatusBean>();				
					String vQueryPickStatus;
					
					try{
					Statement st_status = ds.getStatement(dbName);
					vQueryPickStatus = "call USP_DT_SearchQueueStatusHistory ('"+getQueue.getDocNo()+"','"+getQueue.getOtp_password()+"')";
					System.out.println("vQueryStatus : "+vQueryPickStatus);
					ResultSet rs_status = st_status.executeQuery(vQueryPickStatus);
					
					
					SO_Res_ListQueueStatusBean evt_status;
					SO_Res_ListQueueStatusBean evt_status1;
					list_queuestatus.clear();
					while(rs_status.next()){
						evt_status = new SO_Res_ListQueueStatusBean();
						evt_status1 = new SO_Res_ListQueueStatusBean();
						evt_status.setStatus_id(rs_status.getInt("pick_status"));
						evt_status.setStatus_for_saleorder(rs_status.getString("pick_status"));
						evt_status.setCreate_datetime(rs_status.getString("insertdatetime"));
						
						if(rs_status.getRow()!=0){
							list_queuestatus.add(evt_status);
						}else{
							list_queuestatus.add(evt_status1);
						}
						
					}
					rs_status.close();
					st_status.close();
					}catch(Exception e){}
					finally{ds.close();}
					
					queuedata.setStatus_for_saleorder_history(list_queuestatus);


				}catch (SQLException e) {
					edit.setSuccess(false);
					edit.setError(true);
					edit.setMessage(e.getMessage());

				}finally{
					ds.close();
				}
				
				change.setQueue(queuedata);
			}
			else{
				change.setSuccess(false);
				change.setError(true);
				change.setMessage("Status not assign");
			}
		}else{
			change.setSuccess(false);
			change.setError(true);
			change.setMessage("No queue id");
		}
		
		return change;
	}
	
	public SO_Res_BillingBean PostBilling(String mySqlName,DT_User_LoginBranchBean db,DT_User_LoginBranchBean pos,SO_Req_BillingBean reqsBill){//1
		boolean isSuccess;
		double totalAmount;
		double beforeTaxAmount;
		double taxAmount;

		String vQueryCreditCard;
		String vQueryCoupong;
		String vQuery;
		String vQuerySub;
		String doc_no;
		String vPosNo;
		String ar_code;
		String vQuerySaleOrder;

		LoginBean userCode = new LoginBean();
		
		

		CT_Resp_ResponseBean validateCreditCard = new CT_Resp_ResponseBean();
		CT_Resp_ResponseBean verifyCoupong = new CT_Resp_ResponseBean();
		CT_Resp_ResponseBean verifyDeposit = new CT_Resp_ResponseBean();
		getDataFromData getData = new getDataFromData();

		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();

		System.out.println("Confirm : "+reqsBill.getConfirm());

		PK_Resp_GetDataQueue qIdStatus = new PK_Resp_GetDataQueue();

		qIdStatus = data.searchQueue(reqsBill.getQueue_id());

		totalAmount = data.searchQueueCheckOutAmount(reqsBill.getQueue_id());

		String PosPoint;

		int vCountToken = 0;

		PosPoint = "13";

		PK_Resp_GetDataQueue getQueue = new PK_Resp_GetDataQueue();
		getQueue = getData.searchQueue(reqsBill.getQueue_id());


		if (qIdStatus.getStatus()==2){//2

			if (reqsBill.getConfirm()==0){//3
				totalAmount = data.searchQueueCheckOutAmount(reqsBill.getQueue_id());	
				System.out.println("getCarLicense="+data.searchQueue(reqsBill.getQueue_id()).getCarLicense());
				System.out.println("getSaleOrder="+data.searchQueue(reqsBill.getQueue_id()).getSaleOrder());

				if (reqsBill.getCredit_card().size()!=0){//4

					validateCreditCard = data.validateDataCreditCard_SO(db.getServerName(),db.getDbName(),reqsBill.getCredit_card());

					System.out.println("validate creditcard : "+validateCreditCard.getProcessDesc());
					if (validateCreditCard.getIsSuccess()==true){
						for(int x=0;x<reqsBill.getCredit_card().size();x++){
							System.out.println("CreditCardNo : "+reqsBill.getCredit_card().get(x).getCard_no());

							IV_Reqs_CreditCardBean evt;

							evt = new IV_Reqs_CreditCardBean();

							evt.setCardNo(reqsBill.getCredit_card().get(x).getCard_no());

							evt.setAmount(reqsBill.getCredit_card().get(x).getAmount());
							evt.setBankCode(reqsBill.getCredit_card().get(x).getBank_code());
							evt.setChargeAmount(reqsBill.getCredit_card().get(x).getCharge_amount());
							evt.setConfirmNo(reqsBill.getCredit_card().get(x).getConfirm_no());
							evt.setCreditType(reqsBill.getCredit_card().get(x).getCredit_type());

							crdCard.add(evt);
						}	
					}else{
						validateCreditCard.setIsSuccess(false);
						validateCreditCard.setProcess("validate creditcard");
						validateCreditCard.setProcessDesc(validateCreditCard.getProcessDesc());
					}

				}else{
					validateCreditCard.setIsSuccess(true);
					validateCreditCard.setProcess("validate creditcard");
					validateCreditCard.setProcessDesc("none validate creditcard");
				}//4			

				if(reqsBill.getCoupon_code().size()!=0){//5
					verifyCoupong = data.verifyCoupongBranch_SO("S01",db.getServerName(),db.getDbName(),reqsBill.getCoupon_code());

					System.out.println("verify coupon :"+verifyCoupong.getProcessDesc());
					if (verifyCoupong.getIsSuccess() == true){
						for(int z=0;z<reqsBill.getCoupon_code().size();z++){

							IV_Reqs_CouponBean evt1;

							evt1 = new IV_Reqs_CouponBean();

							evt1.setCouponCode(reqsBill.getCoupon_code().get(z).getCoupon_code());
							evt1.setAmount(reqsBill.getCoupon_code().get(z).getAmount());

							listCoupong.add(evt1);
						}	
					}else{
						verifyCoupong.setIsSuccess(true);
						verifyCoupong.setProcess("verify coupong");
						verifyCoupong.setProcessDesc(verifyCoupong.getProcessDesc());
					}

				}else{
					verifyCoupong.setIsSuccess(true);
					verifyCoupong.setProcess("verify coupong");
					verifyCoupong.setProcessDesc("none verify coupong");
				}//5
				

				if(reqsBill.getDeposit_amount().size()!=0 && getQueue.getDoctype()!=0){//6
					verifyDeposit = data.verifyDeposit(db.getServerName(),db.getDbName(),reqsBill.getAr_code(),reqsBill.getDeposit_amount());


					System.out.println("verify deposit :"+verifyDeposit.getProcessDesc());
					for(int y=0;y<reqsBill.getDeposit_amount().size();y++){

						IV_Req_DepositBean evt2;

						evt2 = new IV_Req_DepositBean();

						evt2.setDeposit_id(reqsBill.getDeposit_amount().get(y).getDeposit_id());
						evt2.setAmount(reqsBill.getDeposit_amount().get(y).getAmount());

						listDeposit.add(evt2);
					}	

				}else{
					verifyDeposit.setIsSuccess(true);
					verifyDeposit.setProcess("verify deposit");
					verifyDeposit.setProcessDesc("none verify deposit");
				}//6
				
				if(reqsBill.getDeposit_amount().size()!=0 && getQueue.getDoctype()==0){
					verifyDeposit.setIsSuccess(false);
					verifyDeposit.setProcess("verify deposit");
					verifyDeposit.setProcessDesc("pos can use deposit");
				}

				System.out.println("Deposit Check = "+verifyDeposit.getIsSuccess()+","+verifyDeposit.getProcess());
				
				if(crdCard.size()!=0){

					for(int a=0;a<crdCard.size();a++){

						checkSumCreditAmount = checkSumCreditAmount+crdCard.get(a).getAmount();
					}
				}

				if(listCoupong.size()!=0){
					for(int b=0;b<listCoupong.size();b++){
						checkSumCouponAmount = checkSumCouponAmount+listCoupong.get(b).getAmount();
						System.out.println("CouponAmount : "+listCoupong.get(b).getAmount());
					}
				}

				if(listDeposit.size()!=0){
					for(int c=0;c<listDeposit.size();c++){
						checkSumDepositAmount = checkSumDepositAmount+listDeposit.get(c).getAmount();
						System.out.println("DepositAmount : "+listDeposit.get(c).getAmount());
					}
				}

				if(getQueue.getDoctype()==0){
					checkCashAmount = reqsBill.getCash();
					checkRemain = (((totalAmount -checkSumCreditAmount)-checkSumCouponAmount)-checkSumDepositAmount);
					if ((checkRemain > 0 && checkCashAmount > 0 && (checkRemain-checkCashAmount <0)) || (checkRemain==0)){
						checkChangeAmount = -1*(checkRemain-checkCashAmount);
					}else{
						checkChangeAmount = 0;
					}

					checkRemainAmount = checkRemain-checkCashAmount+checkChangeAmount;
					if (checkRemainAmount>0){
						sumRemain = checkRemainAmount;
					}else{
						sumRemain = 0;
					}	
				}else{
					checkCashAmount = reqsBill.getCash();
					checkRemain = (((totalAmount -checkSumCreditAmount)-checkSumCouponAmount)-checkSumDepositAmount);

					checkRemainAmount = checkRemain-checkCashAmount;//+checkChangeAmount;
					if (checkRemainAmount>0){
						sumRemain = checkRemainAmount;
					}else{
						sumRemain = 0;
					}	
				}

				

				System.out.println("total :"+totalAmount);
				System.out.println("cash :"+checkCashAmount);
				System.out.println("credit :"+checkSumCreditAmount);
				System.out.println("coupon :"+checkSumCouponAmount);
				System.out.println("deposit :"+checkSumDepositAmount);
				System.out.println("checkChangeAmount :"+checkChangeAmount);
				System.out.println("checkRemain :"+checkRemain);
				System.out.println("checkRemainAmount :"+checkRemainAmount);
				System.out.println("sumRemain :"+sumRemain);

				System.out.println(validateCreditCard.getProcessDesc());
				System.out.println(data.verifyCoupongBranch_SO("S01",db.getServerName(),db.getDbName(),reqsBill.getCoupon_code()).getProcessDesc());

				if (validateCreditCard.getIsSuccess()==false){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage(validateCreditCard.getProcessDesc());
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("Can not save bill");
					respBill.setInvoice(invoice);
					
					
					//System.out.println("Yes0");
				}

				System.out.println("verifyCoupong.getIsSuccess() ="+verifyCoupong.getIsSuccess());
				if (verifyCoupong.getIsSuccess()==false){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage(verifyCoupong.getProcessDesc());
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("Can not save bill");
					respBill.setInvoice(invoice);
				} 

				if (verifyDeposit.getIsSuccess()==false){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage(verifyDeposit.getProcessDesc());
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("Can not save bill");
					respBill.setInvoice(invoice);
				}
				
				System.out.println("getQueue.getDoctype() ="+getQueue.getDoctype());  
				System.out.println("getQueue.getBilltype() ="+getQueue.getBilltype());  
				System.out.println("validateCreditCard.getIsSuccess() ="+validateCreditCard.getIsSuccess());  
				System.out.println("verifyCoupong.getIsSuccess() ="+verifyCoupong.getIsSuccess());  
				System.out.println("verifyDeposit.getIsSuccess() ="+verifyDeposit.getIsSuccess()); 

				if (getQueue.getDoctype() == 0 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true && verifyDeposit.getIsSuccess()==true && checkRemainAmount != 0){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage("This payment is have remaining !!!!");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("Can not save bill");
					respBill.setInvoice(invoice);
				}

				if (getQueue.getDoctype() == 1 && getQueue.getBilltype()== 0 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true &&  verifyDeposit.getIsSuccess()==true && checkRemainAmount != 0){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage("This payment is have remaining !!!!");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("Can not save bill");
					respBill.setInvoice(invoice);

				}

				if (getQueue.getDoctype() == 0 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true &&  verifyDeposit.getIsSuccess()==true && checkRemainAmount == 0){
					respBill.setSuccess(true);
					respBill.setError(false);
					respBill.setMessage("This payment is ready for billing");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("This queue ready to bill");
					respBill.setInvoice(invoice);

				}

				if (getQueue.getDoctype() == 1 && getQueue.getBilltype()== 0 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true &&  verifyDeposit.getIsSuccess()==true && checkRemainAmount == 0){
					respBill.setSuccess(true);
					respBill.setError(false);
					respBill.setMessage("This payment is ready for billing");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("This queue ready to bill");
					respBill.setInvoice(invoice);

				}

				if (getQueue.getDoctype() == 1 && getQueue.getBilltype()== 1 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true &&  verifyDeposit.getIsSuccess()==true && checkRemainAmount>=0){
					respBill.setSuccess(true);
					respBill.setError(false);
					respBill.setMessage("This payment is ready for billing");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("This queue ready to bill");
					respBill.setInvoice(invoice);

				}
				
				if (getQueue.getDoctype() == 1 && getQueue.getBilltype()== 1 && validateCreditCard.getIsSuccess()==true && verifyCoupong.getIsSuccess()==true &&  verifyDeposit.getIsSuccess()==true && checkRemainAmount < 0){
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage("This payment is paid over totalamount");
					respBill.setTotal_amount(totalAmount);
					respBill.setIs_print_cash_form(0);
					respBill.setIs_print_credit_form(0);
					respBill.setIs_print_short_form(0);
					
					invoice.setRemain_amount(sumRemain);
					invoice.setCash_amount(checkCashAmount);
					invoice.setChange_amount(checkChangeAmount);
					invoice.setCoupong_amount(checkSumCouponAmount);
					invoice.setDeposit_amount(checkSumDepositAmount);
					invoice.setCredit_amount(checkSumCreditAmount);
					invoice.setInvoice_no("This payment is paid over totalamount");
					respBill.setInvoice(invoice);

				}

			}else{//confirm == 1
				if(getQueue.getDoctype()==0){//POS======================================================================
					//==================================================================================
					if(reqsBill.getDeposit_amount().size()==0){
					System.out.println("DocType=POS");

					vPosNo = genDoc.genPOSInvoiceNo(PosPoint,pos.getServerName(),pos.getDbName());
					ar = data.searchCustomerName(reqsBill.getAr_code());
					totalAmount = data.searchQueueCheckOutAmount(reqsBill.getQueue_id());
					userCode = data.searchUserAccessToken(reqsBill.getAccess_token());
					que = data.searchQueue(reqsBill.getQueue_id());

					if (reqsBill.getCredit_card().size()!=0){
						validateCreditCard = data.validateDataCreditCard_SO(pos.getServerName(),pos.getDbName(),reqsBill.getCredit_card());
						System.out.println("จำนวน:"+reqsBill.getCredit_card().size());
						for(int x=0;x<reqsBill.getCredit_card().size();x++){

							IV_Reqs_CreditCardBean evt;

							evt = new IV_Reqs_CreditCardBean();

							evt.setCardNo(reqsBill.getCredit_card().get(x).getCard_no());

							evt.setAmount(reqsBill.getCredit_card().get(x).getAmount());
							evt.setBankCode(reqsBill.getCredit_card().get(x).getBank_code());
							evt.setChargeAmount(reqsBill.getCredit_card().get(x).getCharge_amount());
							evt.setConfirmNo(reqsBill.getCredit_card().get(x).getConfirm_no());
							evt.setCreditType(reqsBill.getCredit_card().get(x).getCredit_type());

							checkSumCreditAmount = checkSumCreditAmount+reqsBill.getCredit_card().get(x).getAmount();
							crdCard.add(evt);
						}
					}else{
						validateCreditCard.setIsSuccess(true);
						checkSumCreditAmount = 0;
						IV_Reqs_CreditCardBean evt1;

						evt1 = new IV_Reqs_CreditCardBean();

						evt1.setCardNo("");

						evt1.setAmount(0);
						evt1.setBankCode("");
						evt1.setChargeAmount(0);
						evt1.setConfirmNo("");
						evt1.setCreditType("");

						crdCard.add(evt1);
					}

					if(reqsBill.getCoupon_code().size()!=0){
						verifyCoupong = data.verifyCoupongBranch_SO("S01",pos.getServerName(),pos.getDbName(),reqsBill.getCoupon_code());

						for(int y=0;y<reqsBill.getCoupon_code().size();y++){
							IV_Reqs_CouponBean evt;

							evt = new IV_Reqs_CouponBean();

							evt.setCouponCode(reqsBill.getCoupon_code().get(y).getCoupon_code());
							evt.setAmount(reqsBill.getCoupon_code().get(y).getAmount());

							checkSumCouponAmount=checkSumCouponAmount+reqsBill.getCoupon_code().get(y).getAmount();

							listCoupong.add(evt);

						}
					}else{
						verifyCoupong.setIsSuccess(true);
						checkSumCouponAmount=0;
						IV_Reqs_CouponBean evt1;

						evt1 = new IV_Reqs_CouponBean();

						evt1.setCouponCode("");
						evt1.setAmount(0);

						listCoupong.add(evt1);
					}
					
					checkCashAmount = reqsBill.getCash();
					checkRemain = ((totalAmount -checkSumCreditAmount)-checkSumCouponAmount);
					if (checkRemain-checkCashAmount <0){
						checkChangeAmount = -1*(checkRemain-checkCashAmount);
					}else{
						checkChangeAmount = 0;
					}
					checkRemainAmount = checkRemain-checkCashAmount+checkChangeAmount;
					
					if (checkRemainAmount>0){
						sumRemain = checkRemainAmount;
					}else{
						sumRemain = 0;
					}

					System.out.println(checkRemainAmount);

					double bfTaxAmount;

					bfTaxAmount = (totalAmount*100)/107;


					BigDecimal newBeforeTaxAmount = new BigDecimal(bfTaxAmount);
					BigDecimal changBFAmount;
					changBFAmount = newBeforeTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
					beforeTaxAmount = changBFAmount.doubleValue();

					taxAmount = totalAmount-beforeTaxAmount;


					System.out.println("save ok"+validateCreditCard.getIsSuccess());

					if (validateCreditCard.getIsSuccess()==true){
						System.out.println("savecardit ok");
						if (verifyCoupong.getIsSuccess()==true){
							System.out.println("savecoupong ok");
							if (checkRemainAmount ==0){
								System.out.println("saveremain ok");
								try{	

									Statement st = sqlDS.getSqlStatement(pos.getServerName(),pos.getDbName());
									sumCreditAmount=0;
									sumCouponAmount=0;

									if(crdCard.size()!=0){
										for(int a=0;a<crdCard.size();a++){

											sumCreditAmount = sumCreditAmount+crdCard.get(a).getAmount();
											System.out.println("CreditCardAmount : "+crdCard.get(a).getAmount());
										}
									}

									if(listCoupong.size()!=0){
										for(int b=0;b<listCoupong.size();b++){
											sumCouponAmount = sumCouponAmount+listCoupong.get(b).getAmount();
											System.out.println("CouponAmount : "+listCoupong.get(b).getAmount());
										}
									}

									header.setDocNo(vPosNo);
									header.setDocDate(dateFormat.format(dateNow));
									header.setArCode(reqsBill.getAr_code());
									header.setArName(ar.getArName());
									header.setTaxNo("");
									header.setTaxType(1);
									header.setArAddress(ar.getArAddress());
									header.setCashierCode(userCode.getEmployeeCode());
									header.setMachineNo(PosPoint);
									header.setMachineCode("50011-91-00547");
									header.setPosStatus(1);
									header.setCreditType(crdCard.get(0).getCreditType());
									header.setCreditNo(crdCard.get(0).getCardNo());
									header.setConfirmNo(crdCard.get(0).getConfirmNo());
									header.setChargeWord("");

									header.setCreditBaseAmount(crdCard.get(0).getAmount());
									header.setChargeAmount(crdCard.get(0).getChargeAmount());
									header.setGrandTotal(totalAmount);
									header.setChangeAmount(checkChangeAmount);
									header.setDepartCode("S01-00-00");
									header.setCreditDay(0);
									header.setDueDate("");
									header.setSaleCode(data.searchQueue(reqsBill.getQueue_id()).getSaleCode());
									header.setTaxRate(7);
									header.setIsConfirm(0);
									header.setMyDescription("DriveThru");
									header.setBillType(0);
									header.setBillGroup("");
									header.setRefDocNo(que.getDocNo());
									header.setSumOfItemAmount(totalAmount);
									header.setDiscountWord("");
									header.setDiscountAmount(0);
									header.setAfterDiscount(totalAmount);
									header.setBeforeTaxAmount(beforeTaxAmount);
									header.setTaxAmount(taxAmount);
									header.setTotalAmount(totalAmount);
									header.setZeroTaxAmount(0);
									header.setExceptTaxAmount(0);
									header.setSumCashAmount(reqsBill.getCash());

									header.setSumChqAmount(0);
									header.setSumCreditAmount(sumCreditAmount);
									header.setCoupongAmount(sumCouponAmount);
									header.setSumBankAmount(0);
									header.setDepositIncTax(0);
									header.setSumOfDeposit1(sumDepositAmount);
									header.setSumOfDeposit2(0);
									header.setSumOfWTax(0);
									header.setNetDebtAmount(totalAmount);
									header.setHomeAmount(totalAmount);
									header.setOtherIncome(0);
									header.setOtherExpense(0);
									header.setExcessAmount1(0);
									header.setExcessAmount2(0);
									header.setBillBalance(totalAmount);
									header.setExchangeRate(1);
									header.setIsCancel(0);
									header.setIsCompleteSave(1);
									header.setIsPostGL(0);
									header.setPayBillStatus(0);
									header.setAllocateCode("");
									header.setProjectCode("");
									header.setIsConditionSend(0);
									header.setPayBillAmount(0);
									header.setSoRefNo(data.searchQueue(reqsBill.getQueue_id()).getCarLicense());
									header.setShiftCode("กลางวัน");


									//data.searchQueueCheckOutItem(reqsBill.getqId());

									IV_Resp_ARInvoiceSubBean listInv;
									double itemAmount=0;
									double netAmount=0;
									double qty=0;
									double price=0;

									List<IV_Resp_ARInvoiceSubBean> list_item = new ArrayList<IV_Resp_ARInvoiceSubBean>();
									sub.clear();

									list_item = data.searchQueueCheckOutItem(reqsBill.getQueue_id());
									if(list_item.size()!=0){
										for(int m =0;m<list_item.size();m++){
											listInv = new IV_Resp_ARInvoiceSubBean();
											qty = list_item.get(m).getQty();
											price = list_item.get(m).getPrice();
											itemAmount = qty*price;
											netAmount = (itemAmount*100)/107;
											System.out.println(list_item.get(m).getItemCode());
											listInv.setItemCode(list_item.get(m).getItemCode());
											listInv.setItemName(list_item.get(m).getItemName());
											listInv.setBarCode(list_item.get(m).getBarCode());
											listInv.setQty(list_item.get(m).getQty());
											listInv.setPrice(list_item.get(m).getPrice());
											listInv.setUnitCode(list_item.get(m).getUnitCode());
											listInv.setPackingRate1(list_item.get(m).getPackingRate1());
											listInv.setDiscountAmount(0);
											listInv.setWhCode("S1-B");
											listInv.setShelfCode("-");
											listInv.setMachineCode("50011-91-00547");
											listInv.setMachineNo(PosPoint);
											listInv.setShiftNo(0);
											listInv.setShiftCode("กลางวัน");
											listInv.setAmount(itemAmount);
											listInv.setNetAmount(netAmount);
											listInv.setHomeAmount(netAmount);
											listInv.setSumOfCost(list_item.get(m).getSumOfCost());
											listInv.setSaleCode(list_item.get(m).getSaleCode());
											sub.add(listInv);
										}
									}

									bill.setBillHeader(header);
									bill.setBillSub(sub);


									System.out.println("SaleCodeBill = "+bill.getBillHeader().getSaleCode());

									vQuery = "set dateformat dmy  insert into dbo.BCARInvoice(docNo,docDate,taxNo,taxType,arCode,arName,arAddress,cashierCode,"
											+"machineNo,machineCode,posStatus,billTime,creditType,creditNo,cofirmNo,chargeWord,creditBaseAmount,"
											+"chargeAmount,grandTotal,coupongAmount,changeAmount,departCode,creditDay,dueDate,saleCode,taxRate,"
											+"isConfirm,myDescription,billType,billGroup,refDocNo,sumOfItemAmount,discountWord,discountAmount,"
											+"afterDiscount,beforeTaxAmount,taxAmount,totalAmount,zeroTaxAmount,exceptTaxAmount,sumCashAmount,"
											+"sumChqAmount,sumCreditAmount,sumBankAmount,depositIncTax,sumOfDeposit1,sumOfDeposit2,sumOfWTax,"
											+"netDebtAmount,homeAmount,otherIncome,otherExpense,excessAmount1,excessAmount2,billBalance,exchangeRate,"
											+"isCancel,isCompleteSave,isPostGL,payBillStatus,allocateCode,projectCode,creatorCode,isConditionSend,"
											+"payBillAmount,sORefNo,shiftCode,createdatetime) values( "
											+" '"+bill.getBillHeader().getDocNo()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getTaxNo()+"',"
											+" "+bill.getBillHeader().getTaxType()+",'"+bill.getBillHeader().getArCode()+"','"+bill.getBillHeader().getArName()+"',"
											+" '"+bill.getBillHeader().getArAddress()+"','"+bill.getBillHeader().getCashierCode()+"','"+bill.getBillHeader().getMachineNo()+"',"
											+" '"+bill.getBillHeader().getMachineCode()+"',"+bill.getBillHeader().getPosStatus()+","+"cast(datepart(hour,GETDATE()) as varchar(2))+':'+ cast(datepart(minute,GETDATE())as varchar(2))"+","
											+" '"+bill.getBillHeader().getCreditType()+"','"+bill.getBillHeader().getCreditNo()+"','"+bill.getBillHeader().getConfirmNo()+"',"
											+" '"+bill.getBillHeader().getChargeWord()+"',"+bill.getBillHeader().getCreditBaseAmount()+","+bill.getBillHeader().getChargeAmount()+","
											+" "+bill.getBillHeader().getGrandTotal()+","+bill.getBillHeader().getCoupongAmount()+","+bill.getBillHeader().getChangeAmount()+","
											+" '"+bill.getBillHeader().getDepartCode()+"',"+bill.getBillHeader().getCreditDay()+",'"+bill.getBillHeader().getDueDate()+"',"
											+" '"+bill.getBillHeader().getSaleCode()+"',"+bill.getBillHeader().getTaxRate()+","+bill.getBillHeader().getIsConfirm()+","
											+" '"+bill.getBillHeader().getMyDescription()+"',"+bill.getBillHeader().getBillType()+",'"+bill.getBillHeader().getBillGroup()+"',"
											+" '"+bill.getBillHeader().getRefDocNo()+"',"+bill.getBillHeader().getSumOfItemAmount()+",'"+bill.getBillHeader().getDiscountWord()+"',"
											+" "+bill.getBillHeader().getDiscountAmount()+","+bill.getBillHeader().getAfterDiscount()+","+bill.getBillHeader().getBeforeTaxAmount()+","
											+" "+bill.getBillHeader().getTaxAmount()+","+bill.getBillHeader().getTotalAmount()+","+bill.getBillHeader().getZeroTaxAmount()+","
											+" "+bill.getBillHeader().getExceptTaxAmount()+","+bill.getBillHeader().getSumCashAmount()+","+bill.getBillHeader().getSumChqAmount()+","
											+" "+bill.getBillHeader().getSumCreditAmount()+","+bill.getBillHeader().getSumBankAmount()+","+bill.getBillHeader().getDepositIncTax()+","
											+" "+bill.getBillHeader().getSumOfDeposit1()+","+bill.getBillHeader().getSumOfDeposit2()+","+bill.getBillHeader().getSumOfWTax()+","
											+" "+bill.getBillHeader().getNetDebtAmount()+","+bill.getBillHeader().getHomeAmount()+","+bill.getBillHeader().getOtherIncome()+","
											+" "+bill.getBillHeader().getOtherExpense()+","+bill.getBillHeader().getExcessAmount1()+","+bill.getBillHeader().getExcessAmount2()+","
											+" "+bill.getBillHeader().getBillBalance()+","+bill.getBillHeader().getExchangeRate()+","+bill.getBillHeader().getIsCancel()+","
											+" "+bill.getBillHeader().getIsCompleteSave()+","+bill.getBillHeader().getIsPostGL()+","+bill.getBillHeader().getPayBillAmount()+","
											+" '"+bill.getBillHeader().getAllocateCode()+"','"+bill.getBillHeader().getProjectCode()+"','"+userCode.getEmployeeCode()+"',"
											+" "+bill.getBillHeader().getIsConditionSend()+","+bill.getBillHeader().getPayBillAmount()+",'"+bill.getBillHeader().getSoRefNo()+"','"
											+" "+bill.getBillHeader().getShiftCode()+"',getdate() "
											+" )";
									System.out.println(vQuery);
									isSuccess = npExec.executeSql(pos.getServerName(),pos.getDbName(), vQuery);

									System.out.println("InvoiceSub :"+sub.size());

									for(int i=0;i<sub.size();i++){

										vQuerySub=	"set dateformat dmy  insert into dbo.BCARInvoiceSub(docNo,taxNo,taxType,itemCode,docDate,arCode,departCode,"
												+"saleCode,myDescription,itemName,whCode,shelfCode,cnQty,qty,price,discountWord,"
												+"discountAmount,amount,netAmount,homeAmount,sumOfCost,balanceAmount,unitCode,"
												+"soRefNo,poRefNo,stockType,lineNumber,refLineNumber,isCancel,allocateCode,projectCode,"
												+"exchangeRate,barCode,machineNo,machineCode,billTime,cashierCode,shiftNo,posStatus,"+
												"isConditionSend,taxRate,packingRate1) "
												+" values( '"+bill.getBillHeader().getDocNo()+"','"+bill.getBillHeader().getTaxNo()+"',"+bill.getBillHeader().getTaxType()+","
												+" '"+bill.getBillSub().get(i).getItemCode()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getArCode()+"',"
												+" '"+bill.getBillHeader().getDepartCode()+"','"+bill.getBillSub().get(i).getSaleCode()+"','"+bill.getBillSub().get(i).getMyDescription()+"',"
												+" '"+bill.getBillSub().get(i).getItemName()+"','"+bill.getBillSub().get(i).getWhCode()+"','"+bill.getBillSub().get(i).getShelfCode()+"',"
												+" "+bill.getBillSub().get(i).getQty()+","+bill.getBillSub().get(i).getQty()+","+bill.getBillSub().get(i).getPrice()+","
												+" '"+bill.getBillSub().get(i).getDiscountWord()+"',"+bill.getBillSub().get(i).getDiscountAmount()+","+bill.getBillSub().get(i).getAmount()+","
												+" "+bill.getBillSub().get(i).getNetAmount()+","+bill.getBillSub().get(i).getHomeAmount()+","+bill.getBillSub().get(i).getSumOfCost()+","
												+" "+bill.getBillSub().get(i).getBalanceAmount()+",'"+bill.getBillSub().get(i).getUnitCode()+"','"+bill.getBillSub().get(i).getSoRefNo()+"',"
												+" '"+bill.getBillSub().get(i).getPoRefNo()+"',"+bill.getBillSub().get(i).getStockType()+","+i+",0,"+bill.getBillSub().get(i).getIsCancel()+","
												+" '"+bill.getBillSub().get(i).getAllocateCode()+"','"+bill.getBillSub().get(i).getProjectCode()+"',"+bill.getBillSub().get(i).getExchangeRate()+","
												+" '"+bill.getBillSub().get(i).getBarCode()+"','"+bill.getBillSub().get(i).getMachineNo()+"','"+bill.getBillSub().get(i).getMachineCode()+"',"
												+" "+"cast(datepart(hour,GETDATE()) as varchar(2))+':'+ cast(datepart(minute,GETDATE())as varchar(2))"+",'"+bill.getBillSub().get(i).getCashierCode()+"',"
												+" '"+bill.getBillSub().get(i).getShiftNo()+"',"+bill.getBillHeader().getPosStatus()+","+bill.getBillHeader().getIsConditionSend()+","
												+" "+bill.getBillHeader().getTaxRate()+","+bill.getBillSub().get(i).getPackingRate1()
												+")";
										System.out.println(vQuerySub);
										isSuccess = npExec.executeSql(pos.getServerName(),pos.getDbName(), vQuerySub);

									}

									if(crdCard.size()!=0 && crdCard.get(0).getCardNo()!=""){
										for(int a=0;a<crdCard.size();a++){

											vQueryCreditCard = "set dateformat dmy insert into dbo.BCCreditCard(BankCode,CreditCardNo,DocNo,ArCode,ReceiveDate,DueDate,Status,SaveFrom,Amount,MyDescription,ExchangeRate,CreditType,ConfirmNo,ChargeAmount,CreatorCode,CreateDateTime) values( "
													+" '"+crdCard.get(a).getBankCode()+"','"+crdCard.get(a).getCardNo()+"','"+bill.getBillHeader().getDocNo()+"','"+bill.getBillHeader().getArCode()+"',"
													+" cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),"+"0,1"+","+crdCard.get(a).getAmount()+",'"+"ขายหน้าร้าน"+"',"
													+" "+"1.0000000000"+",'"+crdCard.get(a).getCreditType()+"','"+crdCard.get(a).getConfirmNo()+"',"+crdCard.get(a).getChargeAmount()+",'"+userCode.getEmployeeCode()+"',getdate()"
													+" )";
											System.out.println("POS CreditCard"+vQueryCreditCard);
											isSuccess = npExec.executeSql(pos.getServerName(),pos.getDbName(), vQueryCreditCard);
										}
									}

									if(listCoupong.size()!=0 && listCoupong.get(0).getCouponCode()!= ""){
										for(int b=0;b<listCoupong.size();b++){
											vQueryCoupong="set dateformat dmy insert into dbo.bccouponreceive(COUPONCODE,COUPONTYPE,COUPONNO,TOCOUPONNO,COUPONCOUNT,DOCNO,BOOK,COUPONVAL,LINENUMBER,CREATORCODE,CREATEDATETIME) values( "
													+" '"+listCoupong.get(b).getCouponCode()+"',1,'"+listCoupong.get(b).getCouponCode()+"','"+listCoupong.get(b).getCouponCode()+"',1,'"+bill.getBillHeader().getDocNo()+"',"
													+" '"+listCoupong.get(b).getCouponCode()+"',"+listCoupong.get(b).getAmount()+","+b+",'"+userCode.getEmployeeCode()+"',getdate()"
													+" )";
											System.out.println("POS Coupon"+vQueryCoupong);
											isSuccess = npExec.executeSql(pos.getServerName(),pos.getDbName(), vQueryCoupong);
										}
									}

									vQuery="update Queue set status = 3,invoiceNo = '"+bill.getBillHeader().getDocNo()+"' where docNo ='"+que.getDocNo()+"'";
									isSuccess= excecute.execute(mySqlName,vQuery);

									IV_Reqs_PrintSlipBean req = new IV_Reqs_PrintSlipBean();
									req.setAccess_token(reqsBill.getAccess_token());
									req.setType(0);
									req.setInvoiceNo(bill.getBillHeader().getDocNo());
									req.setArCode(bill.getBillHeader().getArCode());

//////////////////////////////////////////////////////////////////////////////////////////
									if (isSuccess ==true){
										
										System.out.println("POS NUMBER ="+req.getInvoiceNo());

										IV_Resp_PrintSlipBean copyInv = new IV_Resp_PrintSlipBean();

										copyInv = data.copyPosHTML(pos, req);

										System.out.println("ItemName:"+copyInv.getItem().get(0).getItemName());

										HttpClient httpClient = HttpClientBuilder.create().build(); 

										try {
											Gson gson = new Gson();
											String json = gson.toJson(copyInv); 

											System.out.println(json.charAt(0));

											HttpPost request = new HttpPost("http://api.nopadol.com/drivethru/copy/index.php");
											StringEntity params =new StringEntity(json,HTTP.UTF_8);
											request.addHeader("content-type", "application/json; charset=utf-8");
											request.setEntity(params);
											HttpResponse response = httpClient.execute(request);

											System.out.println("CompanyName:"+copyInv.getCompanyName());

										}catch (Exception ex) {

										} finally {
											httpClient.getConnectionManager().shutdown(); //Deprecated
										}
									}

									respBill.setTotal_amount(totalAmount);
									respBill.setIs_print_cash_form(0);
									respBill.setIs_print_credit_form(0);
									respBill.setIs_print_short_form(1);
									
									invoice.setInvoice_no(bill.getBillHeader().getDocNo());
									invoice.setCash_amount(reqsBill.getCash());
									invoice.setCredit_amount(sumCreditAmount);
									invoice.setChange_amount(checkChangeAmount);
									invoice.setCoupong_amount(sumCouponAmount);
									invoice.setDeposit_amount(sumDepositAmount);
									invoice.setRemain_amount(sumRemain);

									respBill.setInvoice(invoice);

									respBill.setSuccess(true);
									respBill.setError(false);
									respBill.setMessage("");


								}catch(SQLException e){
									e.printStackTrace();
									System.out.println("Remain No");

									respBill.setTotal_amount(totalAmount);
									respBill.setIs_print_cash_form(0);
									respBill.setIs_print_credit_form(0);
									respBill.setIs_print_short_form(0);
									
									invoice.setRemain_amount(sumRemain);
									invoice.setInvoice_no("Can not save bill");
									invoice.setCash_amount(reqsBill.getCash());
									invoice.setCredit_amount(sumCreditAmount);
									invoice.setChange_amount(checkChangeAmount);
									invoice.setCoupong_amount(sumCouponAmount);
									invoice.setDeposit_amount(sumDepositAmount);

									respBill.setInvoice(invoice);

									respBill.setSuccess(false);
									respBill.setError(true);
									respBill.setMessage(e.getMessage());

								}finally{
									ds.close();
									sqlDS.close();
								}

							}else{
								respBill.setTotal_amount(totalAmount);
								respBill.setIs_print_cash_form(0);
								respBill.setIs_print_credit_form(0);
								respBill.setIs_print_short_form(0);
								
								invoice.setRemain_amount(sumRemain);
								invoice.setInvoice_no("Can not save bill");
								invoice.setCash_amount(reqsBill.getCash());
								invoice.setCredit_amount(sumCreditAmount);
								invoice.setChange_amount(checkChangeAmount);
								invoice.setCoupong_amount(sumCouponAmount);
								invoice.setDeposit_amount(sumDepositAmount);

								respBill.setInvoice(invoice);

								respBill.setSuccess(false);
								respBill.setError(true);
								respBill.setMessage("This payment have remain <> 0");

							}
						}else{
							respBill.setTotal_amount(totalAmount);
							respBill.setIs_print_cash_form(0);
							respBill.setIs_print_credit_form(0);
							respBill.setIs_print_short_form(0);
							
							invoice.setRemain_amount(sumRemain);
							invoice.setInvoice_no("Can not save bill");
							invoice.setCash_amount(reqsBill.getCash());
							invoice.setCredit_amount(sumCreditAmount);
							invoice.setChange_amount(checkChangeAmount);
							invoice.setCoupong_amount(sumCouponAmount);
							invoice.setDeposit_amount(sumDepositAmount);

							respBill.setInvoice(invoice);

							respBill.setSuccess(false);
							respBill.setError(true);
							respBill.setMessage(verifyCoupong.getProcessDesc());

						}
					}else{
						respBill.setTotal_amount(totalAmount);
						respBill.setIs_print_cash_form(0);
						respBill.setIs_print_credit_form(0);
						respBill.setIs_print_short_form(0);
						
						invoice.setRemain_amount(sumRemain);
						invoice.setInvoice_no("Can not save bill");
						invoice.setCash_amount(reqsBill.getCash());
						invoice.setCredit_amount(sumCreditAmount);
						invoice.setChange_amount(checkChangeAmount);
						invoice.setCoupong_amount(sumCouponAmount);
						invoice.setDeposit_amount(sumDepositAmount);

						respBill.setInvoice(invoice);

						respBill.setSuccess(false);
						respBill.setError(true);
						respBill.setMessage(verifyCoupong.getProcessDesc());

					}
				}else{
					respBill.setSuccess(false);
					respBill.setError(true);
					respBill.setMessage("pos can use deposit");
				}
				}else{//Sale Order======================================================================
					//==================================================================================
					System.out.println("DocType=SaleOrder");
					
					vPosNo = genDoc.genInvoiceNo(reqsBill.getAccess_token(), qIdStatus.getBilltype());
					ar = data.searchCustomerName(reqsBill.getAr_code());
					totalAmount = data.searchQueueCheckOutAmount(reqsBill.getQueue_id());
					userCode = data.searchUserAccessToken(reqsBill.getAccess_token());
					que = data.searchQueue(reqsBill.getQueue_id());
					
					
					if (reqsBill.getCredit_card().size()!=0){
						validateCreditCard = data.validateDataCreditCard_SO(db.getServerName(),db.getDbName(),reqsBill.getCredit_card());
						System.out.println("จำนวน:"+reqsBill.getCredit_card().size());
						for(int x=0;x<reqsBill.getCredit_card().size();x++){

							IV_Reqs_CreditCardBean evt;

							evt = new IV_Reqs_CreditCardBean();

							evt.setCardNo(reqsBill.getCredit_card().get(x).getCard_no());

							evt.setAmount(reqsBill.getCredit_card().get(x).getAmount());
							evt.setBankCode(reqsBill.getCredit_card().get(x).getBank_code());
							evt.setChargeAmount(reqsBill.getCredit_card().get(x).getCharge_amount());
							evt.setConfirmNo(reqsBill.getCredit_card().get(x).getConfirm_no());
							evt.setCreditType(reqsBill.getCredit_card().get(x).getCredit_type());

							checkSumCreditAmount = checkSumCreditAmount+reqsBill.getCredit_card().get(x).getAmount();
							crdCard.add(evt);
						}
					}else{
						validateCreditCard.setIsSuccess(true);
						checkSumCreditAmount = 0;
						IV_Reqs_CreditCardBean evt1;

						evt1 = new IV_Reqs_CreditCardBean();

						evt1.setCardNo("");

						evt1.setAmount(0);
						evt1.setBankCode("");
						evt1.setChargeAmount(0);
						evt1.setConfirmNo("");
						evt1.setCreditType("");

						crdCard.add(evt1);
					}

					if(reqsBill.getCoupon_code().size()!=0){
						verifyCoupong = data.verifyCoupongBranch_SO("S01",db.getServerName(),db.getDbName(),reqsBill.getCoupon_code());

						for(int y=0;y<reqsBill.getCoupon_code().size();y++){
							IV_Reqs_CouponBean evt;

							evt = new IV_Reqs_CouponBean();

							evt.setCouponCode(reqsBill.getCoupon_code().get(y).getCoupon_code());
							evt.setAmount(reqsBill.getCoupon_code().get(y).getAmount());

							checkSumCouponAmount=checkSumCouponAmount+reqsBill.getCoupon_code().get(y).getAmount();

							listCoupong.add(evt);

						}
					}else{
						verifyCoupong.setIsSuccess(true);
						checkSumCouponAmount=0;
						IV_Reqs_CouponBean evt1;

						evt1 = new IV_Reqs_CouponBean();

						evt1.setCouponCode("");
						evt1.setAmount(0);

						listCoupong.add(evt1);
					}
					
					if(reqsBill.getDeposit_amount().size()!=0){
						verifyDeposit = data.verifyDeposit(db.getServerName(),db.getDbName(),reqsBill.getAr_code(),reqsBill.getDeposit_amount());

						for(int z=0;z<reqsBill.getDeposit_amount().size();z++){
							IV_Req_DepositBean evt2;

							evt2 = new IV_Req_DepositBean();

							evt2.setDeposit_id(reqsBill.getDeposit_amount().get(z).getDeposit_id());
							evt2.setAmount(reqsBill.getDeposit_amount().get(z).getAmount());

							checkSumDepositAmount=checkSumDepositAmount+reqsBill.getDeposit_amount().get(z).getAmount();

							listDeposit.add(evt2);

						}
					}else{
						verifyDeposit.setIsSuccess(true);
						checkSumDepositAmount=0;
						IV_Req_DepositBean evt2;

						evt2 = new IV_Req_DepositBean();

						evt2.setDeposit_id("");
						evt2.setAmount(0.0);

						listDeposit.add(evt2);
					}

					checkCashAmount = reqsBill.getCash();
					checkRemain = (((totalAmount -checkSumCreditAmount)-checkSumCouponAmount)-checkSumDepositAmount);
//					if (checkRemain-checkCashAmount <0){
//						checkChangeAmount = -1*(checkRemain-checkCashAmount);
//					}else{
//						checkChangeAmount = 0;
//					}
					checkRemainAmount = checkRemain-checkCashAmount;//+checkChangeAmount;
					
					if (checkRemainAmount>0){
						sumRemain = checkRemainAmount;
					}else{
						sumRemain = 0;
					}

					System.out.println(checkRemainAmount);

					double bfTaxAmount;

					bfTaxAmount = (totalAmount*100)/107;


					BigDecimal newBeforeTaxAmount = new BigDecimal(bfTaxAmount);
					BigDecimal changBFAmount;
					changBFAmount = newBeforeTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
					beforeTaxAmount = changBFAmount.doubleValue();

					taxAmount = totalAmount-beforeTaxAmount;


					System.out.println("save ok"+validateCreditCard.getIsSuccess());

					if (validateCreditCard.getIsSuccess()==true){
						System.out.println("savecardit ok");
						if (verifyCoupong.getIsSuccess()==true){
							System.out.println("savecoupong ok");
							if (verifyDeposit.getIsSuccess()==true){
								System.out.println("savedeposit ok");
								if ((qIdStatus.getBilltype()==0 && checkRemainAmount ==0) || (qIdStatus.getBilltype()==1)){
									System.out.println("saveremain ok");
									try{	

										Statement st = sqlDS.getSqlStatement(db.getServerName(),db.getDbName());
										sumCreditAmount=0;
										sumCouponAmount=0;
										sumDepositAmount=0;

										if(crdCard.size()!=0){
											for(int a=0;a<crdCard.size();a++){

												sumCreditAmount = sumCreditAmount+crdCard.get(a).getAmount();
												System.out.println("CreditCardAmount : "+crdCard.get(a).getAmount());
											}
										}

										if(listCoupong.size()!=0){
											for(int b=0;b<listCoupong.size();b++){
												sumCouponAmount = sumCouponAmount+listCoupong.get(b).getAmount();
												System.out.println("CouponAmount : "+listCoupong.get(b).getAmount());
											}
										}
										
										if(listDeposit.size()!=0){
											for(int c=0;c<listDeposit.size();c++){
												sumDepositAmount = sumDepositAmount+listDeposit.get(c).getAmount();
												System.out.println("DepositAmount : "+listDeposit.get(c).getAmount());
											}
										}
										
										double inv_totalAmount=0;
										double inv_beforeTaxAmount=0;
										double inv_taxAmount=0;
										
										if(qIdStatus.getTaxType()==0){
											inv_beforeTaxAmount = beforeTaxAmount;
											inv_taxAmount = taxAmount;
											inv_totalAmount = totalAmount+taxAmount;
										}
										if(qIdStatus.getTaxType()==1){
											inv_beforeTaxAmount = beforeTaxAmount;
											inv_taxAmount = taxAmount;
											inv_totalAmount = totalAmount;
										}
										if(qIdStatus.getTaxType()==2){
											inv_beforeTaxAmount = beforeTaxAmount;
											inv_taxAmount = 0;
											inv_totalAmount = totalAmount;
										}

										header.setDocNo(vPosNo);
										header.setDocDate(dateFormat.format(dateNow));
										header.setArCode(reqsBill.getAr_code());
										header.setArName(ar.getArName());
										header.setTaxNo(vPosNo);
										header.setTaxType(qIdStatus.getTaxType());
										header.setArAddress(ar.getArAddress());
										header.setCashierCode(userCode.getEmployeeCode());
										header.setMachineNo("");
										header.setMachineCode("");
										header.setPosStatus(0);
										header.setCreditType(crdCard.get(0).getCreditType());
										header.setCreditNo(crdCard.get(0).getCardNo());
										header.setConfirmNo(crdCard.get(0).getConfirmNo());
										header.setChargeWord("");

										header.setCreditBaseAmount(crdCard.get(0).getAmount());
										header.setChargeAmount(crdCard.get(0).getChargeAmount());
										header.setGrandTotal(totalAmount);
										header.setChangeAmount(0);
										header.setDepartCode("S01-00-00");
										header.setCreditDay(0);
										header.setDueDate("");
										header.setSaleCode(qIdStatus.getSaleCode());
										header.setTaxRate(7);
										header.setIsConfirm(0);
										header.setMyDescription("DriveThru");
										header.setBillType(qIdStatus.getBilltype());
										header.setBillGroup("");
										header.setRefDocNo(que.getDocNo());
										header.setSumOfItemAmount(inv_totalAmount);
										header.setDiscountWord("");
										header.setDiscountAmount(0);
										header.setAfterDiscount(inv_totalAmount);
										header.setBeforeTaxAmount(inv_beforeTaxAmount);
										header.setTaxAmount(inv_taxAmount);
										header.setTotalAmount(inv_totalAmount);
										header.setZeroTaxAmount(0);
										header.setExceptTaxAmount(0);
										header.setSumCashAmount(reqsBill.getCash());

										header.setSumChqAmount(0);
										header.setSumCreditAmount(sumCreditAmount);
										header.setCoupongAmount(sumCouponAmount);
										header.setSumBankAmount(0);
										header.setDepositIncTax(1);
										header.setSumOfDeposit1(sumDepositAmount);
										header.setSumOfDeposit2(((sumDepositAmount*100)/107));
										header.setSumOfWTax(0);
										header.setNetDebtAmount(inv_totalAmount);
										header.setHomeAmount(inv_totalAmount);
										header.setOtherIncome(0);
										header.setOtherExpense(0);
										header.setExcessAmount1(0);
										header.setExcessAmount2(0);
										header.setBillBalance(checkRemainAmount);
										header.setExchangeRate(1);
										header.setIsCancel(0);
										header.setIsCompleteSave(1);
										header.setIsPostGL(0);
										header.setPayBillStatus(0);
										header.setAllocateCode("");
										header.setProjectCode("");
										header.setIsConditionSend(0);
										header.setPayBillAmount(0);
										header.setSoRefNo(data.searchQueue(reqsBill.getQueue_id()).getSaleOrder());
										header.setShiftCode("");
										header.setRecurName(data.searchQueue(reqsBill.getQueue_id()).getCarLicense());


										//data.searchQueueCheckOutItem(reqsBill.getqId());

										IV_Resp_ARInvoiceSubBean listInv;
										double itemAmount=0;
										double netAmount=0;
										double qty=0;
										double price=0;
										double inv_itemAmount=0;
										double inv_netAmount=0;

										List<IV_Resp_ARInvoiceSubBean> list_item = new ArrayList<IV_Resp_ARInvoiceSubBean>();
										sub.clear();

										list_item = data.searchQueueCheckOutItem(reqsBill.getQueue_id());
										if(list_item.size()!=0){
											for(int m =0;m<list_item.size();m++){
												listInv = new IV_Resp_ARInvoiceSubBean();
												qty = list_item.get(m).getQty();
												price = list_item.get(m).getPrice();


												if(qIdStatus.getTaxType()==0){
													itemAmount = qty*price;
													netAmount = itemAmount;
												}
												if(qIdStatus.getTaxType()==1){
													itemAmount = qty*price;
													netAmount = (itemAmount*100)/107;
												}
												if(qIdStatus.getTaxType()==2){
													itemAmount = qty*price;
													netAmount = itemAmount;
												}
												
												System.out.println(list_item.get(m).getItemCode());
												listInv.setItemCode(list_item.get(m).getItemCode());
												listInv.setItemName(list_item.get(m).getItemName());
												listInv.setBarCode(list_item.get(m).getBarCode());
												listInv.setQty(list_item.get(m).getQty());
												listInv.setPrice(list_item.get(m).getPrice());
												listInv.setUnitCode(list_item.get(m).getUnitCode());
												listInv.setPackingRate1(list_item.get(m).getPackingRate1());
												listInv.setDiscountAmount(0);
												listInv.setWhCode(list_item.get(m).getWhCode());
												listInv.setShelfCode(list_item.get(m).getShelfCode());
												listInv.setMachineCode("");
												listInv.setMachineNo("");
												listInv.setShiftNo(0);
												listInv.setShiftCode("");											
												listInv.setAmount(itemAmount);
												listInv.setNetAmount(netAmount);
												listInv.setHomeAmount(netAmount);
												listInv.setSumOfCost(list_item.get(m).getSumOfCost());
												listInv.setSaleCode(list_item.get(m).getSaleCode());
												sub.add(listInv);
											}
										}

										bill.setBillHeader(header);
										bill.setBillSub(sub);


										System.out.println("SaleCodeBill = "+bill.getBillHeader().getSaleCode());
										
										
										vQuery = "exec dbo.USP_API_InsertArInvoice "
										+" '"+bill.getBillHeader().getDocNo()+"',"
										+" "+bill.getBillHeader().getTaxType()+","+bill.getBillHeader().getBillType()+",'"+bill.getBillHeader().getArCode()+"','"+bill.getBillHeader().getArName()+"',"
										+" '"+bill.getBillHeader().getArAddress()+"','"+bill.getBillHeader().getCashierCode()+"','"+bill.getBillHeader().getMachineNo()+"',"
										+" '"+bill.getBillHeader().getMachineCode()+"',"+bill.getBillHeader().getPosStatus()+","
										+" '"+bill.getBillHeader().getCreditType()+"','"+bill.getBillHeader().getCreditNo()+"','"+bill.getBillHeader().getConfirmNo()+"',"
										+" '"+bill.getBillHeader().getChargeWord()+"',"+bill.getBillHeader().getCreditBaseAmount()+","+bill.getBillHeader().getChargeAmount()+","
										+" "+bill.getBillHeader().getCoupongAmount()+","+bill.getBillHeader().getChangeAmount()+","
										+" '"+bill.getBillHeader().getSaleCode()+"',"
										+" '"+bill.getBillHeader().getRefDocNo()+"',"+bill.getBillHeader().getSumOfItemAmount()+",'"+bill.getBillHeader().getDiscountWord()+"',"
										+" "+bill.getBillHeader().getDiscountAmount()+","+bill.getBillHeader().getAfterDiscount()+","+bill.getBillHeader().getBeforeTaxAmount()+","
										+" "+bill.getBillHeader().getTaxAmount()+","+bill.getBillHeader().getTotalAmount()+","
										+" "+bill.getBillHeader().getSumCashAmount()+","+bill.getBillHeader().getSumChqAmount()+","
										+" "+bill.getBillHeader().getSumCreditAmount()+","+bill.getBillHeader().getSumBankAmount()+","
										+" "+bill.getBillHeader().getSumOfDeposit1()+","+bill.getBillHeader().getSumOfDeposit2()+","
										+" "+bill.getBillHeader().getNetDebtAmount()+","
										+" "+bill.getBillHeader().getIsConditionSend()+",'"+bill.getBillHeader().getSoRefNo()+"',"
										+" '"+userCode.getEmployeeCode()+"','"+bill.getBillHeader().getRecurName()+"'";

//										vQuery = "set dateformat dmy  insert into dbo.BCARInvoice(docNo,docDate,taxNo,taxType,arCode,arName,arAddress,cashierCode,"
//												+"machineNo,machineCode,posStatus,billTime,creditType,creditNo,cofirmNo,chargeWord,creditBaseAmount,"
//												+"chargeAmount,grandTotal,coupongAmount,changeAmount,departCode,creditDay,dueDate,saleCode,taxRate,"
//												+"isConfirm,myDescription,billType,billGroup,refDocNo,sumOfItemAmount,discountWord,discountAmount,"
//												+"afterDiscount,beforeTaxAmount,taxAmount,totalAmount,zeroTaxAmount,exceptTaxAmount,sumCashAmount,"
//												+"sumChqAmount,sumCreditAmount,sumBankAmount,depositIncTax,sumOfDeposit1,sumOfDeposit2,sumOfWTax,"
//												+"netDebtAmount,homeAmount,otherIncome,otherExpense,excessAmount1,excessAmount2,billBalance,exchangeRate,"
//												+"isCancel,isCompleteSave,isPostGL,payBillStatus,allocateCode,projectCode,creatorCode,isConditionSend,"
//												+"payBillAmount,sORefNo,shiftCode,createdatetime) values( "
//												+" '"+bill.getBillHeader().getDocNo()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getTaxNo()+"',"
//												+" "+bill.getBillHeader().getTaxType()+",'"+bill.getBillHeader().getArCode()+"','"+bill.getBillHeader().getArName()+"',"
//												+" '"+bill.getBillHeader().getArAddress()+"','"+bill.getBillHeader().getCashierCode()+"','"+bill.getBillHeader().getMachineNo()+"',"
//												+" '"+bill.getBillHeader().getMachineCode()+"',"+bill.getBillHeader().getPosStatus()+","+"cast(datepart(hour,GETDATE()) as varchar(2))+':'+ cast(datepart(minute,GETDATE())as varchar(2))"+","
//												+" '"+bill.getBillHeader().getCreditType()+"','"+bill.getBillHeader().getCreditNo()+"','"+bill.getBillHeader().getConfirmNo()+"',"
//												+" '"+bill.getBillHeader().getChargeWord()+"',"+bill.getBillHeader().getCreditBaseAmount()+","+bill.getBillHeader().getChargeAmount()+","
//												+" "+bill.getBillHeader().getGrandTotal()+","+bill.getBillHeader().getCoupongAmount()+","+bill.getBillHeader().getChangeAmount()+","
//												+" '"+bill.getBillHeader().getDepartCode()+"',"+bill.getBillHeader().getCreditDay()+",'"+bill.getBillHeader().getDueDate()+"',"
//												+" '"+bill.getBillHeader().getSaleCode()+"',"+bill.getBillHeader().getTaxRate()+","+bill.getBillHeader().getIsConfirm()+","
//												+" '"+bill.getBillHeader().getMyDescription()+"',"+bill.getBillHeader().getBillType()+",'"+bill.getBillHeader().getBillGroup()+"',"
//												+" '"+bill.getBillHeader().getRefDocNo()+"',"+bill.getBillHeader().getSumOfItemAmount()+",'"+bill.getBillHeader().getDiscountWord()+"',"
//												+" "+bill.getBillHeader().getDiscountAmount()+","+bill.getBillHeader().getAfterDiscount()+","+bill.getBillHeader().getBeforeTaxAmount()+","
//												+" "+bill.getBillHeader().getTaxAmount()+","+bill.getBillHeader().getTotalAmount()+","+bill.getBillHeader().getZeroTaxAmount()+","
//												+" "+bill.getBillHeader().getExceptTaxAmount()+","+bill.getBillHeader().getSumCashAmount()+","+bill.getBillHeader().getSumChqAmount()+","
//												+" "+bill.getBillHeader().getSumCreditAmount()+","+bill.getBillHeader().getSumBankAmount()+","+bill.getBillHeader().getDepositIncTax()+","
//												+" "+bill.getBillHeader().getSumOfDeposit1()+","+bill.getBillHeader().getSumOfDeposit2()+","+bill.getBillHeader().getSumOfWTax()+","
//												+" "+bill.getBillHeader().getNetDebtAmount()+","+bill.getBillHeader().getHomeAmount()+","+bill.getBillHeader().getOtherIncome()+","
//												+" "+bill.getBillHeader().getOtherExpense()+","+bill.getBillHeader().getExcessAmount1()+","+bill.getBillHeader().getExcessAmount2()+","
//												+" "+bill.getBillHeader().getBillBalance()+","+bill.getBillHeader().getExchangeRate()+","+bill.getBillHeader().getIsCancel()+","
//												+" "+bill.getBillHeader().getIsCompleteSave()+","+bill.getBillHeader().getIsPostGL()+","+bill.getBillHeader().getPayBillAmount()+","
//												+" '"+bill.getBillHeader().getAllocateCode()+"','"+bill.getBillHeader().getProjectCode()+"','"+userCode.getEmployeeCode()+"',"
//												+" "+bill.getBillHeader().getIsConditionSend()+","+bill.getBillHeader().getPayBillAmount()+",'"+bill.getBillHeader().getSoRefNo()+"','"
//												+" "+bill.getBillHeader().getShiftCode()+"',getdate() "
//												+" )";
										System.out.println("SaleOrder Insert ="+vQuery);
										System.out.println("Server = "+db.getServerName());
										System.out.println("Database = "+db.getDbName());
										
										isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQuery);


										System.out.println("InvoiceSub :"+sub.size());

										for(int i=0;i<sub.size();i++){

//											vQuerySub=	"set dateformat dmy  insert into dbo.BCARInvoiceSub(docNo,taxNo,taxType,itemCode,docDate,arCode,departCode,"
//													+"saleCode,myDescription,itemName,whCode,shelfCode,cnQty,qty,price,discountWord,"
//													+"discountAmount,amount,netAmount,homeAmount,sumOfCost,balanceAmount,unitCode,"
//													+"soRefNo,poRefNo,stockType,lineNumber,refLineNumber,isCancel,allocateCode,projectCode,"
//													+"exchangeRate,barCode,machineNo,machineCode,billTime,cashierCode,shiftNo,posStatus,"+
//													"isConditionSend,taxRate,packingRate1) "
//													+" values( '"+bill.getBillHeader().getDocNo()+"','"+bill.getBillHeader().getTaxNo()+"',"+bill.getBillHeader().getTaxType()+","
//													+" '"+bill.getBillSub().get(i).getItemCode()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getArCode()+"',"
//													+" '"+bill.getBillHeader().getDepartCode()+"','"+bill.getBillSub().get(i).getSaleCode()+"','"+bill.getBillSub().get(i).getMyDescription()+"',"
//													+" '"+bill.getBillSub().get(i).getItemName()+"','"+bill.getBillSub().get(i).getWhCode()+"','"+bill.getBillSub().get(i).getShelfCode()+"',"
//													+" "+bill.getBillSub().get(i).getQty()+","+bill.getBillSub().get(i).getQty()+","+bill.getBillSub().get(i).getPrice()+","
//													+" '"+bill.getBillSub().get(i).getDiscountWord()+"',"+bill.getBillSub().get(i).getDiscountAmount()+","+bill.getBillSub().get(i).getAmount()+","
//													+" "+bill.getBillSub().get(i).getNetAmount()+","+bill.getBillSub().get(i).getHomeAmount()+","+bill.getBillSub().get(i).getSumOfCost()+","
//													+" "+bill.getBillSub().get(i).getBalanceAmount()+",'"+bill.getBillSub().get(i).getUnitCode()+"','"+bill.getBillSub().get(i).getSoRefNo()+"',"
//													+" '"+bill.getBillSub().get(i).getPoRefNo()+"',"+bill.getBillSub().get(i).getStockType()+","+i+",0,"+bill.getBillSub().get(i).getIsCancel()+","
//													+" '"+bill.getBillSub().get(i).getAllocateCode()+"','"+bill.getBillSub().get(i).getProjectCode()+"',"+bill.getBillSub().get(i).getExchangeRate()+","
//													+" '"+bill.getBillSub().get(i).getBarCode()+"','"+bill.getBillSub().get(i).getMachineNo()+"','"+bill.getBillSub().get(i).getMachineCode()+"',"
//													+" "+"cast(datepart(hour,GETDATE()) as varchar(2))+':'+ cast(datepart(minute,GETDATE())as varchar(2))"+",'"+bill.getBillSub().get(i).getCashierCode()+"',"
//													+" '"+bill.getBillSub().get(i).getShiftNo()+"',"+bill.getBillHeader().getPosStatus()+","+bill.getBillHeader().getIsConditionSend()+","
//													+" "+bill.getBillHeader().getTaxRate()+","+bill.getBillSub().get(i).getPackingRate1()
//													+")";
											
											vQuerySub=	"exec dbo.USP_API_InsertArInvoiceSub "
													+" '"+bill.getBillHeader().getDocNo()+"','"+bill.getBillSub().get(i).getItemCode()+"',"
													+" '"+bill.getBillSub().get(i).getSaleCode()+"',"
													+" '"+bill.getBillSub().get(i).getItemName()+"','"+bill.getBillSub().get(i).getWhCode()+"','"+bill.getBillSub().get(i).getShelfCode()+"',"
													+"  "+bill.getBillSub().get(i).getQty()+","+bill.getBillSub().get(i).getPrice()+","
													+"  "+bill.getBillSub().get(i).getAmount()+","
													+"  "+bill.getBillSub().get(i).getNetAmount()+","+bill.getBillSub().get(i).getSumOfCost()+","
													+"  '"+bill.getBillSub().get(i).getUnitCode()+"',"+i+","
													+"  '"+bill.getBillSub().get(i).getBarCode()+"',"
													+"  "+bill.getBillSub().get(i).getPackingRate1()+"";
											System.out.println(vQuerySub);
											isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQuerySub);

										}

										if(crdCard.size()!=0 && crdCard.get(0).getCardNo()!=""){
											for(int a=0;a<crdCard.size();a++){

												vQueryCreditCard = "set dateformat dmy insert into dbo.BCCreditCard(BankCode,CreditCardNo,DocNo,ArCode,ReceiveDate,DueDate,Status,SaveFrom,Amount,MyDescription,ExchangeRate,CreditType,ConfirmNo,ChargeAmount,CreatorCode,CreateDateTime) values( "
														+" '"+crdCard.get(a).getBankCode()+"','"+crdCard.get(a).getCardNo()+"','"+bill.getBillHeader().getDocNo()+"','"+bill.getBillHeader().getArCode()+"',"
														+" cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),"+"0,1"+","+crdCard.get(a).getAmount()+",'"+"ขายหน้าร้าน"+"',"
														+" "+"1.0000000000"+",'"+crdCard.get(a).getCreditType()+"','"+crdCard.get(a).getConfirmNo()+"',"+crdCard.get(a).getChargeAmount()+",'"+userCode.getEmployeeCode()+"',getdate()"
														+" )";
												System.out.println(vQueryCreditCard);
												isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQueryCreditCard);
											}
										}

										if(listCoupong.size()!=0 && listCoupong.get(0).getCouponCode()!= ""){
											for(int b=0;b<listCoupong.size();b++){
												vQueryCoupong="set dateformat dmy insert into dbo.bccouponreceive(COUPONCODE,COUPONTYPE,COUPONNO,TOCOUPONNO,COUPONCOUNT,DOCNO,BOOK,COUPONVAL,LINENUMBER,CREATORCODE,CREATEDATETIME) values( "
														+" '"+listCoupong.get(b).getCouponCode()+"',1,'"+listCoupong.get(b).getCouponCode()+"','"+listCoupong.get(b).getCouponCode()+"',1,'"+bill.getBillHeader().getDocNo()+"',"
														+" '"+listCoupong.get(b).getCouponCode()+"',"+listCoupong.get(b).getAmount()+","+b+",'"+userCode.getEmployeeCode()+"',getdate()"
														+" )";
												System.out.println(vQueryCoupong);
												isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQueryCoupong);
											}
										}
										
										String vQueryDeposit;
										String vQueryCalcDep;
										if(listDeposit.size()!=0 && listDeposit.get(0).getDeposit_id()!=""){
											for(int c=0;c<listDeposit.size();c++){

												vQueryDeposit = "exec dbo.USP_API_InsertArDepositUse "
														+" '"+bill.getBillHeader().getDocNo()+"','"+listDeposit.get(c).getDeposit_id()+"',"
														+" "+listDeposit.get(c).getAmount()+","+listDeposit.get(c).getAmount()+","
														+" "+listDeposit.get(c).getAmount();
												System.out.println(vQueryDeposit);
												isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQueryDeposit);
												
												vQueryCalcDep = "exec dbo.USP_API_CalcArDeposit '"+bill.getBillHeader().getArCode()+"','"+listDeposit.get(c).getDeposit_id()+"'";
												isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(), vQueryCalcDep);
												
												//คำนวณ การตัดมัดจำ======================================================================
														//==================================================================================
											}
										}
										
										String vQueryRecMoney;
										
										if(qIdStatus.getDoctype()==1 && qIdStatus.getBilltype()==0){
											if(reqsBill.getCash()!=0){
												vQueryRecMoney="set dateformat dmy insert into dbo.bcrecmoney(docno,docdate,arcode,exchangerate,paymenttype,savefrom,payamount,salecode,mydescription) "
														+ " values ( '"+bill.getBillHeader().getDocNo()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getArCode()+"',1.0000000000,0,1,"+reqsBill.getCash()+",'"+bill.getBillHeader().getSaleCode()+"','ขายเงินสด'"+" )";
												System.out.println(vQueryRecMoney);
												isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(),vQueryRecMoney);
											}
											if(sumCreditAmount!=0){
												
												if(crdCard.size()!=0 && crdCard.get(0).getCardNo()!=""){
													for(int d=0;d<crdCard.size();d++){

														
														vQueryRecMoney="set dateformat dmy insert into dbo.bcrecmoney(docno,docdate,arcode,exchangerate,paymenttype,savefrom,payamount,chqtotalamount,credittype,chargeamount,confirmno,linenumber,refno,bankcode,salecode,mydescription,refdate) "
																+ " values ( '"+bill.getBillHeader().getDocNo()+"',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime),'"+bill.getBillHeader().getArCode()+"',1.0000000000,1,1,"+crdCard.get(d).getAmount()+","+crdCard.get(d).getAmount()+",'"+crdCard.get(d).getCreditType()
																+"',"+crdCard.get(d).getChargeAmount()+",'"+crdCard.get(d).getConfirmNo()+"',"+d+",'"+crdCard.get(d).getCardNo()+"','"+crdCard.get(d).getBankCode()+"','"+bill.getBillHeader().getSaleCode()+"','ขายเงินสด',cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) )";
														
														System.out.println(vQueryRecMoney);
														isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(),vQueryRecMoney);
													}
												}
											}
										}

										vQuery="update Queue set status = 3,invoiceNo = '"+bill.getBillHeader().getDocNo()+"' where docNo ='"+que.getDocNo()+"'";
										isSuccess= excecute.execute(mySqlName,vQuery);

										IV_Reqs_PrintSlipBean req = new IV_Reqs_PrintSlipBean();
										req.setAccess_token(reqsBill.getAccess_token());
										req.setType(0);
										req.setInvoiceNo(bill.getBillHeader().getDocNo());
										req.setArCode(bill.getBillHeader().getArCode());

										//ปรับสถานะ ใบสั่งขาย======================================================================
										//==================================================================================
										vQuerySaleOrder="exec dbo.USP_API_UpdateSaleOrderStatus '"+bill.getBillHeader().getDocNo()+"','"+bill.getBillHeader().getSoRefNo()+"','"+reqsBill.getAr_code()+"'";
										System.out.println(vQuerySaleOrder);
										isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(),vQuerySaleOrder);
										
										String vQueryAr ;
										if (bill.getBillHeader().getBillType()==1){
											vQueryAr = "exec dbo.usp_api_updatecustdebt '"+bill.getBillHeader().getArCode()+"',"+checkRemainAmount;
											System.out.println(vQueryAr);
											isSuccess = npExec.executeSql(db.getServerName(),db.getDbName(),vQueryAr);	
										}

										
										//======================================================================
												//==================================================================================
										String linkFormBill = "";
										linkFormBill = "http://drivethru.madebydome.com/resources/app/class/v1/print.php?invoice_no=" +bill.getBillHeader().getDocNo();
										
										
										if (isSuccess ==true){
											
											IV_Resp_PrintSlipBean copyInv = new IV_Resp_PrintSlipBean();

											copyInv = data.copySaleOrderHTML(db, req);

											System.out.println("ItemName:"+copyInv.getItem().get(0).getItemName());

											HttpClient httpClient = HttpClientBuilder.create().build(); 

											try {
												Gson gson = new Gson();
												String json = gson.toJson(copyInv); 

												System.out.println(json.charAt(0));

												HttpPost request = new HttpPost("http://api.nopadol.com/drivethru/copy/index.php");
												StringEntity params =new StringEntity(json,HTTP.UTF_8);
												request.addHeader("content-type", "application/json; charset=utf-8");
												request.setEntity(params);
												HttpResponse response = httpClient.execute(request);

												System.out.println("CompanyName:"+copyInv.getCompanyName());

											}catch (Exception ex) {

											} finally {
												httpClient.getConnectionManager().shutdown(); //Deprecated
											}
										}
										respBill.setTotal_amount(totalAmount);
										if(qIdStatus.getDoctype() == 1 && qIdStatus.getBilltype()==0){
											respBill.setIs_print_cash_form(1);
											respBill.setIs_print_credit_form(0);
										}else{
											respBill.setIs_print_cash_form(0);
											respBill.setIs_print_credit_form(1);
										}

										respBill.setIs_print_short_form(0);
										
										invoice.setRemain_amount(sumRemain);
										invoice.setInvoice_no(bill.getBillHeader().getDocNo());
										invoice.setCash_amount(reqsBill.getCash());
										invoice.setCredit_amount(sumCreditAmount);
										invoice.setChange_amount(checkChangeAmount);
										invoice.setCoupong_amount(sumCouponAmount);
										invoice.setDeposit_amount(sumDepositAmount);

										respBill.setInvoice(invoice);

										respBill.setSuccess(true);
										respBill.setError(false);
										respBill.setMessage("");


									}catch(SQLException e){
										e.printStackTrace();
										System.out.println("Remain No");
										
										respBill.setTotal_amount(totalAmount);
										respBill.setIs_print_cash_form(0);
										respBill.setIs_print_credit_form(0);
										respBill.setIs_print_short_form(0);
										
										invoice.setRemain_amount(sumRemain);
										invoice.setInvoice_no("Can not save bill");
										invoice.setCash_amount(reqsBill.getCash());
										invoice.setCredit_amount(sumCreditAmount);
										invoice.setChange_amount(checkChangeAmount);
										invoice.setCoupong_amount(sumCouponAmount);
										invoice.setDeposit_amount(sumDepositAmount);

										respBill.setInvoice(invoice);

										respBill.setSuccess(false);
										respBill.setError(true);
										respBill.setMessage(e.getMessage());

									}finally{
										ds.close();
										sqlDS.close();
									}

								}else{
									respBill.setTotal_amount(totalAmount);
									respBill.setIs_print_cash_form(0);
									respBill.setIs_print_credit_form(0);
									respBill.setIs_print_short_form(0);
									
									invoice.setRemain_amount(sumRemain);
									invoice.setInvoice_no("Can not save bill");
									invoice.setCash_amount(reqsBill.getCash());
									invoice.setCredit_amount(sumCreditAmount);
									invoice.setChange_amount(checkChangeAmount);
									invoice.setCoupong_amount(sumCouponAmount);
									invoice.setDeposit_amount(sumDepositAmount);

									respBill.setInvoice(invoice);

									respBill.setSuccess(false);
									respBill.setError(true);
									respBill.setMessage("มียอดค้างชำระ กรุณาตรวจสอบ");
									//respBill.setMessage("This payment have remain <> 0");

								}

							}else{
								respBill.setTotal_amount(totalAmount);
								respBill.setIs_print_cash_form(0);
								respBill.setIs_print_credit_form(0);
								respBill.setIs_print_short_form(0);
								
								invoice.setRemain_amount(sumRemain);
								invoice.setInvoice_no("Can not save bill");
								invoice.setCash_amount(reqsBill.getCash());
								invoice.setCredit_amount(sumCreditAmount);
								invoice.setChange_amount(checkChangeAmount);
								invoice.setCoupong_amount(sumCouponAmount);
								invoice.setDeposit_amount(sumDepositAmount);

								respBill.setInvoice(invoice);

								respBill.setSuccess(false);
								respBill.setError(true);
								respBill.setMessage(verifyDeposit.getProcessDesc());

							}

						}else{
							respBill.setTotal_amount(totalAmount);
							respBill.setIs_print_cash_form(0);
							respBill.setIs_print_credit_form(0);
							respBill.setIs_print_short_form(0);
							
							invoice.setRemain_amount(sumRemain);
							invoice.setInvoice_no("Can not save bill");
							invoice.setCash_amount(reqsBill.getCash());
							invoice.setCredit_amount(sumCreditAmount);
							invoice.setChange_amount(checkChangeAmount);
							invoice.setCoupong_amount(sumCouponAmount);
							invoice.setDeposit_amount(sumDepositAmount);

							respBill.setInvoice(invoice);

							respBill.setSuccess(false);
							respBill.setError(true);
							respBill.setMessage(verifyCoupong.getProcessDesc());

						}
					}else{
						respBill.setTotal_amount(totalAmount);
						respBill.setIs_print_cash_form(0);
						respBill.setIs_print_credit_form(0);
						respBill.setIs_print_short_form(0);
						
						invoice.setRemain_amount(sumRemain);
						invoice.setInvoice_no("Can not save bill");
						invoice.setCash_amount(reqsBill.getCash());
						invoice.setCredit_amount(sumCreditAmount);
						invoice.setChange_amount(checkChangeAmount);
						invoice.setCoupong_amount(sumCouponAmount);
						invoice.setDeposit_amount(sumDepositAmount);

						respBill.setInvoice(invoice);

						respBill.setSuccess(false);
						respBill.setError(true);
						respBill.setMessage(verifyCoupong.getProcessDesc());

					}
				}
			}
		}else{

			respBill.setTotal_amount(totalAmount);
			respBill.setIs_print_cash_form(0);
			respBill.setIs_print_credit_form(0);
			respBill.setIs_print_short_form(0);
			
			invoice.setRemain_amount(sumRemain);
			invoice.setInvoice_no("Can not save bill");
			invoice.setCash_amount(reqsBill.getCash());
			invoice.setCredit_amount(sumCreditAmount);
			invoice.setChange_amount(checkChangeAmount);
			invoice.setCoupong_amount(sumCouponAmount);
			invoice.setDeposit_amount(sumDepositAmount);

			respBill.setInvoice(invoice);

			respBill.setSuccess(false);
			respBill.setError(true);
			respBill.setMessage("This queue is bill aready or status queue is not confirm to bill");

		}
		System.out.println(respBill.getMessage());
		return respBill;
	}//1

	SO_Res_SaleOrderInvoiceBean saleInvoice = new SO_Res_SaleOrderInvoiceBean();
	List<SO_Res_ListItemInvoiceBean> list_item_inv = new ArrayList<SO_Res_ListItemInvoiceBean>();
	
	public SO_Res_SaleOrderInvoiceBean SaleOrderInvoiceDetails(DT_User_LoginBranchBean db,CT_Req_SearchInvoiceBean req){
		String vQuery;
		String vQuerySub;
		
		try {
			Statement st = conn.getSqlStatement(db.getServerName(), db.getDbName());
			vQuery = "exec dbo.SP_INV_00002 '"+req.getInvoice_no()+"'";
			ResultSet rs = st.executeQuery(vQuery);
			System.out.println(vQuery);
			
			SO_Res_ListItemInvoiceBean evt_item;
			list_item_inv.clear();
			while(rs.next()){
				
				saleInvoice.setDoc_no(rs.getString("docno"));
				saleInvoice.setDoc_date(rs.getString("docdate"));
				saleInvoice.setAr_code(rs.getString("arcode"));
				saleInvoice.setAr_name(rs.getString("name1"));
				saleInvoice.setAr_address(rs.getString("billaddress"));
				saleInvoice.setAr_telephone(rs.getString("telephone"));
				saleInvoice.setAr_fax(rs.getString("fax"));
				saleInvoice.setSale_code(rs.getString("salecode"));
				saleInvoice.setSale_name(rs.getString("name"));
				saleInvoice.setSo_refno(rs.getString("sorefno"));
				saleInvoice.setCreator_code(rs.getString("creatorcode"));
				saleInvoice.setCreator_name(rs.getString("creatorcode"));
				saleInvoice.setTax_no(rs.getString("taxno"));
				saleInvoice.setTax_rate(rs.getInt("taxrate"));
				saleInvoice.setTax_type(rs.getInt("taxtype"));
				saleInvoice.setMy_description(rs.getString("mydescription"));
				saleInvoice.setSum_item_amount(rs.getDouble("sumofitemamount"));
				saleInvoice.setDiscount_amount(rs.getDouble("discountamount"));
				saleInvoice.setAfter_discount_amount(rs.getDouble("afterdiscount"));
				saleInvoice.setBefore_tax_amount(rs.getDouble("beforetaxamount"));
				saleInvoice.setTax_amount(rs.getDouble("taxamount"));
				saleInvoice.setTotal_amount(rs.getDouble("totalamount"));
				saleInvoice.setIs_condition_send(rs.getInt("isconditionsend"));
				saleInvoice.setCredit_day(rs.getInt("creditday"));
				saleInvoice.setContact_code(rs.getString("contactcode"));
				saleInvoice.setContact_name(rs.getString("contactname"));
				saleInvoice.setApprove_code(rs.getString("approvecode"));
				saleInvoice.setApprove_name(rs.getString("approvecode"));
				saleInvoice.setSo_doc_date(rs.getString("sodocdate"));
				saleInvoice.setPoint(rs.getDouble("sumofmark1"));
				saleInvoice.setForm_type(rs.getString("billtypename"));   
				
				evt_item = new SO_Res_ListItemInvoiceBean();
				evt_item.setItem_code(rs.getString("itemcode"));
				System.out.println("ItemCode = "+rs.getString("itemcode"));
				evt_item.setItem_name(rs.getString("itemname"));
				evt_item.setWh_code(rs.getString("whcode"));
				evt_item.setShelf_code(rs.getString("shelfcode"));
				evt_item.setQty(rs.getDouble("qty"));
				evt_item.setPrice(rs.getDouble("price"));
				evt_item.setUnit_code(rs.getString("unitcode"));
				evt_item.setDiscount_word(rs.getString("discountamount1"));
				evt_item.setDiscount_amount_sub(rs.getDouble("discountamount1"));
				evt_item.setNet_amount(rs.getDouble("netamount"));
				
				evt_item.setItem_description(rs.getString("descriptionline"));
				evt_item.setLine_number(rs.getInt("linenumber"));
				list_item_inv.add(evt_item);
			}
			saleInvoice.setItems(list_item_inv);
				
			saleInvoice.setError(false);
			saleInvoice.setSuccess(true);
			saleInvoice.setMessage("");
		rs.close();
		st.close();
		} catch (SQLException e) {
			saleInvoice.setError(true);
			saleInvoice.setSuccess(false);
			saleInvoice.setMessage(e.getMessage());
			
		}finally{
			conn.close();
		}
		
		return saleInvoice;
	}
	
	
	List<SO_Res_ListCarBrandBean> brand = new ArrayList<SO_Res_ListCarBrandBean>();
	SO_Res_CarBrandBean carBrand = new SO_Res_CarBrandBean();
	public SO_Res_CarBrandBean searchCarBrand(String dbName){
		String vQuery;
		int countrow=0;
		
		try{
			Statement stmt = ds.getStatement(dbName);
			SO_Res_ListCarBrandBean evt;
			SO_Res_ListCarBrandBean evt1;
			
			vQuery = "call USP_DT_SearchCarBrand ('')";
			System.out.println(vQuery);
			ResultSet rs = stmt.executeQuery(vQuery);
			
			brand.clear();
			while(rs.next()){
				countrow++;
				
				evt = new SO_Res_ListCarBrandBean();
				evt.setName(rs.getString("carBrand"));
				brand.add(evt);
			}
			
			carBrand.setSuccess(true);
			carBrand.setError(false);
			carBrand.setMessage("");
			
			carBrand.setCar_brand(brand);
			
			if (countrow==0){
				evt1 = new SO_Res_ListCarBrandBean();
				brand.add(evt1);
				
				carBrand.setSuccess(false);
				carBrand.setError(true);
				carBrand.setMessage("No List Car Brand");
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
	
}
