package yvette.game;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import yvette.game.control.RedTurtleCakes;

public class Main {
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		System.out.println("Screen width = " + d.width);
		System.out.println("Screen height = " + d.height);


		RedTurtleCakes cakes = RedTurtleCakes.getInstance();

		//載入設定檔
		Config config = new Config();
		cakes.initialize(config);

		JFrame frame = new JFrame();
		frame.getContentPane().add(cakes.getGameCanvas());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds((d.width / 2) - (config.getScreenWidth() / 2),
				(d.height / 2) - (config.getScreenWidth() / 2),
				config.getScreenWidth(),
				config.getScreenHeight());
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
