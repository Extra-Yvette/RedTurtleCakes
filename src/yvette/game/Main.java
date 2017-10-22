package yvette.game;

import javax.swing.JFrame;

import yvette.game.control.RedTurtleCakes;

public class Main {
	public static void main(String[] args) {
		RedTurtleCakes cakes = RedTurtleCakes.getInstance();

		// 戴入設定檔
		Config config = new Config();
		cakes.initialize(config);

		JFrame frame = new JFrame();
		frame.getContentPane().add(cakes.getGameCanvas());
		frame.setBounds(100, 100, 530, 320);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
