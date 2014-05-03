package ws.remote.contracts;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
/**
 * 
 * Interface to post to a persistent storage, the actual details can vary depending the class 
 * that implements it, and the type of persistent storage used
 *
 */
public interface PostToStoreInterface {
	public abstract int PostObjectsOfTypeWithParams(String type, String resourcePath, Object obj) throws ClientProtocolException, IOException;
	
}
