package application.presentation.group;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.fitplay_app.R;

import entities.Group;
import entities.User;

public class NewGroup extends Activity implements RemoteDBAdapterDelegate {

	protected static final int ACTIVITY_GET_FREINDS = 1;
	Button b, d;
	EditText et;
	ListView listv;
	ArrayAdapter<User> adapter;
	ArrayList<User> usr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_group);

		et = (EditText) findViewById(R.id.newgroupname);
		d = (Button) findViewById(R.id.doneaddingmembers);
		

		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
		
		try {
			rdb.getAllObjectsOfType("User","user");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
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

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		usr = (ArrayList<User>) obj;
		listv = (ListView) findViewById(R.id.listnewgroup);
        listv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<User>(this, R.layout.simple_list_item_multiple_choice, usr);
        listv.setTextFilterEnabled(true);
        listv.setAdapter(adapter);
        

	     d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Go back to the parent screen
				
				if( et.getText().toString().trim().equals(""))
				 {    
					et.setError( "group name is required!" );
					et.setHint("please enter group name");
				 }
				else
				{
				  SparseBooleanArray checked = listv.getCheckedItemPositions();
			        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
			        for (int i = 0; i < checked.size(); i++) {
			            // Item position in adapter
			            int position = checked.keyAt(i);
			            // Add sport if it is checked i.e.) == TRUE!
			            if (checked.valueAt(i))
			                selectedItems.add(adapter.getItem(position).getId());
			        }
			 
			        int[] outputStrArr = new int[selectedItems.size()];
			 
			        for (int i = 0; i < selectedItems.size(); i++) {
			            outputStrArr[i] = selectedItems.get(i);
			            System.out.println("ITEMSdd " + outputStrArr[i] );
			        }
			        
			        System.out.println(et.getText().toString());
			        Intent intent = new Intent();
			        Bundle b = new Bundle();
			        b.putIntArray("selectedItems", outputStrArr);
			        b.putString("GrpName", et.getText().toString() );
			        intent.putExtras(b);
			        setResult(Activity.RESULT_OK, intent);
			        finish();
				}
			}
		});
	
	}
		
	}


