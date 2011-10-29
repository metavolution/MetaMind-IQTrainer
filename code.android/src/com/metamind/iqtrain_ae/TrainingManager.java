package com.metamind.iqtrain_ae;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;

import com.metamind.iqtrain_ae.trainings.morphmatrix.MorphMatrix;
import com.rainlib.GfxWrapper;

public class TrainingManager implements Runnable {

	enum State {STOPPED,TRAINING_SELECT,TRAINING_SESSION} 
	public State state;
	
	GfxWrapper rgfx = new GfxWrapper();
	IQtrainActivity activity = null;
	Thread thread;

	
	public MorphMatrix morphMatrix = new MorphMatrix();
	
	
	public TrainingManager() {
		thread = new Thread(this);
		thread.start();
		morphMatrix.round = 1;
		state = State.STOPPED;
	}
	
	public void showTrainingSelectGUI() {
		if(activity != null) {
			activity.showTrainingSelectGUI();
		}
 
	}
	
	public void doTraining() {
		

		

			morphMatrix.setGfxInfo(rgfx);
			morphMatrix.doRound();
		
	}
	
	
	public void doDraw() {
		

		if (state == State.TRAINING_SESSION) {
			doTraining();
		}
	
		
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				
				Thread.sleep(100);
				
				if (state == State.TRAINING_SELECT) {
					showTrainingSelectGUI();
				}
			
				
				
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	
	public void setGfxInfo(Canvas canvas) {
		rgfx.setGfxInfo(canvas);
	}
	
	public void setActivity(IQtrainActivity activity_p) {
		activity = activity_p;
	}
}
