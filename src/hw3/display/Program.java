package hw3.display;

import javax.swing.SwingUtilities;

import hw3.Life;

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

	public static void main(String args[]) {
		Life simulation = new Life(grid, born, survive);
		LifePanel lifePanel = new LifePanel(simulation);
		ControlPanel controlPanel = new ControlPanel(lifePanel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(lifePanel, controlPanel);
			}
		});
	}

}
