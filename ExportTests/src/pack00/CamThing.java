package pack00;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CamThing {
	Rectangle2D vRect;
	Point2D coords;
	int gridSize, tileSpacing,tileDimensions,mSize;
	boolean showHighlite;
	int x,y;
	CamThing(int xx, int yy, int gSize, int tileD, int tSpace,int ms)
	{
		mSize=ms;
		x=xx;
		y=yy;
		gridSize= gSize;
		tileSpacing=tSpace;
		tileDimensions=tileD;
		coords=new Point2D(x,y);
		refreshRect();
		showHighlite=true;
	}
	boolean CanMove(int yMove, int xMove)
	{
		System.out.println("current pos:"+coords.getX()+" ,"+coords.getY());
		System.out.println("Move to? pos:"+(coords.getX()+xMove)+" ,"+(coords.getY()+yMove));
		return canMoveY(yMove)&&canMoveX(xMove)?true:false;
	}
	boolean canMoveY(int yMov)
	{
		int cY=(int)coords.getY();
		if((!(cY+yMov<0))&&(cY+gridSize+yMov-1<mSize))
		{
			return true;
		}
		else
			return false;
	}
	int getDistTiles()
	{
		return tileDimensions+tileSpacing; 
	}
	void refreshRect()
	{
		int b=getDistTiles();
		vRect = new Rectangle2D(coords.getX()*b,coords.getY()*b,gridSize*b,gridSize*b);
	}
	boolean canMoveX(int xMov)
	{
		int cX=(int)coords.getX();
		return ((!(cX+xMov<0))&&(cX+gridSize+xMov-1<mSize))?true:false;
	}
	void highliteArea(GraphicsContext graphCon)
	{
		if(showHighlite)
		{
			graphCon.setStroke(Color.ORANGE);
			graphCon.strokeRect(vRect.getMinX(), vRect.getMinY(), vRect.getWidth(),vRect.getHeight());
		}
	}
	
	void move(int xMove,int yMove)
	{
		//coords.add(xMove,yMove);
//		System.out.println("MOVING------------------>");
		System.out.println("current pos:"+coords.getX()+" ,"+coords.getY());
		coords=new Point2D((coords.getX()+xMove),(coords.getY()+yMove));
//		System.out.println("MOVED------------------->");
		System.out.println("current pos:"+coords.getX()+" ,"+coords.getY());
//		System.out.println("MOVED-------------------------------------------");
		refreshRect();
	}
	void getPos()
	{
		System.out.println("current pos:"+coords.getX()+" ,"+coords.getY());
	}
}
