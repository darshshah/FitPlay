package application.presentation.group;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.fitplay_app.R;

import entities.Group;

public class ListGroups extends Activity implements RemoteDBAdapterDelegate {

	private static final int ACTIVITY_GET_NEW_GROUP = 999;
	private GridView gview;
	private ArrayAdapter<Group> adapter;
	private ArrayList<Group> grp;
	
	ArrayList<String> grpnames = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_groups);
		
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
		// Store/Get something in shared preference
	     SharedPreferences preferences = this.getSharedPreferences("MyPreferences", this.MODE_PRIVATE);  
	     // Read data from shared preferences 
	     int user_id = preferences.getInt("uid", 0); 

		rdb.fetchRequestWithTypeAndPath("Group", "groupsOfUser/index.php?id=" + user_id);
	
		
		
			
		Button button_g = (Button) findViewById(R.id.button_add_group);
		button_g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), NewGroup.class);
            	startActivityForResult(intent, ACTIVITY_GET_NEW_GROUP );
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_groups, menu);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   // See which sub activity has finished   

	if (resultCode != Activity.RESULT_CANCELED) {
		switch (requestCode) {     
		
		case ACTIVITY_GET_NEW_GROUP:       
			
			if (resultCode == Activity.RESULT_OK) {         
				 
				 Bundle b = data.getExtras();
			     int[] resultArr = b.getIntArray("selectedItems");
			     
			     // send new grp info to database
			     
			     Group g = new Group(b.getString("GrpName"));
			     
			     //Store something in shared preference
			     SharedPreferences preferences = this.getSharedPreferences("MyPreferences", this.MODE_PRIVATE);  
			     


			     // Read data from shared preferences 
			     int user_id = preferences.getInt("uid", 0); 
			     
			     
			     g.setOwner_id(user_id);
			     
			     RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
			     try {
						rdb.PostObjectsOfTypeWithParams("Group", "group/index.php", g);
						Thread.sleep(200);
				     } catch (Exception e) {
						// TODO Auto-generated catch block
				    	 e.printStackTrace();
				     }
			    
			    try {
				    String path = "addUsersToGroup/?group=" + g.getGname()+ "&user=" + user_id ;
				    rdb.PostObjectsOfTypeWithParams("Request", path, null);
				    for(int i : resultArr){
					     path = "addUsersToGroup/?group=" + g.getGname()+ "&user=" + i ;
					     
					     rdb.PostObjectsOfTypeWithParams("Request", path, null);
					     
				     }
			    } catch (Exception e) {
					// TODO Auto-generated catch block
			    	 e.printStackTrace();
			    }
			    
			    try {
					Thread.sleep(200);
					rdb.fetchRequestWithTypeAndPath("Group", "groupsOfUser/index.php?id=" + user_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			     //grp.add(g);
			     //adapter.notifyDataSetChanged();
			     			     
			    
			     
				}          
			    
			break;
		default:         
				break;     
		}   
	}	
	}
	
	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		grp = (ArrayList<Group>) obj;
		
		gview = (GridView) findViewById(R.id.gridView1);
        adapter = new ArrayAdapter<Group>(this, R.layout.activity_main_list_item, R.id.activity_title, grp);
        gview.setAdapter(adapter);
        gview.setOnItemClickListener( new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int pos, long id)
        	{
        	
        	Intent intent = new Intent(v.getContext(), GroupData.class);
        	Bundle b = new Bundle();
        	int value = grp.get(pos).getGid();
           	b.putInt("SelectedGrpID", value);
        	intent.putExtras(b);
            startActivity(intent);
        	}
		});
	}
	


}
