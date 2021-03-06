
/** This class implements the central logic of simulation implementation. It uses all the other classes to succeed.
 * 
 * 1. It has the following attributes 
 *     -  private static Generator  generator : It is a static variable  that refers to an object generator . Generate the cars that arrives at the bridge 
 *     -  private static Queue<Car> redQueue : It is a static variable  that refers to a Queue of Cars . Keep the red cars ;
 *     -  private static Queue<Car> blueQueue : It is a static variable  that refers to a Queue of Cars . Keep the blue cars ;
 *     -  private static boolean safe  : It has a static boolean variable  which states if the simulation is safe
 *     -  private static boolean fair  : It has a static boolean variable  which states if the simulation is fair.That is, the passage on the bridge is fairly shared on both sides
 *     -  private static boolean hasPrevious : It Indicates what kind of car was previously on the bridge . If is it true bridge had red car previous otherwise had blue . 
 *     -  private static boolean onBridge : Indicates if there is a car on the bridge
 *     -  private static boolean onBridge�lue: Indicates if there is a blue car on the bridge
 *     -  private static boolean onBridgeRed: Indicates if there is a red car on the bridge
 *     -  private static boolean TrafficAdjustment : In case one of the two queues has a car difference above a threshold then in the case of a fair pass, the distribution of the cars is tailored to the tail needs of most cars.
 *     -  private static int limitAdjustment : Adjustment limit. If the difference is above the limit we have an adjustment. *
 *     
 *      
 * 2. The class has the following methods 
 * 
 *     - public static void main() : 
 *          
 *          > Is the main method that start the program . In this method the parameter input process is implemented by the user . It use the library Scanner  to accomplish it.
 *          > Prints information about the parameters in the console.
 *          > It use Timer object which given rate performs a BrigdeThread . It basically puts a car on the bridge to pass.
 *          
 *     - public static  Car getCar() : Return a Car for one of the two Queues . 
 *     - synchronized private static void WaitingCars() : If there are no cars to both of the two Queues this methods wait until a car arrive ;
 *     - private static Car getRandomCar() : Returns a car from one of the two queues with the same probability , it use the Java Library Random.
 *     - private static Car getCarwithoutAdjustment() : Returns a car without fairly but without Adjustment .
 *     - private static Car getCarWithAdjustment() : Returns a car fairly with Adjustment .
 *     
 * 
 */



