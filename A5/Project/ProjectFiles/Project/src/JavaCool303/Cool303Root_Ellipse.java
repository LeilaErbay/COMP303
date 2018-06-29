package JavaCool303;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <h2> Cool303Root_Ellipse </h2>
 * @author LeilaErbay
 * <p> Special Note: extends Cool303Root</p>
 */
public class Cool303Root_Ellipse extends Cool303Root{
	
	private ArrayList<Cool303Box_Ellipse> boxes;		//list of boxes
	private ArrayList<Cool303Border> borders;			//list of borders
	private int numBoxes;
	private int borderNum;
	private InnerPanel innerPanel;						//inner panel
	
	/**
	 * 
	 * @param s represents the theme that will be used in Root_Ellipse
	 * @throws InvalidThemeException if s is not Theme_Summer
	 * <p> precondition: theme must be Cool303Theme_Summer</p>
	 * <p> Side effect: throws exception if theme's string is not summer</p>
	 * <p> instantiates the colors and the boxes list and borders lists</p>
	 */
	public Cool303Root_Ellipse(Cool303Theme s) throws InvalidThemeException{
		super(s);
		if (!Cool303Theme.getTheme().equalsIgnoreCase("summer")) {
			throw new InvalidThemeException ("Ellipse must be associated with Summer theme");
		}
		rootColor = Cool303Theme_Summer.getRootColor();
		boxColor = Cool303Theme_Summer.getBoxColor();
		buttonColor = Cool303Theme_Summer.getButtonColor();
		boxes = new ArrayList<>();
		borders = new ArrayList<>();

	}
	
	
	/**
	 * @param numBoxes the number of boxes the user would like to add
	 * @param numButtons the number of buttons per box the user would like to add
	 * <p>precondition if numBoxes is less than 0 do nothing</p> 
	 * <p> Side effects: instantiates an InnerPanel and adds the inner panel into the Root</p>
	 * <p> instantiates a box each iteration and adds it to the list</p>
	 */
	@Override
	public void fillBoxes(int numBoxes, int numButtons){
		if (numBoxes <=0) return;
		this.numBoxes = numBoxes;
		innerPanel = new InnerPanel(rootColor,numBoxes, new Ellipse2D.Double(0, 0, getWidth()-1, getHeight()-1));
		this.add(innerPanel);
		for (int i = 0; i < numBoxes; i++){
			Cool303Box_Ellipse box = new Cool303Box_Ellipse(this.boxColor, numButtons, this.buttonColor);
			box.fillList();
			box.fillPanel();
			boxes.add(box);
		}
	}
	
	/**
	 *  <p> Side effects: instantiates a box each iteration and sets its theme before adding it</p>
	 *  <p> to the list of borders</p>
	 */
	@Override
	public void fillBorders(){
		if (numBoxes <=0) return;
		for (int i = 0; i < numBoxes; i++ ){
			Cool303Border b = new Cool303Border("summer");
			b.setTheme();
			borders.add(b);
		}
	}
	
	
	/**
	 * <p> Side effects: sets the border color of the box color at each index</p>
	 * <p> adds each box to its respective border </p>
	 * <p> adds each border to the inner panel</p>
	 */
	@Override
	public void fillPanel(){
		for (int i = 0; i < numBoxes; i++){
			Cool303Box_Ellipse box =  boxes.get(i);
			Cool303Border border = borders.get(i);
				border.setBorderColor(boxColor);
				border.add(box);
				
				this.innerPanel.add(border);
		}
	}
	
	/**
	 * @param i the index of the border that is desired
	 * @return Cool303Border from the list at the specified index i
	 */
	@Override
	public Cool303Border getBorder(int i){
		return borders.get(i);
	}

	/**
	 * @param i the border of the border list at index i
	 * @param c the color of the border at index i that will be set
	 * <p> Side effects: updates the border at index i with the new color c</p>
	 */
	@Override
	public void setBorderBackground(int i, Color c){
		borders.get(i).setBorderColor(c);
	}
	
	
	/**
	 * @param i the border of the border list at index i
	 * @param title the title of the border at index i that will be set
	 * <p> Side effects: updates the border at index i with the new string title</p>
	 */
	@Override
	public void setBorderTitle(int i, String title){
		borders.get(i).setTitle(title);
	}
	
	/**
	 * @param g Graphics that is called by JPanel
	 * <p> Side effects: draws an ellipse</p>
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.rootColor);		
		g2.fillOval(-30, 0, getWidth()+50, getHeight());
		g2.drawOval(0, 0, getWidth()-1, getHeight()-1);													//draw shape
  
	}	
}

/**
 * <h2> InnerPanel </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends JPanel</p>
 * <p> Nested inside Cool303Root_Ellipse</p>
 */
class InnerPanel extends JPanel{

	private Color color;
	private Shape s;
	
	/**
	 * 
	 * @param rootColor the color of the Root
	 * @param numBoxes the number of boxes
	 * @param shape the shape of the root
	 * <p> Side effects: sets GridLayout, background color, size</p>
	 */
	public InnerPanel(Color rootColor, int numBoxes, Shape shape){
		super(new GridLayout((int)Math.sqrt(numBoxes), (int)Math.sqrt(numBoxes)));
		color= rootColor;
		s = shape;
		setBackground(rootColor);
		setSize(getWidth()-1, getHeight()-1 );
		setOpaque(false);
	
	}
	
	/**
	 * @param g Graphics used by JPanel
	 * <p> Side effects: draws an ellipse</p>
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.color);		
		g2.drawOval(-30, 0, getWidth()+50, getHeight());
		g2.draw(s);													//draw shape
	
	}
	
}
