package yvette.game.model.scenes;

import java.awt.Color;
import java.util.Random;

import yvette.game.Config;
import yvette.game.model.Cake;
import yvette.game.model.CakeMark;
import yvette.game.model.CakeType;
import yvette.game.model.Flyswatter;
import yvette.game.model.LifeBar;
import yvette.game.model.MouseDragTool;
import yvette.game.model.OnTimeBarTimeoutListener;
import yvette.game.model.ReadDot;
import yvette.game.model.Score;
import yvette.game.model.TimeBar;
import yvette.game.model.WhitePeach;

/**
 * 
 * 遊戲進行中畫面
 * 
 * @author yvette
 *
 */
public class ScenesGameLoop extends Scenes implements OnTimeBarTimeoutListener{

	//時間bar
	private TimeBar mTimeBar;
	//愛心個數(玩家生命)bar
	private LifeBar mLifeBar;
	//要被打的粿
	private Cake mCake;
	//總分bar
	private Score mScoreBar;
	//目前跟隨滑鼠鼠標移動中的工具
	private MouseDragTool mMouseDragTool;
	//用來產生隨機亂數的物件
	private Random mRandom;
	
	public ScenesGameLoop(Config config) {
		super();
		mConfig = config;
	}
	
	@Override
	public void onLoad() {
		initialize(mConfig);
		mTimeBar.resume();
	}
	
	@Override
	public void onUnload() {
		clearRoles();
	}
	
	private void initialize(Config config){
		initTools(config);
		initCake(config);
		initCurrentTool(config);
		initScoreBar(config);
		initTimeBar(config);
		initLifeBar(config);
	}


	//初始化畫面上的粿
	private void initCake(Config config) {
		mRandom = new Random();
		mCake = new Cake();
		mCake.setW(100);
		mCake.setH(100);
		mCake.setX(config.getScreenWidth() / 2 - mCake.getW());
		mCake.setY(config.getScreenHeight() / 2 - mCake.getH());

		mCake.setOnClickListener(() -> {
			System.out.println("拿著["+mMouseDragTool.getCakeType()+"],場上的粿["+mCake.getCakeType()+"]");
			//還沒選擇打粿的道具
			if(mMouseDragTool.getCakeType() == null) {
				return;
			}

			//判斷滑鼠拿的道具是否對應可以打的粿
			if(mMouseDragTool.getCakeType() == mCake.getCakeType()) {
				//TODO 判斷滑鼠拖曳的道具打到了哪種物品
				switch(mCake.getCakeType()){
				case RED:
					hitReadCake();
					break;
				case GREEN:
					hitGreenCake();
					break;
				case WHITE:
					hitWhiteCake();
					break;
				case FLY:
					hitFly();
					break;
				default:
				}
			}else{
				//給錯工具
				hitMiss();
			}
		});

		randomChangeCake();
		addRole(mCake);
	}

	//打到紅龜粿
	private void hitReadCake() {
		System.out.println("打到紅龜粿");
		mScoreBar.addScore(mConfig.getRedScore());
		//TODO 加分、變換別的粿，時間重算
	}

	//打到草阿粿
	private void hitGreenCake() {
		System.out.println("打到草阿粿");
		mScoreBar.addScore(mConfig.getGreenScore());
		//TODO 加分、變換別的粿，時間重算
	}

	//打到壽桃
	private void hitWhiteCake() {
		System.out.println("打到壽桃");
		mScoreBar.addScore(mConfig.getWhiteScore());
		//TODO 加分、變換別的粿，時間重算
	}

	//打到壽桃
	private void hitFly() {
		System.out.println("打到蒼蠅");
		//TODO ???
	}

	private void hitMiss(){
		System.out.println("給錯工具");
		//TODO 給錯工具也會減一顆愛心
		mLifeBar.addCount(-1);
		
		//TODO 判斷愛心扣完，切換畫面到遊戲結束
		if(mLifeBar.getCount() <= 0) {
			changeScenes(mConfig.getScenesGameOver());
		}
	}

	//初始化跟著滑鼠鼠標移動的工具
	private void initCurrentTool(Config config) {
		mMouseDragTool = new MouseDragTool();
		mMouseDragTool.setEnableCenter(true);
		mMouseDragTool.setW(50);
		mMouseDragTool.setH(50);
		
		setMouseRole(mMouseDragTool);

		addRole(mMouseDragTool);
	}

	// 初始化總分分數面版
	private void initScoreBar(Config config){
		mScoreBar = new Score();
		mScoreBar.setColor(Color.BLACK);
		mScoreBar.setY(25);
		mScoreBar.setW(150);
		mScoreBar.setH(30);
		mScoreBar.setX(config.getScreenWidth() - mScoreBar.getW());
		addRole(mScoreBar);
	}

