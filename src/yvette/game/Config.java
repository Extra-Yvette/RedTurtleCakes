package yvette.game;

public class Config {
	private int mFlySpeed;
	private int mCakeChangeTime = 10;

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
}
