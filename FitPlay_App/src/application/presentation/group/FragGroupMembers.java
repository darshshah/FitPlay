package application.presentation.group;

import java.util.ArrayList;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fitplay_app.R;

import entities.User;

public class FragGroupMembers extends Fragment implements RemoteDBAdapterDelegate {
	View rootView;
	ListView listv;
	
	int groupid;
	ArrayAdapter<User> adapter;
	ArrayList<User> usr;
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, 
			Bundle savedInstanceState)
	{
		groupid = getArguments().getInt("SelectedGrpID");
		System.out.println("IDIS" + groupid);
		rootView= inflator.inflate(R.layout.activity_group_members, container, false);
		
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
	    
		try {
	    	rdb.fetchRequestWithTypeAndPath("User", "usersOfGroup/index.php?id=" + Integer.toString(groupid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
		return rootView;
}	

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		usr = (ArrayList<User>) obj;
		System.out.println("abc");
		if(usr != null)
		{
		listv = (ListView) rootView.findViewById(R.id.list2);
        adapter = new ArrayAdapter<User>(getActivity().getApplicationContext(), R.layout.activity_main_list_item, R.id.activity_title, usr);
        listv.setAdapter(adapter);
		}
	}
}
