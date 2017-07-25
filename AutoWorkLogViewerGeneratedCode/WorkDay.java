

/**
 * Class WorkDay
 * Represents a work day, storing the work intervals.
 */
public class WorkDay {

	/**
	 * The sorted collection of work intervals.
	 */
	private SortedSet<DateRange> workIntervals;
	/**
	 * The date of the day.
	 */
	private Date date;	public WorkDay () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of workIntervals
	 * The sorted collection of work intervals.
	 * @param newVar the new value of workIntervals
	 */
	private void setWorkIntervals (SortedSet<DateRange> newVar) {
		workIntervals = newVar;
	}

	/**
	 * Get the value of workIntervals
	 * The sorted collection of work intervals.
	 * @return the value of workIntervals
	 */
	private SortedSet<DateRange> getWorkIntervals () {
		return workIntervals;
	}

	/**
	 * Set the value of date
	 * The date of the day.
	 * @param newVar the new value of date
	 */
	private void setDate (Date newVar) {
		date = newVar;
	}

	/**
	 * Get the value of date
	 * The date of the day.
	 * @return the value of date
	 */
	private Date getDate () {
		return date;
	}

	//
	// Other methods
	//

	/**
	 * Creates a new work day, without any work intervals.
	 * @param        date The Date specifying the date of the day.
	 */
	public void WorkDay(Date date)
	{
	}


	/**
	 * Adds a DataRange, as work interval to the work day.
	 * @param        interval The data range to be added.
	 */
	public void addWorkInterval(DateRange interval)
	{
	}


	/**
	 * Computes the total work of the day.
	 */
	public void getWorkDuration()
	{
	}


	/**
	 * Computes the minimum work interval of the day.
	 * @return       DateRange
	 */
	public DateRange getMinWorkInterval()
	{
	}


	/**
	 * Gets the maximum work interval of teh day.
	 * @return       DateRange
	 */
	public DateRange getMaxWorkInterval()
	{
	}


	/**
	 * Gets the day in form a of a Date object.
	 * @return       Date
	 */
	public Date getDay()
	{
	}


	/**
	 * Compares the curent day to an other one.
	 * @return       int
	 * @param        other The other work day involved in the comparison.
	 */
	public int compareTo(WorkDay other)
	{
	}


	/**
	 * Compares the curent WorkDay to an Object. Return true if the two are equivalent.
	 * @return       boolean
	 * @param        other The other object involved in the comparison.
	 */
	public boolean equals(Object other)
	{
	}


	/**
	 * Computes the Hash code of the curent day.
	 * @return       int
	 */
	public int hashCode()
	{
	}


}
