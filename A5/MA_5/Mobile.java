/**
 * <h2> Mobile </h2>
 * @author Leila Erbay
 * <p> Special notes: extends Item class </p>
 * <p> abstract class </p>
 */
public abstract class Mobile extends Item {
    private int direction = 0;
    private int moved = 0;
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
    Mobile(String name, char token, int x, int y, int xBound, int yBound) throws InvalidCoordinateException{
        super(name, token, x, y, xBound, yBound);
    }

    
    // setting the isMoveable attribute to true
    {
        this.isMoveable = true;
    }

    /**
     * @author Leila Erbay
     * @param int the direction of the movement
     * <p> precondition: direction can't be less than 1 or greater than 4 </p>
     * <p> postcondition: if x,y coordinates are not within then error </p>
     * <p> side effect: throws Invalid Coordinate exception & invalid direction exception </p>
     */
    protected void setNewCoordinate(int direction) throws InvalidCoordinateException, InvalidDirectionException{
        int xCoord = super.getX();
        int yCoord = super.getY();    

        //checking precondition
        if (direction < 1 || direction > 4){
            throw new InvalidDirectionException("An invalid direction was attempted to be set. \n No changes have been made.");
        }

        switch(direction){         
            //north: decrease row and keep col
            case 1: xCoord = xCoord - 1;
                    break;

            //south: increase row and keep col 
            case 2: xCoord = xCoord + 1;    
                    break;
                
            //east: keep row and increase col
            case 3: yCoord = yCoord + 1;
                    break;  

            //west: keep row and decrease row    
            case 4: yCoord = yCoord - 1;
                    break;
                
        }

       

        //if y < boundary or > boundary / x < boundary or > boundary don't set any coordinates just say that there is not space to move
        if (!super.isWithinBoundary(xCoord, yCoord)){
        
            String dir = "";
            switch(direction){
                case 1: dir = "North";
                        break;
                case 2: dir = "South";
                        break;
                case 3: dir = "East";
                        break;
                case 4: dir = "West";
                        break;
            }
            throw new InvalidCoordinateException("Not enough space to step towards the " + dir);
        }

        else{
            super.setX(xCoord);
            super.setY(yCoord);
        }
        
    }

    /**
     * @author Leila Erbay
     * <p> special notes: used to indicate if an autonomous object has already been moved </p>
     */
    public void setMoved(){
        moved = 1;
    }
    
    /**
     * @author Leila Erbay
     * <p> special notes: used to set a moved object to unmoved </p>
     */
    public void setUnmoved(){
        moved = 0;
    }

    /**
     * @author Leila Erbay
     * @return the value if the object is moved or not
     */
    public int getMoved(){
        return moved;
    }

    /**
     * @author Leila Erbay
     * @param int the value of the direction
     * <p> special notes: used to set the direction for the step function
     */
    public void setDirection (int d){
        this.direction = d;
    }

    /**
     * @author leila Erbay
     * @return the value of the direction
     */
    public  int getDirection (){
        return this.direction;
    }

     /**
     * @author Leila Erbay
     * @return int representing a random direction 
     * <p> special note: </p>
     * <p> 0 - no direction </p>
     * <p> 1 - North </p>
     * <p> 2 - South </p>
     * <p> 3 - East </p>
     * <p> 4 - West </p> 
     */
   
    public static int randomDirection(){ 
        return  ((int) (Math.random() * 4 + 1));
    }

    /**
     * @author Leila Erbay
     * @param int the direction the coordinate should move to
     * <p> side effects: calling setNewCoordinate will either set new coordinates or throw an exception </p>
     */
    public void bump(){
        try{
            int direction = getDirection();
            setNewCoordinate(direction);
        }
        catch(Exception e){
            System.out.println("Error occurred trying to set new coordinates.");
        }

    }

}


/**
 * <h2> InvalidDirectionException </h2>
 * @author Leila Erbay
 * <p> special notes: extends Exception </p>
 */
class InvalidDirectionException extends Exception{

    /**
     * @author Leila Erbay
     * @param String the message to be used to signal why the exception that was caused
     */
    InvalidDirectionException(String msg){
        super(msg); 
    }
}