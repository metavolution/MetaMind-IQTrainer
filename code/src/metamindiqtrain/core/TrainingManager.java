package metamindiqtrain.core;

import metamindiqtrain.trainings.morphmatrix.MorphMatrix;
import rainlib.GfxWrapper;



public class TrainingManager implements Runnable {

	enum State {STOPPED,TRAINING_SELECT,TRAINING_SESSION} 
	public State state;
	
	GfxWrapper rgfx = new GfxWrapper();
	Thread thread;

	
	public MorphMatrix morphMatrix = new MorphMatrix();
	
	
	public TrainingManager() {
		thread = new Thread(this);
		thread.start();
		morphMatrix.round = 1;
		state = State.STOPPED;
	}
	
	
	public void doTraining() {
		

		

			morphMatrix.setGfxInfo(rgfx);
			morphMatrix.doRound();
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
