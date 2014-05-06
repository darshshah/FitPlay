package application.presentation.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitplay_app.R;

public class FinishChallenge extends Activity {

	TextView tv1, tv2;
	Button b;
	EditText et;
	String challangename;
	String challengeinfo;
	int challengeid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_challenge);

		tv1 = (TextView) findViewById(R.id.textViewFinishChallengeName);
		tv2 = (TextView) findViewById(R.id.textViewFinishChallengeInfo);
		b = (Button) findViewById(R.id.buttondone);
		et = (EditText) findViewById(R.id.editTextComments);
		
		
	    Bundle bundle = getIntent().getExtras();
	    
	    challangename = bundle.getString("ChallengeName");
	    challengeinfo = bundle.getString("ChallengeInfo");
	    challengeid = bundle.getInt("ChallengeId");
	    		
		tv1.setText(challangename);
	    tv2.setText(challengeinfo);
	    
	    b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				System.out.println("COMMENT" + et.getText().toString());
				Intent intent = new Intent();
				Bundle b = new Bundle();
				
				b.putString("COMMENT", et.getText().toString());
				
				intent.putExtras(b);
				setResult(Activity.RESULT_OK, intent);
		        finish();
				
			}
		});
	    
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


}
