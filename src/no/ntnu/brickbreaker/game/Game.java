package no.ntnu.brickbreaker.game;

import no.ntnu.brickbreaker.models.Paddle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.badlogic.gdx.math.Vector2;

import android.hardware.SensorManager;
import android.view.Display;
import no.ntnu.brickbreaker.models.*;

public class Game extends BaseGameActivity{
	private static int CAMERA_HEIGHT = 480;
	private static int CAMERA_WIDTH = 800;
	private Camera mCamera;
	
	private TextureRegion paddleTextureRegion;
//	private TextureRegion brickTextureRegion;
	private TiledTextureRegion ballTextureRegion;
	
	private Texture mTexture;
	
	private PhysicsWorld mPhysicsWorld;

public Engine onLoadEngine() {
		
		final Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
		CAMERA_WIDTH = defaultDisplay.getWidth();
		CAMERA_HEIGHT = defaultDisplay.getHeight();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

	@Override
	public void onLoadResources() {
		this.mTexture = new Texture(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegionFactory.setAssetBasePath("gfx/");
		
		this.paddleTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "paddle.png", 0, 0);
		this.ballTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "ball.png", 0, 0, 1, 1);
//		this.brickTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "brick.png", 0, 0);
		
		this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(1);
		scene.setBackground(new ColorBackground(0f, 0f, 0f));

//		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		/* Running man */
//		final AnimatedSprite runningman = new AnimatedSprite(200, 180, this.mPlayerTextureRegion);
		final Paddle paddle = new Paddle(CAMERA_WIDTH/2, CAMERA_HEIGHT-80, this.paddleTextureRegion);
//		final Brick[][] bricks = new Brick[5][2];
			
		final Ball ball = new Ball(CAMERA_WIDTH/2, CAMERA_HEIGHT-82, this.ballTextureRegion);
		
		scene.getTopLayer().addEntity(paddle);
		scene.getTopLayer().addEntity(ball);
		
//		scene.registerUpdateHandler(this.mPhysicsWorld);
		
		scene.registerTouchArea(paddle);
		
		
		/* The actual collision-checking. */
		scene.registerUpdateHandler(new IUpdateHandler() {

			
			public void reset() { }

			
			public void onUpdate(final float pSecondsElapsed) {
				if(ball.collidesWith(paddle)) {
					ball.paddleReverse();
				} else {
//					centerRectangle.setColor(0, 1, 0);
				}
			}
		});
		
		return scene;
	}
	
	public static int getCAMERA_HEIGHT() {
		return CAMERA_HEIGHT;
	}
	public static int getCAMERA_WIDTH() {
		return CAMERA_WIDTH;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}


}
