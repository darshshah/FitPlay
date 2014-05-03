package ws.remote.contracts;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
/**
 * This interface is used to enforce that a class implements methods to access persistent storage,
 * the benefit of it, is that the application logic does not care about the type of storage, and can potentially
 * use different types (SQL, files, etc) with the same interface
 */
public interface FetchFromStoreInterface {
	public abstract int getAllObjectsOfType(String type, String resourcePath) throws ClientProtocolException, IOException;
	public abstract int getObjectWithTypeAndId(String type, int id,String resourcePath);
	public abstract int fetchRequestWithTypeAndPath(String type, String resourcePath);

}
