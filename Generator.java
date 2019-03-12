
/**  This class is the generator of the cars. What it does is that given a rate of arrival, it randomly  creates a car either red or blue and puts it in a queue.
 *    If the car it creates is red it places it on RedQueue otherwise if it is blue it places it on BlueQueue
 *    It has 5 attributes 
 *    - Queue<Car> blueQueue :Ét's a queue that keeps the Blue Cars ;
 *    - Queue<Car> redQueue :Ét's a queue that keeps the Red Cars ;
 *    - rate : is the  rate of car arrival
 *    - time : is the passing time 
 *    - totalCars : the total number of car that arrives.
 *    
 *    This class has the following methods 
 *    - public void generate() : This method create a new car every rate seconds until the local variable count is equals to totalCars . In order to accomplish this, he uses the method getRandomCar;
 *    - public void generateRandomCar() : This method creates with the same probability a car either red or blue. Uses the Random library
 *    - public String getTime() : It returns the current time formating mm:ss:SSS
 *    
 *   
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class Generator {

	Queue<Car> blueQueue;
	Queue<Car>  redQueue  ;
	private int rate ;
	private int time;
	private int totalCars;
   

	


	public Generator(Queue<Car> blueQueue, Queue<Car> redQueue, int rate, int time, int totalCars) {
		super();
		this.blueQueue = blueQueue;
		this.redQueue = redQueue;
		this.rate = rate*1000;
		this.time = time;
		this.totalCars = totalCars;
	}


	public void generate() {
		
	   Timer timer = new Timer();
	   timer.scheduleAtFixedRate(new TimerTask() 
       {
           int count = 0;

           public void run() 
           {
               
        	   generateRandomCar();
        	   
               count ++;       
                        
               if(count == totalCars)  timer.cancel();
               
              
           } 
                
       }, 0, rate);
	}
	
	
public void generateRandomCar() {
		
		Random random = new Random();
		
		int randomnumber = random.nextInt(10) + 1;
		
		 
	
		if (randomnumber < 5) {
			
			blueQueue.add(new Car(time,0));
			System.out.println(String.format("%120s","Blue Car with  has just arrived  "+  getTime() +" \n"));	        
			}
		else {
			System.out.println(String.format("%120s","Red Car with  has just arrived  "+  getTime() +" \n"));	   
			redQueue.add(new Car(time,1));
			
		}
		
	}
	
   public String getTime() {

	   return new SimpleDateFormat("mm:ss:SSS").format(new Date());

   }
 }


