package no.ntnu.jump;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Game extends BaseGameActivity{
	private static int CAMERA_HEIGHT = 320;
	private static int CAMERA_WIDTH = 480;
	private Camera mCamera;
	private TiledTextureRegion mPlayerTextureRegion;
	private Texture mTexture;
	


	public static Game getInstance(BaseGameActivity bga){
		if(instance == null){
			return new Game(bga);
		}
		return instance;
	}


	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
