package model;

import javafx.stage.Stage;

public class Context {
	LaunchGUI launchGUI = new LaunchGUI();
	Stage skjemaStage = new Stage();
	public static Context instance = new Context();
	
	
	public Person bruker = new Person();
	public Kalender kalender;
	
	public static Context getInstance(){
		return instance;
	}
	
	public Person getPerson(){
		return this.bruker;
	}
	
	public Kalender getKalender(){
		return this.kalender;
	}
}
