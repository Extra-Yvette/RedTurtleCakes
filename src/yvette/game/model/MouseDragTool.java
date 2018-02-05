package yvette.game.model;


/**
 * 
 * 滑鼠選擇工具後，會拖曳住的工具角色
 * 
 * @author yvette
 *
 */
public class MouseDragTool extends ClickableRole {
	private CakeType mType;

	/**
	 * 設定目前選擇的模具可以用於哪種粿type
	 * 
	 * @param type
	 */
	public void setCakeType(CakeType type) {
		mType = type;
	}

	/**
	 * 取得目前選擇的模具可以用於哪種粿type
	 * 
	 * @return
	 */
	public CakeType getCakeType() {
		return mType;
	}
}
