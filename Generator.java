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


