package application.presentation.users;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import application.presentation.group.NewGroup;

import com.example.fitplay_app.R;

public class NewMembers extends Activity {
	ArrayAdapter<String> adapter;
	ListView listv;
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_members);
	
		String testActivities[] = {"Darsh","Armando","Anish", "Tennis"};
        listv = (ListView) findViewById(R.id.listViewfriends);
        listv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, testActivities);
        listv.setTextFilterEnabled(true);
        listv.setAdapter(adapter);
        
        b = (Button) findViewById(R.id.button_fb_add);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			       SparseBooleanArray checked = listv.getCheckedItemPositions();
			        ArrayList<String> selectedItems = new ArrayList<String>();
			        for (int i = 0; i < checked.size(); i++) {
			            // Item position in adapter
			            int position = checked.keyAt(i);
			            // Add sport if it is checked i.e.) == TRUE!
			            if (checked.valueAt(i))
			                selectedItems.add(adapter.getItem(position));
			        }
			 
			        String[] outputStrArr = new String[selectedItems.size()];
			 
			        for (int i = 0; i < selectedItems.size(); i++) {
			            outputStrArr[i] = selectedItems.get(i);
			            System.out.println("ITEMSdd " + outputStrArr[i] );
			        }
			 
			        //Intent intent = new Intent(getApplicationContext(), NewGroup.class);
			        //Intent intent = new Intent();
			        // Create a bundle object
			        //Bundle b = new Bundle();
			        //b.putStringArray("selectedItems", outputStrArr);
			     
			        //setResult(Activity.RESULT_OK, intent);
			        finish();
			   
			        //startActivity(intent);
			}
		});
      
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_members, menu);
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