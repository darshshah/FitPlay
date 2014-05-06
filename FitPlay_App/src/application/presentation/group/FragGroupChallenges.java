package application.presentation.group;


import java.util.ArrayList;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import application.presentation.challenge.CreateChallege;
import application.presentation.challenge.DescribeChallenge;

import com.example.fitplay_app.R;

import entities.Challenge;

public class FragGroupChallenges extends Fragment implements RemoteDBAdapterDelegate {
	
	protected static final int ADD_NEW_CHALLENGE = 998;
	private GridView gview;
	private ArrayAdapter<Challenge> adapter;
	View rootView;
	int groupid;
	ArrayList<Challenge> carray;
	

	public View onCreateView(LayoutInflater inflator, ViewGroup container, 
			Bundle savedInstanceState)
	{
		groupid = getArguments().getInt("SelectedGrpID");
		System.out.println("IDIS" + groupid);
		rootView= inflator.inflate(R.layout.activity_describe_group, container, false);
		
		// challenges will be populated with the groupid
		String testActivities[] = {"10 miles run"," 100 pushups","20 miles walk", "10k swimming"};
	    
		// get challenges from server for a given group id
		// armando
		
		
	    RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
	    
		try {
	    	rdb.fetchRequestWithTypeAndPath("Challenge", "challengesOfGroup/index.php?id=" + Integer.toString(groupid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		Button button_g = (Button) rootView.findViewById(R.id.buttonaddchallenge);
		button_g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), CreateChallege.class);
                startActivityForResult(intent, ADD_NEW_CHALLENGE);
            }
        });
		
		return rootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {   // See which sub activity has finished   

	if (resultCode != Activity.RESULT_CANCELED) {
		switch (requestCode) {     
		
		case ADD_NEW_CHALLENGE:  
		// add new data to the database for that grp
			if (resultCode == Activity.RESULT_OK)
			{
			Bundle b = data.getExtras();
			b.getInt("sday");
			b.getInt("smonth");
			b.getInt("syear");
			b.getInt("eday");
			b.getInt("emonth");
			b.getInt("eyear");
			
			b.getString("newCname");
			b.getString("newCinfo");
			
			// dummy code since we dont know the challenge id as this is a new challenge
			Challenge c = new Challenge(b.getString("newCname"), b.getString("newCinfo"), groupid);
			
			//
			// make a post request to the database to add new challenge
			// armando
			
			// POST THE CHALLENGE
			
			//carray.add(c);
		    //adapter.notifyDataSetChanged();
			 RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
			    
				try {
					rdb.PostObjectsOfTypeWithParams("Challenge", "challenge/index.php", c);
					Thread.sleep(200);
			    	rdb.fetchRequestWithTypeAndPath("Challenge", "challengesOfGroup/index.php?id=" + Integer.toString(groupid));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		default:
		
			}	
		}
	
	}

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		carray = (ArrayList<Challenge>) obj;
		
		System.out.println("jh");
		if (carray != null)
		{
		gview = (GridView) rootView.findViewById(R.id.gridViewcha);
       
        adapter = new ArrayAdapter<Challenge>(getActivity().getApplicationContext(), R.layout.activity_main_list_item,R.id.activity_title, carray);
        gview.setAdapter(adapter);
        
        gview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), DescribeChallenge.class);
				Bundle b = new Bundle();
				b.putInt("ChallengeId", carray.get(pos).getId());
				b.putString("ChallengeName", carray.get(pos).getName());
				b.putString("ChallengeInfo", carray.get(pos).getDescription());
				intent.putExtras(b);
	            startActivity(intent);
			}
        	
		});
		}
	}
}