package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Varsling;

public class LaunchGUI extends Application{
	
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
		  fxmlLoader.setController(new Varsling());
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/Varsling.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.setTitle("VARSLINGER");
	        primaryStage.show();
		
	}
	
	public void start2(Stage testStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
		  fxmlLoader.setController(new Varsling());
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/Main.fxml"));
	        testStage.setScene(new Scene(root));
	        testStage.setTitle("VARSLINGER");
	        testStage.show();
		
	}

	public static void main(String[] args) {
	
		launch(args);
	}
	
	
}
