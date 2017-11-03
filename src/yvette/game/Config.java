package yvette.game;

public class Config {
	private int mFlySpeed;
	private int mCakeChangeTime = 5;
	private int mScreenWidth = 530;
	private int mScreenHeight = 320;

	/**
	 * 蒼蠅飛行速度
	 * @return
	 */
	public int getFlySpeed() {
		return mFlySpeed;
	}
	
	/**
	 * 取得每次變換粿的時間
	 * @return
	 */
	public int getCakeChangeTime(){
		return mCakeChangeTime;
	}
	
	/**
	 * 取得遊戲畫面寬
	 * @return
	 */
	public int getScreenWidth() {
		return mScreenWidth;
	}
	
	/**
	 * 取得遊戲畫面高
	 * @return
	 */
	public int getScreenHeight() {
		return mScreenHeight;
	}
}
