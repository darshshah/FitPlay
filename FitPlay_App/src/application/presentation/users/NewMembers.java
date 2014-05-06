package application.presentation.users;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fitplay_app.R;

import entities.User;

public class NewMembers extends Activity implements RemoteDBAdapterDelegate{
	ArrayAdapter<User> adapter;
	ListView listv;
	Button b;
	ArrayList<String> userlist;
	int groupid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_members);
		Intent i = getIntent();
		groupid = i.getIntExtra("groupid", -1);
		userlist = i.getStringArrayListExtra("userlist");
		
		b = (Button) findViewById(R.id.addfriendstoexistinggrp);
		listv = (ListView) findViewById(R.id.listViewfriends);
		
		System.out.println("GROUPIDINADDMEMBERS " + groupid);
		
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
	    
		try {
	    	rdb.fetchRequestWithTypeAndPath("User", "user/index.php");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					SparseBooleanArray checked = listv.getCheckedItemPositions();
			        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
			        for (int i = 0; i < checked.size(); i++) {
			            // Item position in adapter
			            int position = checked.keyAt(i);
			            // Add sport if it is checked i.e.) == TRUE!
			            if (checked.valueAt(i))
			                selectedItems.add(adapter.getItem(position).getId());
			        }
			 
			        
			        RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", NewMembers.this);
			        try {
					    
				        for (int i = 0; i < selectedItems.size(); i++) {
				        	String path = "addUsersToGroupWithId/?user=" + selectedItems.get(i) + "&group=" + groupid ;
						     
						    rdb.PostObjectsOfTypeWithParams("Request", path, null);
				            
				        }
				        Thread.sleep(300);
			        } catch (Exception e) {
						// TODO Auto-generated catch block
				    	 e.printStackTrace();
				    }
			        
			        Intent intent = new Intent();
			        
			        setResult(Activity.RESULT_OK, intent);
			        finish();
			   
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

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		
		ArrayList<User> user2 = (ArrayList<User>) obj;
		ArrayList<User> users = (ArrayList<User>) user2.clone();
		for(User u:user2)
		{
			if (userlist.contains(u.getUsername()))
			{
				users.remove(u);
			}
		}
		
		if (users!=null)
		{
        
        listv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<User>(this, R.layout.simple_list_item_multiple_choice, users);
        listv.setTextFilterEnabled(true);
        listv.setAdapter(adapter);
		}
	}

	

}