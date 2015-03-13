package Kontrollere;

import java.sql.ResultSet;

import model.Avtale;
import model.Context;
import model.Gruppe;
import mysql.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GruppeKontroller {
	
	Gruppe gruppe;
	@FXML private TextField brukernavn; 
	@FXML private Button leggTilPerson;
	@FXML private TextField gruppenavn;
	@FXML private Button leggTilGruppe;
	@FXML private Button avbryt;
	private String bruker;
	ObservableList<String> brukere  = FXCollections.observableArrayList();
	@FXML private ListView<String> listePersoner = new ListView<String>(brukere);
	
	private Connector con = new Connector();
	
	@FXML
	void LeggTilPerson() throws Exception{
		String brukerNavn = brukernavn.getText();
		ResultSet rs = con.les("SELECT Brukernavn FROM Person WHERE(Brukernavn = '" + brukerNavn + "')");
		String bruker = null;
		while(rs.next()){
			bruker = rs.getString("Brukernavn");
		}
		if(bruker == null){
			brukernavn.setStyle("-fx-background-color: #FF0000");
		}else{
			brukernavn.setStyle("-fx-background-color: #FFFFFF");
			brukernavn.setText("");
			if(!brukere.contains(bruker)){
				brukere.add(bruker);
			}
			listePersoner.setItems(brukere);
		}
	}
	
	@FXML
	void Avbryt(){
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void LeggTilGruppe() throws Exception{
		// Lager en ny innstans av Gruppe.
		// Gruppen lagres i databasen.
		if(erGruppenavnGyldig()){
			ResultSet rs = con.les("SELECT KalenderID FROM Kalender");
			int kalenderID = 0;
			while(rs.next()){
				kalenderID = rs.getInt("KalenderID");
			}
			gruppe = new Gruppe(gruppenavn.getText(),kalenderID);
			//itererer over brukernavn og legger de til i modelen.
			for (String brukerNavn : brukere) {
				gruppe.leggTilGruppe(brukerNavn);
			}
			System.out.println(gruppe);
			Context.getInstance().getKalender().leggTilGruppe(gruppe);//Legger til gruppe i listen over grupper til kalender.
			gruppe.databaseSettInn();
			Stage stage = (Stage) leggTilGruppe.getScene().getWindow();
			stage.close();
		}
	}
	
	private boolean erGruppenavnGyldig(){
		String gruppeNavn = gruppenavn.getText();
		if (gruppeNavn != " "){
			return true;			
		} else {
			return false;
		}
	}

}
