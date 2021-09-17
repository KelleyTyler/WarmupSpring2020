package pack00;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class TileBoard {
	BoardTiles bTiles[][];
	int maxX,maxY;
	ArrayList<Point2D> points,points2,points3,points4;
	Point2D point3;
	/*CIRCLE STUFF*/
	double bubble;
	int coordX,coordY,dimens;
	
	int countInc;
	
	Point2D circCenter;
	/**/
	/*----------------*/
	/*=-CIRCLE-STUFF-=*/
	/*----------------*/
	/*----------------*/
	//maxX,maxY are the size of the board;
	TileBoard(int mX,int mY) throws Exception{
		bTiles=new BoardTiles[mX][mY];
		maxX=mX;
		maxY=mY;

		gridSet();
		points=new ArrayList<Point2D>();
		points.add(new Point2D(0,0));
		points2=new ArrayList<Point2D>();
		points4=new ArrayList<Point2D>();
		
		countInc=0;
		/*CIRCLE STUFF------------CIRCLE STUFF*/
		bubble=0.0;
		coordX=(int)(Math.sin(bubble)*100)+((mY/2)*dimens);
		coordY=(int)(Math.cos(bubble)*100)+((mY/2)*dimens);
		dimens=((int)bTiles[0][0].sImg.getHeight()+2);
		points3=new ArrayList<Point2D>();
		if(mX%2!=0)
		{
			coordX=(int)(Math.sin(bubble)*100)+(mY/2);
			coordY=(int)(Math.cos(bubble)*100)+(mX/2);
			point3=new Point2D(((mX/2)+1)*dimens,((mY/2)+1)*dimens);
		}
		else
		{

			point3=new Point2D((mX/2)*dimens,(mY/2)*dimens);
//			point3=new Point2D(coordX,coordY);
		}
		points3.add(new Point2D(coordX,coordY));
		/*CIRCLE STUFF--------------CIRCLE STUFF*/
		/*----------CircleStuff2---------------*/

		/*-------------------------------------*/
	}
	void gridSet() throws Exception
	{
		for(int i=0;i<maxX;i++)
		{
			for(int j=0;j<maxY;j++)
			{
				if(i<(maxX/2)) {
					if(j<(maxY/2)) {
						bTiles[i][j]=new BoardTiles(i,j,2);
					}

					else {
						bTiles[i][j]=new BoardTiles(i,j,0);
					}
				}
				else
				{
					if(j<(maxY/2)) {
						bTiles[i][j]=new BoardTiles(i,j,3);
					}
					else {
						bTiles[i][j]=new BoardTiles(i,j,4);
					}
				}
				
			}
		}
	}
	void changeColor(double pX, double pY){
		points.add(new Point2D(pX,pY));
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(bTiles[i][j].rect2d.contains(points.get(points.size()-1))){
					bTiles[i][j].index=1;
					System.out.println("index at "+i+" , "+j+" is "+ bTiles[i][j].index);
					points2.add(new Point2D(i,j));
				}
			}
		}
		if(points2.size()>1){
			System.out.println("Bresenham ACTIVATED");
			for(int xx=0;xx<points2.size()-1;xx++){
				BresenhamTest.bresenham((int)points2.get(0+xx).getX(),(int)points2.get(0+xx).getY(),(int)points2.get(1+xx).getX(),(int)points2.get(1+xx).getY(),bTiles);
			}
		}
	}
	
	void changeColor2(Point2D nPoint){
		points.add(nPoint);
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(bTiles[i][j].rect2d.contains(nPoint)){
					bTiles[i][j].index=1;
					points2.add(new Point2D(i,j));
				}
			}
		}
		if(points2.size()>1){
			for(int xx=0;xx<points2.size()-1;xx++){
				BresenhamTest.bresenham((int)points2.get(0+xx).getX(),(int)points2.get(0+xx).getY(),(int)points2.get(1+xx).getX(),(int)points2.get(1+xx).getY(),bTiles);
			}
		}
	}
	
	void containsPoint(Point2D p2d)
	{
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(bTiles[i][j].rect2d.contains(p2d)){
					
				}
			}
		}
	}
	
	
	//void bresenham(int x0,int y0, int x1, int y1) NOTE: We MIGHT NEED TO COPY THIS BACK LATER;
	
	void clearBresham()
	{
		points2.clear();
		points.clear();
		countInc=0;
		for(int i=0;i<maxX;i++)
		{
			for(int j=0;j<maxY;j++)
			{
				if(i<(maxX/2)) {
					if(j<(maxY/2)) {
						bTiles[i][j].index=2;
					}

					else {
						bTiles[i][j].index=0;
					}
				}
				else
				{
					if(j<(maxY/2)) {
						bTiles[i][j].index=3;
					}
					else {
						bTiles[i][j].index=4;
					}
				}
			}
		}
	}
	
	void circleThing(Point2D cen1,GraphicsContext graphcon,int spacer,int size,int res)
	{
		points3=CircleThing.PointsCircleArList(cen1,1,size*spacer,res);
		
		for(Point2D b:points3)
		{
			graphcon.strokeLine(cen1.getX(),cen1.getY(),b.getX(),b.getY());
		}
		for(int i=0;i<points3.size()-1;i++)
		{
			graphcon.strokeLine(points3.get(i).getX(),points3.get(i).getY(),points3.get(i+1).getX(),points3.get(i+1).getY());
		}
		
		

	}
	
	void circleThing2(Point2D cen1,int spacer,int size,int res)
	{
		points4=CircleThing.PointsCircleArList(cen1,size,res);

		System.out.println("CENTER IS AT:"+cen1.getX()+" , "+cen1.getY());
		int cenX=0;
		int cenY=0;
		//int adX;
		//int adY;
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(bTiles[i][j].rect2d.contains(cen1)){
					cenX=i;
					cenY=j;
				}
			}
		}
		for(int k=0;k<points4.size();k++)
		{
			for(int i=0;i<maxX;i++){
				for(int j=0;j<maxY;j++){
					if(bTiles[i][j].rect2d.contains(points4.get(k))){
						BresenhamTest.bresenham(cenX,cenY,i,j,bTiles);
					}
				}
			}
		}
//		System.out.println("countIncrementer:"+countInc+" Point "+points4.get(countInc).getX()+","+points4.get(countInc).getY());
//		for(int i=0;i<maxX;i++){
//			
//			for(int j=0;j<maxY;j++){
//				
//		
//				if(bTiles[i][j].rect2d.contains(points4.get(countInc))){
//					System.out.println("i: "+i+ " j: "+j);
//					System.out.println("GOOD----");
//					System.out.println("size is: "+ points4.size()+" countInc: "+countInc);
//					BresenhamTest.bresenham(cenX,cenY,i,j,bTiles);
//
//				}
////				else
////					System.out.println("BAD");
//			}
//			
//		}
		if(countInc+1<points4.size())
		{
			countInc++;
		}
		
		for(Point2D a:points4)
		{
			changeColor2(a);
		}

		
	}
}

