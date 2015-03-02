package Kontrollere;

import java.sql.ResultSet;

import model.Person;
import mysql.Connector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoggInnKontroller {
	
	Person model = new Person();
	@FXML private TextField brukernavn;
	@FXML private TextField passord;
	@FXML private TextField feil;
	
	@FXML private Button loggInn;
	@FXML private Button meldInn;
	
	private Connector con = new Connector();
	
	private boolean loggetInn = false;
	
	@FXML
	void loggInn(){
		// her skal alt valideres og bruker bli logget inn i systemet
		while(! loggetInn){
			if(erBrukernavnOgPassordRiktig()){
				//logg inn
				loggetInn = true;
				//åpne kalendervinduet
			} 
		}
	}
	
	@FXML
	void meldInn(){
		// her skal ny bruker bli sendt videre til innmeldingsvindu
		
	}
	
	private boolean erBrukernavnOgPassordRiktig(){
		String brukerNavn = brukernavn.getText();
		String passOrd = passord.getText();
		try {
			ResultSet rs = con.les("SELECT Brukernavn, Passord FROM Bruker WHERE(Brukernavn = '" + brukerNavn + "') AND(Passord = '" + passOrd + "')");
			if (rs == null) {
				feil.setText("Brukernavn/Passord er feil");
				feil.setVisible(true);
				return false;
			} else {
				feil.setVisible(false);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

