package JavaCool303;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <h2> Cool303Root_RoundRectangle </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends Cool303Root</p>
 *
 */
public class Cool303Root_RoundRectangle extends Cool303Root{

	private ArrayList<Cool303Box_RoundRectangle> boxes;		//will be filled with new Round Rectangle boxes
	private ArrayList<Cool303Border> borders;				//will be filled with new borders
	private int numBoxes;
	private int borderNum;			
	
	/**
	 * 
	 * @param p the Cool303Theme
	 * @throws InvalidThemeException if p is not Theme_Pastel
	 * <p> precondition: Theme must be Cool303Theme_Pastel</p>
	 * <p> Side Effect: throws InvalidThemeException if the the theme string is not "pastel"</p>
	 * <p> instantiates a new JPanel inside the Root that will actually hold the boxes</p>
	 * <p> instantiates 2 arraylists: one for boxes, one for borders</p>
	 */
	public Cool303Root_RoundRectangle(Cool303Theme p) throws InvalidThemeException{
		super(p);
		if (!Cool303Theme.getTheme().equals("pastel")) {
			throw new InvalidThemeException ("RoundRectangleDouble must be associated with pastel theme");
		}
		
		
		rootColor = Cool303Theme_Pastel.getRootColor();				//sets COLORS
		boxColor = Cool303Theme_Pastel.getBoxColor();
		buttonColor = Cool303Theme_Pastel.getButtonColor();
		boxes = new ArrayList<>();									//instantiates box LIST & border LIST
		borders = new ArrayList<>();
		
		panel = new JPanel();
		panel.setBackground(rootColor);
		panel.setSize(getWidth()-1, getHeight()-1 );
		this.add(panel);
	
	}
	

	/**
	 * @param numBoxes the number of boxes to be added
	 * @param numButtons the number of buttons per box
	 * <p> Side Effects: sets the layout for the inner panel </p>
	 * instantiates a new box each loop and adds it to the list
	 * <p> precondition: checks if numBoxes is less than, if true then do nothing</p>
	 */
	@Override
	public void fillBoxes(int numBoxes, int numButtons){		//INSTANTIATE BOXES AND ADD THEM TO THE LIST
		if (numBoxes <= 0) return;
		this.numBoxes = numBoxes;
		this.panel.setLayout(new GridLayout((int)Math.sqrt(numBoxes), (int)Math.sqrt(numBoxes)));
		for (int i = 0; i < numBoxes; i++){
			Cool303Box_RoundRectangle box = new Cool303Box_RoundRectangle(this.boxColor, numButtons, this.buttonColor);
			box.fillList();
			box.fillPanel();
			boxes.add(box);
		}
	}
	
	
	/**
	 * <p> Side Effects: a border is instantiated each iteration and then added to the list of borders</p>
	 * <p> precondition: checks if numBoxes is less than, if true then do nothing</p>
	 */
	@Override
	public void fillBorders(){		//INSTANTIATE BORDERS, SET THEME and ADD TO BORDER LIST
		if (numBoxes <= 0 ) return;
		for (int i = 0; i < numBoxes; i++ ){
			Cool303Border b = new Cool303Border("pastel");
			b.setTheme();
			borders.add(b);
		}
	}

	/**
	 * <p> Side Effects: sets borderColor and adds the box to the border, then adds the border to the inner panel</p>
	 */
	@Override
	public void fillPanel(){
		for (int i = 0; i < numBoxes; i++){
			Cool303Box_RoundRectangle box =  boxes.get(i);
			Cool303Border border = borders.get(i);
			
				border.setBorderColor(boxColor);
				border.add(box);
				this.panel.add(border);
			
		}
	}
	
	/**
	 * @return Cool303Border at the specified index of the list
	 */
	@Override
	public Cool303Border getBorder(int i){
		return borders.get(i);
	}

	/**
	 * @param i the index of the border that will be set
	 * @param c the new color of the specific border that will be set
	 * <p> Side effect: alters the border in the border list at i with the color c</p>
	 */
	@Override
	public void setBorderBackground(int i, Color c){
		borders.get(i).setBorderColor(c);
	}
	
	/**
	 * @param i the index of the border that will be altered
	 * @param title the string of the border's new title
	 * <p> Side effect: alters the border in the border list at i with title</p>
	 */
	@Override
	public void setBorderTitle(int i, String title){
		borders.get(i).setTitle(title);
	}
	
	/**
	 * @param g Graphics used by JPanel
	 * <p> Side effects: draws a round rectangle</p>
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.rootColor);		
		g2.fillRoundRect(0,0, getWidth()-1, getHeight()-1, 20, 20);
		g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);													//draw shape
  
	}
	

}


	

		
	
	
	
	


