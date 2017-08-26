package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bean.request.CT_Req_SearchInvoiceBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_ListItemToQueueBean;
import bean.request.SO_Req_ListSOItemToPickupBean;
import bean.response.DT_Res_CompanyBean;
import bean.response.SO_Res_GenQueueRespBean;
import bean.response.SO_Res_ListOwnerPhoneBean;
import bean.response.SO_Res_SaleOrderBean;
import bean.response.SO_Res_SaleOrderInvoiceBean;
import connect.bCryptPassword;

public class DriveThruTest {
	
	
	@Test
	public void test() {
		GenNewDocnoController gen = new GenNewDocnoController();
		String serverName;
		String dataBaseName;
		String PosPoint;
		String vPosNo;
		
		PosPoint = "13";
		
		serverName="192.168.0.26";
		dataBaseName="pos_test";
		
		
		vPosNo = gen.genPOSInvoiceNo(PosPoint,serverName,dataBaseName);
		
		System.out.println("PosNo"+vPosNo); 

	}
	

//	@Test
//	public void test() {
//		
//		bCryptPassword cn = new bCryptPassword();
//		getDataFromData getdb = new getDataFromData();
//		String password = "8125";
//		String hash = "$2a$12$Oeu3GYlZgCKRJ1OB/EByOOFbsQ7IrmNnpVlMYZ.lw.dp.FLEnK1zS";
//		
//		String decodeStr = getdb.EnCoding("$2a$12$tWIHPuhpQhaAUju.f1qoF.1BuNJ9BmFH6z6UZo.PwjOi/rZ2e7rTq");
//
//		//String computed_hash = cn.hashPassword(password);
//		System.out.println("decodeStr : " + decodeStr);
//		System.out.println("hash: " + hash);
//		//System.out.println("computed hash: " + computed_hash);
//
//		String compare_computed = cn.checkPassword(password, hash)? "Passwords Match" : "Passwords do not match";
//
//		System.out.println("Verify against computed hash: " + compare_computed);
//		
//		//System.out.println(check);
//	}
	
	
//	@Test
//	public void test3() {//Company and Zone
//		String pin;
//		DriveThruController ctl = new DriveThruController();
//		getDataFromData  gdt = new getDataFromData();
//		
//		pin = gdt.GenPinOTP();
//		
//		
//		System.out.println(pin);
//	}
	
//	@Test
//	public void test2() {//Company and Zone
//		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
//		db.setServerName("192.168.0.7");
//		db.setDbName("bcnp");
//		DriveThruController ctl = new DriveThruController();
//		SO_Res_SaleOrderInvoiceBean company = new SO_Res_SaleOrderInvoiceBean();
//		CT_Req_SearchInvoiceBean req = new CT_Req_SearchInvoiceBean();
//		
//		req.setAccess_token("29983666-79ab-440f-8976-2779ea3ca70b");
//		req.setInvoice_no("W01-ICV6004-0001");
//		
//		company = ctl.SaleOrderInvoiceDetails(db,req);
//		
//		
//		System.out.println(company.getAr_name());
//	}
//	
//	public void test1() {//SaleOrder
//		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
//		DriveThruController ctl = new DriveThruController();
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
//		evt.setItem_barcode("8852278010401");
//		evt.setItem_code("8852278010401");
//		evt.setItem_unit_code("ถุง");
//		evt.setRequest_qty(20);
//		
//		item.add(evt);
		
//		
//		
//		evt.setItem_barcode("3300725");
//		evt.setItem_id("3300725");
//		evt.setItem_name("แปรงทาสีอย่างดี  2 1/2(008)");
//		evt.setItem_price(45);
//		evt.setItem_unit_code("ตัว");
//		evt.setItem_whcode("S1-A");
//		evt.setItem_shelfcode("-");
//		evt.setQty(2);
//		
//		item.add(evt);
//		
//
//		evt.setItem_barcode("0190800");
//		evt.setItem_id("0190800");
//		evt.setItem_name("สีน้ำมัน TOA G-800 กล.");
//		evt.setItem_price(605);
//		evt.setItem_unit_code("แกลลอน");
//		evt.setItem_whcode("S1-A");
//		evt.setItem_shelfcode("-");
//		evt.setQty(1);
		
//item.add(evt);
		
//		
//		
//		req.setPickup_datetime("2016/11/18 17:00:00");
//		req.setCar_brand("Toyota");
//		req.setPlate_number("0000");
//		req.setReceiver_name("moo");
//		req.setDoc_no("W01-ICV6004-0001");
//
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
//
//	}

}
