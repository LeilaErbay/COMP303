/**
 * <h2> Moveable </h2>
 * @author Leila Erbay
 * <p> special notes: extends Mobile which extends Item (ie. family member of Item) </p>
 */
public class Moveable extends Mobile{

    /**
     * @author Leila Erbay
     * @param String represents the name of the object
     * @param char represents the char token
     * @param int represents the x coordinate
     * @param int represents the y coordinate
     * @param int represents the x boundary
     * @param int represents the y boundary
     * <p> Special notes: calls super constructor of Item</p>
     */
    Moveable(int x, int y, int xBound, int yBound) throws InvalidCoordinateException{
        super("Moveable", 'm', x, y, xBound, yBound);
    }

    
}