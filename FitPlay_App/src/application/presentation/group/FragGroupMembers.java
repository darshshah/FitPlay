package application.presentation.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fitplay_app.R;

public class FragGroupMembers extends Fragment {
	View rootView;
	ListView listv;
	private ArrayAdapter<String> adapter;
	int groupid;
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, 
			Bundle savedInstanceState)
	{
		groupid = getArguments().getInt("SelectedGrpID");
		System.out.println("IDIS" + groupid);
		rootView= inflator.inflate(R.layout.activity_group_members, container, false);
		
		// set the list with the grp id. User list
		String testActivities[] = {"Darsh","Armando","Bajaj"};
        listv = (ListView) rootView.findViewById(R.id.list2);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.activity_main_list_item, R.id.activity_title, testActivities);
        listv.setAdapter(adapter);
		
		return rootView;
}
}
