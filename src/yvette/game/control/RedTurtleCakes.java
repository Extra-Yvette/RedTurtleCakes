package yvette.game.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import yvette.game.Config;
import yvette.game.model.AbstractTool;
import yvette.game.model.Cake;
import yvette.game.model.CakeType;
import yvette.game.model.OnClickListener;
import yvette.game.model.OnTimeBarTimeoutListener;
import yvette.game.model.ReadDot;
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
	//要被打的粿
	private Cake mCake;
	//總分bar
	private Score mScoreBar;
	//畫面下方4個按鈕
	private List<AbstractTool> mTools;
	//目前跟隨滑鼠鼠標移動中的工具
	private Role mCurrentTool;
	//用來產生隨機亂數的物件
	private Random mRandom;

	private RedTurtleCakes(){
		mRandom = new Random();
		mTools = new ArrayList<AbstractTool>();
	}

	public static RedTurtleCakes getInstance() {
		return sInstance;
	}

	public void initialize(Config config) {
		mConfig = config;
		// 建立遊戲繪布
		mGameCanvas = new GameCanvas();
		initTools(config);
		initCake();
		initCurrentTool();
		initScoreBar(config);
		initTimeBar(config);
		initReadyStartGame(config);

		mGameCanvas.addMouseMotionListener(this);
		mGameCanvas.addMouseListener(this);
		mGameCanvas.start();
	}
	
	//初始化首頁顯示請用滑鼠點擊畫面開始遊戲
	private void initReadyStartGame(Config config) {
		AbstractTool readyStartGame = new AbstractTool(){
			private int mColorAlpha = 0;
			private int mAlphaFlag = 8;
			private Font mTitleFont;
			private Font mPressStartFont;
			
			public void onDraw(Graphics canvas) {
				canvas.setColor(getColor());
				canvas.fillRect(getX(), getY(), getW(), getH());
				Font currentFont = canvas.getFont();
				
				//設定首頁紅龜粿字型大小
				if (mTitleFont == null) {
					mTitleFont = currentFont.deriveFont(Font.ITALIC, 72);
				}
				canvas.setFont(mTitleFont);
				
				canvas.setColor(Color.RED);
				canvas.drawString("紅龜粿", config.getScreenWidth() / 2 - 120, config.getScreenHeight() / 2 - 40);
				canvas.setFont(currentFont);
				
				//讓文字淡入淡出效果
				mColorAlpha += mAlphaFlag;
				if(mColorAlpha > 255) {
					mColorAlpha = 255;
					mAlphaFlag = -mAlphaFlag;
				}
				if(mColorAlpha < 0) {
					mColorAlpha = 0;
					mAlphaFlag = -mAlphaFlag;
				}
				
				//設定首頁"請用滑鼠點擊畫面後開始遊戲"字型大小
				if (mPressStartFont == null) {
					mPressStartFont = currentFont.deriveFont(Font.BOLD, 24);
				}
				canvas.setFont(mPressStartFont);
				canvas.setColor(new Color(0, 0, 255, mColorAlpha));
				canvas.drawString("請用滑鼠點擊畫面後開始遊戲", 100, config.getScreenHeight() / 2 + 50);
				canvas.setFont(currentFont);
			}
		};
		readyStartGame.setColor(Color.WHITE);
		readyStartGame.setW(config.getScreenWidth());
		readyStartGame.setH(config.getScreenHeight());
		readyStartGame.setOnClickListener(() ->{
			System.out.println("start game");
			readyStartGame.setIsAlive(false);
			mTimeBar.resume();
		});
		mTools.add(readyStartGame);
		mGameCanvas.addRole(readyStartGame);
	}
	
	//初始化畫面上的粿
	private void initCake() {
		mCake = new Cake();
		mCake.setX(200);
		mCake.setY(100);
		mCake.setW(50);
		mCake.setH(50);
		
		randomChangeCake();
		mGameCanvas.addRole(mCake);
	}
	
	//初始化跟著滑鼠鼠標移動的工具
	private void initCurrentTool() {
		mCurrentTool = new Role(){
			public void onDraw(Graphics canvas) {
				canvas.setColor(getColor());
				canvas.fillArc(getX(), getY(), getW(), getH(), 0, 360);
			}
		};
		mCurrentTool.setEnableCenter(true);
		mCurrentTool.setColor(Color.WHITE);
		mCurrentTool.setW(50);
		mCurrentTool.setH(50);
		
		mGameCanvas.addRole(mCurrentTool);
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

	//初始化4個按鈕(切換工具)
	private void initTools(Config config){
		//範例按鈕-----begin-----
		WhitePeach whitePeach = new WhitePeach();
		whitePeach.setColor(Color.CYAN);
		whitePeach.setX(50);
		whitePeach.setY(200);
		whitePeach.setW(50);
		whitePeach.setH(50);
		whitePeach.setOnClickListener(() -> {
			// TODO 點到了WhitePeach按鈕
			System.out.println("點到了WhitePeach按鈕");
			mCurrentTool.setColor(Color.CYAN);
		});

		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		mTools.add(whitePeach);
		mGameCanvas.addRole(whitePeach);
		//範例按鈕-----end-----


		//範例按鈕2-----begin-----
		ReadDot readDot = new ReadDot();
		readDot.setColor(Color.RED);
		readDot.setX(150);
		readDot.setY(200);
		readDot.setW(50);
		readDot.setH(50);
		readDot.setOnClickListener(()->{
			// TODO 點到了WhitePeach按鈕
			System.out.println("點到了ReadDot按鈕");
			mCurrentTool.setColor(Color.RED);
		});

		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		mTools.add(readDot);
		mGameCanvas.addRole(readDot);
		//範例按鈕-----end-----

		//TODO 將其他2個按鈕照上面範例實作
	}

	//初始化時間條面板
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
	
	//隨機亂數改變畫面中央的粿
	private void randomChangeCake() {
		int randValue = mRandom.nextInt(CakeType.values().length);
		mCake.setCakeType(CakeType.values()[randValue]);
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
		mCurrentTool.setX(e.getX());
		mCurrentTool.setY(e.getY());
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
