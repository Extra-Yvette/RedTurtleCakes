package yvette.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 遊戲畫面正中間隨機變換的粿
 * 
 * @author yvette
 *
 */
public class Cake extends ClickableRole {
	// 目前粿的種類(採隨機)，紅龜粿、草仔粿、壽桃
	private CakeType mType;

	/**
	 * 指定目前要變成哪種粿type
	 * 
	 * @param type
	 */
	public void setCakeType(CakeType type) {
		mType = type;
	}

	/**
	 * 取得目前粿的type
	 * 
	 * @return
	 */
	public CakeType getCakeType() {
		return mType;
	}

	// class GameCanvas通知粿繪圖，此處只要把Cake想顯示的樣子畫到Graphics canvas上即可
	@Override
	public void onDraw(Graphics canvas) {
		if (getImage() != null) {
			// 將粿的圖片畫到畫面上
			canvas.drawImage(getImage(), getX(), getY(), null);
		} else {
			debugDraw(canvas);
		}
	}

	// 除錯時使用，只會畫色塊XDDD
	private void debugDraw(Graphics canvas) {
		String word = null;
		Color color = null;

		switch (mType) {
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
