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
import javafx.scene.control.Label;
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
	@FXML private Label oppdatert;
	@FXML private Label leder;
	
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
	ObservableList<String> inviterte  = FXCollections.observableArrayList();
	@FXML private ListView<String> invitertListe = new ListView<String>(inviterte);

	@FXML private Button jaKnapp;
	@FXML private Button neiKnapp;
	@FXML private Button endre;
	@FXML private Button inviter;
	@FXML private Button fjernDeltager;
	@FXML private Button lagre;
	@FXML private Button avbryt;
	@FXML private Button slettAvtale;
	Avtale avtale;
	
	public void initialize() throws Exception{//Skal ogsaa initialisere seg selv med info om avtale.
		pane.setDisable(true);
		this.avtale = Context.getInstance().getAvtale();
		if(!avtale.getLeder().equals(Context.getInstance().getPerson().getBrukernavn())){
			endre.setVisible(false);
			slettAvtale.setVisible(false);
		}
		leder.setText(avtale.getLeder());
		tittel.setText(avtale.getTittel());
		beskrivelse.setText(avtale.getBeskrivelse());
		String datoString = avtale.getDato();
		LocalDate datoA = LocalDate.parse(datoString);
		dato.setValue(datoA);
		fraTid.setText(avtale.getFraTid());
		tilTid.setText(avtale.getTilTid());
		romListe.add(avtale.getRom());
		rom.setItems(romListe);
		String sql = "SELECT Brukernavn FROM brukerAvtale WHERE(brukerAvtale.avtaleID = " + avtale.getAvtaleID() + ")"
				+ "AND(brukerAvtale.statusKommer = 1)";
		ResultSet rs = con.les(sql);
		String bruker = "";
		while(rs.next()){
			bruker = rs.getString("Brukernavn");
			brukere.add(bruker);
		}
		deltagere.setItems(brukere);
		String sql2 = "SELECT Brukernavn FROM brukerAvtale WHERE(brukerAvtale.avtaleID = " + avtale.getAvtaleID() + ")";
		ResultSet rs2 = con.les(sql2);
		bruker = "";
		while(rs2.next()){
			bruker = rs2.getString("Brukernavn");
			inviterte.add(bruker);
		}
		invitertListe.setItems(inviterte);
		oppdatert.setText(avtale.getOppdatert());
	}
	
	@FXML
	void deltar() throws Exception{
		String bruker = Context.getInstance().getPerson().getBrukernavn();
		String sql = "UPDATE brukeravtale SET statuskommer = 1 "
				+ "WHERE(avtaleID = " +avtale.getAvtaleID() + ") "
				+ "AND(brukernavn = '" + bruker + "')";
		con.skriv(sql);
		if(!brukere.contains(bruker)){
			brukere.add(bruker);
		}
		deltagere.setItems(brukere);
	}
	
	@FXML
	void deltarIkke() throws Exception{
		String bruker = Context.getInstance().getPerson().getBrukernavn();
		String sql = "UPDATE brukeravtale SET statuskommer = 0 "
				+ "WHERE(avtaleID = " +avtale.getAvtaleID() + ") "
				+ "AND(brukernavn = '" + bruker + "')";
		con.skriv(sql);
		brukere.remove(bruker);
	}
	
	@FXML
	void finnRom() throws Exception{
		//rom.setSelectionModel(null);
		romListe.clear();
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
	void fjernDeltager() throws Exception{
		//Fjerner deltagere fra listen
		String bruker = inviterte.get(invitertListe.getSelectionModel().getSelectedIndex());
		String sql = "DELETE FROM brukeravtale WHERE(brukerNavn = '" + bruker + "')";
		con.skriv(sql);
		if (brukere.contains(bruker)) {
			brukere.remove(bruker);
		}
		brukere.remove(bruker);
		inviterte.remove(bruker);
	}
	
	@FXML
	void lagre() throws Exception{
		// Lager en ny innstans av Avtale.
		// Avtale lagres i databasen.
		if(erTilTidRiktig(tilTid.getText()) && erDatoRiktig(dato.getValue()) && erFraTidRiktig(fraTid.getText()) && rom.getSelectionModel().getSelectedIndex() != -1){
			java.util.Date date= new java.util.Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			//itererer over brukernavn og legger de til i modelen.
			String sql1 = "UPDATE avtale SET fraTid= '" + fraTid.getText() + "', tilTid = '" + tilTid.getText() + "',Dato = '" + dato.getValue().toString()+ "'"
					+ ",Tittel = '" + tittel.getText() + "', Beskrivelse = '" +  beskrivelse.getText() + "', Oppdatert = '" + timestamp
					 + "', Romnavn = '" + romListe.get(rom.getSelectionModel().getSelectedIndex()) + "'" 
					+  "WHERE(avtale.avtaleID = " + avtale.getAvtaleID() + ")";
			
			Avtale avtale = Context.getInstance().getAvtale();
			avtale.setBeskrivelse(beskrivelse.getText());
			avtale.setFraTid(fraTid.getText());
			avtale.setTilTid(tilTid.getText());
			avtale.setDato(dato.getValue().toString());
			avtale.setTittel(tittel.getText());
			avtale.setOppdatert(timestamp.toString());
			avtale.setRom(romListe.get(rom.getSelectionModel().getSelectedIndex()));
			con.skriv(sql1);
			
			String sql2 = "DELETE FROM Brukeravtale WHERE(Brukeravtale.avtaleID = " + Context.getInstance().getAvtale().getAvtaleID() + ")";
			con.skriv(sql2);
			for(String deltaker : inviterte){
				String s2 = "INSERT INTO Brukeravtale VALUES('" + deltaker + "','" + Context.getInstance().getAvtale().getAvtaleID() + "', '0','" + dato.getValue().toString() + " " + fraTid.getText() + "')";
				con.skriv(s2);
			}
			
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
			if(!inviterte.contains(bruker)){
				inviterte.add(bruker);
			}
			invitertListe.setItems(inviterte);
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
				if(!inviterte.contains(bruker2) && !bruker2.equals(Context.getInstance().getPerson().getBrukernavn())){
					inviterte.add(bruker2);
				}
			}
			invitertListe.setItems(inviterte);
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
