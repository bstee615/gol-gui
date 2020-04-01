package hw3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GameOfLifePanel extends JPanel {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	public static final int gridSize = 20;
	public static final int minimumWidth = 30 * gridSize;

	private static final Color inactive = Color.lightGray;
	private static final Color active = Color.BLACK;

	private Life simulation;

	public GameOfLifePanel(Life simulation) {
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
		Dimension fromContent = new Dimension(gridSize * squareWidth + 1, gridSize * squareWidth + 1);
		Dimension minimum = new Dimension(minimumWidth, minimumWidth);

		if (squareWidth < 600) {
			return minimum;
		} else {
			return fromContent;
		}
	}

	public int getGridWidth() {
		return getPreferredSize().width / gridSize;
	}

	public int getGridHeight() {
		return getPreferredSize().height / gridSize;
	}

	private void drawGrid(Graphics g) {
		// Draw a grid over the whole thing
		g.setColor(inactive);
		for (int displayrow = 0; displayrow < getGridWidth(); displayrow++) {
			for (int displaycol = 0; displaycol < getGridHeight(); displaycol++) {
				g.drawRect(gridSize * displaycol, gridSize * displayrow, gridSize, gridSize);
			}
		}

		// Display the cells in the center
		final int rowOffset = (getGridWidth() / 2) - (simulation.getRows() / 2);
		final int colOffset = (getGridHeight() / 2) - (simulation.getColumns() / 2);
		g.setColor(active);
		for (int row = 0; row < simulation.getRows(); row++) {
			for (int col = 0; col < simulation.getColumns(); col++) {
				Rectangle gridSquare = new Rectangle(gridSize * (col + colOffset), gridSize * (row + rowOffset),
						gridSize, gridSize);

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
