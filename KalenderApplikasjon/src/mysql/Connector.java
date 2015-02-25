package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Connector {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connect = DriverManager.getConnection("jdbc:mysql://localhost/feedback?" + "user=oystein&password=sqluserpw");

      statement = connect.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM comments");
      writeResultSet(resultSet);
      
    } catch (Exception e) {
    	throw e;
    } finally {
      close();
    }

  }


  private void writeResultSet(ResultSet resultSet) throws SQLException {
    while (resultSet.next()) {
      String user = resultSet.getString("myuser");
      String website = resultSet.getString("webpage");
      String summary = resultSet.getString("summary");
      Date date = resultSet.getDate("datum");
      String comment = resultSet.getString("comments");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("summary: " + summary);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }
  private void close() {
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

    }
  }

} 