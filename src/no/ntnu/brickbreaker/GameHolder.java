package no.ntnu.brickbreaker;

import android.content.Intent;
import android.database.Observable;


public class GameHolder extends Observable<GameObserver>{
	
	private static GameHolder instance = new GameHolder();
	
	private GameHolder() {
		super();
	}
	
	public static GameHolder getInstance() {
		if(instance == null) {
			return new GameHolder();
		}
		return instance;
	}
	
	
}
