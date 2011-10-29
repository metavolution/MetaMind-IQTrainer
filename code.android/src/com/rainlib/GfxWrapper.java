package com.rainlib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.widget.CheckBox;

public class GfxWrapper {

	private Paint paint = new Paint();
	private Canvas canvas;
	private int color;
	
	private int midx;
	private int midy;
	
	private int textsize = 10;
	
	public void setGfxInfo(Canvas canvas_p) {
		setCanvas(canvas_p);
		paint.setAntiAlias(true);
		
		setMidx(getWidth()/2);
		setMidy(getHeight()/2);
	}
	
	public void setColor(int color_p) {
		color = color_p;
		paint.setColor(color);
	}
	
	public void setFontColor(int color_p) {
		
	}
	
	public void setTextSize(int size_p) {
		textsize = size_p;
		paint.setTextSize(textsize);
	}
	
	public void drawRect(int xpos, int ypos, int width, int height) {
		Rect rect = new Rect(xpos,ypos,xpos+width,ypos+height);
		getCanvas().drawRect(rect, paint);
	}

	public void drawText(int x, int y, String text) {
		paint.setTextAlign(Align.LEFT);
		getCanvas().drawText(text, x, y, paint);
	}
	
	public void drawCenterText(int ypos, String text) {
		paint.setTextAlign(Align.CENTER);
		getCanvas().drawText(text, getMidx(), ypos, paint);
		
	}
	
	
	
	
	
	public int getWidth() {
		return getCanvas().getWidth();
	}
	
	public int getHeight() {
		return getCanvas().getHeight();
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidy(int midy) {
		this.midy = midy;
	}

	public int getMidy() {
		return midy;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
