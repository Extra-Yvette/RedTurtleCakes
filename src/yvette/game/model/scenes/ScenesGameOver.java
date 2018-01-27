package yvette.game.model.scenes;

import java.awt.Color;

import yvette.game.Config;
import yvette.game.model.GameOverTitle;

/**
 * 
 * 遊戲結束畫面
 * 
 * @author yvette
 *
 */
public class ScenesGameOver extends Scenes {

	// 遊戲結束，等待玩家按下滑鼠
	private GameOverTitle mGameOverTitle;

	public ScenesGameOver(Config config) {
		super();
		mConfig = config;
		initReadyStartGame(config);
	}

	// 初始化首頁顯示請用滑鼠點擊畫面開始遊戲
	private void initReadyStartGame(Config config) {
		mGameOverTitle = new GameOverTitle();
		mGameOverTitle.setColor(Color.WHITE);
		mGameOverTitle.setW(config.getScreenWidth());
		mGameOverTitle.setH(config.getScreenHeight());
		// 在畫面上首頁按下滑鼠觸發事件
		mGameOverTitle.setOnClickListener(() -> {
			System.out.println("restart game");

			// 切換到遊戲進行場景，進行遊戲
			changeScenes(mConfig.getScenesGameLoop());
		});
		addRole(mGameOverTitle);
	}
}
