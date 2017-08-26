package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.PT_Reqs_MovePictureBean;
import bean.response.CT_Resp_ResponseBean;
import controller.PictureController;

@Path(value="/picture")
public class Picture {
	@POST
	@Path("/move")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CT_Resp_ResponseBean moveFile(PT_Reqs_MovePictureBean file){
		CT_Resp_ResponseBean move = new CT_Resp_ResponseBean();
		PictureController ptc = new PictureController();
		
		move = ptc.p(file);
		return move;
	}
	

	@POST
	@Path("/item")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CT_Resp_ResponseBean movePicItem(PT_Reqs_MovePictureBean file){
		CT_Resp_ResponseBean move = new CT_Resp_ResponseBean();
		PictureController ptc = new PictureController();
		
		move = ptc.savePicItem("pos", file);
		return move;
		
	}
	
	
	@POST
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CT_Resp_ResponseBean movePicUser(PT_Reqs_MovePictureBean file){
		CT_Resp_ResponseBean move = new CT_Resp_ResponseBean();
		PictureController ptc = new PictureController();
		
		move = ptc.savePicUser("pos", file);
		return move;
	}

}
