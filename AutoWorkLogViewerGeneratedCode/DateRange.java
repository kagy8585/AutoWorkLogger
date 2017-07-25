

/**
 * Class DateRange
 * Represents a range of time, with fixed beginning and end time. This class is
 * based on the java.util.Date class, that also stores time in milliseconds
 * precision.
 */
public class DateRange {

	/**
	 * The beggingin the range, in form of a Date.
	 */
	private Date startDate;
	/**
	 * The end Date of the range.
	 */
	private Date endDate;	public DateRange () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of startDate
	 * The beggingin the range, in form of a Date.
	 * @param newVar the new value of startDate
	 */
	private void setStartDate (Date newVar) {
		startDate = newVar;
	}

	/**
	 * Get the value of startDate
	 * The beggingin the range, in form of a Date.
	 * @return the value of startDate
	 */
	private Date getStartDate () {
		return startDate;
	}

	/**
	 * Set the value of endDate
	 * The end Date of the range.
	 * @param newVar the new value of endDate
	 */
	private void setEndDate (Date newVar) {
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 * The end Date of the range.
	 * @return the value of endDate
	 */
	private Date getEndDate () {
		return endDate;
	}

	//
	// Other methods
	//

	/**
	 * @return       Date
	 */
	public Date getStartDate()
	{
	}


	/**
	 * @return       Date
	 */
	public Date setStartDate()
	{
	}


	/**
	 * @return       Date
	 */
	public Date getEndDate()
	{
	}


	/**
	 * @return       Date
	 */
	public Date setEndDate()
	{
	}


	/**
	 * Creates a new date range with the starting and the ending date being the curent
	 * date.
	 */
	public void DateRange()
	{
	}


	/**
	 * Creates a new date range with dates given as parameters.
	 * @param        startDate The strat date of the new date range.
	 * @param        endDate The end date of the new date range.
	 */
	public void DateRange(Date startDate, Date endDate)
	{
	}


	/**
	 * Gets the duration of the DateRange.
	 */
	public void getDuration()
	{
	}


	/**
	 * Compares the curent DataRange with an other one. Returns negative if the curent
	 * data range is before the other one, 0 if the two data ranges are equivalent and
	 * positive if the curent data range is after the other one. The start ans the end
	 * dates are used for comparison.
	 * @return       int
	 * @param        other The other DataRange involved in the comparioson.
	 */
	public int compareTo(DataRange other)
	{
	}


	/**
	 * Compares the curent DataRange with an other object, and returns true only if
	 * they are equivalent.
	 * @return       boolean
	 * @param        other The other object involved in the comparioson.
	 */
	public boolean equals(Object other)
	{
	}


	/**
	 * Computs the hash code of the DataRange object.
	 * @return       int
	 */
	public int hashCode()
	{
	}


}
