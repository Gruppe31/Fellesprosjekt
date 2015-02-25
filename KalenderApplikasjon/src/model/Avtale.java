package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Avtale {
	private LocalTime tid;
	private LocalDate dato;
	private String tittel;
	private String beskrivelse;
	private String oppdatert;
	private Rom rom;
	private ArrayList<Person> folkSomKommer;
	
	Avtale(String avtaleID, LocalTime tid, LocalDate dato, String tittel, String beskrivelse, String oppdatert, String kalenderID, Rom rom, ArrayList<Person> person){
		this.tid = tid;
		this.dato = dato;
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.oppdatert = oppdatert;
		this.rom = rom;
		this.person = person;
	}
	
	setTid(LocalTime tid){
		this.tid = tid
	}
	
	setDato(LocalDate dato){
		this.dato = dato;
	}
	
	setRom(Rom rom){
		this.rom = rom;
	}

	public LocalTime getTid() {
		return tid;
	}

	public LocalDate getDato() {
		return dato;
	}

	public Rom getRom() {
		return rom;
	}

	public ArrayList<Person> getFolkSomKommer() {
		return folkSomKommer;
	}
	
	
}
