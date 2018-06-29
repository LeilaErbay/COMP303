/**
 * <h2> Immoveable </h2>
 * @author Leila Erbay
 * <p> special notes: only extends Item because it can not be moved </p>
 */
public class Immoveable extends Item{
    
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
    Immoveable(int x, int y, int xBound, int yBound) throws InvalidCoordinateException{
        super("Immoveable", 'i', x, y, xBound, yBound);
    }

    //sets moveable status to false
    {
        this.isMoveable = false;
    }
}