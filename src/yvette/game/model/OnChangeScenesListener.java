package yvette.game.model;

public interface OnChangeScenesListener {
	/**
	 * 當有場景切換事件時
	 * @param scenesName 即將前往的場景名稱
	 */
	public void onChangeScenes(String scenesName);
}
