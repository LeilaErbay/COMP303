package JavaCool303;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

/**
 * <h2> Cool303Button_Ellipse </h2>
 * @author LeilaErbay
 * <p> Special Notes: Extends Cool303Button</p>
 *
 */
public class Cool303Button_Ellipse extends Cool303Button {

	/**
	 * 
	 * @param text the label of the button
	 * @param color the color of the button
	 * <p> Special Note: sets button text and color</p>
	 */
	public Cool303Button_Ellipse(String text, Color color){
		super(text, color);
	}
	
	/**
	 * @param g used by JButton to paint the Ellipse shape
	 * <p> Special Note: Indirectly inherited from JButton</p>
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
        if(getModel().isArmed()){
            g2.setColor(Color.BLACK);
        }
        else{
            g2.setColor(color);
        }
        
        g2.fillOval(0, getHeight()/8, getWidth(), getHeight()-(getHeight()/4));  					//fill round Ellipse								
        g2.drawOval(0, getHeight()/8, getWidth(), getHeight()-(getHeight()/4));
	}
	
	/**
	 * @param g used by JButton to paint the border of the button
	 * <p> Special Note: Indirectly inherited from JButton</p>
	 */
	protected void paintBorder(Graphics g) {
	    g.setColor(Color.BLACK);
	    g.drawOval(0, getHeight()/8, getWidth(), getHeight()-(getHeight()/4));
	}
}
