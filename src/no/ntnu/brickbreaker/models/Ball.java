package no.ntnu.brickbreaker.models;

import no.ntnu.brickbreaker.game.Game;

import org.anddev.andengine.entity.primitive.Rectangle;
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
		} else if(this.mX + this.getWidth() > Game.getCAMERA_WIDTH()) {
			this.setVelocityX(-velocity);
		}

		if(this.mY < 0) {
			this.setVelocityY(velocity);
		} else if(this.mY + this.getHeight() > Game.getCAMERA_HEIGHT()) {
			this.setVelocityY(-velocity);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}
	
	public void bounceWithRectangle(Rectangle rectangle){
		float ballPositionX = this.getX();
		float ballPositionY = this.getY();
		float centerVertical = rectangle.getX();
		float westWall = rectangle.getX() - rectangle.getWidth()/2 - this.getWidth()/2;
		float eastWall = rectangle.getX() + rectangle.getWidth()/2 + this.getWidth()/2;
		float centerHorozontal = rectangle.getY();
		float northWall = rectangle.getY() - rectangle.getHeight()/2 - this.getHeight()/2;
		float southWall = rectangle.getY() + rectangle.getHeight()/2 + this.getHeight()/2;
		
		if(ballPositionX >= westWall || ballPositionX <= eastWall) {
			this.setVelocityX(-this.getVelocityX());
		} 
		else {
			this.setVelocityY(-this.getVelocityY());
		}
	}

}
