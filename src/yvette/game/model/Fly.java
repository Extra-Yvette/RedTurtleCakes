package yvette.game.model;

import java.awt.Graphics;

import yvette.game.Config;
import yvette.game.control.RedTurtleCakes;

public class Fly  extends ClickableRole{
	//設定蒼蠅飛的速度
	private int mSpeed;
	private Config mConfig;
	
	public Fly() {
		this.mSpeed = 1;//每單位時間移動1個像素
		mConfig = RedTurtleCakes.getInstance().getConfig();
	}

	@Override
	public void onDraw(Graphics canvas) {
		// TODO Auto-generated method stub
		
		System.out.println("Fly");

		int x = getX();
		x=x+1;
//		if(x >= 300) 
//		{
//			x=50;
//		}
		System.out.println("Fly"+x);
		setX(x);
		
		super.onDraw(canvas);
		
	}

	
	


}
