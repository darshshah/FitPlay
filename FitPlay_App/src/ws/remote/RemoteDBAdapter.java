package ws.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ws.remote.contracts.FetchFromStoreInterface;
import ws.remote.contracts.HttpAdapterDelegate;
import ws.remote.contracts.PostToStoreInterface;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.annotation.SuppressLint;
import entities.Cardio_Exercise;
import entities.Challenge;
import entities.Group;
import entities.Log;
import entities.Strength_Exercise;
import entities.User;



@SuppressLint("UseSparseArrays")
public class RemoteDBAdapter implements FetchFromStoreInterface, PostToStoreInterface, HttpAdapterDelegate {
	private int id;
	private HashMap<Integer,String> typeMap;
	private RemoteDBAdapterDelegate caller;
	/**
	 * Constructor, which sets the delegate, to call when the requests are successfully parsed
	 * @param caller
	 */
	public RemoteDBAdapter(RemoteDBAdapterDelegate caller){
		id = 0;
		typeMap = new HashMap<Integer,String>();
		this.caller = caller;
	}
	
	/**
	 * Retrieve all the objects of the specified type
	 */
	@Override
	public int getAllObjectsOfType(String type)  throws ClientProtocolException, IOException {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		HttpAdapter adapter = new HttpAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com/group/", params, "GET", this, id);
		adapter.execute();
		typeMap.put(id, type);
		return id++;
	}
	/**
	 * Retrieve an object with a specific type and id
	 */
	@Override
	public int getObjectWithTypeAndId(String type, int id) {
		// TODO Auto-generated method stub
		return id++;
	}
	
	/**
	 * When the JSON response is retrieved, create objects according to the type, 
	 * and pass them to the delegate
	 */
	@Override
	public void didReceiveServerResponseJSON(JSONArray json, int id) {
		String type = typeMap.get(Integer.valueOf(id));
		if(type.equals("Log")){
			ArrayList<Log> result= new ArrayList<Log>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new Log(Integer.valueOf((String)obj.get("id")),
							Integer.valueOf((String)obj.get("amount")),
							Integer.valueOf((String)obj.get("user_id")),
							Integer.valueOf((String)obj.get("activity_id")),
							(String)obj.get("activity_type")));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("Group")){
			ArrayList<Group> result= new ArrayList<Group>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new Group(
							Integer.valueOf((String)obj.get("id")),
							(String)obj.get("name"),
							Integer.valueOf((String)obj.get("owner_id"))));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("Challenge")){
			ArrayList<Challenge> result= new ArrayList<Challenge>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new Challenge(
							Integer.valueOf((String)obj.get("id")),
							Integer.valueOf((String)obj.get("group_id")),
							(String)obj.get("name"),
							(String)obj.get("description")
							));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("User")){
			ArrayList<User> result= new ArrayList<User>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new User(
							Integer.valueOf((String)obj.get("id")),
							(String)obj.get("name"),
							(String)obj.get("username")
							));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("Cardio_Exercise")){
			ArrayList<Cardio_Exercise> result= new ArrayList<Cardio_Exercise>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new Cardio_Exercise(
							Integer.valueOf((String)obj.get("id")),
							(String)obj.get("name"),
							(String)obj.get("units"),
							Float.valueOf((String)obj.get("clas_per_unit"))
							));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("Strength_Exercise")){
			ArrayList<Strength_Exercise> result= new ArrayList<Strength_Exercise>();
			for(int i = 0; i < json.length(); i++){
				try {
					JSONObject obj;
					obj = (JSONObject)json.get(i);
					result.add(new Strength_Exercise(
							Integer.valueOf((String)obj.get("id")),
							(String)obj.get("name"),
							Integer.valueOf((String)obj.get("reps")),
							Integer.valueOf((String)obj.get("weight")),
							(String)obj.get("weight_unit"),
							Float.valueOf((String)obj.get("cals_per_unit"))
							));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			caller.didReceiveResponseObjects(result, id);
		}
	}

	@Override
	public int PostObjectsOfTypeWithParams(String type, Object obj)
			throws ClientProtocolException, IOException {
		
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		HttpAdapter adapter = new HttpAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com/log/", params, "POST", this, id);
		adapter.execute();
		typeMap.put(id, type);
		
		return id++;
		
	}
	
	@Override
	public int didReceivePostConfirmation(int id) {
		// TODO Auto-generated method stub
		return id;
	}

}
