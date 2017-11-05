package yvette.game.model;

import java.awt.Graphics;

public class MouseDragTool extends ClickableRole{
	
	
	public void onDraw(Graphics canvas) {
		if(getColor() != null) {
			canvas.setColor(getColor());
			canvas.fillArc(getX(), getY(), getW(), getH(), 0, 360);
		}
	}
}
