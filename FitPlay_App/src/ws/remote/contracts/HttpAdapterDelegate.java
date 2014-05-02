package ws.remote.contracts;

import org.json.JSONArray;

public interface HttpAdapterDelegate {
	public void didReceiveServerResponseJSON(JSONArray json, int id);
	public int didReceivePostConfirmation(int id);
}
