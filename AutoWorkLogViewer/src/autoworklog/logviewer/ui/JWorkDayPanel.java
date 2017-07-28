package autoworklog.logviewer.ui;

import autoworklog.logviewer.WorkDay;

import javax.swing.*;
import java.awt.*;

/**
 * Swing JPanel for showing WorkDay information.
 */
public class JWorkDayPanel extends JPanel {

	/**
	 * The neutral text shown in the panels, when there is nothing to show.
	 */
	private static final String NEUTRAL_TEXT="-";

	/**
	 * The work day shown by the panel.
	 */
	private WorkDay day;
	private JLabel dayInfoLabel, dayLabel;
	private JLabel durationInfoLabel, durationLabel;
	private JLabel minInfoLabel, minLabel;
	private JLabel maxInfoLabel, maxLabel;

	/**
	 * Creates an empty work day panel.
	 */
	public JWorkDayPanel() {
		day=null;

		//creating the UI elements
		this.setLayout(new GridLayout(4, 2, 3, 3));
		//creating the day labels
		dayInfoLabel=new JLabel("Day: ");
		add(dayInfoLabel);
		dayLabel=new JLabel();
		add(dayLabel);
		//creating the labels duration labels
		durationInfoLabel=new JLabel("Work duration: ");
		add(durationInfoLabel);
		durationLabel=new JLabel();
		add(durationLabel);
		//adding min work interval label
		minInfoLabel=new JLabel("Min work period: ");
		add(minInfoLabel);
		minLabel=new JLabel();
		add(minLabel);
		//adding the max work interval label
		maxInfoLabel=new JLabel("Max work period: ");
		add(maxInfoLabel);
		maxLabel=new JLabel();
		add(maxInfoLabel);
	}

	/**
	 * Updates the UI component when the dya changes.
	 */
	private void updatePanelUI() {
		if (day!=null) {
			dayLabel.setText(day.toString());
			durationLabel.setText(day.getWorkDuration().toString());
			minLabel.setText(day.getMinWorkInterval().toString());
			maxLabel.setText(day.getMaxWorkInterval().toString());
		}
		else {
			dayLabel.setText(NEUTRAL_TEXT);
			durationLabel.setText(NEUTRAL_TEXT);
			minLabel.setText(NEUTRAL_TEXT);
			maxLabel.setText(NEUTRAL_TEXT);
		}

	}


	/**
	 * Gets the work day shown by the panel.
	 * @return WorkDay is the work day of the panel.
	 */
	public WorkDay getDay() {
		return day;
	}

	/**
	 * Sets the work day shown by the panel.
	 * @param newDay The new work day shown by the panel.
	 */
	public void setDay(WorkDay newDay) {
		day=newDay;
		updatePanelUI();
	}
}
