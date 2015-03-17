package Kontrollere;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Context;
import model.LaunchGUI;
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
	@FXML private Button nesteUke;
	@FXML private Button forrigeUke;
	@FXML private Label ukelbl;
	
	Stage infoStage = new Stage();
	Stage skjemaStage = new Stage();
	Stage searchStage = new Stage();
	Stage loggInnStage = new Stage();
	Stage gruppeStage = new Stage();
	Stage mainStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	ArrayList<Rectangle> list = new ArrayList<>();
	
	public void initialize() throws Exception{
		//Egen metode for aa initialisere alle avtalene.
		kalPane.setStyle("-fx-background-color: transparent;");
		int ukeNaa = hvilkenUke(date);
		ukelbl.textProperty().set("Uke: "+ukeNaa);
		
		
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
		
		String sql;
		if (Context.getInstance().getTypeKalender()) {
			sql = "SELECT * "
					+ "FROM Avtale,brukeravtale "
					+ "WHERE(AVTALE.AvtaleID = brukeravtale.avtaleID) "
					+ "AND(brukeravtale.Brukernavn = '" + brukerNavn + "')";
			initialiserKalender(sql);
		}else{
			sql = "SELECT * FROM Avtale WHERE (Avtale.kalenderID =" + Context.getInstance().getKalender().getKalenderID() + ")";
			initialiserKalender(sql);
		}
	}
	
	public void initialiserKalender(String sql) throws Exception{
		System.out.println("Test 2");
		kalPane.getChildren().clear();
		//Oppdaterer
		ResultSet rs = con.les(sql);
		String fraTid = "";
		String tilTid = "";
		String dato = "";
		String tittel = "";
		String beskrivelse = "";
		String oppdatert = "";
		String rom = "";
		String leder = "";
		int avtaleID = 0;
		int kalenderID = 0;
		double lengde = 0;
		
		Calendar now  = Calendar.getInstance();
		Date date = now.getTime();
		int uke = hvilkenUke(date);
		
		while(rs.next()){
			Stage primaryStage = new Stage();
			//Aapner avtale vinduet etter at rektangelet er trykket paa.
			fraTid = rs.getString("fraTid");
			tilTid = rs.getString("tilTid");
			dato = rs.getString("Dato");
			tittel = rs.getString("Tittel");
			beskrivelse = rs.getString("Beskrivelse");
			oppdatert = rs.getString("Oppdatert");
			rom = rs.getString("Romnavn");
			leder =rs.getString("leder");
			avtaleID = rs.getInt("AvtaleID");
			kalenderID = rs.getInt("KalenderID");
			
			
			ukelbl.textProperty().set("Uke: "+uke);
			
			Generator gen = new Generator();
			lengde = tidTilDouble(tilTid) - tidTilDouble(fraTid);
			EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
				public void handle(InputEvent event) {
					try {
						Context.getInstance().setAvtale(gen.getAvtale());;
						launchGUI.startInfo(primaryStage);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			Rectangle rect = gen.rectGen(datoTilDag(dato),tidTilDouble(fraTid),lengde,handler, fraTid, tilTid,dato,tittel,beskrivelse,oppdatert,rom,leder,avtaleID,kalenderID);
			
			if(hvilkenUke(rs.getDate("Dato")) == uke){
				kalPane.getChildren().addAll(rect, gen.lblGen(datoTilDag(dato),tidTilDouble(fraTid), lengde,tittel + " " + fraTid.substring(0, 5), handler));
			}
			
			list.add(rect);
			sjekkRectKollisjon(rect);
		}
	}
	
	//For aa initialisere neste eller forrige uke paa kalPane
	public void initializeNext(int uke) throws Exception{
		//Pga av diverse kluss er det mye repitisjon i metoden her.
		kalPane.setStyle("-fx-background-color: transparent;");
		ukelbl.textProperty().set("Uke: "+uke);
		
		String sql;
		if (Context.getInstance().getTypeKalender()) {
			sql =  "SELECT * "
					+ "FROM Avtale,brukeravtale "
					+ "WHERE(AVTALE.AvtaleID = brukeravtale.avtaleID) "
					+ "AND(brukeravtale.Brukernavn = '" + Context.getInstance().getPerson().getBrukernavn() + "')";
		}else{
			sql = "SELECT * FROM Avtale WHERE (Avtale.kalenderID =" + Context.getInstance().getKalender().getKalenderID() + ")";
		}
		ResultSet rs = con.les(sql);
		double hpos = 0;
		double tilTid = 0;
		double lengde = 0;
		
		String leder = "";
		String fraTid = "";
		String oppdatert = "";
		String rom = "";
		int avtaleID = 0;
		int kalenderID = 0;
		String dato = "";
		String tid = "";
		
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
			
			leder = rs.getString("leder");
			fraTid = rs.getString("fraTid");
			oppdatert = rs.getString("oppdatert");
			rom = rs.getString("romNavn");
			avtaleID = rs.getInt("avtaleID");
			kalenderID = rs.getInt("kalenderID");
			dato = rs.getString("dato");
			tid = rs.getString("tilTid");
			
			
			ukelbl.textProperty().set("Uke: "+uke);
			
			
			EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
				public void handle(InputEvent event) {
					try {
						Context.getInstance().setAvtale(gen.getAvtale());
						launchGUI.startInfo(primaryStage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			
			Rectangle rect = gen.rectGen(datoTilDag(dato),tidTilDouble(fraTid),lengde,handler, fraTid, tid,dato,tittel,beskrivelse,oppdatert,rom,leder,avtaleID,kalenderID);
			
			if(hvilkenUke(rs.getDate("Dato")) == uke){
				kalPane.getChildren().addAll(rect, gen.lblGen(bpos,hpos,lengde, tittel + " " + tidText.substring(0, 5), handler));
			}
			list.add(rect);
			sjekkRectKollisjon(rect);
		}
	}
		
	@FXML
	public void testValg() throws Exception{
		//Faa til uten aa lukke vinduet for saa aa aapne et nytt et.
		//Skal faa til aa aapne kalenderen en klikker paa.
		mineKalendere.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mo){
				//Kall metoden for aa initialisere alle avtalene her.
				if (mineKalendere.getSelectionModel().getSelectedIndex() == 0) {
					String sql = "SELECT kalenderID FROM Person WHERE (Brukernavn = '" +  Context.getInstance().getPerson().getBrukernavn() + "')";
					try{
						ResultSet rs = con.les(sql);
						int kalenderID = 0;
						while(rs.next()){
							kalenderID = rs.getInt("KalenderID");
						}
						Context.getInstance().getKalender().setKalenderID(kalenderID);
						Context.getInstance().getGruppe().setGruppenavn(null);
						Context.getInstance().setTypeKalender(true);
						launchGUI.startMain(mainStage);
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
					String navn = kalenderListe.get(mineKalendere.getSelectionModel().getSelectedIndex());
					String sql = "SELECT kalenderID "
							+ "FROM Kalendergruppe, gruppe "
							+ "WHERE(gruppe.Gruppenavn = '" + navn + "') "
							+ "AND(Gruppe.gruppeID = kalendergruppe.gruppeid)";
					try{
						ResultSet rs = con.les(sql);
						int kalenderID = 0;
						while(rs.next()){
							kalenderID = rs.getInt("KalenderID");
						}
						Context.getInstance().getKalender().setKalenderID(kalenderID);
						Context.getInstance().getGruppe().setGruppenavn(navn);
						Context.getInstance().setTypeKalender(false);
						launchGUI.startMain(mainStage);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				Stage stage = (Stage) mineKalendere.getScene().getWindow(); 
				stage.close();
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
						Context.getInstance().setSokeTekst(sok.getText());
						launchGUI.startSearch(searchStage);
					}
				} catch (IOException e) {
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

		//Oppdaterer hele kalenderen
		String sql;
		String brukerNavn = Context.getInstance().getPerson().getBrukernavn();
		if (Context.getInstance().getTypeKalender()) {
			sql = "SELECT * "
					+ "FROM Avtale,brukeravtale "
					+ "WHERE(AVTALE.AvtaleID = brukeravtale.avtaleID) "
					+ "AND(brukeravtale.Brukernavn = '" + brukerNavn + "')";
			initialiserKalender(sql);
		}else{
			sql = "SELECT * FROM Avtale WHERE (Avtale.kalenderID =" + Context.getInstance().getKalender().getKalenderID() + ")";
			initialiserKalender(sql);
		}
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
	
	public static int hvilkenUke(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int uke = now.get(Calendar.WEEK_OF_YEAR);
		return uke;
		
	}
	
	
	private void sjekkRectKollisjon(Shape rect){
		boolean kollisjonFunnet = false;
		for(Shape static_rect : list){
			if(static_rect != rect){
				
				Shape intersect = Shape.intersect(rect, static_rect);
				if(intersect.getBoundsInLocal().getWidth() != -1 && intersect.getBoundsInLocal().getHeight() != -1){
					kollisjonFunnet = true;
					if(rect.getBoundsInLocal().getHeight() > static_rect.getBoundsInLocal().getHeight()){
						rect.toBack();
					}
				}
				
				
			}
		}
		
		if(kollisjonFunnet){
			rect.setOpacity(0.6);
		}
		
	}
	
	
	Calendar now = Calendar.getInstance();
	Date date = now.getTime();
	int denneUke = hvilkenUke(date);
	int ukeTeller;
	
	@FXML
	void nesteUke() throws Exception{
		list.clear();
		int nesteUke;
		nesteUke = denneUke + 1;
		
		
		kalPane.getChildren().clear();
		initializeNext(nesteUke);
		if(nesteUke == 54){
			nesteUke = 1;
			denneUke = nesteUke;
		}
		else{
			denneUke = nesteUke;			
		}
	}
	 
	@FXML
	void forrigeUke() throws Exception{
		list.clear();
		int nesteUke;
		nesteUke = denneUke - 1;			
	
		
		kalPane.getChildren().clear();
		initializeNext(nesteUke);
		if(nesteUke == 1){
			nesteUke = 54;
			denneUke = nesteUke;
		}
		else{
			denneUke = nesteUke;			
		}
		
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
					e.printStackTrace();
				}
			}
		};
		
		
		
		//Generator gen = new Generator();
		
		//kalPane.getChildren().addAll(gen.rectGen(0,0,1, handler), gen.lblGen(0,0," Møte 00:00", handler));
		
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
