package ws.remote;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import entities.NVPair;
import ws.remote.contracts.HttpAdapterDelegate;
import android.os.AsyncTask;
/**
 * 
 * This class is used to make asyncronous HTTP requests, so the program does not block
 * It handles GET and POST requests accordingly
 */
public class HttpAdapter extends AsyncTask<String, Integer, JSONArray> {
	private String url;
	private List<NVPair> param;
	private String method;
	private Object caller;
	private int id;
	public HttpAdapter(String url, List<NVPair> parameters, String method, HttpAdapterDelegate caller, int id){
		super();
		this.url = url;
		this.param = parameters;
		this.method = method;
		this.caller = caller;
		this.id = id;
	}
	@Override
	protected JSONArray doInBackground(String... params) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		//InputStream inputStream;
		if(method.equals("POST")){
			HttpPost httpPost = new HttpPost(url);
            try {
            	
				httpPost.setEntity(new UrlEncodedFormEntity(param));
				
				httpResponse = httpClient.execute(httpPost);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		else if(method == "GET"){
            String paramString = URLEncodedUtils.format(param, "utf-8");
            if(param.size() > 0){
            	url += "?" + paramString;
            }
            HttpGet httpGet = new HttpGet(url);

            try {
            	
				httpResponse = httpClient.execute(httpGet);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
        }
		if(httpResponse != null){
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				
				//inputStream = httpEntity.getContent();
				String result = EntityUtils.toString(httpEntity);
				JSONArray json_res = new JSONArray(result);
				//((HttpAdapterDelegate)caller).didReceiveServerResponseJSON(json_res);
				return json_res;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} 
		
        return null;
		
	}
	@Override
	protected void onPostExecute(JSONArray result) {
		if(method.equals("GET"))
			((HttpAdapterDelegate)caller).didReceiveServerResponseJSON(result, this.id);
		else if(method.equals("POST"))
			((HttpAdapterDelegate)caller).didReceivePostConfirmation(1);
	}




}
