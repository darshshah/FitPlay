package application.presentation.group;


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

public class FragGroupChallenges extends Fragment {
	
	private GridView gview;
	private ArrayAdapter<String> adapter;
	View rootView;
	int groupid;
	

	public View onCreateView(LayoutInflater inflator, ViewGroup container, 
			Bundle savedInstanceState)
	{
		groupid = getArguments().getInt("SelectedGrpID");
		System.out.println("IDIS" + groupid);
		rootView= inflator.inflate(R.layout.activity_describe_group, container, false);
		

		String testActivities[] = {"10 miles run"," 100 pushups","20 miles walk", "10k swimming"};
	    gview = (GridView) rootView.findViewById(R.id.gridViewcha);
        gview.setBackgroundColor(0);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.activity_main_list_item, R.id.activity_title, testActivities);
        gview.setAdapter(adapter);
        gview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), DescribeChallenge.class);
	            startActivity(intent);
			}
        	
		});
        
		Button button_g = (Button) rootView.findViewById(R.id.buttonaddchallenge);
		button_g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), CreateChallege.class);
                startActivity(intent);
            }
        });
		
		return rootView;
	}
}