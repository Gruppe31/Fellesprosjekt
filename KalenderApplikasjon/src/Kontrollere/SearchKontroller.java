package Kontrollere;


import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Context;
import model.LaunchGUI;
import model.Person;
import mysql.Connector;

public class SearchKontroller {
	
	Person model = new Person();
	ObservableList<String> sokeliste  = FXCollections.observableArrayList();
	@FXML private ListView<String> sok = new ListView<String>(sokeliste);
	
	private Connector con = new Connector();
	
	private String soketekst = Context.getInstance().getSokeTekst();

	@FXML private Button velg;
	@FXML private Button avbryt;

	Stage Stage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();

	
	@FXML
	void Avbryt(){
		// g� tilbake til kalendervindu uten endringer
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void Velg(){
		//sokeliste.get(sok.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	void initialize() throws Exception{
		ResultSet bruker = con.les("SELECT Brukernavn FROM Person WHERE (Brukernavn = '" + soketekst + "')" );
		ResultSet gruppe = con.les("SELECT Gruppenavn FROM Gruppe WHERE (Gruppenavn = '" + soketekst + "')" );
		
		if (!bruker.next() && !gruppe.next()){
			sokeliste.add("Ingen match for s�ket.");
			sok.setItems(sokeliste);
		}else{
			sokeliste.add(soketekst);
			sok.setItems(sokeliste);
		}
	}

}



