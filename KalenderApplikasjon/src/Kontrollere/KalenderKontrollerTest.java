package Kontrollere;

import java.io.IOException;

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
import model.Kalender;
import model.LaunchGUI;

public class KalenderKontrollerTest {
	
	private Connector con = new Connector();
	
	@FXML private TextField sok;
	
	
	@FXML private TextField Varslinger;
	@FXML private Button nyAvtale;
	@FXML private Button loggUt;
	@FXML private Button testBtn;
	@FXML private Pane kalPane;
	
	@FXML
	void testBtn(){
		
		System.out.println("Test");
		
		Rectangle rect = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl = new Label(" Møte 00:00");
		rect.setStroke(Color.BLACK);
		rect.relocate(0,0);
		testlbl.relocate(0, 0);
		kalPane.getChildren().addAll(rect,testlbl);
		
		Rectangle rect2 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl2 = new Label(" Møte 01:00");
		rect2.setStroke(Color.BLACK);
		rect2.relocate(135,30);
		testlbl2.relocate(135, 30);
		kalPane.getChildren().addAll(rect2,testlbl2);
		
		Rectangle rect3 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl3 = new Label(" Møte 02:00");
		rect3.setStroke(Color.BLACK);
		rect3.relocate(270,60);
		testlbl3.relocate(270, 60);
		kalPane.getChildren().addAll(rect3,testlbl3);
		
		Rectangle rect4 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl4 = new Label(" Møte 06:00");
		rect4.setStroke(Color.BLACK);
		rect4.relocate(810,180);
		testlbl4.relocate(810, 180);
		kalPane.getChildren().addAll(rect4,testlbl4);
		
		Rectangle rect5 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl5 = new Label(" Møte 05:00");
		rect5.setStroke(Color.BLACK);
		rect5.relocate(675,150);
		testlbl5.relocate(675, 150);
		kalPane.getChildren().addAll(rect5,testlbl5);
		
		Rectangle rect6 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl6 = new Label(" Møte 03:00");
		rect6.setStroke(Color.BLACK);
		rect6.relocate(405,90);
		testlbl6.relocate(405, 90);
		kalPane.getChildren().addAll(rect6,testlbl6);

		Rectangle rect7 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl7 = new Label(" Møte 04:00");
		rect7.setStroke(Color.BLACK);
		rect7.relocate(540,120);
		testlbl7.relocate(540, 120);
		kalPane.getChildren().addAll(rect7,testlbl7);
		
		Rectangle rect8 = new Rectangle(135, 30, Color.CORNSILK);
		Label testlbl8 = new Label(" Møte 01:00");
		rect8.setStroke(Color.BLACK);
		rect8.relocate(0,30);
		testlbl8.relocate(0, 30);
		kalPane.getChildren().addAll(rect8,testlbl8);
		
		
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
		
		
		rect.setOnMouseClicked(handler);
		testlbl.setOnMouseClicked(handler);
		
		rect2.setOnMouseClicked(handler);
		testlbl2.setOnMouseClicked(handler);
		
		rect3.setOnMouseClicked(handler);
		testlbl3.setOnMouseClicked(handler);
		
		rect4.setOnMouseClicked(handler);
		testlbl4.setOnMouseClicked(handler);
		
		rect5.setOnMouseClicked(handler);
		testlbl5.setOnMouseClicked(handler);
		
		rect6.setOnMouseClicked(handler);
		testlbl6.setOnMouseClicked(handler);
		
		rect7.setOnMouseClicked(handler);
		testlbl7.setOnMouseClicked(handler);
		
		rect8.setOnMouseClicked(handler);
		testlbl8.setOnMouseClicked(handler);
		
		
		
		
		/* Pseudo loop for å opprette møte tidspunkt i kalender
		 
		for(int i=0; ...; i++){
			rectName = rectangle+i
			lblName = testlbl+i
			hpos = 135*i
			bpos = 30*i
			
			Rectangle rectName = new Rectangle(135, 30, color.CORNSILK);
			Label lblName = new Label(text);

			rectName.relocate(hpos, vpos);
			lblName.relocate(hpos, vpos);

			kalPane.getChildren().addAll(rectName, lblName);
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

