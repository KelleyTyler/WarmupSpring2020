package pack00;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class CircleThing {

	public static Point2D getPoint(Point2D center, double counter, int radius)
	{
		//counter basically is the radian value for the point on the circle in question.
		double dX=Math.sin(counter);
		double dY=Math.cos(counter);
//		if(dY<0)
//		{
//			System.out.println("dY is Lower Than Zero at "+ counter);
//		}
//		if(dX<0)
//		{
//			System.out.println("dX is Lower Than Zero at "+ counter);
//		}
		Point2D myPoint = new Point2D((((dX*radius)+center.getX())),((dY*radius)+center.getY()));
		return myPoint;
	}
	public static Point2D getPoint(Point2D center, double counter, int radius,int spacer)
	{
		//counter basically is the radian value for the point on the circle in question.
		double dX=Math.sin(counter);
		double dY=Math.cos(counter);
		int radii=radius*spacer;
		double cX=center.getX()*spacer;
		double cY=center.getY()*spacer;

//		if(dY<0)
//		{
//			System.out.println("dY ("+dY+" ) is Lower Than Zero at "+ counter);
//		}
//		if(dX<0)
//		{
//			System.out.println("dX ( "+dX+" ) is Lower Than Zero at "+ counter);
//		}
		if((((dX*radii)+cX))<0||((dY*radii)+cY)<0)
		{
			System.out.println("COORD ALERT!: "+(((dX*radii)+cX))+" , "+((dY*radii)+cY));
		}
		
//		Point2D myPoint = new Point2D((((dX*radius)+center.getX())),((dY*radius)+center.getY()));
		Point2D myPoint = new Point2D((((dX*radii)+cX)),((dY*radii)+cY));
		return myPoint;
	}
	
	//this is designed to produce an array of points along the circumference of the circle.
	public static Point2D[] PointsCircle(Point2D center, int radius)
	{
		ArrayList<Point2D> pArray=new ArrayList<Point2D>();
		double counter=0.0;
		while(counter<(2*Math.PI))
		{
			pArray.add(getPoint(center, counter, radius));
		}
		System.out.println("BEGIN TRANSFER SEQUENCE----");
		Point2D[] myPoints=new Point2D[pArray.size()];
		for(int i=0;i<pArray.size();i++)
		{
			myPoints[i]=pArray.get(i);
		}
		return myPoints;
	}
	
	
	public static ArrayList<Point2D> PointsCircleArList(Point2D center, int radius,int countMax)
	{
		ArrayList<Point2D> pArray=new ArrayList<Point2D>();
		double counter=0.0;
		while(counter<(2*Math.PI))
		{
			pArray.add(getPoint(center, counter, radius));
			counter+=(Math.PI/countMax);
		}
		System.out.println("BEGIN TRANSFER SEQUENCE(ArrayList)");
		return pArray;
	}
	public static ArrayList<Point2D> PointsCircleArList(Point2D center, int spacer,int radius,int countMax)
	{
		ArrayList<Point2D> pArray=new ArrayList<Point2D>();
		double counter=0.0;
		while(counter<(2.25*Math.PI))
		{
			//System.out.println("newPoint: "+getPoint(center, counter, radius,spacer).getX()+" , "+getPoint(center, counter, radius,spacer).getY());
			pArray.add(getPoint(center, counter, radius,spacer));
			counter+=(Math.PI/countMax);
		}
		System.out.println("BEGIN TRANSFER SEQUENCE(ArrayList)");
		return pArray;
	}
}
