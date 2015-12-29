import java.awt.Container;
//import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This application provides a simple template for GUIs, showing several examples of
 * buttons, radio buttons, JLabels, JTextFields, etc.
 * MainPanel uses a BorderLayout for it's five panels, and the center panel uses
 * a GridLayout of size 3,3, to display 7 JLabels.
 * @author ralexander
 * 
 * Modified by Shmuale Mark.
 *
 */
@SuppressWarnings("serial")
public class Driver extends JFrame {
	JPanel mainPanel;

	/**
	 * Driver() is the constructor for the top-level GUI class.  It simply 
	 * calls the MainPanel constructor and adds it to the JFrame's content pane
	 */
	public Driver() {
	      setTitle("Cars R Us Auto Shop");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      Container contentPane = getContentPane();
	      mainPanel = new MainPanel();
	      contentPane.add(mainPanel);
	   }
	
	/**
	 * The main method for the GUI example program
	 * @param args not used
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
	      JFrame frame = new Driver();
	      frame.pack();
	      frame.setVisible(true);
	}

}
