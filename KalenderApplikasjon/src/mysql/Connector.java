package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Connector {
  private Connection connect = null;
  private Statement statement = null;
  private ResultSet resultSet = null;
  private PreparedStatement preparedStatement = null;
  private String url = "jdbc:mysql://78.91.47.120/fp";
  private String user = "kalender";
  private String password = "ok";
  
  public void start() throws Exception{
	  	try {
		  
		  
	      Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager.getConnection(url, user, password);
	    } catch (Exception e) {
	      throw e;
	    }
  }

  public ResultSet les(String sqlstatement) throws Exception {
	  start();
	  statement = connect.createStatement();
      resultSet = statement.executeQuery(sqlstatement);
      return resultSet;
  }
  
  public int skriv(String sqlstatement) throws Exception {
	  start();
	  preparedStatement = connect.prepareStatement(sqlstatement);
	  return preparedStatement.executeUpdate();
  }

  public void close() throws Exception {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {
    	throw e;
    }
  }

} 