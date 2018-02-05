package yvette.game.model;

import java.awt.Graphics;

import yvette.game.Config;
import yvette.game.control.RedTurtleCakes;

public class Fly extends ClickableRole {
	// 設定蒼蠅飛的速度
	private int mSpeed;
	private Config mConfig;
	private Cake mCake;

	public Fly() {
		mConfig = RedTurtleCakes.getInstance().getConfig();
		mSpeed = mConfig.getFlySpeed();// 每單位時間移動1個像素
	}

	@Override
	public void onDraw(Graphics canvas) {
		if(mSpeed == 0){
			super.onDraw(canvas);
			return;
		}
		//讓蒼蠅每次移動一格就重新繪圖一次，防止當speed過大時，蒼蠅移動會出現跳格的方式移動
		for(int i = 0; i < mSpeed; i++){
			int x = getX();
			x = x + mSpeed;

			setX(x);

			if (mCake != null) {
				if (mCake.getX() <= x) {
					mSpeed = 0;
				}
			}

			super.onDraw(canvas);
		}
	}

	public void setCake(Cake cake) {
		mCake = cake;
	}

	public void setSpeed(int speed) {
		mSpeed = speed;
	}
}
