package hw3;

import java.util.Scanner;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;


public class Life {
	private Cell[][] grid;
	private int numRows;
	private int numCols;
	

	
	/**
	 * Constructs Conways Game of Life give a starting grid (a "seed"), the born rules and the survive rules.  The grid is given as an array of strings as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param initConfig an array of Strings encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 */
	public Life(String[] initConfig, int[] born, int[] survive) {
		numRows = initConfig.length;
		Scanner scan = new Scanner(initConfig[0]);
		int col = 0;
		while(scan.hasNextInt()) {
			col++;
			scan.next();
		}

		numCols = col;
		
		grid = new Cell[numRows][numCols];
		
		for(int row = 0; row < grid.length; row++) {
			scan = new Scanner(initConfig[row]);
			for(col = 0; col < grid[row].length; col++) {
				grid[row][col] = new Cell(scan.nextInt() == 1, born, survive);
			}
		}
		
		setNeighbors();
	}
	
	/**
	 * Constructs Conways Game of Life give a starting grid (a "seed"), the born rules and the survive rules.  The grid is given in a file containing a list of strings as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param f the File encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 */
	public Life(File f, int[] born, int[] survive) throws FileNotFoundException{
		
		Scanner scan = new Scanner(f);
		int row = 0;
		while(scan.hasNextLine()) {
			row++;
		}
		scan.close();
		
		String[] initConfig = new String[row];
		int r = 0;
		while(scan.hasNextLine()) {
			initConfig[r] = scan.nextLine();
			r++;
		}
		scan.close();
		
		numRows = initConfig.length;
		scan = new Scanner(initConfig[0]);
		int col = 0;
		while(scan.hasNextInt()) {
			col++;
			scan.next();
		}

		numCols = col;
		
		grid = new Cell[numRows][numCols];
		
		for(row = 0; row < grid.length; row++) {
			scan = new Scanner(initConfig[row]);
			for(col = 0; col < grid[row].length; col++) {
				grid[row][col] = new Cell(scan.nextInt() == 1, born, survive);
			}
		}
		
		setNeighbors();
		
	}
	
	/**
	 * Returns cell at specified position
	 * @param row index of the row 
	 * @param col index of the column
	 * @return Cell at position (row, col)
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	
	/**
	 * Returns the number of rows in the Game of Life
	 * @return number of rows in grid
	 */
	public int getRows() {
		return numRows;
	}
	
	
	/**
	 * Returns the number of columns in the Game of Life
	 * @return number of columns in grid
	 */
	public int getColumns() {
		return numCols;
	}
	/**
	 * Performs one generation of the game
	 * 
	 */
	public void nextGeneration() {
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[row].length; col++) {
				grid[row][col].setNextGeneration();
			}
		}
		
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[row].length; col++) {
				grid[row][col].update();
			}
		}
		
	}
	
	/**
	 * Returns an ArrayList of the neighbors of a cell at the given position.
	 * Note that a cell is not a neighbor of itself.
	 * 
	 * @param row
	 * @param col
	 * @return the neighbors of a cell at given row and col
	 */
	public ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		for(int r = row - 1; r <= row + 1; r++) {
			for(int c = col - 1; c <= col + 1; c++) {
				if(r >= 0 && r< numRows && c>= 0 && c < numCols && (r != row || c != col)) {
					neighbors.add(grid[r][c]);
				}
			}
		}
		return neighbors;
	}
	


	/**
	 * Returns a String representation of the game. Returns the String representation of each element of the game in a grid.
	 * 
	 * @return a string representing the current state of the game.
	 */
	public String toString() {
		String s = "";
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				s += grid[row][col].toString();
			}
			s+="\n";
				
		}
		return s;
		
	}
	
	

	
	private void setNeighbors() {
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[row].length; col++) {
				grid[row][col].setNeighbors(getNeighbors(row, col));
			}
		}
	}
}
