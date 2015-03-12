package Kontrollere;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import mysql.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Avtale;
import model.Context;
import model.Kalender;
import model.LaunchGUI;

public class KalenderKontrollerTest {
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	
	@FXML private TextField Varslinger;
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	@FXML private Button testBtn;
	@FXML private Button test2Btn;
	@FXML private Pane kalPane;
	
	@FXML
	void test2Btn(){
		System.out.println("Test 2");
		
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
		
		
		Avtale avtale = Context.getInstance().getAvtale();
		
		
		String tid = avtale.getFraTid();
		String dag = avtale.getDato();
		
		//tid.bindBidirectional(contact.tidProperty());
		//dag.bindBidirectional(contact.dagProperty());
		
		
		double hpos = tidTilDouble(tid);
		int bpos = datoTilDag(dag);
		
		
		Generator gen = new Generator();
		
		kalPane.getChildren().addAll(gen.rectGen(bpos,hpos,1, handler), gen.lblGen(bpos,hpos," Møte "+tid, handler));
		
		
	}
	
	public double tidTilDouble(String tid){
		String[] tallSplittet = tid.split(":");
		String dou = tallSplittet[0] + "." + (int)((Double.parseDouble(tallSplittet[1])/60.0)*100);
		return Double.parseDouble(dou);
	}
	//2015-03-27
	//yyyy-mm-dd
	
	public int datoTilDag(String dato){
		LocalDate dag = LocalDate.parse(dato);
		//mandag = 1, tirsdag = 2 osv.
		return dag.getDayOfWeek().getValue();
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
	

	
	Stage skjemaStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	@FXML
	public void sokTrykkEnter(){
		sok.setOnKeyPressed(new EventHandler<KeyEvent>(){
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
		try {
			launchGUI.startSkjema(skjemaStage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML void LoggUt(){
		//sendes tilbake til loggInn-vinduet
	}
	
	

}

