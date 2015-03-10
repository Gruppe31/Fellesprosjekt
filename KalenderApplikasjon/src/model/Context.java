package model;

import javafx.stage.Stage;

public class Context {
	LaunchGUI launchGUI = new LaunchGUI();
	Stage skjemaStage = new Stage();
	public static Context instance = new Context();
	
	//Kommer kanskje til aa fjerne avtale.
	Avtale avtale = new Avtale();
	public Person bruker = new Person();
	public Kalender kalender = new Kalender();
	
	public static Context getInstance(){
		return instance;
	}
	
	public Person getPerson(){
		return this.bruker;
	}
	
	public Kalender getKalender(){
		return this.kalender;
	}
	
	public Avtale getAvtale(){
		return this.avtale;
	}
	
	public void setAvtale(Avtale avtale){
		this.avtale = avtale;
	}
}
