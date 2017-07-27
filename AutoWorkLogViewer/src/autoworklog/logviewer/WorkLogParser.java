package autoworklog.logviewer;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * WorkLog file parser.
 * The parsing is unidirectional, the log file can not be recreated from the parsed data.
 */
public class WorkLogParser implements IWorkLogFactory {

	/**
	 * The keyword in each line, that is important to us.
	 */
	private static final String ACTIVE_KEYWORD="active";

	/**
	 * The maximum break between 2 active time points, that is not considered break.
	 */
	private static final Duration MAX_BREAK_DURATION=Duration.ofMinutes(10);

	/**
	 * The date pattern to be used in order to parse the log files.
	 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER=DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss uuuu");

	/**
	 * The name of the input file, stored this way in order to satisfy the
	 * IWorkLogFactory interface.
	 */
	private String inputFilePath;

	/**
	 * Creates a new work log parser based onj an input file.
	 * @param fileName The name of the input file.
	 */
	public WorkLogParser(String fileName) {
		inputFilePath=fileName;
	}

	/**
	 * Creates a new work log based oon the input file.
	 * @return WorkLog obtained form the input file.
	 * Throw IllegalArgumentException if the input file is not found.
	 */
	public WorkLog createWorkLog() {
		//the final work log to be returned
		WorkLog log=null;

		try {
			//the variables needed to produce the output
			PartialWorkLog partLog=new PartialWorkLog();
			LocalDateTime lastActive=null;

			//objects needed for file processing
			FileInputStream fis=new FileInputStream(inputFilePath);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(isr);
			String line, dateString;

			//reading the file line by line
			line=reader.readLine();
			while (line!=null) {
				//removing whitespaces from the beginning and the end.
				line=line.trim();

				//the line mist start with the active keyword in order to be taken in consideration
				if (line.indexOf(ACTIVE_KEYWORD)==0) {
					//extracting the date from the string representation from the line
					dateString=line.substring(ACTIVE_KEYWORD.length()).trim();
					lastActive=LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
					//adding the date time to the current strucutre
					partLog.addLolalDateTime(lastActive);
				}

				//reading the next line
				line=reader.readLine();
			}
			//obtaining the log
			log=partLog.export();

			//closing the input file
			reader.close();
			isr.close();
			fis.close();
		}
		catch (IOException e) {
			//throwing the exception further
			throw new RuntimeException("Can not open file: \""+inputFilePath+"\"!");
		}

		return log;
	}

	/**
	 * A class for modelling partially constructed log.
	 */
	private class PartialWorkLog {
		private WorkLog log;
		private WorkDay lastDay;
		private DateRange lastInterval;

		/**
		 * Creates a partial work log.
		 */
		public PartialWorkLog() {
			log=new WorkLog();
			lastDay=null;
			lastInterval=null;
		}

		/**
		 * Adds a local date time to the partial log.
		 * @param lastActive the input LocalDateTime instance.
		 *                  It must be after the work intervals from the partial log.
		 *
		 */
		public void addLolalDateTime(LocalDateTime lastActive) {
			LocalDate lastActiveDay=lastActive.toLocalDate();

			if (this.lastInterval==null) {
				lastInterval=new DateRange(lastActive, lastActive);
			}
			else {
				//determining if the current date should be added to an interval.
				Duration lastBreakDuration=Duration.between(this.lastInterval.getEndDate(), lastActive);

				//adding to an existing work interval if possible
				if (lastBreakDuration.compareTo(MAX_BREAK_DURATION)<=0 &&
						lastActiveDay.equals(lastInterval.getEndDate().toLocalDate())) {
					//updatring the bounds of the last interval
					lastInterval.setBounds(lastInterval.getStartDate(), lastActive);
				}
				else {
					//adding the last work interval to the last day
					addLastWorkInterval();
					//creating the new work interval
					lastInterval=new DateRange(lastActive, lastActive);
				}
			}
		}

		/**
		 * Adds the last work interval to the last work day.
		 */
		private void addLastWorkInterval() {
			LocalDate lastDate=lastInterval.getEndDate().toLocalDate();

			if (lastDay==null) {
				//creating a new work day
				lastDay=new WorkDay(lastDate);
				lastDay.addWorkInterval(lastInterval);
			}
			else
				if (!lastDate.equals(lastDay.getDay())) {
					//the work interval is in an other day
					log.addDay(lastDay);
					lastDay=new WorkDay(lastDate);
					lastDay.addWorkInterval(lastInterval);
				}
				else //adding any normal day to the work log.
					lastDay.addWorkInterval(lastInterval);
		}

		/**
		 * Completes the partial work log and exports it.
		 * @return WorkLog is the final work log obtained.
		 */
		private WorkLog export() {
			//adding the last work interval if it is not yet added
			if (lastInterval!=null) {
				addLastWorkInterval();
				lastInterval=null;
			}
			//adding the last work day, if it is nor already added
			if (lastDay!=null) {
				log.addDay(lastDay);
				lastDay=null;
			}
			//finally returning the complete log.
			return log;
		}
	}
}
