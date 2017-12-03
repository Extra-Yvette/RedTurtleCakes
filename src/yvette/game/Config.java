package yvette.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * 遊戲設定檔
 * 
 * @author yvette
 *
 */
public class Config {
	//遊戲視窗大小-寬
	private int mScreenWidth = 720;
	//遊戲視窗大小-高
	private int mScreenHeight = 480;
	//蒼蠅飛行速度
	private int mFlySpeed = 1;
	//遊戲中時，畫面正中間的粿間隔多久會變換
	private int mCakeChangeTime = 5;
	//敲紅龜粿可獲得分數
	private int mRedScore = 100;
	//敲草阿粿可獲得分數
	private int mGreenScore = 200;
	//敲草壽桃可獲得分數
	private int mWhiteScore = 300;
	//玩家每次重新開始玩遊戲的愛心個數
	private int mLifeDefault = 5;
	//遊戲繪圖每秒繪制張數
	private int mFPS = 60;
	
	//開場的景場名稱
	//準備開始遊戲
	public String mScenesReadyStart = "ScenesReadyStart";
	//遊戲進行中
	public String mScenesGameLoop = "ScenesGameLoop";
	//遊戲結束
	public String mScenesGameOver = "ScenesGameOver";

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
	
	/**
	 * 玩家每次重新開始玩遊戲的愛心個數
	 * @return
	 */
	public int getLifeDefault(){
		return mLifeDefault;
	}
	
	/**
	 * 遊戲繪圖每秒繪制張數
	 * @return
	 */
	public int getFPS() {
		return mFPS;
	}
	

	/**
	 * 戴入圖片
	 * @param path
	 * @return
	 */
	public Image loadImage(String path) {
		return loadImage(path, null, null);
	}

	/**
	 * 戴入縮放後的圖片
	 * @param path
	 * @param scaledWidth
	 * @param scaledHeight
	 * @return
	 * @throws IOException
	 */
	public Image loadImage(String path, Integer scaledWidth, Integer scaledHeight) {
		BufferedImage targetImage = null;
		try {
			BufferedImage sourceImage = ImageIO.read(getClass().getResource(path));
			
			if(scaledWidth != null &&  scaledHeight != null) {
				targetImage = new BufferedImage(scaledWidth,
						scaledHeight, sourceImage.getType());
				Graphics2D g2d = targetImage.createGraphics();
				g2d.drawImage(sourceImage, 0, 0, scaledWidth, scaledHeight, null);
				g2d.dispose();
			}else {
				targetImage = sourceImage;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetImage;
	}
	
	/**
	 * 取得準備開始遊戲的場景名稱"ScenesReadyStart"
	 * @return
	 */
	public String getScenesReadyStartName() {
		return mScenesReadyStart;
	}
	
	/**
	 * 取得遊戲進行中的場景名稱"ScenesGameLoop"
	 * @return
	 */
	public String getScenesGameLoop() {
		return mScenesGameLoop;
	}
	
	/**
	 * 取得遊戲結束的場景名稱"ScenesGameOver"
	 * @return
	 */
	public String getScenesGameOver() {
		return mScenesGameOver;
	}
}
