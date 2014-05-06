package application.presentation.users;

import ws.remote.DownloadImageTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitplay_app.R;

public class ShowUserInfo extends ActionBarActivity {

	TextView tv1, tv2;
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_user_info);

		tv1 = (TextView) findViewById(R.id.showUsername);
		tv2 = (TextView) findViewById(R.id.showName);
		iv = (ImageView) findViewById(R.id.showImageUserInfo);
		
		Bundle b = getIntent().getExtras();
		
		tv1.setText(b.getString("uname"));
		tv2.setText(b.getString("name"));
		
		new DownloadImageTask(iv).execute("http://ec2-54-86-107-60.compute-1.amazonaws.com/imageUpload/" + b.getString("uname") + ".jpg");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_user_info, menu);
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
