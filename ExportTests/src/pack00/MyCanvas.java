package pack00;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MyCanvas {
	Canvas cnvs1,cnvs2;
	GraphicsContext graphCon1a,graphCon1b,graphCon1c,graphCon2a;
	HBox hbox1;
	int gridSpace;
	int gridSize;
	TileBoard tboard;
	Point2D center1;
	EventHandler<ActionEvent> event1;
	EventHandler<KeyEvent> key1,key2;
	EventHandler<MouseEvent> mouse1,mouse2;
	CamThing cThing;
	int spacer1,spacer2,sGrid;
	boolean canDrag;
	MyCanvas() throws Exception
	{
		eventInit();
		
		spacer1=18;
		spacer2=34;
		gridSize=35;
		sGrid=11;
		cThing=new CamThing(1,1,sGrid,16,2,gridSize);
		hbox1=new HBox();
		hbox1.setSpacing(20);
		hbox1.setPadding(new Insets(5,5,5,5));
		
		if(gridSize%2==0)
		{
			center1=new Point2D((gridSize*spacer1)/2.0,(gridSize*spacer1)/2.0);
		}
		else
		{
			center1=new Point2D(((gridSize*spacer1)/2.0),((gridSize*spacer1)/2.0));
		}
		
		System.out.println("cnvs1 is "+(gridSize*spacer1)+" X "+(gridSize*spacer1));
		cnvs1=new Canvas(gridSize*spacer1,gridSize*spacer1);
		cnvs2=new Canvas(sGrid*spacer2,sGrid*spacer2);
		hbox1.getChildren().add(cnvs1);
		hbox1.getChildren().add(cnvs2);
		//cnvs1.setOnKeyReleased(key1);
		graphCon1a=cnvs1.getGraphicsContext2D();
		graphCon1b=cnvs1.getGraphicsContext2D();
		graphCon1c=cnvs1.getGraphicsContext2D();
		graphCon2a=cnvs2.getGraphicsContext2D();
		tboard=new TileBoard(gridSize,gridSize);
		gridSpace=1+16;
		cnvs1.setOnMouseClicked(mouse1);
		cnvs1.setOnMouseReleased(mouse2);
		cnvs1.setOnMousePressed(mouse2);
		//
		canDrag=false;
	}
	
	void drawGrid()
	{
		graphCon1b.clearRect(0, 0,gridSize*spacer1,gridSize*spacer1);
		graphCon1a.setFill(Color.AQUA);
		graphCon1a.setStroke(Color.BLACK);
		graphCon1a.fillRect(0, 0,gridSize*spacer1,gridSize*spacer1);
		graphCon1a.strokeRect(0, 0,gridSize*spacer1,gridSize*spacer1);
		
		for(int i=0;i<gridSize;i++)
		{
			for(int j=0;j<gridSize;j++)
			{
				graphCon1a.drawImage(tboard.bTiles[i][j].getImage(), (i*spacer1)+1,(j*spacer1)+1);
			}
		}
	}
	
	void drawSmallGrid()
	{
		
		graphCon2a.clearRect(0, 0,sGrid*spacer2,sGrid*spacer2);
		graphCon2a.setFill(Color.AQUA);
		graphCon2a.setStroke(Color.BLACK);
		graphCon2a.fillRect(0, 0,sGrid*spacer2,sGrid*spacer2);
		graphCon2a.strokeRect(0, 0,sGrid*spacer2,sGrid*spacer2);
		
		int xx=(int)cThing.coords.getX();
		int yy=(int)cThing.coords.getY();
		
		
		
		for(int i=xx;i<xx+sGrid;i++)
		{
			for(int j =yy;j<yy+sGrid;j++)
			{
				graphCon2a.drawImage(tboard.bTiles[i][j].getBigImage(), ((i-xx)*spacer2)+1,((j-yy)*spacer2)+1);
			}
		}
		
	}
	

	
	void moveWindow(int xMove,int yMove)
	{
		if(cThing.CanMove(xMove, yMove))
		{
			cThing.move(yMove, xMove);
		}
		refresh();
	}
	void toggleHL()
	{
		if(cThing.showHighlite)
		{
			cThing.showHighlite=false;
		}
		else
			cThing.showHighlite=true;
		refresh();
	}
	
	void refresh()
	{
		drawGrid();
		drawSmallGrid();
		cThing.highliteArea(graphCon1b);
	}
	void showLines()
	{
		refresh();
		if(tboard.points.size()>1)
		{
			System.out.println("hey look line");
			for(int i=0;i<tboard.points.size()-1;i++){
//				graphCon1c.setLineWidth(1);
				graphCon1c.setStroke(Color.WHITE);
				graphCon1c.strokeLine(tboard.points.get(i).getX(),tboard.points.get(i).getY(), tboard.points.get(i+1).getX(), tboard.points.get(i+1).getY());
			}
		}
		
	}
	void toggleDrag()
	{
		if(canDrag)
		{
			System.out.println("canDrag off");
			canDrag=false;
		}
		else 
		{
			System.out.println("canDrag on");
			canDrag=true;
		}
	}
	void clearLines()
	{
		tboard.clearBresham();
		refresh();
	}
	
	void circles()
	{
		tboard.clearBresham();
		graphCon1c.setStroke(Color.BLACK);
		tboard.circleThing(center1,graphCon1c,spacer1,11,32);
	}
	
	//There's a whole bunch of shit here, weird shit..
	//basically the thing doesn't highlight properly.
	//there's a weird "pattern" that emerges when I attempt to raycast.
	void circles2()
	{
		tboard.circleThing2(center1,spacer1,11*spacer1,180);
		refresh();
	}
	
	void circles3()
	{
		
		graphCon1c.setStroke(Color.BLACK);
		double xx=0.0;
		System.out.println("Center1: "+center1.getX()+" , "+center1.getY());

		while(xx<2.25*Math.PI)
		{
			
			graphCon1c.strokeLine(center1.getX(), center1.getY(), CircleThing.getPoint(center1, xx, 50).getX(), CircleThing.getPoint(center1, xx, 50).getY());
			xx+=(Math.PI/2);
		}
		refresh();
	}
	
	
//	void highliteArea()
//	{
//		//graphCon1b.clearRect(0, 0,gridSize*18,gridSize*18);
//		graphCon1b.setStroke(Color.ORANGE);
////		graphCon1b.strokeRect(cThing.vRect.getMinX()*spacer1, cThing.vRect.getMinY()*spacer1, cThing.vRect.getWidth()*spacer1,cThing.vRect.getHeight()*spacer1);
//		graphCon1b.strokeRect(cThing.vRect.getMinX(), cThing.vRect.getMinY(), cThing.vRect.getWidth(),cThing.vRect.getHeight());
//	}
//	void getPos()
//	{
//		cThing.getPos();
//	}

	void eventInit(){
		key1 = new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode())
				{
					case A:
					case LEFT:
						moveWindow(0,-1);
						break;
					case D:
					case RIGHT:
						moveWindow(0,1);
						break;
					case W:
					case UP:
						moveWindow(-1,0);
						break;
					case S:
					case DOWN:
						moveWindow(1,0);
						break;
					default:
						//getPos();
						System.out.println("no Change");
				}
				
			}
		};
		
		mouse1 = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if(!canDrag)
				{
					 double mouseX = event.getX(); 
	                 double mouseY = event.getY();
	                 System.out.println("the coords of Click: "+mouseX+", "+mouseY);
	                 tboard.changeColor(mouseX,mouseY);
	                 refresh();
				}
			}
			
		};
		mouse2 = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if(canDrag)
				{
					 double mouseX = event.getX(); 
	                 double mouseY = event.getY();
	                 System.out.println("the coords of down/release: "+mouseX+", "+mouseY);
	                 tboard.changeColor(mouseX,mouseY);
	                 refresh();
				}
			}
		};
	}
}
