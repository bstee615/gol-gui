package hw3;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameOfLifePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gridSize;
	
	private Life simulation;

	public GameOfLifePanel(Life simulation) {
        this.simulation = simulation;
//		setSize(gridSize * simulation.getColumns(), gridSize * simulation.getRows());
        setVisible(true);
	}
	
	public void setGridSize(int newGridSize) {
		gridSize = newGridSize;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(
				gridSize * simulation.getColumns() + 1,
				gridSize * simulation.getRows() + 1);
	}
	
	private void drawGrid(Graphics g) {
        for (int x = 0; x < simulation.getColumns(); x += 1)
            for (int y = 0; y < simulation.getRows(); y += 1)
            	if (simulation.getCell(x, y).isAlive()) {
                    g.fillRect(gridSize * x, gridSize * y, gridSize, gridSize);	
            	}
            	else {
                    g.drawRect(gridSize * x, gridSize * y, gridSize, gridSize);
            	}
	}

	@Override
    public void paint(Graphics g) {
    	g.clearRect(0,  0,  getWidth(), getHeight());
    	drawGrid(g);
    }


}
