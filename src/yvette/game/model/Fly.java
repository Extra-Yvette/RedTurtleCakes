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
		this.mSpeed = 4;// 每單位時間移動1個像素
		mConfig = RedTurtleCakes.getInstance().getConfig();
	}

	@Override
	public void onDraw(Graphics canvas) {
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

	public void setCake(Cake cake) {
		mCake = cake;
	}

}