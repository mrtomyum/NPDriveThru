package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bean.PK_Reqs_ManageItemBean;
import bean.PK_Reqs_DeleteOrderBean;
import bean.PK_Reqs_EditOrderBean;
import bean.CT_Reqs_NewDocNoBean;
import bean.PK_Resp_ManageItemBean;
import bean.PK_Resp_NewOrderHeaderBean;
import bean.PK_Resp_OrderPendingBean;
import bean.SearchBean;

public class PickupControllerTest {

	@Test
	public void test() {
		SearchBean keyword = new SearchBean();
		List<PK_Resp_OrderPendingBean> orderList = new ArrayList<PK_Resp_OrderPendingBean>();
		PK_Resp_NewOrderHeaderBean resDocNo = new PK_Resp_NewOrderHeaderBean();
		CT_Reqs_NewDocNoBean reqDocNo = new CT_Reqs_NewDocNoBean();
		PK_Reqs_EditOrderBean reqEdit = new PK_Reqs_EditOrderBean();
		PK_Reqs_DeleteOrderBean delOrder = new PK_Reqs_DeleteOrderBean();
		PickupController pcl = new PickupController();
		
		PK_Reqs_ManageItemBean resItem = new PK_Reqs_ManageItemBean();
		PK_Resp_ManageItemBean repItem = new PK_Resp_ManageItemBean();
		
		
		
		boolean vget;
		boolean getDel;
		
		keyword.setKeyword("");
		
		//orderList = pcl.searchOrderPending("SmartQ", keyword);
		
		//resDocNo = pcl.searchNewOrder("SmartQ", reqDocNo);
		
		reqEdit.setCarNumber("1979");
		reqEdit.setqId(3);
		
		
		resItem.setAccess_token("fda962f1-1e4d-465d-b7ce-79f59c173da9");
		resItem.setBarCode("0088536");
		resItem.setQtyBefore(15);
		resItem.setqId(3);
		resItem.setIsCancel(0);
		
		
		
		//vget = pcl.editOrder("SmartQ", reqEdit);
		
		delOrder.setqId(3);
		
		//getDel = pcl.deleteOrder("SmartQ", delOrder);
		
		//repItem = pcl.insertItemPickup("SmartQ", resItem);
		
		//System.out.println(repItem.getError_message());
		
		//System.out.println(orderList.get(0).getCarNumber());
		//System.out.println(resDocNo.getqId());
		//System.out.println(resDocNo.getErrorMessage());
		//System.out.println(repItem.getError_message());
	}

}
