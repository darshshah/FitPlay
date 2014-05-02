package application.presentation.group;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import application.presentation.challenge.CreateChallege;
import application.presentation.challenge.DescribeChallenge;

import com.example.fitplay_app.R;

public class DescribeGroup extends Activity {

	private GridView gview;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_describe_group);

		String testActivities[] = {"10 miles run"," 100 pushups","20 miles walk", "10k swimming"};
	    gview = (GridView) findViewById(R.id.gridViewcha);
        
        adapter = new ArrayAdapter<String>(this, R.layout.activity_main_list_item, R.id.activity_title, testActivities);
        gview.setAdapter(adapter);
        gview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), DescribeChallenge.class);
	            startActivity(intent);
			}
        	
		});
        
		Button button_g = (Button) findViewById(R.id.buttonaddchallenge);
		button_g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), CreateChallege.class);
                startActivity(intent);
            }
        });
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish_challenge, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_finish_challenge, container, false);
			return rootView;
		}
	}

}
