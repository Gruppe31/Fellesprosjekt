package Kontrollere;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
	
	@FXML private TextField Varslinger;
	@FXML private Button testBtn;
	@FXML private Button test2Btn;
	@FXML private Pane kalPane;
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	@FXML private Button nyGruppe;
	
	Stage skjemaStage = new Stage();
	Stage loggInnStage = new Stage();
	Stage gruppeStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	public void initialize() throws Exception{
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
		
		ResultSet rs = con.les("SELECT * FROM Avtale WHERE (Avtale.kalenderID =" + Context.getInstance().getKalender().getKalenderID() + ")");
		double hpos = 0;
		double tilTid = 0;
		double lengde = 0;
		String tidText = "";
		int bpos = 0;
		String tittel = "";
		String beskrivelse = "";
		while(rs.next()){
			Stage primaryStage = new Stage();
			//Aapner avtale vinduet etter at rektangelet er trykket paa.
			hpos = tidTilDouble(rs.getString("fraTid"));
			tidText = rs.getString("fraTid");
			tilTid = tidTilDouble(rs.getString("tilTid"));
			tittel = rs.getString("Tittel");
			beskrivelse = rs.getString("Beskrivelse");
			bpos = datoTilDag(rs.getString("Dato"));
			Generator gen = new Generator();
			lengde = tilTid - hpos;
			EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
				public void handle(InputEvent event) {
					try {
						System.out.println(gen);
						launchGUI.start(primaryStage);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			kalPane.getChildren().addAll(gen.rectGen(bpos,hpos,lengde, handler, tittel, beskrivelse), gen.lblGen(bpos,hpos, tittel + " " + tidText.substring(0, 5), handler));
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
	
	
	@FXML
	void test2Btn() throws Exception{
		System.out.println("Test 2");
		
		
		
		
		//Skal gjore alle sql sporringene her i ett
		
		//tid.bindBidirectional(contact.tidProperty());
		//dag.bindBidirectional(contact.dagProperty());
	}
	
	public double tidTilDouble(String tid){
		String[] tallSplittet = tid.split(":");
		String dou = tallSplittet[0] + "." + (int)((Double.parseDouble(tallSplittet[1])/60.0)*100);
		return Double.parseDouble(dou);
	}
	
	public int datoTilDag(String dato){
		LocalDate dag = LocalDate.parse(dato);
		//mandag = 1, tirsdag = 2 osv.
		int dagIuken = dag.getDayOfWeek().getValue();
		return dagIuken-1;
	}
	
	
	@FXML
	void testBtn(){
		
		System.out.println("Test");
		
		Stage primaryStage = new Stage();
		
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
			public void handle(InputEvent event) {
				try {
					launchGUI.start(primaryStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Generator gen = new Generator();
		
		kalPane.getChildren().addAll(gen.rectGen(0,0,1, handler), gen.lblGen(0,0," Møte 00:00", handler));
		
		/*
		Rectangle rect = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl = new Label(" Møte 00:00");
		rect.setStroke(Color.BLACK);
		rect.relocate(0,0);
		testlbl.relocate(0, 0);
		kalPane.getChildren().addAll(rect,testlbl);
		
		*/
		
		
		/*
		
		/*
		rect.setOnMouseClicked(handler);
		testlbl.setOnMouseClicked(handler);
		
		/*
	
		
		tid.bind(fraTid.textProperty());
		dag.bind(dagText.textProperty());
		text.bind(avtaleText.textProperty());
		
		stage.setScene(scene);
		
		stage.tidProperty().bind(tid);
		stage.dagProperty().bind(dag);
		stage.textProperty().bind(text);
		
		stage.show();
		
        IntegerProperty text  = new SimpleIntegerProperty(1);
        NumberBinding sum = num1.add(num2);
        System.out.println(sum.getValue());
        num1.set(2);
        System.out.println(sum.getValue());
    }
}
		
		 */
				
		
	}
	
}
