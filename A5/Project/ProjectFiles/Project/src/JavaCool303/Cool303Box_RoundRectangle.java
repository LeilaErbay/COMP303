package JavaCool303;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
/**
 * <h2> Cool303Box_RoundRectangle </h2>
 * @author LeilaErbay
 * <p> Special Notes: Extends Cool303Box</p>
 *
 */
public class Cool303Box_RoundRectangle extends Cool303Box {
	private ArrayList<Cool303Button_RoundRectangle> buttons;	//will fill the list of RoundRectangle Buttons with new Buttons
	
	/**
	 * 
	 * @param boxColor the box color 
	 * @param numObjects the number of buttons in the box
	 * @param buttonC the color of the buttons in the box
	 * <p> Side Effects: instantiates the Cool303Button_RoundRectangle ArrayList</p>
	 */
	public Cool303Box_RoundRectangle(Color boxColor, int numObjects, Color buttonC){
		super(boxColor, numObjects, buttonC);
		buttons = new ArrayList<>();
	}
	
	
	/**
	 * <p> Side effects: the ArrayList will fill up with numButtons number of buttons</p>
	 */
	@Override 
	public void fillList(){
		for (int i = 0; i < this.numButtons; i++){
			buttons.add(i, new Cool303Button_RoundRectangle(" " + (i + 1), this.buttonColor));
		}
	}
	
	
	/**
	 * <p> Side effects: adds each button to the box</p>
	 */
	@Override 
	public void fillPanel(){
		for (int i = 0; i < buttons.size(); i++){
			Cool303Button_RoundRectangle b = buttons.get(i);
			this.add(b);	
		}
	}
	
	
	/**
	 * @param g used by JPanel to paint the shape of a round rectangle
	 * <p> Special Note: inherited from JPanel</p>
	 */
	@Override 
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.boxColor);		
		g2.fillRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);
		g2.drawRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);		//draw shape - round rectangle
  
	}
	

	
}
