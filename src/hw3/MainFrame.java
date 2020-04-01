package hw3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	
	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = -746668192754492357L;

	private JPanel layoutPanel(GameOfLifePanel golPanel) {
		// Add layout panel with a border
		JPanel layoutPanel = new JPanel();
		layoutPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		layoutPanel.setLayout(new GridBagLayout());
	
		JPanel buttonPanel = new ControlPanel(golPanel);
		
		// Add items to layout
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
	    layoutPanel.add(buttonPanel, c);
	
	    JPanel dummyPanel = new JPanel();
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.gridy = 0;
		c2.weightx = 1.0;
		c.weighty = 1.0;
		c2.fill = GridBagConstraints.HORIZONTAL;
		layoutPanel.add(dummyPanel, c2);
	
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 1;
		c3.gridwidth = 2;
		c3.weightx = 1.0;
		c3.weighty = 1.0;
		c3.fill = GridBagConstraints.HORIZONTAL;
		layoutPanel.add(golPanel, c3);
		
		return layoutPanel;
	}
	
	public MainFrame(GameOfLifePanel golPanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(layoutPanel(golPanel));
        pack();
		setVisible(true);
	}
}
