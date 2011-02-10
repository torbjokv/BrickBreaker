package no.ntnu.brickbreaker.game;

import no.ntnu.brickbreaker.models.Paddle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;

public class Game extends BaseGameActivity{
	private static int CAMERA_HEIGHT = 320;
	private static int CAMERA_WIDTH = 480;
	private Camera mCamera;
	
	private TiledTextureRegion mPlayerTextureRegion;
	private TextureRegion paddleTextureRegion;
	private Texture mTexture;
	

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

		this.mPlayerTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "knightRun.png", 0, 0, 10, 8);
		
		this.paddleTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "gfx/paddle.png", CAMERA_HEIGHT/2, CAMERA_WIDTH/2);
		
		this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(1);
		scene.setBackground(new ColorBackground(0f, 0f, 0f));

		/* Running man */
//		final AnimatedSprite runningman = new AnimatedSprite(200, 180, this.mPlayerTextureRegion);
		final Sprite paddle = new Paddle(CAMERA_WIDTH/2, CAMERA_HEIGHT-30, this.paddleTextureRegion);
		
		scene.getTopLayer().addEntity(paddle);

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
