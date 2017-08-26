package controller;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import bean.request.*;
import bean.response.*;
import bean.*;


public class ItemContollerTest {
	ItemController itm = new  ItemController();
	UserController  usc = new UserController();
	
	
	@Test 
	//@Ignore
	public void Login(){
		LoginBean userBean = new LoginBean();
		userBean.setEmployeeCode("41054");
		userBean.setBranchId(1);
		LoginResponseBean user = usc.login("SmartConfig", userBean);
		System.out.println(user.getAccessToken());
		System.out.println(user.getUser().getName());
		
		assertEquals(user.getUser().getName(),"satit");
	}
	
	@Test 
	@Ignore
	public void InitialResItemSearchBean() {
		ReqItemSearchBean req = new ReqItemSearchBean();
		
		
		LoginBean userBean = new LoginBean();
		userBean.setEmployeeCode("01");
		userBean.setBranchId(1);
		
		LoginResponseBean user = usc.login("SmartConfig", userBean);
		System.out.println(user.getAccessToken());
		System.out.println(user.getUser().getName());
		
		
		req.setAccess_token(user.getAccessToken());
		//req.setKeyword("8852429411460");
		req.setKeyword("2120250");
		req.setType(0);
		
		ApiItemSearchBean res = itm.itemSearch("SmartQ", req);
		
		
		//System.out.println(res.getResponse());
		assertEquals(itm.itemlist.get(0).getItem_barcode(),"2120250");
	}
	
	
	@Test
	//@Ignore
	public void ItemEdit_Update_shortCode()  {
		System.out.println("Test ItemEdit ");
		ReqItemEditBean  req = new ReqItemEditBean();
		
			
		LoginBean userBean = new LoginBean();
		userBean.setEmployeeCode("41054");
		userBean.setBranchId(1);
		
		LoginResponseBean user = usc.login("SmartConfig", userBean);
		System.out.println(user.getAccessToken());
		System.out.println(user.getUser().getName());
		
		req.setAccess_token(user.getAccessToken());
		req.setBarCode("0088536");
		req.setFilePath("/picture/product/0088536.jpg");
		req.setRemark("Test Update ");
		req.setShortCode("999");
		
		ApiItemEditBean res=itm.itemEdit("SmartQ", req);
		System.out.println(res.getResponse().getIsSuccess());
		System.out.println(res.getResponse().getProcessDesc());
		assertEquals(res.getResponse().getIsSuccess(),true);
		
	}
	
	
}
