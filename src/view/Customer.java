package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.CustomerController;

import bean.CM_Resp_SearchCustomerBean;
import bean.SearchBean;

@Path(value="/customer")
public class Customer {
	
	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CM_Resp_SearchCustomerBean searchCustomer(SearchBean keyword){
		CustomerController cust = new CustomerController();
		CM_Resp_SearchCustomerBean respCust = new CM_Resp_SearchCustomerBean();
		
		//respCust = cust.searchCustomer("nptest", keyword);
		respCust = cust.searchCustomer("bcnp", keyword);
		
		return respCust;
	}
	
	

}
