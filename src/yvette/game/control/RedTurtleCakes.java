package yvette.game.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import yvette.game.Config;
import yvette.game.model.OnTimeBarTimeoutListener;
import yvette.game.model.Score;
import yvette.game.model.TimeBar;
import yvette.game.view.GameCanvas;

public class RedTurtleCakes implements MouseMotionListener, OnTimeBarTimeoutListener {
	private static RedTurtleCakes sInstance = new RedTurtleCakes();
	private GameCanvas mGameCanvas;

	private Config mConfig;
	
	private TimeBar mTimeBar;
	private Score mScoreBar;

	public static RedTurtleCakes getInstance() {
		return sInstance;
	}

	public void initialize(Config config) {
		mConfig = config;
		// 建立遊戲繪布
		mGameCanvas = new GameCanvas();
		mGameCanvas.start();

		initScoreBar(config);
		initTimeBar(config);
		
		mGameCanvas.addMouseMotionListener(this);
		
	}
	
	// 初始化總分分數面版
	private void initScoreBar(Config config){
		mScoreBar = new Score();
		mScoreBar.setColor(Color.BLACK);
		mScoreBar.setX(300);
		mScoreBar.setY(22);
		mScoreBar.setW(100);
		mScoreBar.setH(30);
		mGameCanvas.addRole(mScoreBar);
	}

	private void initTimeBar(Config config){
		mTimeBar = new TimeBar();
		mTimeBar.setColor(Color.PINK);
		mTimeBar.setX(10);
		mTimeBar.setY(10);
		mTimeBar.setW(200);
		mTimeBar.setH(20);
		mTimeBar.setSecond(config.getCakeChangeTime());
		mTimeBar.setMaxSecond(config.getCakeChangeTime());
		mTimeBar.setOnTimeBarTimeoutListener(this);
		mGameCanvas.addRole(mTimeBar);
	}

	public Config getConfig() {
		return mConfig;
	}

	public GameCanvas getGameCanvas() {
		return mGameCanvas;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTimeBarTimeout() {
		// TimeBar,時間到，變換粿
		System.out.println("TimeBar,時間到，變換粿");
	}
}
