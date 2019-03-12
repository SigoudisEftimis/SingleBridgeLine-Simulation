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
