
#ifndef ACTIVITYLOGGER_H
#define ACTIVITYLOGGER_H

#include <string>
class ActivityLogger
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	ActivityLogger ();

	/**
	 * Empty Destructor
	 */
	virtual ~ActivityLogger ();



	/**
	 * Creates a new activity logger.
	 * @param  logFilePath The file paht to the activity log. If the activity log does
	 * not exist, it is created.
	 * @param  monitorFactory The factory used to create the activity monitor for the
	 * logger.
	 */
	 ActivityLogger (char * logFilePath, ActivityMonitorFactory & monitorFactory)
	{
	}


	/**
	 * Starts the data logging on the main thread.
	 */
	void startLogging ()
	{
	}


	/**
	 */
	void stopLogging ()
	{
	}

protected:

public:

protected:

public:

protected:


private:

	// Private attributes
	//  

	// The log file descriptor.
	int logFile;
public:

private:

public:


	// Private attribute accessor methods
	//  


	/**
	 * Set the value of logFile
	 * The log file descriptor.
	 * @param new_var the new value of logFile
	 */
	void setLogFile (int new_var)	 {
			logFile = new_var;
	}

	/**
	 * Get the value of logFile
	 * The log file descriptor.
	 * @return the value of logFile
	 */
	int getLogFile ()	 {
		return logFile;
	}
private:


	void initAttributes () ;

};

#endif // ACTIVITYLOGGER_H
