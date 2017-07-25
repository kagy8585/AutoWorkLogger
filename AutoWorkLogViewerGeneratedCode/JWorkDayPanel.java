
public class JWorkDayPanel extends JPanel {

	/**
	 * The work day shown by the panel.
	 */
	private WorkDay day;	public JWorkDayPanel () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of day
	 * The work day shown by the panel.
	 * @param newVar the new value of day
	 */
	private void setDay (WorkDay newVar) {
		day = newVar;
	}

	/**
	 * Get the value of day
	 * The work day shown by the panel.
	 * @return the value of day
	 */
	private WorkDay getDay () {
		return day;
	}

	//
	// Other methods
	//

	/**
	 * Creates an empty work day panel.
	 */
	public void JWorkDayPanel()
	{
	}


	/**
	 * Gets the work day shown by the panel.
	 * @return       WorkDay
	 */
	public WorkDay getDay()
	{
	}


	/**
	 * Sets the work day shown by the panel.
	 * @param        newDay The new work day shown by the panel.
	 */
	public void setDay(WorkDay newDay)
	{
	}


}
