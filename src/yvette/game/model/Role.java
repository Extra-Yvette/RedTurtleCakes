package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 場景上的角色
 * 
 */
public class Role {
	// 顯示出來的範圍
	private int mX;
	private int mY;
	private int mW;
	private int mH;
	private Color mColor;
	private boolean mIsAlve;
	private boolean mEnableCenter;
	private Image mImage;

	public Role() {
		mIsAlve = true;
		mEnableCenter = false;
	}

	public void setEnableCenter(boolean enable) {
		mEnableCenter = enable;
	}

	/**
	 * 判斷其他物件是否碰撞到目前物件
	 * 
	 * @param obj
	 * @return
	 */
	public boolean hitTest(Role obj) {
		int hx = obj.getX();
		int hy = obj.getY();
		int hh = obj.mH;
		int hw = obj.mW;

		return ((hx + hw > getX()) && (hx < getX() + mW) && (hy + hh > getY()) && (hy < getY()
				+ mH));
	}

	public int getX() {
		if(mEnableCenter) {
			return mX - (mW / 2);
		}
		return mX;
	}

	public int getY() {
		if(mEnableCenter) {
			return mY - (mH / 2);
		}
		return mY;
	}

	public int getH() {
		return mH;
	}

	public int getW() {
		return mW;
	}

	public void setX(int x) {
		mX = x;
	}

	public void setY(int y) {
		mY = y;
	}

	public void setW(int w) {
		mW = w;
	}

	public void setH(int h) {
		mH = h;
	}

	public void setColor(Color color) {
		mColor = color;
	}

	public Color getColor() {
		return mColor;
	}
	
	public void setImage(Image image) {
		mImage = image;
	}
	
	public Image getImage() {
		return mImage;
	}

	public void setIsAlive(boolean isAlive) {
		mIsAlve = isAlive;
	}

	public boolean isALive() {
		return mIsAlve;
	}

	public void onDraw(Graphics canvas) {

	}
}