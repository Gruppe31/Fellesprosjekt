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

		sokeliste.get(sok.getSelectionModel().getSelectedIndex());
=======
		//Skal �pne kalender til personen eller gruppa det blir s�kt p�.
		//Denne �pningen skal man da ikke kunne endre p� noen av tingene inne p� den.
		
		//sokeliste.get(sok.getSelectionModel().getSelectedIndex());

		
	}
	
	@FXML
	void Liste() throws Exception{
		ResultSet bruker = con.les("SELECT Brukernavn FROM Person WHERE (Brukernavn like '"+soketekst+"%')");
		ResultSet gruppe = con.les("SELECT Gruppenavn FROM Gruppe WHERE (Gruppenavn like '"+soketekst+"%')");
		String brukeradd = null;
		String gruppeadd = null;
=======
	void initialize() throws Exception{
		ResultSet bruker = con.les("SELECT Brukernavn FROM Person WHERE (Brukernavn = '" + soketekst + "')" );
		ResultSet gruppe = con.les("SELECT Gruppenavn FROM Gruppe WHERE (Gruppenavn = '" + soketekst + "')" );
		
		if (!bruker.next() && !gruppe.next()){
			sokeliste.add("Ingen match for s�ket.");
			sok.setItems(sokeliste);
			Context.getInstance().setKorrektSok(false);
		}else{
			sokeliste.add(soketekst);
			sok.setItems(sokeliste);
			Context.getInstance().setKorrektSok(true);
		}
//		while (gruppe.next()){
//			gruppeadd = gruppe.getString("Gruppenavn");
//			
//			M� gj�re slik at context aksepterer � hente gruppenavn
//			if(gruppeadd == null || gruppeadd == Context.getInstance().getGruppe().getBrukernavn()){
//				sokeliste.add("Ingen grupper matchet s�ket");
//				
//			}else{
//				sokeliste.add(gruppeadd);
//			}
//		}
		sok.setItems(sokeliste);
	}

}



