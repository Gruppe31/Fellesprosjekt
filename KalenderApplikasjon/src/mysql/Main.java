package mysql;

import java.sql.ResultSet;


public class Main {
	public static void main(String[] args) throws Exception {
	    Connector dao = new Connector();
	    ResultSet rs = dao.les("SELECT antall FROM kuer");
	    
	    while(rs.next()){
	    	System.out.println(rs.getString("Antall"));
	    }
	    
	}
}
