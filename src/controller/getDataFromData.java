package controller;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;

import bean.CK_Resp_AllSummaryBean;
import bean.CM_Resp_MemberListBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.IV_Reqs_CouponBean;
import bean.IV_Reqs_CreditCardBean;
import bean.IV_Reqs_InvoiceDataBean;
import bean.IV_Reqs_PrintSlipBean;
import bean.IV_Resp_ARInvoiceSubBean;
import bean.IV_Resp_PrintSlipBean;
import bean.IV_Resp_PrintSlipSubBean;
import bean.IV_Resp_ValidateCreditCardBean;
import bean.LoginBean;
import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.RP_Resp_QueueDetails;
import bean.User_Resp_CheckDataAccessTokenBean;
import bean.request.CT_Req_SearchCompanyDataBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.IV_Req_DepositBean;
import bean.request.SO_Req_CheckOutManageProductBean;
import bean.request.SO_Req_EditSaleOrderBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_ListEditItemSaleOrderBean;
import bean.request.SO_Req_ListItemToQueueBean;
import bean.request.SO_Req_PickingManageProductBean;
import bean.request.SO_Req_VerifySaleOrderBean;
import bean.request.SO_Reqs_CouponBean;
import bean.request.SO_Reqs_CreditCardBean;
import bean.request.SO_Res_VerifySaleOrderBean;
import bean.response.CT_Res_CompanyDataBean;
import bean.response.CT_Resp_ResponseBean;
import bean.response.DT_Res_ListCompanyBean;
import bean.response.DT_Res_ListZoneBean;
import bean.response.SO_Res_CheckDataSOBean;
import bean.response.SO_Res_CheckQueueItemBean;
import bean.response.SO_Res_ItemSaleOrderBean;
import bean.response.SO_Res_ListARDepositBean;

import connect.NPSQLConn;
import connect.QueueConnect;
import connect.SQLConn;
import connect.bCryptPassword;
import sun.misc.BASE64Encoder;

public class getDataFromData {
	String vQuery ;
	String vQuerySub ;
	String vDocNo;

	String vGenNewDocNo;
	int getID;
	int getInspectID;
	
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private final SQLConn sqlDS = SQLConn.INSTANCE;
	private final QueueConnect dsTK = QueueConnect.INSTANCE;
	private final NPSQLConn npDS = NPSQLConn.INSTANCE;

	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	PK_Resp_GetItemBarcodeBean resItem = new PK_Resp_GetItemBarcodeBean();
	PK_Resp_GetDataQueue resQue = new PK_Resp_GetDataQueue();
	CK_Resp_AllSummaryBean allSum = new CK_Resp_AllSummaryBean();
	
	CM_Resp_MemberListBean ar = new CM_Resp_MemberListBean();
	
	IV_Resp_ARInvoiceSubBean queItem = new IV_Resp_ARInvoiceSubBean();
	List<IV_Resp_ARInvoiceSubBean> itemlist = new ArrayList<IV_Resp_ARInvoiceSubBean>();
	CT_Resp_ResponseBean creditcardRes = new CT_Resp_ResponseBean();
	CT_Resp_ResponseBean coupongRes = new CT_Resp_ResponseBean();
	CT_Resp_ResponseBean depositRes = new CT_Resp_ResponseBean();
	CT_Resp_ResponseBean itemRes = new CT_Resp_ResponseBean();
	
	DT_User_LoginBranchBean connData = new DT_User_LoginBranchBean();
	
	PK_Resp_SaleCodeDetails sale = new PK_Resp_SaleCodeDetails();
	
	LoginBean userCode = new LoginBean();
	
	User_Resp_CheckDataAccessTokenBean branch = new User_Resp_CheckDataAccessTokenBean();
	SO_Res_CheckDataSOBean SO = new SO_Res_CheckDataSOBean();
	
	CT_Res_CompanyDataBean company = new CT_Res_CompanyDataBean();
	
	SO_Res_VerifySaleOrderBean item_veryfy = new SO_Res_VerifySaleOrderBean();
	
	SO_Res_ItemSaleOrderBean sale_item = new SO_Res_ItemSaleOrderBean();
	
	bCryptPassword bcrypt = new bCryptPassword();
	
	IV_Resp_PrintSlipBean printInv = new IV_Resp_PrintSlipBean();
	List<IV_Resp_PrintSlipSubBean> listInv = new ArrayList<IV_Resp_PrintSlipSubBean>();
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	

