package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 場景上的角色
 * 
 */
public abstract class Role {
	// 顯示出來的範圍
	private int mX;
	private int mY;
	private int mW;
	private int mH;
	private Color mColor;
	private boolean mIsAlve;

	public Role() {
		mIsAlve = true;
	}

	/**
	 * 判斷其他物件是否碰撞到目前物件
	 * 
	 * @param obj
	 * @return
	 */
	public boolean hitTest(Role obj) {
		int hx = obj.mX;
		int hy = obj.mY;
		int hh = obj.mH;
		int hw = obj.mW;

		return ((hx + hw > mX) && (hx < mX + mW) && (hy + hh > mY) && (hy < mY
				+ mH));
	}

	public int getX() {
		return mX;
	}

	public int getY() {
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

	public void setIsAlive(boolean isAlive) {
		mIsAlve = isAlive;
	}

	public Color getColor() {
		return mColor;
	}

	public boolean isALive() {
		return mIsAlve;
	}

	public void onDraw(Graphics canvas) {

	}
}