package no.ntnu.brickbreaker.menu;

import no.ntnu.brickbreaker.GameObserver;
import no.ntnu.brickbreaker.GameState;
import no.ntnu.brickbreaker.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;;

public class Menu extends ListActivity implements GameObserver {
	static final String[] ITEMS = new String[] {"Afghanistan", "Albania", "Algeria"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  setListAdapter(new ArrayAdapter<String>(this, R.layout.menu, ITEMS));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		          Toast.LENGTH_SHORT).show();
		    }
		  });
	}
	@Override
	public void update(GameState gameState) {
		// TODO Auto-generated method stub
		
	}
	
}
