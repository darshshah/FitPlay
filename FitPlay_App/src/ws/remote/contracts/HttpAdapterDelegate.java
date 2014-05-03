package ws.remote.contracts;

import org.json.JSONArray;
/**
 * 
 * Interface used to ensure the user of an asyncronous request has defined a method
 * to handle back the results
 *
 */
public interface HttpAdapterDelegate {
	public void didReceiveServerResponseJSON(JSONArray json, int id);
	public int didReceivePostConfirmation(int id);
}
