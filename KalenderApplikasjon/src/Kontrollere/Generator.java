package Kontrollere;


import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Avtale;

public class Generator {
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
	
	Label lblGen(int dag, double tid,double lengde, String text,String text2, EventHandler<InputEvent> handler){
		if(lengde <= 0.50){
			Label lbl = new Label("");
			return lbl;
		}
		
		if(text.length() > 11){
			Label lbl = new Label(" ... "+text2);
			lbl.relocate((dag*135)+2, tid*30);
			lbl.setOnMouseClicked(handler);
			return lbl;
		}
		
		else{
			Label lbl = new Label(text+" "+text2);
			lbl.relocate((dag*135)+2, tid*30);
			lbl.setOnMouseClicked(handler);
			return lbl;
		}
	}
	
	public Avtale getAvtale(){
		return this.avtale;
	}
	
}