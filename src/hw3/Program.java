package hw3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Program {

	public static final String[] grid = {
			"0 0 0 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 0 0 1 1 0",
			  "0 0 0 1 1 0",
			  "0 0 0 0 0 0"};
	public static final int[] born = {3};
	public static final int[] survive = {2,3};
	
	private ActionListener pausePlayButtonListener;
	
	private void display(Life simulation) {
        JFrame app=new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        // Add layout panel with a border
		JPanel layoutPanel = new JPanel();
		layoutPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		layoutPanel.setLayout(new GridBagLayout());

		GameOfLifePanel golPanel = new GameOfLifePanel(simulation);

    	// button panel
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    	// grid size spinner
    	buttonPanel.add(new JLabel("Grid size:"));
    	
    	int defaultGridSize = 50;
		golPanel.setGridSize(defaultGridSize);
    	SpinnerNumberModel model = new SpinnerNumberModel(defaultGridSize, //initial value  
		defaultGridSize, //minimum value  
        100, //maximum value  
        1);
		
    	JSpinner spinner = new JSpinner();
    	spinner.setModel(model);
    	spinner.setMaximumSize(new Dimension(40, 30));
    	spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int)spinner.getModel().getValue();
				golPanel.setGridSize(value);
				golPanel.revalidate();
				app.pack();
				golPanel.repaint();
			}
		});
    	buttonPanel.add(spinner);

    	// next button
    	JButton nextButton = new JButton("Next");
    	nextButton.addActionListener(new ActionListener() {
    		
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	simulation.nextGeneration();
				golPanel.repaint();
    	    }
    	});
    	buttonPanel.add(nextButton);

    	// pause/play button
    	JButton pausePlayButton = new JButton("Play");
    	pausePlayButtonListener = new ActionListener() {
    		Timer timer = new Timer(1000, new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	    	simulation.nextGeneration();
					golPanel.repaint();
	    	    }
	    	});
    		
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	if (timer.isRunning()) {
    	    		pausePlayButton.setText("Play");
        	    	timer.stop();
    	    	}
    	    	else {
    	    		pausePlayButton.setText("Pause");
        	    	timer.restart();
    	    	}
    	    }
    	};
    	pausePlayButton.addActionListener(pausePlayButtonListener);
    	buttonPanel.add(pausePlayButton);
    	
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
        
    	// Display
        app.add(layoutPanel);
        app.pack();
		app.setVisible(true);
    	
		// Resize spinner to be prettier
//    	defaultGridSize = (buttonPanel.getWidth() + dummyPanel.getWidth()) / simulation.getRows();
//		golPanel.setGridSize(defaultGridSize);
//    	SpinnerNumberModel model = new SpinnerNumberModel(defaultGridSize, //initial value  
//    			defaultGridSize, //minimum value  
//                100, //maximum value  
//                1);
//    	spinner.setModel(model);
	}
	
    public static void main(String args[]) {
    	Life simulation = new Life(grid, born, survive);
    	
		SwingUtilities.invokeLater(new Runnable() {
		    public void run()
		    {
		    	new Program().display(simulation);
		    }
	    });
    }

}
