package JavaCool303;

import java.awt.*;
import javax.swing.*;
import java.lang.Math;

/**
 * <h2> Cool303Box </h2>
 * @author LeilaErbay
 * <p>Special Notes : extends JPanel</p>
 * <p> abstract class</p>
 */
//BOX = INNER PANEL
public abstract class Cool303Box extends JPanel {
					
    protected Color boxColor;	//the color of the box
    protected Color buttonColor;	//will pass the button color as it creates buttons
    protected int numButtons;
 
    /**
     * 
     * @param color the color of box
     * @param numObjects the number of buttons in the box
     * @param buttonCol the color of the button
     * <p> Special Notes: sets its layout as a GridLayout</p>
     */
    public Cool303Box(Color color, int numObjects, Color buttonCol){
        super(new GridLayout((int) Math.sqrt(numObjects),(int) Math.sqrt(numObjects)));
      
        this.numButtons = numObjects;
        this.boxColor = color;   
        this.buttonColor = buttonCol;
   
        Dimension size = this.getPreferredSize();
        size.width= size.height= Math.min(size.width, size.height);
        super.setPreferredSize(size);
 
        
        
    }	
    
 
   //METHODS REQUIRED TO EXIST IN CHILDREN
    
    public abstract void fillList();		
    
    public abstract void fillPanel();
    
    protected abstract void paintComponent(Graphics g);
    
}