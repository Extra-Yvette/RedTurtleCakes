package yvette.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import yvette.game.Config;
import yvette.game.control.RedTurtleCakes;

/**
 * 
 * 遊戲結束畫面上的字串
 * 
 * @author yvette
 *
 */
public class GameOverTitle extends ClickableRole {
	private int mColorAlpha = 0;
	private int mAlphaFlag = 8;
	private Font mTitleFont;
	private Font mPressStartFont;

	@Override
	public void onDraw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.fillRect(getX(), getY(), getW(), getH());
		Font currentFont = canvas.getFont();
		Config config = RedTurtleCakes.getInstance().getConfig();

		// 設定首頁紅龜粿字型大小
		if (mTitleFont == null) {
			mTitleFont = currentFont.deriveFont(Font.ITALIC, 54);
		}
		canvas.setFont(mTitleFont);

		canvas.setColor(Color.RED);
		canvas.drawString("遊戲結束囉QQ", config.getScreenWidth() / 2 - 200, config.getScreenHeight() / 2 - 40);
		canvas.setFont(currentFont);

		// 讓文字淡入淡出效果
		mColorAlpha += mAlphaFlag;
		if (mColorAlpha > 255) {
			mColorAlpha = 255;
			mAlphaFlag = -mAlphaFlag;
		}
		if (mColorAlpha < 0) {
			mColorAlpha = 0;
			mAlphaFlag = -mAlphaFlag;
		}

		// 設定首頁"請用滑鼠點擊畫面後開始遊戲"字型大小
		if (mPressStartFont == null) {
			mPressStartFont = currentFont.deriveFont(Font.BOLD, 24);
		}
		canvas.setFont(mPressStartFont);
		canvas.setColor(new Color(0, 0, 255, mColorAlpha));
		canvas.drawString("請用滑鼠點擊畫面後重新遊戲", (config.getScreenWidth() / 2) - 180, config.getScreenHeight() / 2 + 50);
		canvas.setFont(currentFont);
	}
}
