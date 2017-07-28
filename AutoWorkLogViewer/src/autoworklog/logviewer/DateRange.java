package autoworklog.logviewer;


import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Class DateRange
 * Represents a range of time, with fixed beginning and end time. This class is
 * based on the java.util.Date class, that also stores time in milliseconds
 * precision.
 */
public class DateRange implements Comparable<DateRange>{

	/**
	 * The beggingin the range, in form of a Date.
	 */
	private LocalDateTime startDate;
	/**
	 * The end Date of the range.
	 */
	private LocalDateTime endDate;


	/**
	 * Creates a new date range with the starting and the ending date being the curent
	 * date.
	 */
	public DateRange() {
		startDate=LocalDateTime.now();
		endDate=LocalDateTime.now();
	}

	/**
	 * Creates a new date range with dates given as parameters.
	 * @param startDate The strat date of the new date range.
	 * @param endDate The end date of the new date range.
	 */
	public DateRange(LocalDateTime startDate, LocalDateTime endDate) {
		setBounds(startDate, endDate);
	}

	/**
	 * Get the value of startDate
	 * The beggingin the range, in form of a Date.
	 * @return the value of startDate
	 */
	public LocalDateTime getStartDate () {
	    return startDate;
	}

	/**
	 * Get the value of endDate
	 * The end Date of the range.
	 * @return the value of endDate
	 */
	public LocalDateTime getEndDate () {
	    return endDate;
	}



	/**
	 * Sets the bounds of the date range.
	 * @param newStartDate is the new start date of the range.
	 * @param newEndDate is the new end date of the range.
	 *                      It must be greater than the start date.
	 */
	public void setBounds(LocalDateTime newStartDate, LocalDateTime newEndDate) {
	    if (newStartDate.compareTo(newEndDate)>0)
	        throw new IllegalArgumentException("The end date must be after the start date!");
		startDate=newStartDate;
		endDate=newEndDate;
	}

	/**
	 * Gets the duration of the DateRange.
	 */
	public Duration getDuration() {
		return Duration.between(startDate, endDate);
	}

	/**
	 * Determines if the current date range contains a value.
	 * @param date the input date time value.
	 * @return is true if the current date range contains the input date time value,
	 * false otherwise.
	 */
	public boolean contains(LocalDateTime date){
		return (date.compareTo(startDate)>=0 &&
				date.compareTo(endDate)<=0);
	}

	/**
	 * Determines if the current data range overlaps with an other one.
	 * @param other the other DateRange.
	 * @return is true if the current DateRange overlaps with the other one.
	 */
	public boolean overlapsWith(DateRange other) {
		return (this.contains(other.startDate) || this.contains(other.endDate) ||
				other.contains(this.startDate) || other.contains(this.endDate));
	}


	/**
	 * Compares the current DataRange with an other one. Returns negative if the current
	 * data range is before the other one, 0 if the two data ranges are equivalent and
	 * positive if the current data range is after the other one. The start ans the end
	 * dates are used for comparison.
	 * @return int The return value is negative if the current date is before the other,
	 * 	zero if both are equal and positive if the other date is after the current one.
	 * @param    other The other DataRange involved in the comparison.
	 */
	public int compareTo(DateRange other) {
		int r=startDate.compareTo(other.startDate);

		if (r==0)
			r=endDate.compareTo(other.endDate);

		return r;
	}


	/**
	 * Compares the current DataRange with an other object, and returns true only if
	 * they are equivalent.
	 * @return       boolean
	 * @param        other The other object involved in the comparioson.
	 */
	public boolean equals(Object other) {
		if (other instanceof DateRange) {
			DateRange otherRange=(DateRange)other;

			return (startDate.equals(otherRange.startDate) && endDate.equals(otherRange.endDate));
		}
		else
			return false;
	}


	/**
	 * Computs the hash code of the DataRange object.
	 * @return       int
	 */
	public int hashCode() {
	    return startDate.hashCode()+endDate.hashCode();
	}

	/**
	 * Creates the string representation of the date range.
	 * @return is the date range in form of a string.
	 */
	@Override
	public String toString() {
		return "[ " + startDate + ", " + endDate + "]";
	}
}
