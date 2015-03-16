package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchGUI extends Application{
	
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/skjema.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.setTitle("VARSLINGER");
	        primaryStage.show();
		
	}
	
	public void startMain(Stage mainStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/main2.fxml"));
	        mainStage.setScene(new Scene(root));
	        mainStage.setTitle("MAIN");
	        mainStage.show();
		
	}
	
	public void startSkjema(Stage skjemaStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/skjema.fxml"));
	        skjemaStage.setScene(new Scene(root));
	        skjemaStage.setTitle("AVTALE");
	        skjemaStage.show();
		
	}
	
	public void startInfo(Stage infoStage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/info.fxml"));
		infoStage.setScene(new Scene(root));
		infoStage.setTitle("AVTALE");
		infoStage.show();
		
	}
	
	public void startSignup(Stage signupStage) throws IOException{
		  FXMLLoader fxmlLoader = new FXMLLoader();
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/SignUp.fxml"));
	        signupStage.setScene(new Scene(root));
	        signupStage.setTitle("SIGN UP");
	        signupStage.show();
		
	}
	
	public void startLoggInn(Stage loggInnStage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/loggInn.fxml"));
		loggInnStage.setScene(new Scene(root));
		loggInnStage.setTitle("SIGN UP");
		loggInnStage.show();
		
	}
	
	public void startGruppe(Stage gruppeStage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/Gruppe.fxml"));
		gruppeStage.setScene(new Scene(root));
		gruppeStage.setTitle("Lag ny gruppe");
		gruppeStage.show();
		
	}
	
	public void startSearch(Stage searchStage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("/GUI/Search.fxml"));
		searchStage.setScene(new Scene(root));
		searchStage.setTitle("Lag ny gruppe");
		searchStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
