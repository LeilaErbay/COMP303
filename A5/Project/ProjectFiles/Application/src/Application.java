import java.awt.Color;
import java.util.Scanner;
import JavaCool303.*;


public class Application {

	public static void main(String [] args){
		try{
			Scanner sc = new Scanner(System.in);
			int numRoots, numBoxes, numButtons, rootIndex, boxIndex, x, y;
			String theme, response, title;
			int validTheme = 0;
			int loop = 0;
			Cool303Window w = new Cool303Window();
			
			System.out.println("WELCOME TO APPLICATION");
			do{
				do {
					System.out.println("Please enter the theme you would like to implement from the following menu:");
					System.out.println("Theme menu: ");
					System.out.println("1 | pastel ");
					System.out.println("2 | summer ");
					theme = sc.next();
					if(theme.equalsIgnoreCase("summer") || theme.equalsIgnoreCase("pastel") || theme.equals("1") || theme.equals("2")){
						validTheme = 1;
						if (theme.equals("1")) theme = "pastel";
						else if (theme.equals("2")) theme = "summer";
					}
				}while(validTheme == 0);
				
			
			System.out.println("Please enter the number of roots you would like to implement:");
			numRoots = sc.nextInt();
			
			System.out.println("Please enter the number of boxes for each root you would like to implement:");
			numBoxes = sc.nextInt();
			
			System.out.println("Please enter the number of buttons for each box you would like to implement:");
			numButtons = sc.nextInt();
			
			
			w.setTheme(theme);
			w.setUpRoots(numRoots, numBoxes, numButtons);
			w.setVisible();
			
			//ADDING A BORDER IF DESIRED
				System.out.println("Would you like to add a border to a box?");
				response = sc.next();
				
				if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
					System.out.println("Please tell me the root which contains the box that you would like to alter" );
					System.out.println("The roots that exist are below:");
					for (int i = 0; i < numRoots; i++){
						System.out.println("\t"+ (i+1) + " | Root " + (i+1));
					}
					rootIndex = sc.nextInt()-1 ;
					while(rootIndex > numRoots || rootIndex < 0) {
						System.out.println("Please enter a valid root from 1 to "+ numRoots);
						rootIndex = sc.nextInt()-1;
					}
					System.out.println("Please tell me the box in Root " + (rootIndex+1) + " that you would like to add a border");
					System.out.println("The roots that exist are below:");
					for (int i = 0; i < numBoxes; i++){
						System.out.println("\t" + (i+1) + " | Box" + (i+1));
					}
					boxIndex = sc.nextInt() - 1;
					while(boxIndex > numBoxes || boxIndex < 0){
						System.out.println("Please enter a valid box from 1 to " + numBoxes);
						boxIndex = sc.nextInt()-1;
					}
					
					System.out.println("Please tell me the color you would like Box " + (boxIndex+1) + " to change to.");
					Color color = printColor();
					while(color == null){
						System.out.println("Please enter a valid color");
						color = printColor();
					}
					w.setBorderColor(color, boxIndex, rootIndex);
					
				}
				
				//ADDING A TITLE IF DESIRED
				System.out.println("Would you like to add a title to a border?");
				response = sc.next();
				if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
					System.out.println("Please tell me the root which contains the box that you would like to alter" );
					System.out.println("The roots that exist are below:");
					for (int i = 0; i < numRoots; i++){
						System.out.println("\t"+ (i+1) + " | Root " + (i+1));
					}
					rootIndex = sc.nextInt()-1;
					while(rootIndex > numRoots || rootIndex < 0) {
						System.out.println("Please enter a valid root from 1 to "+ numRoots);
						rootIndex = sc.nextInt()-1;
					}
					System.out.println("Please tell me the box in Root " + (rootIndex+1) + " that you would like to add a title");
					System.out.println("The roots that exist are below:");
					for (int i = 0; i < numBoxes; i++){
						System.out.println("\t" + (i+1) + " | Box" + (i+1));
					}
					boxIndex = sc.nextInt() - 1;
					while(boxIndex > numBoxes || boxIndex < 0){
						System.out.println("Please enter a valid box from 1 to " + numBoxes);
						boxIndex = sc.nextInt()-1;
					}
					System.out.println("Please enter the title you would like to give to box " + (boxIndex+1));
					title = sc.next();
					w.addBorderTitle(title, boxIndex, rootIndex);
				}
				
				
				//CHANGING THE SIZE
				System.out.println("Would you like to resize a root?");
				response = sc.next();
				if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
					System.out.println("Please tell me the root which contains the box that you would like to alter" );
					System.out.println("The roots that exist are below:");
					for (int i = 0; i < numRoots; i++){
						System.out.println("\t"+ (i+1) + " | Root " + (i+1));
					}
					rootIndex = sc.nextInt()-1;
					while(rootIndex > numRoots || rootIndex < 0) {
						System.out.println("Please enter a valid root from 1 to "+ numRoots);
						rootIndex = sc.nextInt()-1;
					}
					
					System.out.println("Please enter a value for the width");
					x = sc.nextInt();
					System.out.println("Please enter a value for the height");
					y = sc.nextInt();
					w.resizeRoot(rootIndex,x,y);
					
				}
				System.out.println("Would you like to add a new theme to the simulation?");
				response = sc.next();
				if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
					loop =0;
				}
				else loop =1;
				
				
			}while(loop == 0);
				
			
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		
	
	
	}
	public static Color printColor(){
		String color;
		Color returnColor = null;
		Scanner sc = new Scanner (System.in);
		System.out.println("\t" + " 1 | BLACK");
		System.out.println("\t" + " 2 | BLUE");
		System.out.println("\t" + " 3 | CYAN");
		System.out.println("\t" + " 4 | DARK_GRAY");
		System.out.println("\t" + " 5 | GRAY");
		System.out.println("\t" + " 6 | GREEN");
		System.out.println("\t" + " 7 | LIGHT_GRAY");
		System.out.println("\t" + " 8 | MAGENTA");
		System.out.println("\t" + " 9 | ORANGE");
		System.out.println("\t" + " 10 | PINK");
		System.out.println("\t" + " 11 | RED");
		System.out.println("\t" + " 12 | WHITE");
		System.out.println("\t" + " 13 | YELLOW");
		
		color = sc.next();
		
		if (color.equalsIgnoreCase("black") || color.equals("1")) returnColor =  Color.BLACK;
		else if (color.equalsIgnoreCase("blue") || color.equals("2")) returnColor = Color.BLUE;
		else if (color.equalsIgnoreCase("cyan") || color.equals("3")) returnColor = Color.CYAN;
		else if (color.equalsIgnoreCase("dark_gray") || color.equals("4")) returnColor =Color.DARK_GRAY;
		else if (color.equalsIgnoreCase("gray") || color.equals("5")) returnColor = Color.GRAY;
		else if (color.equalsIgnoreCase("green")|| color.equals("6") ) returnColor = Color.GREEN;
		else if (color.equalsIgnoreCase("light_gray") || color.equals("7")) returnColor = Color.LIGHT_GRAY;
		else if (color.equalsIgnoreCase("magenta")|| color.equals("8")) returnColor = Color.MAGENTA;
		else if (color.equalsIgnoreCase("orange") || color.equals("9")) returnColor = Color.ORANGE;
		else if (color.equalsIgnoreCase("Pink") || color.equals("10")) returnColor = Color.PINK;
		else if (color.equalsIgnoreCase("red") || color.equals("11")) returnColor = Color.RED;
		else if (color.equalsIgnoreCase("white") || color.equals("12")) returnColor = Color.WHITE;
		else if (color.equalsIgnoreCase("yellow") || color.equals("13")) returnColor = Color.YELLOW;
		return returnColor;
		
	}
		
	

}


