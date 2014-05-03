package ws.remote.contracts;

import java.util.ArrayList;
/**
 * This interface is used to make sure that the class that calls an instance of it, has a callback method to handle 
 * the results
 */
public interface RemoteDBAdapterDelegate {
	public void didReceiveResponseObjects(ArrayList<?> obj, int id);
}
