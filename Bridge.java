import java.text.SimpleDateFormat;
import java.util.Date;

public class Bridge{
  
    Car car ;
  
    public Bridge(Car car) {
		super();
		this.car = car;
	}

	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}

	public String colourOFCar() {
		
		if(car.getType() == 0) return "Blue Car " ;
		
		return "Red Car";
		
	}

	public synchronized void SafePassing(){
   
	 String colour = colourOFCar(); 
	 	 
      try
      
      { 
         System.out.println(colour+" has just start passing the bridge "+ getTime() + "\n");
    	
    	 
    	 Handler.setOnBridge(true);
    	 if (car.getType() == 1 ) { 
    			Handler.setOnBridgeRed(true);
    		}else {
    			Handler.setOnBridgeBlue(true);
    			
    		}
    	
    	
    	for (int i = 0 ; i < (car.time); i++) {
    	  
      	
      	System.out.println(colour + " is on the bridge it has " + (i + 1) + " seconds more  \n"); 
      	Thread.sleep(1000); 
      }
         System.out.println(colour + " has just finished " + getTime() + "\n");
         
         
         Handler.setOnBridge(false);
    	 if (car.getType() == 1 ) { 
    			Handler.setHasPrevious(true);
    			Handler.setOnBridgeRed(false);
    		}else {
    			Handler.setHasPrevious(false);
    			Handler.setOnBridgeBlue(false);
    			
    		}
         
         
      } 
      catch (Exception e) 
      { 
          System.out.println("Thread  interrupted."); 
      }
  
      
      
      
    }
	
	public void UnsafePassing(){
   
	 String colour = colourOFCar(); 
	 
	
	 System.out.println(colour+" has just start passing the bridge "+ getTime() + "\n");
	 
	 
	 if (Handler.isOnBridge() )
	       if ((car.getType()==0 && Handler.isOnBridgeRed()) || (car.getType()==1 && Handler.isOnBridgeBlue() )){System.out.println("CARS HAS JUST CRUSHED -- CARS HAS JUST CRUSHED"); System.exit(1);}
	 
	 
	 Handler.setOnBridge(true);
	 if (car.getType() == 1 ) { 
			Handler.setOnBridgeRed(true);
		}else {
			Handler.setOnBridgeBlue(true);
			
		}
	 
	 
	 
      try
      
      {   
    	 
    	for (int i = 0 ; i < (car.time); i++) {
    	  
      	
      	System.out.println(colour + " is on the bridge it has " + (i + 1) + " seconds more  \n"); 
      	Thread.sleep(1000); 
      }
    	 System.out.println(colour + " has just finished " + getTime() + "\n");
       
         
         Handler.setOnBridge(false);
    	 if (car.getType() == 1 ) { 
    			Handler.setHasPrevious(true);
    			Handler.setOnBridgeRed(false);
    		}else {
    			Handler.setHasPrevious(false);
    			Handler.setOnBridgeBlue(false);
    			
    		}
         
         
         
      } 
      catch (Exception e) 
      { 
          System.out.println("Thread  interrupted."); 
      }
  
      
      
    }
	
	 public String getTime() {

		   return new SimpleDateFormat("mm:ss:SSS").format(new Date());

	   }
 }
