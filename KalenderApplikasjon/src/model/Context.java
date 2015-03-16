package model;

import javafx.stage.Stage;

public class Context {
	LaunchGUI launchGUI = new LaunchGUI();
	Stage skjemaStage = new Stage();
	public static Context instance = new Context();
	
	//Kommer kanskje til aa fjerne avtale.
	public Avtale avtale = new Avtale();
	public Person bruker = new Person();
	public Kalender kalender = new Kalender();
	private Gruppe gruppe = new Gruppe();
	private String sokeTekst;
	private boolean typeKalender = true; //true hvis vanlig, false hvis gruppekalender
	
	public static Context getInstance(){
		return instance;
	}
	
	public void setTypeKalender(boolean type){
		this.typeKalender = type;
	}
	
	public boolean getTypeKalender(){
		return this.typeKalender;
	}
	
	public Gruppe getGruppe(){
		return this.gruppe;
	}
	
	public void setSokeTekst(String sok){
		this.sokeTekst = sok;
	}
	
	public String getSokeTekst(){
		return this.sokeTekst;
	}
	
	public Person getPerson(){
		return this.bruker;
	}
	
	public Kalender getKalender(){
		return this.kalender;
	}

	public Avtale getAvtale() {
		return this.avtale;
	}
	
	public void setAvtale(Avtale avtale){
		this.avtale = avtale;
	}
}
