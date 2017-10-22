package yvette.game.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import yvette.game.Config;
import yvette.game.model.Score;
import yvette.game.view.GameCanvas;

public class RedTurtleCakes implements MouseMotionListener {
	private static RedTurtleCakes sInstance = new RedTurtleCakes();
	private GameCanvas mGameCanvas;

	private Config mConfig;

	public static RedTurtleCakes getInstance() {
		return sInstance;
	}

	public void initialize(Config config) {
		mConfig = config;
		// 建立遊戲繪布
		mGameCanvas = new GameCanvas();
		mGameCanvas.start();

		// 初始化總分分數面版
		Score scoreBar = new Score();
		scoreBar.setColor(Color.BLACK);
		scoreBar.setX(10);
		scoreBar.setY(10);
		scoreBar.setW(100);
		scoreBar.setH(30);

		mGameCanvas.addRole(scoreBar);
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

}
