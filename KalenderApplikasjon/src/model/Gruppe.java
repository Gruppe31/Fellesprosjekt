package model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mysql.Connector;

public class Gruppe {
	
	private String gruppenavn;
	private int kalenderID;
	private List<String> brukerListe = new ArrayList();
	
	private Connector con = new Connector();
	
	public Gruppe(){
		// default konstrukt�r.
		this.gruppenavn = null;
		this.kalenderID = 0;
		
	}
	
	public Gruppe(String gruppenavn, int kalenderID){
		this.gruppenavn = gruppenavn;
		this.kalenderID = kalenderID;
	}

	public String getGruppenavn(){
		return this.gruppenavn;
	}

	public void leggTilGruppe(String person){
		this.brukerListe.add(person);
	}
	
	public void slettPersonFraGruppe(Person person){
		this.brukerListe.remove(person);
	}
	
	public List getGruppeliste(){
		return this.brukerListe;
	}
	
	public void databaseSettInn() throws Exception{
		String s1 = "INSERT INTO Gruppe VALUES ('0','" + gruppenavn + "')";
		con.skriv(s1);
		ResultSet rs = con.les("SELECT GruppeID FROM Gruppe ");
		int gruppeID = 0;
		while(rs.next()){
			gruppeID = rs.getInt("GruppeID");
		}
		for(String deltaker : brukerListe){
			String s2 = "INSERT INTO Brukergruppe VALUES('" + deltaker + "'," + gruppeID +  ")";
			con.skriv(s2);
		}
		String s3 = "INSERT INTO Kalendergruppe VALUES(" + kalenderID + "," + gruppeID + ")";
		con.skriv(s3);
	}
	public String toString(){
		return gruppenavn + " " + kalenderID + " " + brukerListe.toString();
	}
}
