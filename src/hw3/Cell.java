package hw3;

import java.util.ArrayList;

public class Cell {

	private boolean alive;
	private boolean aliveNextGeneration;
	private int[] born;
	private int[] survive;
	private ArrayList<Cell> neighbors;
	
	/**
	 * Constructs a new cell in a Game of Life
	 * @param alive the initial state of the cell
	 * @param b an array of integers encoding the born rules for the cell
	 * @param s an array of integers encoding the survive rules for the cell
	 */
	public Cell(boolean alive, int[] b, int[] s) {
		this.alive = alive;
		born = new int[b.length];
		survive = new int[s.length];
		for(int i = 0; i < b.length; i++) {
			born[i] = b[i];
		}
		for(int i = 0; i < s.length; i++) {
			survive[i] = s[i];
		}
		neighbors = new ArrayList<Cell>();
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive. 
	 * 
	 * @return the boolean value of whether the cell is dead or alive.
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive after the next generation.
	 * 
	 * @return the boolean value of whether the cell is dead or alive after the next generation.
	 */
	public boolean isAliveAfterGeneration() {
		return aliveNextGeneration;
	}
	
	/**
	 * Sets the status of the cell (dead or alive) after one generation of the game. 
	 * 
	 * 
	 */
	public void setNextGeneration() {
		int numNeighborsAlive = 0;
		for(int i = 0; i < neighbors.size(); i++) {
			if(neighbors.get(i).isAlive()) {
				numNeighborsAlive++;
			}
		}
		
		if(!alive && bornContains(numNeighborsAlive)) {
			aliveNextGeneration = true;
		}
		else if(alive && surviveContains(numNeighborsAlive)) {
			aliveNextGeneration = true;
		}
		else {
			aliveNextGeneration = false;
		}
	}
	
	/**
	 * Sets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @param ArrayList<Cell> of the neighboring cells.
	 * 
	 */
	public void setNeighbors(ArrayList<Cell> n) {
		  for(int i = 0; i < n.size(); i++) {
			  neighbors.add(n.get(i));
		  }
	}
	
	/**
	 * Gets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @return ArrayList<Cell> of the neighboring cells.
	 * 
	 */
	public ArrayList<Cell> getNeighbors() {
		  return neighbors;
	}
	
	/**
	 * Sets the current status of the cell equal to the status of the cell after one generation of the game. 
	 */
	public void update() {
		alive = aliveNextGeneration;
	}
	
	
	/**
	 * Returns a String representation of the cell. Returns "0" if the cell is dead or "1" if the cell is alive.
	 * 
	 * @return a string representing the current state of the cell.
	 */
	public String toString() {
		if(alive) {
			return "1";
		}
		else {
			return "0";
		}
	}
	
	private boolean bornContains(int b) {
		for(int i = 0; i < born.length; i++) {
			if(born[i] == b) {
				return true;
			}
		}
		return false;
	}
	private boolean surviveContains(int s) {
		for(int i = 0; i < survive.length; i++) {
			if(survive[i] == s) {
				return true;
			}
		}
		return false;
	}
}

