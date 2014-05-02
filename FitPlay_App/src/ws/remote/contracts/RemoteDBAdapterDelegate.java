package ws.remote.contracts;

import java.util.ArrayList;

public interface RemoteDBAdapterDelegate {
	public void didReceiveResponseObjects(ArrayList<?> obj, int id);
}
