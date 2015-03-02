package screenTest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Screen1Controller implements Initializable, ControlledScreen{
	
	ScreensController myController;
	


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	
}

	public void setScreenParent(ScreensController screensParent){
		myController = screensParent;
	}
	
	@FXML
	private void goToMain(ActionEvent event){
		myController.setScreen(ScreensFramework.MAIN_SCREEN);
	}
}
