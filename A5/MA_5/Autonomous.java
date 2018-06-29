/**
 * <h2> Autonomous </h2>
 * @author Leila Erbay
 * <p> special notes: extends Mobile which extends Item (ie family member of Item) </p>
 */
public class Autonomous extends Mobile {
   
    
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
    Autonomous(int x, int y, int xBound, int yBound) throws InvalidCoordinateException{
        super("Autonomous", 'a', x, y, xBound, yBound);
    }

    public void step(){
        try{
            int direction = Mobile.randomDirection();
            setNewCoordinate(direction);
        }
        catch(Exception e){
            System.out.println("Error occurred trying to set new coordinates.");
        }

    }

   
  


   

    

}