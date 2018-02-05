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
	private ValueHolder mAlpha = new ValueHolder(255,-8,0);
	private ValueHolder mY = new ValueHolder(0,-1,0);
	private Font mTextFont;
	private String mText;
	
	class ValueHolder{
		int mRawValue;
		int mOffsetValue;
		int mTargetValue;
		
		ValueHolder(int raw, int offset, int target){
			mRawValue = raw;
			mOffsetValue = offset;
			mTargetValue = target;
		}
	}

	public void setText(String text) {
		mText = text;
	}

	public void setTargetY(int y) {
		mY.mTargetValue = y;
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
		mAlpha.mRawValue += mAlpha.mOffsetValue;
		if (mAlpha.mRawValue < 0) {
			mAlpha.mRawValue = 0;
			setIsAlive(false);
		}
		
		mY.mRawValue = getY();
		
		int y = mY.mRawValue + mY.mOffsetValue;

		if (y > mY.mTargetValue) {
			y = mY.mTargetValue;
		}
		setY(y);

		canvas.setColor(new Color(0, 0, 255, mAlpha.mRawValue));
		canvas.drawString(mText, getX(), getY());
		canvas.setFont(currentFont);
	}
}
