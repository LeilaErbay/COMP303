import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Leila Erbay
 * <p> Special notes: contains a 2D array of Items, which can contain Moveable, Autonomous, Immoveable </p>
 */
public class World{
    private static Item [][] world;
    private JFrame mainFrame ;
    private JPanel grid;
    private JLabel label;
    private Item [] world1D;
    

   /**
    * @author Leila Erbay
    * <p> Special notes: sets the size of the 2D array with the values of the parameters</p>
    */
    private World(int rows, int cols){
        world = new Item[rows][cols];
    }

    //instantiates the World once
    private static World instance = new World(0,0);

    /**
     * @return World
     * <p> Special note: returns instance </p>
     */
    public static World getInstance(){ 
        return instance;
    }

    /**
     * @param rows
     * @param cols
     * <p> Special note: allows user to directly set the size of the world
     */
    public static void setSize(int rows, int cols){
        world = new Item[rows][cols];
    }
    /**
     * @author Leila Erbay
     * <p> special notes: uses swing to display a 2D world </p>
     */
    public void display(){
        grid = new JPanel( new GridLayout(world.length, world[0].length, 2, 2) );
        grid.setBackground( Color.BLACK );                                          //set up the 2D grid
        grid.setBorder( new MatteBorder(2, 2, 2, 2, Color.BLACK) );

        convertTo1D();                                          //convert world to 1D array


        for (int index = 0; index < world1D.length; index++){      // fill in the grid
            label = new JLabel("", JLabel.CENTER);
            label.setSize(50,50);
            label.setFont(new Font("Serif", Font.PLAIN, 25));
            if (world1D[index] == null){
                label.setText("");
            }
            else{
                label.setText("" + world1D[index].getToken());
            }
            label.setOpaque( true );
            
            grid.add( label );
                    
        }

        mainFrame.add(grid);
        mainFrame.setVisible(true);

    }    

    public void setUpDisplay(){
        mainFrame = new JFrame ("The World");
        mainFrame.setSize(world.length+800, world[0].length+800);                   //set the size of the window
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
       
    }

     /**
     * @author Leila Erbay
     * <p> special notes: turns the world into a 1D array to be used in display() </p>
     */
    private void convertTo1D(){
        world1D = new Item [(world.length * world[0].length)];
        for (int row = 0; row < world.length; row++){
            for (int col = 0; col < world[row].length; col++){
                int index = (row * world[row].length) + col;
                world1D[index] = world[row][col];

            }
        }   
    }
    

    /**
     * @author Leila Erbay
     * @param Item the object being added to the world
     * @param int the x coordinate of the item
     * @param int the y coordinate of the item
     */
    public void add(Item i, int x, int y){
        try{
            if (world[x][y] == null) {      // if the cell is empty add the item toc the cell
                if (i == null) {            
                    world[x][y] = null;
                }
                else{
                    world[x][y] = (Item)i;       
                }
            }
            else {                          //if cell is not empty find an empty space
                int [] coords = findEmptySpace();
                int xCoord = coords[0];
                int yCoord = coords[1];
                
                if (xCoord < 0 || yCoord < 0) {
                    System.out.println("item:" + i.getToken() + " " + i.getX() + ", " + i.getY()+ " was not added to the world");
                    return;
                }
                else{
                    world[xCoord][yCoord] = i;  
                    i.setX(xCoord);
                    i.setY(yCoord);
                }
            }
        }
        catch(Exception e){
            System.out.println("Something went wrong trying to place an item:" + i.getToken() + " " + i.getX() + ", " + i.getY());
        }
    
    }

    /**
     * @author Leila Erbay
     * @return int [] is the pair of coordinates for an empty space
     */
    private int[] findEmptySpace(){
        int [] coordinates = new int [2];
        for (int r = 0; r < world.length; r++){
            for (int c = 0; c < world[r].length; c++){
                if( world[r][c] == null){       
                    coordinates[0] = r;
                    coordinates[1] = c;
                    return coordinates;
                }
                else{                           //if no empty space return -1,-1 
                    coordinates [0] = -1;
                    coordinates[1] = -1;
                    return coordinates;
                }
            }
        }
        return coordinates;  
    }

    /**
     * @author Leila Erbay
     * <p> special notes: creates a world that is empty <p>
     */
    public void createWorld(){
        for (int row = 0; row < world.length; row++){
            for (int col = 0; col < world[row].length; col++){

                add(null, row, col);
            }
        }
    }


