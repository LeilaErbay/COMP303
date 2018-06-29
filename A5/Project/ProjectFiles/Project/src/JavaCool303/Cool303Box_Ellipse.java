package JavaCool303;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * <h2> Cool303Box_Ellipse </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends Cool303Box</p>
 *
 */
public class Cool303Box_Ellipse extends Cool303Box{
	private ArrayList<Cool303Button_Ellipse> buttons;		//list of Cool303Button_Ellipse buttons
	
	/**
	 * 
	 * @param c the color of the box
	 * @param numObjects the number of buttons in this box
	 * @param buttonC the color of the buttons
	 * <p> Side effects: initializes the ellipse shape buttons</p>
	 */
	public Cool303Box_Ellipse(Color c, int numObjects, Color buttonC){
		super(c, numObjects, buttonC);
		buttons = new ArrayList<>();
	}
	
	/**
	 * <p> Special Note: fills the arraylist with new buttons</p>
	 */
	public void fillList(){
		for (int i = 0; i < this.numButtons; i++){
			buttons.add(i, new Cool303Button_Ellipse(" " + (i + 1), this.buttonColor));
		}
		
	}
	
	/**
	 * <p> Special Note: adds each button from the list to the box panel</p>
	 */
	@Override 
	public void fillPanel(){
		for (int i = 0; i < buttons.size(); i++){
			Cool303Button_Ellipse b = buttons.get(i);
			add(b);		
		
		}
	}
	
	/**
	 * @param g used by JButton to draw the shape of the box
	 * <p> Special Note: inherited from JPanel</p>
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.boxColor);		
		g2.setBackground(this.boxColor);
		g2.fillOval(-30, 0, getWidth()+50, getHeight());
		g2.drawOval(0,0, getWidth()-1, getHeight()-1);													//draw shape
  
	}
	
}
