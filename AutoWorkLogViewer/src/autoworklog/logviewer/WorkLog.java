package autoworklog.logviewer;

import java.time.Duration;
import java.time.Period;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The object representation of a work log.
 */
public class WorkLog {

	/**
	 * The sored set of days.
	 */
	private SortedSet<WorkDay> days;

	/**
	 * Creates an empty work log.
	 */
	public WorkLog() {
		days=new TreeSet<WorkDay>();
	}


	/**
	 * Adds a new day to the work log.
	 * @param newDay The new work day to be added.
	 * This parameter can not be null.
	 */
	public void addDay(WorkDay newDay) {
		if (newDay==null)
			throw new IllegalArgumentException("Null work day can not be added!");
		days.add(newDay);
	}


	/**
	 * Gets the average duration of the work intervals in each day.
	 * @return Duration is the total duration divided by the number of days.
	 */
	public Duration getAverageWorkDuration() {
		//
		Duration totalWork=this.getTotalWorkDuration();
		Period total=Period.between(days.first().getDay(), days.last().getDay());

		//Plus 1 day because both startinf and ending days are included in teh range
		return  totalWork.dividedBy((total.getDays()+1));
	}


	/**
	 * Get the total work duration during the log.
	 * @return Duration is the total work duration during the logging period.
	 */
	public Duration getTotalWorkDuration() {
		Duration total=Duration.ofNanos(0);

		for (WorkDay day : days)
			total=total.plus(day.getWorkDuration());
		return total;
	}


	/**
	 * Gets the maximum work interval from the logging period.
	 * @return DateRange is the work interval with the maximum duration during the logging period.
	 */
	public DateRange getMaxWorkInterval() {
		DateRange maxInterval=null, temp;
		Duration maxDuration=Duration.ofNanos(0);

		//finding the maximum
		for (WorkDay day : days) {
			temp=day.getMaxWorkInterval();
			if (maxDuration.compareTo(temp.getDuration())<0) {
				maxDuration=temp.getDuration();
				maxInterval=temp;
			}
		}
		return maxInterval;
	}


	/**
	 * Gets the minum working interval from the log.
	 * @return DateRange is the minimum DataRange.
	 */
	public DateRange getMinWorkInterval() {
		DateRange minInterval=null, temp;
		//a long duration of 100 years is selected
		Duration minDuration=Duration.ofHours(24*365*1000);

		//finding the maximum
		for (WorkDay day : days) {
			temp=day.getMinWorkInterval();
			if (minDuration.compareTo(temp.getDuration())>0) {
				minDuration=temp.getDuration();
				minInterval=temp;
			}
		}
		return minInterval;
	}


	/**
	 * Exports all the known days from the log.
	 * @return WorkDay[] is an array of work days.
	 */
	public WorkDay[] getDays() {
		WorkDay[] daysArray=new WorkDay[days.size()];
		days.toArray(daysArray);

		return daysArray;
	}


	/**
	 * Determines if the current WorkLog is equal to an other object.
	 * @return boolean is true if the objects are equal, false otherwise.
	 * @param other The other object involved in the comparison.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof WorkLog) {
			WorkLog otherLog=(WorkLog)other;
			//converting the days to arrays in order to compare
			WorkDay[] daysArray=this.getDays(), otherDaysArray=otherLog.getDays();

			return Arrays.deepEquals(daysArray, otherDaysArray);
		}
		else
			return false;
	}


	/**
	 * Computes the hash code of the object.
	 * @return int is the hashcode of the object.
	 */
	@Override
	public int hashCode() {
		return days.hashCode();
	}
}
