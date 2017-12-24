package yvette.game.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.SwingUtilities;

import yvette.game.model.scenes.Scenes;

/**
 * 繪圖
 * 
 * @author Ray
 * 
 */
public class GameCanvas extends Canvas implements Runnable {
	private static final long serialVersionUID = 6746193229592293224L;
	private boolean mIsRun;
	private boolean mIsPause;
	private int mFps; // 每秒要畫幾次
	private Scenes mScenes;
	private Image mImageBuffer;
	private Graphics mCanvasBuffer;

	public GameCanvas() {
		mIsRun = false;
		mIsPause = false;
		mFps = 30;
	}

	/**
	 * 開始繪圖
	 */
	public void start() {
		if (!mIsRun) {
			mIsRun = true;
			new Thread(this).start();
		}
	}

	/**
	 * 結束繪圖
	 */
	public void stop() {
		mIsRun = false;
	}

	/**
	 * 暫停繪圖
	 */
	public void pause() {
		mIsPause = true;
	}

	/**
	 * 繼續繪圖
	 */
	public void resume() {
		mIsPause = false;
	}

	/**
	 * 設定1秒要畫幾次
	 * 
	 * @param n
	 */
	public void setFPS(int n) {
		mFps = n;
	}

	/**
	 * 取得目前1秒畫幾次
	 * 
	 * @return
	 */
	public int getFPS() {
		return mFps;
	}

	/**
	 * 取得目前是否暫停中
	 * 
	 * @return
	 */
	public boolean isPause() {
		return mIsPause;
	}

	/**
	 * 覆寫update是為了不讓系統自動清上次畫布的內容，覆寫paint()畫快一點會因為系統自動清畫面而一直閃爍
	 */
	@Override
	public void update(Graphics g) {
		if(mCanvasBuffer == null) {
			Image img = createImage(getWidth(), getHeight());
			mCanvasBuffer = img.getGraphics();
			mImageBuffer = img;
		}else {
			mCanvasBuffer.clearRect(0, 0, getWidth(), getHeight());
		}
		onDraw(mCanvasBuffer);
		g.drawImage(mImageBuffer, 0, 0, this);
	}

	@Override
	public void run() {
		while (mIsRun) {

			if (!mIsPause) {
				try {
					SwingUtilities.invokeAndWait(()-> repaint());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int sec = (int) ((10.0 / mFps) * 100);

			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void onDraw(Graphics canvas) {
		// 繪制全部場景上的角色
		if(mScenes != null) {
			mScenes.onDraw(canvas);
		}
	}
	
	/**
	 * 設定目前要顯示在畫面上的場景
	 * @param scenes
	 */
	public void setScenes(Scenes scenes) {
		mScenes = scenes;
	}
	
	/**
	 * 取得目前顯示在畫面上的場景
	 * @return
	 */
	public Scenes getScenes() {
		return mScenes;
	}
}