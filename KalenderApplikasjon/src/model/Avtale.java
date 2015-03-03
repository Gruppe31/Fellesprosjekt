package model;

import java.util.ArrayList;

import mysql.Connector;

public class Avtale{
	private Connector con = new Connector();
	
	private int kalenderID;
	private String rom;
	private String leder;
	private String fraTid;
	private String tilTid;
	private int avtaleID;
	private String dato;
	private String tittel;
	private String beskrivelse;
	private String oppdatert;

	private ArrayList<String> invitert;
	private ArrayList<String> folkSomIkkeKommer;
	
	
	public Avtale(String fraTid, String tilTid, String dato, String tittel, 
			String beskrivelse, String rom){
		// Herfra lagres ting i databasen.
		// Må ha en spørring til databasen for å finne avtaleID så den ikke krasjer med andre avtaleIDer
		this.fraTid = fraTid;
		this.tilTid = tilTid;
		this.dato = dato;
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.rom = rom;
		
		this.invitert = new ArrayList<String>();
		//må gjøre slik at en finner rom med nøkkel
	}
	
	public Avtale(String fraTid, String tilTid, String dato, String tittel, 
			String beskrivelse, String oppdatert, String rom, String leder, int avtaleID, int kalenderID){
		this.fraTid = fraTid;
		this.tilTid = tilTid;
		this.dato = dato;
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.rom = rom;
		this.oppdatert = oppdatert;
		this.leder = leder;
		this.invitert = new ArrayList<String>();
	}
	
	public void databaseSettInn() throws Exception{
		con.les("INSERT INTO AVTALE(dato,fraTid,tilTid,Tittel,Beskrivelse,Oppdatert,KalenderID,Romnavn "
				+ "VALUES" + this.dato + " " + this.tilTid + " " + this.fraTid + " " + this.tittel
				+ " " + this.beskrivelse + " " + this.oppdatert + " " + this.kalenderID + this.rom);
	}
	
	public int getAvtaleID(){
		return this.avtaleID;
	}
	
	public String getLeder(){
		return this.leder;
	}
	
	public String getOppdatert(){
		return this.oppdatert;
	}
	
	public int getKalenderID(){
		return this.kalenderID;
	}
	
	public void removeFolkSomIkkeKommer(Person person){
		folkSomIkkeKommer.remove(person);
	}
	
	public void setAvtaleID(int avtaleID){
		this.avtaleID = avtaleID;
	}
	
	public void setFraTid(String tid){
		this.fraTid = tid;
	}

	public void setTilTid(String tid){
		this.tilTid = tid;
	}
	
	public void setDato(String dato){
		this.dato = dato;
	}
	
	public void setRom(String rom){
		//Rom newRom = new Rom(rom, 10);
		this.rom = rom;
	}
	
	public void setTittel(String tittel){
		this.tittel = tittel;
	}
	
	public void setInviter(ArrayList<String> invitert){
		this.invitert = invitert;
	}
	
	public void setBeskrivelse(String beskrivelse){
		this.beskrivelse = beskrivelse;
	}
	
	public void addInvitert(String brukerNavn){
		invitert.add(brukerNavn);
	}
	
	public void setOppdatert(String oppdatert){
		this.oppdatert = oppdatert;
	}

	public String getFraTid() {
		return fraTid;
	}
	
	public String getTilTid() {
		return tilTid;
	}

	public String getDato() {
		return dato;
	}

	public String getRom() {
		return rom;
	}

	public ArrayList<String> getInvited() {
		return invitert;
	}
	
	public String getTittel(){
		return this.tittel;
	}
	
	@Override
	public String toString() {
		return beskrivelse + " " + tittel + " " + rom + " " + invitert;
	}
	
	
}
