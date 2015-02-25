package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Avtale {
	private LocalTime tid;
	private LocalDate dato;
	private Rom rom;
	private ArrayList<Person> folkSomKommer;
	
	Avtale(LocalTime tid, LocalDate dato, Rom rom, ArrayList<Person> person){
		this.tid = tid;
		this.dato = dato;
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
