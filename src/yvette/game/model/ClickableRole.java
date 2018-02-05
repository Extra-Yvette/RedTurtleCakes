package yvette.game.model;

import java.awt.Graphics;

/**
 * 在畫面上可以被點擊的遊戲角色
 * 
 * @author yvette
 *
 */
public abstract class ClickableRole extends Role {
	private OnClickListener mOnClickListener;

	public void onClick() {
		if (mOnClickListener != null) {
			mOnClickListener.onClick();
		}
	}

	public void setOnClickListener(OnClickListener listener) {
		mOnClickListener = listener;
	}
}
