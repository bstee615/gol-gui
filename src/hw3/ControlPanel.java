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
	private static final int animationTimeoutMs = 1000;

	private void addNextFrameButton(LifePanel lifePanel) {
		JButton nextButton = new JButton("Next Frame");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lifePanel.nextGeneration();
			}
		});
		add(nextButton);
	}

	private void addPausePlayButton(LifePanel lifePanel) {
		JButton pausePlayButton = new JButton("Play");
		ActionListener pausePlayButtonListener = new ActionListener() {
			Timer timer = new Timer(animationTimeoutMs, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					lifePanel.nextGeneration();
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

	public ControlPanel(LifePanel lifePanel) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		addNextFrameButton(lifePanel);
		addPausePlayButton(lifePanel);
	}
}
