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
	        startActivity(gameIntent);
		}
	  });
  	    
  	    resumeButton = (Button)this.findViewById(R.id.resumeButton);
	    
  	   resumeButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
	        System.out.println("Not implemented");
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
		// TODO Auto-generated method stub
		
	}
	
}
