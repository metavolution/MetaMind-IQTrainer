package com.metamind.iqtrain_ae;

import android.graphics.Canvas;
import android.graphics.Color;

import com.rainlib.Debug;
import com.rainlib.GfxWrapper;

public class TrainingSession {
	
	protected GfxWrapper rgfx;

	protected int colorText1 = Color.argb(255,250,250,250);
	protected int colorCorrect = Color.argb(255,0,250,10);
	
	protected String title = "title";
	protected String info = "info";
	protected String trainingName = "";
	protected int rounds = 1;
	protected int complexity = 1;
	protected int level = 1;
	protected int round = 1;
	protected String correctStr = "";
	protected String userStr = "";
	
	public void doRound() {
		this.doRound();
	}
	
	public void doTrainingSession() {
		Debug.print("training start");
	}
	
	public void paintSessionInfo() {
		Debug.print("paintinfo:"+title);
		
		//background
		rgfx.setColor(Color.argb(255,0,0,0));
		rgfx.drawRect(0, 0, rgfx.getWidth(), rgfx.getHeight());

		//traininginfo
		rgfx.setColor(colorText1);	
		rgfx.setTextSize(20);
		rgfx.drawCenterText(20, "MetaMindIQtrain");
		rgfx.setTextSize(15);
		rgfx.drawCenterText(38, title);
		rgfx.setTextSize(11);
		rgfx.drawCenterText(53, info);
		
		//sessioninfo
		rgfx.drawCenterText(rgfx.getHeight()-16, "level: "+level+"         round: "+round);
	}
	
	public void paintUserAnswer() {
		rgfx.setColor(colorText1);	
		rgfx.setTextSize(15);
		rgfx.drawCenterText(rgfx.getHeight(), "ROTATED");
	}
	
	public void paintCorrectAnswer() {
		rgfx.setColor(colorCorrect);	
		rgfx.setTextSize(28);
		rgfx.drawCenterText(rgfx.getHeight()-30, "MIRRORED");
	}
	
	public void paintUserControls() {

	}
	
	public void setGfxInfo(Canvas canvas_p) {
//		rgfx = new GfxWrapper();
		rgfx.setGfxInfo(canvas_p);

	}

}
