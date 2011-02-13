package no.ntnu.brickbreaker.menu;

import java.util.Observable;
import java.util.Observer;

import no.ntnu.brickbreaker.GameHolder;
import no.ntnu.brickbreaker.R;
import no.ntnu.brickbreaker.game.Game;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity implements Observer {
	GameHolder gameHolder;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		gameHolder = GameHolder.getInstance();
		gameHolder.addObserver(this);
		
		  super.onCreate(savedInstanceState);
		
		  String[] countries = getResources().getStringArray(R.array.menu_items);
		  setListAdapter(new ArrayAdapter<String>(this, R.layout.menu, countries));
		  
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		
		  lv.setOnItemClickListener(new OnItemClickListener() {
		    @Override
			public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	Intent gameIntent = new Intent(view.getContext(), Game.class);
		        startActivity(gameIntent);
		    }
		  });
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
	
}
