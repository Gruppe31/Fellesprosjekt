package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysql.Connector;

public class Kalender {
	// Alt initialiseres i denne klassen. Derfor vil denne opprette alle nye avtaler som ligger i databasen.
	private Connector con;
	private int id;
	
	public Kalender(int i){
		this.id = i;
		con = new Connector();
	}
	
	public ArrayList<Avtale> getAvtaler() throws Exception{
		// Maa endre paa databasetabellen saa de blir riktig i forhold til kontrolleren og fxml skjema.
		ResultSet rs = con.les("SELECT * FROM avtale WHERE(KalenderID =" + this.id + ")");
		ArrayList<Avtale> avtaler = new ArrayList<Avtale>();
		while(rs.next()){
			String avtaleIDString = rs.getString("AvtaleID");
			String fraTid = rs.getString("fraTid");
			String tilTid = rs.getString("tilTid");
			String dato = rs.getString("Dato");
			String tittel = rs.getString("Tittel");
			String beskrivelse = rs.getString("Beskrivelse");
			String oppdatert = rs.getString("oppdatert");
			String rom = rs.getString("Romnavn");
			String kalenderID = rs.getString("KalenderID");
			String leder = rs.getString("leder");
			//hente rom her.
			int avtaleID = Integer.parseInt(avtaleIDString);
			avtaler.add(new Avtale(fraTid, tilTid, dato, tittel, beskrivelse, oppdatert, rom, leder, avtaleID, kalenderID)); 
		}
		return avtaler;
	}
	
	public Person getBrukere() throws Exception{
		ResultSet rs = con.les("SELECT * FROM Person WHERE(KalenderID =" + this.id + ")");
		Person bruker = new Person();
		while(rs.next()){
			String brukernavn = rs.getString("Brukernavn");
			String passord = rs.getString("Passord");
			String kalenderIDString = rs.getString("KalenderID");
			int kalenderID = Integer.parseInt(kalenderIDString);
			
			//Person har ikke konstruktør
			bruker.setBrukernavn(brukernavn);
			bruker.setPassord(passord);
			//Person klassen har ingen kalenderID felt 
			//bruker.setKalenderID(kalenderID);
		}
		return bruker;
	}
	
	public ArrayList<Gruppe> getGrupper() throws Exception{
		ResultSet rs = con.les("SELECT * FROM gruppe WHERE(KalenderID =" + this.id + ")");
		ArrayList<Gruppe> grupper = new ArrayList<Gruppe>();
		while(rs.next()){
			String gruppeIDString = rs.getString("GruppeID");
			String gruppenavn = rs.getString("Gruppenavn");
			String kalenderIDString = rs.getString("KalenderID");
			
			int gruppeID = Integer.parseInt(gruppeIDString);
			int kalenderID = Integer.parseInt(kalenderIDString);
			
			grupper.add(new Gruppe(gruppeID, gruppenavn, kalenderID));
		}
		return grupper;
	}
	
	public void removeAvtale(Avtale avtale){
		
	}
	public void addAvtale(Avtale avtale){
		
	}
}
