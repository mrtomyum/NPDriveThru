package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.request.DT_User_LoginBranchBean;

public enum NPSQLConn {
	INSTANCE;
	private static final String url = "jdbc:sqlserver://192.168.0.7; databaseName=bcnp;useUnicode=true;characterEncoding=utf-8";
	//private static final String url = "jdbc:mysql://192.168.0.89:3306/qdb?useUnicode=true&characterEncoding=utf-8";
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
	
	public Statement getSqlStatement(String svName,String urlpassing) throws SQLException {
		Statement stmt = null;
		urlpassing = "jdbc:sqlserver://"+svName+"; databaseName="+urlpassing+";useUnicode=true;characterEncoding=utf-8";
		 //System.out.println("getSqlStatement"+urlpassing);
		// get connection
		conn = DriverManager.getConnection(urlpassing, user, pwd );
		stmt = conn.createStatement();

		return stmt;
	}
	
	
	public Statement getSqlStatementBranch(DT_User_LoginBranchBean sqlData) throws SQLException {
		Statement stmt = null;
		String urlpassing;
		urlpassing = "jdbc:sqlserver://"+sqlData.getServerName()+"; databaseName="+sqlData.getDbName()+";useUnicode=true;characterEncoding=utf-8";
		 //System.out.println("getSqlStatementBranch:"+urlpassing);
		// get connection
		conn = DriverManager.getConnection(urlpassing, user, pwd );
		stmt = conn.createStatement();
		
		
		System.out.println("This Connect: getSqlStatementBranch :"+urlpassing);

		return stmt;
	}
	
	//---------------------------------------
	
		public boolean checkConnect(String serverName,String databaseName,String userid,String password) {
			Statement stmt = null;
			String urlpassing;
			
			try{
				urlpassing = "jdbc:sqlserver://"+serverName+"; databaseName="+databaseName+";useUnicode=true;characterEncoding=utf-8";
				System.out.println("urlpassing"+userid+","+password);
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
