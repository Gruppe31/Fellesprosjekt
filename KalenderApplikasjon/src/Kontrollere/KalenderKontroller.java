package Kontrollere;

import mysql.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Kalender;

public class KalenderKontroller {
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	ObservableList<String> kalenderListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> mineKalendere= new ListView<String>(kalenderListe);
	
	@FXML private TextField Varslinger;
	@FXML private Button nyAvtale;
	
	@FXML 
	void NyAvtale(){
		// nyAvtale-vinduet skal komme opp
	}
	
	@FXML
	void LoggUt(){
		//sendes tilbake til loggInn-vinduet
	}
	
	

}
