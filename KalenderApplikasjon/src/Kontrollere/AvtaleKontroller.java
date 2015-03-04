package Kontrollere;

import javafx.stage.Stage;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Avtale;
import model.Person;
import mysql.Connector;

public class AvtaleKontroller {
	private Connector con = new Connector();
	
	@FXML private AnchorPane pane;
	
	@FXML private TextField tittel;
	@FXML private TextArea beskrivelse;
	
	@FXML private DatePicker dato;
	@FXML private TextField fraTid;
	@FXML private TextField tilTid;
	
	@FXML private Button finnRom;
	// ObservableList må brukes for å oppdatere ListViewet
	ObservableList<String> romListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> rom = new ListView<String>(romListe);
	
	@FXML private TextField leggTilPerson;
	ObservableList<String> brukere  = FXCollections.observableArrayList();
	@FXML private ListView<String> deltagere = new ListView<String>(brukere);

	@FXML private Button inviter;
	@FXML private Button fjernDeltager;
	@FXML private Button lagre;
	@FXML private Button avbryt;
	
	@FXML
	void finnRom() throws Exception{
		if(erFraTidRiktig(fraTid.getText()) && erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue())){
			String s = "SELECT Rom.Romnavn FROM Rom WHERE Romnavn NOT IN(SELECT Rom.Romnavn FROM Rom JOIN Avtale ON (Rom.Romnavn = Avtale.Romnavn) WHERE(Avtale.fraTid = '" + fraTid.getText() + "' AND Avtale.Dato ='" + dato.getValue().toString() +"' AND Rom.Antall >'" + brukere.size() + "'))";
			ResultSet rs = con.les(s);
			while(rs.next()){
				romListe.add(rs.getString("romNavn"));
				rom.setItems(romListe);
			}
		}else{
			//sjekker hvilke felt som er galt og hvis det er galt setter det til rødt.
//			for (String romNavn : romListe) {
//				romListe.remove(romNavn);
//			}
//			rom.setItems(romListe);
			if (!erFraTidRiktig(fraTid.getText())) {
				fraTid.setStyle("-fx-background-color: #FF00000");
			}if(!erTilTidRiktig(tilTid.getText())){
				tilTid.setStyle("-fx-background-color: #FF0000");
			}if(!erDatoRiktig(dato.getValue())){
				dato.setStyle("-fx-background-color: #FF0000");
			}
		}
	}
	
	@FXML
	void fjernDeltager(){
		//Fjerner deltagere fra listen
		brukere.remove(deltagere.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	void lagre() throws Exception{
		// Lager en ny innstans av Avtale.
		// Avtale lagrer i databasen.
		if(erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue()) && erFraTidRiktig(fraTid.getText())){
			Avtale model = new Avtale(fraTid.getText(), tilTid.getText(), dato.getValue().toString(), tittel.getText(), 
					beskrivelse.getText(),"CURRENT_TIMESTAMP" ,romListe.get(rom.getSelectionModel().getSelectedIndex()), "Magnus" , 0 ,1);
			
			//itererer over brukernavn og legger de til.
			for (String brukerNavn : brukere) {
				model.addInvitert(brukerNavn);
			}
			model.databaseSettInn();
			
		}else{
			if (!erFraTidRiktig(fraTid.getText())) {
				fraTid.setStyle("-fx-background-color: #FF00000");
			}if(!erTilTidRiktig(tilTid.getText())){
				tilTid.setStyle("-fx-background-color: #FF0000");
			}if(!erDatoRiktig(dato.getValue())){
				dato.setStyle("-fx-background-color: #FF0000");
			}
		}
	}
	
	@FXML
	void avbryt(){
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	boolean inviter() throws Exception{
		String brukerNavn = leggTilPerson.getText();
		ResultSet rs = con.les("SELECT Brukernavn FROM Bruker WHERE(Brukernavn = '" + brukerNavn + "')");
		String bruker = null;
		
		while(rs.next()){
			bruker = rs.getString("Brukernavn");
		}
		//Sjekker om bruker eksisterer. Hvis den gjør det blir den lagt til i listView
		if(bruker == null){
			leggTilPerson.setStyle("-fx-background-color: #FF0000");
			return false;
		}else{
			//Trenger ikke dette siden SQl gjør det for oss.
			int lengde = Math.max(bruker.length(),brukerNavn.length());
			for (int i = 0; i < lengde; i++) {
				if(bruker.charAt(i) != brukerNavn.charAt(i)){
					leggTilPerson.setStyle("-fx-background-color: #FF0000");
					return false;
				}
			}
			leggTilPerson.setStyle("-fx-background-color: #FFFFFF");
			leggTilPerson.setText("");
			brukere.add(bruker);
			System.out.println(brukere.size());
			deltagere.setItems(brukere);
			return true;
		}
	}
	
	boolean erTilTidRiktig(String nyVerdi){
		try{
			LocalTime til = LocalTime.parse(nyVerdi);
			if (!til.isAfter(LocalTime.parse(fraTid.getText()))) {
				return false;
			}else{
				return true;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	
	boolean erFraTidRiktig(String nyVerdi){
		try{
			// Sjekker om strengen fraTid kan parses til LocalTime
			LocalTime fra = LocalTime.parse(nyVerdi);
			return true;
		}
		catch(Exception e){
			return false;
		}		
	}
	
	private boolean erDatoRiktig(LocalDate newValue){
		if(newValue == null){
			return false;
		}
		if(newValue.isBefore(LocalDate.now())){
			return false;
		}else{
			return true;
		}
	}
}
