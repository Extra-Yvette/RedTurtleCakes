package yvette.game.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import yvette.game.Config;
import yvette.game.model.scenes.OnChangeScenesListener;
import yvette.game.model.scenes.Scenes;
import yvette.game.model.scenes.ScenesGameLoop;
import yvette.game.model.scenes.ScenesGameOver;
import yvette.game.model.scenes.ScenesReadyStart;
import yvette.game.view.GameCanvas;

/**
 * 遊戲分為MVC三個部份，class
 * RedTurtleCakes為Control，管理場景(model)切換，並將場景通知GameCanvas場景畫布繪圖(view)進行顯示
 * 
 * @author yvette
 *
 */
public class RedTurtleCakes implements MouseMotionListener, MouseListener, OnChangeScenesListener {
	private static RedTurtleCakes sInstance = new RedTurtleCakes();
	//遊戲場景畫布
	private GameCanvas mGameCanvas;
	//設定檔
	private Config mConfig;
	//存放3個遊戲場景(遊戲開始畫面、遊戲進行中、遊戲結束)
	private Map<String, Scenes> mScenes;

	private RedTurtleCakes(){
	}

	public static RedTurtleCakes getInstance() {
		return sInstance;
	}

	/**
	 * 初始遊戲
	 * @param config
	 */
	public void initialize(Config config) {
		mConfig = config;

		mScenes = new ConcurrentHashMap<>();
		//建立3個遊戲場景
		ScenesReadyStart readyStart = new ScenesReadyStart(mConfig);
		ScenesGameLoop gameLoop = new ScenesGameLoop(mConfig);
		ScenesGameOver gameOver = new ScenesGameOver(mConfig);

		//將全部遊戲場景緩存要重複使用
		mScenes.put(config.getScenesReadyStartName(), readyStart);
		mScenes.put(config.getScenesGameLoop(), gameLoop);
		mScenes.put(config.getScenesGameOver(), gameOver);

		//註冊遊戲場景切換時，要將景場事件發送class RedTurtleCakes接收，會呼叫void onChangeScenes(String scenesName)
		readyStart.setOnChangeScenesListener(this);
		gameLoop.setOnChangeScenesListener(this);
		gameOver.setOnChangeScenesListener(this);

		// 建立遊戲繪布
		mGameCanvas = new GameCanvas();

		mGameCanvas.setScenes(readyStart);

		mGameCanvas.setFPS(config.getFPS());
		mGameCanvas.addMouseMotionListener(this);
		mGameCanvas.addMouseListener(this);
		mGameCanvas.start();
	}

	//接收到切換景事件
	@Override
	public void onChangeScenes(String scenesName) {
		//目前顯示的場景
		Scenes currentScenes = mGameCanvas.getScenes();
		//將要顯示的景場
		Scenes targetScenes = mScenes.get(scenesName);	
		targetScenes.onLoad();
		//換場景
		mGameCanvas.setScenes(targetScenes);
		currentScenes.onUnload();
	}

	/**
	 * 取得遊戲設定檔物件
	 * @return
	 */
	public Config getConfig() {
		return mConfig;
	}

	/**
	 * 取得遊戲繪圖物件
	 * @return
	 */
	public GameCanvas getGameCanvas() {
		return mGameCanvas;
	}

	//滑鼠拖曳事件(此遊戲用不到)
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	//滑鼠移動事件
	@Override
	public void mouseMoved(MouseEvent e) {
		Scenes scenes = mGameCanvas.getScenes();

		if(scenes != null) {
			scenes.mouseMoved(e);
		}
	}

	//點擊上3個按鈕時觸發滑鼠事件
	@Override
	public void mouseClicked(MouseEvent e) {
		Scenes scenes = mGameCanvas.getScenes();

		if(scenes != null) {
			scenes.mouseClicked(e);
		}
	}

	//按住滑鼠任一按鍵
	@Override
	public void mousePressed(MouseEvent e) {
	}

	//放開滑鼠任一按鍵
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	//滑鼠滑進遊戲視窗內
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	//滑鼠滑出遊戲視窗外
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
