package autoworklog.logviewer;

import java.time.Duration;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class WorkDay
 * Represents a work day, storing the work intervals.
 */
public class WorkDay implements Comparable<WorkDay>{

	/**
	 * The sorted collection of work intervals.
	 */
	private SortedSet<DateRange> workIntervals;
	/**
	 * The date of the day.
	 */
	private LocalDate date;

	/**
	 * Creates a new work day, without any work intervals.
	 * @param date The Date specifying the date of the day.
	 */
	public WorkDay(LocalDate date) {
	    //creating a tree set to store the intervals
	    workIntervals=new TreeSet<DateRange>();
		this.date=date;
	}


	/**
	 * Adds a DataRange, as work interval to the work day.
	 * @param interval The data range to be added.
	 */
	public void addWorkInterval(DateRange interval)
	{
	    //argument verification
	    LocalDate startDate=interval.getStartDate().toLocalDate();
	    LocalDate endDate=interval.getEndDate().toLocalDate();

	    if (!(date.equals(startDate) && date.equals(endDate)))
	        throw new IllegalArgumentException("The Data range exceeds the selected day.");
	    //testing that the new range does not overlap with any other existing one
		for (DateRange range : workIntervals) {
			if (range.overlapsWith(interval))
				throw new IllegalArgumentException("The curent date range overlaps with an existing one!");
		}

	    workIntervals.add(interval);
	}


	/**
	 * Computes the total work of the day.
     * @return Duration is the total work Duration during the day.
	 */
	public Duration getWorkDuration()
	{
        Duration totalDuration, temp;

        //summing up the duration of the work intervals from the day
        totalDuration=Duration.ofNanos(0);
        for (DateRange interval: workIntervals) {
            temp=interval.getDuration();
            totalDuration=totalDuration.plus(temp);
        }
        return totalDuration;
	}


	/**
	 * Computes the minimum work interval of the day.
	 * @return DateRange is the minimum WorkInterval id there is any, null otherwise.
	 */
	public DateRange getMinWorkInterval() {

        Duration minDuration, temp;
        DateRange minRange=null;

        //there should be an interval whoes length is smaller than 2 days
        //because all intervals must take part of a single day.
        minDuration = Duration.ofDays(2);
        for (DateRange interval : workIntervals) {
            temp=interval.getDuration();
            if (temp.compareTo(minDuration)<0) {
                minDuration = temp;
                minRange=interval;
            }
        }
        return minRange;
	}

	/**
	 * Gets the maximum work interval of the day.
	 * @return DateRange the maximum work interval of the day, if there is any, null otherwise.
	 */
	public DateRange getMaxWorkInterval() {

        Duration maxDuration, temp;
        DateRange maxRange=null;

        //there should be an interval whoes length is smaller than 2 days
        //because all intervals must take part of a single day.
        maxDuration = Duration.ofNanos(0);
        for (DateRange interval : workIntervals) {
            temp=interval.getDuration();
            if (temp.compareTo(maxDuration)>0) {
                maxDuration = temp;
                maxRange=interval;
            }
        }
        return maxRange;
	}


	/**
	 * Gets the day in form a of a LocalDate
	 * @return LocalDate the date of the day.
	 */
	public LocalDate getDay() {
	    return date;
	}


	/**
	 * Compares the current day to an other one.
	 * @return int is negative if teh curent day is before an other day, 0 if they are equal, and positive if it
     * after the other dat.
	 * @param other The other work day involved in the comparison.
	 */
	public int compareTo(WorkDay other) {
	    return date.compareTo(other.date);
	}


	/**
	 * Compares the current WorkDay to an Object. Return true if the two are equivalent.
	 * @return boolean is true if the objects are equivalent, false otherwise.
	 * @param other The other object involved in the comparison.
	 */
	public boolean equals(Object other) {
	    if (other instanceof WorkDay) {
	        WorkDay otherDay=(WorkDay) other;

	        return (date.equals(otherDay.date) && workIntervals.equals(otherDay.workIntervals));
        }
        else
            return false;
	}


	/**
	 * Computes the Hash code of the current day.
	 * @return int is the hash code depending on the state of the object.
	 */
	public int hashCode() {
	    return date.hashCode()+workIntervals.hashCode();
	}
}
