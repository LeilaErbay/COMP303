package JavaCool303;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <h2> Cool303Button </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends JButton</p>
 * <p> abstract class</p>
 *
 */
public abstract class Cool303Button extends JButton {
    
    protected JLabel buttonLabel;
    protected Shape shape;
    protected Color color; 
    

    /**
     * 
     * @param text the title of the button
     * @param color the color of the button
     * <p> Side Effects: adds the Label to the Button</p>
     * <p> sets Size, sets an ActionCommand, adds  Action Listener</p>
     */
    public Cool303Button(String text, Color color){
    	buttonLabel = new JLabel(text, CENTER);              //label that will appear when button is clicked
    	add(buttonLabel);                                            //sets the shape
        this.color = color;                                          //sets the color

        Dimension size = super.getPreferredSize();
        size.width= size.height= Math.max(size.width, size.height);
        super.setPreferredSize(size);
        super.setContentAreaFilled(false);
        super.setBorderPainted(false);  	
        setActionCommand(text);							//used for the button when it is clicked
        addActionListener(new ButtonClickListener());	//used for the button when it is clicked

    }

    //METHODS REQUIRED in CHILDREN
	protected abstract void paintComponent(Graphics g);
	protected abstract void paintBorder(Graphics g);

/**
 * <h2> ButtonClickListener </h2>
 * @author LeilaErbay
 * <p> Special Notes: implements Action Listener interface</p>
 * <p> nested inside Cool303Button
 *
 */
class ButtonClickListener implements ActionListener{
	@Override
	
	/**
	 * @param ActionEvent the event of clicking a button
	 * <p> Side Notes: actionPerformed is a method in ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		System.out.println("Button " + command + " was pressed");
		}
	
	}
}
