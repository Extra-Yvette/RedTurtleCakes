package yvette.game;


import javax.swing.JFrame;

import yvette.game.control.RedTurtleCakes;

public class Main {
	public static void main(String[] args) {
		RedTurtleCakes cakes = RedTurtleCakes.getInstance();

		//載入設定檔
		Config config = new Config();
		cakes.initialize(config);

		JFrame frame = new JFrame();
		frame.getContentPane().add(cakes.getGameCanvas());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, config.getScreenWidth(), config.getScreenHeight());
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
