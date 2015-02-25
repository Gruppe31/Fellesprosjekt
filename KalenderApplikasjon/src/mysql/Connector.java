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
  private ResultSet resultSet = null;

  public ResultSet les(String sqlstatement) throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connect = DriverManager.getConnection("jdbc:mysql://localhost/fp?" + "user=root&password=passwd");

      statement = connect.createStatement();
      resultSet = statement.executeQuery(sqlstatement);
      return resultSet;
    } catch (Exception e) {
      throw e;
    } finally {
      close();
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