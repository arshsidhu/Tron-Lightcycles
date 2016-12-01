import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JPanel implements ActionListener{
	
	private TronLightcycles mainFrame; //Variable for the main class
	
	public StartScreen(TronLightcycles m){
	
		setSize(600,580);
		
		FlowLayout flo = new FlowLayout();
		setLayout(flo);
		
		mainFrame = m; //Connects to the main class
		
		JButton start = new JButton("Start"); //Creates the start and controls button
		JButton controls = new JButton("Controls");
		start.addActionListener(this);
		controls.addActionListener(this);
		add(start); //Adds the buttons
		add(controls);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt){
		setVisible(false); //If you press start, starts the game
		mainFrame.startGame();

	}
}