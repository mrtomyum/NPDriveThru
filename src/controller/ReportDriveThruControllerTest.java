package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.RP_Reqs_SearchQueBetweenDateBean;
import bean.RP_Resp_CompareDataDriveThruBean;
import bean.RP_Resp_QueueMasterBean;

public class ReportDriveThruControllerTest {

	@Test
	public void test() {
		RP_Reqs_SearchQueBetweenDateBean search = new RP_Reqs_SearchQueBetweenDateBean();
		RP_Resp_QueueMasterBean dataList = new RP_Resp_QueueMasterBean();
		ReportDriveThruController rcl = new ReportDriveThruController();
		RP_Resp_CompareDataDriveThruBean compareData = new RP_Resp_CompareDataDriveThruBean();
		
		search.setBeginDate("2016-08-01");
		search.setEndDate("2016-08-10");
		
		compareData = rcl.compareData("SmartQ", search);
		
		System.out.println("pickup:"+compareData.getDetails().get(0).getCountPickup());
		
	}

}
