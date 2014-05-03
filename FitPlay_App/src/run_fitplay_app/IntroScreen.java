package run_fitplay_app;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.fitplay_app.R;

import entities.Log;

public class IntroScreen extends Activity implements RemoteDBAdapterDelegate {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro_screen);
		
		Button button_ft = (Button) findViewById(R.id.button_login);
		button_ft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), SecondScreen.class);
                startActivity(intent);
            }
        });
		
		RemoteDBAdapter adapter = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
		try {
			adapter.getAllObjectsOfType("Log","log/index.php");
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
		getMenuInflater().inflate(R.menu.intro_screen, menu);
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
	private ArrayList<Log> logs;
	
	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
			logs = (ArrayList<Log>) obj;
		
	}



}
