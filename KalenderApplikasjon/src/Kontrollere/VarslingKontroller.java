package Kontrollere;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VarslingKontroller {
	
	@FXML private Button forrige;
	@FXML private Button neste;
	@FXML private ListView varslinger;
	@FXML private Label sidenr;
	@FXML private Label antallSider;
	
	@FXML
	void Forrige(){
		//er satt til visible=false i fxml-fila. må settes til visible når man er på feks s. 2 og skal ha mulighet til å trykke forrige
	}
	
	@FXML void Neste(){
		//er satt til visible=false i fxml-fila. må settes til visible når det er flere sider på varslingpanelet
		
	}

}
