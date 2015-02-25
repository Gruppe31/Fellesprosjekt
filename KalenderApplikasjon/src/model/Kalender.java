package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysql.Connector;

public class Kalender {
	Connector con = new Connector();
	int id;
	
	public Kalender(int i){
		this.id = i;
	}
	
	public ArrayList<Avtale> getAvtaler() throws Exception{
		ResultSet rs = con.les("SELECT * FROM avtale WHERE(KalenderID =" + this.id + ")");
		ArrayList<Avtale> avtaler = new ArrayList<Avtale>();
		while(rs.next()){
			String avtaleID = rs.getString("AvtaleID");
			String tid = rs.getString("Starttid");
			String dato = rs.getString("Dato");
			String tittel = rs.getString("Tittel");
			String beskrivelse = rs.getString("Beskrivelse");
			String oppdatert = rs.getString("oppdatert");
			String kalenderID = rs.getString("KalenderID");
			avtaler.add(new Avtale(avtaleID, tid, dato, tittel, beskrivelse, oppdatert, kalenderID));
		}
		return avtaler;
	}
	
	public ArrayList<Avtale> getBrukere(){
		
	}
	
	public void removeAvtale(Avtale avtale){
		
	}
	public void addAvtale(Avtale avtale){
		
	}
}
