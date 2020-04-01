package hw3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {
	
	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 6383142926743409714L;
	private static final int playTimeout = 1000;
	
	private void addSpinner(GameOfLifePanel golPanel) {
		add(new JLabel("Grid size:"));
		
		int defaultGridSize = 50;
		golPanel.setGridSize(defaultGridSize);
		SpinnerNumberModel model = new SpinnerNumberModel(defaultGridSize,  
			defaultGridSize,  
		    100,
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
				golPanel.repaint();

				// Must re-pack frame
//				app.pack();
			}
		});
		add(spinner);
	}
	
	private void addNextButton(GameOfLifePanel golPanel) {
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
				golPanel.nextGeneration();
		    }
		});
		add(nextButton);
	}

	private void addPausePlayButton(GameOfLifePanel golPanel) {
		JButton pausePlayButton = new JButton("Play");
		ActionListener pausePlayButtonListener = new ActionListener() {
			Timer timer = new Timer(playTimeout, new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
					golPanel.nextGeneration();
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
		add(pausePlayButton);
	}
	
	public ControlPanel(GameOfLifePanel golPanel) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// grid size spinner
		addSpinner(golPanel);

		// next button
		addNextButton(golPanel);

		// pause/play button
		addPausePlayButton(golPanel);	
	}
}
