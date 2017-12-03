package yvette.game.model;

import java.awt.Color;

import yvette.game.Config;

/**
 * 
 * 遊戲開始畫面
 * 
 * @author yvette
 *
 */
public class ScenesReadyStart extends Scenes{
	private Config mConfig;

	//開場的畫面，等待玩家按下滑鼠
	private ReadyStartGame mReadyStartGame;
	
	public ScenesReadyStart(Config config) {
		super();
		mConfig = config;
		initReadyStartGame(config);
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
			
			//切換到遊戲進行場景，進行遊戲
			changeScenes(mConfig.getScenesGameLoop());
		});
		addRole(mReadyStartGame);
	}
}
