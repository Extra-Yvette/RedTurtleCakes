package yvette.game.model.scenes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import yvette.game.Config;
import yvette.game.model.ClickableRole;
import yvette.game.model.Role;

/**
 * 遊戲場景
 * 
 * @author yvette
 *
 */
public class Scenes extends Role {
	protected Config mConfig;
	private List<Role> mRoles;
	private Role mMouse;
	private OnChangeScenesListener mOnChangeScenesListener;

	public Scenes() {
		mRoles = new CopyOnWriteArrayList<Role>();

		// 場景建立時，預設的滑鼠感應區大小
		mMouse = new Role();
		mMouse.setEnableCenter(true);
		mMouse.setW(10);
		mMouse.setH(10);
	}

	public void setMouseRole(Role mouse) {
		mMouse = mouse;
	}

	public void setOnChangeScenesListener(OnChangeScenesListener listener) {
		mOnChangeScenesListener = listener;
	}

	/**
	 * 請求切換場景
	 * 
	 * @param scenesName
	 *            場景名稱
	 */
	public void changeScenes(String scenesName) {
		if (mOnChangeScenesListener != null) {
			mOnChangeScenesListener.onChangeScenes(scenesName);
		}
	}

	/**
	 * 切換場景，場景將被顯示在畫面上
	 */
	public void onLoad() {
		System.out.println("onLoad" + getClass().getName());
	}

	/**
	 * 切換場景，場景將要從顯示畫面上移除
	 */
	public void onUnload() {
		System.out.println("onUnload" + getClass().getName());
	}

	/**
	 * 將角色加到場景上
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		mRoles.add(role);
	}

	/**
	 * 將角色從場景上移除
	 * 
	 * @param role
	 */
	public void removeRole(Role role) {
		mRoles.remove(role);
	}

	public void clearRoles() {
		mRoles.clear();
	}

	public List<Role> getRoles() {
		return mRoles;
	}

	@Override
	public void onDraw(Graphics canvas) {
		// 繪制全部場景上的角色
		for (Role obj : mRoles) {
			obj.onDraw(canvas);
		}

		for (int i = mRoles.size() - 1; i >= 0; i--) {
			Role obj = mRoles.get(i);
			// 若場景上角色已不存活，將它移除
			if (!obj.isALive()) {
				mRoles.remove(obj);
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		mMouse.setX(e.getX());
		mMouse.setY(e.getY());
	}

	public void mouseClicked(MouseEvent e) {
		// 滑鼠點了左鍵
		if (e.getButton() == MouseEvent.BUTTON1) {
			// 判斷畫面上有哪個角色與滑鼠碰撞
			for (Role role : mRoles) {
				if (role instanceof ClickableRole) {
					ClickableRole clickRole = (ClickableRole) role;

					if (role.hitTest(mMouse)) {
						clickRole.onClick();
						break;
					}
				}
			}
		}
	}
}
