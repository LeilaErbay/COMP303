package JavaCool303;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

/**
 * <h2> Cool303Root  </h2>
 * @author LeilaErbay
 * <p> Special Note: extends JPanel</p>
 * <p> abstract class</p>
 *
 */
public abstract class Cool303Root extends JPanel {

	protected Color rootColor;			 
	protected Color boxColor;
	protected Color buttonColor;
	protected JPanel panel;			// each child will need an inner panel
	private int w, h;
	
	/**
	 * 
	 * @param t
	 * <p> Side effects: sets its Layout as BORDER LAYOUT</p>
	 * <p> sets up empty labels in all directions of the layout</p>
	 * <p> sets size and assigns w, h</p>
	 */
	public Cool303Root(Cool303Theme t){
		super(new BorderLayout());
		
		setUpRoot();
	   
		Dimension size = getPreferredSize();
		w = h = size.width= size.height= Math.max(size.width, size.height);
        super.setPreferredSize(size);
        
	}
	
	
	/**
	 * <p> Side effects: Adds empty labels in N,S,E,W of the layout so that it can be seen</p>
	 */
	private void setUpRoot(){
		this.add(new JLabel(" "), BorderLayout.NORTH);
	    this.add(new JLabel(" "), BorderLayout.EAST);
	   	this.add(new JLabel(" "), BorderLayout.SOUTH);
	   	this.add(new JLabel(" "), BorderLayout.WEST);
	   
	}
	
	/**
	 * 
	 * @param x represents user's value of a new width
	 * @param y represents user's value of a new height
	 */
	public void setNewSize(int x, int y){
		if (x < w || y < h){				//if the width and /or height are too small, nothing changes
			return;
		}
		else {
			Dimension newSize = new Dimension(x, y);
			super.setSize(newSize);
			addComponentListener(new ResizeListener());
		}
		
	}
	
	
	//METHOD REQUIRED TO BE IMPLEMENTED BY CHILDREN
	protected abstract void fillBoxes(int numBoxes, int numButtons);
	protected abstract  void fillBorders();
	protected abstract  void fillPanel();
	protected abstract  Cool303Border getBorder(int i);
	protected abstract void setBorderBackground(int i, Color c);
	protected abstract void setBorderTitle(int i, String title);
	protected abstract void paintComponent(Graphics g);

	
	class ResizeListener implements ComponentListener{
		public void componentHidden(ComponentEvent e) {
		}

	    public void componentMoved(ComponentEvent e) {
	    }

	    public void componentResized(ComponentEvent e) {
	        System.out.println(e.getComponent().getClass().getName() + " --- Resized ");            
	    }

	    public void componentShown(ComponentEvent e) {
	    }
		
	}
}



