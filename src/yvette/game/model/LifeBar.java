package yvette.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import yvette.game.Config;
import yvette.game.control.RedTurtleCakes;

public class LifeBar extends Role{
	private int mCount;//生命
	

	public LifeBar() {
	}

	/**
	 * 取得總分
	 */
	public int getCount() {
		return mCount;
	}

	/**
	 * 設定目前生命個數
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		mCount = count;
	}

	/**
	 * 增加總分，參數生命個數可為負數
	 * 
	 * @param count
	 */
	public void addCount(int count) {
		mCount += count;
	}
	
	public void onDraw(Graphics canvas) {
		Font currentFont = canvas.getFont();
		Config config = RedTurtleCakes.getInstance().getConfig();
		Font newFont = currentFont.deriveFont(Font.BOLD, 40);
		
		//設定新的字型大小
		canvas.setFont(newFont);
		
		canvas.setColor(getColor());
		
		//將玩家剩餘的愛心生命個數繪到畫面上
		int lifeCount = mCount;
		int padding = 50;
		
		for(int i = 0; i < lifeCount; i++){
			canvas.drawString("❤", (config.getScreenWidth() / 2 - 100 ) + (i * padding), 35);
		}
		
		//還原字型大小
		canvas.setFont(currentFont);
	}
}