
/** This class represents the Bridge Thread . Each bridgeThread is a snapshot of the bridge and a car that transcends it.
 *  It has two attributes one refers to a Bridge object and the other one is a boolean variable indicating that the passage is safe or not
 *  In the run () method inherited, the class extends Thread. If safe is true, secure passage is performed if not performed unsafe.
 * 
 */

public class BridgeThread extends Thread{

    protected Bridge bridge = null;
    protected boolean safe;

   

    public BridgeThread(Bridge bridge, boolean safe) {
		super();
		this.bridge = bridge;
		this.safe = safe;
	}



	public void run() {
	
          
    	if(safe)
    	    bridge.SafePassing();
    	else
    		bridge.UnsafePassing();
     
    }
 }
