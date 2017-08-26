package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenNewDocnoControllerTest {

	@Test
	public void test() {
		String newDocNo;
		
		GenNewDocnoController ct = new GenNewDocnoController();
		
		newDocNo = ct.genDocNo(0);
		
		System.out.println("NEw DocNo ="+newDocNo);
	}

}
