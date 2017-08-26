package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.PT_Reqs_MovePictureBean;
import bean.response.CT_Resp_ResponseBean;

import java.io.File;
import java.io.IOException;

public class PictureControllerTest {

	PictureController ptc = new  PictureController();
	CT_Resp_ResponseBean resp = new CT_Resp_ResponseBean();
	
	@Test
	//@Ignore
	public void moveTest() {
		boolean testmove;
		String picName ;
		String toName ;
		File f = null;
		
		PT_Reqs_MovePictureBean file = null;
		CT_Resp_ResponseBean c;
		
		picName = "http://qserver.nopadol.com/tmp/111.jpg";
		
		toName = "//var//www//html//tmp//111.jpg";
		
		//file.setPictureName(picName);
		
		 //ptc.p(toName);
		
		//PT_Reqs_MovePictureBean file = new PT_Reqs_MovePictureBean();
		
		//file.setModuleType("Item");
		//file.setPictureName("111.jpg");
		
		//picName = "http://qserver.nopadol.com/pictures/111.jpg";
	
		//File afile =new File(picName);
		
		//resp = ptc.download("http://qserver.nopadol.com/tmp/111.jpg","111.jpg");
		
		//System.out.println("Transfer :"+testmove);
		
		//assertEquals(msc.TransferPic(),true);
	}

}
