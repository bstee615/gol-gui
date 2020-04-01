package hw3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ControlPanel extends JPanel {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 6383142926743409714L;
	private static final int playTimeout = 1000;

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
				} else {
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
		addNextButton(golPanel);
		addPausePlayButton(golPanel);
	}
}
