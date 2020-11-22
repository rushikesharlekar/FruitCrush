package application;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Block extends StackPane {
	
	private String type;
	private Text text;

	public Block(String type) {
		this.type = type;
		
		text = new Text(type);
		text.setFont(this.text.getFont().font(20));
		text.setFill(Color.WHITE);
		
		Rectangle bg = new Rectangle(50,50);
		bg.setOpacity(0.7);
		bg.setFill(Color.BLACK);
		bg.setEffect(new GaussianBlur(3.5));
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(bg, text);
		
//		setOnMouseEntered(event -> {
//			bg.setFill(Color.WHITE);
//			text.setFill(Color.BLACK);
//		});
//		
//		setOnMouseExited(event -> {
//				bg.setFill(Color.BLACK);
//				text.setFill(Color.WHITE);
//		});
		
		
		
	}

}
