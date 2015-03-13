package Kontrollere;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.LaunchGUI;
import model.Person;

public class SearchKontroller {
	
	Person model = new Person();
	ObservableList<String> sokeliste  = FXCollections.observableArrayList();
	@FXML private ListView<String> sok = new ListView<String>(sokeliste);

	@FXML private Button velg;
	@FXML private Button avbryt;

	Stage Stage = new Stage();
	LaunchGUI launchGUI = new LaunchGUI();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@FXML
	void Avbryt(){
		// gå tilbake til kalendervindu uten endringer
		Stage stage = (Stage) avbryt.getScene().getWindow();
		stage.close();
	}
	@FXML
	void Lagre(){
		sokeliste.get(sok.getSelectionModel().getSelectedIndex());
	}

}
