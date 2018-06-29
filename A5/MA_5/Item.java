/**
 * <h2> Item </h2>
 * @author Leila Erbay
 * <p> Purpose: An abstract class created for the objects to be placed in the world </p>
 * <p> Side effects: if the x coordinate or y coordinate are negative then an exception is thrown </p>
 * <p> Special notes: </p>
 */
public abstract class Item {
    private String name;
    private char token;
    private int x;
    private int y;
    private int xBoundary;
    private int yBoundary;

    protected boolean isMoveable = false;

    /**
     * @author Leila Erbay
     * @param String represents the name of the object
     * @param char represents the char token
     * @param int represents the x coordinate
     * @param int represents the y coordinate
     * @param int represents the x boundary
     * @param int represents the y boundary
     * <p> Side effects: Throws exception if x or y coordinate are negative </p>
     * <p> Special notes: </p>
     */
    Item(String n, char t, int xCoord, int yCoord, int xBound, int yBound) throws InvalidCoordinateException{
        this.name = n;
        this.token = t;
        this.xBoundary = xBound;
        this.yBoundary = yBound;

        if (!isWithinBoundary(xCoord, yCoord)){
            throw new InvalidCoordinateException("An invalid coordinates was attempted to be set. \n No coordinates have been set for this item.");
        }

        this.x = xCoord;
        this.y = yCoord;
    }            

    /**
     * @author Leila Erbay
     * @return char token
     */
    public char getToken() {
        return this.token;
    }

    /**
     * @author Leila Erbay
     * @param int represents the new value of the x coordinate
     * <p> side effect: throws exception if xValue is negative </p>
     */
    public void setX(int xValue) throws InvalidCoordinateException{
        if (!isValidCoordinate(xValue) || isXGreaterThanBounds(xValue)){
            throw new InvalidCoordinateException("invalid: x coordinate was unable to be set");
        }
        this.x = xValue;
    }

    /**
     * @author Leila Erbay
     * @return int the current x coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * @author Leila Erbay
     * @param int represents the new value of y coordinate 
     * <p> precondition the y coordinate being set must be wihtin boundary</p>
     * <p> Side effect: throws exception if yValue is negative </p>
     */
    public void setY(int yValue) throws InvalidCoordinateException{
        if (!isValidCoordinate(yValue) || isYGreaterThanBounds(yValue)){
            throw new InvalidCoordinateException("invalid:  y coordinate was unable to be set");
        }

        this.y = yValue;
    }

    /**
     * @author Leila Erbay
     * @return int the current y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * @author Leila Erbay
     * @param int x boundary value
     * @param int y boundary value
     * <p> Side effects: updates the attributes xBoundary and yBoundary </p>
     */
    public void setBoundary(int xBounds, int yBounds){
        this.xBoundary = xBounds;
        this.yBoundary = yBounds;

    }

    /**
     * @author Leila Erbay
     * @return the value of the x boundary
     */
    public int getXBound(){
        return this.xBoundary;
    }

    /**
     * @author Leila Erbay
     * @return value of the y boundary
     */
    public int getYBound(){
        return this.yBoundary;
    }

    /**
     * @author Leila Erbay
     * @param int either an x coordinate or a y coordinate
     * @return boolean determined if the the coordinate is negative or not
     */
    public boolean isValidCoordinate(int coord) {
        if (coord < 0) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @author Leila Erbay
     * @param int xCoord represents the current x coordinate 
     * @param int yCoord represents the current y coordinate
     * @return boolean the result of testing if the xCoords are within the boundary of the world
     */
    public boolean isWithinBoundary(int xCoord, int yCoord){
        if (isGreaterThanBoundary(xCoord, yCoord) || !isValidCoordinate(xCoord)|| !isValidCoordinate(yCoord)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @author Leila Erbay
     * @param int the value of the x coordinate being tested
     * @param int the value of the y coordinate being tested 
     * @return boolean determined by testing if the x, y coordinates are greater than the x, y boundary
     */
    public boolean isGreaterThanBoundary(int xCoord, int yCoord){
        if (isXGreaterThanBounds(xCoord) || isYGreaterThanBounds(yCoord)) return true;
        else return false;
    }


    /**
     * @author Leila Erbay
     * @param int x coordinate being tested if it is greater than the x boundary
     * @return boolean from the comparison
     */
    public boolean isXGreaterThanBounds(int xCoord) {
        if (xCoord >= this.xBoundary) return true;
        else return false;
    }

    /**
     * @author Leila Erbay
     * @param int y coordinate being tested if it is greater than the y boundary
     * @return boolean from the comparison
     */
    public boolean isYGreaterThanBounds(int yCoord){
        if (yCoord >= this.yBoundary) return true;
        else return false;
    }

    /** 
     * @author Leila Erbay
     * @return boolean the isMoveable attribute
     */
    public boolean getMoveable(){
        return isMoveable;
    }

    
}

/**
 * <h2> InvalidCoordinateException </h2>
 * @author Leila Erbay
 * <p> Side Effect: </p>
 * <p> Special notes: Custom Exception </p>
 */
class InvalidCoordinateException extends Exception {
    InvalidCoordinateException(String msg){
        super(msg);
    }
}