package Kontrollere;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Person;

public class LoggInnKontroller {
	
	Person model = new Person();
	@FXML private TextField brukernavn;
	@FXML private TextField passord;
	
	@FXML private Button loggInn;
	@FXML private Button meldInn;
	
	void loggInn(){
		// her skal alt valideres og bruker bli logget inn i systemet
		if(erBrukernavnRiktig() && erPassordRiktig()){
			//logg inn
		} else {
			// får melding om at brukernavn og/eller passord er feil
		}
	}
	
	void meldInn(){
		// her skal ny bruker bli sendt videre til innmeldingsvindu
	}
	
	private boolean erBrukernavnRiktig(){
		if(){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean erPassordRiktig(){
		if(){
			return true;
		} else {
			return false;
		}
	}

}
