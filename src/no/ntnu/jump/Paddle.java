package no.ntnu.jump;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * 	
 * @author kristoffer
 * To register paddle use: scene.registerTouchArea(a paddle);
 */
public class Paddle extends AnimatedSprite{

	public Paddle(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
		super(pX, pY, pTiledTextureRegion);
		// TODO Auto-generated constructor stub
	}

	protected void onManagedUpdate(final float pSecondsElapsed) {
		
	}
	
	public boolean onAreaTouched(TouchEvent sceneTouchEvent, float touchAreaLocalX, float touchAreaLocalY) {
		this.setPosition(sceneTouchEvent.getX() - this.getWidth() / 2, GameMain.getCAMERA_HEIGHT()-30);
		return true;
	}
}