    /**
     * @author Leila Erbay
     * <p> Special notes: 
     * <p> sets the direction randomly
     * <p> extracts an item if it is not null
     * <p> if the item is autonomous we move it if it hasn't already been moved
     * <p> set all autonomous items to not moved after shifting 1 step
     */
    public void step() throws InterruptedException{
    
        for (int row = 0; row < world.length; row ++){
            for (int col = 0; col < world.length; col++){
                if (world[row][col] != null){
                    Item object  = world[row][col];     //retrieve the object

                    if (object.isMoveable == true){     //determine if this object can be moved
                    
                        if (object.getToken() == 'a' ){
                            Autonomous item = (Autonomous) object;         //cast object to Autonomous
                            if (item.getMoved() == 0){  //if object has not yet been moved

                                int x = item.getX();
                                int y = item.getY();
                                int d= Mobile.randomDirection();         //set the direction

                                if (canMove(d,x,y)){        //determine if the object can move based on the location
                                    
                                    shift(d, x, y);         //if so, then shift everything necessary accordingly
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        
        resetAllMobile();               //set all autonomous to unmoved after moving by 1 step
                        
    }


    /**
     * @author Leila Erbay
     * @param int the direction of the step
     * @param int value of the item's x coordinate
     * @param int valu of the item's y coordinate
     * @return boolean if the step is possible
     */
    private boolean canMove(int direction, int x, int y) {        //if next cell is empty return true, if there is an immoveable return false
        if (world[x][y] == null) return false;
        if (direction == 1){    //NORTH
            
            if (world[x][y].getX() == 0) return false;              //if at an edge it can't move up
            else if (world[x-1][y] == null) return true;            //if there is an empty space next to it then it can move
            else if (world[x-1][y].getToken()== 'i') return false;  //if there is an immovable item next to it then it can't move
            else return checkSingleArray(direction, x, y);          //checks if there is an empty space in array before an immoveable
            
        }
        if (direction == 2){       //SOUTH
            if (world[x][y].getX() == world.length-1) return false;
            else if(world[x+1][y] == null ) return true;
            else if (world[x+1][y].getToken()== 'i') return false;
            else return checkSingleArray(direction, x, y);
            
        }
        if (direction == 3){    //EAST
            if (world[x][y].getY() == world[x].length-1) return false;
            else if(world[x][y+1] == null) return true;
            else if (world[x][y+1].getToken()== 'i') return false;
            else return checkSingleArray(direction, x, y);   
        }

        if (direction == 4){    //WEST
            if (world[x][y].getY() == 0) return false;
            else if (world[x][y-1] == null) return true;
            else if (world[x][y-1].getToken()== 'i') return false;
            else return checkSingleArray(direction, x, y);
            
            
        }
        return false;
    }

    /**
     * @author Leila Erbay
     * @param int the direction of the step
     * @param int value of the item's x coordinate
     * @param int valu of the item's y coordinate
     * @return boolean if the step is possible
     * <p> special note: checks if there is an empty space next to an autonomous or moveable object </p>
     */
    private boolean checkSingleArray(int direction, int x, int y){  //check if there is an empty space next to moveable or autonomous
        if (direction == 1){    //NORTH
            for (x = x-1; x > 0; x--){  
                if (x == 0 )   return false;
                else if(world[x+1][y].getToken() =='a' && (world[x][y].getToken()== 'a'  || world[x][y].getToken()== 'm') && world[x-1][y] == null){  //check if there is any empty space
                    return true;
                }                            
            }
        }
        if (direction == 2){    //SOUTH
            for (x = x+1; x < world.length; x++){
                if (x == world.length-1) return false;
                else if(world[x-1][y].getToken() =='a' && ((world[x][y].getToken()== 'a'  || world[x][y].getToken()== 'm')) && world[x+1][y] == null){
                    return true;
                }
            }
        }
        if (direction == 3){    //EAST
            for (y = y+1; y < world[x].length; y++){
                if (y == world[x].length-1) return false;
                else if(world[x][y-1].getToken() =='a' && ((world[x][y].getToken()== 'a'  || world[x][y].getToken()== 'm')) && world[x][y+1] == null){
                    return true;
                }
            }
        }
        if (direction == 4){    //WEST
            for (y = y-1; y  >= 0; y--){
                if (y == 0) return false;
                else if((world[x][y+1].getToken() =='a' && ((world[x][y].getToken()== 'a'  || world[x][y].getToken()== 'm'))&& world[x][y].getToken()== 'm') && world[x][y-1] == null){
                    return true;
                }
            }
        }
        return false;
    }

       
    /**
     * @author Leila Erbay
     * @param int the direction of the step
     * @param int value of the item's x coordinate
     * @param int valu of the item's y coordinate
     * @return an array of the coordinates of the nearest empty space
     * <p> special notes: moves along the array (either column or row) to find the nearest empty space from x,y
     */
    private int[] getEmptySpace (int direction, int x, int y){
        int [] coords = new int [2];
        coords[0] = -1;
        coords[1] = -1;
        
        if (direction == 1){                //checking toward the north direction
            for (x = x-1; x >= 0; x--){
                if (world[x][y] == null) {
                    coords[0] = x;
                    coords[1] = y;
                    return coords;
                    
                }
            }
        }
        if (direction ==2) {                //checking the south direction
            for (x = x+1; x< world.length; x++){
                if(world[x][y] == null){
                    coords[0] = x;
                    coords[1] = y;
                    return coords;
                }
            }
        }
        if (direction == 3){                   // checking the east direction
            for (y = y+1; y< world[x].length; y++){
                if(world[x][y] == null){
                    coords[0] = x;
                    coords[1] = y;
                    return coords;
                }
            }                
        }
        if (direction == 4){                // checking the west direction
            for (y = y-1; y  >= 0; y--){
                if(world[x][y] == null){
                    coords[0] = x;
                    coords[1] = y;
                    return coords;
                }
            }
        }
        return coords;        
    }
    

    /**
     * @author Leila Erbay
     * @author Leila Erbay
     * @param int the direction of the step
     * @param int value of the item's x coordinate
     * @param int valu of the item's y coordinate
     * <p> special notes: alters the set up of the world by 1 step in a certain direction
     */
    private void shift(int direction, int x, int y){
        int [] coords = getEmptySpace(direction, x,y);
        int xVal = coords[0];
        int yVal = coords [1];

        if (xVal < 0 || yVal <0) return;    //if there is no room then nothing can move

        try{
            if (direction == 1){        //NORTH
                for(int i= xVal+1; i <= x; i++){    // shift everything down by one 
                    if (world[i][y] != null && world[i][y].getToken() !='i'){
                        Mobile item = (Mobile) world[i][y];      //extract the item
                        
                        item.setDirection(direction);       //set the direction for the item to be moving
                        item.bump();                        //bump the item (ie give the item new coordinates)
                        add(item, xVal, yVal);              //add this item to the position it should move to
                        world[i][y] = null;                 //replace the item's previous position with null
                        xVal = i;

                        if (item.getToken()=='a'){          //set the autonomous object to moved
                            item.setMoved();
                        }
                    }
                }
            }

            else if (direction == 2) { //SOUTH
                for (int i = xVal-1; i >= x ; i--){
                    if (world[i][y] != null && world[i][y].getToken() !='i'){
                        Mobile item = (Mobile) world [i][y];
                        
                        item.setDirection(direction);
                        item.bump();
                        add(item, xVal, yVal);
                        world[i][y] = null;
                        xVal = i;

                        if (item.getToken()=='a'){
                            item.setMoved();      
                        }
                    }
                }
            }

            else if (direction == 3) {  //EAST
                for (int i = yVal-1; i >= y; i--){
                    if (world[x][i] != null && world[x][i].getToken() != 'i'){
                        Mobile item = (Mobile)world[x][i];
                        
                        item.setDirection(direction);
                        item.bump();
                        add(item, xVal, yVal);
                        world[x][i] = null;
                        yVal = i;

                        if (item.getToken()=='a'){
                           item.setMoved();
                        }
                    }
                }
            }
            
            else if (direction == 4){   //WEST
                for (int i = yVal +1; i <= y; i++){
                    if (world[x][i] != null && world[x][i].getToken() != 'i'){
                        Mobile item =(Mobile) world[x][i];
                        
                        item.setDirection(direction);
                        item.bump();
                        add(item, xVal, yVal);
                        world[x][i] = null;
                        yVal = i;

                        if (item.getToken()=='a'){
                            item.setMoved();
                        }
                    }
                }

            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("error2" + e.getLocalizedMessage());
        }
        catch(NullPointerException e){
            System.out.println("error3" + e.getMessage());
        }
    }

    /**
     * @author Leila Erbay
     * <p> special notes: finds the autonomous objects and sets their attribute moved to 0 (ie unmoved)
     */
    private void resetAllMobile(){  
        for (int i = 0; i < world.length; i++){
            for (int j = 0; j< world[i].length; j++){
                if (world[i][j] != null){
                    if (world[i][j].getToken() == 'a'){
                        Mobile item = (Mobile)world[i][j];
                        item.setUnmoved();
                    }
                }
            }
        }
    }

}






   
