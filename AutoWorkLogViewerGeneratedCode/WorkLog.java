
public class WorkLog {

	/**
	 * The sored set of days.
	 */
	private SortedSet<WorkDay> days;	public WorkLog () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of days
	 * The sored set of days.
	 * @param newVar the new value of days
	 */
	private void setDays (SortedSet<WorkDay> newVar) {
		days = newVar;
	}

	/**
	 * Get the value of days
	 * The sored set of days.
	 * @return the value of days
	 */
	private SortedSet<WorkDay> getDays () {
		return days;
	}

	//
	// Other methods
	//

	/**
	 * Creates an empty work log.
	 */
	public void WorkLog()
	{
	}


	/**
	 * Adds a new day to the work log.
	 * @param        newDay The new work day to be added.
	 */
	public void addDay(WorkDay newDay)
	{
	}


	/**
	 * Gets the average duration of the work intervals in each day.
	 */
	public void getAverageWorkDuration()
	{
	}


	/**
	 * Get the total work duration during the log.
	 */
	public void getTotalWorkDuration()
	{
	}


	/**
	 * Gets the maximum work interval from the logging period.
	 * @return       DateRange
	 */
	public DateRange getMaxWorkInterval()
	{
	}


	/**
	 * Gets the minumum working intreval from the log.
	 * @return       DataRange
	 */
	public DataRange getMinWorkInterval()
	{
	}


	/**
	 * Exports all the known days from the log.
	 */
	public void getDays()
	{
	}


	/**
	 * Determines if the curent WorkLog is equal to an other object.
	 * @return       boolean
	 * @param        other The other object involved in the comparison.
	 */
	public boolean equals(Object other)
	{
	}


	/**
	 * Computes the hash code of the object.
	 * @return       int
	 */
	public int hasCode()
	{
	}


}
