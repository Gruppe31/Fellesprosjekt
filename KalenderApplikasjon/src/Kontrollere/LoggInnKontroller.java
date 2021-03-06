package Kontrollere;

import java.io.IOException;
import java.sql.ResultSet;

import model.Context;
import model.LaunchGUI;
import model.Person;
import mysql.Connector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoggInnKontroller {
	
	Person model = new Person();
	@FXML private TextField brukernavn;
	@FXML private PasswordField passord;
	@FXML private Text feil;
	
	@FXML private Button loggInn;
	@FXML private Button meldInn;
	
	Stage mainStage = new Stage();
	Stage signUpStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	private Connector con = new Connector();
	
	@FXML
	void loggInn() throws Exception{
		// her skal alt valideres og bruker bli logget inn i systemet
		if(erBrukernavnOgPassordRiktig()){
			//logg inn
			//åpne kalendervinduet
			try{
				Context.getInstance().getPerson().setBrukernavn(brukernavn.getText());
				Context.getInstance().getPerson().setPassord(passord.getText());
				ResultSet rs = con.les("SELECT KalenderID FROM Person WHERE(Brukernavn = '" + brukernavn.getText() + "')");
				int kalenderID = 0;
				while(rs.next()){
					kalenderID = rs.getInt("KalenderID");
				}
				Context.getInstance().getKalender().setKalenderID(kalenderID);
				//Skal lukke loggInn vinduet etter at main er aapnet.
				Stage stage = (Stage) loggInn.getScene().getWindow(); 
				stage.close();
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
			Stage stage = (Stage) meldInn.getScene().getWindow();
			stage.close();
			launchGUI.startSignup(signUpStage);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	private boolean erBrukernavnOgPassordRiktig(){
		String brukerNavn = brukernavn.getText();
		String passOrd = passord.getText();
		String sql = "SELECT Brukernavn, Passord FROM Person WHERE(Brukernavn = '" + brukerNavn + "' AND Passord = '" + passOrd + "')";
		try {
			ResultSet rs = con.les(sql);
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

