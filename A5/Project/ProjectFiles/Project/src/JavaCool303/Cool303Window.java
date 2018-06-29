package JavaCool303;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h2> Cool303Window </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends JFrame</p>
 * <p> creates a JPanel to hold the roots</p>
 *
 */
public class Cool303Window extends JFrame {

	private ArrayList<Cool303Root> roots;	
	private String theme;
	private Cool303Theme_Pastel p;
	private Cool303Theme_Summer s;
	private int numRoots;
	private JPanel panel;
	
	/**
	 * <p> Side Effect:  sets empty areas of the root and adds the JPanel to the frame</p>
	 */
	public Cool303Window() {
		super();
		
		 panel = new JPanel();
		 setLocation(200,250);
	     setSize(1000,1000);
	     //setResizable(false);
	     setUpPanel();
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	}
	
	
	/**
	 *<p> Side effect: Calls super.setVisible(boolean aFlag)</p>
	 */
	public void setVisible(){
		super.setVisible(true);
	}
	
	
	/**
	 * @param theme the string of the theme
	 * @throws InvalidThemeException
	 * <p> Side effects: based on the theme string it instantiates the respective array and Cool303Theme
	 */
	public void setTheme(String theme) throws InvalidThemeException{
		this.theme = theme;
		if(theme.equalsIgnoreCase("pastel")){			//if one were to create new themes, they would need to add more conditions
			roots = new ArrayList<>();
			p = new Cool303Theme_Pastel();
		}
		else if (theme.equalsIgnoreCase("summer")){
			roots = new ArrayList<>();
			s = new Cool303Theme_Summer();
		}
		else{
			throw new InvalidThemeException(this.theme + "does not exist. Please create a new theme and the respective components");
		}
	}
	
	
	/**
	 * <p> Side effect: sets empty Labels in all directions, and adds the JPanel to the center</p>
	 * <p> Special Notes: called in the constructor</p>
	 */
	private void setUpPanel(){
    	this.add(new JLabel(" "), BorderLayout.NORTH);
    	this.add(new JLabel(" "), BorderLayout.EAST);
    	this.add(new JLabel(" "), BorderLayout.SOUTH);
    	this.add(new JLabel(" "), BorderLayout.WEST);
    	panel.setSize(this.getWidth()-1, this.getHeight()-1);
    	this.add(panel, BorderLayout.CENTER);
	}
	

	/**
	 * 
	 * @param numRoots the number of roots the user would like to add
	 * @param numBoxes the number of boxes the user would like to add
	 * @param numButtons the number of buttons the user would like to add
	 * <p> Side effects: </p>
	 * <p> if the theme is pastel, it instantiates a new RootRectangle Root and adds it to the list of roots, 
	 *     which is added to the JPanel</p>
	 * <p> if the theme is summer, it instantiates a new Ellipse Root and adds it to the list of roots, 
	 *    which is added to the JPanel</p>
	 * <p> catches an exception that may be thrown by Root</p>
	 * <p> edits and updates the frame</p>
	 */
	public void setUpRoots(int numRoots, int numBoxes, int numButtons){
		try{
		panel.setLayout(new GridLayout((int)Math.sqrt(numRoots), (int)Math.sqrt(numRoots)));
			for (int i = 0; i< numRoots; i++){
			
			if (theme.equalsIgnoreCase("pastel")) {
				Cool303Root_RoundRectangle r = new Cool303Root_RoundRectangle(p);
				r.fillBoxes(numBoxes,numButtons);
				r.fillBorders();
				r.fillPanel();
				roots.add(r);
				panel.add(r);
			}
			else if (theme.equalsIgnoreCase("summer")){
				Cool303Root_Ellipse r = new Cool303Root_Ellipse(s);
				r.fillBoxes(numBoxes,numButtons);
				r.fillBorders();
				r.fillPanel();
				roots.add(r);
				panel.add(r);
			}
			
		 }
		repaintWindow();
		}
		catch(Exception e){
			System.out.println("Error occurred in creating the window:" + e.getMessage());
		}
		
	}

	
	/**
	 * 
	 * @param title the title that is to be added to the border
	 * @param borderNum the border that will be altered
	 * @param rootNum the root whose border will be altered
	 * <p> Side effects: updates the frame</p>
	 */
	public void addBorderTitle(String title, int borderNum, int rootNum){
		if (roots.isEmpty())return;
		roots.get(rootNum).setBorderTitle(borderNum, title);
		repaintWindow();
	}
	
	/**
	 * 
	 * @param borderColor the border color that is be added to border
	 * @param borderNum the border that is to be altered
	 * @param rootNum the root whose border is to be altered
	 */
	public void setBorderColor( Color borderColor, int borderNum, int rootNum){
		if (roots.isEmpty())return;
		roots.get(rootNum).setBorderBackground(borderNum, borderColor);
		repaintWindow();
	}
	
	/**
	 * 
	 * @param rootNum the root whose size the user wants to alter
	 * @param x the new width of the root
	 * @param y the new height of the root
	 * <p> Side effects: updates the frame</p>
	 */
	public void resizeRoot(int rootNum, int x, int y){
		roots.get(rootNum).setNewSize(x, y);
		repaintWindow();
	}
	
	/**
	 * <p> Special Notes: updates the frame </p>
	 */
	public void repaintWindow(){
		revalidate();
		repaint();
	}
	
}
/**
 * <h2> InvalidThemeException </h2>
 * @author LeilaErbay
 * <p> Special Notes: Extends Exception</p>
 */
class InvalidThemeException extends Exception{
	
	/**
	 * @param msg the string that will inform the user with what went wrong
	 */
	InvalidThemeException(String msg){
		super(msg);
	}
}
