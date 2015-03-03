package Kontrollere;

import java.io.IOException;

import mysql.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Kalender;
import model.LaunchGUI;

public class KalenderKontroller {
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	ObservableList<String> kalenderListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> mineKalendere= new ListView<String>(kalenderListe);
	
	@FXML private TextField Varslinger;
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	
	
	
	Stage skjemaStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	@FXML
	void sokEnterFelt(){
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
	void NyAvtale(){
		// nyAvtale-vinduet skal komme opp
		nyAvtale.requestFocus();
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
	
	

}
