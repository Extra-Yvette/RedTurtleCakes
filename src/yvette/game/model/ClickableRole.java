package yvette.game.model;

import java.awt.Graphics;


public abstract class ClickableRole extends Role{
	private OnClickListener mOnClickListener;
	
	@Override
	public void onDraw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.fillRect(getX(), getY(), getW(), getH());
	}
	
	public void onClick(){
		if(mOnClickListener != null){
			mOnClickListener.onClick();
		}
	}

	public void setOnClickListener(OnClickListener listener){
		mOnClickListener = listener;
	}
}
