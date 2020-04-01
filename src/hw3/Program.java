package hw3;

import javax.swing.SwingUtilities;

public class Program {

	public static final String[] grid = {
			"0 0 0 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 0 0 1 1 0",
			  "0 0 0 1 1 0",
			  "0 0 0 0 0 0"};
	public static final int[] born = {3};
	public static final int[] survive = {2,3};
	
    public static void main(String args[]) {
    	Life simulation = new Life(grid, born, survive);
    	GameOfLifePanel golPanel = new GameOfLifePanel(simulation);
    	
		SwingUtilities.invokeLater(new Runnable() {
		    public void run()
		    {
		    	new MainFrame(golPanel);
		    }
	    });
    }

}
