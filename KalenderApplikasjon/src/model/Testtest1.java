package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Testtest1 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		String test;
		
		
		HBox hbox = new HBox(10);
		hbox.setLayoutX(25);
		hbox.setLayoutY(25);
		
		TextField textField = new TextField();
		hbox.getChildren().addAll(textField);
		
		primaryStage.setScene(new Scene(hbox));
		primaryStage.show();
		
		test = textField.getText();
		textField.onKeyPressedProperty();
		
		System.out.println(test);
	}

}
