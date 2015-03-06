package Kontrollere;

import java.io.IOException;
import java.sql.ResultSet;
import model.Context;
import model.LaunchGUI;
import model.Person;
import mysql.Connector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoggInnKontroller {
	
	Person model = new Person();
	@FXML private TextField brukernavn;
	@FXML private PasswordField passord;
	@FXML private TextField feil;
	
	@FXML private Button loggInn;
	@FXML private Button meldInn;
	
	Stage mainStage = new Stage();
	Stage signUpStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	private Connector con = new Connector();
	
	@FXML
	void loggInn(){
		// her skal alt valideres og bruker bli logget inn i systemet
		if(erBrukernavnOgPassordRiktig()){
			//logg inn
			//åpne kalendervinduet
			try{
				Context.getInstance().getPerson().setBrukernavn(brukernavn.getText());
				Context.getInstance().getPerson().setPassord(passord.getText());
				launchGUI.startMain(mainStage);
			}catch(IOException e){
				e.printStackTrace();
			}
		} 
	}
	
	@FXML
	void meldInn(){
		// her skal ny bruker bli sendt videre til innmeldingsvindu
		try{
			launchGUI.startSignup(signUpStage);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	private boolean erBrukernavnOgPassordRiktig(){
		String brukerNavn = brukernavn.getText();
		String passOrd = passord.getText();
		String sql = "SELECT Brukernavn, Passord FROM Person WHERE(Brukernavn = '" + brukerNavn + "' AND Passord = '" + passOrd + "')";
		System.out.println(sql);
		try {
			ResultSet rs = con.les(sql);
			System.out.println(rs == null);
			if (!rs.next()) {
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

