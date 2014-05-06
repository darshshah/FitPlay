package application.presentation.challenge;

import java.util.ArrayList;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import application.presentation.users.ShowUserInfo;

import com.example.fitplay_app.R;

import entities.User;

public class DescribeChallenge extends Activity implements RemoteDBAdapterDelegate{

	protected static final int FINISHED_CHALLENGE = 996;
	Button b;
	TextView  tv1, tv2;
	String challangename;
	String challengeinfo;
	int challengeid;
	ListView listv;
	
	ArrayAdapter<User> adapter;
	ArrayList<User> users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_describe_challenge);
		tv1 = (TextView) findViewById(R.id.textViewDescribeChallengeName);
		tv2 = (TextView) findViewById(R.id.textViewDescribeChallengeInfo);
	    Bundle bundle = getIntent().getExtras();
	    
	    challangename = bundle.getString("ChallengeName");
	    challengeinfo = bundle.getString("ChallengeInfo");
	    challengeid = bundle.getInt("ChallengeId");
	    		
		tv1.setText(challangename);
	    tv2.setText(challengeinfo);
	    
	    
		b = (Button) findViewById(R.id.challengeCompleted);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(v.getContext(), FinishChallenge.class);
//				Bundle b = new Bundle();
//				b.putInt("ChallengeId", challengeid);
//				b.putString("ChallengeName", challangename);
//				b.putString("ChallengeInfo", challengeinfo);
//				intent.putExtras(b);
//				
//				startActivityForResult(intent, FINISHED_CHALLENGE);

			     // Read data from shared preferences 
			     SharedPreferences preferences = DescribeChallenge.this.getSharedPreferences("MyPreferences", DescribeChallenge.this.MODE_PRIVATE);  
			     int user_id = preferences.getInt("uid", 0); 
				//POST
				RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com",DescribeChallenge.this);
			    
				try {
			    	rdb.fetchRequestWithTypeAndPath("User", "addChallengeCompletion/index.php?challenge_id=" + 
			    			Integer.toString(DescribeChallenge.this.challengeid) + "&user_id=" + user_id );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//DIALOG
				new AlertDialog.Builder(DescribeChallenge.this)
			    .setTitle("Challenge Completed")
			    .setMessage("Congratulations bro!")
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	dialog.cancel();
			        	DescribeChallenge.this.finish();
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
				
				//EXIT
                //finish();
			}
		});
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com",DescribeChallenge.this);
	    
		try {
	    	rdb.fetchRequestWithTypeAndPath("User", "usersForChallenge/index.php?id=" + 
	    			Integer.toString(DescribeChallenge.this.challengeid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.describe_challenge, menu);
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
		users = (ArrayList<User>) obj;
		SharedPreferences preferences = DescribeChallenge.this.getSharedPreferences("MyPreferences", DescribeChallenge.this.MODE_PRIVATE);  
	    int user_id = preferences.getInt("uid", 0); 
		for(User user : users){
			if(user.getId() == user_id){
				b = (Button) findViewById(R.id.challengeCompleted);
				b.setVisibility(View.GONE);
			}
		}
		
		if(users != null)
		{
		listv = (ListView) findViewById(R.id.listusersdone);
        adapter = new ArrayAdapter<User>(this, R.layout.activity_main_list_item, R.id.activity_title, users);
        listv.setAdapter(adapter);
        
        listv.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
        	
        		Intent intent = new Intent(v.getContext(), ShowUserInfo.class);
        		Bundle b = new Bundle();
        		b.putString("uname", users.get(pos).getUsername());
        		b.putString("name", users.get(pos).getName());
        		intent.putExtras(b);
        		startActivity(intent);
        	}
		});
		}
		}
}
