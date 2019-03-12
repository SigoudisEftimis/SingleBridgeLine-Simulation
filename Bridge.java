
/** This class represents the bridge , it has only one attribute with type Car which is refers each time in the car passing the bridge at that time
 * This class has the following methods 
 * 
 * 1. public Car getCar() : Return the value of attribute Car 
 * 2. public void setCar(Car car) : Set value to attribute Car 
 * 3. public String colourOFCar() : Return the color of the Car 
 * 4. public synchronized void SafePassing() : This method performs a safe passage of a car on the bridge
 * 
 *    - When a car enters the bridge, a message appears on the console that notifies the car that it has entered
 *    - Set the static variable  setonBridge of Handler true indicating that there is a car on the bridge
 *    - Depending on whether red or blue is the car, the Handler.setOnBridgeRed or Handler.setOnBridgeBlue true 
 *    - Given the passage time of the car, a loop  with  duration equal of the passing time , which displays a message on the screen with the remaining time
 *    - Once the pass is over, static variables onBridge , onBridgeBlue or onBridgeRed take the value of false .
 *    
 * 5. public void UnsafePassing() : This method performs a unsafe passage of a car on the bridge .
 *    - It has the same functionality with the SafePassing() except for a checkpoint where there is another car of different color. In this case, we have a conflict.
 *    
 * 6. public String getTime() : It returns the current time formating mm:ss:SSS
 */



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
