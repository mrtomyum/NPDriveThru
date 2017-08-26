package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bean.request.DT_User_LoginBranchBean;
import bean.request.SO_Req_GenOTPSaleOrderBean;
import bean.request.SO_Req_ListItemToQueueBean;
import bean.response.CT_Res_CompanyDataBean;
import bean.response.CT_Resp_ResponseBean;

public class getDataFromDataTest {

	@Test
	public void test() {
		int billCount=0;
		
		getDataFromData data = new getDataFromData();
		CT_Resp_ResponseBean da = new CT_Resp_ResponseBean();
		DT_User_LoginBranchBean db = new DT_User_LoginBranchBean();
		SO_Req_GenOTPSaleOrderBean so = new SO_Req_GenOTPSaleOrderBean();
		List<SO_Req_ListItemToQueueBean> item = new ArrayList<SO_Req_ListItemToQueueBean>();
		
		db.setServerName("192.168.0.7");
		db.setDbName("nptest");
	
		so.setReceiver_name("moo");
		so.setPickup_datetime("2017-02-01");
		so.setCar_brand("toyata");
		so.setPlate_number("0000");
		so.setDoc_no("S01-ROV5911-0295");
		so.setDelivery_type(0);
		so.setBill_type(0);
		so.setTax_type(1);
		so.setAccess_token("2bd347de-1d42-409e-b68b-a8b384ba3852");
		
		SO_Req_ListItemToQueueBean evt;
		
		evt = new SO_Req_ListItemToQueueBean();
		
		evt.setItem_barcode("5501295");
		evt.setItem_code("5501295");
		evt.setItem_unit_code("เส้น");
		evt.setRequest_qty(1);
		evt.setLine_number(0);
		item.add(evt);
		
		evt.setItem_barcode("8852429411286");
		evt.setItem_code("8852429411286");
		evt.setItem_unit_code("แผ่น");
		evt.setRequest_qty(1);
		evt.setLine_number(1);
		
		item.add(evt);
		
		so.setItem(item);
		
		System.out.println(so.getItem().get(1).getItem_barcode());
		

		da = data.verifyItemSaleOrder(db, so);
		
		System.out.println("BranchID : "+da.getIsSuccess());
		
	}

}
