package JavaCool303;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

/**
 * <h2> Cool303Border </h2>
 * @author LeilaErbay
 * <p> Special Note: extends JPanel</p>
 *
 */
public class Cool303Border extends JPanel {
	private JLabel titleL;
	private Font font;
	private Color panelColor;		//color of the border is the same as it's inner box until it is told that it should be altered
	private boolean added;			//acts as a flag that the border has been added
	String theme;
	
	/**
	 * @param t represents the theme that will be passed
	 * <p> Side Effects: sets a border layout and calls setBorder()</p>
	 * <p> sets theme and the boolean added</p>
	 */
	public Cool303Border (String t){
		super(new BorderLayout());
		theme =t;
		setBorder();
		added = true;
		
	}

	/**
	 * <p> Side effects: Sets the color of the border based on the input string from the constructor</p>
	 * <p> Special Notes: Those wishing to use Cool303Border just have to Override 
	 * setTheme with new conditions to set the color</p>
	 */
	public void setTheme(){
		if (theme.equals("pastel")){
			this.panelColor = Cool303Theme_Pastel.getBoxColor();
			setBackground(panelColor);
		}
		else if (theme.equals("summer")) {
			this.panelColor = Cool303Theme_Pastel.getBoxColor();
			setBackground(this.panelColor);
		}
	}
	
	/**
	 * <p>Side Effects: adds empty labels in all directions of the border except center so that </p>
	 * <p> Box can be added to the center</p>
	 */
    public void setBorder(){				    //Setting up Background
    	titleL = new JLabel(" ");
    	JLabel east = new JLabel( " ");
    	JLabel south = new JLabel(" ");
    	JLabel west = new JLabel(" ");
    	this.add(titleL, BorderLayout.NORTH);
    	this.add(east, BorderLayout.EAST);
    	this.add(south, BorderLayout.SOUTH);
    	this.add(west, BorderLayout.WEST);
   
   
    }
    
    /**
     * 
     * @param title
     * <p> Side Effects: If a non-null string is provided it sets the title to that input String and 
     * <p> replaces the north part of the layout</p>
     * <p> Special Note: Font of label is BOLD</p>
     */
    public void setTitle(String title ) {			//ADD TITLE
        if (title == null){        //user do not want to set a title
        	return;
        }
        else if (title != null) {   //title is present
        	this.remove(titleL);
        	titleL = new JLabel(title);
        	font = new Font("Courier", Font.BOLD, 12);
        	titleL.setFont(font);
        	this.add(titleL, BorderLayout.NORTH);
        	added = true;
        }
    }

   /**
    * 
    * @param borderColor
    * <p> Side effect: if borderColor is valid, it updates the color of the border</p>
    */
    public void setBorderColor(Color borderColor){		 //ADD BORDER  = setting "background" color
		if (borderColor == null ) {
			return;
		}
		else {
		this.panelColor = borderColor;
		this.setBackground(borderColor);
		added =true;
		
		}

    }
    
    /**
     * @return boolean of the flag if the border has been added or not
     */
    public boolean getStatus(){
    	return added;
    }
    
    /**
     * param g used by JPanel to paint the shape of a round rectangle
     * <p> Special Note: inherited indirectly from JPanel</p>
     * <p> Those who wish to use border, should override this method so that they can set the shapes for their</p> 
     * <p> respective themes and alter the conditions to do so</p>
     */
    @Override
    public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.panelColor);		
		
		if (theme.equalsIgnoreCase("pastel")) {			//round rectangle shape for pastel
			g2.fillRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);
			g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1, 20, 20);
		}
		else if (theme.equals("summer")){	//ellipse shape for summer
			g2.fillOval(-30, 0, getWidth()+50, getHeight());
			g2.drawOval(-30, 0, getWidth()+50, getHeight());
		}
															
	}
 
}
