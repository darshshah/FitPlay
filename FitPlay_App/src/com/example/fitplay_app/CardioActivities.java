package com.example.fitplay_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CardioActivities extends ActionBarActivity {

	private ListView listv;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardio_activities);
		
		// Setup the + button
		Button log = (Button) findViewById(R.id.button_add_c);
		
		log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), NewCardioActivity.class);
                startActivity(intent);
            }
        });
		
		// Setup ArrayAdapter and ListView
		String testActivities[] = {"Cardio Log 1","Cardio Log 2","Cardio Log 3", "Cardio Log 4"};
        listv = (ListView) findViewById(R.id.list1);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_main_list_item, R.id.activity_title, testActivities);
        listv.setAdapter(adapter);
        
        listv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent intent = new Intent(view.getContext(), LogCardio.class);
                startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cardio_activities, menu);
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
