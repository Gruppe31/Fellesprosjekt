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
import model.Context;
import model.LaunchGUI;
import model.Person;
import model.LaunchGUI;
import mysql.Connector;

public class SignUpKontroller {
	
	private Person model;
	

	@FXML Label BrukerNavnRule;
	@FXML Label PassordRule;

	Connector con = new Connector();

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
				String sql = "INSERT INTO Person VALUES('" + BrukerNavnField.getText() + "','" + PassordField.getText() + "')";
				try {
					con.skriv(sql);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					Context.getInstance().getPerson().setBrukernavn(BrukerNavnField.getText());
					Context.getInstance().getPerson().setPassord(PassordField.getText());
					launchGUI.startSignup(mainStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}
