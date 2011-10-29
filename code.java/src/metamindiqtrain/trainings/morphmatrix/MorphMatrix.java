package metamindiqtrain.trainings.morphmatrix;




import java.awt.Color;
import java.util.Random;

import metamindiqtrain.core.TrainingSession;

import rainlib.Debug;
import rainlib.GfxWrapper;





public class MorphMatrix extends TrainingSession {

	Color color1 = new Color(255,200,120,0); 
	Color color2 = new Color(255,20,20,20);

	int delay = 4000;
	
	
	public MorphMatrix() {
		trainingName = "MorphMatrix";
		title = "Morph and compare the Matrix in your Mind";
		info = "mentally rotate left pattern and compare with right image";
	}
	
	public void doRound() {
		Debug.print("MorphMatrix draw");
        paintSessionInfo();
		
		Random rand = new Random();
		Random rand2 = new Random();
		int csize = 4;
		
		int width = csize*RectCluster.shapex+1;
		int height = csize*RectCluster.shapey+1;
		int yPos = rgfx.getMidy()-height/2;  
       
		RectCluster matrix1 = new RectCluster(rgfx.getMidx()-(int)(width)-RectCluster.shapex,yPos,csize,color1,color2);
		RectCluster matrix2 = new RectCluster(rgfx.getMidx()+RectCluster.shapex,yPos,csize,color1,color2);                

		matrix1.generateMatrix();
		matrix2.morphMatrix(matrix1.getArray(), rand2.nextInt(3)+1);  

		if(rand.nextBoolean())
			matrix2.mutateMatrix();

		matrix1.paintCluster(rgfx);
		matrix2.paintCluster(rgfx);
		
		
		paintUserAnswer();
		paintCorrectAnswer();
		
		round++;
		if (delay >= 1000)
			delay-=100;
		if (delay < 1000 && delay > 100) {
			delay-=10;
		}
		
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
	

	

	public void setGfxInfo(GfxWrapper rgfx_p) {
		rgfx = rgfx_p;
	}
	
}
