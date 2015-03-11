package Kontrollere;

import java.io.IOException;
import java.sql.ResultSet;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
	@FXML Text feilmelding;
	
	Stage mainStage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();
	
	@FXML
	public void meldDegInn(){
		if(riktigBrukernavn()){
			String sql1 = "INSERT INTO Kalender VALUES()";
			try {
				con.skriv(sql1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int kalenderID = 0;
			String sql = "SELECT LAST_INSERT_ID(), kalenderID FROM Kalender";
			try {
				ResultSet rs = con.les(sql);
				while(rs.next()){
					// kalenderID vil da tilslutt vaere den siste ledige nokkelen
					kalenderID = rs.getInt(2);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql2 = "INSERT INTO Person VALUES('" + BrukerNavnField.getText() + "','" + PassordField.getText() + "'," + kalenderID +  ")";
			try {
				con.skriv(sql2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Context.getInstance().getPerson().setBrukernavn(BrukerNavnField.getText());
				Context.getInstance().getPerson().setPassord(PassordField.getText());
				Stage stage = (Stage) MeldDegInn.getScene().getWindow();
				stage.close();
				launchGUI.startMain(mainStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			feilmelding.setVisible(true);
		}
				
	}
	
	public boolean riktigBrukernavn(){
		//sjekker om brukernavnet er i databasen fra foer av
		String sql = "Select Brukernavn FROM Person";
		try {
			ResultSet rs = con.les(sql);
			while(rs.next()){
				String brukernavn = rs.getString("Brukernavn");
				if(brukernavn.equals(BrukerNavnField.getText())){
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean riktigPassord(String passord){
		if(passord.length() < 3){
			return false;
		}
		return true;
		
	}
}
