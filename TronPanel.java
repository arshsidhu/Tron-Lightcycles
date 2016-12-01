//Tron Lightcycles Game Panel
//Arshdeep Sidhu

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*;
import java.awt.MouseInfo;
import java.util.*;

public class TronPanel extends JPanel implements KeyListener{
	
	Player p1; //Variable for Player 1, Player 2
	Player p2; 
		
	String loser = ""; //Variable for the losing player
		
	private boolean []keys; //Variable for all the keys
	
	private Image background; //Variable for the background image
	
	private Image p1picD; //Variable for all the pictures the Players will be seeing, for both players
	private Image p1picU;
	private Image p1picR;
	private Image p1picL;
	
	private Image p2picD;
	private Image p2picU;
	private Image p2picR;
	private Image p2picL;
	
	private Image gameoverp1;
	private Image gameoverp2;
	
	private Image p1directionpic = p1picR; //Sets the initial Direction and Direction Pictures
	private Image p2directionpic = p2picL;
	
	private String p1direction = "R";
	private String p2direction = "L";
	
	private String p1nxtdirection = "R";
	private String p2nxtdirection = "L";
	
	ArrayList<Integer> p1xpos = new ArrayList<Integer>(); //Lists to track positions of both players
	ArrayList<Integer> p1ypos = new ArrayList<Integer>();
	
	ArrayList<Integer> p2xpos = new ArrayList<Integer>();
	ArrayList<Integer> p2ypos = new ArrayList<Integer>();
	
	private TronLightcycles mainFrame; //Variable to access main class 
	
	public TronPanel(TronLightcycles m){
		
		keys = new boolean[KeyEvent.KEY_LAST+1]; //Get key pressed
		
		background = new ImageIcon("starbackground.jpg").getImage(); //Loading all the images 
		
		p1picD= new ImageIcon("player2D.png").getImage();
		p1picU= new ImageIcon("player2U.png").getImage();
		p1picR= new ImageIcon("player2R.png").getImage();
		p1picL= new ImageIcon("player2L.png").getImage();
		
		p2picD= new ImageIcon("player1D.png").getImage();
		p2picU= new ImageIcon("player1U.png").getImage();
		p2picR= new ImageIcon("player1R.png").getImage();
		p2picL= new ImageIcon("player1L.png").getImage();
		
		gameoverp1 = new ImageIcon("GameOver.jpg").getImage();
		gameoverp2 = new ImageIcon("GameOverP2.jpg").getImage();
		
		mainFrame = m; // Connecting to main class 
		
		p1 = new Player(3,290); //Creating the players
		p2 = new Player(594,290);
	
		setSize(600,580);
        addKeyListener(this);
        
        setVisible(true);
	}
	
	public void addNotify() { //Starts the game when The player is ready
        super.addNotify();
        requestFocus();
       	mainFrame.start();
        
    }
    
 	public String directioncheck(String direction, String nxtdirection){ //Used to check if the player is trying to go in the opposite direction (i.e going R, but wants to go L)
    	
    	if(direction.equals("R")){
    		if(nxtdirection.equals("L")){ //If the desired direction is opposite, do not allow it and keep the same Directioin
    			direction = direction; 
    		}
    		else{
    			direction = nxtdirection; //If the desired direction is not opposite, allow the switch
    		}
    	}
    	
    	if(direction.equals("L")){ //Continued for all the four cases
    		if(nxtdirection.equals("R")){
    			direction = direction; 
    		}
    		else{
    			direction = nxtdirection;
    		}
    	}
    	
    	if(direction.equals("U")){
    		if(nxtdirection.equals("D")){
    			direction = direction; 
    		}
    		else{
    			direction = nxtdirection;
    		}
    	}
    	
    	if(direction.equals("D")){
    		if(nxtdirection.equals("D")){
    			direction = direction; 
    		}
    		else{
    			direction = nxtdirection;
    		}
    	}
    	return direction;
    }
    
    public String p2direction(){ //Depending on the key press, updates the direction of the player
		if(keys[KeyEvent.VK_RIGHT] ){
			p2nxtdirection = "R";
		}
		if(keys[KeyEvent.VK_LEFT] ){
			p2nxtdirection = "L";
		}
		if(keys[KeyEvent.VK_UP] ){
			p2nxtdirection = "U";
		}
		if(keys[KeyEvent.VK_DOWN] ){
			p2nxtdirection = "D";
		}
		return directioncheck(p2direction, p2nxtdirection);
    }
    
    public void p2move(String direction){ // Actually moves player 1
    	if(direction=="R"){
    		p2directionpic = p1picR; //This method also cycles through the pictures, depending on the direction
    		p2.moveRight();
    	}
    	else if(direction=="L"){
    		p2directionpic = p1picL;
    		p2.moveLeft();
    	}
    	else if(direction=="U"){
    		p2directionpic = p1picU;
    		p2.moveUp();
    	}
    	else if(direction=="D"){
    		p2directionpic = p1picD;
    		p2.moveDown();
    	}
    	if(keys[KeyEvent.VK_O]){
    		p2.speedBoost();
    	}
    	if(keys[KeyEvent.VK_P]){
    		p2.shieldBoost();
    	}
    }
    
    public void p1move(String direction){ // Actually moves player 2
    	if(direction=="R"){
			p1directionpic = p2picR; //This method also cycles through the pictures, depending on the direction
    		p1.moveRight();
    	}
    	else if(direction=="L"){
			p1directionpic = p2picL;
    		p1.moveLeft();
    	}
    	else if(direction=="U"){
			p1directionpic = p2picU;
    		p1.moveUp();
    	}
    	else if(direction=="D"){
			p1directionpic = p2picD;
    		p1.moveDown();
    	}
    }  
    	
