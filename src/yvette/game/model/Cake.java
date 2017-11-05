package yvette.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cake extends ClickableRole {
	private CakeType mType;
	
	public void setCakeType(CakeType type) {
		mType = type;
	}
	
	public CakeType getCakeType() {
		return mType;
	}
	
	public void onDraw(Graphics canvas) {
		String word = null;
		Color color = null;
		
		switch(mType) {
		case RED:
			word = "龜";
			color = Color.RED;
			break;
		case GREEN:
			word = "草";
			color = Color.GREEN;
			break;
		case WHITE:
			word = "桃";
			color = Color.WHITE;
			break;
		}
		
		Font currentFont = canvas.getFont();
		Font newFont = currentFont.deriveFont(Font.BOLD, 36);
		canvas.setFont(newFont);
		
		canvas.setColor(color);
		canvas.drawString(word, getX(), getY());
		canvas.setFont(currentFont);
	}
}
