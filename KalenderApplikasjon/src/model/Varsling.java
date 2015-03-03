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
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Varsling extends Application {
	
	public static void main(String[] args){
		launch(args);
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Varsling");
		
		
		Popup popup = new Popup();
		popup.setX(1300);
		popup.setY(600);
		
		Label label = new Label("Du har en ny varsling!");
		Rectangle rect = new Rectangle(200, 100, Color.AQUAMARINE);
		
		popup.getContent().addAll(rect, label);
		
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
			public void handle(InputEvent event) {
				try{
		            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Varsling.fxml"));
		            Parent root1 = (Parent) fxmlLoader.load();
		            Stage stage = new Stage();
		            stage.initModality(Modality.APPLICATION_MODAL);
		            stage.initStyle(StageStyle.UNDECORATED);
		            stage.setTitle("VARSLING");
		            stage.setScene(new Scene(root1));  
		            stage.show();
		          }
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("SEND VIDERE TIL VARSLINGER GUI!");
				event.consume();
			}
		};
		
		rect.setOnMouseClicked(handler);
		label.setOnMouseClicked(handler);
			
		
	
		Button show = new Button("Show");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				popup.show(primaryStage);
			}
		});
		
		Button hide = new Button("Hide");
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				try{
		            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Main.fxml"));
		            Parent root1 = (Parent) fxmlLoader.load();
		            Stage stage = new Stage();
		            stage.initModality(Modality.APPLICATION_MODAL);
		            stage.initStyle(StageStyle.UNDECORATED);
		            stage.setTitle("MAIN");
		            stage.setScene(new Scene(root1));  
		            stage.show();
		          }
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("SEND VIDERE TIL VARSLINGER GUI!");
				event.consume();
			}
				
			
			
		});
		
		
		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(show, hide);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
		
	}


	
	

}
