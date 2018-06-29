package JavaCool303;

import java.awt.*;
import javax.swing.*;

/**
 * <h2> Cool303Theme </h2>
 * @author LeilaErbay
 * <p> Special Note: abstract class</p>
 * <p> Children will have to instantiate colors and string of the theme</p>
 */
public abstract class Cool303Theme {

	protected static Color rootColor;
	protected static Color boxColor;
	protected static Color buttonColor;
	protected static String theme;
	
	/**
	 * 
	 * @return Color color of root
	 */
	public static Color getRootColor(){
		return rootColor;
	}
	
	/**
	 * 
	 * @return Color color of box
	 */
	public static Color getBoxColor(){
		return boxColor;
	}
	
	/**
	 * 
	 * @return Color color of button
	 */
	public static Color getButtonColor(){
		return buttonColor;
	}
	
	/**
	 * 
	 * @return String the type of the theme
	 */
	public static String getTheme(){
		return theme;
	}
}

/**
 * <h2> IncorrectThemeException </h2>
 * @author LeilaErbay
 * <p> Special Notes: extends Exception</p>
 * <p> nested inside Cool303Theme </p>
 */
class IncorrectThemeException extends Exception{
	
	/**
	 * @param msg the message that will inform the user what went wrong
	 */
	public IncorrectThemeException(String msg){
		super(msg);
	}
}
