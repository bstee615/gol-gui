package hw3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class LifePanel extends JPanel {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	public static final int gridCellSize = 20;
	public static final int minimumGridWidth = 30 * gridCellSize;

	private static final Color inactiveColor = Color.lightGray;
	private static final Color activeColor = Color.BLACK;

	private Life simulation;

	public LifePanel(Life simulation) {
		this.simulation = simulation;
		setSize(getPreferredSize());
		setVisible(true);
	}

	public void nextGeneration() {
		simulation.nextGeneration();
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		int squareWidth = Math.max(simulation.getRows(), simulation.getColumns());
		Dimension fromContent = new Dimension(gridCellSize * squareWidth + 1, gridCellSize * squareWidth + 1);
		Dimension minimum = new Dimension(minimumGridWidth, minimumGridWidth);

		if (squareWidth < 600) {
			return minimum;
		} else {
			return fromContent;
		}
	}

	private void drawGrid(Graphics g) {
		final int gridWidth = getPreferredSize().width / gridCellSize;
		final int gridHeight = getPreferredSize().height / gridCellSize;

		// Draw a gray grid over the whole thing
		g.setColor(inactiveColor);
		for (int displayrow = 0; displayrow < gridWidth; displayrow++) {
			for (int displaycol = 0; displaycol < gridHeight; displaycol++) {
				g.drawRect(gridCellSize * displaycol, gridCellSize * displayrow, gridCellSize, gridCellSize);
			}
		}

		// Display the cells in the center
		g.setColor(activeColor);

		// How many cells offset to put the automaton in the center
		final int rowOffset = (gridWidth / 2) - (simulation.getRows() / 2);
		final int colOffset = (gridHeight / 2) - (simulation.getColumns() / 2);
		for (int row = 0; row < simulation.getRows(); row++) {
			for (int col = 0; col < simulation.getColumns(); col++) {
				Rectangle gridSquare = new Rectangle(gridCellSize * (col + colOffset), gridCellSize * (row + rowOffset),
						gridCellSize, gridCellSize);

				if (simulation.getCell(row, col).isAlive()) {
					g.fillRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
				} else {
					g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		drawGrid(g);
	}

}
