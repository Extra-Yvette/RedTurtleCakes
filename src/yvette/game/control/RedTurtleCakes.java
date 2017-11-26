package yvette.game.control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import yvette.game.Config;
import yvette.game.model.ClickableRole;
import yvette.game.model.Flyswatter;
import yvette.game.model.LifeBar;
import yvette.game.model.MouseDragTool;
import yvette.game.model.Cake;
import yvette.game.model.CakeMark;
import yvette.game.model.CakeType;
import yvette.game.model.OnTimeBarTimeoutListener;
import yvette.game.model.ReadDot;
import yvette.game.model.ReadyStartGame;
import yvette.game.model.Score;
import yvette.game.model.TimeBar;
import yvette.game.model.WhitePeach;
import yvette.game.view.GameCanvas;

public class RedTurtleCakes implements MouseMotionListener, MouseListener, OnTimeBarTimeoutListener {
	private static RedTurtleCakes sInstance = new RedTurtleCakes();
	//遊戲場景畫布
	private GameCanvas mGameCanvas;
	//設定檔
	private Config mConfig;

	private RedTurtleCakes(){
	}

	public static RedTurtleCakes getInstance() {
		return sInstance;
	}

	public void initialize(Config config) {
		mConfig = config;
		// 建立遊戲繪布
		mGameCanvas = new GameCanvas();
		
		initReadyStartGame(config);

		mGameCanvas.setFPS(config.getFPS());
		mGameCanvas.addMouseMotionListener(this);
		mGameCanvas.addMouseListener(this);
		mGameCanvas.start();
	}

	//初始化首頁顯示請用滑鼠點擊畫面開始遊戲
	private void initReadyStartGame(Config config) {
		mReadyStartGame = new ReadyStartGame();
		mReadyStartGame.setColor(Color.WHITE);
		mReadyStartGame.setW(config.getScreenWidth());
		mReadyStartGame.setH(config.getScreenHeight());
		//在畫面上首頁按下滑鼠觸發事件
		mReadyStartGame.setOnClickListener(() ->{
			System.out.println("start game");
			mReadyStartGame.setIsAlive(false);
			mClickableRole.remove(mReadyStartGame);
			//開始進行計時，進行遊戲
			mTimeBar.resume();
		});
		mClickableRole.add(0, mReadyStartGame);
		mGameCanvas.addRole(mReadyStartGame);
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
		mMouseDragTool.setX(e.getX());
		mMouseDragTool.setY(e.getY());
	}

	//時間條倒數完時，時間到事件
	@Override
	public void onTimeBarTimeout() {
		//TODO TimeBar,時間到，變換粿
		System.out.println("TimeBar,時間到，變換粿");
		randomChangeCake();
	}

	//點擊上3個按鈕時觸發滑鼠事件
	@Override
	public void mouseClicked(MouseEvent e) {
		//滑鼠點了左鍵
		if(e.getButton() == MouseEvent.BUTTON1){
			//把畫面上4個按鈕取出來判斷有哪個按鈕與滑鼠碰撞
			for(ClickableRole tool : mClickableRole){
				if(tool.hitTest(mMouseDragTool)){
					tool.onClick();
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
