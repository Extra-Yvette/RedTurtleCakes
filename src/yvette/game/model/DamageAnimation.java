package yvette.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 傷害值(有動畫效果)
 * 
 * @author yvette
 *
 */
public class DamageAnimation extends Role {
	private int mColorAlpha = 255;
	private int mOffsetValueAlpha = -8;
	private int mOffsetValueY = -1;
	private Font mTextFont;
	private String mText;
	private int mTargetY;

	public void setText(String text) {
		mText = text;
	}

	public void setTargetY(int y) {
		mTargetY = y;
	}

	@Override
	public void onDraw(Graphics canvas) {
		if (mText == null || mText.length() == 0) {
			return;
		}

		canvas.setColor(getColor());

		Font currentFont = canvas.getFont();

		// 設定首頁紅龜粿字型大小
		if (mTextFont == null) {
			mTextFont = currentFont.deriveFont(Font.CENTER_BASELINE, 16);
		}
		canvas.setFont(mTextFont);

		// 讓文字淡出效果
		mColorAlpha += mOffsetValueAlpha;
		if (mColorAlpha < 0) {
			mColorAlpha = 0;
			setIsAlive(false);
		}
		int y = getY() + mOffsetValueY;

		if (y > mTargetY) {
			y = mTargetY;
		}
		setY(y);

		canvas.setColor(new Color(0, 0, 255, mColorAlpha));
		canvas.drawString(mText, getX(), getY());
		canvas.setFont(currentFont);
	}
}
