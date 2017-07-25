
public class JWorkLogPanel extends JPanel {

	/**
	 * The work log presetned in the panel.
	 */
	private WorkLog workLog;	public JWorkLogPanel () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of workLog
	 * The work log presetned in the panel.
	 * @param newVar the new value of workLog
	 */
	private void setWorkLog (WorkLog newVar) {
		workLog = newVar;
	}

	/**
	 * Get the value of workLog
	 * The work log presetned in the panel.
	 * @return the value of workLog
	 */
	private WorkLog getWorkLog () {
		return workLog;
	}

	//
	// Other methods
	//

	/**
	 * Creates a work log panel with an empty WorkLog,
	 */
	public void JWorkLogPanel()
	{
	}


	/**
	 * Gets the work log shown by the panel.
	 * @return       WorkLog
	 */
	public WorkLog getWorkLog()
	{
	}


	/**
	 * Sets the work log presented in the panel.
	 * @param        newWorkLog The new work log to be presented in the panel.
	 */
	public void setWorkLog(WorkLog newWorkLog)
	{
	}


}
