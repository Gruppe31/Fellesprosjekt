package Kontrollere;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import model.Avtale;
import mysql.Connector;

public class AvtaleKontroller {
	Avtale model = new Avtale();
	private Connector con = new Connector();
	
	@FXML private TextField tittel;
	@FXML private TextArea beskrivelse;
	
	@FXML private DatePicker dato;
	@FXML private TextField fraTid;
	@FXML private TextField tilTid;
	
	@FXML private Button finnRom;
	// ObservableList må brukes for å oppdatere ListViewet
	ObservableList<String> romListe  = FXCollections.observableArrayList();
	@FXML private ListView rom = new ListView<String>(romListe);
	
	@FXML private TextField leggTilPerson;
	ObservableList<String> brukere  = FXCollections.observableArrayList();
	@FXML private ListView<String> deltagere = new ListView<String>(brukere);

	@FXML private Button inviter;
	@FXML private Button fjernDeltager;
	@FXML private Button lagre;
	@FXML private Button avbryt;
	
	@FXML
	void finnRom() throws Exception{
		LocalDate avtaleDato = dato.getValue();
		if(erFraTidRiktig(fraTid.getText()) && erTilTidRiktig(tilTid.getText()) && erDatoRiktig(avtaleDato)){
			ResultSet rs = con.les("SELECT romNavn FROM Avtale, Rom WHERE avtale.romnavn = rom.romnavn AND avtale.fraTid !=" + fraTid + "AND avtale.dato !=" + avtaleDato);
			while(rs.next()){
				romListe.add(rs.getString("romNavn"));
			}
		}else{
			//sjekker hvilke felt som er galt og hvis det er galt setter det til rødt.
			if (!erFraTidRiktig(fraTid.getText())) {
				fraTid.setStyle("-fx-background-color: #FFFFFF");
			}if(erTilTidRiktig(tilTid.getText())){
				tilTid.setStyle("-fx-background-color: #FFFFFF");
			}if(erDatoRiktig(avtaleDato)){
				dato.setStyle("-fx-background-color: #FFFFFF");
			}
		}
	}
	
	@FXML
	void fjernDeltager(){
		//Fjerner deltagere fra listen
		brukere.remove(deltagere.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	void lagre(){
		if(erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue()) ){
			model.setFraTid(fraTid.getText());
			model.setTilTid(tilTid.getText());
			model.setDato(dato.toString());
		}
	}
	
	@FXML
	void avbryt(){
		System.out.println("Knappen fungerer ikke");
		// Her går man ut uten å ha lagre noe
	}
	
	@FXML
	boolean inviter() throws Exception{
		String brukerNavn = leggTilPerson.getText();
//		ResultSet rs = con.les("SELECT Brukernavn FROM Bruker WHERE(Brukernavn = '" + brukerNavn + "')");
//		String bruker = null;
//		
//		while(rs.next()){
//			bruker = rs.getString("Brukernavn");
//		}
		String bruker = "Lars";
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
			brukere.add("Lina");
			deltagere.setItems(brukere);
			return true;
		}
	}
	
	boolean erTilTidRiktig(String nyVerdi){
		try{
			LocalTime tilTid = LocalTime.parse(nyVerdi);
			if (!tilTid.isAfter(LocalTime.parse(fraTid.getText()))) {
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
			LocalTime fraTid = LocalTime.parse(nyVerdi);
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
