//Player Object
//Arshdeep Sidhu

public class Player{
	
	private int x; //Variable for x pos.
	private int y; //Variable for y pos.
	private int shieldCounter = 0; //Counter for shield usages
	private boolean shield = false; //Variable for shield active
	private int boostCounter = 0; //Variable for boost usages
	private int incint = 3; //The increase int. How many pixels does the player move each time
	
	public Player(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){return x;} //Returns the x pos
	
	public int getY(){return y;} //Returns the y pos
	
	public void moveUp(){ //Moves the player up
		y-=incint;
	} 
	
	public void moveDown(){ //Moves the player down
		y+=incint;
	}
	
	public void moveRight(){ //Moves the player right
		x+=incint;
	}
	
	public void moveLeft(){ //Moves the player left
		x-=incint;
	}
	
	public boolean getShield(){ //Returns if the player is using a shield
		return shield;
	}
	
	public void speedBoost(){
		if(boostCounter==0){ //Checks to see if boost was used before
			long t = System.currentTimeMillis(); //Takes current time
			long end = t+5000; //Adds 5 seconds on to the current time
			while(System.currentTimeMillis()<end){ //While time current time is reaching time + 5 seconds 
				incint = 6; //The players speed increases from 3 to 6
				try {
	    			Thread.sleep(10);                 //Do not understand why I have to do this.
				} catch(InterruptedException ex) { //Internet says "Pause to avoid churning"
	    			Thread.currentThread().interrupt();
				}
			}
			boostCounter+=1; // Memory spot for boost being used
			incint = 3; //Sets speed back to 3 once the 5 seconds are up.
		}
	}
	
	public void shieldBoost(){
		if (shieldCounter==0){ //Checks to see if shield was used or not
			long t = System.currentTimeMillis(); //Takes current time
			long end = t+5000; //Adds 5 seconds on to the current time
			while(System.currentTimeMillis()<end){  //While time current time is reaching time + 5 seconds
				shield = true; //The player gets a shield 
				try {
	    			Thread.sleep(10);                 //Do not understand why I have to do this.
				} catch(InterruptedException ex) { //Internet says "Pause to avoid churning"
	    			Thread.currentThread().interrupt();
				}
			}
			shieldCounter+=1; //Memory spot for shield being used
			shield = false; //Turns shield off after 5 seconds.
		}
	}	
}