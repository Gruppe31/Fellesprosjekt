package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import mysql.Connector;

// Avtale spør databasen hvem som kommer

// Har ikke løst:
// Hvordan vite hvilke person spm kommer

public class Avtale {
	private Connector con = new Connector();
	
	private String leder;
	private String fraTid;
	private String tilTid;
	private String avtaleID;
	private String dato;
	private String tittel;
	private String beskrivelse;
	private String oppdatert;
	private Rom rom;
	private ArrayList<Person> invitert;
	private ArrayList<Person> folkSomIkkeKommer;
	
	public Avtale(String avtaleID, String fraTid, String tilTid, String dato, String tittel, String beskrivelse, String oppdatert, String kalenderID, String rom, ArrayList<Person> invitert, String leder){
		this.fraTid = fraTid;
		this.tilTid = tilTid;
		this.dato = dato;
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.oppdatert = oppdatert;
		this.leder = leder;
		//this.rom = rom; må gjøre slik at en finner rom med nøkkel
	}
	
	public Avtale(){
		this.avtaleID = 123456 + "";
	}
	
	public String getLeder(){
		return this.leder;
	}
	
	public void removeFolkSomIkkeKommer(Person person){
		folkSomIkkeKommer.remove(person);
	}
	
	
	Avtale getAvtale() throws Exception{
		ResultSet rs = con.les("SELECT * FROM avtale WHERE(avtaleID =" + avtaleID + ")");
		String avtaleID = rs.getString("AvtaleID");
		String tid = rs.getString("Starttid");
		String dato = rs.getString("Dato");
		String tittel = rs.getString("Tittel");
		String beskrivelse = rs.getString("Beskrivelse");
		String oppdatert = rs.getString("oppdatert");
		String kalenderID = rs.getString("KalenderID");
		String rom  = rs.getString("rom");
		Avtale avtale = new Avtale(avtaleID, tid, dato, tittel, beskrivelse, oppdatert, kalenderID, rom);
		return avtale;
	}
	
	public void setTid(String tid){
		this.tid = tid;
	}
	
	public void setDato(String dato){
		this.dato = dato;
	}
	
	public void setRom(Rom rom){
		this.rom = rom;
	}

	public String getTid() {
		return tid;
	}

	public String getDato() {
		return dato;
	}

	public Rom getRom() {
		return rom;
	}

	public ArrayList<Person> getInvited() {
		return invitert;
	}
	
	
}
