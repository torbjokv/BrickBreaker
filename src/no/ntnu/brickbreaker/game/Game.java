package no.ntnu.brickbreaker.game;

import no.ntnu.brickbreaker.models.Ball;
import no.ntnu.brickbreaker.models.Brick;
import no.ntnu.brickbreaker.models.Paddle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;

public class Game extends BaseGameActivity implements IOnSceneTouchListener{
	private static int CAMERA_HEIGHT = 480;
	private static int CAMERA_WIDTH = 800;
	private Camera mCamera;


	private TiledTextureRegion ballTextureRegion;

	private Texture paddleTexture;
	private Texture ballTexture;
	private Paddle paddle;



	public Engine onLoadEngine() {

		final Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
		CAMERA_WIDTH = defaultDisplay.getWidth();
		CAMERA_HEIGHT = defaultDisplay.getHeight();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

	@Override
	public void onLoadResources() {
		this.ballTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.paddleTexture = new Texture(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegionFactory.setAssetBasePath("gfx/");

		this.ballTextureRegion = TextureRegionFactory.createTiledFromAsset(this.ballTexture, this, "ball.png", 0, 0, 1, 1);

		this.mEngine.getTextureManager().loadTexture(this.paddleTexture);
		this.mEngine.getTextureManager().loadTexture(this.ballTexture);
	}




	@Override
	public Scene onLoadScene() {


		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(1);
		scene.setBackground(new ColorBackground(0f, 0f, 0f));
		scene.setOnSceneTouchListener(this);

		final Ball ball = new Ball(CAMERA_WIDTH/2, CAMERA_HEIGHT-80, this.ballTextureRegion);
		ball.setVelocity(100.0f, 100.0f);

		scene.getTopLayer().addEntity(ball);
		
		paddle = new Paddle(CAMERA_WIDTH/2, CAMERA_HEIGHT-30, 64, 16);

		final Brick[][] bricks = new Brick[5][5];

		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				bricks[i][j]= new Brick(CAMERA_WIDTH-6*(CAMERA_WIDTH/6)+i*70, CAMERA_HEIGHT-4*(CAMERA_HEIGHT/5)-j*20, 30, 16);
				scene.getTopLayer().addEntity(bricks[i][j]);
			}
		}

		scene.getTopLayer().addEntity(paddle);
		scene.getTopLayer().addEntity(ball);
		scene.registerTouchArea(paddle);

		/* The actual collision-checking. */
		scene.registerUpdateHandler(new IUpdateHandler() {
			public void reset() { }

			public void onUpdate(final float pSecondsElapsed) {
					for (int i = 0; i < bricks.length; i++) {
						for (int j = 0; j < bricks[0].length; j++) {
							scene.setBackground(new ColorBackground(0f, 0f, 0f));
							if(ball.collidesWith(bricks[i][j])) {
								scene.getTopLayer().removeEntity(bricks[i][j]);
							}
						}
					}
					if(ball.collidesWith(paddle)) {
						ball.paddelBounce();
					}
					if(ball.getY() >= Game.getCAMERA_HEIGHT() - 30) {
						scene.setBackground(new ColorBackground(255f, 0f, 0f));
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

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		paddle.setPosition(pSceneTouchEvent.getX() - paddle.getWidth() / 2, Game.getCAMERA_HEIGHT()-30);
		return true;
	}


}
