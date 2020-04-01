package hw3.display;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 6383142926743409714L;
	// Move to the next generation every so many seconds.
	Timer animationTimer;
	public static final int DEFAULT_ANIMATION_FPS = 5;
	public static final int ANIMATION_FPS_MIN = 1;
	public static final int ANIMATION_FPS_MAX = 30;

	private final ImageIcon playButtonDisplay = new ImageIcon("icons/resume_co.png");
	private final ImageIcon pauseButtonDisplay = new ImageIcon("icons/suspend_co.png");
	private final ImageIcon nextButtonDisplay = new ImageIcon("icons/stepover_co.png");

	public ControlPanel(LifePanel lifePanel) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		animationTimer = new Timer(1000 / DEFAULT_ANIMATION_FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lifePanel.nextGeneration();
			}
		});
		addNextGenerationButton(lifePanel);
		addPausePlayButton(lifePanel);
		addPlaySpeedSlider();
	}

	/**
	 * Add a slider that sets the play speed.
	 * 
	 * @param lifePanel A reference to our simulation's display.
	 */
	private void addPlaySpeedSlider() {
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));

		JLabel sliderLabel = new JLabel("Animation speed");
		sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sliderPanel.add(sliderLabel);

		JSlider animationFpsSlider = new JSlider(ANIMATION_FPS_MIN, ANIMATION_FPS_MAX, DEFAULT_ANIMATION_FPS);
		animationFpsSlider.setMinorTickSpacing(1);
		animationFpsSlider.setPaintTicks(true);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(ANIMATION_FPS_MIN), new JLabel("Slow"));
		labelTable.put(new Integer(ANIMATION_FPS_MAX), new JLabel("Fast"));
		animationFpsSlider.setLabelTable(labelTable);
		animationFpsSlider.setPaintLabels(true);

		animationFpsSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				animationTimer.setDelay(1000 / animationFpsSlider.getValue());
			}
		});
		sliderPanel.add(animationFpsSlider);

		add(sliderPanel);
	}

	/**
	 * Add a button that moves the simulation to the next generation.
	 * 
	 * @param lifePanel A reference to our simulation's display.
	 */
	private void addNextGenerationButton(LifePanel lifePanel) {
		JButton nextButton = new JButton(nextButtonDisplay);
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
		JButton pausePlayButton = new JButton(playButtonDisplay);
		ActionListener pausePlayButtonListener = new ActionListener() {
			@Override
			// Move next generation, then toggle timer/label text
			public void actionPerformed(ActionEvent e) {
				lifePanel.nextGeneration();

				if (animationTimer.isRunning()) {
					pausePlayButton.setIcon(playButtonDisplay);
					animationTimer.stop();
				} else {
					pausePlayButton.setIcon(pauseButtonDisplay);
					animationTimer.restart();
				}
			}
		};
		pausePlayButton.addActionListener(pausePlayButtonListener);
		add(pausePlayButton);
	}
}
