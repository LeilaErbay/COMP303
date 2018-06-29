package JavaCool303;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * <h2> Cool303Button_RoundRectangle </h2> 
 * @author LeilaErbay
 * 
 * <p> Special Notes: extends Cool303Button</p>
 *
 */
public class Cool303Button_RoundRectangle extends Cool303Button {
	  
	/**
	 * 
	 * @param text the label of the button
	 * @param color the color of the button
	 * <p>Special Notes: sets the button label and color of the button</p>
	 */
	  public Cool303Button_RoundRectangle(String text, Color color){
		  super(text,color);
	  }
	 
	
	  /**
	   * @param g used by Button to paint the shape
	   * <p> Special Note: Indirectly inherited from JButton</p>
	   */
	  @Override 
	  public void paintComponent(Graphics g){
		  Graphics2D g2 = (Graphics2D) g;
	        if(getModel().isArmed()){
	            g2.setColor(Color.GREEN);
	        }
	        else{
	            g2.setColor(color);
	        }
	        
	        g2.fillRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);  	//fill round rectangle							//set color
	        g2.drawRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);													//draw shape
		  
	  }
	  
	  /**
	   * @param g used by Button to paint the border
	   * <p> Special Note: Indirectly inherited from JButton</p>
	   */
	  protected void paintBorder(Graphics g) {
		    g.setColor(Color.BLACK);
		    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		}
	        
	  
}
