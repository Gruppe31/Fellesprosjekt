package Kontrollere;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Context;
import model.LaunchGUI;
import model.Person;
import mysql.Connector;

public class KalenderKontroller{
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	ObservableList<String> kalenderListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> mineKalendere= new ListView<String>(kalenderListe);
	
	ObservableList<String> varslingListe  = FXCollections.observableArrayList();
	@FXML private ListView<String> mineVarslinger = new ListView<String>(kalenderListe);
	
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	@FXML private Button nyGruppe;
	
	Stage skjemaStage = new Stage();
	Stage loggInnStage = new Stage();
	Stage gruppeStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	public void initialize() {
		//Egen metode for aa initialisere alle avtalene.
		String brukerNavn = Context.getInstance().getPerson().getBrukernavn();
		kalenderListe.add(brukerNavn + " sin kalender");
		String s = "SELECT Gruppenavn FROM Gruppe JOIN Brukergruppe ON(Gruppe.GruppeID = Brukergruppe.GruppeID) WHERE(Brukernavn='" + brukerNavn + "')";
		try {
			ResultSet rs = con.les(s);
			while(rs.next()){
				String gruppeNavn = rs.getString("Gruppenavn");
				
				kalenderListe.add(gruppeNavn);
			}
			mineKalendere.setItems(kalenderListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql2 = "SELECT Tittel FROM Avtale JOIN Brukeravtale ON (Avtale.AvtaleID = Brukeravtale.AvtaleID) WHERE (Brukernavn = '" + brukerNavn + "')"
				+ "";
		try {
			ResultSet rs = con.les(sql2);
			while(rs.next()){
				String avtaleVarsling = rs.getString("Tittel");
				
				varslingListe.add("Ny avtale: " + avtaleVarsling);
			}
			mineVarslinger.setItems(varslingListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void testValg(){
		//Skal faa til aa aapne kalenderen en klikker paa.
		mineKalendere.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mo){
				//Kall metoden for aa initialisere alle avtalene her.
				System.out.println(kalenderListe.get(mineKalendere.getSelectionModel().getSelectedIndex()));
			}
		});
	}
	
	@FXML
	public void sokTrykkEnter(){
		sok.setOnKeyPressed(new EventHandler<KeyEvent>(){
		// Hva skal vi gjore her.
		// Fa opp grupper/brukere som har samme brukernavn.
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
		// nyAvtale-vinduet dukker opp.
		try {
			launchGUI.startSkjema(skjemaStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML void lagNyGruppe(){
		try{
			launchGUI.startGruppe(gruppeStage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML void LoggUt(){
		//sendes tilbake til loggInn-vinduet
		try{
			Stage stage = (Stage) loggUt.getScene().getWindow(); 
			stage.close();
			launchGUI.startLoggInn(loggInnStage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
