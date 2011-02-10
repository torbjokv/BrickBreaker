package no.ntnu.jump;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;


public class Ball extends AnimatedSprite {
	float velocity = 100;
	
	public Ball(float positionX, float positionY, TiledTextureRegion positionTextureRegion) {
		super(positionX, positionY, positionTextureRegion);
	}

	
	protected void onManagedUpdate(final float pSecondsElapsed) {
		if(this.mX < 0) {
			this.setVelocityX(velocity);
		} else if(this.mX + this.getWidth() > GameMain.getCAMERA_WIDTH()) {
			this.setVelocityX(-velocity);
		}

		if(this.mY < 0) {
			this.setVelocityY(velocity);
		} else if(this.mY + this.getHeight() > GameMain.getCAMERA_HEIGHT()) {
			this.setVelocityY(-velocity);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}
}
