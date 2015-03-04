package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LaunchGUI;

public class VarslingerTest extends Application {
	
	public static void main(String[] args){
		launch(args);
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Varsling");
		
		
		LaunchGUI launchGUI = new LaunchGUI();
		
		Popup popup = new Popup();
		popup.setX(1300);
		popup.setY(600);
		
		Label label = new Label("Du har 3 nye varslinger!");
		Rectangle rect = new Rectangle(200, 100, Color.AQUAMARINE);
		
		popup.getContent().addAll(rect, label);
		
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
			public void handle(InputEvent event) {
				try {
					launchGUI.start(primaryStage);
					popup.hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		rect.setOnMouseClicked(handler);
		label.setOnMouseClicked(handler);
			
		
	
		Button show = new Button("Vis popup");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				popup.show(primaryStage);
			}
		});
		
		Stage mainStage = new Stage();
		
		Button hide = new Button("Main.fxml");
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				popup.hide();
				try {
					launchGUI.startMain(mainStage);
					popup.hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	    
		
		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(show, hide);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
		
	}


	
	

}
