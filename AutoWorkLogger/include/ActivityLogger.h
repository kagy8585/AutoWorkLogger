
#ifndef ACTIVITYLOGGER_H
#define ACTIVITYLOGGER_H

#include <string>
#include <iostream>
#include <fstream>

#include <unistd.h>

#include "ActivityMonitor.h"
#include "ActivityMonitorFactory.h"

#include <assert.h>

class ActivityLogger
{
private:

	/**
	 * Keyword that determines when the user is active.
	 */
	static const std::string ACTIVE_KEYWORD;

	/**
	 * The period of the logger in seconds.
	 * The logger check activity in every period time.
	 */
	static const unsigned int PERIOD_S=5*60;



	//The main activity logger of the class
	ActivityMonitor * pMonitor;
	//the output log file
	std::ofstream log;
	volatile bool canRun;
public:

	/**
	 * Creates a new activity logger.
	 * @param  logFilePath The file path to the activity log. If the activity log does
	 * not exist, it is created.
	 * @param  monitorFactory The factory used to create the activity monitor for the
	 * logger.
	 */
	ActivityLogger(const std::string & logFilePath, const ActivityMonitorFactory & monitorFactory);


	/**
	 * Starts the data logging on the main thread.
	 */
	void startLogging();


	/**
	 * Stops the logging. This method should be called from an other thread.
	 */
	void stopLogging();

	/**
	 * Destructor that closes the output file at the end.
	 */
	~ActivityLogger();
};

#endif // ACTIVITYLOGGER_H
