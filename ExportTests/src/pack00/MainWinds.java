package pack00;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainWinds extends Application{
	
	EventHandler<ActionEvent> event1,event2,event3,event4,event5;
	BPanel bpanel;
	VBox vb1;
	Label lb1;
	Scene scene1;
	StackPane sp1;
	MyCanvas myCnvs;
	
	
	@Override
	public void start(Stage myStage) throws Exception {
		// TODO Auto-generated method stub
		eventInit();
		initStuff();
		scene1= new Scene(sp1);
		myStage.setScene(scene1);
		
		myStage.show();

	}
	public static void main(String[] args)
	{
		System.out.println("HELLO DOLLY!");
		
		launch(args);
	}
	
	public void initStuff() throws Exception
	{
		bpanel=new BPanel();
		vb1=new VBox();
		vb1.setPadding(new Insets(5,5,5,5));
		
		lb1=new Label();
		lb1.setText("----A TITLE PAGE----");
		lb1.setPadding(new Insets(5,5,5,5));
		lb1.setTextAlignment(TextAlignment.CENTER);
		vb1.setSpacing(20);
		vb1.getChildren().add(lb1);
		myCnvs = new MyCanvas();
		vb1.getChildren().add(myCnvs.hbox1);
		vb1.getChildren().add(bpanel.hb1);
		bpanel.b1.setOnAction(event1);
		bpanel.b2.setText("I/0-Highlite");
		bpanel.b2.setOnAction(event2);
		bpanel.b3.setText("I/0-Lines");
		bpanel.b3.setOnAction(event2);
		bpanel.b4.setText("CLEAR");
		bpanel.b4.setOnAction(event2);
		bpanel.b5.setOnAction(event2);
		bpanel.b6.setOnAction(event2);
		bpanel.b7.setOnAction(event2);
		bpanel.b8.setOnAction(event2);
		//bpanel.b3.setOnKeyPressed(myCnvs.key1);
		vb1.setOnKeyPressed(myCnvs.key1);
		sp1=new StackPane();
		sp1.getChildren().add(vb1);
	}
	
	void eventInit(){
		event1 =new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				myCnvs.drawGrid();
				myCnvs.drawSmallGrid();
			}
	
		};
		event2 =new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				if(arg0.getSource().equals(bpanel.b2))
				{
					myCnvs.toggleHL();
				}
				else if(arg0.getSource().equals(bpanel.b3))
				{
					myCnvs.showLines();
				}
				else if(arg0.getSource().equals(bpanel.b4))
				{
					myCnvs.clearLines();
				}
				else if(arg0.getSource().equals(bpanel.b5))
				{
						myCnvs.toggleDrag();
				}
				else if(arg0.getSource().equals(bpanel.b6))
				{
						myCnvs.circles2();
				}
				else if(arg0.getSource().equals(bpanel.b7))
				{
						myCnvs.circles3();
				}
				else if(arg0.getSource().equals(bpanel.b8))
				{
						myCnvs.circles();
				}
			
			}
	
		};
		event3 =new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				
			}
	
		};
		event4 =new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				if(arg0.getSource().equals(bpanel.b4))
				{
					myCnvs.clearLines();
				}
				else if(arg0.getSource().equals(bpanel.b5))
				{
						myCnvs.toggleDrag();
				}
					
			}
	
		};
		
		event5 =new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			
				
			}
	
		};
		
	}

}
