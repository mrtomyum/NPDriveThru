package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemContollerTestTest {

	@Test
	public void test() {
		//String vText = "http://qserver.nopadol.com/pictures/item/5c4b20db2c473461d42cc24034c8e693.jpg";
		String Str = new String("http://qserver.nopadol.com/drivethru/tmp/6cdba17567b0ba192fa82a427b7c762a.jpg");
		
	      String SubStr1 = new String("tmp");
	      String SubStr2 = new String("tmp");


	      System.out.println( Str.indexOf(SubStr1 ));
	      System.out.println(Str.length());
	      System.out.println(Str.substring(Str.indexOf( SubStr1)+4, Str.length()));

	      
	}

}
