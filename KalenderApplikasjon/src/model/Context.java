package model;

import javafx.stage.Stage;

public class Context {
	LaunchGUI launchGUI = new LaunchGUI();
	Stage skjemaStage = new Stage();
	public static Context instance = new Context();
	
	//Kommer kanskje til aa fjerne avtale.
	public Avtale avtale = new Avtale();
	public Person bruker = new Person();
<<<<<<< HEAD

	public Kalender kalender;


=======
	public Kalender kalender = new Kalender();
>>>>>>> master
	
	public static Context getInstance(){
		return instance;
	}
	
	public Person getPerson(){
		return this.bruker;
	}
	
	public Kalender getKalender(){
		return this.kalender;
	}
<<<<<<< HEAD

=======
>>>>>>> master

	public Avtale getAvtale() {
		return this.avtale;
	}
<<<<<<< HEAD

=======
>>>>>>> master
	
	public void setAvtale(Avtale avtale){
		this.avtale = avtale;
	}
<<<<<<< HEAD

=======
>>>>>>> master
}