    public String p1direction(){  //Depending on the key press, updates the direction of the player
		if(keys[KeyEvent.VK_D] ){
			p1nxtdirection="R";
		}
		if(keys[KeyEvent.VK_A] ){
			p1nxtdirection="L";
		}
		if(keys[KeyEvent.VK_W] ){
			p1nxtdirection="U";
		}
		if(keys[KeyEvent.VK_S] ){
			p1nxtdirection="D";
		}
		return directioncheck(p1direction, p1nxtdirection);
    }
    
    public boolean inList(ArrayList<Integer>xlist,ArrayList<Integer>ylist,int x, int y){ //Method used for collision
    	for(int i = 0; i<xlist.size(); i++){ //Cycles through the x and y lists
    		for(int j = 0; j<ylist.size(); j++){
    			if(x==xlist.get(i) && y==ylist.get(i)){ //Checks if the current point is passing through one of the points in the list
    				return true; //If it is, they have collided
    			}
    		}
    	}
    	return false; //If not they have not collided
    }
    
    /*public boolean selfCollide(ArrayList<Integer>xlist, ArrayList<Integer>ylist,int x, int y){
    	int startx = x;
    	int starty = y;
    	for(int i=0; i<xlist.size(); i++){
    		for(int j=0; j<ylist.size(); j++){
    			if(xlist.size()==0 && ylist.size()==0){							//Could not get self collide to work
    				return false;
    			}
    			else if(x==xlist.get(i-1) && y==ylist.get(i-1)){
    				return false;
    			}
    			else if(x==xlist.get(i) && y==ylist.get(i)){
    				return true;
    			}
    		}
    	}
    	return false;
    }*/
    
    public boolean borderCollide(Player p){ //Checks to see if any of the players have went off the screen
    	if(p.getX()>=597 || p.getX()<=0 || p.getY()<=0 || p.getY()>=597){
    		return true; //if yes, the game is over
    	}
    	return false; // if no, the game continues
    }
    
    public boolean collide(){ //Using other methods, responds to the collisions
    	boolean p1collide = inList(p2xpos, p2ypos, p1.getX(), p1.getY()); //variable if p1 has collided with p2
    	boolean p2collide = inList(p1xpos, p1ypos, p2.getX(), p2.getY()); //variable if p2 has collided with p1
    	boolean p2border = borderCollide(p2); //variable if p2 went off the screen
    	boolean p1border = borderCollide(p1); //variable if p1 went off the screen
    	if(p1collide == true || p2collide == true || p1border == true || p2border == true){ //If either player collides or goes of the screen, this ends the game
    		if(p1.getShield()==true){ //Unless the players shield is active
    			System.out.println("Shield Active");
    			return false;
    		}
    		else{
    			losingPlayer(p1collide,p2collide,p2border,p1border);
	    		return true;
    		}
    	}
    	return false;
    }
    
    public void losingPlayer(boolean p1c, boolean p2c, boolean p2b, boolean p1b){ //Checks to see who the losing player is depending on the collision
    	if(p1c==true){
    		loser= "P1";
    	}
    	if(p2c==true){
    		loser= "P2";
    	}
    	if(p2b==true){
    		loser= "P2";
    	}
    	if(p1b==true){
    		loser= "P1";
    	}
    }
    	
    public void keyTyped(KeyEvent e) {} //Used for keys 
    
    public void keyPressed(KeyEvent e) { //Used for keys 
        keys[e.getKeyCode()] = true;
    }
    
    public void keyReleased(KeyEvent e) { //Used for keys 
        keys[e.getKeyCode()] = false;
    }
    
    public void p1pathway(Graphics g){ //Draws the pathways for p1
    	p1xpos.add(p1.getX()); //Adds their postition into the lists
    	p1ypos.add(p1.getY());
    	g.setColor(Color.orange); //Player 1's color is orange
    	for(int i = 0; i< p1xpos.size(); i++){ 
    		g.fillRect(p1xpos.get(i),p1ypos.get(i),5,5); //Draws the tail.
    	}
    }
    
    public void p2pathway(Graphics g){ //Draws the pathway for p2
    	p2xpos.add(p2.getX()); //Adds their position into the lists 
    	p2ypos.add(p2.getY());
    	g.setColor(Color.blue); //Player 2's color is blue 
    	for(int i = 0; i< p2xpos.size(); i++){
    		g.fillRect(p2xpos.get(i),p2ypos.get(i),5,5); //Draws the tail
    	}
    }
    
    /*public void gameend(){ //Displays the game over screen
    	g.drawImage(gameover,0,0,this);
    }*/
    
    public void paintComponent(Graphics g){ 	
    	g.drawImage(background,0,0,this); //Draws the background image
		p1pathway(g); //Calls the p1 tail method
		g.drawImage(p1directionpic, p1.getX()-17, p1.getY()-17, this); //Draws p1 pictures
		p2pathway(g); //Calls the p2 tail method
		g.drawImage(p2directionpic,p2.getX()-12,p2.getY()-12,this); //Draws p2 pictures	
		if(loser.equals("P1")){ //Displays the final screen if player 1 loses
			g.drawImage(gameoverp1,0,0,this);
		}
		if(loser.equals("P2")){ //Displays the final screen if player 2 loses
			g.drawImage(gameoverp2,0,0,this);
		}
    }
}