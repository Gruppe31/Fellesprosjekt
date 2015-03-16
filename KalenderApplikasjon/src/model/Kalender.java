package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysql.Connector;

public class Kalender {
	// Alt initialiseres i denne klassen. Derfor vil denne opprette alle nye avtaler som ligger i databasen.
	private Connector con;
	private int id;
	private int uke;
	ArrayList<Avtale> avtaler;
	ArrayList<Gruppe> grupper;
	
	public Kalender(){
		con = new Connector();
		avtaler = new ArrayList<Avtale>();
		grupper = new ArrayList<Gruppe>();
	}
	
	public ArrayList<Avtale> getAvtaler() throws Exception{
		ResultSet rs1 = con.les("SELECT KalenderID FROM Person WHERE(Brukernavn = '" + Context.getInstance().getPerson().getBrukernavn() + "')");
		while(rs1.next()){
			this.id = rs1.getInt("KalenderID");
		}
		
		ResultSet rs = con.les("SELECT * FROM avtale WHERE(KalenderID =" + this.id + ")");
		avtaler = new ArrayList<Avtale>();
		while(rs.next()){
			String avtaleIDString = rs.getString("AvtaleID");
			String fraTid = rs.getString("fraTid");
			String tilTid = rs.getString("tilTid");
			String dato = rs.getString("Dato");
			String tittel = rs.getString("Tittel");
			String beskrivelse = rs.getString("Beskrivelse");
			String oppdatert = rs.getString("oppdatert");
			String rom = rs.getString("Romnavn");
			String kalenderIDString = rs.getString("KalenderID");
			String leder = rs.getString("leder");
			//hente rom her.
			int kalenderID = Integer.parseInt(kalenderIDString);
			int avtaleID = Integer.parseInt(avtaleIDString);
			avtaler.add(new Avtale(fraTid, tilTid, dato, tittel, beskrivelse, oppdatert, rom, leder, avtaleID, kalenderID)); 
		}
		return avtaler;
	}
	
	public Person getBruker() throws Exception{
		ResultSet rs = con.les("SELECT * FROM Person WHERE(KalenderID =" + this.id + ")");
		Person bruker = new Person();
		while(rs.next()){
			String brukernavn = rs.getString("Brukernavn");
			String passord = rs.getString("Passord");
			String kalenderIDString = rs.getString("KalenderID");
			int kalenderID = Integer.parseInt(kalenderIDString);
			
			//Person har ikke konstrukt�r
			bruker.setBrukernavn(brukernavn);
			bruker.setPassord(passord);
			//Person klassen har ingen kalenderID felt 
			//bruker.setKalenderID(kalenderID);
		}
		return bruker;
	}
	
	public Gruppe getGruppe() throws Exception{
		ResultSet rs = con.les("SELECT * FROM gruppe WHERE(KalenderID =" + this.id + ")");
		Gruppe gruppe = new Gruppe();
		while(rs.next()){
			String gruppenavn = rs.getString("Gruppenavn");
			String kalenderIDString = rs.getString("KalenderID");
			
			int kalenderID = Integer.parseInt(kalenderIDString);
			
			gruppe = new Gruppe(gruppenavn, kalenderID);
		}
		return gruppe;
	}
	
	public void removeAvtale(Avtale avtale){
		avtaler.remove(avtale);
	}
	
	public void addAvtale(Avtale avtale){
		avtaler.add(avtale);
	}
	
	public void leggTilGruppe(Gruppe gruppe){
		grupper.add(gruppe);
	}
	
	public void setKalenderID(int id){
		this.id = id;
	}
	
	public int getKalenderID(){
		return this.id;
	}
	
	public void setUke(int uke){
		this.uke = uke;
	}
	
	public int getUke(){
		return this.uke;
		
	}
	
}
