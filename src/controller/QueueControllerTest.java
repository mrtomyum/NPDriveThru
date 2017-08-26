package controller;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.Object;
import java.util.Timer;

import org.junit.Test;

public class QueueControllerTest {

	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
	
	@Test
	public void test() {
		
		dtDoc.applyPattern("yyyy-MM-dd");
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Date dateNow = new Date();

		
		System.out.println(dateFormat.format(dateNow));
	}

}
