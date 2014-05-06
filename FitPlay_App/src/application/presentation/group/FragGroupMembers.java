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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import application.presentation.users.NewMembers;
import application.presentation.users.ShowUserInfo;

import com.example.fitplay_app.R;

import entities.User;

public class FragGroupMembers extends Fragment implements RemoteDBAdapterDelegate {
	protected static final int ADD_NEW_MEMBER_INGROUP = 994;
	View rootView;
	ListView listv;
	
	int groupid;
	ArrayAdapter<User> adapter;
	ArrayList<User> usr;
	ArrayList<String> str = new ArrayList<String>();
	
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
	
		Button button_g = (Button) rootView.findViewById(R.id.buttonaddmemberingrp);
		button_g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), NewMembers.class);
            	intent.putExtra("groupid", groupid);
            	intent.putStringArrayListExtra("userlist", str);
            	startActivityForResult(intent, ADD_NEW_MEMBER_INGROUP);
                
            }
        });
		
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
        str.clear();
        
        for(User u:usr)
        {
        	str.add(u.getUsername());
        }
        	
        
        listv.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
        	
        		Intent intent = new Intent(v.getContext(), ShowUserInfo.class);
        		Bundle b = new Bundle();
           		b.putString("uname", usr.get(pos).getUsername());
        		b.putString("name", usr.get(pos).getName());
        		intent.putExtras(b);
        		startActivity(intent);
        	}
		});
		}
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {   // See which sub activity has finished   

		if (resultCode != Activity.RESULT_CANCELED) {
			switch (requestCode) {     
			
			case ADD_NEW_MEMBER_INGROUP:  
			// add new data to the database for that grp
				if (resultCode == Activity.RESULT_OK)
				{
					RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
				    
					try {
						rdb.fetchRequestWithTypeAndPath("User", "usersOfGroup/index.php?id=" + Integer.toString(groupid));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}
}
