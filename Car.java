

/** This class represents the Car , it has two attributes , the first (time) refers to the passage time and the second (type)  to the type of car . If type equals to 0 car is blue if type equals to 1 is Red
 * It has only on method which return the type of the car ; 
 * 
 */

public class Car 
{ 
   
    int time ;
    int type; 
    
	public Car(int time, int type) {
		super();
		this.time = time;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	

} 