package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.CM_Resp_SearchCustomerBean;
import bean.SearchBean;

public class CustomerControllerTest {
	 
	SearchBean keyword = new SearchBean();

	@Test
	public void test() {
		CustomerController cust = new CustomerController();
		CM_Resp_SearchCustomerBean respCust = new CM_Resp_SearchCustomerBean();
		
		keyword.setAccess_token("");
		keyword.setKeyword("A153");
		
		respCust = cust.searchCustomer("POS", keyword);
		
		System.out.println("Test : "+respCust.getMember().get(0).getArCode());
	}

}
