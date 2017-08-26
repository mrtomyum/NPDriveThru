package controller;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import connect.QueueConnect;

public class TestInsertPicture {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	
	ExcecuteController excecute = new  ExcecuteController();
	
	public boolean insertPic() {

	    boolean isInsert;
	    
	    System.out.println("Moo");
	    
	    isInsert = true;

	    try {
	    	String filePath = "E:/Private/Moo/shogoon.jpg";
	    	InputStream inputStream = new FileInputStream(new File(filePath));
	    	
	    	InputStream is = new BufferedInputStream(new FileInputStream("source.gif"));
	        Image image = ImageIO.read(is);
	    	 
	    	String sql = "INSERT INTO MyPicture (photo) values ("+image+")";
	    	
	    	InputStream inputstream = new FileInputStream("c:\\data\\input-text.txt");

	    	int data = inputstream.read();
	    	while(data != -1) {
	    	  //do something with data...
	    	  //doSomethingWithData(data);

	    	  data = inputstream.read();
	    	}
	    	inputstream.close();
	    	
	    	
	    	System.out.println("inputStream : "+inputStream);
	    	System.out.println("sql : "+sql);
	    	
	    	//isInsert= excecute.execute("SmartQ",sql);
	    	
	    	inputStream.close();
	    	
	    	System.out.println("inputStream");
	    }
	    catch(Exception e){
	    	isInsert = false;
	    }
	     finally {
	      ds.close();
	    }
	    
	    return isInsert;
	  }
	
}
