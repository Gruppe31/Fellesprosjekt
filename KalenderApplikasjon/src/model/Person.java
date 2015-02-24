package logikk;


import java.security.KeyStore.PasswordProtection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.PasswordField;

public class Person {
	
	private StringProperty FornavnProperty = new SimpleStringProperty();
	private StringProperty EtternavnProperty = new SimpleStringProperty();
	private StringProperty BrukernavnProperty = new SimpleStringProperty();
	private PasswordField PassordField = new PasswordField();
		

	public String getFornavn() {
		return FornavnProperty.getValue();
	}

	public void setFornavn(String Fornavn) {
		FornavnProperty.setValue(Fornavn);
	}

	public StringProperty FornavnProperty() {
		return FornavnProperty;
	}

	public String getEtternavn() {
		return EtternavnProperty.getValue();
	}

	public void setEtternavn(String rom) {
		EtternavnProperty.setValue(rom);
	}

	public StringProperty EtternavnProperty() {
		return EtternavnProperty;
	}
	
	public StringProperty BrukernavnProperty(){
		return BrukernavnProperty;
	}
	
	public String getBrukernavn(){
		return FornavnProperty.getValue();
	}
	
	public void setBrukernavn(String Brukernavn){
		BrukernavnProperty.setValue(Brukernavn);
	}
	
	public PasswordField PassordField(){
		return PassordField;
	}
	
	public String getPassord(){
		return PassordField.getText();
	}
	
	public void setPassord(String Passord){
		PassordField.setText(Passord);
	}

}
