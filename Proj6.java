
import java.util.*;
import java.io.*;
/**
* Proj6
* Jon Fuller / Thursday 2:30
* This program implements Conway's Game of Life, simulating the evolution of cells in a two-dimensional grid. 
* The user provides an initial configuration of the grid via a text file. The program then iterates through generations, 
* applying specific rules to determine whether a cell lives, dies, or is born based on its neighboring cells. The simulation 
* continues indefinitely until manually stopped by the user, displaying each generation's grid with a 300-millisecond interval update.
*/
public class Proj6{
/**
* The readBoard method takes a specifically formated file, where the first two lines are the rows and columns, respectively. This then creates a 2D Array with those parameters and then inputs the rest of the Life file into it.
*
* @param fileName takes a specifically formated file to create the array from
* 
* @return Returns an array filled with the current board the user inputs 
* @throws IOException for File I/O 
*/	
	public static int [][] readBoard(String fileName)throws IOException{	
		int rows;
		int columns;
		File file = new File(fileName);
		Scanner inFile = new Scanner (new File(fileName));
		rows = Integer.parseInt(inFile.nextLine());
		columns = Integer.parseInt(inFile.nextLine());
		int [][] array = new int [rows+2][columns+2]; // add 2 to each
		
		int iL = array.length-1;
		int jL = array[0].length-1;
		
		for (int i = 0; i<(iL-1); i++){
			String st = inFile.nextLine();
			for(int j = 0; j<(jL-1); j++){
				char c = st.charAt(j);
				array[i][j] = Character.getNumericValue(c); // Character.getNumericValue got from Java Character class documentation in order to turn the character to an integer https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
			}// end inner for
		}//end outer for
		return array;
	}// end readBoard
	
/**
* This method returns a single String representing the current state of the cells array
*
* @param int2dArray The array that the method uses to break apart and put into a string. It also determins the value in each position of the array, and then prints either a "*" or a "." accordingly.
* @return returns a string of the array values converted to either a "*" or a "."
*/
	public static String boardDisplay(int [][] int2dArray ){
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i<int2dArray.length-1; i++){
			for(int j = 1; j<int2dArray[0].length-1; j++){
				if(j == int2dArray[0].length-2){
					if(int2dArray[i][j] == 1){
						sb.append("*");
						sb.append("\n");
					}// end if		
					else{
						sb.append(".");
						sb.append("\n");
					}// end else
				}// end if
				else{
					if(int2dArray[i][j] == 1){
						sb.append("*");
					}// end if		
					else{
						sb.append(".");
					}// end else
				}// end else
			}// end inner for
		}//end outer for
		String display = sb.toString();
		return display;
	}// end boardDisplay
	
	
	
	
/**
* This method takes an array and updates the values in it based on the rules of the Game of Life.
*
* @param anArray The main array that the method will update
* 
* @return Returns an updated array for the values of the array taken as an parameters
* 
*/
	
	public static int[][] update(int[][] anArray){
		
		int[][] updatedArray = new int [anArray.length][anArray[0].length];
		
		for (int i = 1; i<anArray.length-2; i++){
			for(int j = 1; j<anArray[0].length-2; j++){
				int living = neighbors(anArray, i, j);
				if(anArray[i][j] == 1){
					if(living == 2 || living == 3){
						updatedArray[i][j] = 1;
					}//end if if
					else{
						updatedArray[i][j] = 0;
					}//end if else
				}// end big if
				else{
					if(living == 3){
						updatedArray[i][j] = 1;
					}//end else if
					else{
						updatedArray[i][j] = 0;
					}//end else else
					
				}//end big else
					
				
					
			}// end inner for
		}//end outer for
		
		return updatedArray;
	}// end update
	
	
/**
* This method returns the number of live neighbors that a certain position (row, col) has in the 2D cells array
*
* @param testArray The main array that the method is checking from
* @param row The row that the method checks around
* @param col The column that the method checks around
* 
* @return Returns the count of living cells next to the one given from (row, col)
* 
*/
	
	public static int neighbors(int[][] testArray, int row, int col){
		int count = 0;
	
		
		if(testArray[row][col+1] == 1)/*right*/
			count++;
		if(testArray[row][col-1] == 1)/*left*/
			count++;
		if(testArray[row-1][col] == 1)/*up*/
			count++;
		if(testArray[row-1][col+1] == 1)/*up right*/
			count++;
		if(testArray[row-1][col-1] == 1)/*up left*/
			count++;
		if(testArray[row+1][col] == 1)/*down*/
			count++;
		if(testArray[row+1][col+1] == 1)/*down right*/
			count++;
		if(testArray[row+1][col-1] == 1)/*down left*/
			count++;
		
	
		
		return count;
		
	
	}// end neighbors
	
	
	
	
/**
* Proj6
* Jon Fuller / Thursday 2:30
* This program implements Conway's Game of Life, simulating the evolution of cells in a two-dimensional grid. 
* The user provides an initial configuration of the grid via a text file. The program then iterates through generations, 
* applying specific rules to determine whether a cell lives, dies, or is born based on its neighboring cells. The simulation 
* continues indefinitely until manually stopped by the user, displaying each generation's grid with a 300-millisecond interval update.
*
* @param args Command line argument
* @throws IOException for File I/O
*/
	public static void main(String [] args) throws IOException {
		
		String fileName = args[0];
		File file = new File(fileName);
		if(!file.exists()){			
			System.out.println("File not found. Please restart the program with a proper file name.");
			return;
		}// end !file while
		
		int [][] my2dArray;
		
		my2dArray = readBoard(fileName);	
		
		System.out.println(boardDisplay(my2dArray));
		
		try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
		
		System.out.println(boardDisplay(update(my2dArray)));
		int[][] newArray = update(my2dArray);
		
		try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
		
		
		while(true){
			System.out.println();
			
			newArray = update(newArray);
			System.out.println(boardDisplay(newArray));
			
			
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			}
		
	}//end main
	
	
	

}//end Proj6