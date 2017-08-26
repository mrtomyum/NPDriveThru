package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.LoginBean;
import bean.LoginResponseBean;
import bean.request.ReqItemEditBean;
import bean.request.ReqItemSearchBean;
import bean.response.ApiItemEditBean;
import bean.response.ApiItemSearchBean;
import controller.ItemController;
import controller.UserController;

@Path(value="/item")
public class Item {

	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ApiItemSearchBean itemSearch(ReqItemSearchBean req ) {
		ItemController item = new  ItemController();
		ApiItemSearchBean Res = new ApiItemSearchBean();
		
		
		Res=item.itemSearch("SmartQ", req);
		
	
		return Res;
	}
	
	@POST
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ApiItemEditBean itemEdit(ReqItemEditBean req ) {
		ItemController item = new  ItemController();
		ApiItemEditBean Res = new ApiItemEditBean();
		
		
		Res=item.itemEdit("SmartQ", req);
		
	
		return Res;
	}
	
	
}
