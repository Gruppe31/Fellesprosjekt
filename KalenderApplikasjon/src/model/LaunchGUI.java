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
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/skjema.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.setTitle("VARSLINGER");
	        primaryStage.show();
		
	}
	
	public void startMain(Stage testStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/Main.fxml"));
	        testStage.setScene(new Scene(root));
	        testStage.setTitle("MAIN");
	        testStage.show();
		
	}
	
	public void startSkjema(Stage skjemaStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/skjema.fxml"));
	        skjemaStage.setScene(new Scene(root));
	        skjemaStage.setTitle("AVTALE");
	        skjemaStage.show();
		
	}

	public static void main(String[] args) {
	
		launch(args);
	}
	
	
}
