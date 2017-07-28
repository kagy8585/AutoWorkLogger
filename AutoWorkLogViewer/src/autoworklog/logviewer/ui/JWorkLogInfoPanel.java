package autoworklog.logviewer.ui;

import autoworklog.logviewer.WorkLog;

import javax.swing.*;
import java.awt.*;

public class JWorkLogInfoPanel extends JPanel {
	/**
	 * The neutral text shown in the panels, when there is nothing to show.
	 */
	private static final String NEUTRAL_TEXT="-";

	//panel for the basic infos
	private JLabel totalWorkInfoLabel, totalWorkLabel;
	private JLabel averageWorkInfoLabel, averageWorkLabel;
	private JLabel minWorkInfoLabel, minWorkLabel;
	private JLabel maxWorkInfoLabel, maxWorkLabel;

	/**
	 * The work log presented in the panel.
	 */
	private WorkLog workLog;

	/**
	 * Creates a work log panel with an empty WorkLog,
	 */
	public JWorkLogInfoPanel() {
		workLog=null;

		//creating the grid panel
		setLayout(new GridLayout(4, 2, 3, 3));
		//adding total work time label
		totalWorkInfoLabel=new JLabel("Total work time: ");
		add(totalWorkInfoLabel);
		totalWorkLabel=new JLabel();
		add(totalWorkLabel);
		//adding average wok time label
		averageWorkInfoLabel=new JLabel("Daily work time: ");
		add(averageWorkInfoLabel);
		averageWorkLabel=new JLabel();
		add(averageWorkLabel);
		//add min work time
		minWorkInfoLabel=new JLabel("Minimum work interval: ");
		add(minWorkInfoLabel);
		minWorkLabel=new JLabel();
		add(minWorkLabel);
		//add max work time
		maxWorkInfoLabel=new JLabel("Maximum work interval: ");
		add(maxWorkInfoLabel);
		maxWorkLabel=new JLabel();
		add(maxWorkLabel);

		//updating the UI panel
		updateUIPanel();
	}

	/**
	 * Updates the UI panel, in order to be refreshed.
	 */
	private void updateUIPanel() {
		if (workLog!=null) {
			totalWorkLabel.setText(workLog.getTotalWorkDuration().toString());
			averageWorkLabel.setText(workLog.getAverageWorkDuration().toString());
			minWorkLabel.setText(workLog.getMinWorkInterval().toString());
			maxWorkLabel.setText(workLog.getMaxWorkInterval().toString());
		}
		else {
			totalWorkLabel.setText(NEUTRAL_TEXT);
			averageWorkLabel.setText(NEUTRAL_TEXT);
			minWorkLabel.setText(NEUTRAL_TEXT);
			maxWorkLabel.setText(NEUTRAL_TEXT);
		}
	}


	/**
	 * Gets the work log shown by the panel.
	 * @return The WorkLog show on the panel.
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}


	/**
	 * Sets the work log presented in the panel.
	 * @param newWorkLog The new work log to be presented in the panel.
	 */
	public void setWorkLog(WorkLog newWorkLog) {

		workLog=newWorkLog;
		updateUIPanel();
	}
}