import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Handler {

	private static Generator  generator;
	private static Queue<Car> blueQueue;
	private static Queue<Car>  redQueue  ;
    private static boolean fair ;
    private static boolean safe ;
    private static boolean hasPrevious = false;
    private static boolean onBridge = false ;
    private static boolean onBridgeRed = false ;
    private static boolean onBridgeBlue = false;
    private static boolean TrafficAdjustment  = false;
    private static int limitAdjustment ;
   
	public static void main(String[] args) {
	

        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\r\n" + "Give the rate of arrival of cars in seconds : ");
		int rate = keyboard.nextInt();
		System.out.println("\r\n" + "Give the transit time of cars in  seconds : ");
		int time = keyboard.nextInt();
		System.out.println("\r\n" + "How many cars want to simulate ?: ");
		int totalCars = keyboard.nextInt();
		System.out.println("\r\n");
		System.out.println("\r\n" + "Is the simulation fair ? ");
		 fair = keyboard.nextBoolean();
		System.out.println("\r\n");
		System.out.println("\r\n" + "Is the simulation safe ? ");
		safe = keyboard.nextBoolean();  
		System.out.println("\r\n");
		
		if(fair) {
		      System.out.println("\r\n" + "Do you traffic adjustment base on the traffic  ?  YES = TRUE   NO = FALSE  ");
		      TrafficAdjustment  = keyboard.nextBoolean(); 
		      System.out.println("\r\n");
		}
		if (TrafficAdjustment){
			
			System.out.println("\r\n" + "Please give the adjustmentlimit :  ");
		    limitAdjustment  = keyboard.nextInt(); 
			System.out.println("\r\n");
		}
		
		
		System.out.println("\r\n");
		System.out.println("SingleBridge Simulation  \n" );
		System.out.println("Safe : "+ safe + "\n");
		System.out.println("Fair : "+ fair + "\n");
		System.out.println("Arrival rate :  1 car per " + rate + "\n");
		System.out.println("Car transit time  : " + time +"\n");
		System.out.println("Adjustment  : " + TrafficAdjustment +"\n");
		System.out.println("LimitAdjustment : " + limitAdjustment +"\n");
		System.out.println("\r\n");
		
		System.out.println("=================Bridge=====================");
		System.out.print(String.format("%70s",""));
		System.out.print("====================Arrivals ==============");
		System.out.print(String.format("%30s",""));
		System.out.print("================== Cars Waiting ============");
		System.out.print(String.format("%20s",""));
		System.out.print("========== Adjustments =============");
		
		
		
		blueQueue = new LinkedList<Car>();
		redQueue = new LinkedList<Car>(); 
		generator = new Generator(blueQueue,redQueue,rate,time,totalCars);
	    generator.generate();
		 
		
		
		 Bridge bridge  = new Bridge(null);
		 
		    Timer timer = new Timer(); 
		 
		        timer.scheduleAtFixedRate(new TimerTask() 
		        {
		            int count = 0;
		 
		            public void run() 
		            {
		                
		            	      System.out.println(String.format("%170s" ,"Cars :   ("+  blueQueue.size()+" "+redQueue.size()+")\n")); 
                              Thread  threadA = new BridgeThread(bridge,safe);

                              synchronized(bridge)	 { 

                              Car car = getCar();	 
  
                               bridge.setCar(car);
 
                               threadA.start();   
                               
                              }
		            	
		                count ++;       
		                         
		                if(count == totalCars)  timer.cancel();
		            } 
		                 
		        }, 0,1000); 
		         
		    }

		 
		 
	



 
 public static  Car getCar() {
		
        WaitingCars();
        
        if (redQueue.isEmpty() && !blueQueue.isEmpty()) return blueQueue.poll();
		else if (!redQueue.isEmpty() && blueQueue.isEmpty()) return redQueue.poll();
		
		if (isFair()) {
			
			if(!TrafficAdjustment) return getCarwithoutAdjustment();
			else return getCarWithAdjustment();
				 
		}else return  getRandomCar();

	
		
}


 synchronized private static void WaitingCars(){
	
	while(redQueue.isEmpty() && blueQueue.isEmpty()) {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
	
 private static Car getRandomCar() {
		
	    Random random = new Random();
	    int rand = random.nextInt(10) + 1 ;
	  
		if (rand < 5 ) return blueQueue.poll();
					
		return redQueue.poll();
	
	
		
	}

 
 private static Car getCarwithoutAdjustment() {

	 
	 if (isHasPrevious()) {
	
		 return blueQueue.poll();
	 }
	 
	 else {
		
		 return redQueue.poll();	
	 }
 }
 
 
 private static Car getCarWithAdjustment() {
	
	 
		 if (blueQueue.size() - redQueue.size()  > limitAdjustment ) { System.out.println(String.format("%230s",blueQueue.size() - redQueue.size() + " Blue adjustment" ));return blueQueue.poll(); }
				 
		 else if(redQueue.size() - blueQueue.size()  > limitAdjustment) { System.out.println(String.format("%230s",redQueue.size() - blueQueue.size()  + " Red  adjustment" ));  return redQueue.poll();}
		 
		 else  return getCarwithoutAdjustment();
	 
		
}


public static boolean isFair() {
	return fair;
}


public static void setFair(boolean fair) {
	Handler.fair = fair;
}


public static boolean isSafe() {
	return safe;
}


public static void setSafe(boolean safe) {
	Handler.safe = safe;
}


public static boolean isHasPrevious() {
	return hasPrevious;
}


public static void setHasPrevious(boolean hasPrevious) {
	Handler.hasPrevious = hasPrevious;
}

public static boolean isOnBridge() {
	return onBridge;
}

public static void setOnBridge(boolean onBridge) {
	Handler.onBridge = onBridge;
}

public static boolean isOnBridgeRed() {
	return onBridgeRed;
}

public static void setOnBridgeRed(boolean onBridgeRed) {
	Handler.onBridgeRed = onBridgeRed;
}

public static boolean isOnBridgeBlue() {
	return onBridgeBlue;
}

public static void setOnBridgeBlue(boolean onBridgeBlue) {
	Handler.onBridgeBlue = onBridgeBlue;
}



}







