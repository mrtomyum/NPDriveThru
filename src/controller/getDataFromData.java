package controller;

import bean.CK_Resp_AllSummaryBean;
import bean.CM_Resp_MemberListBean;
import bean.IV_Reqs_CouponBean;
import bean.IV_Reqs_CreditCardBean;
import bean.IV_Reqs_PrintSlipBean;
import bean.IV_Resp_ARInvoiceSubBean;
import bean.IV_Resp_PrintSlipBean;
import bean.IV_Resp_PrintSlipSubBean;
import bean.LoginBean;
import bean.PK_Reqs_ManageItemBean;
import bean.PK_Resp_GetDataQueue;
import bean.PK_Resp_GetItemBarcodeBean;
import bean.PK_Resp_SaleCodeDetails;
import bean.User_Resp_CheckDataAccessTokenBean;
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
import bean.response.SO_Res_CheckDataSOBean;
import bean.response.SO_Res_CheckQueueItemBean;
import bean.response.SO_Res_ItemSaleOrderBean;
import bean.response.SO_Res_ListProductQueueBean;
import bean.response.SO_Res_ListSaleOrderItemBean;
import bean.response.SO_Res_QueuePickupBean;
import connect.NPSQLConn;
import connect.QueueConnect;
import connect.S01PosConn;
import connect.SQLConn;
import connect.bCryptPassword;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sun.misc.BASE64Encoder;


public class getDataFromData {
	String vQuery;
    String vQuerySub;
    String vDocNo;
    String vGenNewDocNo;
    int getID;
    int getInspectID;
    private final QueueConnect ds;
    private final SQLConn sqlDS;
    private final QueueConnect dsTK;
    private final NPSQLConn npDS;
    private final S01PosConn posDs;
    private SimpleDateFormat dtDoc;
    private SimpleDateFormat dt;
    
    private final SQLConn conn = SQLConn.INSTANCE;
    NPSQLExecController npExec = new NPSQLExecController();
    
    DateFormat dateFormat;
    PK_Resp_GetItemBarcodeBean resItem;
    PK_Resp_GetDataQueue resQue;
    CK_Resp_AllSummaryBean allSum;
    CM_Resp_MemberListBean ar;
    IV_Resp_ARInvoiceSubBean queItem;
    List<IV_Resp_ARInvoiceSubBean> itemlist;
    CT_Resp_ResponseBean creditcardRes;
    CT_Resp_ResponseBean coupongRes;
    CT_Resp_ResponseBean depositRes;
    CT_Resp_ResponseBean itemRes;
    DT_User_LoginBranchBean connData;
    PK_Resp_SaleCodeDetails sale;
    LoginBean userCode;
    User_Resp_CheckDataAccessTokenBean branch;
    SO_Res_CheckDataSOBean SO;
    CT_Res_CompanyDataBean company;
    SO_Res_VerifySaleOrderBean item_veryfy;
    SO_Res_ItemSaleOrderBean sale_item;
    bCryptPassword bcrypt;
    IV_Resp_PrintSlipBean printInv;
    List<IV_Resp_PrintSlipSubBean> listInv;
    CT_Resp_ResponseBean response;
    private boolean isSuccess;
    ExcecuteController excecute;
    
    //getDataFromData data = new getDataFromData();
    
    
    public getDataFromData() {
        this.ds = QueueConnect.INSTANCE;
        this.sqlDS = SQLConn.INSTANCE;
        this.dsTK = QueueConnect.INSTANCE;
        this.npDS = NPSQLConn.INSTANCE;
        this.posDs = S01PosConn.INSTANCE;
        this.dtDoc = new SimpleDateFormat();
        this.dt = new SimpleDateFormat();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.resItem = new PK_Resp_GetItemBarcodeBean();
        this.resQue = new PK_Resp_GetDataQueue();
        this.allSum = new CK_Resp_AllSummaryBean();
        this.ar = new CM_Resp_MemberListBean();
        this.queItem = new IV_Resp_ARInvoiceSubBean();
        this.itemlist = new ArrayList();
        this.creditcardRes = new CT_Resp_ResponseBean();
        this.coupongRes = new CT_Resp_ResponseBean();
        this.depositRes = new CT_Resp_ResponseBean();
        this.itemRes = new CT_Resp_ResponseBean();
        this.connData = new DT_User_LoginBranchBean();
        this.sale = new PK_Resp_SaleCodeDetails();
        this.userCode = new LoginBean();
        this.branch = new User_Resp_CheckDataAccessTokenBean();
        this.SO = new SO_Res_CheckDataSOBean();
        this.company = new CT_Res_CompanyDataBean();
        this.item_veryfy = new SO_Res_VerifySaleOrderBean();
        this.sale_item = new SO_Res_ItemSaleOrderBean();
        this.bcrypt = new bCryptPassword();
        this.printInv = new IV_Resp_PrintSlipBean();
        this.listInv = new ArrayList();
        this.response = new CT_Resp_ResponseBean();
        this.excecute = new ExcecuteController();
    }
    
    
    public PK_Resp_GetItemBarcodeBean searchItemCode(String barcode) {
        try {
            Statement st = this.sqlDS.getSqlStatement("bcnp");
            this.vQuery = "exec dbo.USP_DT_SearchBarCodeDetails '" + barcode + "'";
            System.out.println("Search BarCode " + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resItem.setCode(rs.getString("itemcode"));
                this.resItem.setRate1(rs.getInt("rate"));
                this.resItem.setUnitCode(rs.getString("unitcode"));
                this.resItem.setItemName(rs.getString("barcodename"));
                this.resItem.setExpertCode(rs.getString("expertcode"));
                this.resItem.setDepartmentCode(rs.getString("departmentcode"));
                this.resItem.setDepartmentName(rs.getString("departname"));
                this.resItem.setCategoryCode(rs.getString("categorycode"));
                this.resItem.setCategoryName(rs.getString("category"));
                this.resItem.setSecCode(rs.getString("secCode"));
                this.resItem.setSecName(rs.getString("secName"));
                this.resItem.setAverageCost(rs.getDouble("averagecost"));
                this.resItem.setZoneId(rs.getString("zoneid"));
            }

            rs.close();
            st.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.sqlDS.close();
        }

        return this.resItem;
    }

    public PK_Resp_GetItemBarcodeBean searchItemCodeBranch(DT_User_LoginBranchBean db, String barcode) {
        try {
            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
            this.vQuery = "exec dbo.USP_DT_SearchBarCodeDetails '" + barcode + "'";
            System.out.println("Search BarCode " + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resItem.setCode(rs.getString("itemcode"));
                this.resItem.setRate1(rs.getInt("rate"));
                this.resItem.setUnitCode(rs.getString("unitcode"));
                this.resItem.setItemName(rs.getString("barcodename"));
                this.resItem.setExpertCode(rs.getString("expertcode"));
                this.resItem.setDepartmentCode(rs.getString("departmentcode"));
                this.resItem.setDepartmentName(rs.getString("departname"));
                this.resItem.setCategoryCode(rs.getString("categorycode"));
                this.resItem.setCategoryName(rs.getString("category"));
                this.resItem.setSecCode(rs.getString("secCode"));
                this.resItem.setSecName(rs.getString("secName"));
                this.resItem.setAverageCost(rs.getDouble("averagecost"));
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            this.sqlDS.close();
        }

        return this.resItem;
    }

    public PK_Resp_GetDataQueue searchQueue(int qId) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(saleorder,'') as saleorder,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus,isload from Queue where qId = " + qId + " and docdate = CURDATE() limit 1";
            System.out.println("searchQueue :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resQue.setDocNo(rs.getString("docno"));
                this.resQue.setIsCancel(rs.getInt("iscancel"));
                this.resQue.setStatus(rs.getInt("status"));
                this.resQue.setCarLicense(rs.getString("carLicence"));
                this.resQue.setSaleCode(rs.getString("saleCode"));
                this.resQue.setOtp_password(rs.getString("otpcode"));
                this.resQue.setDelivery_type(rs.getInt("deliverytype"));
                this.resQue.setBilltype(rs.getInt("billtype"));
                this.resQue.setDoctype(rs.getInt("doctype"));
                this.resQue.setTaxType(rs.getInt("taxType"));
                this.resQue.setPickStatus(rs.getInt("pickStatus"));
                this.resQue.setSaleOrder(rs.getString("saleorder"));
                this.resQue.setIsLoad(rs.getInt("isload"));
            }

            rs.close();
            st.close();
            System.out.println("GetDocno :" + this.vQuery);
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.resQue;
    }

