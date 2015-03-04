package Kontrollere;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.LaunchGUI;
import mysql.Connector;

public class KalenderKontroller implements Initializable{
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	ObservableList<String> kalenderListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> mineKalendere= new ListView<String>(kalenderListe);
	
	@FXML private TextField Varslinger;
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	
	
	
	Stage skjemaStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	public void initialize(){
		//Lager ny kalender som tar inn ID. Saa hentes avtaler og slikt ut fra databasen
		//Kalender kalender = new Kalender(123456);
	}
	
	@FXML
	public void sokTrykkEnter(){
		sok.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke){
				try {
					if(ke.getCode().equals(KeyCode.ENTER)){
						launchGUI.startSkjema(skjemaStage);
						System.out.println("test");
					}
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
					
			}
			
		});
	}
	
	@FXML 
	void NyAvtale(){//trenger ikke den handlingen innerst her.
		// nyAvtale-vinduet skal komme opp
		nyAvtale.requestFocus(); //Virker ikke?
		nyAvtale.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				try {
					launchGUI.startSkjema(skjemaStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	@FXML void LoggUt(){
		//sendes tilbake til loggInn-vinduet
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String s = "SELECT Kalender.KalenderID FROM Kalender JOIN Bruker ON (Kalender.KalenderID = Bruker.KalenderID) WHERE(Brukernavn='Magnus')";
		try {
			ResultSet rs = con.les(s);
			while(rs.next()){
				String kalenderID = rs.getString("kalenderID");
				
				kalenderListe.add(kalenderID);
			}
			mineKalendere.setItems(kalenderListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
