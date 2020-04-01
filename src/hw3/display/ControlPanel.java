package hw3.display;

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
	public static final int animationTimeoutMs = 1000;

	/**
	 * Add a button that moves the simulation to the next generation.
	 * 
	 * @param lifePanel A reference to our simulation's display.
	 */
	private void addNextGenerationButton(LifePanel lifePanel) {
		JButton nextButton = new JButton("Next Generation");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lifePanel.nextGeneration();
			}
		});
		add(nextButton);
	}

	/**
	 * Add a button that continues to move the simulation to the next generation
	 * according to {@link animationTimeoutMs}.
	 * 
	 * @param lifePanel A reference to our simulation's display.
	 */
	private void addPausePlayButton(LifePanel lifePanel) {
		JButton pausePlayButton = new JButton("Play");
		ActionListener pausePlayButtonListener = new ActionListener() {
			// Move to the next generation every so many seconds.
			Timer timer = new Timer(animationTimeoutMs, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					lifePanel.nextGeneration();
				}
			});

			@Override
			// Toggle timer/label text
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
		addNextGenerationButton(lifePanel);
		addPausePlayButton(lifePanel);
	}
}
