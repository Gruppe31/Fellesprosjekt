package logikk;


import java.security.KeyStore.PasswordProtection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private StringProperty FornavnProperty = new SimpleStringProperty();
	private StringProperty EtternavnProperty = new SimpleStringProperty();
	private StringProperty BrukernavnProperty = new SimpleStringProperty();
	private StringProperty PassordProperty = new SimpleStringProperty();
		

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
	
	public StringProperty PassordProperty(){
		return PassordProperty;
	}
	
	public String getPassord(){
		return PassordProperty.getValue();
	}
	
	public void setPassord(String Passord){
		PassordProperty.setValue(Passord);
	}

}