    public PK_Resp_GetDataQueue searchQueueBranch(String db, int qId) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement(db);
            this.vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where qId = " + qId + " and docdate = CURDATE() limit 1";
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resQue.setDocNo(rs.getString("docno"));
                this.resQue.setIsCancel(rs.getInt("iscancel"));
                this.resQue.setStatus(rs.getInt("status"));
                this.resQue.setCarLicense(rs.getString("carLicence"));
                this.resQue.setSaleCode(rs.getString("saleCode"));
                this.resQue.setOtp_password(rs.getString("otpcode"));
                this.resQue.setDelivery_type(rs.getInt("deliverytype"));
                this.resQue.setBilltype(rs.getInt("billtype"));
                this.resQue.setDoctype(rs.getInt("doctype"));
                this.resQue.setTaxType(rs.getInt("taxType"));
                this.resQue.setPickStatus(rs.getInt("pickStatus"));
            }

            rs.close();
            st.close();
            System.out.println("GetDocno :" + this.vQuery);
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.resQue;
    }

    public PK_Resp_GetDataQueue searchSaleOrder(String docno) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where saleorder = '" + docno + "' and docdate = CURDATE() limit 1";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resQue.setDocNo(rs.getString("docno"));
                this.resQue.setIsCancel(rs.getInt("iscancel"));
                this.resQue.setStatus(rs.getInt("status"));
                this.resQue.setCarLicense(rs.getString("carLicence"));
                this.resQue.setSaleCode(rs.getString("saleCode"));
                this.resQue.setOtp_password(rs.getString("otpcode"));
                this.resQue.setDelivery_type(rs.getInt("deliverytype"));
                this.resQue.setBilltype(rs.getInt("billtype"));
                this.resQue.setDoctype(rs.getInt("doctype"));
                this.resQue.setTaxType(rs.getInt("taxType"));
                this.resQue.setPickStatus(rs.getInt("pickStatus"));
            }

            rs.close();
            st.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.resQue;
    }

    public PK_Resp_GetDataQueue searchSaleOrderBranch(String db, String docno) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement(db);
            this.vQuery = "select docno,iscancel,status,carLicence,salecode,ifnull(otpcode,'') as otpcode,ifnull(deliverytype,0) as deliverytype,ifnull(billtype,0) as billtype,ifnull(doctype,0) as doctype,ifnull(taxtype,1) as taxtype,ifnull(pickStatus,0) as pickStatus from Queue where saleorder = '" + docno + "' and docdate = CURDATE() limit 1";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resQue.setDocNo(rs.getString("docno"));
                this.resQue.setIsCancel(rs.getInt("iscancel"));
                this.resQue.setStatus(rs.getInt("status"));
                this.resQue.setCarLicense(rs.getString("carLicence"));
                this.resQue.setSaleCode(rs.getString("saleCode"));
                this.resQue.setOtp_password(rs.getString("otpcode"));
                this.resQue.setDelivery_type(rs.getInt("deliverytype"));
                this.resQue.setBilltype(rs.getInt("billtype"));
                this.resQue.setDoctype(rs.getInt("doctype"));
                this.resQue.setTaxType(rs.getInt("taxType"));
                this.resQue.setPickStatus(rs.getInt("pickStatus"));
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.resQue;
    }

    public PK_Resp_GetDataQueue searchQueueBranch(String db, int qId, String branchCode) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement(db);
            this.vQuery = "call USP_DT_SearchQueueStatus ('" + branchCode + "'," + qId + ")";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.resQue.setDocNo(rs.getString("docno"));
                this.resQue.setIsCancel(rs.getInt("iscancel"));
                this.resQue.setStatus(rs.getInt("status"));
                this.resQue.setCarLicense(rs.getString("carLicence"));
                this.resQue.setSaleCode(rs.getString("saleCode"));
            }

            rs.close();
            st.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.resQue;
    }

    public PK_Resp_SaleCodeDetails searchTopSaleCode(int getQ) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        this.sale.setIsExist(0);
        this.sale.setSaleCode("");
        this.sale.setSaleName("");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SelectTopSaleCode (" + getQ + ")";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.sale.setIsExist(1);
                this.sale.setSaleCode(rs.getString("code"));
                this.sale.setSaleName(rs.getString("name"));
            }

            rs.close();
            st.close();
        } catch (Exception var7) {
            this.sale.setIsExist(0);
            this.sale.setSaleCode("");
            this.sale.setSaleName("");
            var7.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.sale;
    }

    public PK_Resp_SaleCodeDetails searchTopSaleCodeBranch(String branchCode, int getQ) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        this.sale.setIsExist(0);
        this.sale.setSaleCode("");
        this.sale.setSaleName("");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SelectTopSaleCodeBranch (" + getQ + ",'" + this.branch.getBranchCode() + "')";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.sale.setIsExist(1);
                this.sale.setSaleCode(rs.getString("code"));
                this.sale.setSaleName(rs.getString("name"));
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            this.sale.setIsExist(0);
            this.sale.setSaleCode("");
            this.sale.setSaleName("");
            var8.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.sale;
    }

    public CK_Resp_AllSummaryBean searchQueueSummary(int qId) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select sum(pickqty*price) as totalBeforeAmount,sum(checkoutqty*price) as totalAfterAmount  from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = " + qId + " and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.allSum.setTotalBeforePrice(rs.getDouble("totalBeforeAmount"));
                this.allSum.setTotalAfterPrice(rs.getDouble("totalAfterAmount"));
            }

            rs.close();
            st.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.allSum;
    }

    public CK_Resp_AllSummaryBean searchQueueSummaryBranch(int qId, String branchCode) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SearchQueueSummary ('" + branchCode + "'," + qId + ")";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.allSum.setTotalBeforePrice(rs.getDouble("totalBeforeAmount"));
                this.allSum.setTotalAfterPrice(rs.getDouble("totalAfterAmount"));
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.allSum;
    }

    public double searchQueueCheckOutAmount(int qId) {
        double totalAmount = 0.0D;
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select sum(checkoutqty*price) as totalamount from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno  where a.qId = " + qId + " and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
            System.out.println("GetDocno :" + this.vQuery);

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); totalAmount = rs.getDouble("totalamount")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            this.ds.close();
        }

        return totalAmount;
    }

    public double searchQueueCheckOutAmountBranch(int qId, String branchCode) {
        double totalAmount = 0.0D;
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SearchQueueCheckOutAmount ('" + branchCode + "'," + qId + ")";
            System.out.println("GetDocno :" + this.vQuery);

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); totalAmount = rs.getDouble("totalamount")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.ds.close();
        }

        return totalAmount;
    }

    public List<IV_Resp_ARInvoiceSubBean> searchQueueCheckOutItem(int qId) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select a.docno,a.docdate,b.itemcode,b.barcode,b.itemName as name1,b.unitCode,b.checkoutQty,b.price,b.whCode,b.shelfCode,b.rate1,ifnull(b.averageCost,0) as averagecost,ifnull(b.checkoutAmount,0) as checkoutAmount,b.salecode from Queue a inner join QItem b on a.qid = b.qid and a.docno = b.docno where a.qId = " + qId + " and a.docdate = CURDATE() and a.iscancel = 0 and b.iscancel = 0 and b.isCheckOut = 1 ";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);
            this.itemlist.clear();

            while(rs.next()) {
                IV_Resp_ARInvoiceSubBean evt = new IV_Resp_ARInvoiceSubBean();
                evt.setItemCode(rs.getString("itemcode"));
                System.out.println("Item CheckOut :" + rs.getString("itemcode"));
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
                this.itemlist.add(evt);
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            this.ds.close();
        }

        System.out.println("Item Qty : " + this.itemlist.size());
        return this.itemlist;
    }

    public List<IV_Resp_ARInvoiceSubBean> searchQueueCheckOutItemBranch(int qId, String branchCode) {
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SearchQueueCheckOutItem  ('" + branchCode + "'," + qId + ")";
            System.out.println("GetDocno :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);
            this.itemlist.clear();

            while(rs.next()) {
                IV_Resp_ARInvoiceSubBean evt = new IV_Resp_ARInvoiceSubBean();
                evt.setItemCode(rs.getString("itemcode"));
                System.out.println("Item CheckOut :" + rs.getString("itemcode"));
                evt.setItemName(rs.getString("name1"));
                evt.setBarCode(rs.getString("barcode"));
                evt.setQty(rs.getDouble("checkoutQty"));
                evt.setPrice(rs.getDouble("price"));
                evt.setUnitCode(rs.getString("unitcode"));
                evt.setPackingRate1(rs.getInt("rate1"));
                evt.setSaleCode(rs.getString("saleCode"));
                evt.setSumOfCost(rs.getDouble("averagecost"));
                this.itemlist.add(evt);
            }

            rs.close();
            st.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            this.ds.close();
        }

        System.out.println("Item Qty : " + this.itemlist.size());
        return this.itemlist;
    }

    public LoginBean searchUserAccessToken(String access_token) {
        try {
            Statement st = this.ds.getStatement("SmartConfig");
            this.vQuery = "select u.code,u.name,u.role from  User as u inner join UserAccess as ua on u.code=ua.userCode and ua.userUUID='" + access_token + "'" + " order by ua.dateTimeStamp DESC LIMIT 1";
            System.out.println("SaleCode = " + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.userCode.setEmployeeCode(rs.getString("code"));
                this.userCode.setEmployeeName(rs.getString("name"));
                this.userCode.setServer_name("");
                this.userCode.setDatabase_name("");
            }

            rs.close();
            st.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            this.ds.close();
        }

        return this.userCode;
    }

    public PK_Resp_SaleCodeDetails searchSaleCode(String saleCode) {
        try {
            this.sale.setIsExist(0);
            this.sale.setSaleCode("");
            this.sale.setSaleName("");
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SearchSale ('" + saleCode + "')";
            System.out.println("Search Sale :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.sale.setIsExist(1);
                this.sale.setSaleCode(rs.getString("code"));
                this.sale.setSaleName(rs.getString("name"));
            }

            rs.close();
            st.close();
        } catch (SQLException var7) {
            this.sale.setIsExist(0);
            this.sale.setSaleCode("");
            this.sale.setSaleName("");
        } finally {
            this.ds.close();
        }

        return this.sale;
    }

    public int checkItemExistQueue(PK_Reqs_ManageItemBean reqItem) {
        new PK_Resp_GetItemBarcodeBean();
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        int vExist = 0;
        PK_Resp_GetItemBarcodeBean getItemCode = this.searchItemCode(reqItem.getBarCode());

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "select ifnull(count(itemcode),0) as vCount from QItem where docDate = CURDATE() and qId='" + reqItem.getqId() + "' and itemcode = '" + getItemCode.getCode() + "' and unitcode = '" + getItemCode.getUnitCode() + "' ";
            System.out.println("ExistQueue :" + this.vQuery);

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); vExist = rs.getInt("vCount")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            this.ds.close();
        }

        return vExist;
    }

    public boolean verifySaleOrderQty(List<SO_Req_ListEditItemSaleOrderBean> req) {
        boolean qtyError = true;
        return qtyError;
    }

    public SO_Res_CheckQueueItemBean checkItemExistPickupProduct(SO_Req_PickingManageProductBean reqItem) {
        new PK_Resp_GetItemBarcodeBean();
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        int vExist = 0;
        SO_Res_CheckQueueItemBean itemexist = new SO_Res_CheckQueueItemBean();
        PK_Resp_GetItemBarcodeBean getItemCode = this.searchItemCode(reqItem.getItem_barcode());

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_CheckItemExist ('" + reqItem.getQueue_id() + "','" + getItemCode.getCode() + "','" + getItemCode.getUnitCode() + "') ";
            System.out.println("ExistQueue :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.ds.close();
        }

        return itemexist;
    }
    
    
    public SO_Res_CheckQueueItemBean checkItemExistLoadProduct(int que_id, String bar_code, int line_number) {
        new PK_Resp_GetItemBarcodeBean();
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        int vExist = 0;
        SO_Res_CheckQueueItemBean itemexist = new SO_Res_CheckQueueItemBean();
        PK_Resp_GetItemBarcodeBean getItemCode = this.searchItemCode(bar_code);

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_CheckItemSOData (" + que_id + ",'" + getItemCode.getCode() + "'," +line_number + ") ";
            System.out.println("ExistQueue :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
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
                itemexist.setQty_before(rs.getDouble("loadQty"));
                itemexist.setBefore_amount(rs.getDouble("itemamount"));
                itemexist.setQty_after(rs.getDouble("checkoutQty"));
                itemexist.setAfter_amount(rs.getDouble("checkoutAmount"));
                itemexist.setBill_type(rs.getInt("billtype"));
                itemexist.setSale_qty(rs.getDouble("qty"));
            }

            rs.close();
            st.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.ds.close();
        }

        return itemexist;
    }

    public SO_Res_CheckQueueItemBean checkItemExistCheckOutProduct(SO_Req_CheckOutManageProductBean reqItem) {
        new PK_Resp_GetItemBarcodeBean();
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        int vExist = 0;
        SO_Res_CheckQueueItemBean itemexist = new SO_Res_CheckQueueItemBean();
        PK_Resp_GetItemBarcodeBean getItemCode = this.searchItemCode(reqItem.getItem_barcode());

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_CheckItemExist ('" + reqItem.getQueue_id() + "','" + getItemCode.getCode() + "','" + getItemCode.getUnitCode() + "') ";
            System.out.println("ExistQueue :" + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.ds.close();
        }

        return itemexist;
    }

    public int checkItemExistQueueBranch(String branchCode, PK_Reqs_ManageItemBean reqItem) {
        new PK_Resp_GetItemBarcodeBean();
        this.dtDoc.applyPattern("yyyy-MM-dd");
        this.dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
        int vExist = 0;
        PK_Resp_GetItemBarcodeBean getItemCode = this.searchItemCode(reqItem.getBarCode());

        try {
            Statement st = this.ds.getStatement("SmartQ");
            this.vQuery = "call USP_DT_SearchItemExistQueue ('" + branchCode + "'," + reqItem.getqId() + ",'" + getItemCode.getCode() + "','" + getItemCode.getUnitCode() + "')";
            System.out.println("ExistQueue :" + this.vQuery);

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); vExist = rs.getInt("vCount")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.ds.close();
        }

        return vExist;
    }

    public double searchItemPrice(String itemCode, String barCode, String unitCode) {
        double price = 0.0D;

        try {
            Statement st = this.sqlDS.getSqlStatement("bcnp");
            this.vQuery = "exec dbo.USP_DT_SearchItemPrice '" + itemCode + "','" + unitCode + "'";
            System.out.println("Check Price :" + this.vQuery);

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); price = rs.getDouble("price")) {
                ;
            }

            rs.close();
            st.close();
        } catch (SQLException var11) {
            var11.printStackTrace();
            price = 0.0D;
        } finally {
            this.sqlDS.close();
        }

        return price;
    }

    public CM_Resp_MemberListBean searchCustomerName(String arCode) {
        try {
            Statement st = this.sqlDS.getSqlStatement("POS");
            this.vQuery = "exec dbo.USP_DT_SearchCustomerData '" + arCode + "'";
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.ar.setArName(rs.getString("name1"));
                this.ar.setArAddress(rs.getString("billAddress"));
                this.ar.setArId(rs.getString("taxid"));
                this.ar.setArPhone(rs.getString("telephone"));
                this.ar.setArPoint(rs.getDouble("point"));
            }

            rs.close();
            st.close();
        } catch (SQLException var7) {
            var7.printStackTrace();
            this.ar.setArName("");
            this.ar.setArAddress("");
            this.ar.setArId("");
            this.ar.setArPhone("");
            this.ar.setArPoint(0.0D);
        } finally {
            this.sqlDS.close();
        }

        return this.ar;
    }

    public CT_Resp_ResponseBean validateCreditCard(List<IV_Reqs_CreditCardBean> crdCard) {
        int counterr = 0;
        int isUsed = 0;
        if (crdCard.size() != 0) {
            for(int x = 0; x < crdCard.size(); ++x) {
                System.out.println("carditcard No :" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo());
                if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != null) {
                    if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getAmount() == 0.0D) {
                        ++counterr;
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("CreditAmount = 0");
                    } else if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != null) {
                        if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != null) {
                            if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != null) {
                                try {
                                    Statement st = this.sqlDS.getSqlStatement("POS");
                                    this.vQuery = "set dateformat dmy select count(ConfirmNo) as vCount from dbo.bccreditcard where CreditCardNo = '" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() + "' and ConfirmNo = '" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() + "'";
                                    ResultSet rs = st.executeQuery(this.vQuery);
                                    System.out.println("Verify CreditCard :" + this.vQuery);

                                    while(rs.next()) {
                                        isUsed = rs.getInt("vCount");
                                    }

                                    if (isUsed == 0) {
                                        System.out.println("CreditCard is true = " + isUsed);
                                        this.creditcardRes.setIsSuccess(true);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is ok");
                                    } else {
                                        System.out.println("CreditCard is false = " + isUsed);
                                        this.creditcardRes.setIsSuccess(false);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is used");
                                    }

                                    rs.close();
                                    st.close();
                                } catch (SQLException var10) {
                                    ++counterr;
                                    this.creditcardRes.setIsSuccess(false);
                                    this.creditcardRes.setProcess("validate creditcard");
                                    this.creditcardRes.setProcessDesc(var10.getMessage());
                                } finally {
                                    this.sqlDS.close();
                                }
                            } else {
                                ++counterr;
                                this.creditcardRes.setIsSuccess(false);
                                this.creditcardRes.setProcess("validate creditcard");
                                this.creditcardRes.setProcessDesc("CreditType is null or empty");
                            }
                        } else {
                            ++counterr;
                            this.creditcardRes.setIsSuccess(false);
                            this.creditcardRes.setProcess("validate creditcard");
                            this.creditcardRes.setProcessDesc("ConfirmNo is null or empty");
                        }
                    } else {
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("Bank is null or empty");
                    }
                } else {
                    ++counterr;
                    this.creditcardRes.setIsSuccess(false);
                    this.creditcardRes.setProcess("validate creditcard");
                    this.creditcardRes.setProcessDesc("CreditcardNo is null or empty");
                }
            }
        }

        return this.creditcardRes;
    }

    public CT_Resp_ResponseBean validateDataCreditCard(String serverName, String dataBaseName, List<IV_Reqs_CreditCardBean> crdCard) {
        int counterr = 0;
        int isUsed = 0;
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);
        if (crdCard.size() != 0) {
            for(int x = 0; x < crdCard.size(); ++x) {
                System.out.println("carditcard No :" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo());
                if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != null) {
                    if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getAmount() == 0.0D) {
                        ++counterr;
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("CreditAmount = 0");
                    } else if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != null) {
                        if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != null) {
                            if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != null) {
                                try {
                                    Statement st = this.npDS.getSqlStatementBranch(this.connData);
                                    this.vQuery = "exec dbo.USP_DT_ValidateCreditCard '" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() + "','" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() + "'";
                                    ResultSet rs = st.executeQuery(this.vQuery);
                                    System.out.println("Verify Coupong :" + this.vQuery);

                                    while(rs.next()) {
                                        isUsed = rs.getInt("vCount");
                                    }

                                    if (isUsed == 0) {
                                        this.creditcardRes.setIsSuccess(true);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is ok");
                                    } else {
                                        this.creditcardRes.setIsSuccess(false);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is used");
                                    }

                                    rs.close();
                                    st.close();
                                } catch (SQLException var12) {
                                    ++counterr;
                                    this.creditcardRes.setIsSuccess(false);
                                    this.creditcardRes.setProcess("validate creditcard");
                                    this.creditcardRes.setProcessDesc(var12.getMessage());
                                } finally {
                                    this.npDS.close();
                                }
                            } else {
                                ++counterr;
                                this.creditcardRes.setIsSuccess(false);
                                this.creditcardRes.setProcess("validate creditcard");
                                this.creditcardRes.setProcessDesc("CreditType is null or empty");
                            }
                        } else {
                            ++counterr;
                            this.creditcardRes.setIsSuccess(false);
                            this.creditcardRes.setProcess("validate creditcard");
                            this.creditcardRes.setProcessDesc("ConfirmNo is null or empty");
                        }
                    } else {
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("Bank is null or empty");
                    }
                } else {
                    ++counterr;
                    this.creditcardRes.setIsSuccess(false);
                    this.creditcardRes.setProcess("validate creditcard");
                    this.creditcardRes.setProcessDesc("CreditcardNo is null or empty");
                }
            }
        }

        return this.creditcardRes;
    }

    public CT_Resp_ResponseBean validateDataCreditCard_SO(String serverName, String dataBaseName, List<SO_Reqs_CreditCardBean> crdCard) {
        int counterr = 0;
        int isUsed = 0;
        int checkdulpicate = 0;
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);
        if (crdCard.size() != 0) {
            for(int x = 0; x < crdCard.size(); ++x) {
                System.out.println("carditcard No :" + ((SO_Reqs_CreditCardBean)crdCard.get(x)).getCard_no());

                for(int d = 0; d < crdCard.size(); ++d) {
                    if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getBank_code().equals(((SO_Reqs_CreditCardBean)crdCard.get(d)).getBank_code()) && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getCard_no().equals(((SO_Reqs_CreditCardBean)crdCard.get(d)).getCard_no()) && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getConfirm_no().equals(((SO_Reqs_CreditCardBean)crdCard.get(d)).getConfirm_no())) {
                        ++checkdulpicate;
                    }
                }

                if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getCard_no() != "" && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getCard_no() != null) {
                    if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getAmount() != 0.0D && checkdulpicate <= 1) {
                        if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getBank_code() != "" && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getBank_code() != null) {
                            if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getConfirm_no() != "" && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getConfirm_no() != null) {
                                if (((SO_Reqs_CreditCardBean)crdCard.get(x)).getCredit_type() != "" && ((SO_Reqs_CreditCardBean)crdCard.get(x)).getCredit_type() != null) {
                                    try {
                                        Statement st = this.npDS.getSqlStatementBranch(this.connData);
                                        this.vQuery = "exec dbo.USP_DT_ValidateCreditCard '" + ((SO_Reqs_CreditCardBean)crdCard.get(x)).getCard_no() + "','" + ((SO_Reqs_CreditCardBean)crdCard.get(x)).getConfirm_no() + "'";
                                        ResultSet rs = st.executeQuery(this.vQuery);
                                        System.out.println("Verify Coupong :" + this.vQuery);

                                        while(rs.next()) {
                                            isUsed = rs.getInt("vCount");
                                        }

                                        if (isUsed == 0) {
                                            this.creditcardRes.setIsSuccess(true);
                                            this.creditcardRes.setProcess("validate creditcard");
                                            this.creditcardRes.setProcessDesc("CreditCard is ok");
                                        } else {
                                            this.creditcardRes.setIsSuccess(false);
                                            this.creditcardRes.setProcess("validate creditcard");
                                            this.creditcardRes.setProcessDesc("CreditCard is used");
                                        }

                                        rs.close();
                                        st.close();
                                    } catch (SQLException var13) {
                                        ++counterr;
                                        this.creditcardRes.setIsSuccess(false);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc(var13.getMessage());
                                    } finally {
                                        this.npDS.close();
                                    }
                                } else {
                                    ++counterr;
                                    this.creditcardRes.setIsSuccess(false);
                                    this.creditcardRes.setProcess("validate creditcard");
                                    this.creditcardRes.setProcessDesc("CreditType is null or empty");
                                }
                            } else {
                                ++counterr;
                                this.creditcardRes.setIsSuccess(false);
                                this.creditcardRes.setProcess("validate creditcard");
                                this.creditcardRes.setProcessDesc("ConfirmNo is null or empty");
                            }
                        } else {
                            this.creditcardRes.setIsSuccess(false);
                            this.creditcardRes.setProcess("validate creditcard");
                            this.creditcardRes.setProcessDesc("Bank is null or empty");
                        }
                    } else {
                        ++counterr;
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("CreditAmount = 0 or Data CreditCard Duplicate");
                    }
                } else {
                    ++counterr;
                    this.creditcardRes.setIsSuccess(false);
                    this.creditcardRes.setProcess("validate creditcard");
                    this.creditcardRes.setProcessDesc("CreditcardNo is null or empty");
                }
            }
        }

        return this.creditcardRes;
    }

    public CT_Resp_ResponseBean validateCreditCardBranch(String branchCode, String serverName, String dataBaseName, List<IV_Reqs_CreditCardBean> crdCard) {
        int counterr = 0;
        int isUsed = 0;
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);
        if (crdCard.size() != 0) {
            for(int x = 0; x < crdCard.size(); ++x) {
                System.out.println("carditcard No :" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo());
                if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() != null) {
                    if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getAmount() == 0.0D) {
                        ++counterr;
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("CreditAmount = 0");
                    } else if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getBankCode() != null) {
                        if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() != null) {
                            if (((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != "" && ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCreditType() != null) {
                                try {
                                    Statement st = this.npDS.getSqlStatementBranch(this.connData);
                                    this.vQuery = "exec dbo.USP_DT_ValidateCreditCard '" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getCardNo() + "','" + ((IV_Reqs_CreditCardBean)crdCard.get(x)).getConfirmNo() + "'";
                                    ResultSet rs = st.executeQuery(this.vQuery);
                                    System.out.println("Verify Coupong :" + this.vQuery);

                                    while(rs.next()) {
                                        isUsed = rs.getInt("vCount");
                                    }

                                    if (isUsed == 0) {
                                        this.creditcardRes.setIsSuccess(true);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is ok");
                                    } else {
                                        this.creditcardRes.setIsSuccess(false);
                                        this.creditcardRes.setProcess("validate creditcard");
                                        this.creditcardRes.setProcessDesc("CreditCard is used");
                                    }

                                    rs.close();
                                    st.close();
                                } catch (SQLException var13) {
                                    ++counterr;
                                    this.creditcardRes.setIsSuccess(false);
                                    this.creditcardRes.setProcess("validate creditcard");
                                    this.creditcardRes.setProcessDesc(var13.getMessage());
                                } finally {
                                    this.npDS.close();
                                }
                            } else {
                                ++counterr;
                                this.creditcardRes.setIsSuccess(false);
                                this.creditcardRes.setProcess("validate creditcard");
                                this.creditcardRes.setProcessDesc("CreditType is null or empty");
                            }
                        } else {
                            ++counterr;
                            this.creditcardRes.setIsSuccess(false);
                            this.creditcardRes.setProcess("validate creditcard");
                            this.creditcardRes.setProcessDesc("ConfirmNo is null or empty");
                        }
                    } else {
                        this.creditcardRes.setIsSuccess(false);
                        this.creditcardRes.setProcess("validate creditcard");
                        this.creditcardRes.setProcessDesc("Bank is null or empty");
                    }
                } else {
                    ++counterr;
                    this.creditcardRes.setIsSuccess(false);
                    this.creditcardRes.setProcess("validate creditcard");
                    this.creditcardRes.setProcessDesc("CreditcardNo is null or empty");
                }
            }
        }

        return this.creditcardRes;
    }

    public CT_Resp_ResponseBean verifyCoupong(List<IV_Reqs_CouponBean> coupong) {
        int counterr = 0;
        int checkexist = 0;
        if (coupong.size() != 0) {
            for(int y = 0; y < coupong.size(); ++y) {
                if (((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() != "" && ((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() != null) {
                    if (((IV_Reqs_CouponBean)coupong.get(y)).getAmount() != 0.0D) {
                        try {
                            Statement st = this.sqlDS.getSqlStatement("POS");
                            this.vQuery = "set dateformat dmy select count(code) as vCount from dbo.bccoupon where code = '" + ((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() + "' and couponval = " + ((IV_Reqs_CouponBean)coupong.get(y)).getAmount() + "and code not in (select couponcode from dbo.bccouponreceive where docno in (select docno from dbo.bcarinvoice where iscancel = 0)) and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= expiredate";
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Coupong :" + this.vQuery);

                            while(rs.next()) {
                                checkexist = rs.getInt("vCount");
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var10) {
                            var10.printStackTrace();
                            ++counterr;
                        } finally {
                            this.sqlDS.close();
                        }
                    } else {
                        ++counterr;
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("coupongsize :" + coupong.size() + "," + counterr + "," + checkexist);
        if (counterr == 0 && checkexist != 0) {
            this.coupongRes.setIsSuccess(true);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong is pass");
        } else if (counterr == 0 && checkexist == 0) {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used");
        } else {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong data error");
        }

        return this.coupongRes;
    }

    public CT_Resp_ResponseBean verifyCoupongBranch(String branchCode, String serverName, String dataBaseName, List<IV_Reqs_CouponBean> coupong) {
        int counterr = 0;
        int checkexist = 0;
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);
        System.out.println("CouponBranch = " + serverName + "," + dataBaseName);
        if (coupong.size() != 0) {
            for(int y = 0; y < coupong.size(); ++y) {
                if (((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() != "" && ((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() != null) {
                    if (((IV_Reqs_CouponBean)coupong.get(y)).getAmount() != 0.0D) {
                        try {
                            Statement st = this.npDS.getSqlStatementBranch(this.connData);
                            this.vQuery = "exec dbo.USP_DT_VerifyCoupong '" + ((IV_Reqs_CouponBean)coupong.get(y)).getCouponCode() + "'," + ((IV_Reqs_CouponBean)coupong.get(y)).getAmount();
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Coupong :" + this.vQuery);

                            while(rs.next()) {
                                checkexist = rs.getInt("vCount");
                                System.out.println("Count Coupon =" + rs.getInt("vCount"));
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var13) {
                            var13.printStackTrace();
                            ++counterr;
                        } finally {
                            this.npDS.close();
                        }
                    } else {
                        ++counterr;
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("coupongsizeBranch :" + coupong.size() + "," + counterr + "," + checkexist);
        if (counterr == 0 && checkexist != 0) {
            this.coupongRes.setIsSuccess(true);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong is pass");
        } else if (counterr == 0 && checkexist == 0) {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used or value invalid");
        } else {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong data error");
        }

        System.out.println(" = " + this.coupongRes.getIsSuccess());
        return this.coupongRes;
    }

    public CT_Resp_ResponseBean verifyCoupongBranch_SO(String branchCode, String serverName, String dataBaseName, List<SO_Reqs_CouponBean> coupong) {
        int counterr = 0;
        int checkexist = 0;
        
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);
        System.out.println("CouponBranch = " + serverName + "," + dataBaseName);
        if (coupong.size() != 0) {
            for(int y = 0; y < coupong.size(); ++y) {
                if (((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code() != "" && ((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code() != null) {
                    int checkduplicate = 0;

                    for(int c = 0; c < coupong.size(); ++c) {
                        System.out.println("coupong :Y=" + y + " " + ((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code() + " " + "C=" + c + " " + ((SO_Reqs_CouponBean)coupong.get(c)).getCoupon_code());
                        if (((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code().equals(((SO_Reqs_CouponBean)coupong.get(c)).getCoupon_code())) {
                            ++checkduplicate;
                            System.out.println("coupong.get(y).getCoupon_code()=Y=" + y + " " + ((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code() + " " + "C=" + c + " " + ((SO_Reqs_CouponBean)coupong.get(c)).getCoupon_code());
                            System.out.println("Coupon checkduplicate =" + checkduplicate);
                        }
                    }

                    System.out.println("checkduplicate :" + checkduplicate);
                    if (((SO_Reqs_CouponBean)coupong.get(y)).getAmount() != 0.0D && checkduplicate <= 1) {
                        try {
                            Statement st = this.npDS.getSqlStatementBranch(this.connData);
                            this.vQuery = "exec dbo.USP_DT_VerifyCoupong '" + ((SO_Reqs_CouponBean)coupong.get(y)).getCoupon_code() + "'," + ((SO_Reqs_CouponBean)coupong.get(y)).getAmount();
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Coupong :" + this.vQuery);

                            while(rs.next()) {
                                checkexist = rs.getInt("vCount");
                                System.out.println("Count Coupon =" + rs.getInt("vCount"));
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var14) {
                            var14.printStackTrace();
                            ++counterr;
                        } finally {
                            this.npDS.close();
                        }
                    } else {
                        ++counterr;
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("coupongsizeBranch :" + coupong.size() + "," + counterr + "," + checkexist);
        if (counterr == 0 && checkexist != 0) {
            this.coupongRes.setIsSuccess(true);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong is pass");
        } else if (counterr == 0 && checkexist == 0) {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong not exist or expire or value invalid or is used or value invalid");
        } else {
            this.coupongRes.setIsSuccess(false);
            this.coupongRes.setProcess("verifyCoupong");
            this.coupongRes.setProcessDesc("Coupong data error");
        }

        System.out.println(" = " + this.coupongRes.getIsSuccess());
        return this.coupongRes;
    }

    public CT_Resp_ResponseBean verifyDeposit(String serverName, String dataBaseName, String ar_code, List<IV_Req_DepositBean> deposit) {
        int counterr = 0;
        int checkexist = 0;
        if (deposit.size() != 0) {
            for(int z = 0; z < deposit.size(); ++z) {
                if (((IV_Req_DepositBean)deposit.get(z)).getDeposit_id() != "" && ((IV_Req_DepositBean)deposit.get(z)).getDeposit_id() != null) {
                    if (((IV_Req_DepositBean)deposit.get(z)).getAmount().doubleValue() != 0.0D) {
                        try {
                            Statement st = this.sqlDS.getSqlStatement(serverName, dataBaseName);
                            this.vQuery = "set dateformat dmy select count(docno) as vCount from dbo.bcardeposit where arcode = '" + ar_code + "' and docno = '" + ((IV_Req_DepositBean)deposit.get(z)).getDeposit_id() + "' and billbalance >= " + ((IV_Req_DepositBean)deposit.get(z)).getAmount() + " and iscancel = 0";
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Deposit :" + this.vQuery);

                            while(rs.next()) {
                                checkexist = rs.getInt("vCount");
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var13) {
                            var13.printStackTrace();
                            ++counterr;
                        } finally {
                            this.sqlDS.close();
                        }
                    } else {
                        ++counterr;
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("depositsize :" + deposit.size() + "," + counterr + "," + checkexist);
        if (counterr == 0 && checkexist != 0) {
            this.depositRes.setIsSuccess(true);
            this.depositRes.setProcess("verifyDeposit");
            this.depositRes.setProcessDesc("Deposit is pass");
        } else if (counterr == 0 && checkexist == 0) {
            this.depositRes.setIsSuccess(false);
            this.depositRes.setProcess("verifyDeposit");
            this.depositRes.setProcessDesc("Deposit not exist or expire or value invalid or isUsed");
        } else {
            this.depositRes.setIsSuccess(false);
            this.depositRes.setProcess("verifyDeposit");
            this.depositRes.setProcessDesc("Deposit data error");
        }

        return this.depositRes;
    }

    public CT_Resp_ResponseBean verifyItemSaleOrder(DT_User_LoginBranchBean db, SO_Req_GenOTPSaleOrderBean item) {
        int counterr = 0;
        int checkexist = 0;
        double remainqty = 0.0D;
        if (item.getItem().size() != 0) {
            for(int m = 0; m < item.getItem().size(); ++m) {
                if (((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getItem_code() != "" && ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getItem_code() != null) {
                    if (((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getRequest_qty() != 0.0D) {
                        try {
                            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
                            this.vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '" + item.getDoc_no() + "' and itemcode = '" + ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getItem_code() + "' and unitcode ='" + ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getItem_unit_code() + "' and linenumber =" + ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getLine_number() + " and iscancel = 0";
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Item :" + this.vQuery);

                            while(rs.next()) {
                                remainqty = rs.getDouble("remainqty");
                            }

                            System.out.println("remainqty,item.getItem().get(m).getRequest_qty()==" + remainqty + "," + ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getRequest_qty());
                            if (remainqty > 0.0D && remainqty < ((SO_Req_ListItemToQueueBean)item.getItem().get(m)).getRequest_qty()) {
                                ++counterr;
                                System.out.println("Count Error =" + counterr);
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var13) {
                            var13.printStackTrace();
                            ++counterr;
                        } finally {
                            this.sqlDS.close();
                        }
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("itemsize :" + item.getItem().size() + "," + counterr + "," + checkexist);
        if (counterr == 0) {
            this.itemRes.setIsSuccess(true);
            this.itemRes.setProcess("verifyItem");
            this.itemRes.setProcessDesc("Item is pass");
        } else {
            this.itemRes.setIsSuccess(false);
            this.itemRes.setProcess("verifyDeposit");
            this.itemRes.setProcessDesc("Item data error");
        }

        return this.itemRes;
    }

    public double checkItemSaleOrder(DT_User_LoginBranchBean db, String doc_no, SO_Req_ListItemToQueueBean item) {
        double remainqty = 0.0D;
        if (doc_no != "" && item.getItem_code() != "" && item.getItem_code() != null && item.getRequest_qty() != 0.0D) {
            try {
                Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
                this.vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '" + doc_no + "' and itemcode = '" + item.getItem_code() + "' and unitcode ='" + item.getItem_unit_code() + "' and linenumber =" + item.getLine_number() + " and iscancel = 0";
                ResultSet rs = st.executeQuery(this.vQuery);
                System.out.println("Verify Deposit :" + this.vQuery);

                while(rs.next()) {
                    remainqty = rs.getDouble("remainqty");
                }

                rs.close();
                st.close();
            } catch (SQLException var11) {
                var11.printStackTrace();
                remainqty = 0.0D;
            } finally {
                this.sqlDS.close();
            }
        }

        return remainqty;
    }

    public double calcPointInvoice(String vInvNo) {
        double point = 0.0D;

        try {
            Statement st = this.sqlDS.getSqlStatement("POS");
            this.vQuery = "exec dbo.USP_VP_CheckInvoicePoint '" + vInvNo + "'";

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); point = rs.getDouble("pointbal")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var9) {
            System.out.println(var9.getMessage());
        } finally {
            this.sqlDS.close();
        }

        return point;
    }

    public SO_Res_CheckDataSOBean checkSaleOrderData(DT_User_LoginBranchBean db, String vSaleOrderNo) {
        try {
            Statement stmt = this.npDS.getSqlStatementBranch(db);
            this.vQuery = "exec dbo.USP_NP_CheckDataSaleOrder '" + vSaleOrderNo + "'";
            System.out.println(this.vQuery);
            ResultSet rs = stmt.executeQuery(this.vQuery);

            while(rs.next()) {
                this.SO.setDoc_date(rs.getString("docdate"));
                this.SO.setAr_code(rs.getString("arcode"));
                this.SO.setAr_name(rs.getString("arname"));
                this.SO.setSale_code(rs.getString("salecode"));
                this.SO.setSale_name(rs.getString("salename"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException var8) {
            this.response.setProcess("QueueDetails");
            this.response.setProcessDesc(var8.getMessage());
            this.response.setIsSuccess(false);
        } finally {
            this.ds.close();
        }

        return this.SO;
    }

    public SO_Res_ItemSaleOrderBean checkItemSaleOrderData(DT_User_LoginBranchBean db, String vSaleOrderNo, String vItemCode, String vUnitCode, int vLineNumber) {
        try {
            Statement stmt = this.npDS.getSqlStatementBranch(db);
            this.vQuery = "exec dbo.USP_NP_CheckDataItemSaleOrder '" + vSaleOrderNo + "','" + vItemCode + "','" + vUnitCode + "'," + vLineNumber;
            System.out.println(this.vQuery);
            ResultSet rs = stmt.executeQuery(this.vQuery);

            while(rs.next()) {
                this.sale_item.setItem_code(rs.getString("itemcode"));
                this.sale_item.setItem_name(rs.getString("itemname"));
                this.sale_item.setItem_price(rs.getDouble("price"));
                this.sale_item.setWh_code(rs.getString("whcode"));
                this.sale_item.setShelf_code(rs.getString("shelfcode"));
                this.sale_item.setItem_rate(rs.getInt("rate1"));
                this.sale_item.setItem_average(rs.getDouble("avgcost"));
                this.sale_item.setItem_category_code("");
                this.sale_item.setItem_category_name("");
                this.sale_item.setItem_depart_name("");
                this.sale_item.setItem_department_code("");
                this.sale_item.setItem_expert_code("");
                this.sale_item.setSec_code("");
                this.sale_item.setSec_name("");
                this.sale_item.setZone_id(rs.getString("zoneid"));
                this.sale_item.setPick_zone(rs.getString("pickzone"));
                this.sale_item.setRemain_qty(rs.getDouble("remainqty"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException var11) {
            this.response.setProcess("QueueDetails");
            this.response.setProcessDesc(var11.getMessage());
            this.response.setIsSuccess(false);
        } finally {
            this.ds.close();
        }

        return this.sale_item;
    }

    public boolean updateSaleOrderQtyRequestData(DT_User_LoginBranchBean db, String dbName, String qId, String vSaleOrderNo) {
        try {
            Statement stmt = this.npDS.getSqlStatementBranch(db);
            this.vQuery = "exec dbo.USP_NP_DataSaleOrderDriveThru '" + vSaleOrderNo + "'";
            System.out.println(this.vQuery);
            ResultSet rs = stmt.executeQuery(this.vQuery);

            while(rs.next()) {
                this.sale_item.setItem_code(rs.getString("itemcode"));
                this.sale_item.setUnit_code(rs.getString("unitcode"));
                this.sale_item.setRemain_qty(rs.getDouble("remainqty"));
                this.sale_item.setQty(rs.getDouble("qty"));
                this.sale_item.setWh_code(rs.getString("whcode"));
                this.sale_item.setShelf_code(rs.getString("shelfcode"));
                System.out.println("ItemCode = " + rs.getString("itemcode"));
                this.vQuerySub = "update QItem set Qty=" + this.sale_item.getRemain_qty() + " where qid = " + qId + " and itemcode = '" + this.sale_item.getItem_code() + "' and unitcode = '" + this.sale_item.getUnit_code() + "' and whcode = '" + this.sale_item.getWh_code() + "' and shelfcode = '" + this.sale_item.getShelf_code() + "';";
                this.isSuccess = this.excecute.execute(dbName, this.vQuerySub);
                System.out.println("vQuerySub=" + this.vQuerySub);
            }

            rs.close();
            stmt.close();
            return true;
        } catch (SQLException var10) {
            ;
        } finally {
            this.ds.close();
        }

        return false;
    }

    public String getItemFilePath(String itemCode) {
        String filePath = "";

        try {
            Statement st = this.posDs.getSqlStatement("POS");
            this.vQuery = "select isnull(picfilename1,'') as picfilename1 from dbo.bcitem where code = '" + itemCode + "'";
            System.out.println("getItemFilePath = " + this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                filePath = rs.getString("picfilename1");
                System.out.println(itemCode + " Set filePath : " + filePath);
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            System.out.println(var8.getMessage());
        } finally {
            this.sqlDS.close();
        }

        return filePath;
    }

    public double calcPointInvoiceBranch(String serverName, String dataBaseName, String vInvNo) {
        double point = 0.0D;
        this.connData.setServerName(serverName);
        this.connData.setDbName(dataBaseName);

        try {
            Statement st = this.npDS.getSqlStatementBranch(this.connData);
            this.vQuery = "exec dbo.USP_VP_CheckInvoicePoint '" + vInvNo + "'";

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); point = rs.getDouble("pointbal")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var11) {
            System.out.println(var11.getMessage());
        } finally {
            this.npDS.close();
        }

        return point;
    }

    public int CheckAccessToken(String accessToken) {
        int access = 0;

        try {
            Statement stmtTK = this.dsTK.getStatement("SmartConfig");
            this.vQuery = "select count(userUUID) as vCount from UserAccess where userUUID = '" + accessToken + "'";

            ResultSet rsTK;
            for(rsTK = stmtTK.executeQuery(this.vQuery); rsTK.next(); access = rsTK.getInt("vCount")) {
                ;
            }

            rsTK.close();
            stmtTK.close();
        } catch (Exception var8) {
            System.err.println(var8.getMessage());
        } finally {
            this.dsTK.close();
        }

        return access;
    }

    public User_Resp_CheckDataAccessTokenBean CheckDataAccessToken(String accessToken) {
        boolean var2 = false;

        try {
            Statement stmtTK = this.dsTK.getStatement("SmartConfig");
            this.vQuery = "call USP_DT_CheckDataAccessToken ('Pos_DriveThru','pos','" + accessToken + "')";
            ResultSet rsTK = stmtTK.executeQuery(this.vQuery);

            while(rsTK.next()) {
                this.branch.setExist(rsTK.getInt("vCount"));
                this.branch.setBranchCode(rsTK.getString("branch_id"));
                this.branch.setMachineNo(rsTK.getString("machine_no"));
                this.branch.setMachineCode(rsTK.getString("machine_code"));
                this.branch.setWhCode(rsTK.getString("wh_code"));
                this.branch.setShelfCode(rsTK.getString("shelf_code"));
                this.branch.setServerName(rsTK.getString("server_name"));
                this.branch.setDataBaseName(rsTK.getString("database_name"));
                this.branch.setZoneid(rsTK.getString("zone_id"));
            }

            rsTK.close();
            stmtTK.close();
        } catch (Exception var8) {
            System.err.println(var8.getMessage());
        } finally {
            this.dsTK.close();
        }

        return this.branch;
    }

    public IV_Resp_PrintSlipBean copyHTML(String dbName, IV_Reqs_PrintSlipBean reqs) {
        new IV_Resp_PrintSlipBean();
        List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList();
        double pointBal = 0.0D;
        pointBal = 0.0D;
        String pointDesc = "เอกสารนี้ได้จำนวนแต้ม :" + pointBal + " แต้ม";
        System.out.println("Copy POS");

        try {
            Statement st = this.sqlDS.getSqlStatement(dbName);
            this.vQuery = "exec dbo.USP_NP_InvoicePrintDetails 0, '" + reqs.getInvoiceNo() + "','" + reqs.getArCode() + "'";
            System.out.println(this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);
            this.listInv.clear();

            while(rs.next()) {
                Date billDocDate = rs.getDate("docdate");
                this.printInv.setDocNo(rs.getString("docno"));
                this.printInv.setDocDate(billDocDate.toString());
                this.printInv.setCompanyName(rs.getString("companyname"));
                this.printInv.setTaxId(rs.getString("taxid"));
                this.printInv.setPosId(rs.getString("posid"));
                this.printInv.setCashier(rs.getString("cashiercode"));
                this.printInv.setSaleCode(rs.getString("salecode"));
                this.printInv.setBillTime(rs.getString("billtime"));
                this.printInv.setTotalAmount(rs.getDouble("totalamount"));
                this.printInv.setTax(rs.getInt("taxrate"));
                this.printInv.setTaxAmount(rs.getDouble("taxamount"));
                this.printInv.setCashAmount(rs.getDouble("sumcashamount"));
                this.printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
                this.printInv.setChange(rs.getDouble("changeamount"));
                this.printInv.setGreeting1(rs.getString("greeting1"));
                this.printInv.setGreeting2(rs.getString("greeting2"));
                this.printInv.setGreeting3(rs.getString("greeting3"));
                this.printInv.setGreeting4(rs.getString("greeting4"));
                this.printInv.setGreeting5(rs.getString("greeting5"));
                this.printInv.setRemark("");
                this.printInv.setPromotionDesc1(pointDesc);
                this.printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
                this.printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
                this.printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
                this.printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
                this.printInv.setPoint(rs.getInt("point"));
                IV_Resp_PrintSlipSubBean evt = new IV_Resp_PrintSlipSubBean();
                evt.setItemCode(rs.getString("itemcode"));
                evt.setItemName(rs.getString("itemname"));
                evt.setQty(rs.getInt("qty"));
                evt.setPrice(rs.getDouble("price"));
                evt.setAmount(rs.getDouble("amount"));
                evt.setUnitCode(rs.getString("unitcode"));
                System.out.println(rs.getString("itemcode"));
                this.listInv.add(evt);
            }

            this.response.setIsSuccess(true);
            this.response.setProcess("Search Print Slip");
            this.response.setProcessDesc("Successfully");
            this.printInv.setItem(this.listInv);
            this.printInv.setResponse(this.response);
            System.out.println("CashierCode : " + this.printInv.getCashier());
            rs.close();
            st.close();
        } catch (SQLException var15) {
            this.printInv.setItem(listINVCopy);
            this.printInv.setResponse(this.response);
        } finally {
            this.sqlDS.close();
        }

        System.out.println("InvoiceNO : " + this.printInv.getDocNo());
        this.response.setIsSuccess(true);
        this.response.setProcess("Test");
        this.response.setProcessDesc("Successful");
        return this.printInv;
    }

    public IV_Resp_PrintSlipBean copyPosHTML(DT_User_LoginBranchBean db, IV_Reqs_PrintSlipBean reqs) {
        new IV_Resp_PrintSlipBean();
        List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList();
        double pointBal = 0.0D;
        pointBal = 0.0D;
        String pointDesc = "เอกสารนี้ได้จำนวนแต้ม :" + pointBal + " แต้ม";
        System.out.println("Copy POS");

        try {
            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
            this.vQuery = "exec dbo.USP_NP_InvoicePrintDetails 0, '" + reqs.getInvoiceNo() + "','" + reqs.getArCode() + "'";
            System.out.println(this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);
            this.listInv.clear();

            while(rs.next()) {
                Date billDocDate = rs.getDate("docdate");
                this.printInv.setDocNo(rs.getString("docno"));
                this.printInv.setDocDate(billDocDate.toString());
                this.printInv.setCompanyName(rs.getString("companyname"));
                this.printInv.setTaxId(rs.getString("taxid"));
                this.printInv.setPosId(rs.getString("posid"));
                this.printInv.setCashier(rs.getString("cashiercode"));
                this.printInv.setSaleCode(rs.getString("salecode"));
                this.printInv.setBillTime(rs.getString("billtime"));
                this.printInv.setTotalAmount(rs.getDouble("totalamount"));
                this.printInv.setTax(rs.getInt("taxrate"));
                this.printInv.setTaxAmount(rs.getDouble("taxamount"));
                this.printInv.setCashAmount(rs.getDouble("sumcashamount"));
                this.printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
                this.printInv.setChange(rs.getDouble("changeamount"));
                this.printInv.setGreeting1(rs.getString("greeting1"));
                this.printInv.setGreeting2(rs.getString("greeting2"));
                this.printInv.setGreeting3(rs.getString("greeting3"));
                this.printInv.setGreeting4(rs.getString("greeting4"));
                this.printInv.setGreeting5(rs.getString("greeting5"));
                this.printInv.setRemark("");
                this.printInv.setPromotionDesc1(pointDesc);
                this.printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
                this.printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
                this.printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
                this.printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
                this.printInv.setPoint(rs.getInt("point"));
                IV_Resp_PrintSlipSubBean evt = new IV_Resp_PrintSlipSubBean();
                evt.setItemCode(rs.getString("itemcode"));
                evt.setItemName(rs.getString("itemname"));
                evt.setQty(rs.getInt("qty"));
                evt.setPrice(rs.getDouble("price"));
                evt.setAmount(rs.getDouble("amount"));
                evt.setUnitCode(rs.getString("unitcode"));
                System.out.println(rs.getString("itemcode"));
                this.listInv.add(evt);
            }

            this.response.setIsSuccess(true);
            this.response.setProcess("Search Print Slip");
            this.response.setProcessDesc("Successfully");
            this.printInv.setItem(this.listInv);
            this.printInv.setResponse(this.response);
            System.out.println("CashierCode : " + this.printInv.getCashier());
            rs.close();
            st.close();
        } catch (SQLException var15) {
            this.printInv.setItem(listINVCopy);
            this.printInv.setResponse(this.response);
        } finally {
            this.sqlDS.close();
        }

        System.out.println("InvoiceNO : " + this.printInv.getDocNo());
        this.response.setIsSuccess(true);
        this.response.setProcess("Test");
        this.response.setProcessDesc("Successful");
        return this.printInv;
    }

    public IV_Resp_PrintSlipBean copySaleOrderHTML(DT_User_LoginBranchBean dbName, IV_Reqs_PrintSlipBean reqs) {
        new IV_Resp_PrintSlipBean();
        List<IV_Resp_PrintSlipSubBean> listINVCopy = new ArrayList();
        double pointBal = 0.0D;
        pointBal = 0.0D;
        String pointDesc = "เอกสารนี้ได้จำนวนแต้ม :" + pointBal + " แต้ม";

        try {
            Statement st = this.sqlDS.getSqlStatement(dbName.getServerName(), dbName.getDbName());
            this.vQuery = "exec dbo.USP_NP_InvoicePrintDetails 1, '" + reqs.getInvoiceNo() + "','" + reqs.getArCode() + "'";
            System.out.println(this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);
            this.listInv.clear();

            while(rs.next()) {
                Date billDocDate = rs.getDate("docdate");
                System.out.println("Bill Point" + rs.getInt("point"));
                this.printInv.setDocNo(rs.getString("docno"));
                this.printInv.setDocDate(billDocDate.toString());
                this.printInv.setCompanyName(rs.getString("companyname"));
                this.printInv.setTaxId(rs.getString("taxid"));
                this.printInv.setPosId(rs.getString("posid"));
                this.printInv.setCashier(rs.getString("cashiercode"));
                this.printInv.setSaleCode(rs.getString("salecode"));
                this.printInv.setBillTime(rs.getString("billtime"));
                this.printInv.setTotalAmount(rs.getDouble("totalamount"));
                this.printInv.setTax(rs.getInt("taxrate"));
                this.printInv.setTaxAmount(rs.getDouble("taxamount"));
                this.printInv.setCashAmount(rs.getDouble("sumcashamount"));
                this.printInv.setCreditAmount(rs.getDouble("sumcreditamount"));
                this.printInv.setChange(rs.getDouble("changeamount"));
                this.printInv.setGreeting1(rs.getString("greeting1"));
                this.printInv.setGreeting2(rs.getString("greeting2"));
                this.printInv.setGreeting3(rs.getString("greeting3"));
                this.printInv.setGreeting4(rs.getString("greeting4"));
                this.printInv.setGreeting5(rs.getString("greeting5"));
                this.printInv.setRemark("");
                this.printInv.setPromotionDesc1(pointDesc);
                this.printInv.setPromotionDesc2(rs.getString("promotionDesc2"));
                this.printInv.setPromotionDesc3(rs.getString("promotionDesc3"));
                this.printInv.setPromotionDesc4(rs.getString("promotionDesc4"));
                this.printInv.setPromotionDesc5(rs.getString("promotionDesc5"));
                this.printInv.setPoint(rs.getInt("point"));
                IV_Resp_PrintSlipSubBean evt = new IV_Resp_PrintSlipSubBean();
                evt.setItemCode(rs.getString("itemcode"));
                evt.setItemName(rs.getString("itemname"));
                evt.setQty(rs.getInt("qty"));
                evt.setPrice(rs.getDouble("price"));
                evt.setAmount(rs.getDouble("amount"));
                evt.setUnitCode(rs.getString("unitcode"));
                System.out.println("Bill Item" + rs.getString("itemcode"));
                this.listInv.add(evt);
            }

            this.response.setIsSuccess(true);
            this.response.setProcess("Search Print Slip");
            this.response.setProcessDesc("Successfully");
            this.printInv.setItem(this.listInv);
            this.printInv.setResponse(this.response);
            System.out.println("CashierCode : " + this.printInv.getCashier());
            rs.close();
            st.close();
        } catch (SQLException var15) {
            this.printInv.setItem(listINVCopy);
            this.printInv.setResponse(this.response);
        } finally {
            this.sqlDS.close();
        }

        System.out.println("SaleOrder InvoiceNO : " + this.printInv.getDocNo());
        this.response.setIsSuccess(true);
        this.response.setProcess("Test");
        this.response.setProcessDesc("Successful");
        return this.printInv;
    }

    public int countBillAll(String vDocDate) {
        int billAll = 0;

        try {
            Statement st = this.sqlDS.getSqlStatement("POS");
            this.vQuery = "exec dbo.USP_DT_CountBillZoneDriveThru '" + vDocDate + "'";

            ResultSet rs;
            for(rs = st.executeQuery(this.vQuery); rs.next(); billAll = rs.getInt("billall")) {
                ;
            }

            rs.close();
            st.close();
        } catch (Exception var8) {
            System.out.println(var8.getMessage());
        } finally {
            this.sqlDS.close();
        }

        return billAll;
    }

    public CT_Res_CompanyDataBean searchCompanyData(String company_code, String zone_id) {
        CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();
        db.setServerName("192.168.0.7");
        db.setDatabaseName("bcnp");
        System.out.println(db.getServerName());

        try {
            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDatabaseName());
            this.vQuery = "exec dbo.USP_NP_SearchCompanyZoneData '" + company_code + "','" + zone_id + "'";
            System.out.println(this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.company.setBranch_code(rs.getString("branchcode"));
                this.company.setWh_code(rs.getString("whcode"));
                this.company.setShelf_code(rs.getString("shelfcode"));
            }

            this.response.setIsSuccess(true);
            this.response.setProcess("Company");
            this.response.setProcessDesc("Successfull");
            rs.close();
            st.close();
        } catch (Exception var9) {
            this.response.setIsSuccess(false);
            this.response.setProcess("Company");
            this.response.setProcessDesc(var9.getMessage());
        } finally {
            this.ds.close();
        }

        return this.company;
    }

    public SO_Res_VerifySaleOrderBean VerifySaleOrder(CT_Req_ServerDataBaseBean db, SO_Req_VerifySaleOrderBean req) {
        System.out.println(db.getServerName());

        try {
            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDatabaseName());
            this.vQuery = "exec dbo.USP_API_VerifySaleOrderItemNotBilling 'np','drivethru'";
            System.out.println(this.vQuery);
            ResultSet rs = st.executeQuery(this.vQuery);

            while(rs.next()) {
                this.item_veryfy.setAr_code(rs.getString("arcode"));
                this.item_veryfy.setBill_type(rs.getInt("billtype"));
                this.item_veryfy.setDeliver_type(rs.getInt("isconditionsend"));
                this.item_veryfy.setDoc_date(rs.getString("docdate"));
                this.item_veryfy.setDoc_no(rs.getString("docno"));
                this.item_veryfy.setItem_rate(rs.getDouble("packingrate1"));
                this.item_veryfy.setRemain_qty(rs.getDouble("remainqty"));
                this.item_veryfy.setSale_code(rs.getString("salecode"));
                this.item_veryfy.setSo_qty(rs.getDouble("qty"));
            }

            this.response.setIsSuccess(true);
            this.response.setProcess("Verify SaleOrder");
            this.response.setProcessDesc("Successfull");
            rs.close();
            st.close();
        } catch (Exception var8) {
            this.response.setIsSuccess(false);
            this.response.setProcess("Verify SaleOrder");
            this.response.setProcessDesc(var8.getMessage());
        } finally {
            this.ds.close();
        }

        return this.item_veryfy;
    }

    public CT_Resp_ResponseBean verifyEditItemSaleOrder(DT_User_LoginBranchBean db, SO_Req_EditSaleOrderBean item) {
        int counterr = 0;
        int checkexist = 0;
        double remainqty = 0.0D;
        PK_Resp_GetDataQueue queue_id = this.searchQueue(item.getQueue_id());
        if (item.getItem().size() != 0) {
            for(int m = 0; m < item.getItem().size(); ++m) {
                if (((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getItem_code() != "" && ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getItem_code() != null) {
                    if (((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getRequest_qty() != 0.0D) {
                        try {
                            Statement st = this.sqlDS.getSqlStatement(db.getServerName(), db.getDbName());
                            this.vQuery = "set dateformat dmy select isnull(remainqty,0) as remainqty from dbo.bcsaleordersub where docno = '" + queue_id.getSaleOrder() + "' and itemcode = '" + ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getItem_code() + "' and unitcode ='" + ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getItem_unit_code() + "' and linenumber =" + ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getLine_number() + " and iscancel = 0";
                            ResultSet rs = st.executeQuery(this.vQuery);
                            System.out.println("Verify Item :" + this.vQuery);

                            while(rs.next()) {
                                remainqty = rs.getDouble("remainqty");
                            }

                            System.out.println("remainqty,item.getItem().get(m).getRequest_qty()==" + remainqty + "," + ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getRequest_qty());
                            if (remainqty > 0.0D && remainqty < ((SO_Req_ListEditItemSaleOrderBean)item.getItem().get(m)).getRequest_qty()) {
                                ++counterr;
                                System.out.println("Count Error =" + counterr);
                            }

                            rs.close();
                            st.close();
                        } catch (SQLException var14) {
                            var14.printStackTrace();
                            ++counterr;
                        } finally {
                            this.sqlDS.close();
                        }
                    }
                } else {
                    ++counterr;
                }
            }
        }

        System.out.println("itemsize :" + item.getItem().size() + "," + counterr + "," + checkexist);
        if (counterr == 0) {
            this.itemRes.setIsSuccess(true);
            this.itemRes.setProcess("verifyItem");
            this.itemRes.setProcessDesc("Item is pass");
        } else {
            this.itemRes.setIsSuccess(false);
            this.itemRes.setProcess("verifyDeposit");
            this.itemRes.setProcessDesc("Item data error");
        }

        return this.itemRes;
    }

    public String GenPinOTP() {
        int x = (int)(Math.random() * 9.0D);
        ++x;
        String randomPIN = String.valueOf(x) + (int)(Math.random() * 1000.0D);
        return randomPIN;
    }

    public String EnCoding(String myPassword) {
        BASE64Encoder encoder = new BASE64Encoder();
        String afterhex = bCryptPassword.toSHA1(myPassword.getBytes());
        String encodedBytes = encoder.encodeBuffer(afterhex.getBytes());
        return encodedBytes;
    }
    
    public void UpdateSaleOrderData(String dbName,DT_User_LoginBranchBean db, int queue_id) {
    	SO_Res_ItemSaleOrderBean item_saleorder = new SO_Res_ItemSaleOrderBean();
    	double itemPrice;
    	int vCheckExistItem;
    	double itemAmount;
    	double remainQty;
    	
    	String saleCode;
    	String saleName;
    	String vQueryLog;
    	
    	try {
    		
    		PK_Resp_GetDataQueue QueueData = new PK_Resp_GetDataQueue();
    		
    		QueueData = searchQueue(queue_id);
    		
    		saleCode = QueueData.getSaleCode();
    		saleName = QueueData.getSaleName();
    		
			Statement st_sub = conn.getSqlStatement(db.getServerName(), db.getDbName());
			vQuerySub = "exec dbo.USP_API_SOItemAddBillDriveThru '"+QueueData.getSaleOrder()+"'";
			System.out.println(vQuerySub);
			ResultSet rs_sub = st_sub.executeQuery(vQuerySub);
			
			while(rs_sub.next()){
				System.out.println("Item1 = "+rs_sub.getString("itemcode"));
				
				remainQty = rs_sub.getDouble("remainqty");

				//item_saleorder = data.checkItemSaleOrderData(db, QueueData.getSaleOrder(), rs_sub.getString("itemcode"), rs_sub.getString("unitcode"), rs_sub.getInt("linenumber"));

				if (rs_sub.getString("itemcode")!=null && rs_sub.getString("itemcode")!=""){
					System.out.println("Item2 = "+rs_sub.getString("itemcode"));	
						System.out.println("Item3 = "+rs_sub.getString("itemcode"));
						if(QueueData.getStatus() < 2) {
							vCheckExistItem = 0;

							itemPrice = rs_sub.getDouble("price");

							if (saleCode == "" || saleCode == null) {
								saleCode = userCode.getEmployeeCode();//lastSale.getSaleCode();
								saleName = userCode.getEmployeeName();//lastSale.getSaleName();

								System.out.println("No Have SaleCode");

							}

							itemAmount = remainQty*itemPrice;

							System.out.println("SaleName : "+saleCode+"/"+saleName);

							if (rs_sub.getString("itemcode")!=null && rs_sub.getString("itemcode")!=""){
								if (vCheckExistItem==0 && rs_sub.getDouble("remainqty")>0){

									vQuery = "insert into QItem(qId,docNo,docDate,itemCode,itemName,unitCode,barCode,qty,reqQty,pickQty,loadQty,checkoutQty,price,itemAmount,rate1,pickerCode,pickDate,isCancel,lineNumber,saleCode,saleName,expertCode,departCode,departName,catCode,catName,secManCode,secManName,averageCost,whcode,shelfcode,zoneid,pickzone) "+ "values("
											+queue_id+",'"+QueueData.getDocNo()+"',CURDATE(),'"+ rs_sub.getString("itemcode")+"','"+rs_sub.getString("itemname")+"','"+rs_sub.getString("unitcode")+"','"+rs_sub.getString("barcode")+"',"+ rs_sub.getDouble("remainqty")+","+rs_sub.getDouble("remainqty")+",0,0,0,"+itemPrice+","+itemAmount+","+rs_sub.getInt("packingrate1")+",'',CURRENT_TIMESTAMP,0,"+rs_sub.getInt("linenumber")+",'"+saleCode+"','"+saleName+"','','','','','','','',"+ rs_sub.getDouble("AverageCost")+",'"+rs_sub.getString("whcode")+"','"+rs_sub.getString("shelfcode")+"','"+rs_sub.getString("zoneid")+"','"+rs_sub.getString("pickzone")+"' )";
									System.out.println("QueryInsert :"+vQuery);
									isSuccess= excecute.execute(dbName,vQuery);

									vQueryLog = "exec dbo.USP_NP_InsertPickItemDriveThru '"+QueueData.getSaleOrder()+"','"+QueueData.getOtp_password()+"','"+QueueData.getCarLicense()+"','"+rs_sub.getString("itemcode")+"',"+rs_sub.getDouble("remainqty")+",'"+rs_sub.getString("unitcode")+"','"+rs_sub.getString("whcode")+"','"+rs_sub.getString("shelfcode")+"','','',0,"+rs_sub.getInt("linenumber");
									System.out.println("vQueryLog = "+vQueryLog);

									isSuccess= npExec.executeSqlBranch(db,vQueryLog);

								}else{
									vQuery = "update QItem set qty ="+rs_sub.getDouble("remainqty")+",reqQty="+rs_sub.getDouble("remainqty")+",price ="+itemPrice +",itemAmount="+itemAmount+",isCancel=0,salecode='"+saleCode+"',salename='"+saleName+"' where qId = "+queue_id+" and docNo ='"+vGenNewDocNo+"' and itemCode='"+rs_sub.getString("itemcode")+"' and barCode ='"+rs_sub.getString("barcode")+"' and unitCode ='"+rs_sub.getString("unitcode")+"' and lineNumber ="+rs_sub.getInt("linenumber");
									System.out.println("QuerySub :"+vQuery);
									isSuccess= excecute.execute(dbName,vQuery);
								}

								vQuerySub = "update  Queue set numberofitem = (select count(itemCode) as countItem from QItem where docNo = '"+QueueData.getDocNo()+"') where docNo ='"+QueueData.getDocNo()+"'";
								System.out.println("vQuerySub = "+vQuerySub);

								isSuccess= excecute.execute(dbName,vQuerySub);

							}else{

								isSuccess=false;
							}
						}else{
							isSuccess=false;
						}

				}else{
					//==============================
					isSuccess=false;
				}
				
			}
			
			rs_sub.close();
			st_sub.close();

    	}catch(SQLException e) {
			System.out.println(e.getMessage());	
    	}finally {
    		
    	}
    }
}