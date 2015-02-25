package model;

import java.util.ArrayList;
import java.util.List;

public class Gruppe {
	
	private String gruppenavn;
	private int gruppeid;
	priavte int kalenderID;
	private List<Person> brukerListe = new ArrayList();
	
	public Gruppe(String gruppenavn, int gruppeid, int kalenderID){
		setGruppenavn(gruppenavn);
		setGruppeid(gruppeid);
		this.kalenderID = kalenderID;
	}
	
	public void setGruppeid(int gruppeid) {
		if(gruppeid > 0){
			this.gruppeid = gruppeid;
		}
	}
	
	public int getGruppeid(){
		return this.gruppeid;
	}

	public void setGruppenavn(String gruppenavn){
		if(gruppenavn != " "){
			this.gruppenavn = gruppenavn;
		}
	}
	
	public String getGruppenavn(){
		return this.gruppenavn;
	}

	public void leggTilGruppe(Person person){
		this.brukerListe.add(person);
	}
	
	public void slettPersonFraGruppe(Person person){
		this.brukerListe.remove(person);
	}
	
	public List getGruppeliste(){
		return this.brukerListe;
	}
	
}
