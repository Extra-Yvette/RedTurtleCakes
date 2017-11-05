package yvette.game.model;

import java.awt.Graphics;

public class MouseDragTool extends ClickableRole{
	private CakeType mType;
	
	public void setCakeType(CakeType type) {
		mType = type;
	}
	
	public CakeType getCakeType() {
		return mType;
	}
	
	public void onDraw(Graphics canvas) {
		if(getColor() != null) {
			canvas.setColor(getColor());
			canvas.fillArc(getX(), getY(), getW(), getH(), 0, 360);
		}
	}
}
