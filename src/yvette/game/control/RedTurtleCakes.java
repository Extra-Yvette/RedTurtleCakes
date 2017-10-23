package yvette.game.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import yvette.game.Config;
import yvette.game.model.AbstractTool;
import yvette.game.model.OnClickListener;
import yvette.game.model.OnTimeBarTimeoutListener;
import yvette.game.model.Role;
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
	//時間bar
	private TimeBar mTimeBar;
	//總分bar
	private Score mScoreBar;
	//畫面下方4個按鈕
	private List<AbstractTool> mTools;
	//目前跟隨滑鼠鼠標移動中的工具
	private Role mCurrentTool;
	
	private RedTurtleCakes(){
		mCurrentTool = new Role(){};
		mTools = new ArrayList<AbstractTool>();
	}

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
		initTools(config);
		
		mGameCanvas.addMouseMotionListener(this);
		mGameCanvas.addMouseListener(this);
		
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

	private void initTools(Config config){
		//範例按鈕-----begin-----
		WhitePeach whitePeach = new WhitePeach();
		whitePeach.setColor(Color.CYAN);
		whitePeach.setX(10);
		whitePeach.setY(200);
		whitePeach.setW(50);
		whitePeach.setH(50);
		whitePeach.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick() {
				// TODO 點到了WhitePeach按鈕
				System.out.println("點到了WhitePeach按鈕");
			}
		});
		
		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		mTools.add(whitePeach);
		mGameCanvas.addRole(whitePeach);
		//範例按鈕-----end-----
		
		//TODO 將其他3個按鈕照上面範例實作
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
		//TODO 滑鼠移動事件
		mCurrentTool.setX(e.getX());
		mCurrentTool.setY(e.getY());
	}

	@Override
	public void onTimeBarTimeout() {
		// TimeBar,時間到，變換粿
		System.out.println("TimeBar,時間到，變換粿");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//滑鼠點了左鍵
		if(e.getButton() == MouseEvent.BUTTON1){
			for(AbstractTool tool : mTools){
				if(tool.hitTest(mCurrentTool)){
					tool.onClick();
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
