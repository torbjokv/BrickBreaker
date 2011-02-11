package no.ntnu.brickbreaker.models;

import no.ntnu.brickbreaker.GameHolder;

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
		} else if(this.mX + this.getWidth() > GameHolder.getCAMERA_WIDTH()) {
			this.setVelocityX(-velocity);
		}

		if(this.mY < 0) {
			this.setVelocityY(velocity);
		} else if(this.mY + this.getHeight() > GameHolder.getCAMERA_HEIGHT()) {
			this.setVelocityY(-velocity);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}
	
	public void wallReverse(){
		this.setVelocity(-this.getVelocityX(), this.getVelocityY());

	}

	public void paddleReverse(){
		this.setVelocity(this.getVelocityX()+20, -this.getVelocityY()+20);

	}
}