	//初始化4個按鈕(切換工具)
	private void initTools(Config config){
		int baseX = config.getScreenWidth()/2 - 200;
		int paddingX = 125;
		//粿印
		CakeMark cakeMark = new CakeMark();
		cakeMark.setColor(Color.RED);
		cakeMark.setX(baseX + (paddingX * 0));
		cakeMark.setY(config.getScreenHeight() - 100);
		cakeMark.setW(50);
		cakeMark.setH(50);
		cakeMark.setImage(mConfig.loadImage("/images/redTurtleUtil.png", cakeMark.getW(), cakeMark.getH()));
		cakeMark.setOnClickListener(() -> {
			// TODO 切換成粿印
			System.out.println("切換粿印");
			mMouseDragTool.setColor(Color.RED);
			mMouseDragTool.setCakeType(CakeType.RED);
			mMouseDragTool.setImage(mConfig.loadImage("/images/redTurtleUtil.png", mMouseDragTool.getW(), mMouseDragTool.getH()));
		});

		addRole(cakeMark);

		//草阿粿點紅點
		ReadDot readDot = new ReadDot();
		readDot.setColor(Color.GREEN);
		readDot.setX(baseX + (paddingX * 1));
		readDot.setY(config.getScreenHeight() - 100);
		readDot.setW(50);
		readDot.setH(50);
		readDot.setOnClickListener(()->{
			// TODO 切換成草阿粿模具
			System.out.println("切換成草阿粿模具");
			mMouseDragTool.setColor(Color.GREEN);
			//設定滑鼠拖曳的道具用來打草阿粿
			mMouseDragTool.setCakeType(CakeType.GREEN);
			mMouseDragTool.setImage(null);
		});

		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		addRole(readDot);


		//壽模模具
		WhitePeach whitePeach = new WhitePeach();
		whitePeach.setColor(Color.WHITE);
		whitePeach.setX(baseX + (paddingX * 2));
		whitePeach.setY(config.getScreenHeight() - 100);
		whitePeach.setW(50);
		whitePeach.setH(50);
		whitePeach.setOnClickListener(() -> {
			// TODO 切換成壽桃模具
			System.out.println("切換成壽桃模具");
			mMouseDragTool.setColor(Color.WHITE);
			mMouseDragTool.setCakeType(CakeType.WHITE);
			mMouseDragTool.setImage(null);
		});

		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		addRole(whitePeach);


		//蒼蠅拍
		Flyswatter flyswatter = new Flyswatter();
		flyswatter.setColor(Color.BLACK);
		flyswatter.setX(baseX + (paddingX * 3));
		flyswatter.setY(config.getScreenHeight() - 100);
		flyswatter.setW(50);
		flyswatter.setH(50);
		flyswatter.setOnClickListener(() -> {
			// TODO 切換成蒼蠅拍
			System.out.println("切換蒼蠅拍");
			mMouseDragTool.setColor(Color.BLACK);
			mMouseDragTool.setCakeType(CakeType.FLY);
			mMouseDragTool.setImage(null);
		});

		//將按鈕放到可被檢查是否有點擊到按鈕的容器(陣列)
		addRole(flyswatter);
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
		addRole(mTimeBar);
	}

	private void initLifeBar(Config config){
		mLifeBar = new LifeBar();
		mLifeBar.setColor(Color.RED);
		mLifeBar.setX(config.getScreenWidth() - 100);
		mLifeBar.setY(25);
		mLifeBar.setW(200);
		mLifeBar.setH(20);
		mLifeBar.setCount(config.getLifeDefault());
		addRole(mLifeBar);
	}

	//時間條倒數完時，時間到事件
	@Override
	public void onTimeBarTimeout() {
		//TODO TimeBar,時間到，變換粿
		System.out.println("TimeBar,時間到，變換粿");
		randomChangeCake();
	}

	//隨機亂數改變畫面中央的粿
	private void randomChangeCake() {
		//隨機選一種粿出現，-1是不讓蒼蠅出現(workaround做法…)
		int randValue = mRandom.nextInt(CakeType.values().length - 1);
		mCake.setCakeType(CakeType.values()[randValue]);

		switch(mCake.getCakeType()) {
		case RED:
			mCake.setImage(mConfig.loadImage("/images/redTurtleCake.png", mCake.getW(), mCake.getH()));
			break;
		case GREEN:
			//TODO 換成草仔粿圖片
			mCake.setImage(mConfig.loadImage("/images/caozaiguo.png", mCake.getW(), mCake.getH()));
			break;
		case WHITE:
			//TODO 換成壽桃圖片
			mCake.setImage(mConfig.loadImage("/images/peach.png", mCake.getW(), mCake.getH()));
			break;
		}
	}
}
