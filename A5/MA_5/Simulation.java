import java.lang.Math;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * <h2> Simulation </h2>
 * @author Leila Erbay
 * <p> special notes: </p>
 */
public class Simulation {
    private static int  xBoundary;
    private static int  yBoundary;
    private static int i = 0;

    /**
     * @author Leila Erbay
     * @param int value of the x boundary
     */
    private static void setXBound (int x){
        xBoundary = x;
    }

    /**
     * @author Leila Erbay
     * @return the x boundary value
     */
    private static int getXBound(){
        return xBoundary;
    }

    /**
     * @author Leila Erbay
     * @param int sets the value of the boundary
     */
    private static void setYBound(int y){
        yBoundary = y;
    }

    /**
     * @author Leila Erbay
     * @return the y boundary value
     */
    private static int getYBound(){
        return yBoundary;
    }


    /**
     * @author Leila Erbay
     * @return world that is randomly filled with hard-coded numbers of Autonomous, Moveable, and Immoveable Objects
     */
    private static World buildWorld(){
        
        setXBound(20);
        setYBound(20);

        World world = World.getInstance();
        World.setSize(getXBound(), getYBound());

        world.createWorld();

        fillWorldRandomly("Autonomous", 5, world);      //5 autonomous
        fillWorldRandomly("Moveable", 3, world);        //3 Moveable
        fillWorldRandomly("Immoveable", 2, world);      //2 Immoveable

        return world;
        
       
    }

    /**
     * @author Leila Erbay
     * @param char determines which of the coordinates to create
     * @return returns the value of the coordinate
     * <p> helper method to randomize the placement of items </p>
     */
    private static int randomCoord(char axis){
        int coord = 0;
        if (axis == 'x'){
            coord =  ( (int) (Math.random() * Simulation.getXBound() + 0) ); 
        }
        else if (axis == 'y'){
            coord = ( (int) (Math.random() * Simulation.getYBound() + 0) );
        }
        return coord;
    }

    /**
     * @author Leila Erbay
     * @param String sets the type of item to be created
     * @return Item of type Automous, Moveable, Immoveable
     * <p> side effect: exception may be thrown by one of the items</p>
     */
    private static Item createItem (String type) {
        Item item = null;
        try{
         
            int xBounds = Simulation.getXBound();
            int yBounds = Simulation.getYBound();

            if (type.equals("Autonomous")){         //CREATE AUTONOMOUS
                item = new Autonomous(randomCoord('x'), randomCoord('y'), xBounds, yBounds );
                return (Autonomous) item;
            }

            if (type.equals("Moveable")){           //CREATE MOVEABLE
                item = new Moveable (randomCoord('x'), randomCoord('y'), xBounds, yBounds);
                return (Moveable) item;
            }

            if (type.equals("Immoveable")){         //CREATE IMMOVEABLE
                item = new Immoveable(randomCoord('x'), randomCoord('y'), xBounds, yBounds);
                return (Immoveable) item;
            }
            else { 
                item = null;
            }
        }
        catch(Exception e){
            System.out.println("Could not fill world");
        }
        return item;
    }
   

    /**
     * @author Leila Erbay
     * @param String determines the type of the item
     * @param int determines the number of objects to be created and added to the world
     * @param World the world to be filled
     */
    private static void fillWorldRandomly(String type, int noObjects, World w){
        for (int i = 0; i < noObjects; i++){
            Item item = createItem(type);
            w.add(item, item.getX(), item.getY());       
        }
    }


    /**
     * @author Leila Erbay
     * @param String[] 
     * <p> Special notes: creates a world with 10 objects and displays this world and runs a simulation for 100 loops
     * <p> asking the user if 100 runs if they'd like to run the simulation again.
     */
    public static void main(String [] args){
        try{

            Scanner input = new Scanner(System.in);
            World world =  buildWorld();    //world will contain 10 items 
            world.setUpDisplay();

            int run = 1;                            
            while (run == 1){                       //RUNNING THE SIMULATION
                for (int i = 0; i < 100; i++ ){
                    world.step();
                    TimeUnit.SECONDS.sleep(1);
                    world.display();
                }

                System.out.println("Would you like to run the simulation again?");
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase("No") || answer.equalsIgnoreCase("n")) {            //asking user to run the simulation again or not
                    run = 0;
                }
                else if ( answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")){
                    run = 1;
                }   
            }
            input.close();
        }
        catch(NullPointerException e){
            e.printStackTrace() ;
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}