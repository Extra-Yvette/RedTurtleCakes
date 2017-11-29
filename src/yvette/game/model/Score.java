package yvette.game.model;

import java.awt.Font;
import java.awt.Graphics;

/**
 * 
 * 遊戲獲得總分分數面版
 * 
 */
public class Score extends Role {
	private int mScore;

	@Override
	public void onDraw(Graphics canvas) {
		canvas.setColor(getColor());
		
		Font currentFont = canvas.getFont();
		Font newFont = currentFont.deriveFont(Font.BOLD, 22);
		
		//設定新的字型大小
		canvas.setFont(newFont);
		
		canvas.drawString("" + mScore, getX(), getY());
		
		canvas.setFont(currentFont);
	}

	/**
	 * 取得總分
	 */
	public int getScore() {
		return mScore;
	}

	/**
	 * 設定總分
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		mScore = score;
	}

	/**
	 * 增加總分，參數score可為負數
	 * 
	 * @param score
	 */
	public void addScore(int score) {
		mScore += score;
	}
}
