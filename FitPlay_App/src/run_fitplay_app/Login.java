package run_fitplay_app;

import java.util.ArrayList;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import application.presentation.group.ListGroups;

import com.example.fitplay_app.R;

import entities.User;

public class Login extends ActionBarActivity implements RemoteDBAdapterDelegate{

	protected static final int REGISTER_ACTIVITY = 0;
	public EditText et1,et2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		et1 = (EditText) findViewById(R.id.username);
		et2 = (EditText) findViewById(R.id.password);
		
		Button b = (Button) findViewById(R.id.register);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), Register.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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


	public void CheckLogin(View v)  // Login Butto pressed
	{
		//username - et1.getText().toString();
		//password - et2.getText().toString();
		// authenticate
		
		if( et1.getText().toString().trim().equals(""))
		 {    
			et1.setError( "username is required!" );
			et1.setHint("please enter username");
		 }
		else if( et2.getText().toString().trim().equals(""))
		 {    
			et2.setError( "password is required!" );
			et2.setHint("please enter password");
		 }
		else
		{
		// armando - check with database if the it is correct
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
		
		try {
	    	rdb.fetchRequestWithTypeAndPath("User", "user/index.php?username=" + et1.getText().toString()
	    			+ "&password=" + et2.getText().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		if(obj.size() == 1){
			User user = (User) obj.get(0);
			// Set the user ID in shared preferences 
			//Store something in shared preference
		     SharedPreferences preferences = this.getSharedPreferences("MyPreferences", this.MODE_PRIVATE);  
		     SharedPreferences.Editor editor = preferences.edit();
		     editor.putInt("uid", user.getId());
		     editor.commit();
			
			// Go to the next screen
			Intent intent = new Intent(this, ListGroups.class);
			Bundle b = new Bundle();
			b.putString("username", et1.getText().toString());
			intent.putExtras(b);
			startActivity(intent);
		}
		else{
			//DIALOG
			new AlertDialog.Builder(this)
		    .setTitle("NO NO NO!")
		    .setMessage("Bad username/password combination bro! U a H4x0r?")
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        	dialog.cancel();
		        	
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_alert)
		     .show();
		}
	}
	
	
}
