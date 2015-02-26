package Kontrollere;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import model.Avtale;

public class AvtaleKontroller {
	Avtale model = new Avtale();
	@FXML private TextField tittel;
	@FXML private TextArea beskrivelse;
	
	@FXML private DatePicker dato;
	@FXML private TextField fra;
	@FXML private TextField til;
	@FXML private TextField rom;
	
	@FXML private TextField leggTilPerson;
	@FXML private ListView deltagere;
	@FXML private Rectangle repetisjonRectangle;
	@FXML private DatePicker slutt;
	@FXML private Rectangle sluttRectangle;

	@FXML private Button fjernDeltager;
	@FXML private Button fjernDeltager;
	@FXML private Button lagre;
	@FXML private Button avbryt;
	
	void fjernDeltager(){
		//Fjerner deltagere fra listen
	}
	
	void lagre(){
		// Her skal alle valideres og lagres
	}
	
	void avbryt(){
		// Her går man ut uten å ha lagre noe
	}
	
	private boolean erDatoRiktig(LocalDate newValue){
		if(newValue == null){
			return false;
		}
		if(newValue.isBefore(LocalDate.now())){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	
	
	
}
