package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * 遊戲倒數計時的時間bar
 * 
 */
public class TimeBar extends Role{
	private int mSecond;
	private int mMaxSecond;
	private OnTimeBarTimeoutListener mOnTimeBarTimeoutListener;
	private long mSpendTime;
	private boolean mIsPause;
	
	public TimeBar(){
		mIsPause = true;
	}
	
	public void pause() {
		mIsPause = true;
	}
	
	public void resume() {
		mSpendTime = System.currentTimeMillis();
		mIsPause = false;
	}
	
	public void setOnTimeBarTimeoutListener(OnTimeBarTimeoutListener listener){
		mOnTimeBarTimeoutListener = listener;
	}

	@Override
	public void onDraw(Graphics canvas) {
		if(mIsPause) {
			return;
		}
		
		canvas.setColor(Color.BLACK);
		
		canvas.drawRect(getX() - 1, getY() - 1, getW() + 1, getH() + 1);
		
		double percent = (double)mSecond / (double)mMaxSecond;
		
		canvas.setColor(getColor());
		canvas.fillRect(getX(), getY(), (int)(getW() * percent), getH());
		
		countdownTimer();
	}

	/**
	 * 取得總分
	 */
	public int getScore() {
		return mSecond;
	}

	/**
	 * 設定目前秒數
	 * 
	 * @param second
	 */
	public void setSecond(int second) {
		mSecond = second * 1000;
	}
	
	/**
	 * 設定最大秒數
	 * 
	 * @param second
	 */
	public void setMaxSecond(int second) {
		mMaxSecond = second * 1000;
	}

	/**
	 * 增加總分，參數second可為負數
	 * 
	 * @param score
	 */
	public void addSecond(int second) {
		mSecond += (second * 1000);
	}

	//倒數計數
	private void countdownTimer() {
		if(isALive()){
			long time = 0L;
			long current = System.currentTimeMillis();
			
			if(mSpendTime != current) {
				time = current - mSpendTime;
				mSpendTime = current;
			}
			mSecond -= time;
			
			if(mSecond < 0){
				mSecond = mMaxSecond;
				if(mOnTimeBarTimeoutListener != null){
					mOnTimeBarTimeoutListener.onTimeBarTimeout();
				}
			}
		}
	}
	
	
}
