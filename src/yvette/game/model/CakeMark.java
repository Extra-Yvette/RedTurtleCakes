package yvette.game.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * 紅龜粿模具
 * 
 * @author yvette
 *
 */
public class CakeMark extends ClickableRole {

	@Override
	public void onDraw(Graphics canvas) {
		if (getImage() != null) {
			canvas.drawImage(getImage(), getX(), getY(), new ImageObserver() {

				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}

			});
		} else {
			super.onDraw(canvas);
		}
	}
}
