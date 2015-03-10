package Kontrollere;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Avtale;
import model.Context;

public class Generator {
	
	//private Avtale avtale = Context.getInstance().getAvtale();
	
	
	//String tid = avtale.getFraTid();
	//String dag = avtale.getDato();
	
	//tid.bindBidirectional(contact.tidProperty());
	//dag.bindBidirectional(contact.dagProperty());
	
	int hpos = 0; //Integer.parseInt(tid);
	int bpos = 0; //Integer.parseInt(dag);
	

	Rectangle rectGen(int dag, int tid, EventHandler<InputEvent> handler){
		Rectangle rect = new Rectangle(135, 30, Color.CORNSILK);
		rect.setStroke(Color.BLACK);
		rect.relocate(dag*135, tid*30);
		rect.setOnMouseClicked(handler);
		return rect;
	}
	
	Label lblGen(int dag, int tid, String text, EventHandler<InputEvent> handler){
		Label lbl = new Label(text); //avtale.toString());
		lbl.relocate(dag*135, tid*30);
		lbl.setOnMouseClicked(handler);
		return lbl;
	}
}