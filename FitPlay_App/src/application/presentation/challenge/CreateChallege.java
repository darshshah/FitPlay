//Test
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

import com.example.fitplay_app.R;

public class CreateChallege extends Activity {

	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_challege);
		
		b = (Button) findViewById(R.id.button_add_challenge);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent = new Intent();
			Bundle b = new Bundle();
			DatePicker start = (DatePicker)findViewById(R.id.startdate);
			DatePicker end = (DatePicker)findViewById(R.id.enddate);
			b.putInt("sday", start.getDayOfMonth());
			b.putInt("smonth", start.getMonth());
			b.putInt("syear", start.getYear());
			b.putInt("eday", end.getDayOfMonth());
			b.putInt("emonth", end.getMonth());
			b.putInt("eyear", end.getYear());
			EditText name = (EditText)findViewById(R.id.newchallengename);
			EditText info = (EditText)findViewById(R.id.newchallengeinfo);
			b.putString("newCname", name.getText().toString());
			b.putString("newCinfo", info.getText().toString());
			intent.putExtras(b);
			setResult(Activity.RESULT_OK, intent);
	        finish();
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_challege, menu);
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
