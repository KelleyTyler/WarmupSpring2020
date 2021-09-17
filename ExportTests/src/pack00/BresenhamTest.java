package pack00;

public class BresenhamTest{
	
	
	static void plotLineLow(int x0,int y0, int x1,int y1,BoardTiles bTiles[][])
	{
		
		int dx= x1-x0;
		int dy= y1-y0;
//		System.out.println("plot line Low "+dy+" , "+dx);
		int yi=1;
		if(dy<0)
		{
			yi=-1;
			dy=-dy;
		}
		int D=2*dy-dx;
		int y=y0;
		for(int x=x0;x<x1;x++)
		{
			bTiles[x][y].index=1;
			if(D>0)
			{
				y=y+yi;
				D=D-2*dx;
			}
			D=D+2*dy;
		}
	}
	
	static void plotLineHigh(int x0,int y0, int x1,int y1,BoardTiles bTiles[][])
	{
		
		int dx= x1-x0;
		int dy= y1-y0;
//		System.out.println("plot line High dy"+dy+" dx"+dx+" x0,y0 "+x0+" , "+y0+"|| x1,y1 "+x1+" , "+y1);
		int xi=1;
		if(dx<0)
		{
			xi=-1;
			dx=-dx;
		}
		int D=2*dx-dy;
		int x=x0;
		
		for(int y=y0;y<y1;y++)
		{
			bTiles[x][y].index=1;
	
			if(D>0)
			{
				x=x+xi;
				D=D-2*dy;
			}
			D=D+2*dx;
		}
	}
	//----------
	
	
	
	
	static void plotLineHigh2(int x0,int y0, int x1,int y1,BoardTiles bTiles[][])
	{
		
		int dx= x1-x0;
		int dy= y1-y0;
//		System.out.println("plot line High dy"+dy+" dx"+dx+" x0,y0 "+x0+" , "+y0+"|| x1,y1 "+x1+" , "+y1);
		int xi=1;
		if(dx<0)
		{
			xi=-1;
			dx=-dx;
		}
		int D=2*dx-dy;
		int x=x0;
		
		for(int y=y0;y<y1;y++)
		{
			switch(bTiles[x][y].index)
			{
			case 0:
				bTiles[x][y].index=1;
				break;
			case 1:
				bTiles[x][y].index=2;
				break;
			case 2:
				bTiles[x][y].index=3;
				break;
			case 4:
				bTiles[x][y].index=0;
				break;
			default:
				bTiles[x][y].index=1;
			}	
	
			if(D>0)
			{
				x=x+xi;
				D=D-2*dy;
			}
			D=D+2*dx;
		}
	}
	static void plotLineLow2(int x0,int y0, int x1,int y1,BoardTiles bTiles[][])
	{
		
		int dx= x1-x0;
		int dy= y1-y0;
//		System.out.println("plot line Low "+dy+" , "+dx);
		int yi=1;
		if(dy<0)
		{
			yi=-1;
			dy=-dy;
		}
		int D=2*dy-dx;
		int y=y0;
		for(int x=x0;x<x1;x++)
		{
			switch(bTiles[x][y].index)
			{
			case 0:
				bTiles[x][y].index=1;
				break;
			case 1:
				bTiles[x][y].index=2;
				break;
			case 2:
				bTiles[x][y].index=3;
				break;
			case 4:
				bTiles[x][y].index=0;
				break;
			default:
				bTiles[x][y].index=1;
			}			
			
			if(D>0)
			{
				y=y+yi;
				D=D-2*dx;
			}
			D=D+2*dy;
		}
	}
	
	
	
	
	
	
	static void bresenham(int x0,int y0, int x1, int y1,BoardTiles bTiles[][])
	{
		if(Math.abs(y1-y0)<Math.abs(x1-x0)){
			if(x0>x1){
				BresenhamTest.plotLineLow(x1, y1, x0, y0, bTiles);
			}
			else{
				BresenhamTest.plotLineLow(x0, y0, x1, y1, bTiles);
			}
		}
		else{
			if(y0>y1){
				BresenhamTest.plotLineHigh(x1, y1, x0, y0, bTiles);
			}
			else{
				BresenhamTest.plotLineHigh(x0, y0, x1, y1, bTiles);
			}
		}
	}
	static void bresenham2(int x0,int y0, int x1, int y1,BoardTiles bTiles[][])
	{
		if(Math.abs(y1-y0)<Math.abs(x1-x0)){
			if(x0>x1){
				BresenhamTest.plotLineLow2(x1, y1, x0, y0, bTiles);
			}
			else{
				BresenhamTest.plotLineLow2(x0, y0, x1, y1, bTiles);
			}
		}
		else{
			if(y0>y1){
				BresenhamTest.plotLineHigh2(x1, y1, x0, y0, bTiles);
			}
			else{
				BresenhamTest.plotLineHigh2(x0, y0, x1, y1, bTiles);
			}
		}
	}
	
	
	//-----------------------
	static void errorBased(int x0,int y0, int x1,int y1,BoardTiles bTiles[][])
	{
		int dx = Math.abs(x1-x0);
		int sx=x0<x1?1:-1;
		int dy=-Math.abs(y1-y0);
		int sy= y0<y1?1:-1;
		int err=dx+dy;
		boolean treu=true;;
		while(treu)
		{
			if(x0==x1&&y0==y1) break;
			int e2=2*err;
			if(e2>=dy)
			{
				err+=dy;
				x0+=sx;
			}
			if(e2<=dx)
			{
				err+=dx;
				y0+=sy;
			}
		}
	}
	//---------------------------------------------------
	
	
	
}
