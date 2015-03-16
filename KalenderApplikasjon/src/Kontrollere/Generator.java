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
	private int dag;
	private double tid;
	Avtale avtale;

	Rectangle rectGen(int dag, double tid, double lengde, EventHandler<InputEvent> handler, String fraTid, String tilTid, String dato, String tittel, String beskrivelse,
			String oppdatert, String rom, String leder, int avtaleID, int kalenderID){
		this.avtale = new Avtale(fraTid, tilTid, dato, tittel, beskrivelse, oppdatert, rom, leder, avtaleID, kalenderID);
		Rectangle rect = new Rectangle(132, 30*lengde, Color.CORNSILK);
		rect.setStroke(Color.BLACK);
		rect.relocate((dag*135)+2, tid*30);
		rect.setOnMouseClicked(handler);
		return rect;
	}
	
	Label lblGen(int dag, double tid,double lengde, String text, EventHandler<InputEvent> handler){
		if(lengde <= 0.50){
			Label lbl = new Label("");
			return lbl;
		}
		else{
			Label lbl = new Label(text);
			lbl.relocate((dag*135)+2, tid*30);
			lbl.setOnMouseClicked(handler);
			return lbl;
		}
	}
	
	public Avtale getAvtale(){
		return this.avtale;
	}
	
}