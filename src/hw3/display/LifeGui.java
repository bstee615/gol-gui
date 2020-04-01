package hw3.display;

import javax.swing.SwingUtilities;

import hw3.Life;

/**
 * Utility methods for a GUI for Conway's Game of Life.
 * 
 * @author Ben Steenhoek
 *
 */
public class LifeGui {
	/**
	 * 
	 * @param grid    A grid defining the initial state of the simulation.
	 * @param born    Rules defining when cells should be born.
	 * @param survive Rules defining when cells should survive.
	 */
	public static void launchGui(String[] grid, int[] born, int[] survive) {
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
