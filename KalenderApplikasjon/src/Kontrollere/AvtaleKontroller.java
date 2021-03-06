package Kontrollere;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Avtale;
import model.Context;
import mysql.Connector;

public class AvtaleKontroller{
	
	private String bruker;
	int kalenderID;
	private Connector con = new Connector();
	//Må legge til å kunne velge hvor mange som kommer som et rent tall.
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
	
	public void initialize() {//Skal ogsaa initialisere seg selv med info om avtale.
		this.bruker = Context.getInstance().getPerson().getBrukernavn();
		brukere.add(bruker);
		deltagere.setItems(brukere);
		this.kalenderID = Context.getInstance().getKalender().getKalenderID();
	}
	
	@FXML
	void finnRom() throws Exception{
		romListe.clear();
		rom.setItems(romListe);
		if(erFraTidRiktig(fraTid.getText()) && erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue())){
			int antall = brukere.size();
			String s = "SELECT Rom.Romnavn FROM Rom WHERE Romnavn NOT IN(SELECT Rom.Romnavn FROM Rom JOIN Avtale ON (Rom.Romnavn = Avtale.Romnavn) WHERE(Avtale.fraTid = '" 
			+ fraTid.getText() + "' AND Avtale.Dato ='"
			+ dato.getValue().toString() +"')) AND Rom.Antall >=" + antall;
			ResultSet rs = con.les(s);
			while(rs.next()){
			
				if(!romListe.contains(rs.getString("romNavn"))){
					romListe.add(rs.getString("romNavn"));
					rom.setItems(romListe);
				}
			}
		}else{
			//sjekker hvilke felt som er galt og hvis det er galt setter det til rødt.
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
		// Avtale lagres i databasen.
		if(erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue()) && erFraTidRiktig(fraTid.getText()) && rom.getSelectionModel().getSelectedIndex() != -1){
			//ResultSet rs = con.les("SELECT KalenderID FROM Person WHERE(Brukernavn = '" + bruker + "')");
			int kalenderID = this.kalenderID;
			//while(rs.next()){
			//	kalenderID = rs.getInt("KalenderID");
			//}
			Avtale model = new Avtale(fraTid.getText(),tilTid.getText(), dato.getValue().toString(), tittel.getText(), 
					beskrivelse.getText(),"CURRENT_TIMESTAMP" ,romListe.get(rom.getSelectionModel().getSelectedIndex()), this.bruker , 0 , kalenderID);
			
			//itererer over brukernavn og legger de til i modelen.
			for (String brukerNavn : brukere) {
				model.addInvitert(brukerNavn);
			}
			//model.addInvitert(Context.getInstance().getPerson().getBrukernavn());
			Context.getInstance().getKalender().addAvtale(model);//Legger til avtale i listen over avtaler til kalender.
			model.databaseSettInn();
			Stage stage = (Stage) lagre.getScene().getWindow();
			stage.close();
			
		}else{
			if (!erFraTidRiktig(fraTid.getText())) {
				fraTid.setStyle("-fx-background-color: #FF00000");
			}else{
				fraTid.setStyle("-fx-background-color: #FFFFFFF");
			}
			if(!erTilTidRiktig(tilTid.getText())){
				tilTid.setStyle("-fx-background-color: #FF0000");
			}else{
				tilTid.setStyle("-fx-background-color: #FFFFFF");
			}
			if(!erDatoRiktig(dato.getValue())){
				dato.setStyle("-fx-background-color: #FF0000");
			}else{
				dato.setStyle("-fx-background-color: #FFFFFF");
			}
			if(rom.getSelectionModel().getSelectedIndex() == -1){
				rom.setStyle("-fx-background-color: #FF0000");
			}else{
				rom.setStyle("-fx-background-color: #FFFFFF");
			}
		}
	}
	
	@FXML
	void avbryt(){
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void inviter() throws Exception{
		String brukerNavn = leggTilPerson.getText();
		ResultSet rs = con.les("SELECT Brukernavn FROM Person WHERE(Brukernavn = '" + brukerNavn + "')");
		ResultSet rs2 = con.les("SELECT Gruppenavn FROM Gruppe WHERE(Gruppenavn = '" + brukerNavn + "')");
		String bruker = null;
		String gruppe = null;
		while(rs.next()){
			bruker = rs.getString("Brukernavn");
		}
		
		if(bruker == null || bruker == Context.getInstance().getPerson().getBrukernavn()){
			leggTilPerson.setStyle("-fx-background-color: #FF0000");
		}else{
			leggTilPerson.setStyle("-fx-background-color: #FFFFFF");
			leggTilPerson.setText("");
			if(!brukere.contains(bruker)){
				brukere.add(bruker);
			}
			deltagere.setItems(brukere);
		}	
		while(rs2.next()){
			gruppe = rs2.getString("Gruppenavn");
		}
		
		if(gruppe == null){
			//leggTilPerson.setStyle("-fx-background-color: #FF0000");
		}else{
			leggTilPerson.setStyle("-fx-background-color: #FFFFFF");
			leggTilPerson.setText("");
			String s3 = "SELECT Brukernavn From Brukergruppe JOIN Gruppe ON(Brukergruppe.GruppeID = Gruppe.GruppeID) WHERE(Gruppenavn ='" + gruppe + "')";
			ResultSet rs3 = con.les(s3);
			while(rs3.next()){
				String bruker2 = rs3.getString("Brukernavn");
				if(!brukere.contains(bruker2) && !bruker2.equals(Context.getInstance().getPerson().getBrukernavn())){
					brukere.add(bruker2);
				}
			}
			deltagere.setItems(brukere);
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
