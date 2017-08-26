package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;






import bean.BranchBean;
import bean.UserBean;
import controller.BranchController;
import controller.UserController;


@Path(value="/")
public class Branch {
//		@Path("/getSearchx/") 
		@Path("/branch") 
		@GET
		@Consumes( {MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})

	public List<BranchBean> branchView() throws IOException {
		BranchController osc = new  BranchController();
		List<BranchBean> listBranch = new ArrayList<BranchBean>();
		
		listBranch=osc.getBranch("SmartConfig");
		
		try {
			System.out.println(listBranch.get(0).getCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listBranch;

	}
	

}
