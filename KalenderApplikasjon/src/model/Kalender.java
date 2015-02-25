package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysql.Connector;

public class Kalender {
	private Connector con = new Connector();
	private int id;
	
	public Kalender(int i){
		this.id = i;
	}
	
	public ArrayList<Avtale> getAvtaler() throws Exception{
		ResultSet rs = con.les("SELECT * FROM avtale WHERE(KalenderID =" + this.id + ")");
		ArrayList<Avtale> avtaler = new ArrayList<Avtale>();
		while(rs.next()){
			String avtaleIDString = rs.getString("AvtaleID");
			String tid = rs.getString("Starttid");
			String dato = rs.getString("Dato");
			String tittel = rs.getString("Tittel");
			String beskrivelse = rs.getString("Beskrivelse");
			String oppdatert = rs.getString("oppdatert");
			String kalenderID = rs.getString("KalenderID");
			avtaler.add(new Avtale(avtaleID, tid, dato, tittel, beskrivelse, oppdatert, kalenderID)); //Maa kanskje legge til rom og en liste over personer som kommer.
		}
		return avtaler;
	}
	
	public ArrayList<Avtale> getBrukere(){
		ResultSet rs = con.les("SELECT * FROM bruker WHERE(KalenderID =" + this.id + ")");
		ArrayList<Avtale> brukere = new ArrayList<Avtale>();
		while(rs.next()){
			String brukernavn = rs.getString("Brukernavn");
			String passord = rs.getString("Passord");
			String kalenderIDString = rs.getString("KalenderID");
			
			int kalenderID = Integer.parseInt(kalenderIDString);
			
			brukere.add(new Bruker(brukernavn, passord, kalenderID));
		}
		return brukere;
	}
	
	public ArrayList<Gruppe> getGrupper{
		ResultSet rs = con.les("SELECT * FROM gruppe WHERE(KalenderID =" + this.id + ")");
		ArrayList<Avtale> grupper = new ArrayList<Avtale>();
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