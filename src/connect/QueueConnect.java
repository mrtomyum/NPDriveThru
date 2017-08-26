package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum QueueConnect {

INSTANCE;
	private static final String url = "jdbc:mysql://192.168.0.89:3306/qdb?useUnicode=true&characterEncoding=utf-8";
	//private static final String url = "jdbc:mysql://report.nopadol.com:3306/qdb?useUnicode=true&characterEncoding=utf-8";
	private static final String user = "it";
	private static final String pwd = "[ibdkifu";
	//private static final String user = "satit";
	//private static final String pwd = "[ibdkifu88";

	private Connection conn = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	public Statement getStatement() throws SQLException {
		Statement stmt = null;
		// get connection
		//System.out.println(url);
		conn = DriverManager.getConnection(url, user, pwd );
		stmt = conn.createStatement();
		
		return stmt;
	}
	
	public Statement getStatement(String urlpassing) throws SQLException {
		Statement stmt = null;
		urlpassing = "jdbc:mysql://192.168.0.89:3306/"+urlpassing+"?useUnicode=true&characterEncoding=utf-8";
		//urlpassing = "jdbc:mysql://report.nopadol.com:3306/"+urlpassing+"?useUnicode=true&characterEncoding=utf-8";
		 //System.out.println(urlpassing);
		// get connection
		conn = DriverManager.getConnection(urlpassing, user, pwd );
		stmt = conn.createStatement();
		
		return stmt;
	}

//---------------------------------------
	
	public static Connection getConnection() {
		 try  {
			 Connection conn = DriverManager.getConnection(url, user, pwd);
			 	
	            return conn;
	        }
	        catch(Exception ex) {
	            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
	            return null;
	        }
	    }
	 public static void close(Connection con) {
	        try  {
	            con.close();
	        }
	        catch(Exception ex) {
	        }
	     }
	     
//------------------------------------
	

	public void close() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


