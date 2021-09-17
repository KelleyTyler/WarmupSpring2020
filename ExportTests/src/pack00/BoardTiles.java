package pack00;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class BoardTiles {
	Image imgs[];
	Image bImgs[];
	Image img1,bImg,sImg;
	int index;
	int dmension;
	int x,y;
	Rectangle2D rect2d;
	
	BoardTiles() throws Exception{
		index=0;
		initImgs();
		x=0;
		y=0;
		rect2d=new Rectangle2D(x*((int)sImg.getHeight()+2),y*((int)sImg.getHeight()+2),imgs[0].getWidth(),imgs[0].getHeight());
	}
	BoardTiles(int xx,int yy,int ind) throws Exception{
		index=ind;
		initImgs();
		x=xx;
		y=yy;
		rect2d=new Rectangle2D(x*((int)sImg.getHeight()+2),y*((int)sImg.getHeight()+2),(int)sImg.getHeight(),(int)sImg.getHeight());
	}
	void initImgs(){
		
		imgs=new Image[5];
		bImgs=new Image[5];
		try
		{
			sImg=new Image("File:assets/16x16TileStrip.png");
			bImg=new Image("File:assets/32x32TileStrip.png");

//			imgs[0]=new Image("File:assets/16x16tile_W.png");
//			imgs[1]=new Image("File:assets/16x16tile_C.png");
//			imgs[2]=new Image("File:assets/16x16tile_R.png");
//			imgs[3]=new Image("File:assets/16x16tile_Y.png");
//			imgs[4]=new Image("File:assets/16x16tile_B.png");
			ImAssigner.assignImg(imgs, sImg, 5, (int)sImg.getHeight(), (int)sImg.getHeight(), 0, 0);
			//---------------------------------------------- 
//			bImgs[0]=new Image("File:assets/32x32White.png");
//			bImgs[1]=new Image("File:assets/32x32Black.png");
//			bImgs[2]=new Image("File:assets/32x32Red.png");
//			bImgs[3]=new Image("File:assets/32x32Yellow.png");
//			bImgs[4]=new Image("File:assets/32x32Blue.png");
			ImAssigner.assignImg(bImgs, bImg, 5, (int)bImg.getHeight(), (int)bImg.getHeight(), 0, 0);
			//img1=new Image("File:assets/16x16tile_R.png");
			img1=imgs[index];
			dmension=16;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
	public Image getImage(){
		
		return imgs[index];
	}
public Image getBigImage(){
		
		return bImgs[index];
	}
}
