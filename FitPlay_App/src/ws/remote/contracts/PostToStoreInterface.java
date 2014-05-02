package ws.remote.contracts;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface PostToStoreInterface {
	public abstract int PostObjectsOfTypeWithParams(String type, Object obj) throws ClientProtocolException, IOException;
	//public abstract void objectWasPosted(int originalId, int objectId);
}
