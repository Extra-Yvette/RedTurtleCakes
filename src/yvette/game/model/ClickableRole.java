package yvette.game.model;

import java.awt.Graphics;

/**
 * 在畫面上可以被點擊的遊戲角色
 * 
 * @author yvette
 *
 */
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
