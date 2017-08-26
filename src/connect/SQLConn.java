package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum SQLConn {
	INSTANCE;
	private static final String url = "jdbc:sqlserver://192.168.0.7; databaseName=bcnp;useUnicode=true;characterEncoding=utf-8";
	//private static final String url = "jdbc:sqlserver://192.168.0.182; databaseName=pos;useUnicode=true;characterEncoding=utf-8";
	private static final String user = "sa";
	private static final String pwd = "[ibdkifu";

	private Connection conn = null;
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	

	public Statement getSqlStatement() throws SQLException {
		Statement stmt = null;
		// get connection
		System.out.println(url);
		conn = DriverManager.getConnection(url, user, pwd );
		stmt = conn.createStatement();
		
		return stmt;
	}
	
	public Statement getSqlStatement(String urlpassing) throws SQLException {
		Statement stmt = null;
		urlpassing = "jdbc:sqlserver://192.168.0.7; databaseName="+urlpassing+";useUnicode=true;characterEncoding=utf-8";
		//urlpassing = "jdbc:sqlserver://192.168.0.182; databaseName="+urlpassing+";useUnicode=true;characterEncoding=utf-8";
		 //System.out.println(urlpassing);
		// get connection
		conn = DriverManager.getConnection(urlpassing, user, pwd );
		stmt = conn.createStatement();

		return stmt;
	}
	
	public Statement getSqlStatement(String serverName,String databaseName) throws SQLException {
		Statement stmt = null;
		String urlpassing;
		urlpassing = "jdbc:sqlserver://"+serverName+"; databaseName="+databaseName+";useUnicode=true;characterEncoding=utf-8";
		conn = DriverManager.getConnection(urlpassing, user, pwd );
		stmt = conn.createStatement();

		return stmt;
	}
	
	//---------------------------------------
	
		public boolean checkConnect(String serverName,String databaseName,String userid,String password) {
			Statement stmt = null;
			String urlpassing;
			
			System.out.println("LOGIN TEST"+serverName+"---"+databaseName);
			
			try{
				urlpassing = "jdbc:sqlserver://"+serverName+"; databaseName="+databaseName+";useUnicode=true;characterEncoding=utf-8";
				System.out.println("checkConnect urlpassing ="+userid+","+password);
				Connection conn = DriverManager.getConnection(urlpassing, userid, password );
				return true;
			}
	        catch(SQLException ex) {
	            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
	            return false;
				
			}
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
