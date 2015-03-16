package Kontrollere;

import java.sql.ResultSet;
import java.sql.Timestamp;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Avtale;
import model.Context;
import mysql.Connector;

public class InfoKontroller{
	
	private Connector con = new Connector();
	//Må legge til å kunne velge hvor mange som kommer som et rent tall.
	@FXML private Pane pane;
	
	@FXML private TextField tittel;
	@FXML private TextArea beskrivelse;
	@FXML private TextField oppdatert;
	
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
	@FXML private Button slettAvtale;
	Avtale avtale;
	
	public void initialize() throws Exception{//Skal ogsaa initialisere seg selv med info om avtale.
		pane.setDisable(true);
		this.avtale = Context.getInstance().getAvtale();
		tittel.setText(avtale.getTittel());
		beskrivelse.setText(avtale.getBeskrivelse());
		String datoString = avtale.getDato();
		LocalDate datoA = LocalDate.parse(datoString);
		dato.setValue(datoA);
		fraTid.setText(avtale.getFraTid());
		tilTid.setText(avtale.getTilTid());
		romListe.add(avtale.getRom());
		rom.setItems(romListe);
		String sql = "SELECT Brukernavn FROM brukerAvtale WHERE(brukerAvtale.avtaleID = " + avtale.getAvtaleID() + ")";
		ResultSet rs = con.les(sql);
		String bruker = "";
		while(rs.next()){
			bruker = rs.getString("Brukernavn");
			brukere.add(bruker);
		}
		deltagere.setItems(brukere);
		oppdatert.setText(avtale.getOppdatert());;
	}
	
	@FXML
	void finnRom() throws Exception{
		romListe.removeAll();
		rom.setItems(romListe);
		String sql = "UPDATE Avtale SET Romnavn = NULL"; 
		con.skriv(sql);
		if(erFraTidRiktig(fraTid.getText()) && erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue())){
			int antall = brukere.size();
			String s = "SELECT Rom.Romnavn FROM Rom WHERE Romnavn NOT IN(SELECT Rom.Romnavn FROM Rom JOIN Avtale ON (Rom.Romnavn = Avtale.Romnavn) WHERE(Avtale.fraTid = '" 
			+ fraTid.getText() + "' AND Avtale.Dato ='"
			+ dato.getValue().toString() +"')) AND Rom.Antall >" + antall;
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
		if(erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue()) && erFraTidRiktig(fraTid.getText()) && romListe.get(rom.getSelectionModel().getSelectedIndex()) != null){
			java.util.Date date= new java.util.Date();
			Avtale model = new Avtale(fraTid.getText().substring(0, 5),tilTid.getText().substring(0, 5), dato.getValue().toString(), tittel.getText(), 
					beskrivelse.getText(),"CURRENT_TIMESTAMP" ,romListe.get(rom.getSelectionModel().getSelectedIndex()), avtale.getLeder() , 0 , avtale.getKalenderID());
			
			//itererer over brukernavn og legger de til i modelen.
			for (String brukerNavn : brukere) {
				model.addInvitert(brukerNavn);
			}
			model.addInvitert(Context.getInstance().getPerson().getBrukernavn());
			String sql1 = "UPDATE avtale SET fraTid= '" + fraTid.getText() + "', tilTid = '" + tilTid.getText() + "',Dato = '" + dato.getValue().toString()+ "'"
					+ ",Tittel = '" + tittel.getText() + "', Beskrivelse = '" +  beskrivelse.getText() + "', Oppdatert = '" + new Timestamp(date.getTime())
					 + "', Romnavn = '" + romListe.get(rom.getSelectionModel().getSelectedIndex()) + "'";
			con.skriv(sql1);
			
			String sql2 = "DELETE FROM Brukeravtale WHERE(Brukeravtale.avtaleID = " + Context.getInstance().getAvtale().getAvtaleID() + ")";
			con.skriv(sql2);
			for(String deltaker : brukere){
				String s2 = "INSERT INTO Brukeravtale VALUES('" + deltaker + "','" + Context.getInstance().getAvtale().getAvtaleID() + "', '0','" + dato.getValue().toString() + " " + fraTid.getText() + "')";
				con.skriv(s2);
			}
			
			Context.getInstance().getKalender().addAvtale(model);//Legger til avtale i listen over avtaler til kalender.
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
			if(romListe.get(rom.getSelectionModel().getSelectedIndex()) == null){
				rom.setStyle("-fx-background-color: #FF0000");
			}else{
				rom.setStyle("-fx-background-color: #FFFFFF");
			}
		}
	}
	
	@FXML
	void avbryt() throws Exception{
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void endreAvtale() throws Exception{
		//Avtalen skal kunne endres.
		pane.setDisable(false);
	}
	
	@FXML
	void slettAvtale() throws Exception{
		//skal slette avtalen
		String sql = "DELETE FROM Avtale WHERE(Avtale.AvtaleID = " + avtale.getAvtaleID() + ")";
		con.skriv(sql);
		Stage stage = (Stage) slettAvtale.getScene().getWindow();
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
