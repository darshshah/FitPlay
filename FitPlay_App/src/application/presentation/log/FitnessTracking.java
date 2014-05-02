package application.presentation.log;


import com.example.fitplay_app.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import application.presentation.activites.ChooseActivityType;

public class FitnessTracking extends ActionBarActivity {

	private ListView listv;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fitness_tracking);
		
		
		
		// Setup the + button
		Button subscribe = (Button) findViewById(R.id.button_add_strength);
		
		subscribe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), ChooseActivityType.class);
                startActivity(intent);
            }
        });
		
		// Setup ArrayAdapter and ListView
		String testActivities[] = {"Log1: abd for time x"," Log1: lol for time y","something something", "lol"};
        listv = (ListView) findViewById(R.id.list1);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_main_list_item, R.id.activity_title, testActivities);
        listv.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fitness_tracking, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
