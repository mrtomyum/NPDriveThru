package controller;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


import bean.LoginBean;
import bean.UserBean;
import bean.UserRegisterBean;
import bean.UserSearchBean;
import bean.UserSearchResponseBean;




public class UserControllerTest {
	
	UserController msc = new  UserController();
	UserBean user = new UserBean();
	LoginBean login = new LoginBean();
	UserRegisterBean userRegister = new UserRegisterBean();
	UserSearchBean userSearch = new UserSearchBean();
	
	@Test 
//	@Ignore
	public void loginTest() {
		
		login.setBranchId(1);
		login.setEmployeeCode("46110");
		
		msc.login("SmartConfig",login);
		assertEquals(msc.response.getIsSuccess(),true);
	}
	@Test
	@Ignore
	public void searchTest() {
		
		userSearch.setAccess_token("404c4b98-5109-49a6-a48f-98600156ddee");
		userSearch.setKeyword("1");
				
		msc.userSearch("SmartConfig", userSearch);
		
		assertEquals(msc.response.getIsSuccess(),true);
	}

	@Test
	@Ignore
	public void registerTest() {
		
		userRegister.setAccessToken("404c4b98-5109-49a6-a48f-98600156ddee");
		user.setCode("11113");
		user.setName("register test");
		user.setCreatorCode("46110");
//		user.setCreatedateTime("2015-07-18 09:42:28.740");
		
		userRegister.setUser(user);
		
		msc.register("SmartConfig",userRegister);
		
		assertEquals(msc.response.getIsSuccess(),true);
	}
	
	@Test
	@Ignore
	public void editTest() {
		
		userRegister.setAccessToken("404c4b98-5109-49a6-a48f-98600156ddee");
		user.setId(7);
		user.setCode("11113");
		user.setName("edit test");
		user.setLasteditorCode("46110");
		
		userRegister.setUser(user);
		
		msc.userEdit("SmartConfig",userRegister);
		
		assertEquals(msc.response.getIsSuccess(),true);
	}
	
	
//	@Test
//	//@Ignore
//	public void moveTest() {
//		boolean testmove;
//		String picName ;
//		
//		picName = "D:\\Moo\\del.jpg";
//	
//		File afile =new File(picName);
//		
//		testmove = msc.TransferPic(afile);
//		
//		System.out.println("Transfer");
//		
//		//assertEquals(msc.TransferPic(),true);
//	}


}
