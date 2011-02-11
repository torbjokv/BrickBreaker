package no.ntnu.brickbreaker.models;

import no.ntnu.brickbreaker.GameHolder;
import no.ntnu.brickbreaker.game.Game;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * 	
 * @author kristoffer
 * To register paddle use: scene.registerTouchArea(a paddle);
 */
public class Paddle extends Sprite{

	public Paddle(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		// TODO Auto-generated constructor stub
	}

	protected void onManagedUpdate(final float pSecondsElapsed) {
		
	}
	
	public boolean onAreaTouched(TouchEvent sceneTouchEvent, float touchAreaLocalX, float touchAreaLocalY) {
		this.setPosition(sceneTouchEvent.getX() - this.getWidth() / 2, Game.getCAMERA_HEIGHT()-30);
		return true;
	}
}
