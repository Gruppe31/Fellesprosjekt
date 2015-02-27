package Kontrollere;

import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Person;
import mysql.Connector;

public class LoggInnKontroller {
	
	Person model = new Person();
	@FXML private TextField brukernavn;
	@FXML private TextField passord;
	
	@FXML private Button loggInn;
	@FXML private Button meldInn;
	
	private Connector con = new Connector();
	
	@FXML
	void loggInn(){
		// her skal alt valideres og bruker bli logget inn i systemet
		if(erBrukernavnRiktigOgPassordRiktig()){
			//logg inn
		} else {
			// får melding om at brukernavn og/eller passord er feil
		}
	}
	
	@FXML
	void meldInn(){
		// her skal ny bruker bli sendt videre til innmeldingsvindu
	}
	
	private boolean erBrukernavnOgPassordRiktig(){
		String brukerNavn = brukernavn.getText();
		String passOrd = passord.getText();
		ResultSet rs = con.les("SELECT Brukernavn, Passord FROM Bruker WHERE(Brukernavn = '" + brukerNavn + "') AND(Passord = '" + passOrd + "')");
		if(){
			return true;
		} else {
			return false;
		}
	}

}
