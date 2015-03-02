package Kontrollere;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Person;

public class SignUpKontroller {
	
	private Person model;
	
	@FXML Label ForNavnRule;
	@FXML Label EtterNavnRule;
	@FXML Label BrukerNavnRule;
	@FXML Label PassordRule;
	@FXML TextField ForNavnField;
	@FXML TextField EtterNavnField;
	@FXML TextField BrukerNavnField;
	@FXML PasswordField PassordField;
	@FXML Button MeldDegInn;
	@FXML Button Avbryt;
	
	
	public void initialize(){
		
	}
	
	
	//sjekke om Fornavn er gyldig
	private void validateForNavnView(String newValue) {
		validate(newValue, "[\\p{L}\\.\\-\\s)]*", ForNavnField, ForNavnRule);
	}
	
	//set fornavn lik input
	private void updateForNavnModel() {
		model.setFornavn(ForNavnField.getText());
	}
	
	
	//sjekke om input er riktig/godkjent for fornavn
	@FXML void ForNavnFieldChange(ObservableValue<? extends String> property, String oldValue, String newValue) {
		validateForNavnView(newValue);
	}
	
	//prøve å oppdatere/lagre inupt for fornavn
	@FXML void ForNavnFieldFocusChange(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue) {
		if (! newValue) {
			try {
				updateForNavnModel();
			} catch (Exception e) {
				
			}
		}
	}
	
	//sjekke om etternavn er gyldig
	private void validateEtterNavnView(String newValue) {
		validate(newValue, "[\\p{L}\\.\\-\\s)]*", EtterNavnField, EtterNavnRule);
	}
	
	//sett etternavn lik input
	private void updateEtterNavnModel() {
		model.setEtternavn(ForNavnField.getText());
	}
	
	//sjekke om input er riktig/godkjent for etternavn
	@FXML void EtterNavnFieldChange(ObservableValue<? extends String> property, String oldValue, String newValue) {
		validateEtterNavnView(newValue);
	}
		
	//prøve å oppdatere/lagre inupt for etternavn
	@FXML void EtterNavnFieldFocusChange(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue) {
		if (! newValue) {
			try {
				updateEtterNavnModel();
				} 
			catch (Exception e) {
					
			}
		}
		
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
