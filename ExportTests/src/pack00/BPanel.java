package pack00;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BPanel {
	HBox hb1;
	Button b1,b2,b3,b4,b5,b6,b7,b8;
	BPanel(){
		hb1=new HBox();
		b1=new Button();
		b1.setText("Button 1");
		b2=new Button();
		b2.setText("Button 2");

		b3=new Button();
		b3.setText("Button 3");
		
		b4=new Button();
		b4.setText("Button 4");
		
		b5=new Button();
		b5.setText("toggleDrag");
		
		b6=new Button();
		b6.setText("Circles");
		
		b7=new Button();
		b7.setText("CirclesLines");
		
		b8=new Button();
		b8.setText("CirclesLines2");
		
		hb1.setPadding(new Insets(5,5,5,5));
		hb1.setSpacing(20);
		hb1.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8);
	}

}
