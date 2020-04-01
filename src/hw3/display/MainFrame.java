package hw3.display;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = -746668192754492357L;

	public MainFrame(LifePanel lifePanel, ControlPanel controlPanel) {
		setTitle("Homework 3");
		setIconImage(new ImageIcon("icons/window_icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(controlPanel, BorderLayout.NORTH);
		add(lifePanel, BorderLayout.SOUTH);
		pack();

		setVisible(true);
	}
}
