package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 場景上的全部角色都要繼承的class
 * 
 * @author yvette
 * 
 */
public class Role {
	// 顯示出來的範圍
	private int mX;
	private int mY;
	private int mW;
	private int mH;
	/** 圖層 */
	private int mLayerOrder;
	private Color mColor;
	private boolean mIsAlve;
	private boolean mEnableCenter;
	private Image mImage;

	public Role() {
		mIsAlve = true;
		mEnableCenter = false;
	}

	/**
	 * 設定角色自身中心點為鼠標中心點
	 * 
	 * @param enable
	 */
	public void setEnableCenter(boolean enable) {
		mEnableCenter = enable;
	}

	/**
	 * 判斷其他物件是否碰撞到目前物件
	 * 
	 * @param obj
	 */
	public boolean hitTest(Role obj) {
		int hx = obj.getX();
		int hy = obj.getY();
		int hh = obj.mH;
		int hw = obj.mW;

		return ((hx + hw > getX()) && (hx < getX() + mW) && (hy + hh > getY()) && (hy < getY() + mH));
	}

	/**
	 * 角色存在於場景上時的x軸
	 */
	public int getX() {
		if (mEnableCenter) {
			return mX - (mW / 2);
		}
		return mX;
	}

	/**
	 * 角色存在於場景上時的y軸
	 */
	public int getY() {
		if (mEnableCenter) {
			return mY - (mH / 2);
		}
		return mY;
	}

	/**
	 * 取得角色高
	 */
	public int getH() {
		return mH;
	}

	/**
	 * 取得角色寬
	 */
	public int getW() {
		return mW;
	}

	/**
	 * 設定角色存在於場景上時的x軸
	 * 
	 * @param x
	 */
	public void setX(int x) {
		if (mEnableCenter) {
			mX = x + (mW / 2);
		} else {
			mX = x;
		}
	}

	/**
	 * 設定角色存在於場景上時的y軸
	 * 
	 * @param y
	 */
	public void setY(int y) {
		if (mEnableCenter) {
			mY = y + (mH / 2);
		} else {
			mY = y;
		}
	}

	/**
	 * 設定角色寬
	 * 
	 * @param w
	 */
	public void setW(int w) {
		mW = w;
	}

	/**
	 * 設定角色高度
	 * 
	 * @param h
	 */
	public void setH(int h) {
		mH = h;
	}

	/**
	 * 設定角色(Role)在場景(Scenes)上顯示的圖層，數值越大角色在越上層
	 * 
	 * @param order
	 */
	public void setLayerOrder(int order) {
		mLayerOrder = order;
	}

	/**
	 * 取得角色(Role)在場景(Scenes)上顯示的圖層，數值越大角色在越上層
	 * 
	 * @return
	 */
	public int getLayerOrder() {
		return mLayerOrder;
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

	/**
	 * 設置角色狀態是否存活在場景上，true:存活在場景上，false:不存活在場景上，將會從場景上被移除
	 * 
	 * @param isAlive
	 */
	public void setIsAlive(boolean isAlive) {
		mIsAlve = isAlive;
	}

	/**
	 * 取得角色狀態是否存活在場景上，true:存活在場景上，false:不存活在場景上，將會從場景上被移除
	 * 
	 * @return
	 */
	public boolean isAlive() {
		return mIsAlve;
	}

	public void onDraw(Graphics canvas) {

	}
}