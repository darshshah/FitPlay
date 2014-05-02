package application.presentation.group;

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
import android.widget.EditText;
import android.widget.ListView;

import com.example.fitplay_app.R;

public class NewGroup extends Activity {

	protected static final int ACTIVITY_GET_FREINDS = 1;
	Button b, d;
	EditText et;
	ListView listv;
	ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_group);

		et = (EditText) findViewById(R.id.newgroupname);
		d = (Button) findViewById(R.id.doneaddingmembers);
		
		String testActivities[] = {"Darsh","Armando","Anish", "Tennis", "a", "b"};
        listv = (ListView) findViewById(R.id.listnewgroup);
        listv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, testActivities);
        listv.setTextFilterEnabled(true);
        listv.setAdapter(adapter);
        

	     d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Go back to the parent screen
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
			        
			        System.out.println(et.getText().toString());
			        Intent intent = new Intent();
			        Bundle b = new Bundle();
			        b.putStringArray("selectedItems", outputStrArr);
			        b.putString("GrpName", et.getText().toString() );
			        intent.putExtras(b);
			        setResult(Activity.RESULT_OK, intent);
			        finish();
			   
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_group, menu);
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
