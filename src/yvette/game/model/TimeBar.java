package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * 遊戲獲得總分分數面版
 * 
 */
public class TimeBar extends Role implements Runnable{
	private int mSecond;
	private int mMaxSecond;
	private Thread mTimeCountdown;
	private OnTimeBarTimeoutListener mOnTimeBarTimeoutListener;
	
	public TimeBar(){
		mTimeCountdown = new Thread(this);
		mTimeCountdown.start();
	}
	
	public void setOnTimeBarTimeoutListener(OnTimeBarTimeoutListener listener){
		mOnTimeBarTimeoutListener = listener;
	}

	@Override
	public void onDraw(Graphics canvas) {
		canvas.setColor(Color.BLACK);
		
		canvas.drawRect(getX() - 1, getY() - 1, getW() + 1, getH() + 1);
		
		double percent = (double)mSecond / (double)mMaxSecond;
		
		canvas.setColor(getColor());
		canvas.fillRect(getX(), getY(), (int)(getW() * percent), getH());
		
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

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mSecond -= 10;
			
			if(mSecond < 0){
				mSecond = mMaxSecond;
				if(mOnTimeBarTimeoutListener != null){
					mOnTimeBarTimeoutListener.onTimeBarTimeout();
				}
			}
		}
	}
	
	
}
