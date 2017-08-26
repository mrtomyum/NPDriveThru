package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_ListItemToQueueBean;
import bean.request.SO_Req_ListSOItemToPickupBean;
import bean.request.SO_Req_SearchSaleOrderBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_ListOwnerPhoneBean;
import bean.response.SO_Res_SaleOrderDetailsBean;

public class SaleOrderControllerTest {

	@Test
	public void test() {
////		SO_Req_SearchSaleOrderBean req = new SO_Req_SearchSaleOrderBean();
//		DT_User_LoginBranchBean db  = new DT_User_LoginBranchBean();
//		DriveThruController ctl = new DriveThruController();
////		SO_Res_SaleOrderDetailsBean so = new SO_Res_SaleOrderDetailsBean();
////		
////		db.setServerName("192.168.0.7");
////		db.setDatabaseName("bcnp");
////		req.setAccess_token("");
////		req.setSale_order_id("W01-SCN5909-0181");
////		
////		so = ctl.saleOrdertoOTP(db, req);
//		
//		
//		SO_Req_GenOTPSaleOrderBean req = new SO_Req_GenOTPSaleOrderBean();
//		SO_Res_GenQueueRespBean otp = new SO_Res_GenQueueRespBean();
//		
//		
//		List<SO_Req_ListItemToQueueBean> item = new ArrayList<SO_Req_ListItemToQueueBean>();
//		List<SO_Res_ListOwnerPhoneBean> owner_phone = new ArrayList<SO_Res_ListOwnerPhoneBean>();
//		
//		SO_Req_ListItemToQueueBean evt;
//		evt = new SO_Req_ListItemToQueueBean();
//		evt.setItem_barcode("8851917200470");
//		evt.setItem_code("8851917200470");
//		evt.setItem_name("ลูกกลิ้งทาสี SOMIC 4");
//		evt.setItem_price(50);
//		evt.setItem_unit_code("ัน");
//		evt.setItem_whcode("S1-A");
//		evt.setItem_shelfcode("-");
//		evt.setRequest_qty(2);
//		
//		item.add(evt);
//		
//		
//		
//		evt = new SO_Req_ListItemToQueueBean();
//		evt.setItem_barcode("3300725");
//		evt.setItem_code("3300725");
//		evt.setItem_name("แปรงทาสีอย่างดี  2 1/2(008)");
//		evt.setItem_price(45);
//		evt.setItem_unit_code("ตัว");
//		evt.setItem_whcode("S1-A");
//		evt.setItem_shelfcode("-");
//		evt.setRequest_qty(2);
//		
//		item.add(evt);
//		
//		evt = new SO_Req_ListItemToQueueBean();
//		evt.setItem_barcode("0190800");
//		evt.setItem_code("0190800");
//		evt.setItem_name("สีน้ำมัน TOA G-800 กล.");
//		evt.setItem_price(605);
//		evt.setItem_unit_code("แกลลอน");
//		evt.setItem_whcode("S1-A");
//		evt.setItem_shelfcode("-");
//		evt.setRequest_qty(1);
//		
//		item.add(evt);
//		
//		
//		req.setOtp_password("1234567890");
//		req.setPickup_datetime("18/11/2016");
//		req.setCar_brand("Toyota");
//		req.setPlate_number("0000");
//		req.setReceiver_name("moo");
//		req.setDoc_no("W01-SCV5911-2223");
//		
//		SO_Res_ListOwnerPhoneBean evt1;
//		evt1 = new SO_Res_ListOwnerPhoneBean();
//		evt1.setPhone_no("082-6298210");
//		owner_phone.add(evt1);
//		
//		req.setOwner_phone(null);
//		req.setReceiver_phone(null);
//
//		req.setItem(item);
//		
//		otp = ctl.SaleOrderToDriveThru("SmartQ",db, req);
	}

}
