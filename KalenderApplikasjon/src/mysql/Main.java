package mysql;

import java.sql.ResultSet;


public class Main {
	public static void main(String[] args) throws Exception {
	    Connector con = new Connector();
		ResultSet rs = con.les("SELECT Brukernavn FROM Bruker");
		while(rs.next()){
			String bruker = rs.getString("Brukernavn");
			System.out.println(bruker);
		}
	    
	}
}
