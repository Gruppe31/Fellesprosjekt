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
	

	Rectangle rectGen(int dag, double tid, int lengde, EventHandler<InputEvent> handler){
		Rectangle rect = new Rectangle(135, 30*lengde, Color.CORNSILK);
		rect.setStroke(Color.BLACK);
		rect.relocate(dag*135, tid*30);
		rect.setOnMouseClicked(handler);
		return rect;
	}
	
	Label lblGen(int dag, double tid, String text, EventHandler<InputEvent> handler){
		Label lbl = new Label(text); //avtale.toString());
		lbl.relocate(dag*135, tid*30);
		lbl.setOnMouseClicked(handler);
		return lbl;
	}
}