	public PK_Resp_GetItemBarcodeBean searchItemCode(String barcode){

		try{
			//Statement st = sqlDS.getSqlStatement("nptest");
			Statement st = sqlDS.getSqlStatement("bcnp");
			
//			vQuery = "select a.itemcode,a.unitcode,rate,b.name1 as barcodename,isnull(d.expertcode,'') as expertcode, "+
//					" isnull(d.departmentcode,'') as departmentcode,isnull(d.department,'') as departname, "+
//					" isnull(d.categorycode,'') as categorycode,isnull(d.category,'') as category, "+
//					" isnull(e.salecode,'') as secCode,isnull(e.salename,'') as secName,isnull(b.averagecost,0) as averagecost "+
//					" from dbo.bcbarcodemaster a " +
//					" inner join dbo.bcitem  b on a.itemcode = b.code " +
//					" inner join dbo.bcstkpacking c on a.itemcode = c.itemcode and a.unitcode = c.unitcode " +
//					" left join nebula.npdb.dbo.tb_inc_itemextension d on b.code = d.itemcode" +
//					" left join nebula.npdb.dbo.tb_ic_sectiondepartment e on d.expertcode = e.catcode and year(getdate()) = e.atyear and month(getdate()) = e.atmonth and d.departmentcode = e.departmentcode " +
//					" where barcode = '"+barcode+"'";
			
			vQuery = "exec dbo.USP_DT_SearchBarCodeDetails '"+barcode+"'";
			
			System.out.println("Search BarCode "+ vQuery);

			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				resItem.setCode(rs.getString("itemcode"));
				resItem.setRate1(rs.getInt("rate"));
				resItem.setUnitCode(rs.getString("unitcode"));
				resItem.setItemName(rs.getString("barcodename"));
				resItem.setExpertCode(rs.getString("expertcode"));
				resItem.setDepartmentCode(rs.getString("departmentcode"));
				resItem.setDepartmentName(rs.getString("departname"));
				resItem.setCategoryCode(rs.getString("categorycode"));
				resItem.setCategoryName(rs.getString("category"));
				resItem.setSecCode(rs.getString("secCode"));
				resItem.setSecName(rs.getString("secName"));
				resItem.setAverageCost(rs.getDouble("averagecost"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlDS.close();
		}

		return resItem;
	}
	
	public PK_Resp_GetItemBarcodeBean searchItemCodeBranch(DT_User_LoginBranchBean db,String barcode){

		try{
			Statement st = sqlDS.getSqlStatement(db.getServerName(),db.getDbName());
			//Statement st = npDS.getSqlStatement("192.168.0.7", "bcnp");
			
//			vQuery = "select a.itemcode,a.unitcode,rate,b.name1 as barcodename,isnull(d.expertcode,'') as expertcode, "+
//					" isnull(d.departmentcode,'') as departmentcode,isnull(d.department,'') as departname, "+
//					" isnull(d.categorycode,'') as categorycode,isnull(d.category,'') as category, "+
//					" isnull(e.salecode,'') as secCode,isnull(e.salename,'') as secName,isnull(b.averagecost,0) as averagecost "+
//					" from dbo.bcbarcodemaster a " +
//					" inner join dbo.bcitem  b on a.itemcode = b.code " +
//					" inner join dbo.bcstkpacking c on a.itemcode = c.itemcode and a.unitcode = c.unitcode " +
//					" left join nebula.npdb.dbo.tb_inc_itemextension d on b.code = d.itemcode" +
//					" left join nebula.npdb.dbo.tb_ic_sectiondepartment e on d.expertcode = e.catcode and year(getdate()) = e.atyear and month(getdate()) = e.atmonth and d.departmentcode = e.departmentcode " +
//					" where barcode = '"+barcode+"'";
			
			vQuery = "exec dbo.USP_DT_SearchBarCodeDetails '"+barcode+"'";
			
			System.out.println("Search BarCode "+ vQuery);

			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				resItem.setCode(rs.getString("itemcode"));
				resItem.setRate1(rs.getInt("rate"));
				resItem.setUnitCode(rs.getString("unitcode"));
				resItem.setItemName(rs.getString("barcodename"));
				resItem.setExpertCode(rs.getString("expertcode"));
				resItem.setDepartmentCode(rs.getString("departmentcode"));
				resItem.setDepartmentName(rs.getString("departname"));
				resItem.setCategoryCode(rs.getString("categorycode"));
				resItem.setCategoryName(rs.getString("category"));
				resItem.setSecCode(rs.getString("secCode"));
				resItem.setSecName(rs.getString("secName"));
				resItem.setAverageCost(rs.getDouble("averagecost"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlDS.close();
		}

		return resItem;
	}
	
	public PK_Resp_GetDataQueue searchQueue(int qId){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(saleorder,'') as saleorder,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where qId = "+qId +" and docdate = CURDATE() limit 1";
			//vQuery = "select docno,iscancel,status,carLicence,salecode from Queue where qId = "+qId +" and docdate = '"+dateFormat.format(dateNow)+"' limit 1";
			System.out.println("searchQueue :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));
				resQue.setIsCancel(rs.getInt("iscancel"));
				resQue.setStatus(rs.getInt("status"));
				resQue.setCarLicense(rs.getString("carLicence"));
				resQue.setSaleCode(rs.getString("saleCode"));
				resQue.setOtp_password(rs.getString("otpcode"));
				resQue.setDelivery_type(rs.getInt("deliverytype"));
				resQue.setBilltype(rs.getInt("billtype"));
				resQue.setDoctype(rs.getInt("doctype"));
				resQue.setTaxType(rs.getInt("taxType"));
				resQue.setPickStatus(rs.getInt("pickStatus"));
				resQue.setSaleOrder(rs.getString("saleorder"));
			}
		    rs.close();
		    st.close();
		    
		    System.out.println("GetDocno :"+vQuery);
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return resQue;
	}
	
	public PK_Resp_GetDataQueue searchQueueBranch(String db,int qId){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			Statement st = ds.getStatement(db);
			
			vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where qId = "+qId +" and docdate = CURDATE() limit 1";
			//vQuery = "select docno,iscancel,status,carLicence,salecode from Queue where qId = "+qId +" and docdate = '"+dateFormat.format(dateNow)+"' limit 1";
			//System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));
				resQue.setIsCancel(rs.getInt("iscancel"));
				resQue.setStatus(rs.getInt("status"));
				resQue.setCarLicense(rs.getString("carLicence"));
				resQue.setSaleCode(rs.getString("saleCode"));
				resQue.setOtp_password(rs.getString("otpcode"));
				resQue.setDelivery_type(rs.getInt("deliverytype"));
				resQue.setBilltype(rs.getInt("billtype"));
				resQue.setDoctype(rs.getInt("doctype"));
				resQue.setTaxType(rs.getInt("taxType"));
				resQue.setPickStatus(rs.getInt("pickStatus"));
			}
		    rs.close();
		    st.close();
		    
		    System.out.println("GetDocno :"+vQuery);
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return resQue;
	}
	
	public PK_Resp_GetDataQueue searchSaleOrder(String docno){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where saleorder = '"+docno +"' and docdate = CURDATE() limit 1";
			//vQuery = "select docno,iscancel,status,carLicence,salecode from Queue where qId = "+qId +" and docdate = '"+dateFormat.format(dateNow)+"' limit 1";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));
				resQue.setIsCancel(rs.getInt("iscancel"));
				resQue.setStatus(rs.getInt("status"));
				resQue.setCarLicense(rs.getString("carLicence"));
				resQue.setSaleCode(rs.getString("saleCode"));
				resQue.setOtp_password(rs.getString("otpcode"));
				resQue.setDelivery_type(rs.getInt("deliverytype"));
				resQue.setBilltype(rs.getInt("billtype"));
				resQue.setDoctype(rs.getInt("doctype"));
				resQue.setTaxType(rs.getInt("taxType"));
				resQue.setPickStatus(rs.getInt("pickStatus"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return resQue;
	}
	
	public PK_Resp_GetDataQueue searchSaleOrderBranch(String db,String docno){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			Statement st = ds.getStatement(db);
			
			vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where saleorder = '"+docno +"' and docdate = CURDATE() limit 1";
			//vQuery = "select docno,iscancel,status,carLicence,salecode from Queue where qId = "+qId +" and docdate = '"+dateFormat.format(dateNow)+"' limit 1";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));
				resQue.setIsCancel(rs.getInt("iscancel"));
				resQue.setStatus(rs.getInt("status"));
				resQue.setCarLicense(rs.getString("carLicence"));
				resQue.setSaleCode(rs.getString("saleCode"));
				resQue.setOtp_password(rs.getString("otpcode"));
				resQue.setDelivery_type(rs.getInt("deliverytype"));
				resQue.setBilltype(rs.getInt("billtype"));
				resQue.setDoctype(rs.getInt("doctype"));
				resQue.setTaxType(rs.getInt("taxType"));
				resQue.setPickStatus(rs.getInt("pickStatus"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return resQue;
	}
	
	public PK_Resp_GetDataQueue searchQueueBranch(String db,int qId,String branchCode){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			Statement st = ds.getStatement(db);
			
			//vQuery = "select docno,iscancel,status,carLicence,salecode from QueueMaster where branchCode ='"+branchCode+"' and qId = "+qId +" and docdate = CURDATE() limit 1";
			vQuery = "call USP_DT_SearchQueueStatus ('"+branchCode+"',"+qId+")";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				resQue.setDocNo(rs.getString("docno"));
				resQue.setIsCancel(rs.getInt("iscancel"));
				resQue.setStatus(rs.getInt("status"));
				resQue.setCarLicense(rs.getString("carLicence"));
				resQue.setSaleCode(rs.getString("saleCode"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return resQue;
	}
	
	public PK_Resp_SaleCodeDetails searchTopSaleCode(int getQ){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		sale.setIsExist(0);
		sale.setSaleCode("");
		sale.setSaleName("");
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "call USP_DT_SelectTopSaleCode ("+getQ +")";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				sale.setIsExist(1);
				sale.setSaleCode(rs.getString("code"));
				sale.setSaleName(rs.getString("name"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			sale.setIsExist(0);
			sale.setSaleCode("");
			sale.setSaleName("");
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return sale;
	}
	
	public PK_Resp_SaleCodeDetails searchTopSaleCodeBranch(String branchCode,int getQ){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		sale.setIsExist(0);
		sale.setSaleCode("");
		sale.setSaleName("");
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "call USP_DT_SelectTopSaleCodeBranch ("+getQ +",'"+branch.getBranchCode()+"')";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				sale.setIsExist(1);
				sale.setSaleCode(rs.getString("code"));
				sale.setSaleName(rs.getString("name"));
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			sale.setIsExist(0);
			sale.setSaleCode("");
			sale.setSaleName("");
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return sale;
	}
	
	public CK_Resp_AllSummaryBean searchQueueSummary(int qId){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select sum(pickqty*price) as totalBeforeAmount,sum(checkoutqty*price) as totalAfterAmount  from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0";
			//vQuery = "select sum(pickqty*price) as totalBeforeAmount,sum(checkoutqty*price) as totalAfterAmount  from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = "+qId +" and a.docdate = '"+dateFormat.format(dateNow)+"' and a.iscancel = 0 and b.iscancel = 0";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				allSum.setTotalBeforePrice(rs.getDouble("totalBeforeAmount"));
				allSum.setTotalAfterPrice(rs.getDouble("totalAfterAmount"));
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return allSum;
	}
	
	public CK_Resp_AllSummaryBean searchQueueSummaryBranch(int qId,String branchCode){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			//vQuery = "select sum(pickqty*price) as totalBeforeAmount,sum(checkoutqty*price) as totalAfterAmount  from QueueMaster a inner join QueueSub b on a.branchCode = b.branchCode and a.qid = b.qid and a.docno = b.docno  where a.branchCode = '"+branchCode+"' and a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0";
			vQuery = "call USP_DT_SearchQueueSummary ('"+branchCode+"',"+qId +")";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				allSum.setTotalBeforePrice(rs.getDouble("totalBeforeAmount"));
				allSum.setTotalAfterPrice(rs.getDouble("totalAfterAmount"));
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return allSum;
	}
	
	public double searchQueueCheckOutAmount(int qId){
		double totalAmount = 0.0;
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select sum(checkoutqty*price) as totalamount from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			//vQuery = "select sum(checkoutqty*price) as totalamount from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = "+qId +" and a.docdate = '"+dateFormat.format(dateNow)+"' and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				totalAmount = rs.getDouble("totalamount");
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return totalAmount;
	}
	
	public double searchQueueCheckOutAmountBranch(int qId,String branchCode){
		double totalAmount = 0.0;
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			//vQuery = "select sum(checkoutqty*price) as totalamount from QueueMaster a inner join QueueSub b on a.qid = b.qid and a.docno = b.docno and a.branchCode = b.branchCode where a.branchCode = '"+branchCode+"' and a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			vQuery = "call USP_DT_SearchQueueCheckOutAmount ('"+branchCode+"',"+qId +")";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				totalAmount = rs.getDouble("totalamount");
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return totalAmount;
	}
	
	public List<IV_Resp_ARInvoiceSubBean> searchQueueCheckOutItem(int qId){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select a.docno,a.docdate,b.itemcode,b.barcode,b.itemName as name1,b.unitCode,b.checkoutQty,b.price,b.whCode,b.shelfCode,b.rate1,ifnull(b.averageCost,0) as averagecost,ifnull(b.checkoutAmount,0) as checkoutAmount,b.salecode from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno where a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			//vQuery = "select a.docno,a.docdate,b.itemcode,b.barcode,b.itemName as name1,b.unitCode,b.checkoutQty,b.price,b.rate1,ifnull(b.averageCost,0) as averagecost,ifnull(b.checkoutAmount,0) as checkoutAmount,b.salecode from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno where a.qId = "+qId +" and a.docdate = '"+dateFormat.format(dateNow)+"' and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			
			itemlist.clear();
			IV_Resp_ARInvoiceSubBean evt;
			while(rs.next()){
				evt = new IV_Resp_ARInvoiceSubBean();
				evt.setItemCode(rs.getString("itemcode"));
				
				System.out.println("Item CheckOut :"+rs.getString("itemcode"));
				evt.setItemName(rs.getString("name1"));
				evt.setBarCode(rs.getString("barcode"));
				evt.setQty(rs.getDouble("checkoutQty"));
				evt.setPrice(rs.getDouble("price"));
				evt.setUnitCode(rs.getString("unitcode"));
				evt.setPackingRate1(rs.getInt("rate1"));
				evt.setSaleCode(rs.getString("saleCode"));
				evt.setSumOfCost(rs.getDouble("averagecost"));
				evt.setWhCode(rs.getString("whCode"));
				evt.setShelfCode(rs.getString("shelfCode"));
				itemlist.add(evt);
			}	
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		System.out.println("Item Qty : "+itemlist.size());
		return itemlist;
	}
	
	public List<IV_Resp_ARInvoiceSubBean> searchQueueCheckOutItemBranch(int qId,String branchCode){
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		//Date dateNow = new Date();
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			//vQuery = "select a.docno,a.docdate,b.itemcode,b.barcode,b.itemName as name1,b.unitCode,b.checkoutQty,b.price,b.rate1,ifnull(b.averageCost,0) as averagecost,ifnull(b.checkoutAmount,0) as checkoutAmount,b.salecode from QueueMaster a inner join QueueSub b on a.qid = b.qid and a.docno = b.docno and a.branchCode = b.branchCode where  a.branchCode = '"+branchCode+"' and a.qId = "+qId +" and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
			vQuery = "call USP_DT_SearchQueueCheckOutItem  ('"+branchCode+"',"+qId +")";
			System.out.println("GetDocno :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			
			itemlist.clear();
			IV_Resp_ARInvoiceSubBean evt;
			while(rs.next()){
				evt = new IV_Resp_ARInvoiceSubBean();
				evt.setItemCode(rs.getString("itemcode"));
				
				System.out.println("Item CheckOut :"+rs.getString("itemcode"));
				evt.setItemName(rs.getString("name1"));
				evt.setBarCode(rs.getString("barcode"));
				evt.setQty(rs.getDouble("checkoutQty"));
				evt.setPrice(rs.getDouble("price"));
				evt.setUnitCode(rs.getString("unitcode"));
				evt.setPackingRate1(rs.getInt("rate1"));
				evt.setSaleCode(rs.getString("saleCode"));
				evt.setSumOfCost(rs.getDouble("averagecost"));
				itemlist.add(evt);
			}	
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		System.out.println("Item Qty : "+itemlist.size());
		return itemlist;
	}
	
	public LoginBean searchUserAccessToken(String access_token){

		try{
			Statement st = ds.getStatement("SmartConfig");
			
		    vQuery="select u.code,u.name,u.role from  User as u inner join UserAccess as ua on u.code=ua.userCode and ua.userUUID='"+access_token+"'"
		    		+" order by ua.dateTimeStamp DESC LIMIT 1" ;
		    
		    System.out.println("SaleCode = "+vQuery);
		    
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				userCode.setEmployeeCode(rs.getString("code"));
				userCode.setEmployeeName(rs.getString("name"));
				userCode.setServer_name("");
				userCode.setDatabase_name("");
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return userCode;
	}
	
	public PK_Resp_SaleCodeDetails searchSaleCode(String saleCode){
		try {
			
			sale.setIsExist(0);
			sale.setSaleCode("");
			sale.setSaleName("");
			
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "call USP_DT_SearchSale ('"+saleCode+"')";
			
			System.out.println("Search Sale :"+vQuery);
			
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				sale.setIsExist(1);
				sale.setSaleCode(rs.getString("code"));
				sale.setSaleName(rs.getString("name"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO: handle exception
			sale.setIsExist(0);
			sale.setSaleCode("");
			sale.setSaleName("");
		}finally{
			ds.close();
		}
		
		return sale;
	}
	
	public int checkItemExistQueue(PK_Reqs_ManageItemBean reqItem){
		PK_Resp_GetItemBarcodeBean getItemCode = new PK_Resp_GetItemBarcodeBean();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		int vExist=0;
		
		getItemCode = this.searchItemCode(reqItem.getBarCode());
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");

		    vQuery = "select ifnull(count(itemcode),0) as vCount from QItem where docDate = CURDATE() and qId='"+ reqItem.getqId()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";
		    //vQuery = "select ifnull(count(itemcode),0) as vCount from QItem where docDate = '"+ dateFormat.format(dateNow)+"' and qId='"+ reqItem.getqId()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";
		    System.out.println("ExistQueue :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				vExist = rs.getInt("vCount");
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return vExist;
	}
	
	public boolean verifySaleOrderQty(List<SO_Req_ListEditItemSaleOrderBean> req){
		boolean qtyError=true;
		
		return qtyError;
	}
	
	
	public SO_Res_CheckQueueItemBean checkItemExistPickupProduct(SO_Req_PickingManageProductBean reqItem){
		PK_Resp_GetItemBarcodeBean getItemCode = new PK_Resp_GetItemBarcodeBean();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		int vExist=0;
		
		SO_Res_CheckQueueItemBean itemexist = new SO_Res_CheckQueueItemBean();
		
		getItemCode = this.searchItemCode(reqItem.getItem_barcode());
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");

		    //vQuery = "select ifnull(count(itemcode),0) as vCount,itemcode,barcode,unitcode,price from QItem where docDate = CURDATE() and qId='"+ reqItem.getQueue_id()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";
		    vQuery = "call USP_DT_CheckItemExist ('"+ reqItem.getQueue_id()+"','"+ getItemCode.getCode() +"','"+getItemCode.getUnitCode()+"') ";
		    System.out.println("ExistQueue :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				itemexist.setItem_barcode(rs.getString("barcode"));
				itemexist.setItem_code(rs.getString("itemcode"));
				itemexist.setItem_exist(rs.getInt("itemexist"));
				itemexist.setItem_price(rs.getDouble("price"));
				itemexist.setItem_source(rs.getInt("doctype"));
				itemexist.setItem_unitcode(rs.getString("unitcode"));
				itemexist.setSale_code(rs.getString("salecode"));
				itemexist.setSale_name(rs.getString("salename"));
				itemexist.setRequest_qty(rs.getDouble("reqqty"));
				itemexist.setItem_name(rs.getString("itemname"));
				itemexist.setItem_filepath(rs.getString("filepath"));
				itemexist.setQty_before(rs.getDouble("pickQty"));
				itemexist.setBefore_amount(rs.getDouble("itemamount"));
				itemexist.setQty_after(rs.getDouble("checkoutQty"));
				itemexist.setAfter_amount(rs.getDouble("checkoutAmount"));
				itemexist.setBill_type(rs.getInt("billtype"));
				itemexist.setSale_qty(rs.getDouble("qty"));
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return itemexist;
	}
		
	public SO_Res_CheckQueueItemBean checkItemExistCheckOutProduct(SO_Req_CheckOutManageProductBean reqItem){
		PK_Resp_GetItemBarcodeBean getItemCode = new PK_Resp_GetItemBarcodeBean();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		int vExist=0;
		
		SO_Res_CheckQueueItemBean itemexist = new SO_Res_CheckQueueItemBean();
		
		getItemCode = this.searchItemCode(reqItem.getItem_barcode());
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");

		    //vQuery = "select ifnull(count(itemcode),0) as vCount,itemcode,barcode,unitcode,price from QItem where docDate = CURDATE() and qId='"+ reqItem.getQueue_id()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";
		    vQuery = "call USP_DT_CheckItemExist ('"+ reqItem.getQueue_id()+"','"+ getItemCode.getCode() +"','"+getItemCode.getUnitCode()+"') ";
		    System.out.println("ExistQueue :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				itemexist.setItem_barcode(rs.getString("barcode"));
				itemexist.setItem_code(rs.getString("itemcode"));
				itemexist.setItem_exist(rs.getInt("itemexist"));
				itemexist.setItem_price(rs.getDouble("price"));
				itemexist.setItem_source(rs.getInt("doctype"));
				itemexist.setItem_unitcode(rs.getString("unitcode"));
				itemexist.setSale_code(rs.getString("salecode"));
				itemexist.setSale_name(rs.getString("salename"));
				itemexist.setRequest_qty(rs.getDouble("reqqty"));
				itemexist.setItem_name(rs.getString("itemname"));
				itemexist.setItem_filepath(rs.getString("filepath"));
				itemexist.setQty_before(rs.getDouble("pickQty"));
				itemexist.setBefore_amount(rs.getDouble("itemamount"));
				itemexist.setQty_after(rs.getDouble("checkoutQty"));
				itemexist.setAfter_amount(rs.getDouble("checkoutAmount"));
				itemexist.setBill_type(rs.getInt("billtype"));
				itemexist.setSale_qty(rs.getDouble("qty"));
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return itemexist;
	}
	
	public int checkItemExistQueueBranch(String branchCode,PK_Reqs_ManageItemBean reqItem){
		PK_Resp_GetItemBarcodeBean getItemCode = new PK_Resp_GetItemBarcodeBean();
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		
		
		//Date dateNow = new Date();
		
		int vExist=0;
		
		
		getItemCode = this.searchItemCode(reqItem.getBarCode());
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");

		    //vQuery = "select ifnull(count(itemcode),0) as vCount from QueueSub where branchCode= '"+ branchCode+"' and docDate = CURDATE() and qId='"+ reqItem.getqId()+"' and itemcode = '"+ getItemCode.getCode() +"' and unitcode = '"+getItemCode.getUnitCode()+"' ";
		    vQuery = "call USP_DT_SearchItemExistQueue ('"+branchCode+"',"+ reqItem.getqId()+",'"+ getItemCode.getCode() +"','"+getItemCode.getUnitCode()+"')";
		    System.out.println("ExistQueue :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				vExist = rs.getInt("vCount");
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}

		return vExist;
	}
	
	public double searchItemPrice(String itemCode,String barCode,String unitCode){
		 double price=0.0;
		
		try{
			//Statement st = sqlDS.getSqlStatement("nptest");
			Statement st = sqlDS.getSqlStatement("bcnp");
			//vQuery = "select price from Price where itemCode='"+itemCode+"' and unitCode='"+unitCode+"' limit 1";
			//vQuery = "set dateformat dmy select top 1 saleprice1 as price from BCPriceList  where itemCode='"+itemCode+"' and unitCode='"+unitCode+"' and saletype = 0 and transporttype = 0 and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) >= cast(rtrim(day(startdate))+'/'+rtrim(month(startdate))+'/'+rtrim(year(startdate)) as datetime) and  cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= cast(rtrim(day(stopdate))+'/'+rtrim(month(stopdate))+'/'+rtrim(year(stopdate)) as datetime)";
			vQuery = "exec dbo.USP_DT_SearchItemPrice '"+itemCode+"','"+unitCode+"'";
			System.out.println("Check Price :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				price = rs.getDouble("price");
			}
			
		    rs.close();
		    st.close();
		    
			}catch(SQLException e){
				e.printStackTrace();
				price = 0;
			}finally{
				sqlDS.close();
			}
		
			return price;
	}
		
	public CM_Resp_MemberListBean searchCustomerName(String arCode){
		
		try{
			Statement st = sqlDS.getSqlStatement("POS");
			//vQuery = "select top 1 code,name1,billAddress,isnull(telephone,'') as telephone,isnull(taxNo,'') as taxId,isnull(emailaddress,'') as email,sumofmark1 as point,isnull(memberId,'') as memberId from BCAR where code='"+arCode+"'";
			vQuery = "exec dbo.USP_DT_SearchCustomerData '"+arCode+"'";
			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				ar.setArName(rs.getString("name1"));
				ar.setArAddress(rs.getString("billAddress"));
				ar.setArId(rs.getString("taxid"));
				ar.setArPhone(rs.getString("telephone"));
				ar.setArPoint(rs.getDouble("point"));
			}
			
		    rs.close();
		    st.close();
		    
			}catch(SQLException e){
				e.printStackTrace();
				ar.setArName("");
				ar.setArAddress("");
				ar.setArId("");
				ar.setArPhone("");
				ar.setArPoint(0);
			}finally{
				sqlDS.close();
			}
		
			return ar;
	}	
	
	public CT_Resp_ResponseBean validateCreditCard(List<IV_Reqs_CreditCardBean> crdCard){
		int counterr=0;
		int isUsed = 0;
		
		if (crdCard.size()!=0){
			for(int x=0;x<crdCard.size();x++){
				
				System.out.println("carditcard No :"+crdCard.get(x).getCardNo());
				
				if (crdCard.get(x).getCardNo() != "" && crdCard.get(x).getCardNo() != null){
					if (crdCard.get(x).getAmount()!=0){
						if(crdCard.get(x).getBankCode()!= "" && crdCard.get(x).getBankCode()!= null){
							if (crdCard.get(x).getConfirmNo() != "" && crdCard.get(x).getConfirmNo() != null){
								if (crdCard.get(x).getCreditType()!= "" && crdCard.get(x).getCreditType()!= null){

									try{
										Statement st = sqlDS.getSqlStatement("POS");
										
										vQuery = "set dateformat dmy select count(ConfirmNo) as vCount from dbo.bccreditcard where CreditCardNo = '"+crdCard.get(x).getCardNo()+"' and ConfirmNo = '"+crdCard.get(x).getConfirmNo()+"'";
										ResultSet rs = st.executeQuery(vQuery);
										
										System.out.println("Verify CreditCard :"+vQuery);
										while(rs.next()){
											isUsed = rs.getInt("vCount");
										}
										
										if (isUsed == 0){
											System.out.println("CreditCard is true = "+isUsed);
											creditcardRes.setIsSuccess(true);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is ok");
										}else{
											System.out.println("CreditCard is false = "+isUsed);
											creditcardRes.setIsSuccess(false);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is used");
										}
										
									    rs.close();
									    st.close();
									    
									}catch(SQLException e){
										counterr++;
										creditcardRes.setIsSuccess(false);
										creditcardRes.setProcess("validate creditcard");
										creditcardRes.setProcessDesc(e.getMessage());
									}
									finally{
										sqlDS.close();
									}

								}else{
									counterr++;
									creditcardRes.setIsSuccess(false);
									creditcardRes.setProcess("validate creditcard");
									creditcardRes.setProcessDesc("CreditType is null or empty");
								}
							}else{
								counterr++;
								creditcardRes.setIsSuccess(false);
								creditcardRes.setProcess("validate creditcard");
								creditcardRes.setProcessDesc("ConfirmNo is null or empty");
							}
						}else{
							creditcardRes.setIsSuccess(false);
							creditcardRes.setProcess("validate creditcard");
							creditcardRes.setProcessDesc("Bank is null or empty");
						}
					}else{
						counterr++;
						creditcardRes.setIsSuccess(false);
						creditcardRes.setProcess("validate creditcard");
						creditcardRes.setProcessDesc("CreditAmount = 0");
					}
				}else{
					counterr++;
					creditcardRes.setIsSuccess(false);
					creditcardRes.setProcess("validate creditcard");
					creditcardRes.setProcessDesc("CreditcardNo is null or empty");
				}
			}
		}
		return creditcardRes; 
		
	}
	
	public CT_Resp_ResponseBean validateDataCreditCard(String serverName,String dataBaseName,List<IV_Reqs_CreditCardBean> crdCard){
		int counterr=0;
		int isUsed = 0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		if (crdCard.size()!=0){
			for(int x=0;x<crdCard.size();x++){
				
				System.out.println("carditcard No :"+crdCard.get(x).getCardNo());
				
				if (crdCard.get(x).getCardNo() != "" && crdCard.get(x).getCardNo() != null){
					if (crdCard.get(x).getAmount()!=0){
						if(crdCard.get(x).getBankCode()!= "" && crdCard.get(x).getBankCode()!= null){
							if (crdCard.get(x).getConfirmNo() != "" && crdCard.get(x).getConfirmNo() != null){
								if (crdCard.get(x).getCreditType()!= "" && crdCard.get(x).getCreditType()!= null){

									try{
										Statement st = npDS.getSqlStatementBranch(connData);
										
										//vQuery = "set dateformat dmy select count(ConfirmNo) as vCount from dbo.bccreditcard where CreditCardNo = '"+crdCard.get(x).getCardNo()+"' and ConfirmNo = '"+crdCard.get(x).getConfirmNo()+"'";
										vQuery = "exec dbo.USP_DT_ValidateCreditCard '"+crdCard.get(x).getCardNo()+"','"+crdCard.get(x).getConfirmNo()+"'";
										ResultSet rs = st.executeQuery(vQuery);
										
										System.out.println("Verify Coupong :"+vQuery);
										while(rs.next()){
											isUsed = rs.getInt("vCount");
										}
										
										if (isUsed == 0){
											creditcardRes.setIsSuccess(true);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is ok");
										}else{
											creditcardRes.setIsSuccess(false);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is used");
										}
										
									    rs.close();
									    st.close();
									    
									}catch(SQLException e){
										counterr++;
										creditcardRes.setIsSuccess(false);
										creditcardRes.setProcess("validate creditcard");
										creditcardRes.setProcessDesc(e.getMessage());
									}
									finally{
										npDS.close();
									}

								}else{
									counterr++;
									creditcardRes.setIsSuccess(false);
									creditcardRes.setProcess("validate creditcard");
									creditcardRes.setProcessDesc("CreditType is null or empty");
								}
							}else{
								counterr++;
								creditcardRes.setIsSuccess(false);
								creditcardRes.setProcess("validate creditcard");
								creditcardRes.setProcessDesc("ConfirmNo is null or empty");
							}
						}else{
							creditcardRes.setIsSuccess(false);
							creditcardRes.setProcess("validate creditcard");
							creditcardRes.setProcessDesc("Bank is null or empty");
						}
					}else{
						counterr++;
						creditcardRes.setIsSuccess(false);
						creditcardRes.setProcess("validate creditcard");
						creditcardRes.setProcessDesc("CreditAmount = 0");
					}
				}else{
					counterr++;
					creditcardRes.setIsSuccess(false);
					creditcardRes.setProcess("validate creditcard");
					creditcardRes.setProcessDesc("CreditcardNo is null or empty");
				}
			}
		}
		return creditcardRes; 
		
	}
	
	
	public CT_Resp_ResponseBean validateDataCreditCard_SO(String serverName,String dataBaseName,List<SO_Reqs_CreditCardBean> crdCard){
		int counterr=0;
		int isUsed = 0;
		int checkdulpicate = 0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		if (crdCard.size()!=0){
			for(int x=0;x<crdCard.size();x++){
				
				System.out.println("carditcard No :"+crdCard.get(x).getCard_no());
				for(int d=0;d<crdCard.size();d++){
					if (crdCard.get(x).getBank_code().equals(crdCard.get(d).getBank_code()) && crdCard.get(x).getCard_no().equals(crdCard.get(d).getCard_no())  && crdCard.get(x).getConfirm_no().equals(crdCard.get(d).getConfirm_no()) ){
						checkdulpicate = checkdulpicate+1;
					}
				}
				
				if (crdCard.get(x).getCard_no() != "" && crdCard.get(x).getCard_no() != null){
					if (crdCard.get(x).getAmount()!=0 && checkdulpicate <= 1){
						if(crdCard.get(x).getBank_code()!= "" && crdCard.get(x).getBank_code()!= null){
							if (crdCard.get(x).getConfirm_no() != "" && crdCard.get(x).getConfirm_no() != null){
								if (crdCard.get(x).getCredit_type()!= "" && crdCard.get(x).getCredit_type()!= null){

									try{
										Statement st = npDS.getSqlStatementBranch(connData);
										
										//vQuery = "set dateformat dmy select count(ConfirmNo) as vCount from dbo.bccreditcard where CreditCardNo = '"+crdCard.get(x).getCardNo()+"' and ConfirmNo = '"+crdCard.get(x).getConfirmNo()+"'";
										vQuery = "exec dbo.USP_DT_ValidateCreditCard '"+crdCard.get(x).getCard_no()+"','"+crdCard.get(x).getConfirm_no()+"'";
										ResultSet rs = st.executeQuery(vQuery);
										
										System.out.println("Verify Coupong :"+vQuery);
										while(rs.next()){
											isUsed = rs.getInt("vCount");
										}
										
										if (isUsed == 0){
											creditcardRes.setIsSuccess(true);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is ok");
										}else{
											creditcardRes.setIsSuccess(false);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is used");
										}
										
									    rs.close();
									    st.close();
									    
									}catch(SQLException e){
										counterr++;
										creditcardRes.setIsSuccess(false);
										creditcardRes.setProcess("validate creditcard");
										creditcardRes.setProcessDesc(e.getMessage());
									}
									finally{
										npDS.close();
									}

								}else{
									counterr++;
									creditcardRes.setIsSuccess(false);
									creditcardRes.setProcess("validate creditcard");
									creditcardRes.setProcessDesc("CreditType is null or empty");
								}
							}else{
								counterr++;
								creditcardRes.setIsSuccess(false);
								creditcardRes.setProcess("validate creditcard");
								creditcardRes.setProcessDesc("ConfirmNo is null or empty");
							}
						}else{
							creditcardRes.setIsSuccess(false);
							creditcardRes.setProcess("validate creditcard");
							creditcardRes.setProcessDesc("Bank is null or empty");
						}
					}else{
						counterr++;
						creditcardRes.setIsSuccess(false);
						creditcardRes.setProcess("validate creditcard");
						creditcardRes.setProcessDesc("CreditAmount = 0 or Data CreditCard Duplicate");
					}
				}else{
					counterr++;
					creditcardRes.setIsSuccess(false);
					creditcardRes.setProcess("validate creditcard");
					creditcardRes.setProcessDesc("CreditcardNo is null or empty");
				}
			}
		}
		return creditcardRes; 
		
		
	}
	
	public CT_Resp_ResponseBean validateCreditCardBranch(String branchCode,String serverName,String dataBaseName,List<IV_Reqs_CreditCardBean> crdCard){
		int counterr=0;
		int isUsed = 0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		if (crdCard.size()!=0){
			for(int x=0;x<crdCard.size();x++){
				
				System.out.println("carditcard No :"+crdCard.get(x).getCardNo());
				
				if (crdCard.get(x).getCardNo() != "" && crdCard.get(x).getCardNo() != null){
					if (crdCard.get(x).getAmount()!=0){
						if(crdCard.get(x).getBankCode()!= "" && crdCard.get(x).getBankCode()!= null){
							if (crdCard.get(x).getConfirmNo() != "" && crdCard.get(x).getConfirmNo() != null){
								if (crdCard.get(x).getCreditType()!= "" && crdCard.get(x).getCreditType()!= null){

									try{
										Statement st = npDS.getSqlStatementBranch(connData);
										
										//vQuery = "set dateformat dmy select count(ConfirmNo) as vCount from dbo.bccreditcard where CreditCardNo = '"+crdCard.get(x).getCardNo()+"' and ConfirmNo = '"+crdCard.get(x).getConfirmNo()+"'";
										vQuery = "exec dbo.USP_DT_ValidateCreditCard '"+crdCard.get(x).getCardNo()+"','"+crdCard.get(x).getConfirmNo()+"'";
										ResultSet rs = st.executeQuery(vQuery);
										
										System.out.println("Verify Coupong :"+vQuery);
										while(rs.next()){
											isUsed = rs.getInt("vCount");
										}
										
										if (isUsed == 0){
											creditcardRes.setIsSuccess(true);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is ok");
										}else{
											creditcardRes.setIsSuccess(false);
											creditcardRes.setProcess("validate creditcard");
											creditcardRes.setProcessDesc("CreditCard is used");
										}
										
									    rs.close();
									    st.close();
									    
									}catch(SQLException e){
										counterr++;
										creditcardRes.setIsSuccess(false);
										creditcardRes.setProcess("validate creditcard");
										creditcardRes.setProcessDesc(e.getMessage());
									}
									finally{
										npDS.close();
									}

								}else{
									counterr++;
									creditcardRes.setIsSuccess(false);
									creditcardRes.setProcess("validate creditcard");
									creditcardRes.setProcessDesc("CreditType is null or empty");
								}
							}else{
								counterr++;
								creditcardRes.setIsSuccess(false);
								creditcardRes.setProcess("validate creditcard");
								creditcardRes.setProcessDesc("ConfirmNo is null or empty");
							}
						}else{
							creditcardRes.setIsSuccess(false);
							creditcardRes.setProcess("validate creditcard");
							creditcardRes.setProcessDesc("Bank is null or empty");
						}
					}else{
						counterr++;
						creditcardRes.setIsSuccess(false);
						creditcardRes.setProcess("validate creditcard");
						creditcardRes.setProcessDesc("CreditAmount = 0");
					}
				}else{
					counterr++;
					creditcardRes.setIsSuccess(false);
					creditcardRes.setProcess("validate creditcard");
					creditcardRes.setProcessDesc("CreditcardNo is null or empty");
				}
			}
		}
		return creditcardRes; 
		
		
	}
	
	public CT_Resp_ResponseBean verifyCoupong(List<IV_Reqs_CouponBean> coupong){
		int counterr=0;
		int checkexist=0;
			
		if(coupong.size()!=0){
			for(int y=0;y<coupong.size();y++){
				if (coupong.get(y).getCouponCode()!="" && coupong.get(y).getCouponCode()!= null){
					if (coupong.get(y).getAmount()!= 0){
						try{
							Statement st = sqlDS.getSqlStatement("POS");
							vQuery = "set dateformat dmy select count(code) as vCount from dbo.bccoupon where code = '"+coupong.get(y).getCouponCode()+"' and couponval = "+coupong.get(y).getAmount()+"and code not in (select couponcode from dbo.bccouponreceive where docno in (select docno from dbo.bcarinvoice where iscancel = 0)) and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= expiredate";
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Coupong :"+vQuery);
							while(rs.next()){
								checkexist = rs.getInt("vCount");
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							sqlDS.close();
						}
						
					}else{
						counterr++;
						//error Amount;
					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("coupongsize :"+coupong.size()+","+counterr+","+checkexist);
		
		if(counterr==0 && checkexist !=0){
			coupongRes.setIsSuccess(true);
			coupongRes.setProcess("verifyCoupong");
			coupongRes.setProcessDesc("Coupong is pass");
		}else{
			if (counterr==0 && checkexist ==0){
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used");
			}else{
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong data error");
			}
		}
		
		
		return coupongRes;
	}
	
	public CT_Resp_ResponseBean verifyCoupongBranch(String branchCode,String serverName,String dataBaseName,List<IV_Reqs_CouponBean> coupong){
		int counterr=0;
		int checkexist=0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		
		System.out.println("CouponBranch = "+serverName+","+dataBaseName);
			
		if(coupong.size()!=0){
			for(int y=0;y<coupong.size();y++){
				if (coupong.get(y).getCouponCode()!="" && coupong.get(y).getCouponCode()!= null){
					if (coupong.get(y).getAmount()!= 0){
						try{
							Statement st = npDS.getSqlStatementBranch(connData);
							//vQuery = "set dateformat dmy select count(code) as vCount from dbo.bccoupon where code = '"+coupong.get(y).getCouponCode()+"' and couponval = "+coupong.get(y).getAmount()+"and code not in (select couponcode from dbo.bccouponreceive where docno in (select docno from dbo.bcarinvoice where iscancel = 0)) and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= expiredate";
							vQuery = "exec dbo.USP_DT_VerifyCoupong '"+coupong.get(y).getCouponCode()+"',"+coupong.get(y).getAmount();
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Coupong :"+vQuery);
							while(rs.next()){
								checkexist = rs.getInt("vCount");
								
								System.out.println("Count Coupon ="+rs.getInt("vCount"));
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							npDS.close();
						}
						
					}else{
						counterr++;
						//error Amount;
					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("coupongsizeBranch :"+coupong.size()+","+counterr+","+checkexist);
		
		if(counterr==0 && checkexist !=0){
			coupongRes.setIsSuccess(true);
			coupongRes.setProcess("verifyCoupong");
			coupongRes.setProcessDesc("Coupong is pass");
		}else{
			if (counterr==0 && checkexist ==0){
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used or value invalid");
			}else{
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong data error");
			}
		}
		
		System.out.println(" = "+coupongRes.getIsSuccess());
		
		return coupongRes;
	}
	
	
	public CT_Resp_ResponseBean verifyCoupongBranch_SO(String branchCode,String serverName,String dataBaseName,List<SO_Reqs_CouponBean> coupong){
		int counterr=0;
		int checkexist=0;
		int checkduplicate=0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		
		System.out.println("CouponBranch = "+serverName+","+dataBaseName);
		
		if(coupong.size()!=0){
			
			for(int y=0;y<coupong.size();y++){
				if (coupong.get(y).getCoupon_code()!="" && coupong.get(y).getCoupon_code()!= null){
					
					checkduplicate = 0;	
					for(int c=0;c<coupong.size();c++){
						System.out.println("coupong :"+"Y="+y+" "+coupong.get(y).getCoupon_code()+" "+"C="+c+" "+coupong.get(c).getCoupon_code());
						if (coupong.get(y).getCoupon_code().equals(coupong.get(c).getCoupon_code())){
							checkduplicate = checkduplicate+1;
							System.out.println("coupong.get(y).getCoupon_code()="+"Y="+y+" "+coupong.get(y).getCoupon_code()+" "+"C="+c+" "+coupong.get(c).getCoupon_code());
							System.out.println("Coupon checkduplicate ="+checkduplicate);
						} 
					}
					
					System.out.println("checkduplicate :"+checkduplicate);
					if (coupong.get(y).getAmount()!= 0 && checkduplicate <= 1){
						try{
							Statement st = npDS.getSqlStatementBranch(connData);
							//vQuery = "set dateformat dmy select count(code) as vCount from dbo.bccoupon where code = '"+coupong.get(y).getCouponCode()+"' and couponval = "+coupong.get(y).getAmount()+"and code not in (select couponcode from dbo.bccouponreceive where docno in (select docno from dbo.bcarinvoice where iscancel = 0)) and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= expiredate";
							vQuery = "exec dbo.USP_DT_VerifyCoupong '"+coupong.get(y).getCoupon_code()+"',"+coupong.get(y).getAmount();
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Coupong :"+vQuery);
							while(rs.next()){
								checkexist = rs.getInt("vCount");
								
								System.out.println("Count Coupon ="+rs.getInt("vCount"));
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							npDS.close();
						}
						
					}else{
						counterr++;
						//error Amount;
					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("coupongsizeBranch :"+coupong.size()+","+counterr+","+checkexist);
		
		if(counterr==0 && checkexist !=0){
			coupongRes.setIsSuccess(true);
			coupongRes.setProcess("verifyCoupong");
			coupongRes.setProcessDesc("Coupong is pass");
		}else{
			if (counterr==0 && checkexist ==0){
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used or value invalid");
			}else{
				coupongRes.setIsSuccess(false);
				coupongRes.setProcess("verifyCoupong");
				coupongRes.setProcessDesc("Coupong data error");
			}
		}
		
		System.out.println(" = "+coupongRes.getIsSuccess());
		
		return coupongRes;
	}

	public CT_Resp_ResponseBean verifyDeposit(String serverName,String dataBaseName,String ar_code,List<IV_Req_DepositBean> deposit){
		int counterr=0;
		int checkexist=0;
		
//		CT_Req_ServerDataBaseBean dbName = new CT_Req_ServerDataBaseBean();
//	
//		dbName.setServerName("192.168.0.7");
//		dbName.setDatabaseName("nptest");
		
		
		if(deposit.size()!=0){
			for(int z=0;z<deposit.size();z++){
				if (deposit.get(z).getDeposit_id()!="" && deposit.get(z).getDeposit_id()!= null){
					if (deposit.get(z).getAmount()!= 0){
						try{
							Statement st = sqlDS.getSqlStatement(serverName, dataBaseName);
							vQuery = "set dateformat dmy select count(docno) as vCount from dbo.bcardeposit where arcode = '"+ar_code+"' and docno = '"+deposit.get(z).getDeposit_id()+"' and billbalance >= "+deposit.get(z).getAmount()+" and iscancel = 0";
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Deposit :"+vQuery);
							while(rs.next()){
								checkexist = rs.getInt("vCount");
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							sqlDS.close();
						}
						
					}else{
						counterr++;
						//error Amount;
					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("depositsize :"+deposit.size()+","+counterr+","+checkexist);
		
		if(counterr==0 && checkexist !=0){
			depositRes.setIsSuccess(true);
			depositRes.setProcess("verifyDeposit");
			depositRes.setProcessDesc("Deposit is pass");
		}else{
			if (counterr==0 && checkexist ==0){
				depositRes.setIsSuccess(false);
				depositRes.setProcess("verifyDeposit");
				depositRes.setProcessDesc("Deposit not exist or expire or value invalid or isUsed");
			}else{
				depositRes.setIsSuccess(false);
				depositRes.setProcess("verifyDeposit");
				depositRes.setProcessDesc("Deposit data error");
			}
		}
		
		
		return depositRes;
	}
	
	public CT_Resp_ResponseBean verifyItemSaleOrder(DT_User_LoginBranchBean db,SO_Req_GenOTPSaleOrderBean item){
		int counterr=0;
		int checkexist=0;
		double remainqty=0;
		
		//CT_Req_ServerDataBaseBean dbName = new CT_Req_ServerDataBaseBean();
	
		//dbName.setServerName("192.168.0.7");
		//dbName.setDatabaseName("nptest");
		
		
		if(item.getItem().size()!=0){
			for(int m=0;m<item.getItem().size();m++){
				if (item.getItem().get(m).getItem_code()!="" && item.getItem().get(m).getItem_code()!= null){
					if (item.getItem().get(m).getRequest_qty()!= 0){
						try{
							Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
							vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '"+item.getDoc_no()+"' and itemcode = '"+item.getItem().get(m).getItem_code()+"' and unitcode ='"+item.getItem().get(m).getItem_unit_code()+"' and linenumber ="+item.getItem().get(m).getLine_number()+" and iscancel = 0";
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Item :"+vQuery);
							while(rs.next()){
								remainqty = rs.getDouble("remainqty");
							}
							
							System.out.println("remainqty,item.getItem().get(m).getRequest_qty()=="+remainqty+","+item.getItem().get(m).getRequest_qty());
							
							if (remainqty > 0 && remainqty<item.getItem().get(m).getRequest_qty()){
								counterr++;
								
								System.out.println("Count Error ="+counterr);
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							sqlDS.close();
						}
						
					}//else{
//						counterr++;
//						//error Amount;
//					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("itemsize :"+item.getItem().size()+","+counterr+","+checkexist);
		
		if(counterr==0){
			itemRes.setIsSuccess(true);
			itemRes.setProcess("verifyItem");
			itemRes.setProcessDesc("Item is pass");
		}else{

			itemRes.setIsSuccess(false);
			itemRes.setProcess("verifyDeposit");
			itemRes.setProcessDesc("Item data error");
		}
		
		return itemRes;
	}
	
	public double checkItemSaleOrder(DT_User_LoginBranchBean db,String doc_no,SO_Req_ListItemToQueueBean item){
		double remainqty=0;
	
		
		if(doc_no !=""){
			if (item.getItem_code()!="" && item.getItem_code()!= null){
				if (item.getRequest_qty()!= 0){
					try{
						Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
						vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '"+doc_no+"' and itemcode = '"+item.getItem_code()+"' and unitcode ='"+item.getItem_unit_code()+"' and linenumber ="+item.getLine_number()+" and iscancel = 0";
						ResultSet rs = st.executeQuery(vQuery);

						System.out.println("Verify Deposit :"+vQuery);
						while(rs.next()){
							remainqty = rs.getDouble("remainqty");
						}

						rs.close();
						st.close();

					}catch(SQLException e){
						e.printStackTrace();
						remainqty =0;
					}finally{
						sqlDS.close();
					}

				}

			}
		}

		return remainqty;
	}
	
	public double calcPointInvoice(String vInvNo){
		double point=0;
		
		try {
			Statement st = sqlDS.getSqlStatement("POS");
			
			vQuery = "exec dbo.USP_VP_CheckInvoicePoint '" + vInvNo +"'";
			
			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				point = rs.getDouble("pointbal");
			}
			
		    rs.close();
		    st.close();
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			sqlDS.close();
		}
		
		return point;
	}
	
	public SO_Res_CheckDataSOBean checkSaleOrderData(DT_User_LoginBranchBean db,String vSaleOrderNo){
		//DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		
//		db.setServerName("192.168.0.7");
//		db.setDbName("bcnp");

		
			try {
				Statement stmt = npDS.getSqlStatementBranch(db);
				vQuery = "exec dbo.USP_NP_CheckDataSaleOrder '"+vSaleOrderNo+"'";
				
				System.out.println(vQuery);
				ResultSet rs = stmt.executeQuery(vQuery);
				
				
				while(rs.next()){

					SO.setDoc_date(rs.getString("docdate"));
					SO.setAr_code(rs.getString("arcode"));
					SO.setAr_name(rs.getString("arname"));
					SO.setSale_code(rs.getString("salecode"));
					SO.setSale_name(rs.getString("salename"));
				
				}
				
				rs.close();
				stmt.close();
				
			}
			catch (SQLException e) {
				// TODO: handle exception
				
				response.setProcess("QueueDetails");
				response.setProcessDesc(e.getMessage());
				response.setIsSuccess(false);
			}finally{
				ds.close();
			}
			
			
			return SO;
	}
	
	public SO_Res_ItemSaleOrderBean checkItemSaleOrderData(DT_User_LoginBranchBean db,String vSaleOrderNo,String vItemCode,String vUnitCode,int vLineNumber){

			try {
				Statement stmt = npDS.getSqlStatementBranch(db);
				vQuery = "exec dbo.USP_NP_CheckDataItemSaleOrder '"+vSaleOrderNo+"','"+vItemCode+"','"+vUnitCode+"',"+vLineNumber;
				
				System.out.println(vQuery);
				ResultSet rs = stmt.executeQuery(vQuery);
				
				while(rs.next()){
					sale_item.setItem_code(rs.getString("itemcode"));
					sale_item.setItem_name(rs.getString("itemname"));
					sale_item.setItem_price(rs.getDouble("price"));
					sale_item.setWh_code(rs.getString("whcode"));
					sale_item.setShelf_code(rs.getString("shelfcode"));
					sale_item.setItem_rate(rs.getInt("rate1"));
					sale_item.setItem_average(rs.getDouble("avgcost"));
					sale_item.setItem_category_code("");
					sale_item.setItem_category_name("");
					sale_item.setItem_depart_name("");
					sale_item.setItem_department_code("");
					sale_item.setItem_expert_code("");
					sale_item.setSec_code("");
					sale_item.setSec_name("");
					sale_item.setRemain_qty(rs.getDouble("remainqty"));
				}
				
				rs.close();
				stmt.close();
				
			}
			catch (SQLException e) {
				// TODO: handle exception
				
				response.setProcess("QueueDetails");
				response.setProcessDesc(e.getMessage());
				response.setIsSuccess(false);
			}finally{
				ds.close();
			}
			
			return sale_item;
	}
	
	public String getItemFilePath(String itemCode){
		String filePath="";
		try {
			Statement st = sqlDS.getSqlStatement("POS");
			
			vQuery = "select isnull(picfilename1,'') as picfilename1 from dbo.bcitem where code = '" + itemCode +"'";
			
			System.out.println("getItemFilePath = "+vQuery);
			
			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				filePath = rs.getString("picfilename1");
				
				System.out.println(itemCode +" Set filePath : "+filePath);
			}
			
		    rs.close();
		    st.close();
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			sqlDS.close();
		}
		
		return filePath;
	}
		
	public double calcPointInvoiceBranch(String serverName,String dataBaseName,String vInvNo){
		double point=0;
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		
		try {
			Statement st = npDS.getSqlStatementBranch(connData);
			
			vQuery = "exec dbo.USP_VP_CheckInvoicePoint '" + vInvNo +"'";
			
			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				point = rs.getDouble("pointbal");
			}
			
		    rs.close();
		    st.close();
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			npDS.close();
		}
		
		return point;
	}
		
	public int CheckAccessToken(String accessToken){
		int access=0;
		
		
		try {
			Statement stmtTK = dsTK.getStatement("SmartConfig");
			
			vQuery = "select count(userUUID) as vCount from UserAccess where userUUID = '"+ accessToken +"'";
			//System.out.println("vCountToken : "+vQuery);
			ResultSet rsTK = stmtTK.executeQuery(vQuery);
			while (rsTK.next()) {
				access = rsTK.getInt("vCount");
			}
			rsTK.close();
			stmtTK.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			dsTK.close();
		}
		
		 return access;
	}
	
	public User_Resp_CheckDataAccessTokenBean CheckDataAccessToken(String accessToken){
		int access=0;
		
		try {
			Statement stmtTK = dsTK.getStatement("SmartConfig");
			
			//vQuery = "select count(userUUID) as vCount,branchCode,machineNo,machineCode,whCode,shelfCode,serverName,dataBaseName from UserAccess a LEFT JOIN Branch b on a.branchCode = b.code where userUUID = '"+ accessToken +"'";
			vQuery = "call USP_DT_CheckDataAccessToken ('Pos_DriveThru','pos','"+accessToken+"')";
			//System.out.println("vCountToken : "+vQuery);
			ResultSet rsTK = stmtTK.executeQuery(vQuery);
			while (rsTK.next()) {
				branch.setExist(rsTK.getInt("vCount"));
				branch.setBranchCode(rsTK.getString("branch_id"));
				branch.setMachineNo(rsTK.getString("machine_no"));
				branch.setMachineCode(rsTK.getString("machine_code"));
				branch.setWhCode(rsTK.getString("wh_code"));
				branch.setShelfCode(rsTK.getString("shelf_code"));
				branch.setServerName(rsTK.getString("server_name"));
				branch.setDataBaseName(rsTK.getString("database_name"));
				branch.setZoneid(rsTK.getString("zone_id"));
			}
			rsTK.close();
			stmtTK.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			dsTK.close();
		}
		
		 return branch;
	}
	
	public IV_Resp_PrintSlipBean copyHTML (String dbName,IV_Reqs_PrintSlipBean reqs){
		IV_Resp_PrintSlipBean copyInv = new IV_Resp_PrintSlipBean();
		List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList<IV_Resp_PrintSlipSubBean>();
		Date  billDocDate ;
		String pointDesc;
		double pointBal = 0;
		
		
		
		pointBal = 0;//data.calcPointInvoiceBranch(branch.getServerName(),branch.getDataBaseName(),bill.getBillHeader().getDocNo());
		
		
		pointDesc = "เอกสารนี้ได้จำนวนแต้ม :"+ pointBal+" แต้ม";
		
		System.out.println("Copy POS");
		
		try {
			Statement st = sqlDS.getSqlStatement(dbName);
			
			vQuery = "exec dbo.USP_NP_InvoicePrintDetails 0, '"+reqs.getInvoiceNo()+"','"+reqs.getArCode()+"'";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			
			listInv.clear();
			while(rs.next()){
				
				billDocDate = rs.getDate("docdate");
				
				printInv.setDocNo(rs.getString("docno"));
				printInv.setDocDate(billDocDate.toString());
				printInv.setCompanyName(rs.getString("companyname"));
				printInv.setTaxId(rs.getString("taxid"));
				printInv.setPosId(rs.getString("posid"));
				printInv.setCashier(rs.getString("cashiercode"));
				printInv.setSaleCode(rs.getString("salecode"));
				printInv.setBillTime(rs.getString("billtime"));
				printInv.setTotalAmount(rs.getDouble("totalamount"));
				printInv.setTax(rs.getInt("taxrate"));
				printInv.setTaxAmount(rs.getDouble("taxamount"));
				printInv.setCashAmount(rs.getDouble("sumcashamount"));
				printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
				printInv.setChange(rs.getDouble("changeamount"));
				printInv.setGreeting1(rs.getString("greeting1"));
				printInv.setGreeting2(rs.getString("greeting2"));
				printInv.setGreeting3(rs.getString("greeting3"));
				printInv.setGreeting4(rs.getString("greeting4"));
				printInv.setGreeting5(rs.getString("greeting5"));
				printInv.setRemark("");
				printInv.setPromotionDesc1(pointDesc);
				printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
				printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
				printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
				printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
				printInv.setPoint(rs.getInt("point"));


					IV_Resp_PrintSlipSubBean evt;
					evt = new IV_Resp_PrintSlipSubBean();
					evt.setItemCode(rs.getString("itemcode"));
					evt.setItemName(rs.getString("itemname"));
					evt.setQty(rs.getInt("qty"));
					evt.setPrice(rs.getDouble("price"));
					evt.setAmount(rs.getDouble("amount"));
					evt.setUnitCode(rs.getString("unitcode"));
					
					System.out.println(rs.getString("itemcode"));
					listInv.add(evt);
					
				}
				response.setIsSuccess(true);
				response.setProcess("Search Print Slip");
				response.setProcessDesc("Successfully");
				printInv.setItem(listInv);
				printInv.setResponse(response);
				
				System.out.println("CashierCode : "+printInv.getCashier());
		
			    rs.close();
			    st.close();
			
		} catch (SQLException e) {								
			printInv.setItem(listINVCopy);
			printInv.setResponse(response);
		}finally{
			sqlDS.close();
		}   
		
		System.out.println("InvoiceNO : "+printInv.getDocNo());
		

	    response.setIsSuccess(true);
	    response.setProcess("Test");
	    response.setProcessDesc("Successful");
	    return printInv; 
		  
	}
	
	
	public IV_Resp_PrintSlipBean copyPosHTML (DT_User_LoginBranchBean db,IV_Reqs_PrintSlipBean reqs){
		IV_Resp_PrintSlipBean copyInv = new IV_Resp_PrintSlipBean();
		List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList<IV_Resp_PrintSlipSubBean>();
		Date  billDocDate ;
		String pointDesc;
		double pointBal = 0;
		
		
		
		pointBal = 0;//data.calcPointInvoiceBranch(branch.getServerName(),branch.getDataBaseName(),bill.getBillHeader().getDocNo());
		
		
		pointDesc = "เอกสารนี้ได้จำนวนแต้ม :"+ pointBal+" แต้ม";
		
		System.out.println("Copy POS");
		
		try {
			Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
			
			vQuery = "exec dbo.USP_NP_InvoicePrintDetails 0, '"+reqs.getInvoiceNo()+"','"+reqs.getArCode()+"'";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			
			listInv.clear();
			while(rs.next()){
				
				billDocDate = rs.getDate("docdate");
				
				printInv.setDocNo(rs.getString("docno"));
				printInv.setDocDate(billDocDate.toString());
				printInv.setCompanyName(rs.getString("companyname"));
				printInv.setTaxId(rs.getString("taxid"));
				printInv.setPosId(rs.getString("posid"));
				printInv.setCashier(rs.getString("cashiercode"));
				printInv.setSaleCode(rs.getString("salecode"));
				printInv.setBillTime(rs.getString("billtime"));
				printInv.setTotalAmount(rs.getDouble("totalamount"));
				printInv.setTax(rs.getInt("taxrate"));
				printInv.setTaxAmount(rs.getDouble("taxamount"));
				printInv.setCashAmount(rs.getDouble("sumcashamount"));
				printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
				printInv.setChange(rs.getDouble("changeamount"));
				printInv.setGreeting1(rs.getString("greeting1"));
				printInv.setGreeting2(rs.getString("greeting2"));
				printInv.setGreeting3(rs.getString("greeting3"));
				printInv.setGreeting4(rs.getString("greeting4"));
				printInv.setGreeting5(rs.getString("greeting5"));
				printInv.setRemark("");
				printInv.setPromotionDesc1(pointDesc);
				printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
				printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
				printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
				printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
				printInv.setPoint(rs.getInt("point"));


					IV_Resp_PrintSlipSubBean evt;
					evt = new IV_Resp_PrintSlipSubBean();
					evt.setItemCode(rs.getString("itemcode"));
					evt.setItemName(rs.getString("itemname"));
					evt.setQty(rs.getInt("qty"));
					evt.setPrice(rs.getDouble("price"));
					evt.setAmount(rs.getDouble("amount"));
					evt.setUnitCode(rs.getString("unitcode"));
					
					System.out.println(rs.getString("itemcode"));
					listInv.add(evt);
					
				}
				response.setIsSuccess(true);
				response.setProcess("Search Print Slip");
				response.setProcessDesc("Successfully");
				printInv.setItem(listInv);
				printInv.setResponse(response);
				
				System.out.println("CashierCode : "+printInv.getCashier());
		
			    rs.close();
			    st.close();
			
		} catch (SQLException e) {								
			printInv.setItem(listINVCopy);
			printInv.setResponse(response);
		}finally{
			sqlDS.close();
		}   
		
		System.out.println("InvoiceNO : "+printInv.getDocNo());
		

	    response.setIsSuccess(true);
	    response.setProcess("Test");
	    response.setProcessDesc("Successful");
	    return printInv; 
		  
	}
	
	
	public IV_Resp_PrintSlipBean copySaleOrderHTML (DT_User_LoginBranchBean dbName,IV_Reqs_PrintSlipBean reqs){
		IV_Resp_PrintSlipBean copyInv = new IV_Resp_PrintSlipBean();
		List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList<IV_Resp_PrintSlipSubBean>();
		Date  billDocDate ;
		String pointDesc;
		double pointBal = 0;
		
		
		
		pointBal = 0;//data.calcPointInvoiceBranch(branch.getServerName(),branch.getDataBaseName(),bill.getBillHeader().getDocNo());
		
		
		pointDesc = "เอกสารนี้ได้จำนวนแต้ม :"+ pointBal+" แต้ม";
		
		try {
			Statement st = sqlDS.getSqlStatement(dbName.getServerName(), dbName.getDbName());
			
			vQuery = "exec dbo.USP_NP_InvoicePrintDetails 1, '"+reqs.getInvoiceNo()+"','"+reqs.getArCode()+"'";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			
			listInv.clear();
			while(rs.next()){
				
				billDocDate = rs.getDate("docdate");
				
				System.out.println("Bill Point"+rs.getInt("point"));
				
				printInv.setDocNo(rs.getString("docno"));
				printInv.setDocDate(billDocDate.toString());
				printInv.setCompanyName(rs.getString("companyname"));
				printInv.setTaxId(rs.getString("taxid"));
				printInv.setPosId(rs.getString("posid"));
				printInv.setCashier(rs.getString("cashiercode"));
				printInv.setSaleCode(rs.getString("salecode"));
				printInv.setBillTime(rs.getString("billtime"));
				printInv.setTotalAmount(rs.getDouble("totalamount"));
				printInv.setTax(rs.getInt("taxrate"));
				printInv.setTaxAmount(rs.getDouble("taxamount"));
				printInv.setCashAmount(rs.getDouble("sumcashamount"));
				printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
				printInv.setChange(rs.getDouble("changeamount"));
				printInv.setGreeting1(rs.getString("greeting1"));
				printInv.setGreeting2(rs.getString("greeting2"));
				printInv.setGreeting3(rs.getString("greeting3"));
				printInv.setGreeting4(rs.getString("greeting4"));
				printInv.setGreeting5(rs.getString("greeting5"));
				printInv.setRemark("");
				printInv.setPromotionDesc1(pointDesc);
				printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
				printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
				printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
				printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
				printInv.setPoint(rs.getInt("point"));

				

					IV_Resp_PrintSlipSubBean evt;
					evt = new IV_Resp_PrintSlipSubBean();
					evt.setItemCode(rs.getString("itemcode"));
					evt.setItemName(rs.getString("itemname"));
					evt.setQty(rs.getInt("qty"));
					evt.setPrice(rs.getDouble("price"));
					evt.setAmount(rs.getDouble("amount"));
					evt.setUnitCode(rs.getString("unitcode"));
					
					System.out.println("Bill Item"+rs.getString("itemcode"));
					listInv.add(evt);
					
				}
				response.setIsSuccess(true);
				response.setProcess("Search Print Slip");
				response.setProcessDesc("Successfully");
				printInv.setItem(listInv);
				printInv.setResponse(response);
				
				System.out.println("CashierCode : "+printInv.getCashier());
		
			    rs.close();
			    st.close();
			
		} catch (SQLException e) {								
			printInv.setItem(listINVCopy);
			printInv.setResponse(response);
		}finally{
			sqlDS.close();
		}   
		
		System.out.println("SaleOrder InvoiceNO : "+printInv.getDocNo());
		

	    response.setIsSuccess(true);
	    response.setProcess("Test");
	    response.setProcessDesc("Successful");
	    return printInv; 
		  
	}
	
	public int countBillAll(String vDocDate){
		int billAll=0;
		
		try {
			Statement st = sqlDS.getSqlStatement("POS");
			
			vQuery = "exec dbo.USP_DT_CountBillZoneDriveThru '" + vDocDate +"'";
			
			ResultSet rs = st.executeQuery(vQuery);
			
			while(rs.next()){
				billAll = rs.getInt("billall");
			}
			
		    rs.close();
		    st.close();
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			sqlDS.close();
		}
		
		return billAll;
	}
	
	public CT_Res_CompanyDataBean searchCompanyData(String company_code,String zone_id){
		CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		
		db.setServerName("192.168.0.7");
		db.setDatabaseName("bcnp");
		
		System.out.println(db.getServerName());
		
		try {
			Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDatabaseName());
			vQuery = "exec dbo.USP_NP_SearchCompanyZoneData '"+ company_code+"','"+zone_id+"'";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
		
			while(rs.next()){
				company.setBranch_code(rs.getString("branchcode"));
				company.setWh_code(rs.getString("whcode"));
				company.setShelf_code(rs.getString("shelfcode"));
			}
			response.setIsSuccess(true);
			response.setProcess("Company");
			response.setProcessDesc("Successfull");
			
			rs.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			response.setIsSuccess(false);
			response.setProcess("Company");
			response.setProcessDesc(e.getMessage());
		}finally{
			ds.close();
		}
		
		return company;
	}
	
	public SO_Res_VerifySaleOrderBean VerifySaleOrder(CT_Req_ServerDataBaseBean db,SO_Req_VerifySaleOrderBean req){
		//CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
		
		//db.setServerName("192.168.0.7");
		//db.setDatabaseName("bcnp");
		
		System.out.println(db.getServerName());
		
		try {
			Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDatabaseName());
			vQuery = "exec dbo.USP_API_VerifySaleOrderItemNotBilling 'np','drivethru'";
			System.out.println(vQuery);
			ResultSet rs = st.executeQuery(vQuery);
		
			while(rs.next()){
				item_veryfy.setAr_code(rs.getString("arcode"));
				item_veryfy.setBill_type(rs.getInt("billtype"));
				item_veryfy.setDeliver_type(rs.getInt("isconditionsend"));
				item_veryfy.setDoc_date(rs.getString("docdate"));
				item_veryfy.setDoc_no(rs.getString("docno"));
				item_veryfy.setItem_rate(rs.getDouble("packingrate1"));
				item_veryfy.setRemain_qty(rs.getDouble("remainqty"));
				item_veryfy.setSale_code(rs.getString("salecode"));
				item_veryfy.setSo_qty(rs.getDouble("qty"));
			}
			response.setIsSuccess(true);
			response.setProcess("Verify SaleOrder");
			response.setProcessDesc("Successfull");
			
			rs.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			response.setIsSuccess(false);
			response.setProcess("Verify SaleOrder");
			response.setProcessDesc(e.getMessage());
		}finally{
			ds.close();
		}
		
		return item_veryfy;
	}

	public CT_Resp_ResponseBean verifyEditItemSaleOrder(DT_User_LoginBranchBean db,SO_Req_EditSaleOrderBean item){
		int counterr=0;
		int checkexist=0;
		double remainqty=0;
		
		//CT_Req_ServerDataBaseBean dbName = new CT_Req_ServerDataBaseBean();
	
		//dbName.setServerName("192.168.0.7");
		//dbName.setDatabaseName("nptest");
		
		PK_Resp_GetDataQueue queue_id;
		
		queue_id = this.searchQueue(item.getQueue_id());
		
		if(item.getItem().size()!=0){
			for(int m=0;m<item.getItem().size();m++){
				if (item.getItem().get(m).getItem_code()!="" && item.getItem().get(m).getItem_code()!= null){
					if (item.getItem().get(m).getRequest_qty()!= 0){
						try{
							Statement st = sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
							vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '"+queue_id.getSaleOrder()+"' and itemcode = '"+item.getItem().get(m).getItem_code()+"' and unitcode ='"+item.getItem().get(m).getItem_unit_code()+"' and linenumber ="+item.getItem().get(m).getLine_number()+" and iscancel = 0";
							ResultSet rs = st.executeQuery(vQuery);
							
							System.out.println("Verify Item :"+vQuery);
							while(rs.next()){
								remainqty = rs.getDouble("remainqty");
							}
							
							System.out.println("remainqty,item.getItem().get(m).getRequest_qty()=="+remainqty+","+item.getItem().get(m).getRequest_qty());
							
							if (remainqty > 0 && remainqty<item.getItem().get(m).getRequest_qty()){
								counterr++;
								
								System.out.println("Count Error ="+counterr);
							}
							
						    rs.close();
						    st.close();
						    
						}catch(SQLException e){
							e.printStackTrace();
							counterr++;
						}finally{
							sqlDS.close();
						}
						
					}//else{
//						counterr++;
//						//error Amount;
//					}
						
				}else{
					counterr++;
					//error Code
				}
			}
		}
		
		System.out.println("itemsize :"+item.getItem().size()+","+counterr+","+checkexist);
		
		if(counterr==0){
			itemRes.setIsSuccess(true);
			itemRes.setProcess("verifyItem");
			itemRes.setProcessDesc("Item is pass");
		}else{

			itemRes.setIsSuccess(false);
			itemRes.setProcess("verifyDeposit");
			itemRes.setProcessDesc("Item data error");
		}
		
		return itemRes;
	}
	
	
	public String GenPinOTP(){
		  int x = (int)(Math.random() * 9);
		    x = x + 1;
		    String randomPIN = (x + "") + ( ((int)(Math.random()*1000)) + "" );
		    return randomPIN;
	}
	
	public String EnCoding(String myPassword){
		BASE64Encoder encoder = new BASE64Encoder();
		String afterhex=bcrypt.toSHA1(myPassword.getBytes());
		String encodedBytes = encoder.encodeBuffer(afterhex.getBytes());
//		System.out.println("myPassword : "+myPassword);
//		System.out.println("afterhex : "+afterhex);
//		System.out.println("encodedBytes : "+encodedBytes);
		
		return encodedBytes;
	}
	
	
	
	
//	public String UserMoveFile(String userCode)
//	{	
//	    File file = new File("filename");
//	    
//	    // Destination directory
//	    File dir = new File("directoryname");
//	    
//	    // Move file to new directory
//	    boolean success = file.renameTo(new File(dir, file.getName()));
//	    if (!success) {
//	        // File was not successfully moved
//	    }
//    
//	}
}
	

