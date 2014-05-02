package ws.remote.contracts;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface FetchFromStoreInterface {
	public abstract int getAllObjectsOfType(String type) throws ClientProtocolException, IOException;
	public abstract int getObjectWithTypeAndId(String type, int id);

}
