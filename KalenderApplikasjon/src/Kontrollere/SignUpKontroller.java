package Kontrollere;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LaunchGUI;
import model.Person;
import model.LaunchGUI;

public class SignUpKontroller {
	
	private Person model;
	
	@FXML Label BrukerNavnRule;
	@FXML Label PassordRule;
	@FXML TextField BrukerNavnField;
	@FXML PasswordField PassordField;
	@FXML Button MeldDegInn;
	@FXML Button Avbryt;
	
	
		
	
	Stage mainStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	@FXML
	void meldDegInn(){
		MeldDegInn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				try {
					launchGUI.startSignup(mainStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	//sjekke om Brukernavn er gyldig
		private void validateBrukerNavnView(String newValue) {
			validate(newValue, "[\\p{L}\\.\\-\\s)]*", BrukerNavnField, BrukerNavnRule);
		}
		
		//set brukernavn lik input
		private void updateBrukerNavnModel() {
			model.setBrukernavn(BrukerNavnField.getText());
		}
		
		
		//sjekke om input er riktig/godkjent for Brukernavn
		@FXML void BrukerNavnFieldChange(ObservableValue<? extends String> property, String oldValue, String newValue) {
			validateBrukerNavnView(newValue);
		}
		
		//prøve å oppdatere/lagre inupt for Brukernavn
		@FXML void BrukerNavnFieldFocusChange(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue) {
			if (! newValue) {
				try {
					updateBrukerNavnModel();
				} catch (Exception e) {
					
				}
			}
		}

		//sjekke om Passord er gyldig
		private void validatePassordView(String newValue) {
			validate(newValue, "[\\p{L}\\.\\-\\s)]*", PassordField, PassordRule);
		}
		
		//set Passord lik input
		private void updatePassordModel() {
			model.setPassord(PassordField.getText());
		}
		
		
		//sjekke om input er riktig/godkjent for Passord
		@FXML void PassordFieldChange(ObservableValue<? extends String> property, String oldValue, String newValue) {
			validatePassordView(newValue);
		}
		
		//prøve å oppdatere/lagre inupt for Passord
		@FXML void PassordFieldFocusChange(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue) {
			if (! newValue) {
				try {
					updatePassordModel();
				} catch (Exception e) {
					
				}
			}
		}
		
	//hvis input er gyldig, blå kant, hvis ikke rød kant 
	private void validate(String value, String regex, TextField textField, Label ruleField) {
		boolean isValid = value.matches(regex);
		String color = isValid ? "blue" : "red";
		textField.setStyle("-fx-border-color: " + color);
//		if (ruleField != null) {
//			ruleField.setVisible(! isValid);
//		}
	}
	

}
