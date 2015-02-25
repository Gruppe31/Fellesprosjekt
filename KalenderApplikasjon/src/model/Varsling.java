package model;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Varsling extends Application {
	
	public static void main(String[] args){
		launch(args);
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Varsling");
		final Popup popup = new Popup();
		popup.setX(960);
		popup.setY(500);
		popup.getContent().addAll(new Rectangle(180,25, Color.AQUAMARINE));
		popup.getContent().addAll(new Label("Du har en ny notification!"));
		
		/*/FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(new **KONTROLLER HER**());
	    	Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("Varsling.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
		/*/
		
	

		Button show = new Button("Show");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				popup.show(primaryStage);
			}
		});
		
		Button hide = new Button("Hide");
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				popup.hide();
				
			}
		});
		
		
		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(show, hide);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
		
	}
	
	

}
