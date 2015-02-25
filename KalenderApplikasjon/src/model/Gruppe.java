package model;

import java.util.ArrayList;
import java.util.List;

public class Gruppe {
	
	private String gruppenavn;
	private int gruppeid;
	private List<Person> gruppeListe = new ArrayList();
	
	public Gruppe(String gruppenavn, int gruppeid){
		setGruppenavn(gruppenavn);
		setGruppeid(gruppeid);
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
		gruppeListe.add(person);
	}
	
	public void slettPersonFraGruppe(Person person){
		gruppeListe.remove(person);
	}
	
	public List getGruppeliste(){
		return this.gruppeListe;
	}
	
}
