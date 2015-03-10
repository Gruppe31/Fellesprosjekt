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
	
	private String sql;
	
	
		
	
	Stage mainStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	@FXML
	public void meldDegInn(){
		sql = "INSERT INTO Person VALUES('" + BrukerNavnField.getText() + "','" + PassordField.getText() + "','1')";
		try {
			con.skriv(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Context.getInstance().getPerson().setBrukernavn(BrukerNavnField.getText());
			Context.getInstance().getPerson().setPassord(PassordField.getText());
			launchGUI.startMain(mainStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	public boolean riktigPassord(String passord){
		if(passord.length() < 3){
			return false;
		}
		return true;
		
	}
}
