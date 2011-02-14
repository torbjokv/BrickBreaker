package no.ntnu.brickbreaker.menu;

import java.util.Observable;
import java.util.Observer;

import no.ntnu.brickbreaker.GameHolder;
import no.ntnu.brickbreaker.R;
import no.ntnu.brickbreaker.game.Game;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity implements Observer {
	private GameHolder gameHolder;
	private Button newGameButton;
	private Button resumeButton;
	private Button exitButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		gameHolder = GameHolder.getInstance();
		gameHolder.addObserver(this);
		
  	    super.onCreate(savedInstanceState);
  	    setContentView(R.layout.menu1);
	  
  	    newGameButton = (Button)this.findViewById(R.id.newGameButton);
  	    
  	    newGameButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
	        Intent gameIntent = new Intent(v.getContext(), Game.class);
	        gameHolder.setGameIntent(gameIntent);
	        startActivity(gameIntent);
	        gameHolder.setGameState(gameHolder.getInGameState());
		}
	  });
  	    
  	    resumeButton = (Button)this.findViewById(R.id.resumeButton);
	    
  	   resumeButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(gameHolder.getGameState() == gameHolder.getNewGameState()) {
				Toast.makeText(v.getContext(), "There is no game to resume.", 3).show();
			} else {
				Toast.makeText(v.getContext(), "Not implemented.", 3).show();
				//startActivityIfNeeded(gameHolder.getGameIntent(), Intent.FLAG_ACTIVITY_SINGLE_TOP);
//				startActivityFromChild(gameHolder.getGameActivity(), gameHolder.getGameIntent(), Intent.FLAG_ACTIVITY_SINGLE_TOP);
//				gameHolder.setGameState(gameHolder.getInGameState());
			}
		}
	  });
  	    
  	 exitButton = (Button)this.findViewById(R.id.exitButton);
	    
  	exitButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
	        finish();
		}
	  });
  	   
	}
	
	@Override
	public void update(Observable observable, Object data) {
		GameHolder gameHolder = (GameHolder)observable;
		gameHolder.getGameState();
	}
	
}
