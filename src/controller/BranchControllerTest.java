package controller;

import static org.junit.Assert.*;
//import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Test;



public class BranchControllerTest {

	BranchController osc = new  BranchController();
		
	@Test 
//	@Ignore
	public void getBranch() {
		osc.getBranch("SmartConfig");
		assertEquals(osc.listBranch.get(0).getCode(),"S01");
	}

}
