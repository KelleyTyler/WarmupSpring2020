package pack00;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/*
 * the idea behind this bit is to create a sort of constructor or factory of a sort, one that isn't going to have everything in it
 * but which will instead allow for concepts like 
*/
public class ImAssigner {
	static Image sSheet;
	public static Image assign1(String sSheetPath) throws Exception
	{
		try
		{
			sSheet= new Image("File:"+sSheetPath);
		}
		catch(Exception e)
		{
			System.out.println("error Exception: "+e);
		}
		return sSheet;
	}
	//imgs=the array of images we're assigning these images too.
	//img = the basic compound image/sprite-sheet.
	//intTotFrames->total frames per image.
	//int fXSize, fYSize, basically the frame dimensions.
	//int FrameSkip = number of pixels between separate frames.
	//int lnNum = the number of Lines; if the number of lines is '1' enter '0'
	public static void assignImg(Image[] imgs, Image img, int totFrames,int fXSize,int fYSize, int frameSkip,int lnNum)
	{	
		int frames=totFrames;
		PixelReader pxRead1;
		pxRead1=img.getPixelReader();
		PixelWriter pxWrite1;
		WritableImage wImg = new WritableImage(fXSize,fYSize);
		
		Color color;
		for(int i = 0; i < frames;i++)
		{
			wImg = new WritableImage(fYSize,fXSize);
//			wrIm[i] = new WritableImage(32,32);
//			pxWrite1 =wrIm[i].getPixelWriter();
			pxWrite1=wImg.getPixelWriter();
			int ww=(int)fXSize*i;
//			System.out.println("at Frame" + i+ " ww="+ww);
			for(int readY=((lnNum*fYSize)+frameSkip);readY<fYSize;readY++)
			{
				//System.out.println("starting at "+readY+","+ww);
				//System.out.println("at "+i+" ww = "+ww);
				for(int readX=ww;readX<(ww+fXSize);readX++)
				{
					color = pxRead1.getColor(readX, readY);
					pxWrite1.setColor(readX-ww, readY, color);
				}
				
			}
			imgs[i]=wImg;
//			wImg=null;
//			pxWrite1=null;
//			System.out.println("-------------------------------------");
		}
//		wrIm=null;
		pxRead1=null;
		pxWrite1=null;
//		System.out.println("-END IM ASSIGN------------------------------------");

	}
	//
	//think this one outputs a 2d array of images.
	
	//------------------------------------------------------------------------------------------------------------------------
	static Image[][] imgReturn(int nPerLine, int nFrames,int fXSize,int fYSize, int frameSkip,Image img)
	{
		int[] frameCaster=framesAndLines(nPerLine,nFrames);
		Image[][] imMatrix=new Image[frameCaster.length][];
		for(int i=0;i<frameCaster.length;i++)
		{
			imMatrix[i]=new Image[frameCaster[i]];
		}
		//Declarations are DONE!
		if(frameCaster.length>1)
		{
			for(int i=0;i<frameCaster.length;i++)
			{
				ImAssigner.assignImg(imMatrix[i],img,frameCaster[i], fXSize, fYSize, frameSkip,i);
			}
		}
		return imMatrix;
	}
	//This function basically outputs a 2d array designed to communicate the numbers of frames per line in a particular file
	//----------------------------------------------------------------------------------------------------------------------
	static int[] framesAndLines(int nPerLine, int tFrames)
	{
		if(tFrames<nPerLine)
		{
			int[] rTurn = new int[] {tFrames};
			return rTurn;
		}
		else
		{
			if(tFrames%nPerLine==0)
			{
				int[] rTurn = new int[tFrames/nPerLine];
				for(int i=0;i<(tFrames/nPerLine);i++)
				{
					rTurn[i]=nPerLine;
				}
				return rTurn;
			}
			else
			{
				int[] rTurn=new int[(tFrames/nPerLine)+1];
				for(int i=0;i<(tFrames/nPerLine);i++)
				{
					rTurn[i]=nPerLine;
				}
				System.out.println("testBonusLine");
				rTurn[(tFrames/nPerLine)]=(tFrames%nPerLine);
				return rTurn;
			}
		}
	}
	//--------------------------------
	
	//--------------------------------
	static double aCurve(int a, int min)
	{
//		return (double)(16-(Math.pow((((0.8*a)/(0.5*min))),2)));
		return (double)4-(Math.pow(Math.pow((a),2),0.33333333));
	}
}