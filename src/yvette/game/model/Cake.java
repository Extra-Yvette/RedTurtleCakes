package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;

public class Cake extends Role {
	private CakeType mType;
	
	public void setCakeType(CakeType type) {
		mType = type;
	}
	
	public CakeType getCakeType() {
		return mType;
	}
	
	public void onDraw(Graphics canvas) {
		Color color = null;
		
		switch(mType) {
		case RED:
			color = Color.ORANGE;
			break;
		case GREEN:
			color = Color.GREEN;
			break;
		case WHITE:
			color = Color.WHITE;
			break;
		}
		canvas.setColor(color);
		canvas.fillRect(getX(), getY(), getW(), getH());
	}
}
