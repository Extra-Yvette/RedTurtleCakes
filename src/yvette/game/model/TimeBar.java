package yvette.game.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * 遊戲倒數計時的時間bar
 * 
 * @author yvette
 * 
 */
public class TimeBar extends Role {
	// 目前剩餘秒數
	private int mSecond;
	// 遊戲最大秒數
	private int mMaxSecond;
	// TimeBar被Alarm的百分比
	private double mPercent;
	// 時間到之後要通知的對象
	private OnTimeBarTimeoutListener mOnTimeBarTimeoutListener;
	// 通知鬧鐘響
	private OnTimeBarAlarmClockListener mOnTimeBarAlarmClockListener;

	// 每次刷新畫面經過時間
	private long mSpendTime;
	// 是否有暫停
	private boolean mIsPause;
	private boolean mIsAlarm;

	public TimeBar() {
		mIsPause = true;
	}

	/**
	 * 時間暫停
	 */
	public void pause() {
		mIsPause = true;
	}

	/**
	 * 時間繼續
	 */
	public void resume() {
		mSpendTime = System.currentTimeMillis();
		mIsPause = false;
	}

	/**
	 * 設定時間到之後，要通知的對象
	 * 
	 * @param listener
	 */
	public void setOnTimeBarTimeoutListener(OnTimeBarTimeoutListener listener) {
		mOnTimeBarTimeoutListener = listener;
	}

	public void setOnTimeBarAlarmClockListener(double percent, OnTimeBarAlarmClockListener listener) {
		mPercent = percent;
		mOnTimeBarAlarmClockListener = listener;
	}

	@Override
	public void onDraw(Graphics canvas) {
		if (mIsPause) {
			return;
		}

		// 將畫布上的顏色改成黑色
		canvas.setColor(Color.BLACK);

		// 畫一個空心長方型(黑色框)
		canvas.drawRect(getX() - 1, getY() - 1, getW() + 1, getH() + 1);

		// 用 剩餘時間 / 最大時間 ，可得到百分比，比如0.87
		double percent = (double) mSecond / (double) mMaxSecond;

		// 將畫布上的顏色改成指定的顏色
		canvas.setColor(getColor());
		// 畫一個實心長方型
		canvas.fillRect(getX(), getY(), (int) (getW() * percent), getH());

		//
		if (!mIsAlarm && mPercent < percent) {
			mIsAlarm = true;
			if (mOnTimeBarAlarmClockListener != null) {
				mOnTimeBarAlarmClockListener.onTimeBarAlarmClock();
			}
		}

		// 將剩餘時間減掉經過時間，若剩餘時間低於0就通知時間到
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

	// 倒數計數
	private void countdownTimer() {
		if (isAlive()) {
			long time = 0L;
			long current = System.currentTimeMillis();

			if (mSpendTime != current) {
				time = current - mSpendTime;
				mSpendTime = current;
			}
			mSecond -= time;

			if (mSecond < 0) {
				mSecond = mMaxSecond;
				mIsAlarm = false;
				if (mOnTimeBarTimeoutListener != null) {
					mOnTimeBarTimeoutListener.onTimeBarTimeout();
				}
			}
		}
	}
}
