package hw3.display;

public class Program {

	// @formatter:off
	public static final String[] grid = {
			"0 0 0 0 0 0",
			"0 1 1 0 0 0",
			"0 1 1 0 0 0",
			"0 0 0 1 1 0",
			"0 0 0 1 1 0",
			"0 0 0 0 0 0" };
	// @formatter:on

	public static final int[] born = { 3 };
	public static final int[] survive = { 2, 3 };

	/**
	 * Runs a GOL simulation with an example grid & rules.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String args[]) {
		LifeGui.launchGui(grid, born, survive);
	}

}
