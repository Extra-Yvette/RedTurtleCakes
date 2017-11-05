package yvette.game;

public class Config {
	private int mFlySpeed;
	private int mCakeChangeTime = 5;
	private int mScreenWidth = 530;
	private int mScreenHeight = 320;
	//敲紅龜粿可獲得分數
	private int mRedScore = 100;
	//敲草阿粿可獲得分數
	private int mGreenScore = 200;
	//敲草壽桃可獲得分數
	private int mWhiteScore = 300;

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
	
	/**
	 * 敲紅龜粿可獲得分數
	 * @return
	 */
	public int getRedScore() {
		return mRedScore;
	}

	/**
	 * 敲草阿粿可獲得分數
	 * @return
	 */
	public int getGreenScore() {
		return mGreenScore;
	}
	
	/**
	 * 敲草壽桃可獲得分數
	 * @return
	 */
	public int getWhiteScore() {
		return mWhiteScore;
	}
}
