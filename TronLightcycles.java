//Tron Lightcycles Main Class
//Arshdeep Sidhu
//This program will create a game based off the arcade game Tron

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*;
import java.awt.MouseInfo;
import java.util.ArrayList;

public class TronLightcycles extends JFrame implements ActionListener{
	
	Timer myTimer; //Variables for timer, Game Panel and Start Up Screen
	TronPanel tp;
	StartScreen s;
	
	public static void main(String[]args){
		TronLightcycles t = new TronLightcycles(); //Creates the game
	}
		
	public TronLightcycles(){ 
		super("Tron Lightcycles"); //Titles the game
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows you to close the game using the "X" button
		setSize(600,600); // Sets size
		
		myTimer = new Timer(10, this); //Sets up Timer
		
		s= new StartScreen(this); //Creates Start up Screen
		add(s); //Starts it
		tp= new TronPanel(this); //Creates Game Panel
 
		setResizable(false); //Does not let resize
		setVisible(true); 
	}
	
	public void start(){ //Method with starts the timer when im ready to start it
		myTimer.start();
	}
	
	public void startGame(){ //Method that actually starts the game
		add(tp); //Starts the game Panel
	}
	
	@Override
	public void actionPerformed(ActionEvent evt){
		if(tp.collide()==false){ //As long as both the players are alive
			tp.p1move(tp.p1direction()); //They are still able to move
			tp.p2move(tp.p2direction());
			tp.repaint();
		}
		if(tp.collide()==true){ //One of the players has lost
			myTimer.stop();
		}
	}
}