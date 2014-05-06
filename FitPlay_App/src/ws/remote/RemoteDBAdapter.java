package ws.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import entities.NVPair;
import entities.Strength_Exercise;
import entities.User;


/**
 * This class is used to provide the application access to remote objects, specifically, retrieved from a 
 * web server
 *
 */
@SuppressLint("UseSparseArrays")
public class RemoteDBAdapter implements FetchFromStoreInterface, PostToStoreInterface, HttpAdapterDelegate {
	private int id;
	private HashMap<Integer,String> typeMap;
	private RemoteDBAdapterDelegate caller;
	private String baseIP;
	/**
	 * Constructor, which sets the delegate, to call when the requests are successfully parsed
	 * @param caller
	 */
	public RemoteDBAdapter(String baseIP, RemoteDBAdapterDelegate caller){
		id = 0;
		typeMap = new HashMap<Integer,String>();
		this.caller = caller;
		this.baseIP = baseIP;
	}
	
	/**
	 * Retrieve all the objects of the specified type
	 */
	@Override
	public int getAllObjectsOfType(String type, String resourcePath)  throws ClientProtocolException, IOException {
		ArrayList<NVPair> params = new ArrayList<NVPair>();
		StringBuilder url = new StringBuilder(baseIP);
		url.append("/");url.append(resourcePath);
		HttpAdapter adapter = new HttpAdapter(url.toString(), params, "GET", this, id);
		adapter.execute();
		typeMap.put(id, type);
		return id++;
	}
	/**
	 * Different types of requests
	 */
	@Override
	public int getObjectWithTypeAndId(String type, int id, String resourcePath) {
		// TODO Auto-generated method stub
		return id++;
	}

	@Override
	public int fetchRequestWithTypeAndPath(String type, String resourcePath) {
		ArrayList<NVPair> params = new ArrayList<NVPair>();
		StringBuilder url = new StringBuilder(baseIP);
		url.append("/");url.append(resourcePath);
		HttpAdapter adapter = new HttpAdapter(url.toString(), params, "GET", this, id);
		adapter.execute();
		typeMap.put(id, type);
		return id++;

	}
	/**
	 * Callback methods:
	 * When the JSON response is retrieved, create objects according to the type, and request type
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
			if(json != null)
			{
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
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("Challenge")){
			ArrayList<Challenge> result= new ArrayList<Challenge>();
			if(json != null)
			{
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
			}
			caller.didReceiveResponseObjects(result, id);
		}
		else if(type.equals("User")){
			ArrayList<User> result= new ArrayList<User>();
			if(json != null)
			{
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
	public int PostObjectsOfTypeWithParams(String type, String resourcePath, Object obj)
			throws ClientProtocolException, IOException {
		
		List<NVPair> params = new ArrayList<NVPair>();
		StringBuilder url = new StringBuilder(baseIP);url.append("/");
		url.append(resourcePath);
		if(type.equals("Group")){
			Group group = (Group) obj;
			//params.add(new NVPair("id", String.valueOf(group.getGid())));
			params.add(new NVPair("name", group.getGname()));
			params.add(new NVPair("owner_id", String.valueOf(group.getOwner_id())));
			
		}
		if(type.equals("Challenge")){
			Challenge challenge = (Challenge) obj;
			params.add(new NVPair("name", challenge.getName()));
			params.add(new NVPair("description", challenge.getDescription()));
			params.add(new NVPair("group_id", String.valueOf(challenge.getGroup_id())));
		}
		if(type.equals("User")){
			User user = (User) obj;
			params.add(new NVPair("name", user.getName()));
			params.add(new NVPair("username", user.getUsername()));
			params.add(new NVPair("password", user.getPassword()));
		}
		if(type.equals("Request")){
			
		}
		
		HttpAdapter adapter = new HttpAdapter(url.toString(), params, "POST", this, id);
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
