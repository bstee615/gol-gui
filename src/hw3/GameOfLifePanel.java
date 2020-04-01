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
		final int rowOffset = (getGridWidth() / 2) - (simulation.getRows() / 2);
		final int colOffset = displaycol - ((getGridHeight() / 2) - (simulation.getColumns() / 2));
		for (int displayrow = getGridWidth() - 1; displayrow >= 0; displayrow -= 1) {
			for (int displaycol = getGridHeight() - 1; displaycol >= 0; displaycol -= 1) {
				// Display the cells in the center
				int row = displayrow - rowOffset;
				int col = displaycol - colOffset;
				Rectangle gridSquare = new Rectangle(gridSize * displaycol, gridSize * displayrow, gridSize, gridSize);

				if (row >= 0 && col >= 0 && row < simulation.getRows() && col < simulation.getColumns()) {
//					Skip
					g.setColor(active);
//
//					if (simulation.getCell(row, col).isAlive()) {
//						g.fillRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
//					} else {
//						g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
//					}
				} else {
					g.setColor(inactive);
					g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
				}
			}
		}
		for (int displayrow = getGridWidth() - 1; displayrow >= 0; displayrow -= 1) {
			for (int displaycol = getGridHeight() - 1; displaycol >= 0; displaycol -= 1) {
				// Display the cells in the center
				int row = displayrow - rowOffset;
				int col = displaycol - colOffset;
				Rectangle gridSquare = new Rectangle(gridSize * displaycol, gridSize * displayrow, gridSize, gridSize);

				if (row >= 0 && col >= 0 && row < simulation.getRows() && col < simulation.getColumns()) {
					g.setColor(active);

					if (simulation.getCell(row, col).isAlive()) {
						g.fillRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
					} else {
						g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
					}
				} else {
//					g.setColor(inactive);
//					g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
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
