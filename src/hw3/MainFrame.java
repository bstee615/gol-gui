package hw3;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = -746668192754492357L;

	public MainFrame(GameOfLifePanel golPanel, ControlPanel controlPanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(controlPanel, BorderLayout.NORTH);
		add(golPanel, BorderLayout.SOUTH);
		pack();

		setVisible(true);
	}
}
