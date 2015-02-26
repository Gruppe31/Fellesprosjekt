package mysql;

import java.sql.ResultSet;

public class Main {
	public static void main(String[] args) throws Exception {
	    Connector dao = new Connector();
	    dao.skriv("INSERT INTO kuer values (45)");
	    
	    dao.close();
	}
}
