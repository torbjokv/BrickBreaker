package no.ntnu.brickbreaker;

import java.util.Observable;



public class GameHolder extends Observable {
	
	private static GameHolder instance = new GameHolder();
	
	private GameState gameState;
	private GameState inGameState = new InGameState(this);
	private GameState newGameState = new NewGameState(this);
	private GameState pausedGameState = new PausedGameState(this);
	
	private GameHolder() {
		super();
		gameState = newGameState;
	}

	public static GameHolder getInstance() {
		if(instance == null) {
			instance = new GameHolder();
		}
		return instance;
	}

	public void startGame() {
		gameState.startGame();
	}

	public void pauseGame() {
		gameState.pauseGame();
	}
	
	public void resumeGame() {
		gameState.resumeGame();
	}

	public void killGame() {
		gameState.killGame();
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		notifyObservers();
	}

	public GameState getGameState() {
		notifyAll();
		return gameState;
	}
	
	public GameState getNewGameState() {
		return newGameState;
	}

	public void setNewGameState(GameState newGameState) {
		this.newGameState = newGameState;
	}

	public GameState getPausedGameState() {
		return pausedGameState;
	}

	public void setPausedGameState(GameState pausedGameState) {
		this.pausedGameState = pausedGameState;
	}

	public void setInGameState(GameState inGameState) {
		this.inGameState = inGameState;
	}

	public GameState getInGameState() {
		return inGameState;
	}
	
}
